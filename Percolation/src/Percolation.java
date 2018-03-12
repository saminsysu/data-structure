import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	// record whether grid is opened or not
	private boolean[] sites;
	// record size of Percolation
	private final int size;
	// number Of Open Sites
	private int numOpenSite = 0;
	
	private final WeightedQuickUnionUF uftop;
	private final WeightedQuickUnionUF uf;
	
	// create n-by-n grid, with all sites blocked
	public Percolation(int n) {
		uftop = new WeightedQuickUnionUF(n * n + 1);
		uf = new WeightedQuickUnionUF(n * n + 2);
		
		if (n <= 0)
			throw new IllegalArgumentException("Initial value less than 0");
		size = n;
		sites = new boolean[n * n];
		for (int i = 0; i < n * n; i++) {
			sites[i] = false;
		}
	}

	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		if (row <= 0 || row > size)
			throw new IllegalArgumentException("row is outside prescribed range");
		if (col <= 0 || col > size)
			throw new IllegalArgumentException("col is outside prescribed range");
		int index = (row - 1) * size + col - 1;
		if (sites[index])
			return;
		sites[index] = true;
		numOpenSite++;
		
		if (row == 1) {
			uf.union(index, size * size);
			uftop.union(index, size * size);
		}
		if (row == size) {
			uf.union(index, size * size + 1);
		}
		
		int r[] = {1, -1, 0, 0};
		int c[] = {0, 0, 1, -1};
		for (int i = 0; i < 4; i++) {
			int row2 = row + r[i];
			int col2 = col + c[i];
			if (row2 <= size && row2 > 0 && col2 <= size && col2 > 0 && isOpen(row2, col2)) {
				int index2 = (row2 - 1) * size + col2 - 1;
				uf.union(index, index2);
				uftop.union(index, index2);
			}	
		}
	}
	
	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		if (row <= 0 || row > size)
			throw new IllegalArgumentException("row is outside prescribed range");
		if (col <= 0 || col > size)
			throw new IllegalArgumentException("col is outside prescribed range");
		return sites[(row - 1) * size + col - 1];
	}
	
	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		if (row <= 0 || row > size)
			throw new IllegalArgumentException("row is outside prescribed range");
		if (col <= 0 || col > size)
			throw new IllegalArgumentException("col is outside prescribed range");
		return uftop.connected((row - 1) * size + col - 1, size * size);
	}
	
	// number of open sites
	public int numberOfOpenSites() {
		return numOpenSite;
	}
	
	// does the system percolate?
	public boolean percolates() {
		return uf.connected(size * size, size * size + 1);
	}
	
	// test client (optional)
	public static void main(String[] args) {
		
//		Percolation pl = new Percolation(3);
//		pl.open(1, 1);
//		System.out.println(pl.percolates());
//		pl.open(2, 1);
//		System.out.println(pl.percolates());
//		pl.open(3, 3);
//		System.out.println(pl.percolates());
//		pl.open(3, 2);
//		System.out.println(pl.percolates());
//		pl.open(3, 1);
//		System.out.println(pl.percolates());
//		System.out.println(pl.isFull(3, 3));
		
//		Percolation pl2 = new Percolation(2);
//		pl2.open(1, 1);
//		System.out.println(pl2.percolates());
//		pl2.open(2, 1);
//		System.out.println(pl2.percolates());
		
//		Percolation pl3 = new Percolation(1);
//		pl3.open(1, 1);
//		System.out.println(pl3.percolates());
		
	}
	
}