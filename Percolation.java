/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean grid[][];
    int gridSize = 0;
    WeightedQuickUnionUF uf;
    private int openCells = 0;

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        grid = new boolean[n][n];
        if (n <= 0) throw new IllegalArgumentException("some error");
        uf = new WeightedQuickUnionUF(n*n);
        gridSize = n;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                grid[i][j] = false;
            }
        }
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if ( row-1 < 0 || row > gridSize || col-1 < 0 || col > gridSize) throw new IllegalArgumentException("some error");
        grid[row-1][col-1] = true;
        openCells++;
        //left side
        if ( col-2 >= 0 && isOpen(row - 1,col - 2))
        {
            uf.union(((row - 1)*gridSize + col - 1 ), (row - 1)*gridSize + col - 2);
        }
        //up side
        if ( row-2 >= 0 && isOpen(row - 2,col - 1))
        {
            uf.union(((row - 1)*gridSize + col - 1 ), (row - 2)*gridSize + col - 1);
        }
        //right side
        if ( col > gridSize && isOpen(row - 1, col))
        {
            uf.union(((row - 1)*gridSize + col - 1 ), (row - 1)*gridSize + col);
        }
        //bottom side
        if ( row > gridSize && isOpen(row - 2,col - 1))
        {
            uf.union(((row - 1)*gridSize + col - 1 ), (row)*gridSize + col - 1);
        }

    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        if ( row <= 0 || row > gridSize || col <= 0 || col > gridSize) throw new IllegalArgumentException("some error");
        return grid[row-1][col-1];
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if ( row <= 0 || row > gridSize || col <= 0 || col > gridSize) throw new IllegalArgumentException("some error");
        return !grid[row-1][col-1];
    }

    public int numberOfOpenSites()       // number of open sites
    {
        return openCells;
    }

    public boolean percolates()              // does the system percolate?
    {
        boolean check = false;
        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++){
                if (uf.connected(gridSize + i-1, (gridSize-1)*gridSize+j-1)) check = true;
            }
        }
        return check;
    }

    public static void main(String[] args)
    {
        int N = 10;
        Percolation p = new Percolation(N);
        for (int j = 0; j <= N - 1; j++) {
            for (int i = 0; i <= N - 1; i++) {
                System.out.print(p.grid[i][j]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }

    }
}
