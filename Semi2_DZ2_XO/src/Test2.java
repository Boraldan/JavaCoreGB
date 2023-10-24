import java.util.Random;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Random r = new Random();
        int ray = 7;
        int col = 7;
        char[][] charArr = new char[ray][col];
        for (int i = 0; i < ray; i++) {
            for (int j = 0; j < col; j++) {
                charArr[i][j] = ' ';
            }
        }
//        charArr[6][0] = 'X';
//        charArr[5][1] = 'X';


//        int count = 0;
//        while (true) {
//            printField(charArr);
//            if (count % 2 == 0) {
//                System.out.println("Ход игрока: ");
//                int iray = inNum(charArr.length + 1);
//                int jray = inNum(charArr[0].length + 1);
//                charArr[iray][jray] = 'X';
//            } else {
//                checkW(charArr, 4);
//            }
//            count++;
//            System.out.println("---------------------------------------------------------");
//        }
        int ROW = charArr.length;
        int COLL = charArr[0].length;
        int[] countTop = new int[4]; // ящейки: 0=X, 1='_', 2=O, 3=|

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                if (charArr[i][j] == ' ' && charArr[i][j] == 'X') {
                    int X = 0;
                    int free = 0;
                    int wall = 0;
                    int O = 0;
                    // проверяем строку на 4 хода вперёд --> ищем xXx
                    countTop = checkLine(charArr, i, j);
                    if (countTop[0] == 3 && countTop[1] == 1) {

                    }


                    // проверяем столбец на 4 хода вперёд -->

                    // проверяем диоганаль вниз на 4 хода вперёд -->

                    // проверяем диоганаль вверх на 4 хода вперёд -->

                }
            }
        }


    }

    public static void checkW(char[][] charArr, int winLine) {
        for (int i = 0; i < charArr.length; i++) {
            int x = 0;
            int free = 0;
            for (int j = 0; j < charArr[0].length; j++) {
                if (charArr[i][j] == 'X') {
                    x++;
                    if (x == 2) {
                        if (stepIiRow(charArr, i, j, x, free)) return;
                    }
                } else if (charArr[i][j] == 'O') {
                    x = 0;
                    free = 0;
                } else {
                    if (x == 1) free++;
                    if (free > 2) {
                        free = 0;
                        x = 0;
                    }
                }
            }

            x = 0;
            free = 0;
            for (int j = 0; j < charArr.length; j++) {
                if (charArr[j][i] == 'X') {
                    x++;
                    if (x == 2) {
                        if (stepIiCol(charArr, i, j, x, free)) return;
                    }
                } else if (charArr[i][j] == 'O') {
                    x = 0;
                    free = 0;
                } else {
                    if (x == 1) free++;
                    if (free > 2) {
                        free = 0;
                        x = 0;
                    }
                }
            }

            if (i >= 3) {
                int k = i;
                x = 0;
                free = 0;
                for (int j = 0; j <= i; j++) {
                    if (charArr[k][j] == 'X') {
                        x++;
                        if (x == 2) {
                            if (stepIiLeftTop(charArr, k, j, x, free)) return;
                        }
                    } else if (charArr[k][j] == 'O') {
                        x = 0;
                        free = 0;
                    } else {
                        if (x == 1) free++;
                        if (free > 2) {
                            free = 0;
                            x = 0;
                        }
                    }
                    k--;
                }
            }

        }


        Random r = new Random();
        boolean flag = true;
        while (flag) {
            int i = r.nextInt(charArr.length);
            int j = r.nextInt(charArr[0].length);
            if (charArr[i][j] == ' ') {
                charArr[i][j] = 'O';
                flag = false;
            }
        }
    }

    public static boolean stepIiLeftTop(char[][] charArr, int i, int j, int x, int free) {
        // проверка такой схемы и установка нолика в скобки:
        //  |x_X(_) +
        if ((free == 1) && ((j - 3) < 0 && (charArr[i - 1][j + 1] == ' '))) {
            charArr[i - 1][j + 1] = 'O';
            System.out.printf(" |x_x(_)  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // (_)x_X| +
        else if ((free == 1) && ((i - 1) < 0 && (charArr[i + 2][j - 2] == ' '))) {
            charArr[i + 2][j - 2] = 'O';
            System.out.printf("(_)x_x|  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // x(_)X_  or  x(_)Xx  +
        else if ((free == 1) && ((i - 1) >= 0 && (charArr[i - 1][j + 1] == ' ' || charArr[i - 1][j + 1] == 'X'))) {
            charArr[i + 1][j - 1] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // _x(_)X_  +
        else if ((free == 1) && ((j - 3) >= 0 && (charArr[i + 3][j - 3] == ' ') &&
                ((i - 1) >= 0 && charArr[i - 1][j + 1] == ' '))) {
            charArr[i - 1][j + 1] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // _x(_)X  +
        else if ((free == 1) && ((j - 3) >= 0 && (charArr[i + 3][j - 3] == ' '))) {
            charArr[i + 1][j - 1] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // x_(_)x  +
        else if ((free == 2)) {
            charArr[i][j - 1] = 'O';   // TODO сюда можно вставить умную вставку
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  _xXx(_)  +
        else if (((i - 1) >= 0 && charArr[i - 1][j + 1] == 'X')
                && ((i - 2) > 0 && charArr[i - 2][j + 2] == ' ')) {
            charArr[i - 2][j + 2] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  (_)xXx|  +
        else if (((i - 1) >= 0 && charArr[i - 1][j + 1] == 'X')
                && ((j - 2) >= 0 && charArr[i + 2][j - 2] == ' ')) {
            charArr[i + 2][j - 2] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  _xXxo  +
        else if (((i - 1) >= 0 && charArr[i - 1][j + 1] == 'X')
                && ((i - 2) >= 0 && charArr[i - 2][j + 2] == 'O')
                && ((j - 2 >= 0 && charArr[i][j - 2] == ' '))) {
            charArr[i - 2][j - 2] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  (_)xX_|  +
        else if (((i - 2) < 0 && (i - 1) >= 0 && charArr[i - 1][j + 1] == ' ')
                && charArr[i + 3][j - 2] == ' ') {
            charArr[i + 3][j - 2] = 'O'; // TODO сюда можно вставить умную вставку
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // |xX_(_)  +
        else if (((j - 2) < 0) && (charArr[i - 2][j + 2] == ' ' && charArr[i - 1][j + 1] == ' ')) {
            charArr[i - 2][j + 2] = 'O';   // TODO сюда можно вставить умную вставку
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  (_)_xX|  +
        else if (((i - 1) < 0) && (charArr[i + 3][j - 3] == ' ' && charArr[i + 2][j - 2] == ' ')) {
            charArr[i + 3][j - 3] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  (_)xX_o  +
        else if ((((i - 2) >= 0 && (charArr[i - 2][j + 2] == 'O' && charArr[i - 1][j + 1] == ' '))
                && ((j - 2) >= 0 && charArr[i + 2][j - 2] == ' '))) {
            charArr[i + 2][j - 2] = 'O';
            System.out.printf("(_)xx_o  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  _xX(_)  +
        else if (((j - 2) >= 0 && charArr[i + 2][j - 2] == ' ') && ((i - 1) >= 0 && charArr[i - 1][j + 1] == ' ')) {
            charArr[i - 1][j + 1] = 'O'; // TODO сюда можно вставить умную вставку
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  oxX_(_)
        else if (((j - 2) >= 0 && charArr[i + 2][j - 2] == 'O') && ((i - 2) >= 0 && charArr[i - 2][j + 2] == ' '
                && charArr[i - 1][j + 1] == ' ')) {
            charArr[i - 2][j + 2] = 'O'; // TODO сюда можно вставить умную вставку
            System.out.printf("oxX_(_)  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  (_)_xXo
        else if (((i - 1) >= 0 && charArr[i - 1][j + 1] == 'O') && ((j - 3) >= 0
                && charArr[i + 3][j - 3] == ' ' && charArr[i + 2][j - 2] == ' ')) {
            charArr[i + 3][j - 3] = 'O'; // TODO сюда можно вставить умную вставку
            System.out.printf("(_)_xXo i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        System.out.println("x = " + x);
        return false;
    }

    public static boolean stepIiRow(char[][] charArr, int i, int j, int x, int free) {
        // проверка такой схемы и установка нолика в скобки:
        //  |x_x(_)
        if ((free == 1) && ((j - 3) < 0 && (charArr[i][j + 1] == ' '))) {
            charArr[i][j + 1] = 'O';
            System.out.printf(" |x_x(_)  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // (_)x_x|
        else if ((free == 1) && ((j + 1) > charArr[0].length - 1 && (charArr[i][j - 3] == ' '))) {
            charArr[i][j - 3] = 'O';
            System.out.printf("(_)x_x|  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // x(_)x_  or  x(_)xx
        else if ((free == 1) && ((j + 1) < charArr[0].length && (charArr[i][j + 1] == ' ' || charArr[i][j + 1] == 'X'))) {
            charArr[i][j - 1] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // _x(_)x_
        else if ((free == 1) && ((j - 3) >= 0 && (charArr[i][j - 3] == ' ') &&
                ((j + 1) < charArr[0].length && charArr[i][j + 1] == ' '))) {
            charArr[i][j - 1] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // _x(_)x
        else if ((free == 1) && ((j - 3) >= 0 && (charArr[i][j - 3] == ' '))) {
            charArr[i][j - 1] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // x_(_)x
        else if ((free == 2)) {
            charArr[i][j - 1] = 'O';   // TODO сюда можно вставить умную вставку
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  _xXx(_)
        else if (((j + 1) < charArr[0].length && charArr[i][j + 1] == 'X')
                && ((j + 2) < charArr[0].length && charArr[i][j + 2] == ' ')) {
            charArr[i][j + 2] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  _xXx|
        else if (((j + 1) < charArr[0].length && charArr[i][j + 1] == 'X')
                && ((j + 2) > charArr[0].length - 1 && charArr[i][j - 2] == ' ')) {
            charArr[i][j - 2] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  _xXxo
        else if (((j + 1) < charArr[0].length && charArr[i][j + 1] == 'X')
                && ((j + 2) < charArr[0].length && charArr[i][j + 2] == 'O')
                && ((j - 2 >= 0 && charArr[i][j - 2] == ' '))) {
            charArr[i][j - 2] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  (_)xx_|
        else if (((j + 2) > charArr[0].length - 1 && (j + 1) < charArr[0].length && charArr[i][j + 1] == ' ')
                && charArr[i][j - 2] == ' ') {
            charArr[i][j - 2] = 'O'; // TODO сюда можно вставить умную вставку
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // |xx_(_)
        else if (((j - 2) < 0) && (charArr[i][j + 2] == ' ' && charArr[i][j + 1] == ' ')) {
            charArr[i][j + 2] = 'O';   // TODO сюда можно вставить умную вставку
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  (_)_xx|
        else if (((j + 1) > charArr[0].length - 1) && (charArr[i][j - 3] == ' ' && charArr[i][j - 2] == ' ')) {
            charArr[i][j - 3] = 'O';
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  (_)xx_o
        else if ((((j + 2) < charArr[0].length - 1) && (charArr[i][j + 2] == 'O' && charArr[i][j + 1] == ' '))
                && ((j - 2) >= 0 && charArr[i][j - 2] == ' ')) {
            charArr[i][j - 2] = 'O';
            System.out.printf("(_)xx_o  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  _xx(_)
        else if (((j - 2) >= 0 && charArr[i][j - 2] == ' ') && ((j + 1) < charArr[0].length && charArr[i][j + 1] == ' ')) {
            charArr[i][j + 1] = 'O'; // TODO сюда можно вставить умную вставку
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  oxx_(_)
        else if (((j - 2) >= 0 && charArr[i][j - 2] == 'O') && ((j + 2) < charArr[0].length && charArr[i][j + 2] == ' '
                && charArr[i][j + 1] == ' ')) {
            charArr[i][j + 2] = 'O'; // TODO сюда можно вставить умную вставку
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  (_)_xxo
        else if (((j + 1) < charArr[0].length && charArr[i][j + 1] == 'O') && ((j - 3) >= 0
                && charArr[i][j - 3] == ' ' && charArr[i][j - 2] == ' ')) {
            charArr[i][j - 3] = 'O'; // TODO сюда можно вставить умную вставку
            System.out.printf("i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        System.out.println("x = " + x);
        return false;
    }

    public static boolean steoIiRowXXX(char[][] charArr, int i, int j) {
        // проверка такой схемы и установка нолика в скобки:
        //   (_)xXx
        if (charArr[i][j] == ' ')                {
            charArr[i][j] = 'O';
            System.out.printf("(_)xXx  i%d j%d х = %d  free = %d", i, j );
            return true;
            //  x(_)Xx
        }
        //  x(_)Xx
        else if (charArr[i][j+1] == ' ') {
            charArr[i][j + 1] = 'O';
            System.out.printf("x(_)xx  i%d j%d х = %d  free = %d", i, j);
            return true;
        }
        //  xX(_)x
        else if (charArr[i][j+2] == ' ') {
            charArr[i][j + 2] = 'O';
            System.out.printf(" xX(_)xx  i%d j%d х = %d  free = %d", i, j);
            return true;
        }
        //  xXx(_)
        else if (charArr[i][j+3] == ' ') {
            charArr[i][j + 3] = 'O';
            System.out.printf("xXx(_)  i%d j%d х = %d  free = %d", i, j);
            return true;
        }
        return false;
    }

    public static boolean stepIiCol(char[][] charArr, int i, int j, int x, int free) {
        // проверка такой схемы и установка нолика в скобки:
        //  |x_x(_)
        if ((free == 1) && ((j - 3) < 0 && (charArr[j + 1][i] == ' '))) {
            charArr[j + 1][i] = 'O';
            System.out.printf(" |x_x(_)  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // (_)x_x|
        else if ((free == 1) && ((j + 1) > charArr.length - 1 && (charArr[j - 3][i] == ' '))) {
            charArr[j - 3][i] = 'O';
            System.out.printf("(_)x_x|  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // x(_)x_  or  x(_)xx
        else if ((free == 1) && ((j + 1) < charArr.length && (charArr[j + 1][i] == ' ' || charArr[j + 1][i] == 'X'))) {
            charArr[j - 1][i] = 'O';
            System.out.printf("x(_)x_  or  x(_)xx  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // _x(_)x_
        else if ((free == 1) && ((j - 3) >= 0 && (charArr[j - 3][i] == ' ') &&
                ((j + 1) < charArr.length && charArr[j + 1][i] == ' '))) {
            charArr[j - 1][i] = 'O';
            System.out.printf("_x(_)x_  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // _x(_)x
        else if ((free == 1) && ((j - 3) >= 0 && (charArr[j - 3][i] == ' '))) {
            charArr[j - 1][i] = 'O';
            System.out.printf(" _x(_)x  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // x_(_)x
        else if ((free == 2)) {
            charArr[j - 1][i] = 'O';   // TODO сюда можно вставить умную вставку
            System.out.printf("x_(_)x  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  _xXx(_)
        else if (((j + 1) < charArr.length && charArr[j + 1][i] == 'X')
                && ((j + 2) < charArr.length && charArr[j + 2][i] == ' ')) {
            charArr[j + 2][i] = 'O';
            System.out.printf("_xXx(_) i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  _xXx|
        else if (((j + 1) < charArr.length && charArr[j + 1][i] == 'X')
                && ((j + 2) > charArr.length - 1 && charArr[j - 2][i] == ' ')) {
            charArr[j - 2][i] = 'O';
            System.out.printf("_xXx|  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  _xXxo
        else if (((j + 1) < charArr.length && charArr[j + 1][i] == 'X')
                && ((j + 2) < charArr.length && charArr[j + 2][i] == 'O')
                && ((j - 2 >= 0 && charArr[j - 2][i] == ' '))) {
            charArr[j - 2][i] = 'O';
            System.out.printf("_xXxo  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  (_)xx_|
        else if (((j + 2) > charArr.length - 1 && (j + 1) < charArr.length && charArr[j + 1][i] == ' ')
                && charArr[j - 2][i] == ' ') {
            charArr[j - 2][i] = 'O'; // TODO сюда можно вставить умную вставку
            System.out.printf("(_)xx_|  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        // |xx_(_)
        else if (((j - 2) < 0) && (charArr[j + 2][i] == ' ' && charArr[j + 1][i] == ' ')) {
            charArr[j + 2][i] = 'O';   // TODO сюда можно вставить умную вставку
            System.out.printf("|xx_(_)  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  (_)_xx|
        else if (((j + 1) > charArr.length - 1) && (charArr[j - 3][i] == ' ' && charArr[j - 2][i] == ' ')) {
            charArr[j - 3][i] = 'O';
            System.out.printf("(_)_xx|  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  (_)xx_o
        else if ((((j + 2) < charArr.length - 1) && (charArr[j + 2][i] == 'O' && charArr[j + 1][i] == ' '))
                && ((j - 2) >= 0 && charArr[j - 2][i] == ' ')) {
            charArr[j - 2][i] = 'O';
            System.out.printf("(_)xx_o  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  _xx(_)
        else if (((j - 2) >= 0 && charArr[j - 2][i] == ' ') && ((j + 1) < charArr.length && charArr[j + 1][i] == ' ')) {
            charArr[j + 1][i] = 'O'; // TODO сюда можно вставить умную вставку
            System.out.printf("_xx(_)  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  oxx_(_)
        else if (((j - 2) >= 0 && charArr[j - 2][i] == 'O') && ((j + 2) < charArr.length && charArr[j + 2][i] == ' '
                && charArr[j + 1][i] == ' ')) {
            charArr[j + 2][i] = 'O'; // TODO сюда можно вставить умную вставку
            System.out.printf("oxx_(_)  i%d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        //  (_)_xxo
        else if (((j + 1) < charArr.length && charArr[j + 1][i] == 'O') && ((j - 3) >= 0
                && charArr[j - 3][i] == ' ' && charArr[j - 2][i] == ' ')) {
            charArr[j - 3][i] = 'O'; // TODO сюда можно вставить умную вставку
            System.out.printf("(_)_xxo  %d j%d х = %d  free = %d", i, j, x, free);
            return true;
        }
        System.out.println("x = " + x);
        return false;
    }

    public static void printField(char[][] charArr) {
        for (int i = 0; i < charArr.length; i++) {
            System.out.print("| ");
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
//            System.out.println();
        }
    }

    public static int inNum(int size) {
        int num;
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            try {
                num = Integer.parseInt(scan.nextLine());
                if (num > 0 && num < size) return num - 1;
            } catch (Exception e) {
            }
            System.out.println("Введите корректное число");
        }
        scan.close();
        return -1;
    }


    public static int[] checkLine(char[][] charArr, int i, int j) {
        int[] count = new int[4];
        // проверяем строку на 4 хода вперёд -->
        for (int k = j; k < j + 4; k++) {
            if ((k < charArr[0].length) && (charArr[i][k] == 'X')) {
                count[0]++;
            } else if ((k < charArr[0].length) && (charArr[i][k] == ' ')) {
                count[1]++;
            } else if (k == charArr[0].length) {
                count[3]++;
                break;
            } else {
                count[2]++;
                break;
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
            } else if (k == charArr.length) {
                count[3]++;
                break;
            } else {
                count[2]++;
                break;
            }
        }
        return count;
    }

    public static int[] checkDown(char[][] charArr, int i, int j) {
        int[] count = new int[4];
        // проверяем столбцы на 4 хода вперёд -->
        for (int k = i; k < i + 4; k++) {
            if ((k < charArr.length & j < charArr[0].length) && (charArr[k][j] == 'X')) {
                count[0]++;
            } else if ((k < charArr.length & j < charArr[0].length) && (charArr[k][j] == ' ')) {
                count[1]++;
            } else if (k == charArr.length && j == charArr[0].length) {
                count[3]++;
                break;
            } else {
                count[2]++;
                break;
            }
            j++;
        }
        return count;
    }

    public static int[] checkUp(char[][] charArr, int i, int j) {
        int[] count = new int[4];
        // проверяем столбцы на 4 хода вперёд -->
        for (int k = i; k > i - 4; k--) {
            if ((k < 0 & j < charArr[0].length) && (charArr[k][j] == 'X')) {
                count[0]++;
            } else if ((k < charArr.length & j < charArr[0].length) && (charArr[k][j] == ' ')) {
                count[1]++;
            } else if (k < 0 && j == charArr[0].length) {
                count[3]++;
                break;
            } else {
                count[2]++;
                break;
            }
            j++;
        }
        return count;
    }

}