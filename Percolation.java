/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Percolation {

    private boolean grid[][];

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                grid[i][j]=false;
            }
        }
    }

    public    void open(int row, int col)    // open site (row, col) if it is not open already
    {
        grid[row][col]=true;
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        return grid[row][col];
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        return !grid[row][col];
    }

    public     int numberOfOpenSites()       // number of open sites
    {
        return 1;
    }

    public boolean percolates()              // does the system percolate?
    {
        return true;
    }

    public static void main(String[] args)
    {}
}
