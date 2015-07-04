public class Percolation {
    // variables
    int[][] grid;
    WeightedQuickUnionUF wquf;


    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        this.grid = new int[N][N];
        this.wquf = new WeightedQuickUnionUF(N*N + 2);
        for (int i = 1; i <= N; i++) {
            this.wquf.union(0,i);
            this.wquf.union(N*N+1,i*N);
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        this.grid[i][j] = 1;
        boolean top = i != 0;
        boolean left = j != 0;
        boolean right = j != this.grid.length - 1;
        boolean bottom = i != this.grid.length - 1;
        int newi = i+1;
        int newj = j+1;
        if (top && this.isOpen(i-1,j)) {
            System.out.println("TOP");
            this.wquf.union((newi-1)*newj, newi*newj);
        }
        if (left && this.isOpen(i,j-1)) {
            System.out.println("LEFT");
            this.wquf.union(newi*(newj-1), newi*newj);
        }
        if (right && this.isOpen(i,j+1)) {
            System.out.println("RIGHT");
            this.wquf.union((newi-1)*newj, newi*newj);
        }
        if (bottom && this.isOpen(i+1,j)) {
            System.out.println("BOT");
            this.wquf.union((newi-1)*newj, newi*newj);
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        return this.grid[i][j] == 1;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        i++;
        j++;
        return this.wquf.connected(i * j, 0);
    }

    // does the system percolate?
    public boolean percolates() {
        return this.wquf.connected(0, this.grid.length + 1);
    }

    // test client (optional)
    public static void main(String[] args) {

        Percolation p = new Percolation(5);
        p.open(0,0);
        p.open(1,0);
        p.open(2,0);
        p.open(3,0);
        p.open(4,0);
        System.out.println(p.percolates());
    }
}
