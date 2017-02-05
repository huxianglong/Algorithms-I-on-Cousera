import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author qwe95
 *
 */
/**
 * @author qwe95
 *
 */
/**
 * @author qwe95
 *
 */
public class Percolation 
{
    private int count;                            // The counter of open sites
    private int[][] gridState;
    private int n;
    private WeightedQuickUnionUF grid;
	// Create n-by-n grid, with all sites blocked.
    public Percolation(int n)                
    {   
    	//Notice that gridState is different from grid!!
    	this.n = n;
        if (n <= 0) throw new java.lang.IllegalArgumentException();
    	this.gridState = new int[n+1][n+1];	     // 0 means blocked, 1 means open.
    	// Initialization.
     	// The first n^2-1 is the grid, the n^2+1 is the virtual top root, the n^2+2 is the bottom one.
     	this.grid = new WeightedQuickUnionUF(n*n + 1 + 2); 
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col)       
    {
        if (row<1||row>n||col<1||col>n) throw new java.lang.IndexOutOfBoundsException();
        // The first one is (1,1) instead of (0,0)
    	// every site is opened once
    	if (gridState[row][col] != 1)
    	{
    	gridState[row][col] = 1;
    	count++;
        //StdOut.printf("%d,%d\n",row, col);
    	// Union the surrounding grids when open it.
    	// Consider the upper grid
    	if (row-1 >= 1)
    	{ if (isOpen(row - 1, col))  grid.union((row - 1)*n + col, (row - 2)*n + col);}   
    	// If in the first row   
    	else
    	{ grid.union(n*n + 1, (row - 1)*n + col);}
    	// Consider the lower grid
    	if (row+1 <= n)
    	{ if (isOpen(row + 1, col))  grid.union((row - 1)*n + col, row*n + col);}   
    	// If in the last row   
    	else
    	{ grid.union(n*n + 2, (row - 1)*n + col);}
    	// Consider the left grid
    	if ((col-1 >= 1) && (isOpen(row, col - 1)))   { grid.union((row - 1)*n + col, (row - 1)*n + col - 1);}
    	// Consider the right grid
    	if ((col+1 <= n) && (isOpen(row, col + 1))) { grid.union((row - 1)*n + col, (row - 1)*n + col + 1);}
    	}
    }

	// is site (row, col) open?
    public boolean isOpen(int row, int col)  
    { 
        if (row<1||row>n||col<1||col>n) throw new java.lang.IndexOutOfBoundsException();
        return gridState[row][col] == 1;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col)  
    { 
        if (row<1||row>n||col<1||col>n) throw new java.lang.IndexOutOfBoundsException();
        return grid.connected((row-1)*n + col, n*n + 1);
    }

    // number of open sites
    public int numberOfOpenSites()       
    { return count;}

    // does the system percolate?
    public boolean percolates()   
    { return grid.connected(n*n + 1, n*n + 2);}
    
     // test client (optional)
    public static void main(String args[])   
    {
    	int  n = 4;
        Percolation test = new Percolation(n);
        while (!test.percolates()) test.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));
        StdOut.print(test.numberOfOpenSites());
    }
}
