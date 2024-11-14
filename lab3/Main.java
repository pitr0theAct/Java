import java.util.Scanner;// Импорт класса Scanner для считывания ввода с консоли

public class Main {
    public static void main(String[] args) {
        Board board = new Board();// Создание объекта класса Board
        board.init();// Инициализация доски
        board.setColorGame('w');// Установка цвета хода на белый

        boolean isGame = true;// Флаг, указывающий на продолжение игры
        Scanner in = new Scanner(System.in);// Создание объекта Scanner

        while (isGame) {// Цикл, продолжающийся до конца игры
            board.print_board();// Вывод доски на экран
            System.out.println();

            // Описание формата хода
            System.out.println("\u001B[32m" + "Управление:" + "\u001B[0m");
            System.out.println("----row col row1 col1: Ход фигуры из клетки (row, col) в (row1, col1)");

            // Переключение в зависимости от цвета хода
            switch (board.getColorGame()) {
                case 'w': System.out.println("\u001B[33m" + "Ход белых" + "\u001B[0m"); break;
                case 'b': System.out.println("\u001B[33m"+ "Ход черных" + "\u001B[0m"); break;
            }

            // Вывод запроса на ввод хода
            System.out.print("Введите ход: ");
            // Блок try-catch для обработки возможных ошибок
            try {
                String inputLine = in.nextLine();// Считывание строки ввода с консоли
                String[] coords = inputLine.split(" ");// Разделение строки на массив строк по пробелу

                // Проверка на корректность количества координат
                if (coords.length != 4) {
                    System.out.println("Неверный формат ввода! Введите четыре числа через пробел.");
                    continue;
                }

                // Преобразование координат в целые числа
                int row = Integer.parseInt(coords[0]);
                int col = Integer.parseInt(coords[1]);
                int row1 = Integer.parseInt(coords[2]);
                int col1 = Integer.parseInt(coords[3]);

                // Проверка координат на корректность
                if (row < 0 || row > 7 || col < 0 || col > 7 ||
                        row1 < 0 || row1 > 7 || col1 < 0 || col1 > 7) {
                    System.out.println("Координаты должны быть в диапазоне от 0 до 7!");
                    continue;
                }

                if (!board.move_figure(row, col, row1, col1)) {
                    System.out.println("Ошибка! Невозможный ход!");// Вывод сообщения об ошибке
                    continue;// Переход к следующей итерации цикла
                }


                char oppositeColor = (board.getColorGame() == 'w') ? 'b' : 'w';// Определение цвета противника
                // Проверка на шах противнику
                if (board.isCheck(oppositeColor)) {
                    // Проверка на мат противнику
                    if (board.isMate(oppositeColor)) {
                        System.out.println("Мат! Игра окончена.");
                        isGame = false;
                    } else {
                        System.out.println("Шах!");
                    }
                }
                board.switchColor();// Переключение цвета хода


            } catch (NumberFormatException e) {// Обработка исключения при ошибке преобразования строки в число
                System.out.println("Ошибка! Введите числовые координаты!");
            } catch (Exception e) {// Обработка любого другого исключения
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
        in.close();
    }
}