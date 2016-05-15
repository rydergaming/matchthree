package hu.unideb.inf.rydergaming.matchthree;

public class Globe {
	
	int value = -1;
	int row, column, x, y, dX, dY;
	
	public Globe(int value, int row, int column) {		
		this.value = value;
		this.row = row;
		this.column = column;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getdX() {
		return dX;
	}

	public void setdX(int dX) {
		this.dX = dX;
	}

	public int getdY() {
		return dY;
	}

	public void setdY(int dY) {
		this.dY = dY;
	}

	public boolean equalsTo(Globe obj) {
		if (obj == null)
			return false;
		if (this.value == obj.value)
			return true;
		return false;
	}

}
