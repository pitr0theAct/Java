import Figures.*;// Импортирует все классы из пакета Figures.


public class Board { private char colorGame;// Хранит цвет игрока, который ходит (например, 'w' для белых, 'b' для черных).
    // Двумерный массив, представляющий шахматную доску, где каждый элемент - это фигура или null
    private Figure[][] fields = new Figure[8][8];

    // Устанавливает цвет игрока, который ходит.
    public void setColorGame(char colorGame) {
        this.colorGame = colorGame;// Сохраняет переданный цвет
    }

    // Возвращает цвет игрока, который ходит.
    public char getColorGame() {
        return colorGame;
    }

    // Меняет цвет игрока, который ходит, на противоположный.
    public void switchColor() {
        this.colorGame = (this.colorGame == 'w') ? 'b' : 'w';
    }

    // Инициализирует шахматную доску с фигурами в начальном положении.
    public void init() {
        // Инициализация пешек
        for (int i = 0; i < 8; i++) {
            fields[1][i] = new Pawn("P", 'w');
            fields[6][i] = new Pawn("P", 'b');
        }
        // Инициализация остальных фигур
        fields[0][0] = new Rook("R", 'w'); fields[0][7] = new Rook("R", 'w');
        fields[7][0] = new Rook("R", 'b'); fields[7][7] = new Rook("R", 'b');
        fields[0][1] = new Knight("N", 'w'); fields[0][6] = new Knight("N", 'w');
        fields[7][1] = new Knight("N", 'b'); fields[7][6] = new Knight("N", 'b');
        fields[0][2] = new Bishop("B", 'w'); fields[0][5] = new Bishop("B", 'w');
        fields[7][2] = new Bishop("B", 'b'); fields[7][5] = new Bishop("B", 'b');
        fields[0][3] = new King("K", 'w');
        fields[7][3] = new King("K", 'b');
        fields[0][4] = new Queen("Q", 'w');
        fields[7][4] = new Queen("Q", 'b');
    }

    // Возвращает строковое представление клетки доски (включая фигуру, если она есть).
    public String getCell(int row, int col) {
        // Получает фигуру, находящуюся на указанной клетке.
        Figure figure = fields[row][col];
        if (figure == null) {// Если на клетке нет фигуры.
            return "    ";// Возвращает пустую строку.
        }
        //Возвращает строку с цветом и именем фигуры.
        return " " + figure.getColor() + figure.getName() + " ";
    }

    // Выводит шахматную доску на консоль.
    public void print_board() {
        // Печатает верхнюю границу доски.
        System.out.println(" +----+----+----+----+----+----+----+----+");
        // Проходит по строкам доски в обратном порядке (от 7 до 0).
        for (int row = 7; row >= 0; row--) {
            System.out.print(row);// Выводит номер строки.
            // Проходит по столбцам доски.
            for (int col = 0; col < 8; col++) {
                // Выводит строковое представление текущей клетки.
                System.out.print("|" + getCell(row, col));
            }

            System.out.println("|");// Выводит вертикальную границу доски.
            System.out.println(" +----+----+----+----+----+----+----+----+");// Выводит горизонтальную границу доски.
        }
        //Выводит номера столбцов.
        System.out.println("    0    1    2    3    4    5    6    7 ");
    }

    //Метод move_figure реализует логику хода фигуры на шахматной доске
    public boolean move_figure(int row, int col, int row1, int col1) {
        Figure figure = fields[row][col];// Получаем фигуру с начальной
        // Проверяем, существует ли фигура и правильный ли цвет
        if (figure == null || figure.getColor() != colorGame) return false;

        // Проверяем возможность хода и чистоту пути
        if (figure.canMove(row, col, row1, col1) && isPathClear(row, col, row1, col1)) {
            // Проверяем, не атакуем ли мы своего короля или фигуру своего цвета
            Figure target = fields[row1][col1];
            if (target != null && target.getColor() == colorGame) {
                return false;
            }

            // Временно делаем ход
            fields[row1][col1] = figure;
            fields[row][col] = null;

            // Проверяем, не подставляем ли мы под шах своего короля
            if (isCheck(colorGame)) {
                // Если да - отменяем ход
                fields[row][col] = figure;
                fields[row1][col1] = target;
                return false;
            }
            return true;
        }
        return false;
    }

