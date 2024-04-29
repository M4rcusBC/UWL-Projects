package Spring2024.CS220.Assignments.Assign05;

/**
 * A coordinate class to more easily track a row/column value as a single object.
 * 
 * @author asauppe
 */
public class Coordinate {

	private final int row;
	private final int col;
	
	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}
