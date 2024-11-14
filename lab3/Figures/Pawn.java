package Figures;// Объявление пакета, в котором находится класс

// Объявление класса Pawn, наследующего от класса Figure
public class Pawn extends Figure {
    //Поле isFirstMove, отслеживающее, был ли сделан первый ход пешкой
    private boolean isFirstMove = true;

    // Конструктор класса Pawn, принимающий имя и цвет фигуры
    public Pawn(String name, char color) {
        super(name, color);// Вызов конструктора родительского класса Figure, передавая ему имя и цвет
    }

    @Override
    // Метод для проверки возможности хода пешки
    public boolean canMove(int row, int col, int row1, int col1) {
        // Определение направления движения пешки: вверх для белых, вниз для черных
        int direction = (getColor() == 'w') ? 1 : -1;

        // Проверка, является ли ход прямым (без диагонали)
        if (col == col1) {  // Проверка, осуществляется ли ход на одну клетку вперед
            if (row + direction == row1) {// После первого хода устанавливаем флаг isFirstMove в false
                isFirstMove = false;  // После первого хода устанавливаем его в false
                return true;// Возвращаем true, если ход возможен
            } // Проверка, является ли ход первым ходом на две клетки
            else if (isFirstMove && row + 2 * direction == row1) {
                isFirstMove = false;// Устанавливаем флаг isFirstMove в false
                return true;// Возвращаем true, если ход возможен
            }
        }
        return false;// Возвращаем false, если ход невозможен
    }

    @Override
    // Метод для проверки возможности атаки пешки
    public boolean canAttack(int row, int col, int row1, int col1) {
        // Определяем направление атаки: вверх для белых, вниз для черных
        int direction = (getColor() == 'w') ? 1 : -1;
        // Проверяем, выполняются ли условия атаки: движение на одну клетку по диагонали вперед
        return (Math.abs(col - col1) == 1 && row + direction == row1);
    }
}