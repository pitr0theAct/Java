package Figures;// Объявление пакета, в котором находится класс

// Объявление абстрактного класса Figure
public abstract class Figure {
    private String name;
    private char color;

    // Конструктор класса Figure, принимающий имя и цвет фигуры
    public Figure(String name, char color) {
        this.name = name;
        this.color = color;
    }

    // Метод getName для получения имени фигуры
    public String getName() {
        return name;
    }

    // Метод getColor для получения цвета фигуры
    public char getColor() {
        return color;
    }

    // Метод canMove для проверки возможности хода фигуры
    public abstract boolean canMove(int row, int col, int row1, int col1);

    // Метод canAttack для проверки возможности атаки
    public boolean canAttack(int row, int col, int row1, int col1) {
        return canMove(row, col, row1, col1);
    }
}