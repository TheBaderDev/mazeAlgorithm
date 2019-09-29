package maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Grid {
	private Cell[][] grid;
	private Cell start;
	private Cell end;

	public Grid( int size , int rs, int cs) {
		if( size == 1)
			throw new IllegalArgumentException();
		
    	Cell[][] rv = new Cell[2 * size + 1][2 * size + 1];
    	for (int r = 0; r < 2 * size + 1; r++) {
    		for (int c = 0; c < 2 * size + 1; c++) {
    			if (r % 2 == 0) {
    				rv[r][c] = new Cell(true, r, c);
    			} else if (c % 2 == 0) {
    				rv[r][c] = new Cell(true, r, c);
    			} else {
    				rv[r][c] = new Cell(false, r, c);
    			}
        	}
    	}
    	this.grid = rv;
    	
    	start = grid[rs][cs];
    	start.setStart(true);
    }
	
	public void generateMaze() {
		generateMaze(this.start);
	}
	
    public void generateMaze( Cell current) {
    	ArrayList<Cell> endArray = new ArrayList<Cell>();
		Stack<Cell> stack = new Stack<Cell>();
		current.setIsChecked( true );
		
		int cellsUnchecked = (int) Math.pow((this.grid.length - 1)/2, 2);
		
		while( cellsUnchecked != 0 ) {
			
			if( getNumberOfNeighbors(current) != 0 ) { //if current has any unvisited neighbors
				Cell n = getRandomNeighbor(current);
				if(getNumberOfNeighbors(current) > 1) //pushes current to stack
					stack.push(current);
				
				Cell wall = getWallBetween( current, n ); //removes wall
				wall.setSolidState( false );
				
				current = n;                     //moves current
				current.setIsChecked( true );
				cellsUnchecked--;
				
			} else if ( !stack.isEmpty() ) {
				endArray.add(current);
				current = getNextCellInStack( stack );
				if (current == null)
					break;
			}
		}
		
		if (!endArray.isEmpty()) {
			//endArray.get(endArray.size() - 2).setEnd(true);
			endArray.get(endArray.size()/2).setEnd(true);
		}

	}

	private Cell getNextCellInStack(Stack<Cell> stack) {
		Cell x = stack.pop();
		if (getNumberOfNeighbors(x) != 0) {
			return x;
		} else {
			if (stack.isEmpty())
				return null;
			return getNextCellInStack(stack);
		}
			
	}

	private Cell getWallBetween(Cell a, Cell b) {
		if( a.getRow() == b.getRow() ) {
			if (a.getCol() < b.getCol())
				return this.grid[a.getRow()][a.getCol() + 1];
			else
				return this.grid[a.getRow()][a.getCol() - 1];
		} else {
			if (a.getRow() < b.getRow())
				return this.grid[a.getRow() + 1][a.getCol()];
			else
				return this.grid[a.getRow() - 1][b.getCol()];
		}
	}

	private Cell getRandomNeighbor(Cell cell) {
		Random rand = new Random();
		
		ArrayList<Cell> array = new ArrayList<Cell>();

		try {
			Cell n = grid[cell.getRow()][cell.getCol() - 2]; //left Neighbor
			if(!n.isChecked()) {
				array.add(n);
			}
		} catch(IndexOutOfBoundsException e) {
			//doesn't increment
		}
		
		try {
			Cell n = grid[cell.getRow()][cell.getCol() + 2]; //right Neighbor
			if(!n.isChecked()) {
				array.add(n);
			}
		} catch(IndexOutOfBoundsException e) {
			//doesn't increment
		}
		
		try {
			Cell n = grid[cell.getRow() - 2][cell.getCol()]; //upperNeighbor
			if(!n.isChecked()) {
				array.add(n);
			}
		} catch(IndexOutOfBoundsException e) {
			//doesn't increment
		}
		
		try {
			Cell n = grid[cell.getRow() + 2][cell.getCol()]; //upperNeighbor
			if(!n.isChecked()) {
				array.add(n);
			}
		} catch(IndexOutOfBoundsException e) {
			//doesn't increment
		}
		
		int position = rand.nextInt(array.size());
		return array.get(position);
	}
	
	private int getNumberOfNeighbors(Cell cell) {
		int count = 0;
		try {
			Cell n = grid[cell.getRow()][cell.getCol() - 2]; //left Neighbor
			if(!n.isChecked()) {
				count++;
			}
		} catch(IndexOutOfBoundsException e) {
			//doesn't increment
		}
		
		try {
			Cell n = grid[cell.getRow()][cell.getCol() + 2]; //right Neighbor
			if(!n.isChecked()) {
				count++;
			}
		} catch(IndexOutOfBoundsException e) {
			//doesn't increment
		}
		
		try {
			Cell n = grid[cell.getRow() - 2][cell.getCol()]; //upperNeighbor
			if(!n.isChecked()) {
				count++;
			}
		} catch(IndexOutOfBoundsException e) {
			//doesn't increment
		}
		
		try {
			Cell n = grid[cell.getRow() + 2][cell.getCol()]; //upperNeighbor
			if(!n.isChecked()) {
				count++;
			}
		} catch(IndexOutOfBoundsException e) {
			//doesn't increment
		}
		return count;
	}

	public void printGrid() {
    	for (int r = 0; r < grid.length; r++) {
    		for (int c = 0; c < grid.length; c++) {
    			String val = getCellChar(r, c);
    			//String val = grid[r][c].isSolid() ? "*" : " ";
    			System.out.print( val + " ");
    		}
    		System.out.println();
    	}
	}

	private String getCellChar(int r, int c) {
		boolean hasLeft;
		boolean hasRight;
		boolean hasTop;
		boolean hasBot;
		
		if(grid[r][c].isEnd())
			return "E";
		else if(grid[r][c].isStart())
			return "S";
		else if(!grid[r][c].isSolid())
			return " ";
		
		try {
			hasLeft = grid[r][c - 1].isSolid();
		} catch (IndexOutOfBoundsException e) {
			hasLeft = false;
		}
		
		try {
			hasRight = grid[r][c + 1].isSolid();
		} catch (IndexOutOfBoundsException e) {
			hasRight = false;
		}
		
		try {
			hasTop = grid[r - 1][c].isSolid();
		} catch (IndexOutOfBoundsException e) {
			hasTop = false;
		}
		
		try {
			hasBot = grid[r + 1][c].isSolid();
		} catch (IndexOutOfBoundsException e) {
			hasBot = false;
		}
		
		if( hasLeft && hasRight && !hasTop && !hasBot )
			return "—";
		else if( !hasLeft && !hasRight && hasTop && hasBot )
			return "|";
		else if( hasLeft && !hasRight && !hasTop && hasBot )
			return "—"; //⌝
		else if( hasLeft && !hasRight && hasTop && !hasBot )
			return "—"; //⌟
		else if( !hasLeft && hasRight && !hasTop && hasBot )
			return "—"; //⌜
		else if( !hasLeft && hasRight && hasTop && !hasBot )
			return "—"; //⌞
		else if( hasLeft && hasRight && hasTop && hasBot )
			return "|";
		else if( hasLeft && hasRight && hasTop && !hasBot )
			return "—";
		else if( hasLeft && hasRight && !hasTop && hasBot )
			return "—";
		else if( !hasLeft && hasRight && hasTop && hasBot )
			return "|"; 
		else if( hasLeft && !hasRight && hasTop && hasBot )
			return "|";
		else if( hasLeft && !hasRight && !hasTop && !hasBot )
			return "—";
		else if( !hasLeft && hasRight && !hasTop && !hasBot )
			return "—";
		else if( !hasLeft && !hasRight && hasTop && !hasBot )
			return "|";
		else if( !hasLeft && !hasRight && !hasTop && hasBot )
			return "|";
		else
			return "*";
	}

}
