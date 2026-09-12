package btd.boards;    

public enum StateCell {

    /** State of an empty cell */
    EMPTY("   "),

    /** State of a path when the board is created*/
    PATH(""),

    // --- PIÈCES DU CHEMIN CONTINU ---
    /** State of a cell if the cell's path goes horizontally */
    PATH_H("───"),         // Ligne horizontale

    /** State of a cell if the cell's path goes vertically */
    PATH_V(" │ "),         // Ligne verticale
    
    // --- VIRAGES ---
    /** State of a cell if the cell's path goes from the left to the bottom */
    PATH_CORNER_LD("─┐ "), // (Left -> Down)
    /** State of a cell if the cell's path goes from the top to the right */
    PATH_CORNER_TR(" └─"), // (Top -> Right)
    /** State of a cell if the cell's path goes from the left to the top */
    PATH_CORNER_LU("─┘ "), // (Left -> Up)
    /** State of a cell if the cell's path goes from the bottom to the right */
    PATH_CORNER_BR(" ┌─"), // (Bottom -> Right)

    /** State of a cell when it's a tower */
    TOWER("  T");

    private final String symbol;

    private StateCell(String symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        return this.symbol;
    }
}