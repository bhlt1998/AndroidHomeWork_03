package com.example.chapter3.homework;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
    private static final long serialVersionUID = -6099312954099962806L;
    private String title;


    public Item(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("马化腾"));
        items.add(new Item("王思聪"));
        items.add(new Item("马  云"));
        return items;
    }

    @Override
    public String toString() {
        return getTitle();
    }

}
