import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	// number of trials
	private final int t;
	// stats of T percolation thresholds
	private double[] stats; 
	
	public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
	{
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException("");
		t = trials;
		stats = new double[t];
		
		double stat = 0;
		for (int i = 0; i < t; i++) {
			final Percolation pl = new Percolation(n);
			while (!pl.percolates()) {
				int r = StdRandom.uniform(1, n + 1);
				int c = StdRandom.uniform(1, n + 1);
				while (pl.isOpen(r, c)) {
					r = StdRandom.uniform(1, n + 1);
					c = StdRandom.uniform(1, n + 1);
				}
				pl.open(r, c);
			}
			stat = (double) pl.numberOfOpenSites() / (n * n);
			setStat(i, stat);
		}
	}
	
	public double mean()                          // sample mean of percolation threshold
 	{
	   return StdStats.mean(stats);
	}
   
	public double stddev()                        // sample standard deviation of percolation threshold
	{
		return StdStats.stddev(stats);
	}
   
	public double confidenceLo()                  // low endpoint of 95% confidence interval
	{
	   return mean() - 1.96 * stddev() / Math.sqrt(t);
	}
   
	public double confidenceHi()                  // high endpoint of 95% confidence interval
	{
	   return mean() + 1.96 * stddev() / Math.sqrt(t);
	}
	
	private void setStat(int i, double stat) {
		stats[i] = stat;
	}

	public static void main(String[] args)        // test client (described below)
	{
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);
		final PercolationStats pls = new PercolationStats(n, t);
		
		System.out.printf("mean=%f, stddev=%f, 95%% confidence interval=[%f, %f]\n", 
				pls.mean(), pls.stddev(), pls.confidenceLo(), pls.confidenceHi());
	}
}
