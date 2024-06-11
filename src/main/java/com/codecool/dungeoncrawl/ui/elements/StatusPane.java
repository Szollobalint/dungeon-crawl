package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;

public class StatusPane {
    public static final int RIGHT_PANEL_WIDTH = 200;
    public static final int RIGHT_PANEL_PADDING = 10;
    private final GridPane ui;
    private final Label healthTextLabel;
    private final Label healthValueLabel;
    private final Label inventoryTextLabel;
    private final Label inventoryValueLabel;

    private final Map<Integer, String> inventory = new HashMap<>();

    public StatusPane() {
        ui = new GridPane();
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        inventoryTextLabel = new Label("Inventory: ");
        inventoryValueLabel = new Label();
    }

    public BorderPane build() {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));

        ui.add(healthTextLabel, 0, 0);
        ui.add(healthValueLabel, 1, 0);
        ui.add(inventoryTextLabel, 0, 2);
        ui.add(inventoryValueLabel, 0, 3);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);
        return borderPane;
    }

    public void setHealthValue(String text) {
        healthValueLabel.setText(text);
    }

    public void addKeyToInventory(int id, String item){
        inventory.put(id, item);
    }
    private static int id = 7;
    public void addItemToInventory(String item){
        inventory.put(id++, item);
    }

    public Map<Integer, String> getInventoryValue(){
        return inventory;
    }

    public void updateInventoryLabel() {
        StringBuilder inventoryString = new StringBuilder();
        for (Map.Entry<Integer, String> entry : inventory.entrySet()) {
            inventoryString.append(entry.getValue()).append("\n");
        }
        inventoryValueLabel.setText(inventoryString.toString());
    }

}
