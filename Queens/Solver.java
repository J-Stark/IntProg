import java.text.NumberFormat;
/**
 * Class for solving the Queen Problems.
 *
 * @author J. Stark
 */
public class Solver
{
    private int noOfQueens;
    private int [] queens;
    private int noOfSolutions;
    private long startTime;
    private boolean showSolutions;
    private char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    
    public static void testSolver()
    {
        Solver solver = new Solver();
        solver.findAllSolutions(1);
        solver.findAllSolutions(2);
        solver.findAllSolutions(6);
        
        solver.findNoOfSolutions(1,12);
    }
    
    public void findAllSolutions(int noOfQueens)
    {
        this.noOfQueens = noOfQueens;
        queens = new int[noOfQueens];
        noOfSolutions = 0;
        showSolutions = true;
        startTime = System.currentTimeMillis();
        
        System.out.println("*****************************************************************");
        if (noOfQueens > 1)
        {
            System.out.println("Solution for " + noOfQueens + " queens:");
        }
        else
            System.out.println("Solution for " + noOfQueens + " queen:");
        System.out.println("");

        positionQueens(0);
        
        System.out.println("");
        long timeTaken = Math.max(System.currentTimeMillis() - startTime,1);
        System.out.println("A total of " + noOfSolutions + " solutions were found in " + timeTaken + " ms");
        
    }
    
    // Method that runs through an interval of different queen problems. Also handles a lot of the terminal printing.
    public void findNoOfSolutions(int min, int max)
    {
        showSolutions = false;
        System.out.println("****************************************");
        System.out.println("Queens  Solutions  Time(ms)  Solutions/ms");
        
        for (int i = min; i<=max ;i++)
        {
            this.noOfQueens = i;
            queens = new int[i];
            noOfSolutions = 0;
            startTime = System.currentTimeMillis();
            
            positionQueens(0);
            
            long timeTaken = Math.max(System.currentTimeMillis() - startTime,1);
            System.out.format(" %3d %,12d %, 8d %, 8d %n", noOfQueens, noOfSolutions, timeTaken, noOfSolutions / timeTaken);
        }        
    }
    
    // Recursive method that starts placing queens if they're legal.
    private void positionQueens(int row)
    {
        
        for (int i = 0; i<noOfQueens; i++)
        {
            if (legal(row,i))
            {
              queens[row] = i;
              if (row == noOfQueens - 1)
              {
                  printSolution();
              }
              else
              {
                  positionQueens(row + 1);
              }
            }
        }
    }
    
    // Checks if the given position is allowed
    private boolean legal(int row, int col)
    {   
        for (int i = 0; i < row; i++)
        {
            //Checks if a queen is already in the column, then checks both diagonals.
            if (queens[i] == col || Math.abs(row - i) == Math.abs(col - queens[i]))
            {
                return false;
            }
        }
        return true;
    }

    // Prints the location of all queens in the solutions
    private void printSolution()
    {       
        noOfSolutions++;
        if (showSolutions)
        {
            for(int i = 0; i< noOfQueens; i++)
            {
               System.out.print(convert(i,queens[i]) + " ");
            }
            System.out.println("");
        }
    }
    
    // Converts our array to proper chess board.
    private String convert(int row, int col)
    {
        return String.valueOf((char)('a' + col)) + Integer.toString(row + 1);
    }
}