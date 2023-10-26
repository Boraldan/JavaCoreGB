import java.util.Arrays;
import java.util.Scanner;

public class Temp2 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Введите имя игрока  ");
        String name = scanner.nextLine();

        System.out.println("Введите размер поля (от 6 до 20) ");
        int ray = inNum();
        int col = inNum();

        char[][] charArr = new char[ray][col];
        for (int i = 0; i < ray; i++) {
            for (int j = 0; j < col; j++) {
                charArr[i][j] = ' ';
            }
        }
//        charArr[1][0] = 'O';
//        charArr[2][0] = 'O';
//
//        charArr[7][0] = 'O';
//        charArr[7][1] = 'O';
//        charArr[7][2] = 'O';

//        charArr[1][5] = 'O';
//        charArr[3][5] = 'O';
//        charArr[4][5] = 'O';


        game(charArr, name);
    }

    public static void game(char[][] charArr, String gamer) {
        int timeGame = charArr.length * charArr[0].length;
        int count = 0;
        while (count < timeGame) {
            printField(charArr);
            if (count % 2 == 0) {
                System.out.println("Ход игрока: " + gamer + "  ");
                int iray;
                int jray;
                int can = 0;
                while (can < 3) {
                    iray = inNum(charArr.length + 1);
                    jray = inNum(charArr[0].length + 1);
                    if (iray == -1 || jray == -1) {
                        System.out.println("1. Вы проиграли, потому что вводите не те значения");
                        return;
                    }
                    if (charArr[iray][jray] == ' ') {
                        charArr[iray][jray] = 'X';
                        break;
                    }
                    System.out.println("Выберите другую ячейку");
                    can++;
                }
                if (can == 3) {
                    System.out.println("2. Вы проиграли, потому что выбираете занятые ячейки");
                    return;
                }

                if (checkWin(charArr)) {
                    System.out.println("----------------------------------");
                    printField(charArr);
                    System.out.printf("Победил игрок :  %s   ход = %d", gamer, count);
                    return;
                }
            } else {
                stepIi(charArr);
                if (checkWin(charArr)) {
                    System.out.println("----------------------------------");
                    printField(charArr);
                    System.out.printf("     Победил   КОМПЬЮТЕР !!!  ход = %d", count);
                    return;
                }
            }
            count++;
            System.out.println("----------------------------------");
        }
        System.out.println("----------  НИЧЬЯ !!!      -----------");
    }

    public static void stepIi(char[][] charArr) {
        int ROW = charArr.length;
        int COLL = charArr[0].length;
        int[] countTop = new int[4]; // ящейки: 0='X', 1=' ', 2='O', 3='|'

        // ставим 4 'O' на победу  OOO(O)
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                if (charArr[i][j] == ' ' || charArr[i][j] == 'O') {

                    countTop = checkLine(charArr, i, j);
                    if (countTop[2] == 3 & countTop[1] == 1) {
                        if (stepIiRow(charArr, i, j)) return;
                    }

                    countTop = checkColl(charArr, i, j);
                    if (countTop[2] == 3 & countTop[1] == 1) {
                        if (stepIiCol(charArr, i, j)) return;
                    }

                    countTop = checkDown(charArr, i, j);
                    if (countTop[2] == 3 & countTop[1] == 1) {
                        if (stepIiDown(charArr, i, j)) return;
                    }

                    countTop = checkUp(charArr, i, j);
                    if (countTop[2] == 3 & countTop[1] == 1) {
                        if (stepIiUp(charArr, i, j)) return;
                    }
                }
            }
        }

        // блокируем x(_)xx
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                if (charArr[i][j] == ' ' | charArr[i][j] == 'X') {

                    countTop = checkLine(charArr, i, j);
                    if (countTop[0] == 3 & countTop[1] == 1) {
                        if (stepIiRowBlock(charArr, i, j)) return;
                    }

                    countTop = checkColl(charArr, i, j);
                    if (countTop[0] == 3 & countTop[1] == 1) {
                        if (stepIiColBlock(charArr, i, j)) return;
                    }

                    countTop = checkDown(charArr, i, j);
                    if (countTop[0] == 3 & countTop[1] == 1) {
                        if (stepIiDownBlock(charArr, i, j)) return;
                    }

                    countTop = checkUp(charArr, i, j);
                    if (countTop[0] == 3 & countTop[1] == 1) {
                        if (stepIiUpBlock(charArr, i, j)) return;
                    }
                }
            }
        }

        // ставим 3 'O' в линию O(O)_O
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                if (charArr[i][j] == ' ' | charArr[i][j] == 'O') {
                    countTop = checkDown(charArr, i, j);
                    if (countTop[2] == 2 & countTop[1] == 2) {
                        if (stepIiDown(charArr, i, j)) return;
                    }

                    countTop = checkUp(charArr, i, j);
                    if (countTop[2] == 2 & countTop[1] == 2) {
                        if (stepIiUp(charArr, i, j)) return;
                    }

                    countTop = checkLine(charArr, i, j);
                    if (countTop[2] == 2 & countTop[1] == 2) {
                        if (stepIiRow(charArr, i, j)) return;
                    }

                    countTop = checkColl(charArr, i, j);
                    if (countTop[2] == 2 & countTop[1] == 2) {
                        if (stepIiCol(charArr, i, j)) return;
                    }

                }
            }
        }

        // блокируем x(_)_x
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                if (charArr[i][j] == ' ' | charArr[i][j] == 'X') {

                    countTop = checkLine(charArr, i, j);
                    if (countTop[0] == 2 & countTop[1] == 2) {

                        // пробуем обратобать xx(o), поставить рядом с ноликом свой нолик
                        countTop = checkLine(charArr, i, j + 1);
                        if (countTop[0] == 2 & countTop[2] == 1) {
                            int[] ijO = oFindRow(charArr, i, j + 1);
                            if (stepIiRow(charArr, ijO[0], ijO[1])) return;
                        }

                        if (stepIiRowBlock(charArr, i, j)) return;
                    }

                    countTop = checkColl(charArr, i, j);
                    if (countTop[0] == 2 & countTop[1] == 2) {
                        if (stepIiColBlock(charArr, i, j)) return;
                    }

                    countTop = checkDown(charArr, i, j);
                    if (countTop[0] == 2 & countTop[1] == 2) {
                        if (stepIiDownBlock(charArr, i, j)) return;
                    }

                    countTop = checkUp(charArr, i, j);
                    if (countTop[0] == 2 & countTop[1] == 2) {
                        if (stepIiUpBlock(charArr, i, j)) return;
                    }
                }
            }
        }

        // ставим 2 'O' в линию O(O)__
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                if (charArr[i][j] == ' ' | charArr[i][j] == 'O') {
                    countTop = checkDown(charArr, i, j);
                    if (countTop[2] == 1 & countTop[1] == 3) {
                        if (stepIiDown(charArr, i, j)) return;
                    }

                    countTop = checkUp(charArr, i, j);
                    if (countTop[2] == 1 & countTop[1] == 3) {
                        if (stepIiUp(charArr, i, j)) return;
                    }

                    countTop = checkLine(charArr, i, j);
                    if (countTop[2] == 1 & countTop[1] == 3) {
                        if (stepIiRow(charArr, i, j)) return;
                    }

                    countTop = checkColl(charArr, i, j);
                    if (countTop[2] == 1 & countTop[1] == 3) {
                        if (stepIiCol(charArr, i, j)) return;
                    }

                }
            }
        }

        // блокируем x(_)__
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                if (charArr[i][j] == 'X') {

                    countTop = checkLine(charArr, i, j);
                    if (countTop[0] == 1 & countTop[1] == 3) {
                        if (stepIiRowBlock(charArr, i, j)) return;
                    }

                    countTop = checkColl(charArr, i, j);
                    if (countTop[0] == 1 & countTop[1] == 3) {
                        if (stepIiColBlock(charArr, i, j)) return;
                    }

                    countTop = checkDown(charArr, i, j);
                    if (countTop[0] == 1 & countTop[1] == 3) {
                        if (stepIiDownBlock(charArr, i, j)) return;
                    }

                    countTop = checkUp(charArr, i, j);
                    if (countTop[0] == 1 & countTop[1] == 3) {
                        if (stepIiUpBlock(charArr, i, j)) return;
                    }

                    if (((i - 1 > 0) & (j - 1 > 0)) && charArr[i - 1][j - 1] == ' ') {
                        charArr[i - 1][j - 1] = 'O';
                        return;
                    }
                }
            }
        }
    }

    public static void printField(char[][] charArr) {
        System.out.printf("   %s", " ");
        for (int i = 0; i < charArr[0].length; i++) {
            System.out.printf("%d |", i+1);
            System.out.printf("%s", " ");
        }

        System.out.println();
        for (int i = 0; i < charArr.length; i++) {
            System.out.printf("%d | ", i+1);
            for (int j = 0; j < charArr[0].length; j++) {
                if (charArr[i][j] == ' ') {
                    System.out.printf("%s |", "_");
                    System.out.printf("%s", " ");
                } else {
                    System.out.printf("%s |", charArr[i][j]);
                    System.out.printf("%s", " ");
                }
            }
            System.out.println();
        }
    }

    public static int inNum(int size) {
        int num;
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            try {
                num = Integer.parseInt(scan.nextLine());
                if (num > 0 && num < size) return (num - 1);
            } catch (Exception e) {
            }
            System.out.println("Введите корректное число");
        }
        scan.close();
        return -1;
    }

    public static int inNum() {
        int num;
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            try {
                num = Integer.parseInt(scan.nextLine());
                if (num > 5 & num <= 20) return num;
            } catch (Exception e) {
            }
            System.out.println("Введите корректное число");
        }
        scan.close();
        return -1;
    }

    public static boolean checkWin(char[][] charArr) {
        int ROW = charArr.length;
        int COLL = charArr[0].length;
        //  проверяем победу 'X'
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                int[] countTop = new int[4]; // ящейки: 0='X', 1=' ', 2='O', 3='|'
                if (charArr[i][j] == 'X') {

                    countTop = checkLine(charArr, i, j);
                    if (countTop[0] == 4) return true;

                    countTop = checkColl(charArr, i, j);
                    if (countTop[0] == 4) return true;

                    countTop = checkDown(charArr, i, j);
                    if (countTop[0] == 4) return true;

                    countTop = checkUp(charArr, i, j);
                    if (countTop[0] == 4) return true;
                }
            }
        }
        //  проверяем победу 'O'
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                int[] countTop = new int[4]; // ящейки: 0='X', 1=' ', 2='O', 3='|'
                if (charArr[i][j] == 'O') {

                    countTop = checkLine(charArr, i, j);
                    if (countTop[2] == 4) return true;

                    countTop = checkColl(charArr, i, j);
                    if (countTop[2] == 4) return true;

                    countTop = checkDown(charArr, i, j);
                    if (countTop[2] == 4) return true;

                    countTop = checkUp(charArr, i, j);
                    if (countTop[2] == 4) return true;
                }
            }
        }
        return false;
    }

    public static boolean stepIiRowBlock(char[][] charArr, int i, int j) {
        for (int k = j; k < j + 4; k++) {
            if (charArr[i][k] == 'X') {
                if ((k + 1 < charArr[0].length && charArr[i][k + 1] == ' ')) {
                    charArr[i][k + 1] = 'O';
                    return true;
                } else if ((k - 1 >= 0 && charArr[i][k - 1] == ' ')) {
                    charArr[i][k - 1] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean stepIiRow(char[][] charArr, int i, int j) {
        for (int k = j; k < j + 4; k++) {
            if (charArr[i][k] == 'O') {
                if ((k + 1 < charArr[0].length && charArr[i][k + 1] == ' ')) {
                    charArr[i][k + 1] = 'O';
                    return true;
                } else if ((k - 1 >= 0 && charArr[i][k - 1] == ' ')) {
                    charArr[i][k - 1] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean stepIiColBlock(char[][] charArr, int i, int j) {
        for (int k = i; k < i + 4; k++) {
            if (charArr[k][j] == 'X') {
                if (k + 1 < charArr.length && charArr[k + 1][j] == ' ') {
                    charArr[k + 1][j] = 'O';
                    return true;
                } else if ((k - 1 >= 0 && charArr[k - 1][j] == ' ')) {
                    charArr[k - 1][j] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean stepIiCol(char[][] charArr, int i, int j) {
        for (int k = i; k < i + 4; k++) {
            if (charArr[k][j] == 'O') {
                if (k + 1 < charArr.length && charArr[k + 1][j] == ' ') {
                    charArr[k + 1][j] = 'O';
                    return true;
                } else if ((k - 1 >= 0 && charArr[k - 1][j] == ' ')) {
                    charArr[k - 1][j] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean stepIiDownBlock(char[][] charArr, int i, int j) {
        for (int k = 0; k < 4; k++) {
            if (charArr[i + k][j + k] == 'X') {
                if ((((i + k) + 1) < charArr.length & ((j + k) + 1) < charArr[0].length) &&
                        charArr[(i + k) + 1][(j + k) + 1] == ' ') {
                    charArr[(i + k) + 1][(j + k) + 1] = 'O';
                    return true;
                } else if ((((i + k) - 1) >= 0 & ((j + k) - 1) >= 0) && charArr[(i + k) - 1][(j + k) - 1] == ' ') {
                    charArr[(i + k) - 1][(j + k) - 1] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean stepIiDown(char[][] charArr, int i, int j) {
        for (int k = 0; k < 4; k++) {
            if (charArr[i + k][j + k] == 'O') {
                if ((((i + k) + 1) < charArr.length & ((j + k) + 1) < charArr[0].length) &&
                        charArr[(i + k) + 1][(j + k) + 1] == ' ') {
                    charArr[(i + k) + 1][(j + k) + 1] = 'O';
                    return true;
                } else if ((((i + k) - 1) >= 0 & ((j + k) - 1) >= 0) && charArr[(i + k) - 1][(j + k) - 1] == ' ') {
                    charArr[(i + k) - 1][(j + k) - 1] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean stepIiUpBlock(char[][] charArr, int i, int j) {
        for (int k = 0; k < 4; k++) {
            if (charArr[i - k][j + k] == 'X') {
                if ((((i - k) - 1) >= 0 & ((j + k) + 1) < charArr[0].length) &&
                        charArr[(i - k) - 1][(j + k) + 1] == ' ') {
                    charArr[(i - k) - 1][(j + k) + 1] = 'O';
                    return true;
                } else if ((((i - k) + 1) < charArr.length & ((j + k) + 1) < charArr[0].length) && charArr[(i - k) + 1][(j + k) - 1] == ' ') {
                    charArr[(i - k) + 1][(j + k) - 1] = 'O';
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean stepIiUp(char[][] charArr, int i, int j) {
        for (int k = 0; k < 4; k++) {
            if (charArr[i - k][j + k] == 'O') {
                if ((((i - k) - 1) >= 0 & ((j + k) + 1) < charArr[0].length) &&
                        charArr[(i - k) - 1][(j + k) + 1] == ' ') {
                    charArr[(i - k) - 1][(j + k) + 1] = 'O';
                    return true;
                } else if ((((i - k) + 1) < charArr.length & ((j + k) + 1) < charArr[0].length)
                        && charArr[(i - k) + 1][(j + k) - 1] == ' ') {
                    charArr[(i - k) + 1][(j + k) - 1] = 'O';
                    return true;
                }
            }
        }
        return false;
    }


    public static int[] checkLine(char[][] charArr, int i, int j) {
        int[] count = new int[4];
        // проверяем строку на 4 хода вперёд -->
        for (int k = j; k < j + 4; k++) {
            if ((k < charArr[0].length) && (charArr[i][k] == 'X')) {
                count[0]++;
            } else if ((k < charArr[0].length) && (charArr[i][k] == ' ')) {
                count[1]++;
            } else if (k >= charArr[0].length) {
                count[3]++;
            } else if ((k < charArr[0].length) && (charArr[i][k] == 'O')) {
                count[2]++;
            }
        }
        return count;
    }

    public static int[] checkColl(char[][] charArr, int i, int j) {
        int[] count = new int[4];
        // проверяем столбцы на 4 хода вперёд -->
        for (int k = i; k < i + 4; k++) {
            if ((k < charArr.length) && (charArr[k][j] == 'X')) {
                count[0]++;
            } else if ((k < charArr.length) && (charArr[k][j] == ' ')) {
                count[1]++;
            } else if (k >= charArr.length) {
                count[3]++;
            } else if ((k < charArr.length) && (charArr[k][j] == 'O')) {
                count[2]++;
            }
        }

        return count;
    }

    public static int[] checkDown(char[][] charArr, int i, int j) {
        int[] count = new int[4];
        // проверяем столбцы на 4 хода вперёд -->
        for (int k = 0; k < 4; k++) {
            if (((i+ k) < charArr.length & (j+k) < charArr[0].length) && (charArr[i+k][+j] == 'X')) {
                count[0]++;
            } else if (((i+ k) < charArr.length & (j+k) < charArr[0].length) && (charArr[i+k][+j] == ' ')) {
                count[1]++;
            } else if ((i+k) == charArr.length && (j+k) == charArr[0].length) {
                count[3]++;
            } else if (((i+ k) < charArr.length & (j+k) < charArr[0].length) && (charArr[i+k][+j] == 'O')) {
                count[2]++;
            }
            j++;
        }
        return count;
    }

    public static int[] checkUp(char[][] charArr, int i, int j) {
        int[] count = new int[4];
        // проверяем столбцы на 4 хода вперёд -->
        for (int k = i; k > i - 4; k--) {
            if ((k >= 0 & j < charArr[0].length) && (charArr[k][j] == 'X')) {
                count[0]++;
            } else if ((k >= 0 & j < charArr[0].length) && (charArr[k][j] == ' ')) {
                count[1]++;
            } else if (k < 0 && j == charArr[0].length) {
                count[3]++;
            } else if ((k >= 0 & j < charArr[0].length) && (charArr[k][j] == 'O')) {
                count[2]++;
            }
            j++;
        }
        return count;
    }

    public static int[] oFindRow(char[][] charArr, int i, int j) {
        int[] ijO = new int[2];
        // проверяем строку на 4 хода вперёд -->
        for (int k = j; k < j + 4; k++) {
            if ((k < charArr[0].length) && (charArr[i][k] == 'O')) {
                ijO[0] = k;
                ijO[1] = j;
            }
        }
        return ijO;
    }

}

