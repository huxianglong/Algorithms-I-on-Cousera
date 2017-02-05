import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
	// perform trials independent experiments on an n-by-n grid
	private double[] data;
	private int trials;
    public PercolationStats(int n, int trials)    
    {
        if (n<=0 || trials<=0) throw new java.lang.IllegalArgumentException();
    	data = new double[trials];
    	this.trials = trials;
    	for (int i = 0; i <= trials-1; i++)
    	{
    		Percolation grid = new Percolation(n);
    		while (!grid.percolates()) grid.open(StdRandom.uniform(1,n+1), StdRandom.uniform(1,n+1));
    		data[i] = (double) grid.numberOfOpenSites()/(n*n);
    	}
    }
    // sample mean of percolation threshold
    public double mean()                          
    { return StdStats.mean(data);}
    // sample standard deviation of percolation threshold
    public double stddev()                        
    { return StdStats.stddev(data);}
    // low  endpoint of 95% confidence interval
    public double confidenceLo()                  
    { return mean() - 1.96*stddev()/Math.sqrt(trials);}
    // high endpoint of 95% confidence interval
    public double confidenceHi()                  
    { return mean() + 1.96*stddev()/Math.sqrt(trials);}
    // test client (described below)
    public static void main(String[] args) 
    {
    	PercolationStats data = new PercolationStats(100, 1000);
        StdOut.print(data.mean());
    }       
}
