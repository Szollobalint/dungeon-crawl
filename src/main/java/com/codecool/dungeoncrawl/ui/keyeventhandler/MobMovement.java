package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.MobType;

import java.util.Random;

public abstract class MobMovement implements KeyHandler {
    public void moveEnemyRandomDirectionOnPlayerMove(GameMap map) throws InterruptedException {
        Random random = new Random();
        boolean chanceOfMobAndPlayerSameMove = random.nextBoolean();
        if (chanceOfMobAndPlayerSameMove) {
            map.getMobs().get(MobType.MOVING).forEach(mob -> mob.move(generateRandomDirection(), generateRandomDirection()));
            wait(200);
        }
    }

    public int generateRandomDirection(){
        Random random = new Random();
        return random.nextInt(3) - 1;
    }
}
