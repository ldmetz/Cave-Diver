//Levi Metzger
//Cave Diver

public class CaveCell {
    private int cellDepth;
    private int rowNum;
    private int columnNum;
    private boolean escapeRoute;

    /**
     * Constructs a cave cell
     * @param pCellDepth the depth of the cell
     * @param pRowNum the row in which the cell is located
     * @param pColumnNum the column in which the cell is located
     */

    public CaveCell(int pCellDepth, int pRowNum, int pColumnNum){
        cellDepth = pCellDepth;
        rowNum = pRowNum;
        columnNum = pColumnNum;
        escapeRoute = false;
    }

    /**
     * Gets the depth of the cell
     * @return the cell depth
     */

    public int getCellDepth(){
        return cellDepth;
    }

    /**
     * Gets the row of the cell
     * @return the row in which the cell is located
     */

    public int getRowNum() {
        return rowNum;
    }

    /**
     * Gets the column of the cell
     * @return the column in which the cell is located
     */

    public int getColumnNum() {
        return columnNum;
    }

    /**
     * Gets the boolean value indicating whether the cell is part of an escape route
     * @return whether the cell is part of an escape route
     */

    public boolean isEscapeRoute() {
        return escapeRoute;
    }

    /**
     * Sets the boolean value indicating whether the cell is part of an escape route
     * @param pEscapeRoute whether the cell is part of an escape route
     */

    public void setEscapeRoute(boolean pEscapeRoute){
        escapeRoute = pEscapeRoute;
    }
}
