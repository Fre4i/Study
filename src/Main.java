import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.System.out;

public class Main {

    //Массивы гласных и согласных
    final static char[] vov = {'a', 'e', 'i', 'o', 'u', 'y'};
    final static char[] con = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'};

    static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm','n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) {
        matrix();
    }

    public static void matrix() {
        int M;
        Scanner sc = new Scanner(System.in);
        out.println("Введите размер матрицы их диапазона от 2 до 5 включительно:");
        M = testNum();
        while (M > 5 || M < 2) {
            out.println("Число выходит за пределы диапазона (от 2 до 5 включительно) или не является целым");
            M = testNum();
        }

        //Объявление квадратной матрицы
        String[][] matrix = new String[M][M];
        matrix = matrixInit(M);

        //Вывод матрицы до обработки
        out.println("\nВаша матрица:");
        matrixPrint(matrix);

        //Обработка матрицы
        matrixCalc(matrix);

        //Вывод обработанной матрицы
        out.println("\nОбработанная матрица:");
        matrixPrint(matrix);

    }

    public static int testNum() {
        int res = 0;
        boolean b = true;
        while (b) {
            try {
                Scanner sc = new Scanner(System.in);
                res = sc.nextInt();
                return res;
            } catch (Exception e) {
                out.println("Ошибка. Введите, пожалуйста, число.");
            }
        }
        return -99;
    }

    public static String[][] matrixInit(int M) {
        String[][] matrix = new String[M][M];
        out.println("Введите \"1\", чтобы ввести данные матрицы вручную");
        out.println("Введите \"0\", чтобы данные матрицы заполнились автоматически");
        int v = testNum();
        while (v != 0 && v != 1) {
            out.println("Введённое число не является 0 или 1\n");
            out.println("Введите \"1\", чтобы ввести данные матрицы вручную");
            out.println("Введите \"0\", чтобы данные матрицы заполнились автоматически");
            v= testNum();
        }
        //Заполнение матрицы
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (v == 1) {
                    boolean b = true;
                    while (b) {
                        out.println("Инициализируйте элемент " + i + " строки и " + j + " столбца");
                        out.println("Элемент должен состоять из пяти английских букв:");
                        matrix[i][j] = sc.nextLine();
                        char[] ch = matrix[i][j].toLowerCase().toCharArray();
                        int count = 0;
                        for (char item : ch) {
                            //Проверка гласных
                            for (char v_i : vov) {
                                if (item == v_i) {
                                    count++;
                                    break;
                                }
                            }
                            //Проверка согласных
                            for (char c_i : con) {
                                if (item == c_i) {
                                    count++;
                                    break;
                                }
                            }
                        }
                        if (count == ch.length && ch.length == 5) {
                            b = false;
                        } else {
                            out.println("Неккоректный элемент матрицы");
                        }
                    }
                } else {
                    //Симуляция автоматического заполнения
                    StringBuilder rand_word = new StringBuilder("");
                    for (int ch = 0; ch < 5; ch++) {
                        rand_word.append(alphabet[abs(random.nextInt() % alphabet.length)]);
                    }
                    matrix[i][j] = String.valueOf(rand_word);
                }
            }
        }
        return matrix;
    }

    public static void matrixCalc(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <  matrix.length; j++) {
                //Обработка элемента
                int vov_count = 0; //счётчик гласных
                int con_count = 0; //счётчик согласных
                char[] el = matrix[i][j].toCharArray();
                for (char item : el) {
                    //Подсчёт гласных
                    for (char v_i : vov) {
                        if (item == v_i) {
                            vov_count++;
                        }
                    }
                    //Подсчёт согласных
                    for (char c_i : con) {
                        if (item == c_i) {
                            con_count++;
                        }
                    }
                }
                //Замена элемента матрицы
                matrix[i][j] = String.valueOf(vov_count) + String.valueOf(con_count);
            }
        }
    }

    public static void matrixPrint(String[][] matrix) {
        //Вывод получившейся матрицы
        for (int i = 0; i < matrix.length; i++) {
            out.print("| ");
            for (int j = 0; j < matrix.length; j++) {
                out.print(matrix[i][j] + " ");
            }
            out.print("|\n");
        }
    }
}
