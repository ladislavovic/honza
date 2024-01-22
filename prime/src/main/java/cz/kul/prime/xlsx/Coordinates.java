package cz.kul.prime.xlsx;

/**
 * Coordinates in excel like file.
 * 
 * It contains row and column coordinates (0 based). You can also use
 * {@link Coordinates#ALL} constant which means the coordinate is for whole row / column.
 * It is lets say wildcard.
 */
public class Coordinates {

    /**
     * It meas all rows or columns, no partucular one.
     */
    public static final int ALL = -1;

    private int row;

    private int column;

    private Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Coordinates getForRow(int row) {
        return new Coordinates(row, ALL);
    }

    public static Coordinates getForColumn(int column) {
        return new Coordinates(ALL, column);
    }

    public static Coordinates get(int row, int column) {
        return new Coordinates(row, column);
    }

    public static Coordinates getForAll() {
        return new Coordinates(ALL, ALL);
    }

    /**
     * Returns true, if both row and column match.
     * 
     * @param row
     *            given row
     * @param column
     *            given column
     * @return true if match
     */
    public boolean match(int row, int column) {
        return matchRow(row) && matchColumn(column);
    }

    /**
     * The given row match with the instance if the row of the instance is equal to the
     * given row or when the row of the instance is {@link Coordinates#ALL}
     * 
     * @param row
     *            given row
     * @return true if match
     */
    public boolean matchRow(int row) {
        return this.row == ALL || this.row == row;
    }

    /**
     * The given column match with the instance if the column of the instance is equal to
     * the given column or when the column of the instance is {@link Coordinates#ALL}
     * 
     * @param column
     *            given column
     * @return true if match
     */
    public boolean matchColumn(int column) {
        return this.column == ALL || this.column == column;
    }

}