package com.codecool.dungeoncrawl.data.items.weapons;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.ui.Tiles;

public class Sword extends Item implements Weapon {

    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public int getDamage() {
        return 6;
    }

    @Override
    public int getAttackRange() {
        return 1;
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}

