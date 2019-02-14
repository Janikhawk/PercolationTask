public class PercolationStats {

    private double[] counts;
    private int trials = 0;



    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        counts = new double[trials];
        this.trials = trials;
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
        for (int i = 0; i < trials; i++) {
            sum += counts[i];
        }
        return sum / trials;    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        double sum = 0;
        double mean = mean();
        for (int i = 0; i < trials; i++) {
            sum += (counts[i] - mean) * (counts[i] - mean);
        }
        return Math.sqrt(sum / (trials - 1));    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials) );
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials) );
    }

    public static void main(String[] args)
    {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);
        double mean = ps.mean();
        double stddev = ps.stddev();
        double a = ps.confidenceLo();
        double b = ps.confidenceHi();
        System.out.println(a);
        System.out.format("mean\t\t\t= %f\n", mean);
        System.out.format("stddev\t\t\t= %f\n", stddev);
        System.out.format("95%% confidence interval\t= %f, %f\n", a, b);
    }
}
