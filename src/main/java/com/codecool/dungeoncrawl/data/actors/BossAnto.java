package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class BossAnto extends Actor{
    public BossAnto(Cell cell) {
        super(cell);
        this.health = 1000;
        this.damage = 50;
        this.attackRange = 2;
    }
    @Override
    public String getTileName() {
        return "bossAnto";
    }

}
