package roguelike.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private int width, height;
    private List<List<Tile>> mapTiles = new ArrayList<>(height);

    public Map(int width, int height) {
        this.width = width;
        this.height = height;

        generateMap();
        generateTerrain(new Tile(TileType.MOUNTAIN), 3, 5, 2);
        generateTerrain(new Tile(TileType.WATER), 5, 8, 1);
        generateTerrain(new Tile(TileType.FOREST), 2, 4, 4);
    }

    private void generateMap() {
        for (int i = 0; i < height; i++) {
            mapTiles.add(i, new ArrayList<Tile>());
            for (int j = 0; j < width; j++) {
                mapTiles.get(i).add(j, new Tile(TileType.GRASS));
            }
        }
    }

    private void generateTerrain(Tile tile, int minSize, int maxSize, int amount) {
        Random rand = new Random();
        for (int i = 0; i < amount; i++) {
            int terrainHeight = rand.nextInt(minSize, maxSize);
            int startHeight = rand.nextInt(1, this.height - terrainHeight);

            for (int j = 0; j < terrainHeight; j++) {
                int terrainWidth = rand.nextInt((int)(0.7 * maxSize), maxSize);
                int startWidth = rand.nextInt(3, this.width - maxSize) - rand.nextInt(1, 2);
                for (int h = 0; h < terrainWidth; h++) {
                    mapTiles.get(startHeight + j).set(startWidth + h, tile);
                }
            }
        }
    }

    public void displayMap() {
        String border = "+" + " -".repeat(width) + " +";
        System.out.println(border);
        for (List<Tile> list : mapTiles) {
            System.out.print(" ");
            for (Tile tile : list) {
                System.out.print( " " + tile.getSymbol());
            }
            System.out.println();
        }
        System.out.println(border);
    }
}