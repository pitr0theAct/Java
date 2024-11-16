import java.util.HashSet;
import java.util.Arrays;


class main {
    // №1 Метод для нахождения наибольшей подстроки без повторяющихся символов
    public static String longestUniqueSubstring(String s) {
        int maxLength = 0; // Инициализация максимальной длины подстроки
        String longestSubstring = ""; // Инициализация самой длинной подстроки
        HashSet<Character> charSet = new HashSet<>(); // Создание множества для хранения уникальных символов

        int left = 0;//Левая граница
        for (int right = 0; right < s.length(); right++) {// Цикл по строке
            // Если символ уже существует в множестве, сдвигаем левую границу
            while (charSet.contains(s.charAt(right))) {// Пока символ уже есть в множестве
                charSet.remove(s.charAt(left));// Удаляем символ с левой границы
                left++;// Сдвигаем левую границу
            }
            charSet.add(s.charAt(right)); // Добавляем текущий символ

            // Проверяем, является ли текущая подстрока самой длинной
            if (right - left + 1 > maxLength) {// Если длина текущей подстроки больше максимальной
                maxLength = right - left + 1;// Обновляем максимальную длину
                longestSubstring = s.substring(left, right + 1);// Обновляем самую длинную подстроку
            }
        }
        return longestSubstring; // Возвращаем самую длинную подстроку без повторяющихся символов
    }

    // №2 Метод для объединения двух отсортированных массивов
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int n1 = arr1.length;// Длина первого массива
        int n2 = arr2.length;// Длина второго массива
        int[] mergedArray = new int[n1 + n2];// Создание результирующего массива

        int i = 0; // Указатель для первого массива
        int j = 0;// Указатель для второго массива
        int k = 0;// Указатель для результирующего массива

        // Пока не закончились элементы в обоих массивах
        while (i < n1 && j < n2) {
            if (arr1[i] < arr2[j]) {// Сравниваем элементы
                mergedArray[k] = arr1[i];// Меньший элемент добавляем в результирующий массив
                i++;// Переходим к следующему элементу первого массива
            } else {
                mergedArray[k] = arr2[j];// Меньший элемент добавляем в результирующий массив
                j++;// Переходим к следующему элементу второго массива
            }
            k++;// Переходим к следующему элементу результирующего массива
        }

        // Добавляем оставшиеся элементы из первого массива
        while (i < n1) {
            mergedArray[k] = arr1[i];
            i++;
            k++;
        }

        // Добавляем оставшиеся элементы из второго массива
        while (j < n2) {
            mergedArray[k] = arr2[j];
            j++;
            k++;
        }

