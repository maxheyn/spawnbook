package com.maxheyn.spawnbook;

import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;

public class SpawnbookConfig {
    public List<String> pages;
    public String author;
    public String title;
    public boolean isDefaultSettings;

    public SpawnbookConfig(List<String> pages, String author, String title){
        this.pages = pages;
        this.author = author;
        this.title = title;
    }



    public SpawnbookConfig(List<String> pages, String author, String title, boolean isDefaultSettings){
        this.pages = pages;
        this.author = author;
        this.title = title;
        this.isDefaultSettings = isDefaultSettings;
    }

    public static SpawnbookConfig getDefaultConfig(){
        List<String> defaultPages = new ArrayList<>();
        String defaultTitle = "Spawnbook";
        String defaultAuthor = "ServerAdmin";

        defaultPages.add("You can write in plaintext and it will work. The next page shows you how to use special formatting.");
        defaultPages.add("Sample Text\n§lBold§r\n§oItalics§r\n§nUnderlined§r\n§l§oBoldAndItalics§r\n§l§nBoldAndUnderline§r\n§o§nItalicsAndUnderline§r\n§dColored§0Words");
        defaultPages.add("Then next page shows how to hyperlink somewhere. You will have to edit it in §3§ospawnbook.json§0 to remove the quotes, so that it starts and ends with curly braces.");
        defaultPages.add("{\"text\":\"Book Writing Guide\",\"underlined\":true,\"color\":\"blue\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://minecraft.tools/en/book.php\"}}");
        return new SpawnbookConfig(defaultPages, defaultAuthor, defaultTitle, false);
    }
}