package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.MobType;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameLogic {
    private GameMap map;

    public GameLogic() {
        this.map = MapLoader.loadMap(0);
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public void setMap(int level) {
        Player player = map.getPlayer();
        int x = player.getX();
        int y = player.getY();
        this.map = MapLoader.loadMap(level);
        this.map.setPlayer(player);
        if (x >= 30) {
            x = 0;
            player.playerTeleport(x, y);
        } else if (x <= 1) {
            x = 31;
            player.playerTeleport(x, y);
        } else if (y <= 1) {
            player.playerTeleport(x, 20);
        } else if (y == 19) {
            player.playerTeleport(x, 1);
        }
        getCell(x, player.getY()).setActor(player);
        player.setCell(getCell(x, player.getY()));
    }

    public int getPlayerRoom() {
        return map.getPlayer().getCurrentRoom();
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }

    public List<Item> getPlayerItems() {
        return map.getPlayer().getItems();
    }

    public GameMap getMap() {
        return map;
    }

    public void moveMobs() {
        boolean move = true;
        while (move) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Error occured: " + e.getMessage());
            }
            for (Actor mob : map.getMobs().get(MobType.MOVING)) {
                if (!mob.isDead()) {
                    mob.move(generateRandomDirection(3), generateRandomDirection(3));
                }
            }
            move = false;
        }
    }

    public void teleportMobs() {
        boolean tp = true;
        while (tp) {
            for (Actor mob : map.getMobs().get(MobType.TELEPORTER)) {
                if (!mob.isDead()) {
                    mob.move(generateRandomDirection(10), generateRandomDirection(10));
                }
            }
            tp = false;
        }
    }


    public int generateRandomDirection(int bound) {
        Random random = new Random();
        return random.nextInt(bound) - bound / 2;
    }


}
