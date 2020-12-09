package pa2.tictactoe;

/** Represents a board coordinate. */
class Coordinate {
    private static final Coordinate EMPTY = new Coordinate(-1, -1);

    private int row;
    private int col;

    Coordinate(int row, int col){
        this.row  = row;
        this.col = col;
    }

    public int row(){
        return row;
    }

    public int column(){
        return col;
    }

    @Override
    public String toString(){
        return "row: "+row+" "+"col: "+col;
    }

    @Override
    public boolean equals(Object other){
       Coordinate otherCoordinate = (Coordinate)other;
        return (row == otherCoordinate.row) && (col == otherCoordinate.col);
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(row)^Integer.hashCode(col);
    }
}
