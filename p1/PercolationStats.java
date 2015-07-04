public class PercolationStats {
    // Variables
    int[] results;

    // Perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {

        this.results = new int[T];
        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
        }

    }

    // sample mean of percolation threshold
    public double mean() {

        return 0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {

        return 0;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {

        return 0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0.0;
    }

    // test client (described below)
    public static void main(String[] args) {

    }
}
