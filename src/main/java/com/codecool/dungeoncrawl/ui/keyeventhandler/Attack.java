package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Attack implements KeyHandler {
    public static final KeyCode code = KeyCode.SPACE;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if (code.equals(event.getCode())) {
            int damage = map.getPlayer().getDamage();
            int range = map.getPlayer().getAttackRange();
            map.getPlayer().attack(damage, range, map);
        }
    }

    @Override
    public void MobAutoMovement(GameMap map, boolean playerDead) throws InterruptedException {
    }

}

