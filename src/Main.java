import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.System.out;

public class Main {

    final static char[] vov = {'a', 'e', 'i', 'o', 'u', 'y'};

    final static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) {
        matrix();
    }

    public static void matrix() {
        //Массивы гласных и согласных
        int M;
        Scanner sc = new Scanner(System.in);
        out.println("Введите размер матрицы их диапазона от 2 до 5 включительно:");
        M = testNum();
//        M = sc.nextLine();

        while (M > 5 || M < 2) {
            out.println("Число выходит за пределы диапазона (от 2 до 5 включительно)");
            M = testNum();
        }
        //Объявление квадратной матрицы
        String[][] m = new String[M][M];
        //Заполнение матрицы
        m = matrixInit(M);
        //Вывод получившейся матрицы
        out.println("\nВаша матрица:");
        matrixPrint(m);
        //Обработка матрицы
        matrixCalc(m);
        //Вывод обработанной матрицы
        out.println("\nОбработанная матрица:");
        matrixPrint(m);
    }

    public static int testNum() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            out.println("Вы ввели не число");
            sc = new Scanner(System.in);
        }
        return sc.nextInt();
    }

    public static String[][] matrixInit(int M) {
        out.println("Введите \"1\", чтобы ввести данные матрицы вручную");
        out.println("Введите \"0\", чтобы данные матрицы заполнились автоматически");
        int v = testNum();
        while (v != 0 && v != 1) {
            out.println("Вы ввели число, отличное от 0 или 1");
            out.println("Введите \"1\", чтобы ввести данные матрицы вручную");
            out.println("Введите \"0\", чтобы данные матрицы заполнились автоматически");
            v = testNum();
        }
        String[][] matrix = new String[M][M];
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        out.println("Элемент должен состоять из пяти английских букв.");
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (v == 1) {
                    //Упрощённая реализация
                    out.println("Инициализируйте элемент " + i + " строки и " + j + " столбца");
                    matrix[i][j] = sc.nextLine();
                    while (!matrix[i][j].matches("^[a-zA-Z0-9]+$")) {
                        sc = new Scanner(System.in);
                        out.println("Введён неверный формат элемента матрицы");
                        out.println("Элемент матрицы должен состоять из пяти английских букв:");
                        matrix[i][j] = sc.nextLine();
                    }
                } else {
                    //Симуляция автоматического заполнения
                    StringBuilder word = new StringBuilder();
                    for (int ch_int = 0; ch_int < 5; ch_int++) {
                        word.append(alphabet[abs(random.nextInt() % alphabet.length)]);
                    }
                    matrix[i][j] =  String.valueOf(word);
                }
            }
        }
        return matrix;
    }

    public static void matrixCalc(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                //Обработка элемента
                int vov_count = 0; //счётчик гласных
                int con_count = 0; //счётчик согласных
                char[] el = matrix[i][j].toLowerCase().toCharArray();
                for (char item : el) {
                    //Подсчёт гласных
                    for (char v_i : vov) {
                        if (item == v_i) {
                            vov_count++;
                        }
                    }
                    //Подсчёт согласных
                    con_count = el.length - vov_count;
                }
                //Замена элемента матрицы
                matrix[i][j] = String.valueOf(vov_count) + String.valueOf(con_count);
            }
        }
    }

    public static void matrixPrint(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            out.print("| ");
            for (int j = 0; j < matrix.length; j++) {
                out.print(matrix[i][j] + " ");
            }
            out.print("|\n");
        }
    }
}