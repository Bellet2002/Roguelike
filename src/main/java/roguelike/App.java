package roguelike;

import roguelike.map.GameMap;

public class App 
{
    public static void main( String[] args )
    {
        GameMap map = new GameMap(15, 10, true);
        map.displayMap();
    }
}
