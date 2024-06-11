package com.codecool.dungeoncrawl.data.items.weapons;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.ui.Tiles;

public class BattleAxe extends Item implements Weapon {
    public BattleAxe(Cell cell) {
        super(cell);
    }

    public BattleAxe() {
        super(new BattleAxe().getCell());
    }

    @Override
    public int getDamage() {
        return 8;
    }

    @Override
    public int getAttackRange() {
        return 2;
    }

    @Override
    public String getTileName() {
        return "battleAxe";
    }
}