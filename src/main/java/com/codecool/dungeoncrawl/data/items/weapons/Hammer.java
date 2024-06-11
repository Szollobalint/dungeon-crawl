package com.codecool.dungeoncrawl.data.items.weapons;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.ui.Tiles;

public class Hammer extends Item implements Weapon {
    public Hammer(Cell cell) {
        super(cell);
    }

    public Hammer() {
        super(new Hammer().getCell());
    }

    @Override
    public int getDamage() {
        return 9;
    }

    @Override
    public int getAttackRange() {
        return 2;
    }

    @Override
    public String getTileName() {
        return "hammer";
    }
}
