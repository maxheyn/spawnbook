package com.maxheyn.spawnbook;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

public class SpawnbookConfig {
    public static String configPath = "config/spawnbook.json";
    public List<JsonObject[]> pages;
    public String author;
    public String title;
    public boolean isDefaultSettings;

    public SpawnbookConfig(List<JsonObject[]> pages, String author, String title){
        this.pages = pages;
        this.author = author;
        this.title = title;
    }



    public SpawnbookConfig(List<JsonObject[]> pages, String author, String title, boolean isDefaultSettings){
        this.pages = pages;
        this.author = author;
        this.title = title;
        this.isDefaultSettings = isDefaultSettings;
    }

    public static SpawnbookConfig getDefaultConfig(){
        List<JsonObject[]> defaultPagesList = new ArrayList<>();
        JsonObject[] defaultPages = new JsonObject[10];

        String defaultTitle = "Spawnbook";
        String defaultAuthor = "Server Admin";

        /* Generate Default Config Book
        *  This is probably a really stupid way to do it. */
        JsonObject jo = new JsonObject();
        jo.add("text", new JsonPrimitive("If you are the "));
        jo.add("color", new JsonPrimitive("reset"));
        defaultPages[0] = jo;

        JsonObject jo2 = new JsonObject();
        jo2.add("text", new JsonPrimitive("server admin"));
        jo2.add("color", new JsonPrimitive("dark_green"));
        jo2.add("bold", new JsonPrimitive(true));
        defaultPages[1] = jo2;

        JsonObject jo3 = new JsonObject();
        jo3.add("text", new JsonPrimitive(", check out the"));
        jo3.add("color", new JsonPrimitive("reset"));
        defaultPages[2] = jo3;

        JsonObject jo4 = new JsonObject();
        jo4.add("text", new JsonPrimitive(" config to edit this book.\n\nIf you are a "));
        jo4.add("color", new JsonPrimitive("reset"));
        defaultPages[3] = jo4;

        JsonObject jo5 = new JsonObject();
        jo5.add("text", new JsonPrimitive("player"));
        jo5.add("color", new JsonPrimitive("gold"));
        jo5.add("bold", new JsonPrimitive(true));
        defaultPages[4] = jo5;

        JsonObject jo6 = new JsonObject();
        jo6.add("text", new JsonPrimitive(" and you see this, tell your "));
        jo6.add("color", new JsonPrimitive("reset"));
        defaultPages[5] = jo6;

        JsonObject jo7 = new JsonObject();
        jo7.add("text", new JsonPrimitive("server admin"));
        jo7.add("color", new JsonPrimitive("dark_green"));
        jo7.add("bold", new JsonPrimitive(true));
        defaultPages[6] = jo7;

        JsonObject jo8 = new JsonObject();
        jo8.add("text", new JsonPrimitive(" to edit the config to update the book.\n\nIf you lose this book, type "));
        jo8.add("color", new JsonPrimitive("reset"));
        defaultPages[7] = jo8;

        JsonObject jo9 = new JsonObject();
        jo9.add("text", new JsonPrimitive("/spawnbook"));
        jo9.add("color", new JsonPrimitive("light_purple"));
        defaultPages[8] = jo9;

        JsonObject jo10 = new JsonObject();
        jo10.add("text", new JsonPrimitive(" to get it back."));
        jo10.add("color", new JsonPrimitive("reset"));
        defaultPages[9] = jo10;

        defaultPagesList.add(defaultPages);
        return new SpawnbookConfig(defaultPagesList, defaultAuthor, defaultTitle, false);
    }
}
