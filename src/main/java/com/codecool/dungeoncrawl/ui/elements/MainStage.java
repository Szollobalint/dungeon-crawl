package com.codecool.dungeoncrawl.ui.elements;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;

import java.util.Map;

public class MainStage {
    private Canvas canvas;
    private Scene scene;
    private StatusPane statusPane;

    public MainStage(Canvas canvas) {
        this.canvas = canvas;
        statusPane = new StatusPane();
        scene = setUpScene();
    }

    private Scene setUpScene() {
        BorderPane borderPane = statusPane.build();
        borderPane.setCenter(canvas);
        Scene scene = new Scene(borderPane);
        return scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setHealthLabelText(String text) {
        this.statusPane.setHealthValue(text);
    }

    public void setInventoryLabelValue(int id, String text) {
        if (text == "key") {
            this.statusPane.addKeyToInventory(id, text);
        } else {
            this.statusPane.addItemToInventory(text);
        }
    }

    public Map<Integer, String> getInventoryValue() {
        return this.statusPane.getInventoryValue();
    }

    public void updateInventoryLabel() {
        this.statusPane.updateInventoryLabel();
    }
}
