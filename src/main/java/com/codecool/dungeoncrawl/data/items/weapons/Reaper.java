package com.codecool.dungeoncrawl.data.items.weapons;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.ui.Tiles;

public class Reaper extends Item implements Weapon {
    public Reaper(Cell cell) {
        super(cell);
    }

    public Reaper() {
        super(new Reaper().getCell());
    }

    @Override
    public int getDamage() {
        return 13;
    }

    @Override
    public int getAttackRange() {
        return 3;
    }

    @Override
    public String getTileName() {
        return "reaper";
    }
}