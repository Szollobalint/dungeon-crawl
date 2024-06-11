package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.MobType;
import com.codecool.dungeoncrawl.logic.GameLogic;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Up extends MobMovement implements KeyHandler {

    public static final KeyCode code = KeyCode.UP;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if (code.equals(event.getCode())) {
            map.getPlayer().move(0, -1);
            map.getMobs().get(MobType.MIMIC).forEach(mob -> {
                if (!mob.isDead()) {
                    mob.move(0, 1);
                }
            });
        }
    }

    @Override
    public void MobAutoMovement(GameMap map, boolean playerDead) throws InterruptedException {
        while (!playerDead) {
            moveEnemyRandomDirectionOnPlayerMove(map);
        };
    }
}
