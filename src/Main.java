import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.out;

public class Main {

    final static char[] vov = {'a', 'e', 'i', 'o', 'u', 'y'};
    final static char[] con = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'};

    public static void main(String[] args) {
//        out.println("Введите размер матрицы их диапазона от 2 до 5 включительно:");
//        matrix(testNum());
    }

    public static void matrix(int M) {
        //Массивы гласных и согласных

        Scanner sc = new Scanner(System.in);
        out.println("Введите размер матрицы их диапазона от 2 до 5 включительно:");
        M = sc.nextInt();
        while (M > 5 || M < 2) {
            out.println("Число выходит за пределы диапазона (от 2 до 5 включительно)");
            M = testNum();
        }
        //Объявление квадратной матрицы
        String[][] m = new String[M][M];
        int v = -1;
        while (v != 0 && v != 1) {
            out.println("Введите \"1\", чтобы ввести данные матрицы вручную");
            out.println("Введите \"0\", чтобы данные матрицы заполнились автоматически");
            v = sc.nextInt();
        }
        //Заполнение матрицы
        sc = new Scanner(System.in);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (v == 1) {
                    boolean b = true;
                    while (b) {
                        out.println("Инициализируйте элемент " + i + " строки и " + j + " столбца");
                        out.println("Элемент должен состоять из пяти английских букв:");
                        m[i][j] = sc.nextLine();
                        char[] ch = m[i][j].toLowerCase().toCharArray();
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
                        ;
                    }
                } else {
                    //Симуляция автоматического заполнения
                    m[i][j] = "abcde";
                }
            }
        }
        //Вывод получившейся матрицы
        out.println("\nВаша матрица:");
        for (int i = 0; i < M; i++) {
            out.print("| ");
            for (int j = 0; j < M; j++) {
                out.print(m[i][j] + " ");
            }
            out.print("|\n");
        }
        //Обработка матрицы
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                //Обработка элемента
                int vov_count = 0; //счётчик гласных
                int con_count = 0; //счётчик согласных
                char[] el = m[i][j].toCharArray();
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
                m[i][j] = String.valueOf(vov_count) + String.valueOf(con_count);
            }
        }
        //Вывод обработанной матрицы
        out.println("\nОбработанная матрица:");
        for (int i = 0; i < M; i++) {
            out.print("| ");
            for (int j = 0; j < M; j++) {
                out.print(m[i][j] + " ");
            }
            out.print("|\n");
        }
    }

    public static int testNum() {
        int res = 0;
        boolean b = true;
        while (b) {
            try {
                Scanner sc = new Scanner(System.in);
                res = sc.nextInt();
            } catch (Exception e) {
                out.println("Ошибка. Введите, пожалуйста, число.");
            }
        }
        b = false;
        return res;
    }

    public static int[][] matrixInit(int M) {
        int[][] matrix = new int[M][M];

    }
}
