package Figures; // Объявление пакета, в котором находится класс

// Объявление класса King, наследующего от класса Figure
public class King extends Figure {
    // Конструктор класса King, принимающий имя и цвет фигуры
    public King(String name, char color) {
        super(name, color);// Вызов конструктора родительского класса Figure, передавая ему имя и цвет
    }

    @Override
    // Метод для проверки возможности хода короля
    public boolean canMove(int row, int col, int row1, int col1) {
        //Проверка, выполняется ли условие хода короля: перемещение на одну клетку в любом направлении
        return Math.abs(row - row1) <= 1 && Math.abs(col - col1) <= 1;
    }
}
