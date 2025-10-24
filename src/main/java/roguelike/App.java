package roguelike;

import roguelike.map.GameMap;

public class App 
{
    public static void main( String[] args )
    {
        GameMap map = new GameMap(true);
        map.displayMap();
    }
}
