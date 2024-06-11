package com.codecool.dungeoncrawl.data.items.misc;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;

public class Key extends Item {
    public Key(Cell cell, int id) {
        super(cell);
        this.id = id;
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
