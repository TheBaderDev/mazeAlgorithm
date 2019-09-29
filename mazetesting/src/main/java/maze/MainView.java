package maze;

public class MainView {

    public static void main( String[] args ) {
//        Grid grid1 = new Grid( 15, 1, 1 );
//        grid1.generateMaze();
//        grid1.printGrid();
    	
    	DonutGrid grid2 = new DonutGrid( 15 );
    	grid2.generateMaze();
    	grid2.printGrid();
    }
    
}
