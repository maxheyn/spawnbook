package com.maxheyn.spawnbook;

import com.google.gson.*;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import me.bymartrixx.playerevents.api.event.PlayerFirstJoinCallback;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;

public class Spawnbook implements DedicatedServerModInitializer {

    public static SpawnbookConfig config;
    public static SpawnbookConfig defaultConfig = SpawnbookConfig.getDefaultConfig();
    public static Path pathForTheConfig = Paths.get("config/spawnbook.json");
    public static Gson configDataStuff = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

    @Override
    public void onInitializeServer() {
        initConfig();
        PlayerFirstJoinCallback.EVENT.register((player, source) -> {
            try {
                giveSpawnBook(player);
            } catch (FileNotFoundException e) {
                System.out.println("Could not find the spawnbook.json config file.");
            }
        });
        try {
            Register.bookCmd();
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void giveSpawnBook(ServerPlayerEntity player) throws FileNotFoundException {
        ItemStack spawnbook = new ItemStack(Items.WRITTEN_BOOK);
        Text playerMsg = new LiteralText("You received a SpawnBook!").formatted(Formatting.AQUA, Formatting.BOLD);
        NbtCompound tags = new NbtCompound();

        Object obj = new JsonParser().parse(new FileReader("config/spawnbook.json"));
        JsonObject jo = (JsonObject) obj;

        String title = jo.get("Title").toString();
        String author = jo.get("Author").toString();
        JsonArray p = jo.getAsJsonArray("Pages");
        String[] pgs = new String[p.size()];
        for(int i = 0; i < p.size(); i++)
            pgs[i] = p.get(i).toString();
        NbtList bookContent = new NbtList();

        tags.putString("title", title.substring(1,title.length()-1));
        tags.putString("author", author.substring(1,author.length()-1));

        for (String page : pgs) {
            bookContent.add(NbtString.of(page));
        }

        tags.put("pages", bookContent);
        spawnbook.setNbt(tags);
        player.giveItemStack(spawnbook);
        player.sendMessage(playerMsg, false);
    }

    public static void initConfig(){
        try {
            if (pathForTheConfig.toFile().exists()){
                config = configDataStuff.fromJson(new String(Files.readAllBytes(pathForTheConfig)), SpawnbookConfig.class);
                if (config.isDefaultSettings){
                    config = defaultConfig;
                    Files.write(pathForTheConfig, Collections.singleton(configDataStuff.toJson(defaultConfig)));
                }
            } else {
                Files.write(pathForTheConfig, Collections.singleton(configDataStuff.toJson(defaultConfig)));
                config = defaultConfig;
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}


