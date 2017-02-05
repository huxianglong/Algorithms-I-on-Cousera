import java.util.Iterator;

/**
 * 
 */

/**
 * @author qwe95
 *
 */
public class Board {
	private int[][] board;       //  the array which store the information of the board.
	private int[][] twinboard;   //  the array of the twin board.
	private int N;               //  the dimension of the board.
	private int hamming;         //  the hamming distance.
	private int manhattan;       //  the manhattan distance.
	/*  construct a board from an n-by-n array of blocks
	 *  (where blocks[i][j] = block in row i, column j)
	 */
	public Board(int[][] blocks){
		// get a the input array, it's necessary.
		board = blocks.clone();
		N = board[0].length;
		assert board[N - 1].length == N;
	}

	//  board dimension n					   
	public int dimension() {
		return N;
	}              

	//  number of blocks out of place
	public int hamming() {
		for (int i =0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (!isInPlace(i, j)) hamming++;
		return hamming;
	}       
	/*  0 is the blank piece, we always consider it in place in order not to 
	 *  repeat counting.
	 */
	private boolean isInPlace(int i, int j) {
		if (board[i][j] == 0) return true;
		else if (board[i][j] == i + 1 + j + 1) return true;
		else return false; 
	}

	// sum of Manhattan distances between blocks and goal from the first grid to the last one.
	public int manhattan() {
		for (int i =0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (isInPlace(i, j)) continue;
				else manhattan += misplace(i, j);
		return manhattan;
	} 

	/*  misplace consider only the Manhattan distance of  single piece which is 
	 *  not in its position, which doesn't include blank piece(0). Actually these circumstances 
	 *  can be resolved in this method, however it would make the logic less clear.
	 */
	private int misplace(int i, int j) {
		int idealRow = board[i][j] % N - 1;  // the ideal row of the number;
		int idealCol = board[i][j] / N;      // the ideal col of the number;
		return Math.abs(i - idealRow) + Math.abs(j - idealCol);
	}

	//  is this board the goal board?
	/*  there is no need to write a new method, just use the result of manhattan() method.
	 */
	public boolean isGoal() {
		return manhattan() == 0;
	}         

	//  a board that is obtained by exchanging any pair of blocks
	/*  The choice of the pairs is totally random. 
	 *
	 *  Here I always change the one pair of non-blank grids among 
	 *  three grids, (0,0)(0,1)(1,0).
	 *
	 *  I used two extra private methods, specialExch() and exch() to realize the exchange, 
	 *  there might be a little problem.   
	 *  
	 */
	public Board twin() {
		twinboard = board.clone();
		specialExch(twinboard);
		return new Board(twinboard);
	}

	//  This is used to find two non-blank grids to exchange.
	private void specialExch(int[][] s) {
		if (s[0][0] == 0) exch(s[0][1], s[1][0]);
		else if (s[0][1] == 0) exch(s[0][0], s[1][0]);
		else exch(s[0][0], s[0][1]);
	}


	//  exchange the two given integers.
	private void exch(int a, int b) {
		int med;
		med = a;
		a = b;
		b = med;
	}


	// does this board equal y? I totally follow the same instructions on the book.
	public boolean equals(Object y) {
		if (this == y) return true;
		if (y == null) return false;
		if (this.getClass() != y.getClass()) return false;
		Board that = (Board) y;
		if (that.board != this.board) return false;
		return true;
	}

	// all neighboring boards.
	/*
	 *
	 *  Actually I don't quite know the meaning of iterable, so I just leave this 
	 *  problem at the moment.
	 *  
	 *  From the information provided in the checklist, I think it just need me to return an 
	 *  iterable data structure. 
	 *
	 * 
	 */
	public Iterable<Board> neighbors() {
		public Iterator<Board> iterator() { return new BoardIterator(); }
		private BoardIterator implements Iterator<Board> {
			private Board current = new Board(board);
			public boolean hasNext() { return !current.isGoal(); }
			public void remove() { /* not supported */ }
			public Board next()
			{
				Item item = current.item;
				current = current.next;
				return item;
			}
		}
	} 


	//  string representation of this board (in the output format specified below)
	/*
	 *  This follows the result on the specification.
	 */
	public String toString()  {
		StringBuilder s = new StringBuilder();
    	s.append(N + "\n");
    	for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
            	s.append(String.format("%2d ", board[i][j]));
        	}
        s.append("\n");
    	}
    	return s.toString();
	}            

	// unit tests (not graded)
	public static void main(String[] args) {}
}
