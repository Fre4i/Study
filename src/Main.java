import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.System.out;

public class Main {

    final static char[] vov = {'a', 'e', 'i', 'o', 'u', 'y'};
    final static char[] vov_CAPS = {'A', 'E', 'I', 'O', 'U', 'Y'};

    final static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) {
        matrix();
    }

    public static void matrix() {
        //Массивы гласных и согласных
        int M;
        Scanner sc = new Scanner(System.in);
        out.println("Введите число M ∈ [2;5] - размер матрицы:");
        String input = sc.nextLine();
        while (true) {
            if (matchNum(input)) {
                M = Integer.parseInt(input);
                if (M >= 2 && M <= 5) {
                    break;
                } else {
                    out.println("Ошибка. Введённое число ∉ [2 ; 5] \nПовторите ввод:");
                    input = sc.nextLine();
                }
            } else {
                out.println("Ошибка. Некорректный тип данных. \nПовторите ввод:");
                input = sc.nextLine();
            }
        }

        //Объявление квадратной матрицы
        String[][] matrix;
        //Заполнение матрицы
        matrix = matrixInit(M);
        //Вывод получившейся матрицы
        out.println("\nВаша матрица:");
        matrixPrint(matrix);
        //Обработка матрицы
        matrixCalc(matrix);
        //Вывод обработанной матрицы
        out.println("\nОбработанная матрица:");
        matrixPrint(matrix);
    }

    public static String[][] matrixInit(int M) {
        out.println("Введите \"1\", чтобы ввести данные матрицы вручную");
        out.println("Введите \"0\", чтобы данные матрицы заполнились автоматически");

        Scanner sc = new Scanner(System.in);
        int v;
        String input = sc.nextLine();
        while (true) {
            if (matchNum(input)) {
                v = Integer.parseInt(input);
                if (v == 1 || v == 0) {
                    break;
                } else {
                    out.println("Ошибка. Введённое число не является 1 или 0. \nПовторите ввод:");
                    input = sc.nextLine();
                }
            } else {
                out.println("Ошибка. Некорректный тип данных. \nПовторите ввод:");
                input = sc.nextLine();
            }
        }
        String[][] matrix = new String[M][M];
        sc = new Scanner(System.in);
        Random random = new Random();
        out.println("Элемент должен состоять из пяти английских букв.");
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (v == 1) {
                    //Упрощённая реализация
                    out.println("Инициализируйте элемент " + i + " строки и " + j + " столбца");
                    matrix[i][j] = sc.nextLine();

                    while (true) {
                        if (matrix[i][j].length() == 5) {
                            if (matchSym(matrix[i][j])) {
                                break;
                            } else {
                                out.println("Ошибка. Слово должно состоять из 5 английских букв. \nПовторите ввод.");
                                out.println("Инициализируйте элемент " + i + " строки и " + j + " столбца");
                                matrix[i][j] = sc.nextLine();
                            }
                        } else {
                            out.println("Ошибка. Длина слова должна состоять из 5 английских букв. \nПовторите ввод.");
                            out.println("Инициализируйте элемент " + i + " строки и " + j + " столбца");
                            matrix[i][j] = sc.nextLine();
                        }
                    }
                } else {
                    //Симуляция автоматического заполнения
                    StringBuilder word = new StringBuilder();
                    for (int ch_int = 0; ch_int < 5; ch_int++) {
                        word.append(alphabet[abs1(random.nextInt() % alphabet.length)]);
                    }
                    matrix[i][j] =  String.valueOf(word);
                }
            }
        }
        return matrix;
    }

    //Выполнено
    public static void matrixCalc(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                //Обработка элемента
                int vov_count = 0; //счётчик гласных
                int con_count = 0; //счётчик согласных
                char[] el = matrix[i][j].toLowerCase().toCharArray();
                for (int item = 0; item < el.length ; ++item) {
                    //Подсчёт гласных
                    for (int v_i = 0 ; v_i < vov.length; ++v_i) {
                        if (el[item] == vov[v_i] || el[item] == vov_CAPS[v_i]) {
                            vov_count++;
                        }
                    }
                    //Подсчёт согласных
                    con_count = el.length - vov_count;
                }
                //Замена элемента матрицы
                matrix[i][j] = "" + vov_count + con_count;
            }
        }
    }

    //Выполнено
    public static void matrixPrint(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            out.print("| ");
            for (int j = 0; j < matrix.length; j++) {
                out.print(matrix[i][j] + " ");
            }
            out.print("|\n");
        }
    }


    //Вспомогательные функции
    public static int abs1(int a) {
        return (a < 0) ? -a : a;
    }

    public static boolean matchSym(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!((s.charAt(i) >= 65 && s.charAt(i) <= 90) || (s.charAt(i) >= 97 && s.charAt(i) <= 122))) {
                return false;
            }
        }
        return true;
    }

    public static boolean matchNum(String s) {
        if (s.equals("")) return false;
        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) >= 48 && s.charAt(i) <= 57)) {
                return false;
            }
        }
        return true;
    }

}