package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.elements.MainStage;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UI {
    public int KeyCounter;
    private Canvas canvas;
    private GraphicsContext context;
    private MainStage mainStage;
    private GameLogic logic;

    private Set<KeyHandler> keyHandlers;
    private int level = 0;

    public UI(GameLogic logic, Set<KeyHandler> keyHandlers) {
        this.canvas = new Canvas(
                logic.getMapWidth() * Tiles.TILE_WIDTH,
                logic.getMapHeight() * Tiles.TILE_WIDTH);
        this.logic = logic;
        this.context = canvas.getGraphicsContext2D();
        this.mainStage = new MainStage(canvas);
        this.keyHandlers = keyHandlers;
    }

    public void setUpPain(Stage primaryStage){
        Scene scene = mainStage.getScene();
        primaryStage.setScene(scene);
        logic.setup();
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
    }


    private void onKeyPressed(KeyEvent keyEvent) {
        KeyCounter++;
        for (KeyHandler keyHandler : keyHandlers) {
            keyHandler.perform(keyEvent, logic.getMap());
        }
        if(KeyCounter % 4 == 0) {
            logic.teleportMobs();
        }
        logic.moveMobs();
        refresh();
    }

    public void refresh() {
        System.out.println("Current UI: "+level);
        int playerRoom = logic.getPlayerRoom();
        if (level != playerRoom) {
            switch (playerRoom) {
                case 0:
                    logic.setMap(0);
                    break;
                case 1:
                    logic.setMap(1);
                    break;
                case 2:
                    logic.setMap(2);
                    break;
                case 3:
                    logic.setMap(3);
                    break;
                case 4:
                    logic.setMap(4);
                    break;
                case 5:
                    logic.setMap(5);
                    break;

            }
            this.level = playerRoom;
        }
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < logic.getMapWidth(); x++) {
            for (int y = 0; y < logic.getMapHeight(); y++) {
                Cell cell = logic.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        mainStage.setHealthLabelText(logic.getPlayerHealth());
        refreshPlayerInventory();
        mainStage.updateInventoryLabel();
    }

    public void refreshPlayerInventory() {
        List<Item> playerItems = logic.getPlayerItems();
        removeItemsNotInSet(mainStage.getInventoryValue(), playerItems);
        for (Item item : playerItems) {
            if (!mainStage.getInventoryValue().containsKey(item.getId())) {
                mainStage.setInventoryLabelValue(item.getId(), item.getTileName());
            } else if (!mainStage.getInventoryValue().containsKey(item.getTileName())) {
                mainStage.setInventoryLabelValue(item.getId(), item.getTileName());
            }
        }
    }

    private List<Integer> getItemIds(List<Item> playerItems) {
        return playerItems.stream()
                .map(Item::getId)
                .collect(Collectors.toList());
    }

    private Predicate<Map.Entry<Integer, String>> notInPlayerItems(List<Integer> playerItemIds) {
        return entry -> !playerItemIds.contains(entry.getKey());
    }

    private void removeItemsNotInSet(Map<Integer, String> itemMap, List<Item> playerItems) {
        List<Integer> playerItemIds = getItemIds(playerItems);
        System.out.println(playerItemIds);
        itemMap.entrySet().removeIf(notInPlayerItems(playerItemIds));
    }

}