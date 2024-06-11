package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.items.Item;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private Item item;
    private int x, y;

    public Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Cell getCell() {
        return this;
    }
    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    public Cell getCellXY(int x, int y) {return gameMap.getCell(x,y);}

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setItem(Item item) {
        if(gameMap.getCell(x,y).getItem() == null) {
            this.item = item;
        }
    }

    public void removeItem(){
        this.item = null;
    }

    public Item getItem() {
        return item;
    }

    public void setDead(){
        this.actor.setDead(true);
        if (!gameMap.getPlayer().isDead()) {
            gameMap.getPlayer().setHealth(gameMap.getPlayer().getHealth() + 8);
        }
    }
}