import java.util.Scanner;// Импорт класса Scanner для ввода данных с клавиатуры

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);// Создание объекта Scanner для считывания ввода с консоли
        while (true) {// Цикл, который будет выполняться до тех пор, пока пользователь не введет 0
            System.out.println("Выберите задачу (1-5) или введите 0 для выхода:");
            int taskNumber = scanner.nextInt();// Считывание номера задачи с консоли

            if (taskNumber == 0) {// Проверка, ввел ли пользователь 0
                System.out.println("Выход из программы.");// Вывод сообщения о выходе из программы
                break;
            }
            // Переключение по номеру задачи
            switch (taskNumber) {
                case 1:
                    // Вывод сообщения с просьбой ввести число
                    System.out.println("Введите число для сиракузской последовательности:");
                    int n = scanner.nextInt();// Считывание числа с консоли
                    // Вывод количества шагов в сиракузской последовательности
                    System.out.println("Количество шагов: " + collatzSequence(n));
                    break;
                case 2:
                    System.out.println("Введите количество чисел и сами числа ряда:");
                    System.out.println("Сумма ряда: " + alternatingSum(scanner));
                    break;
                case 3:
                    System.out.println("Введите координаты клада:");
                    System.out.println("Минимальное количество указаний карты: " + findTreasure(scanner));
                    break;
                case 4:
                    System.out.println("Введите количество дорог:");
                    int roadCount = scanner.nextInt();
                    System.out.println("Номер дороги и максимальная высота грузовика: " + logisticMaximin(scanner, roadCount));
                    break;
                case 5:
                    System.out.println("Введите трехзначное число:");
                    System.out.println("Является ли число «дважды четным»? " + isDoubleEven(scanner));
                    break;
                default:
                    System.out.println("Задача не найдена");
            }
        }
    }

    // Функция для вычисления количества шагов в сиракузской последовательности.
    public static int collatzSequence(int n) {
        int steps = 0;// Инициализация счетчика шагов
        while (n != 1) {// Цикл, который будет выполняться до тех пор, пока n не станет равно 1
            if (n % 2 == 0) {// Проверка, четное ли число n
                n = n / 2;// Деление n на 2
            } else {// Если число n нечетное
                n = 3 * n + 1;
            }
            steps++;// Увеличение счетчика шагов
        }
        return steps;// Возврат количества шагов
    }

    // Функция для вычисления знакочередующейся суммы ряда.
    public static int alternatingSum(Scanner scanner) {
        int n = scanner.nextInt();// Считывание количества чисел с консоли
        int sum = 0;// Инициализация суммы
        for (int i = 0; i < n; i++) {// Цикл для обработки каждого числа
            int num = scanner.nextInt();// Считывание числа с консоли
            if (i % 2 == 0) { // Проверка, четное ли число i
                sum += num;// Добавление числа к сумме
            } else {// Если число i нечетное
                sum -= num;// Вычитание числа из суммы
            }
        }
        return sum;// Возврат суммы
    }

    // Функция для поиска клада
    public static int findTreasure(Scanner scanner) {
        int x = scanner.nextInt();// Считывание координаты x клада
        int y = scanner.nextInt(); // Считывание координаты y клада
        int currentX = 0;// Инициализация текущей координаты x
        int currentY = 0;// Инициализация текущей координаты y
        int steps = 0;// Инициализация счетчика шагов
        int minSteps = Integer.MAX_VALUE; // Минимальное количество шагов до клада

        // Цикл, который будет выполняться до тех пор, пока не будет введено "стоп"
        while (true) {
            String direction = scanner.next();// Считывание направления движения
            // Проверка, ввел ли пользователь "стоп"
            if (direction.equals("стоп")) {
                break;
            }
            int distance = scanner.nextInt();// Считывание расстояния движения

            // Переключение по направлению движен
            switch (direction) {
                case "север":
                    currentY += distance;
                    break;
                case "юг":
                    currentY -= distance;
                    break;
                case "восток":
                    currentX += distance;
                    break;
                case "запад":
                    currentX -= distance;
                    break;
            }

            steps++; // Увеличиваем счетчик шагов

            // Проверка, достигли ли мы клада
            if (currentX == x && currentY == y) {
                minSteps = Math.min(minSteps, steps); // Обновляем минимальное количество шагов
            }
        }

        // Возврат минимального количества шагов или 0, если клад не был найден
        return minSteps == Integer.MAX_VALUE ? 0 : minSteps;
    }

    // Функция для вычисления максимальной высоты грузовика.
    public static String logisticMaximin(Scanner scanner, int roadCount) {
        int bestRoad = 1;// Номер лучшей дороги (с наибольшей высотой грузовика), инициализируется 1
        int maxHeight = 0;// Максимальная высота грузовика, инициализируется 0
        // Цикл по дорогам
        for (int i = 1; i <= roadCount; i++) {
            int tunnelCount = scanner.nextInt();// Считывание количества туннелей на дороге
            int minTunnelHeight = Integer.MAX_VALUE; // Минимальная высота туннеля на текущей дороге, инициализируется максимальным значением
            for (int j = 0; j < tunnelCount; j++) {// Цикл по туннелям на текущей дороге
                int height = scanner.nextInt();// Считывание высоты туннеля
                minTunnelHeight = Math.min(minTunnelHeight, height);// Обновление минимальной высоты туннеля
            }
            // Проверка, является ли текущая дорога лучшей (с наибольшей высотой)
            if (minTunnelHeight > maxHeight || (minTunnelHeight == maxHeight && i < bestRoad)) {
                bestRoad = i; // Обновление номера лучшей дороги
                maxHeight = minTunnelHeight;// Обновление максимальной высоты
            }
        }
        return bestRoad + " " + maxHeight;// Возврат номера лучшей дороги и максимальной высоты
    }


    // Функция для проверки, является ли число «дважды четным».
    public static boolean isDoubleEven(Scanner scanner) {
        int num = scanner.nextInt();// Считывание числа
        int sum = 0;// Сумма цифр числа
        int product = 1;// Произведение цифр числа

        while (num > 0) {// Цикл, пока число больше 0
            int digit = num % 10;// Вычисление последней цифры числа
            sum += digit;// Добавление цифры к сумме
            product *= digit;// Умножение цифры на произведение
            num /= 10;// Удаление последней цифры из числа
        }

        return sum % 2 == 0 && product % 2 == 0;// Возврат true, если сумма и произведение цифр четные
    }
}