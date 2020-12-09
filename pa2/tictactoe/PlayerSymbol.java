package pa2.tictactoe;

/** Enum representing either an X or O symbol. */
enum PlayerSymbol {
    X, O;

    @Override
    public String toString(){
        switch (this){
            case X: return "X";
            case O: return "O";
            default: return"";
        }
    }
}
