/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean grid[][];
    int gridSize = 0;
    WeightedQuickUnionUF uf;
    private int openCells = 0;

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        grid = new boolean[n+1][n+1];
        if (n <= 0) throw new IllegalArgumentException("some error");
        uf = new WeightedQuickUnionUF(n*n);
        gridSize = n;
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                grid[i][j] = false;
            }
        }
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if (row <= 0 || row > gridSize || col <= 0 || col > gridSize) throw new IllegalArgumentException("some error");
        grid[row][col] = true;
        openCells++;
        // left side
        if (col > 1 && isOpen(row, col-1)) { // left
            System.out.format("Left: Connecting %d to %d\n", (row - 1) * gridSize + col - 1, (row - 1) * gridSize + col - 2);
            uf.union((row - 1) * gridSize + col - 1, (row - 1) * gridSize + col - 2);
        }
        if (col < gridSize && isOpen(row, col+1)) // right
        {
            System.out.format("Right: Connecting %d to %d\n", (row - 1) * gridSize + col - 1, (row - 1) * gridSize + col);
            uf.union((row - 1) * gridSize + col - 1, (row - 1) * gridSize + col);
        }
        if (row > 1 && isOpen(row - 1, col)) // up
        {
            System.out.format("Up: Connecting %d to %d\n", (row - 1) * gridSize + col - 1, (row - 2) * gridSize + col - 1);
            uf.union((row - 1) * gridSize + col - 1, (row - 2) * gridSize + col - 1);
        }
        if (row < gridSize && isOpen(row+1, col)) // down
        {
            System.out.format("Connecting %d to %d\n", (row - 1) * gridSize + col - 1, row * gridSize + col - 1);
            uf.union((row - 1) * gridSize + col - 1, row * gridSize + col - 1);
        }

    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        StdOut.println("row "+row+ " col "+col);
        if (row <= 0 || row > gridSize || col <= 0 || col > gridSize) throw new IllegalArgumentException("some error");
        return grid[row][col];
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (row <= 0 || row > gridSize || col <= 0 || col > gridSize) throw new IllegalArgumentException("some error");
        /*for (int i = 0; i < gridSize; i++ )
        {
            if(uf.connected(col*gridSize))
        }*/
        return !grid[row][col];
    }

    public int numberOfOpenSites()       // number of open sites
    {
        return openCells;
    }

    public boolean percolates()              // does the system percolate?
    {
        /*boolean check = false;
        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                if (uf.connected(gridSize + i-1, (gridSize-1)*gridSize+j-1)) check = true;
            }
        }
        return check;*/

        for (int i = (gridSize * (gridSize - 1)); i < (gridSize * gridSize); i++) {
            for (int i2 = 0; i2 < gridSize; i2++) {
                if (uf.connected(i, i2)) return true;
            }
        }
        return false;
    }

    public void openrandom()
    {
        int indexX = StdRandom.uniform(0, gridSize-1);
        int indexY = StdRandom.uniform(0, gridSize-1);
        while(!isOpen(indexX, indexY))
        {
            open(indexX, indexY);
            indexX = StdRandom.uniform(0, gridSize-1);
            indexY = StdRandom.uniform(0, gridSize-1);
        }
    }

    public static void main(String[] args)
    {
        int n = 10;
        Percolation p = new Percolation(n);
        for (int j = 0; j <= n - 1; j++) {
            for (int i = 0; i <= n - 1; i++) {
                System.out.print(p.grid[i][j]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }

    }
}
