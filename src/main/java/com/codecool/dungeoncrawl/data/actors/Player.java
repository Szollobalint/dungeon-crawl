package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.items.misc.HealthPotion;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.weapons.*;

import java.util.List;


public class Player extends Actor implements Drawable {
    private int damage;
    private final double attackSpeed;
    private int attackRange;
    private final List<Item> items;

    public Player(Cell cell, List<Item> items) {
        super(cell);
        this.items = items;
        this.damage = 4;
        this.attackSpeed = 1.0;
        this.attackRange = 1;
        this.currentRoom = 0;
    }

    @Override
    public int getDamage() {
        return damage;
    }
    

    @Override
    public int getAttackRange() {
        return attackRange;
    }


    @Override
    public String getTileName() {
        return "player";

    }

    public void addItem(Item item, Cell cell) {
        switch (item.getTileName()) {
            case "sword":
                Weapon sword = new Sword(cell);
                this.damage =  sword.getDamage();
                this.attackRange = sword.getAttackRange();
                System.out.println(this.damage);
                System.out.println(this.attackRange);
                items.add(item);
                break;
            case "battleAxe":
                Weapon battleAxe = new BattleAxe(cell);
                this.damage = battleAxe.getDamage();
                this.attackRange = battleAxe.getAttackRange();
                System.out.println(this.damage);
                System.out.println(this.attackRange);
                items.add(item);
                break;
            case "hammer":
                Weapon hammer = new Hammer(cell);
                this.damage = hammer.getDamage();
                this.attackRange = hammer.getAttackRange();
                System.out.println(this.damage);
                System.out.println(this.attackRange);
                items.add(item);
                break;
            case "reaper":
                Weapon reaper = new Reaper(cell);
                this.damage = 13;
                this.attackRange = reaper.getAttackRange();
                System.out.println(this.damage);
                System.out.println(this.attackRange);
                items.add(item);
                break;
            case "key":
                items.add(item);
                break;
            default:
                this.setDamage(4);
                this.setAttackRange(1);
                break;
        }
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public void move(int dx, int dy) {
        printCurrentRoom();
        Cell nextCell = getSuper().getCell().getNeighbor(dx, dy);
        if (nextCell.getType() == CellType.DOOR_CLOSED && hasKey()) {
            openDoor(nextCell);
            removeKey();
        }
        if (canMoveToCell(nextCell)) {
            moveToCell(nextCell);
            if (nextCell.getType() == CellType.ENTRANCE) {
                changeRoom();
            }
        }
    }

    private void printCurrentRoom() {
        System.out.println(currentRoom);
    }

    private boolean canMoveToCell(Cell nextCell) {
        return nextCell.getType() != CellType.WALL && nextCell.getType() != CellType.DOOR_CLOSED && nextCell.getActor() == null;
    }

    private void moveToCell(Cell nextCell) {
        Cell currentCell = getSuper().getCell();
        nextCell.setActor(getSuper());
        currentCell.setActor(null);
        getSuper().setCell(nextCell);
        if (nextCell.getItem() != null) {
            if (nextCell.getItem().getTileName().equals("healthPotion")) {
                HealthPotion healthPotion = (HealthPotion) nextCell.getItem();
                healthPotion.onPickup(this);
                nextCell.removeItem();
            } else {
                this.addItem(nextCell.getItem(), nextCell);
                nextCell.removeItem();
            }
        }
    }

    private void openDoor(Cell nextCell) {
        nextCell.setType(CellType.DOOR_OPEN);
    }

    private boolean hasKey() {
        return getItems().stream().anyMatch(item -> "key".equals(item.getTileName()));
    }

    private void removeKey() {
        getItems().stream()
                .filter(item -> "key".equals(item.getTileName()))
                .findFirst()
                .ifPresent(items::remove);
    }

    private void changeRoom() {
        int x = getX();
        int y = getY();
        if (x >= 30) {
            switch (currentRoom) {
                case 0:
                    currentRoom = 1;
                    break;
                case 1:
                    currentRoom = 2;
                    break;
                case 2:
                    currentRoom = 3;
                    break;
                case 3:
                    currentRoom = 4;
                    break;
                case 4:
                    currentRoom = 5;
                    break;
                case 5:
                    currentRoom = 6;
                    break;
            }
        }
    }

    private Actor getSuper() {
        return this;
    }

    public void playerTeleport(int x, int y) {
        Cell newCell = getCell().getCellXY(x, y);
        newCell.setActor(this);
        setCell(newCell);
    }
}
