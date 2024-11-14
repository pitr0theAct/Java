package Figures;

public class Rook extends Figure {
    public Rook(String name, char color) {
        super(name, color);
    }

    @Override
    // Метод для проверки возможности хода ладьи
    public boolean canMove(int row, int col, int row1, int col1) {
        return row == row1 || col == col1;
    }
}