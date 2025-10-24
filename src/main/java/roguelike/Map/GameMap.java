package roguelike.map;

import java.util.*;

public class GameMap {
    private static final int FRAME_SIZE = 800;
    private static final int UNIT_SIZE = FRAME_SIZE / 20;
    private int width = UNIT_SIZE, height = UNIT_SIZE;
    private List<List<Tile>> mapTiles = new ArrayList<>(height);
    private Random rand = new Random();
    private record TileDistance(Tile tile, int distance) {}

    public GameMap(boolean randomized) {
        generateMap();
        if (randomized) {
            generateRandomTerrain(TileType.MOUNTAIN, 1, 2, 0.8);
            generateRandomTerrain(TileType.WATER, 2, 4, 0.5);
            generateRandomTerrain(TileType.FOREST, 3, 5, 0.25);
        }
        generateRoad();
    }

    private void generateMap() {
        for (int i = 0; i < height; i++) {
            mapTiles.add(i, new ArrayList<Tile>());
            for (int j = 0; j < width; j++) {
                mapTiles.get(i).add(j, new Tile(TileType.GRASS, j, i));
            }
        }
    }

    private void generateRandomTerrain(TileType type, int minPatches, int maxPatches, double patchSize) {
        int maxDimension = Math.min(width, height);
        int amount = minPatches + rand.nextInt(maxPatches - minPatches + 1);
        int minSize = 1 + rand.nextInt(Math.max(1, (int)Math.ceil((maxDimension * patchSize) / 2)));
        int maxSize = minSize + rand.nextInt(Math.max(1, (int)Math.ceil(maxDimension * patchSize) - minSize));

        int zoneHeight = height / amount;
        int zoneWidth = width / amount;

        for (int i = 0; i < amount; i++) {
            int patchHeight = minSize + rand.nextInt(maxSize - minSize + 1);
            int patchWidth = minSize + rand.nextInt(maxSize - minSize + 1);
            int startY = i * zoneHeight + rand.nextInt(Math.max(1, zoneHeight - patchHeight));
            for (int y = startY; y < Math.min(height, startY + patchHeight); y++) {
                int rowOffset = rand.nextInt((int)Math.ceil(Math.max(1, patchWidth * 0.7)));
                int startX = i * zoneWidth + rand.nextInt(Math.max(1, zoneWidth - patchWidth)) + rowOffset;
                for (int x = startX; x < Math.min(width, startX + patchWidth); x++) {
                    if (rand.nextDouble() < 0.85) {
                        mapTiles.get(y).get(x).setType(type);
                    }
                }
            }
        }
    }

    private void generateRoad() {
        List<Tile> points = generateRandomRoadPoints();
        List<Tile> path = generatePath(points.get(0), points.get(1));
        for (Tile tile : path) {
            tile.setType(TileType.ROAD);
        }
        
    }

    public List<Tile> generatePath(Tile start, Tile end) {
        Map<Tile, Integer> distances = new HashMap<>();
        Map<Tile, Tile> previous = new HashMap<>();
        PriorityQueue<TileDistance> pq = new PriorityQueue<>(Comparator.comparingInt(TileDistance::distance));

        for (List<Tile> row : mapTiles) {
            for (Tile tile : row) {
                distances.put(tile, Integer.MAX_VALUE);
            }
        }

        distances.put(start, 0);
        pq.offer(new TileDistance(start, 0));

        while (!pq.isEmpty()) {
            TileDistance current = pq.poll();
            Tile tile = current.tile();

            for (Tile neighbour : findNeighbours(tile)) {
                int newDistance = distances.get(tile) + neighbour.getWeight();
                if (newDistance < distances.get(neighbour)) {
                    distances.put(neighbour, newDistance);
                    previous.put(neighbour, tile);
                    pq.add(new TileDistance(neighbour, newDistance));
                }
            }
        }
        
        List<Tile> path = new ArrayList<>();
        Tile current = end;
        while (current != null && previous.containsKey(current)) {
            path.add(current);
            current = previous.get(current);
        }
        path.add(start);
        Collections.reverse(path);

        return path;
    }

    public List<Tile> findNeighbours(Tile tile) {
        List<Tile> result = new ArrayList<>();
        int x = tile.getPosX();
        int y = tile.getPosY();

        if (y > 0) { result.add(mapTiles.get(y - 1).get(x)); }
        if (y < height - 1) { result.add(mapTiles.get(y + 1).get(x)); }
        if (x > 0) { result.add(mapTiles.get(y).get(x - 1)); }
        if (x < width - 1) { result.add(mapTiles.get(y).get(x + 1)); }

        return result;
    }

    public List<Tile> generateRandomRoadPoints() {
        int startEdge = rand.nextInt(4);
        int endEdge;
        do {
            endEdge = rand.nextInt(4);
        } while (startEdge == endEdge);

        List<Tile> result = new ArrayList<>();
        result.add(randomRoadPointsHelper(startEdge));
        result.add(randomRoadPointsHelper(endEdge));

        return result;
    }

    private Tile randomRoadPointsHelper(int edge) {
        switch (edge) {
            case 0:
                return mapTiles.get(0).get(rand.nextInt(width - 1));
            case 1:
                return mapTiles.get(height - 1).get(rand.nextInt(width - 1));
            case 2:
                return mapTiles.get(rand.nextInt(height - 1)).get(0);
            case 3:
                return mapTiles.get(rand.nextInt(height - 1)).get(width - 1);
            default:
                throw new IllegalArgumentException();
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

    public List<List<Tile>> getMap() { return this.mapTiles; }
    public Tile getTile(int x, int y) {
        int X = ((x % width) + width) % width;
        int Y = ((y % height) + height) % height;

        return mapTiles.get(Y).get(X);
    }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getFrameSize() { return FRAME_SIZE; }
}