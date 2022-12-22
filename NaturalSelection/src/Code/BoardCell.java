package Code;

public class BoardCell {
	private int xCord;
	private int yCord;
	private boolean food;
	
	public BoardCell(int xCord, int yCord) {
		setYCord(yCord);
		setXCord(xCord);
		setFood(false);
	}

	public int getXCord() {
		return xCord;
	}

	public void setXCord(int xCord) {
		this.xCord = xCord;
	}

	public int getYCord() {
		return yCord;
	}

	public void setYCord(int yCord) {
		this.yCord = yCord;
	}

	public boolean isFood() {
		return food;
	}

	public void setFood(boolean food) {
		this.food = food;
	}
}
