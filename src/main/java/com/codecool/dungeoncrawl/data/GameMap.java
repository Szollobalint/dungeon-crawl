package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.MobType;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;
    private Player player;
    private Map<MobType, Set<Actor>> mobs = Map.of(
            MobType.MOVING, new HashSet<>(),
            MobType.STATIONARY, new HashSet<>(),
            MobType.MIMIC, new HashSet<>(),
            MobType.TELEPORTER, new HashSet<>());

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Map<MobType, Set<Actor>> getMobs() {
        return mobs;
    }

    public void setMob(Actor mob, MobType mobType) {
        mobs.get(mobType).add(mob);
    }

    public void setMob(Actor mob, MobType[] mobTypes) {
        for (MobType mobType : mobTypes) {
            mobs.get(mobType).add(mob);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}


