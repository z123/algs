public class Percolation {
    // variables
    int[][] grid;
    WeightedQuickUnionUF wquf;
    int N;


    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        this.N = N;
        this.grid = new int[N][N];
        this.wquf = new WeightedQuickUnionUF(N*N + 2);
        for (int i = 1; i <= N; i++) {
            this.wquf.union(i,0);
            this.wquf.union((N-1)*N+i, N*N+1);
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        this.grid[i][j] = 1;
        boolean top = i != 0;
        boolean left = j != 0;
        boolean right = j != this.grid.length - 1;
        boolean bottom = i != this.grid.length - 1;
        if (top && this.isOpen(i-1,j)) {
            this.wquf.union(this.N*i+j+1, this.N*(i-1)+j+1);
        }
        if (left && this.isOpen(i,j-1)) {
            this.wquf.union(this.N*i+j+1, this.N*i+(j-1)+1);
        }
        if (right && this.isOpen(i,j+1)) {
            this.wquf.union(this.N*i+j+1, this.N*i+(j+1)+1);
        }
        if (bottom && this.isOpen(i+1,j)) {
            this.wquf.union(this.N*i+j+1, this.N*(i+1)+j+1);
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
        return this.wquf.connected(0, i * j);
    }

    // does the system percolate?
    public boolean percolates() {
        return this.wquf.connected(0,N*N+1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(0,0);
        p.open(1,0);
        p.open(2,0);
        p.open(3, 0);
        p.open(4, 0);
        System.out.println(p.percolates());
    }
}
