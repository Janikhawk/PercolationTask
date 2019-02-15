import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final double mean, stddev, confidenceLo, confidenceHi;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("N and T must be <= 0");
        }
        double[] counts = new double[trials];
        for (int i = 0; i < trials; i++)
        {
            Percolation p = new Percolation(n);
            while (!p.percolates())
            {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                p.open(row, col);
            }
            counts[i] = (double) p.numberOfOpenSites() /(double) (n * n);
        }
        mean = StdStats.mean(counts);
        stddev = StdStats.stddev(counts);
        confidenceLo = mean - ((CONFIDENCE_95 * stddev) / Math.sqrt(trials));
        confidenceHi = mean + ((CONFIDENCE_95 * stddev) / Math.sqrt(trials));
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            int n = Integer.parseInt(args[0]);
            int t = Integer.parseInt(args[1]);
            PercolationStats ps = new PercolationStats(n, t);
            double mean = ps.mean();
            double stddev = ps.stddev();
            double a = ps.confidenceLo();
            double b = ps.confidenceHi();
            StdOut.println(a);
            StdOut.printf("mean\t\t\t= %f\n", mean);
            StdOut.printf("stddev\t\t\t= %f\n", stddev);
            StdOut.printf("95%% confidence interval\t= %f, %f\n", a, b);
        }
    }
}