package Code;

import java.util.Random;

public class Board {
	private final int YMIN = 0;
	private final int XMIN = 0;
	private final int YMAX = 600;
	private final int XMAX = 600;
	private final int MAXFOOD = 100;
	private final BoardCell[][] BOARD = new BoardCell[YMAX][XMAX];
	private final Random rnd;
	
	public Board() {
		for(int row=0; row<YMAX; row++) {
			for(int col=0; col<XMAX; col++) {
				BOARD[row][col] = new BoardCell(col, row);
			}
		}
		rnd = new Random();
	}
	
	public void setFood() {
		for(int i=0; i<MAXFOOD; i++) {
			int xC = rnd.nextInt(XMAX);
			int yC = rnd.nextInt(YMAX);
			
			BOARD[yC][xC].setFood(true); 
		}
	}
}
