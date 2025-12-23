//Levi Metzger
//Cave Diver

import java.util.Random;

public class Cave {
    private CaveCell[][] cave;
    private static final int GRID_SIZE = 10;

    /**
     * Constructs a cave populated with cells of random depths
     */

    public Cave(){
        Random randGen = new Random();
        cave = new CaveCell[GRID_SIZE][GRID_SIZE];

        for(int i = 0; i < GRID_SIZE; i++){
            for(int j = 0; j < GRID_SIZE; j++){
                cave[i][j] = new CaveCell(randGen.nextInt(10) + 1, i, j);
            }
        }
    }

    /**
     * Attempts an escape by calling the recursive dive method
     * @param depthRating the depth rating entered by the user
     * @return whether an escape route exists
     */

    public boolean escape(int depthRating){
        if(depthRating < 1 || depthRating > 10){
            throw new IllegalArgumentException();
        }

        return dive(depthRating, cave[0][0]);
    }

    /**
     * Dives through the cave recursively to determine whether an escape route exists.
     * Dives down the grid until it reaches a cell that surpasses the given depth rating, and then
     * attempts to dive to the right.
     * @param depthRating the depth rating of the diver
     * @param currentCell the cell from which to attempt an escape
     * @return whether an escape route exists from the given cell
     */

    public boolean dive(int depthRating, CaveCell currentCell){
        int row = currentCell.getRowNum();
        int col = currentCell.getColumnNum();

        if(currentCell.getCellDepth() > depthRating){
            return false;
        }

        if(row == GRID_SIZE - 1 && col == GRID_SIZE - 1){
            currentCell.setEscapeRoute(true);
            return true;
        }

        if(row < GRID_SIZE - 1 && dive(depthRating, cave[row + 1][col])){
            currentCell.setEscapeRoute(true);
            return true;
        }

        if (col < GRID_SIZE - 1 && dive(depthRating, cave[row][col + 1])){
            currentCell.setEscapeRoute(true);
            return true;
        }

        return false;
    }

    /**
     * Resets the cave by clearing any escape route
     */

    public void resetCave(){
        for(CaveCell[] row : cave){
            for(CaveCell cell : row){
                cell.setEscapeRoute(false);
            }
        }
    }

    /**
     * Gets the cave cell at the given location
     * @param x the column of the cell
     * @param y the row of the cell
     * @return the cell at location (x,y)
     */

    public CaveCell getCell(int x, int y){
        return cave[y][x];
    }
}
