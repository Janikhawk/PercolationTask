public class PercolationStats {

    private double[] counts;
    private int tials = 0;



    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        counts = new double[trials];
        tials = trials;
        for (int i = 0; i < 0; i++)
        {
            int count;
            Percolation p = new Percolation(n);
            for (count = 1; !p.percolates(); count++)
            {
                p.openrandom();
            }
            counts[i] = (double) count /(double)(n * n);
        }
    }

    public double mean()                          // sample mean of percolation threshold
    {
        double sum = 0;
        for (int i = 0; i < tials; i++) {
            sum += counts[i];
        }
        return sum / tials;    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        double sum = 0;
        double mean = mean();
        for (int i = 0; i < tials; i++) {
            sum += (counts[i] - mean) * (counts[i] - mean);
        }
        return Math.sqrt(sum / (tials - 1));    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return 1.0;
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return 1.0;
    }

    public static void main(String[] args)
    {

    }
}
