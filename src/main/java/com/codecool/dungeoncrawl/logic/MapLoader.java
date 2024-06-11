package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.*;
import com.codecool.dungeoncrawl.data.items.misc.HealthPotion;
import com.codecool.dungeoncrawl.data.items.misc.Key;
import com.codecool.dungeoncrawl.data.items.weapons.BattleAxe;
import com.codecool.dungeoncrawl.data.items.weapons.Hammer;
import com.codecool.dungeoncrawl.data.items.weapons.Reaper;
import com.codecool.dungeoncrawl.data.items.weapons.Sword;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(int level) {
        InputStream is = null;
        switch (level) {
            case 0:
                is = MapLoader.class.getResourceAsStream("/Start.txt");
                break;
            case 1:
                is = MapLoader.class.getResourceAsStream("/Room1.txt");
                break;
            case 2:
                is = MapLoader.class.getResourceAsStream("/Room2.txt");
                break;
            case 3:
                is = MapLoader.class.getResourceAsStream("/Room3.txt");
                break;
            case 4:
                is = MapLoader.class.getResourceAsStream("/Room4.txt");
                break;
            case 5:
                is = MapLoader.class.getResourceAsStream("/Boss.txt");
                break;
            case 6:
                is = MapLoader.class.getResourceAsStream("/PortalRoom.txt");
                break;

        }
        Scanner scanner = new Scanner(is);
        RandomNumberGenerator gen = new RandomNumberGenerator();
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.setMob(new Skeleton(cell), MobType.MOVING);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell, new ArrayList<>()));
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell, gen.GenerateRandom(5));
                            break;
                        case 'e':
                            cell.setType(CellType.ENTRANCE);
                            break;
                        case 'c':
                            cell.setType(CellType.DOOR_CLOSED);
                            break;
                        case 'o':
                            cell.setType(CellType.DOOR_OPEN);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            map.setMob(new BuffedSkeleton(cell), MobType.STATIONARY);
                            break;
                        case 'B':
                            cell.setType(CellType.FLOOR);
                            map.setMob(new BossAnto(cell), new MobType[]{MobType.TELEPORTER, MobType.MIMIC});
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new BattleAxe(cell);
                            break;
                        case 'h':
                            cell.setType(CellType.FLOOR);
                            new Hammer(cell);
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            new Reaper(cell);
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            new HealthPotion(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}