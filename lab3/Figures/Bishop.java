package Figures;// Объявление пакета, в котором находится класс

// Объявление класса Bishop, наследующего от класса Figure
public class Bishop extends Figure {
    // Конструктор класса Bishop, принимающий имя и цвет фигуры
    public Bishop(String name, char color) {
        super(name, color);// Вызов конструктора родительского класса Figure, передавая ему имя и цвет
    }

    // Аннотация, указывающая на переопределение метода родительского класса
    @Override
    // Метод для проверки возможности хода слона
    public boolean canMove(int row, int col, int row1, int col1) {
        // Проверка, выполняется ли условие хода слона: перемещение по диагонали
        return Math.abs(row - row1) == Math.abs(col - col1);
    }
}