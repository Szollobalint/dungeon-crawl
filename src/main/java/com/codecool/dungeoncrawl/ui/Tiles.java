package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import com.codecool.dungeoncrawl.data.weapons.*;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;
    private static final Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;

        public Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }

    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(25, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("buffedSkeleton", new Tile(30, 6));
        tileMap.put("yoda", new Tile(29, 3));
        tileMap.put("doubleSwordedYoda", new Tile(30, 3));
        tileMap.put("omega", new Tile(27, 4));
        tileMap.put("spaceKiddo", new Tile(24, 4));
        tileMap.put("bossAnto", new Tile(31, 6));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("entrance", new Tile(23, 26));
        tileMap.put("door_closed", new Tile(13, 11));
        tileMap.put("door_opened", new Tile(12, 11));
        tileMap.put("sword", new Tile(4,30));
        tileMap.put("reaper", new Tile(4,24));
        tileMap.put("battleAxe", new Tile(10,30));
        tileMap.put("hammer", new Tile(6,29));
        tileMap.put("healthPotion", new Tile(26, 23));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }

}
