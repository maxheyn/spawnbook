package com.maxheyn.spawnbook;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.io.FileNotFoundException;

public class Register {

    public static void bookCmd() throws CommandSyntaxException, FileNotFoundException {
        CommandRegistrationCallback.EVENT.register((dispatcher, tank) ->
                dispatcher.register(CommandManager.literal("spawnbook").requires(src -> src.hasPermissionLevel(0))
                        .executes(ctx -> {
                            try {
                                return give(ctx);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            return 0;
                        })));
    }

    public static int give(CommandContext<ServerCommandSource> c) throws CommandSyntaxException, FileNotFoundException {
        ServerCommandSource src = c.getSource();
        ServerPlayerEntity player = src.getPlayer();
        Spawnbook.giveSpawnBook(player);
        return 1;
    }

}
