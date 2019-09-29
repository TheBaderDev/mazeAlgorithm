package maze;

import java.util.Stack;

public class MainView{

    public static void main( String[] args ) {
        Grid grid1 = new Grid( 20, 1, 1 );
        grid1.generateMaze();
        grid1.printGrid();
    }
    
}