    // Проверяет, находится ли король указанного цвета под шахом.
    public boolean isCheck(char kingColor) {
        // Находим позицию короля
        int kingRow = -1, kingCol = -1;// Инициализируем координаты короля.
        for (int row = 0; row < 8; row++) {// Проходим по всем строкам доски.
            for (int col = 0; col < 8; col++) {// Проходим по всем столбцам
                Figure figure = fields[row][col];// Получаем фигуру на текущей позиции.
                // Проверяем, является ли фигура королем и соответствует ли его цвет.
                if (figure instanceof King && figure.getColor() == kingColor) {
                    // Запоминаем координаты короля.
                    kingRow = row;
                    kingCol = col;
                    break;
                }
            }
            // Переходим к следующей строке, так как король найден.
            if (kingRow != -1) break;
        }

        // Проверяем все фигуры противника
        for (int row = 0; row < 8; row++) {// Проходим по всем строкам доски.
            for (int col = 0; col < 8; col++) {// Проходим по всем столбцам доски.
                Figure figure = fields[row][col];// Получаем фигуру на текущей позиции.
                if (figure != null && figure.getColor() != kingColor) {// Проверяем, есть ли фигура на текущей позиции и является ли она фигурой противника.

                    //Проверяем, может ли фигура противника атаковать короля.
                    // Проверяем, свободен ли путь для атаки.
                    if (figure.canMove(row, col, kingRow, kingCol) &&
                            isPathClear(row, col, kingRow, kingCol)) {
                        return true;// Шах.
                    }
                }
            }
        }
        return false;// Шаха нет.
    }

    // Проверяет, есть ли мат королю указанного цвета
    public boolean isMate(char kingColor) {
        //Проверяем, находится ли король под шахом. Если нет, то мат невозможен.
        if (!isCheck(kingColor)) return false;

        // Проверяем все возможные ходы всех фигур
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = fields[row][col];
                if (figure != null && figure.getColor() == kingColor) {
                    // Проверяем все возможные клетки для хода
                    for (int newRow = 0; newRow < 8; newRow++) {
                        for (int newCol = 0; newCol < 8; newCol++) {
                            // Пропускаем клетки с фигурами своего цвета
                            if (fields[newRow][newCol] != null &&
                                    fields[newRow][newCol].getColor() == kingColor) {
                                continue;
                            }

                            //Проверяем, может ли фигура ходить на указанную позицию
                            if (figure.canMove(row, col, newRow, newCol) &&
                                    isPathClear(row, col, newRow, newCol)) {

                                // Пробуем сделать ход
                                Figure target = fields[newRow][newCol];
                                fields[newRow][newCol] = figure;
                                fields[row][col] = null;

                                // Проверяем, находится ли король под шахом после виртуального хода.
                                boolean stillInCheck = isCheck(kingColor);

                                // Возвращаем фигуры обратно
                                fields[row][col] = figure;
                                fields[newRow][newCol] = target;

                                // Если нашёлся ход, спасающий от шаха
                                // Если после виртуального хода шах снят, то мат не поставлен.
                                if (!stillInCheck) return false;
                            }
                        }
                    }
                }
            }
        }
        return true;// Если ни один ход не снимает шах, то король в мате.
    }

    //Метод, который проверяет, чист ли путь для хода фигуры.
    private boolean isPathClear(int row, int col, int row1, int col1) {
        // Если фигура - конь, то путь считается чистым, так как конь перепрыгивает через фигуры.
        if (fields[row][col] instanceof Knight) return true;

        // Определяем направление движения
        int deltaRow = Integer.signum(row1 - row);
        int deltaCol = Integer.signum(col1 - col);

        // Начинаем с клетки, следующей за начальной
        int currRow = row + deltaRow;
        int currCol = col + deltaCol;

        // Проверяем каждую клетку на пути
        while (currRow != row1 || currCol != col1) {// Пока не достигли конечной клетки.
            if (fields[currRow][currCol] != null) return false;// Если на пути есть фигура, путь не свободен.
            currRow += deltaRow;// Переходим к следующей клетке в том же направлении.
            currCol += deltaCol;// Переходим к следующей клетке в том же направлении.
        }
        return true;// Если дошли до конечной клетки без встреч с фигурами, путь свободен.
    }
}
