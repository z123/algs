public class PercolationStats {
    // Variables
    double[] results;

    // Perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {

        this.results = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
            double count=0;
            while(!p.percolates()) {
                int x = StdRandom.uniform(N);
                int y = StdRandom.uniform(N);
                if (!p.isOpen(x,y)) {
                    p.open(x,y);
                    count++;
                }
            }
            this.results[i] = count / (N*N);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.results);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - 1.96*this.stddev() / Math.sqrt(this.results.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + 1.96*this.stddev() / Math.sqrt(this.results.length);
    }

    // test client (described below)
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.printf("mean                    = %f\n", ps.mean());
        StdOut.printf("stdev =                 = %f\n", ps.stddev());
        StdOut.printf("95%% confidence interval = %f, %f\n", ps.confidenceLo(), ps.confidenceHi());
    }
}
