//*****************************************************************************
//*  Name: Zhangeldy
//*  Date: 15.02.2019
//*  Description: Percolation implementation
//****************************************************************************

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private final int gridSize;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ug;
    private final int virtualTop;
    private final int virtualBottom;
    private int openCells = 0;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("incorrect size of teh grid");
        grid = new boolean[n+1][n+1];
        virtualTop = n * n;
        virtualBottom = virtualTop + 1;
        uf = new WeightedQuickUnionUF(virtualTop+2);
        ug = new WeightedQuickUnionUF(virtualTop+1);
        gridSize = n;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                grid[i][j] = false;
            }
        }
    }

    public void open(int row, int col) {
        validateSite(row,  col);
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        openCells++;
        if (row == 1) {
            uf.union(virtualTop, toSingleArray(row, col));
            ug.union(virtualTop, toSingleArray(row, col));
        }
        if (row == gridSize) {
            uf.union(virtualBottom, toSingleArray(row, col));
        }
        if (col > 1 && isOpen(row, col-1)) {
            uf.union(toSingleArray(row, col), toSingleArray(row, col-1));
            ug.union(toSingleArray(row, col), toSingleArray(row, col-1));
        }
        if (col < gridSize && isOpen(row, col+1)) {
            uf.union(toSingleArray(row, col), toSingleArray(row, col+1));
            ug.union(toSingleArray(row, col), toSingleArray(row, col+1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(toSingleArray(row, col), toSingleArray(row-1, col));
            ug.union(toSingleArray(row, col), toSingleArray(row-1, col));
        }
        if (row < gridSize && isOpen(row+1, col)) {
            uf.union(toSingleArray(row, col), toSingleArray(row+1, col));
            ug.union(toSingleArray(row, col), toSingleArray(row+1, col));
        }

    }

    public boolean isOpen(int row, int col) {
        validateSite(row,  col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col)  {
        validateSite(row,  col);
        if (!isOpen(row, col)) return false;
        return ug.connected(toSingleArray(row, col), virtualTop);
    }

    public int numberOfOpenSites() {
        return openCells;
    }

    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }

    public static void main(String[] args) {
        int n = 10;
        Percolation p = new Percolation(n);
        for (int j = 0; j <= n - 1; j++) {
            for (int i = 0; i <= n - 1; i++) {
                StdOut.print(p.grid[i][j]);
                StdOut.print("\t");
            }
            StdOut.print("\n");
        }
    }

    private int toSingleArray(int row, int col) {
        if (row == 1) return col - 1;
        else return (row - 1)*gridSize + col - 1;
    }

    private void validateSite(int row, int col) {
        if (row <= 0 || row > gridSize || col <= 0 || col > gridSize)
            throw new IllegalArgumentException("out of range exception error");
    }
}