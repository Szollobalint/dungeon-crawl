package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.GameMap;

public abstract class Actor implements Drawable {
    private Cell cell;
    int health = 30;
    int damage = 4;
    double attackSpeed = 1.0;
    int attackRange = 1;
    boolean isDead = false;

    int currentRoom = 0;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() != CellType.WALL && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void attack(int damage, int attackRange, GameMap map) {
        for (int rangeX = -attackRange; rangeX <= attackRange; rangeX++) {
            for (int rangeY = -attackRange; rangeY <= attackRange; rangeY++) {
                Cell targetCell = map.getCell(getX() + rangeX, getY() + rangeY);
                Actor targetActor = targetCell.getActor();
                if (targetActor != null && !targetActor.equals(this)) {
                    targetActor.takeDamage(damage);
                    if (targetActor.getAttackRange() >= attackRange) {
                        map.getPlayer().takeDamage(targetActor.getDamage());
                    }
                }
            }
        }
    }

    void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            cell.setDead();
            cell.setActor(null);
        }
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public int getDamage() {
        return damage;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }
}