        return mergedArray;// Возвращаем отсортированный массив
    }

    // №3 Метод для нахождения максимальной суммы подмассива
    public static int findMaxSubarraySum(int[] arr) {
        int n = arr.length;// Длина массива
        int maxSoFar = arr[0];// Инициализация максимальной суммы
        int maxEndingHere = arr[0];// Инициализация текущей максимальной суммы

        for (int i = 1; i < n; i++) {// Цикл по массиву
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]); // Находим максимум между текущим элементом и суммой текущего и предыдущего
            maxSoFar = Math.max(maxSoFar, maxEndingHere);// Обновляем максимальную сумму
        }

        return maxSoFar;// Возвращаем максимальную сумму подмассива
    }

    // №4 Метод для поворота матрицы на 90 градусов по часовой стрелке
    public static int[][] rotateMatrix(int[][] matrix) {
        int n = matrix.length;  // Количество строк
        int m = matrix[0].length; // Количество столбцов
        int[][] rotated = new int[m][n]; // Новый массив для хранения повернутой матрицы

        // Заполняем новый массив, поворачивая элементы
        for (int i = 0; i < n; i++) {// Цикл по строкам
            for (int j = 0; j < m; j++) {// Цикл по столбцам
                rotated[j][n - 1 - i] = matrix[i][j]; // Меняем местами элементы
            }
        }

        return rotated;// Возвращаем повернутую матрицу
    }

    // №5 Метод для нахождения пары элементов в массиве, сумма которых равна заданному числу
    public static int[] findTwoSum(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) { // Внешний цикл
            for (int j = i + 1; j < arr.length; j++) {// Внутренний цикл
                if (arr[i] + arr[j] == target) {// Если сумма равна target
                    return new int[]{arr[i], arr[j]};// Возвращаем пару элементов
                }
            }
        }

        return null; // Если пары нет, возвращаем null
    }

    // №6 Метод для нахождения суммы всех элементов в двумерном массиве
    public static int sumOf2DArray(int[][] matrix) {
        int sum = 0;// Инициализация суммы

        for (int i = 0; i < matrix.length; i++) {// Цикл по строкам
            for (int j = 0; j < matrix[i].length; j++) {// Цикл по столбцам
                sum += matrix[i][j];// Прибавляем элемент к сумме
            }
        }

        return sum;// Возвращаем сумму всех элементов
    }

    // №7 Метод для нахождения максимального элемента в каждой строке двумерного массива
    public static int[] findMaxElementInEachRow(int[][] matrix) {
        int[] maxElements = new int[matrix.length];// Создаем массив для хранения максимальных элементов каждой строки.
        // Размер массива равен количеству строк в исходной матрице.

        for (int i = 0; i < matrix.length; i++) {// Цикл по строкам матрицы
            int max = matrix[i][0];// Предполагаем, что максимальный элемент - первый элемент текущей строки.
            for (int j = 1; j < matrix[i].length; j++) {// Внутренний цикл по элементам текущей строки, начиная со второго.
                if (matrix[i][j] > max) {// Сравниваем текущий элемент с максимальным найденным на данный момент.
                    max = matrix[i][j];// Если текущий элемент больше, обновляем значение максимального элемента.
                }
            }
            maxElements[i] = max;// Записываем максимальный элемент текущей строки в результирующий массив.
        }

        return maxElements;// Возвращаем массив, содержащий максимальные элементы каждой строки.
    }


    // №8 Поворот двумерного массива на 90 градусов против часовой стрелки
    public static int[][] rotateMatrixAntiClockwise(int[][] matrix) {
        int n = matrix.length; // Количество строк в исходной матрице.
        int m = matrix[0].length; // Количество столбцов в исходной матрице.
        int[][] rotated = new int[m][n]; // Создаем новую матрицу для хранения повернутой матрицы.  Размеры меняются местами.

        // Заполняем новый массив, поворачивая элементы
        for (int i = 0; i < n; i++) {// Внешний цикл по строкам исходной матрицы.
            for (int j = 0; j < m; j++) {// Внутренний цикл по столбцам исходной матрицы.
                rotated[m - j - 1][i] = matrix[i][j]; // Меняем местами элементы
            }
        }

        return rotated;// Возвращаем повернутую матрицу.
    }

    public static void main(String[] args) {
        // №1 Наибольшая подстрока без повторяющихся символов
        String s = "abcabcbb";
        System.out.println("\n" + "\u001B[32m" + "№1:" + "\u001B[0m" + " Наибольшая подстрока без повторяющихся символов: " + longestUniqueSubstring(s));

        // №2 Объединение двух отсортированных массивов
        int[] arr1 = {15, 30, 35, 100};
        int[] arr2 = {5, 10, 11, 12};
        int[] merged = mergeSortedArrays(arr1, arr2);
        System.out.println("\u001B[32m" + "№2:" + "\u001B[0m" + " Объединенный массив: " + Arrays.toString(merged));

        // №3 Максимальная сумма подмассива
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("\u001B[32m" + "№3:" + "\u001B[0m" + " Максимальная сумма подмассива: "  + findMaxSubarraySum(nums));

        // №4 Поворот неквадратной матрицы на 90 градусов по часовой стрелке
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        int[][] rotated = rotateMatrix(matrix);
        System.out.println("\u001B[32m" + "№4:" + "\u001B[0m" + " Повернутая матрица по часовой стрелке:");
        for (int[] row : rotated) {
            System.out.println(Arrays.toString(row));
        }

        // №5 Найти пару элементов в массиве, сумма которых равна заданному числу
        int[] arrForPair = {10, 11, 3, 7};
        int target = 14;
        int[] pair = findTwoSum(arrForPair, target);
        if (pair != null) {
            System.out.println("\u001B[32m" + "№5:" + "\u001B[0m" + " Пара с суммой "  + target + ": " + Arrays.toString(pair));
        } else {
            System.out.println("\u001B[32m" + "№5:" + "\u001B[0m" + " Пара с суммой " + target + " не найдена.");
        }

        // №6 Сумма всех элементов в двумерном массиве
        int[][] twoDArray = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("\u001B[32m" + "№6:" + "\u001B[0m" +" Сумма всех элементов в двумерном массиве: " + sumOf2DArray(twoDArray));

        // №7 Максимальный элемент в каждой строке двумерного массива
        int[][] matrixForMax = {
                {1, 3, 5},
                {3, 3, 7},
                {6, 9, 8}
        };
        int[] maxElements = findMaxElementInEachRow(matrixForMax);
        System.out.println("\u001B[32m" + "№7:" + "\u001B[0m" +" Максимальные элементы в каждой строке: " + Arrays.toString(maxElements));

        // №8 Поворот двумерного массива на 90 градусов против часовой стрелки
        int[][] matrixCounterClockwise = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] rotatedCCW = rotateMatrixAntiClockwise(matrixCounterClockwise);
        System.out.println("\u001B[32m" + "№8:" + "\u001B[0m" +" Повернутая матрица против часовой стрелки:");
        for (int[] row : rotatedCCW) {
            System.out.println(Arrays.toString(row));
        }
    }
}


