package maze;

public class DonutGrid extends Grid {

	//Only works well for grids with sizes of 9, 12 and 15 (div by 3)
	public DonutGrid(int size) {
		super(size, 1, 1);
		
		for(int r = 0; r < 2 * size + 1; r++) {
			for(int c = 0; c < 2 * size + 1; c++) {
				if (r < (size * 4/3) && r > (size * 2/3) && c < (size * 4/3) && c > (size * 2/3)) {
					grid[r][c].setSolidState(false);
					grid[r][c].setIsChecked(true);
				}
			}
		}
		
		grid[size * 2/3][size].setIsChecked( false );
		grid[size * 2/3][size].setSolidState( false );
	}
}
