package roguelike;

import roguelike.map.Map;

public class App 
{
    public static void main( String[] args )
    {
        Map map = new Map(15, 10);
        map.displayMap();
    }
}
