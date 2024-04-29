/**
 * A coordinate class to more easily track a row/column value as a single object.
 * 
 * @author asauppe
 */
public class Coordinate {

	private int row;
	private int col;
	
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
