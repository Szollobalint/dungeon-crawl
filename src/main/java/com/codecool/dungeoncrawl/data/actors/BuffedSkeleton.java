package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class BuffedSkeleton extends Actor{
    public BuffedSkeleton(Cell cell) {
        super(cell);
        this.damage = 2;
        this.health = 30;
        this.attackRange = 1;
    }
    @Override
    public String getTileName() {
        return "buffedSkeleton";
    }

}
