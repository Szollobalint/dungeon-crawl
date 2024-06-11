package com.codecool.dungeoncrawl.data.items.misc;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Item;

public class HealthPotion extends Item {
    public HealthPotion(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "healthPotion";
    }

    public int getRestoreAmount() {
        return 50;
    }

    public void onPickup(Player player) {
        player.setHealth(player.getHealth() + getRestoreAmount());
    }
}
