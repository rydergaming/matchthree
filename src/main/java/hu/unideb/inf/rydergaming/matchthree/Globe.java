package hu.unideb.inf.rydergaming.matchthree;

import javafx.scene.image.Image;

public class Globe {
	
	int value = -1;
	int row, column, x, y, dX, dY;
	Image sprite;
	
	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public Globe(int value, int row, int column) {		
		this.value = value;
		this.row = row;
		this.column = column;
		sprite = new Image(this.getClass()
        		.getResourceAsStream("/sprites/spr_" + Integer.toString(this.value+1)+".png"));
		this.x = row * 46;
		this.y = column * 46;
	}
	
	public void showValues() {
		String tmp = "row: " + Integer.toString(row) +
					"\tcolumn: " + Integer.toString(column) +
					"\tvalue: " + Integer.toString(value) +
					"\tX Y: " + Integer.toString(x) + " " + Integer.toString(y);
		MainApp.logger.warn(tmp);
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
