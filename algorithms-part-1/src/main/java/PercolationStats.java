import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	final private double[] trialsResults;
	
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	if(n <= 0 || trials <= 0) {
    		throw new IllegalArgumentException();
    	}
    	
        int siteSize = n;
        trialsResults = new double[trials];

        Percolation percolation;
        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(siteSize);
            int count = 0;
            do {
                int row = StdRandom.uniform(1, siteSize + 1);
                int col = StdRandom.uniform(1, siteSize + 1);
                percolation.open(row, col);
            } while (!percolation.percolates());
            count = percolation.numberOfOpenSites();
            trialsResults[i] = (double) count / (double) (siteSize * siteSize);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
    	return StdStats.mean(trialsResults);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	return StdStats.stddev(trialsResults);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	return mean() - (1.96 * stddev() / Math.sqrt(trialsResults.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	return mean()+ (1.96 * stddev() / Math.sqrt(trialsResults.length));
    }

   // test client (see below)
   public static void main(String[] args) {
	   
       int n = Integer.parseInt(args[0]);
       int t = Integer.parseInt(args[1]);
       PercolationStats percolationStats = new PercolationStats(n, t);
       
       System.out.printf("mean                     = %f\n", percolationStats.mean());
       System.out.printf("stddev                   = %f\n", percolationStats.stddev());
       System.out.printf("95%% confidence interval = [%f, %f]\n",
               percolationStats.confidenceLo(), percolationStats.confidenceHi());
   }
}
