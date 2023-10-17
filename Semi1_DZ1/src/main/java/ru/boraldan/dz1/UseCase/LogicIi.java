package ru.boraldan.dz1.UseCase;

public class LogicIi {

    public int moveIi(char[] fieldGame, int step) {
        System.out.println("Ход игрока Ii  --> ");
        // первые 3 хода
        if (step <= 3) {
            if (fieldGame[4] == ' ') return 4;
            if (fieldGame[0] == ' ') return 0;
            if (fieldGame[2] == ' ') return 2;
        }

        if (step > 3) {
            // -------- ищем место для третьего нолика  ---------------
            // проверка строк
            for (int i = 0; i < 9; i = i + 3) {
                StringBuilder builder0 = new StringBuilder();
                StringBuilder builder01 = new StringBuilder();
                for (int j = i; j < i + 3; j++) {
                    if (fieldGame[j] == 'O') builder0.append(fieldGame[j]);
                    if (fieldGame[j] == ' ') builder01.append(fieldGame[j]);
                }
                if (builder0.toString().equals("OO") && builder01.toString().equals(" ")) {
                    for (int j = i; j < i + 3; j++) {
                        if (fieldGame[j] == ' ') return j;
                    }
                }
            }

            // проверка столбцов
            for (int i = 0; i < 3; i++) {
                StringBuilder builder2 = new StringBuilder();
                StringBuilder builder21 = new StringBuilder();
                for (int j = i; j < 9; j = j + 3) {
                    if (fieldGame[j] == 'O') builder2.append(fieldGame[j]);
                    if (fieldGame[j] == ' ') builder21.append(fieldGame[j]);
                }
                if (builder2.toString().equals("OO") && builder21.toString().equals(" ")) {
                    for (int j = i; j < 9; j = j + 3) {
                        if (fieldGame[j] == ' ') return j;
                    }
                }
            }

            // проверка диагоналей
            StringBuilder builder3 = new StringBuilder();
            StringBuilder builder31 = new StringBuilder();
            for (int i = 0; i < 9; i = i + 4) {
                if (fieldGame[i] == 'O') builder3.append(fieldGame[i]);
                if (fieldGame[i] == ' ') builder31.append(fieldGame[i]);
            }
            if (builder3.toString().equals("OO") && builder31.toString().equals(" ")) {
                for (int i = 0; i < 9; i = i + 4) {
                    if (fieldGame[i] == ' ') return i;
                }
            }
            StringBuilder builder4 = new StringBuilder();
            StringBuilder builder41 = new StringBuilder();
            for (int i = 2; i < 7; i = i + 2) {
                if (fieldGame[i] == 'O') builder4.append(fieldGame[i]);
                if (fieldGame[i] == ' ') builder41.append(fieldGame[i]);
            }
            if (builder4.toString().equals("OO") && builder41.toString().equals(" ")) {
                for (int i = 2; i < 7; i = i + 2) {
                    if (fieldGame[i] == ' ') return i;
                }
            }

            // --------  блокируем противнику поставить третий крестик --------------
            // проверка строк
            for (int i = 0; i < 9; i = i + 3) {
                StringBuilder builder5 = new StringBuilder();
                for (int j = i; j < i + 3; j++) {
                    if (fieldGame[j] == 'X') builder5.append(fieldGame[j]);
                }
                if (builder5.toString().equals("XX")) {
                    for (int j = i; j < i + 3; j++) {
                        if (fieldGame[j] == ' ') return j;
                    }
                }
            }

            // проверка столбцов
            for (int i = 0; i < 3; i++) {
                StringBuilder builder6 = new StringBuilder();
                for (int j = i; j < 9; j = j + 3) {
                    if (fieldGame[j] == 'X') builder6.append(fieldGame[j]);
                }
                if (builder6.toString().equals("XX")) {
                    for (int j = i; j < 9; j = j + 3) {
                        if (fieldGame[j] == ' ') return j;
                    }
                }
            }

            // проверка диагоналей
            StringBuilder builder7 = new StringBuilder();
            StringBuilder builder71 = new StringBuilder();
            for (int i = 0; i < 9; i = i + 4) {
                if (fieldGame[i] == 'X') builder7.append(fieldGame[i]);
                if (fieldGame[i] == ' ') builder71.append(fieldGame[i]);
            }
            if (builder7.toString().equals("XX") && builder71.toString().equals(" ")) {
                for (int i = 0; i < 9; i = i + 4) {
                    if (fieldGame[i] == ' ') return i;
                }
            }
            StringBuilder builder8 = new StringBuilder();
            StringBuilder builder81 = new StringBuilder();
            for (int i = 2; i < 7; i = i + 2) {
                if (fieldGame[i] == 'X') builder8.append(fieldGame[i]);
                if (fieldGame[i] == ' ') builder81.append(fieldGame[i]);
            }
            if (builder8.toString().equals("XX") && builder81.toString().equals(" ")) {
                for (int i = 2; i < 7; i = i + 2) {
                    if (fieldGame[i] == ' ') return i;
                }
            }

            // --------  ищем один 'O' и две свободных ячейки на линии ----------------
            for (int i = 0; i < 9; i = i + 3) {
                StringBuilder builder9 = new StringBuilder();
                StringBuilder builder91 = new StringBuilder();
                for (int j = i; j < i + 3; j++) {
                    if (fieldGame[j] == 'O') builder9.append(fieldGame[j]);
                    if (fieldGame[j] == ' ') builder91.append(fieldGame[j]);
                }
                if (builder9.toString().equals("O") && builder91.toString().equals("  ")) {
                    for (int j = i; j < i + 3; j++) {
                        if (fieldGame[j] == ' ') return j;
                    }
                }
            }

            // проверка столбцов
            for (int i = 0; i < 3; i++) {
                StringBuilder builder10 = new StringBuilder();
                StringBuilder builder101 = new StringBuilder();
                for (int j = i; j < 9; j = j + 3) {
                    if (fieldGame[j] == 'O') builder10.append(fieldGame[j]);
                    if (fieldGame[j] == ' ') builder101.append(fieldGame[j]);
                }
                if (builder10.toString().equals("O") && builder101.toString().equals("  ")) {
                    for (int j = i; j < 9; j = j + 3) {
                        if (fieldGame[j] == ' ') return j;
                    }
                }
            }

            // проверка диагоналей
            StringBuilder builder11 = new StringBuilder();
            StringBuilder builder111 = new StringBuilder();
            for (int i = 0; i < 9; i = i + 4) {
                if (fieldGame[i] == 'O') builder11.append(fieldGame[i]);
                if (fieldGame[i] == ' ') builder111.append(fieldGame[i]);
            }
            if (builder11.toString().equals("O") && builder111.toString().equals("  ")) {
                for (int i = 0; i < 9; i = i + 4) {
                    if (fieldGame[i] == ' ') return i;
                }
            }
            StringBuilder builder12 = new StringBuilder();
            StringBuilder builder121 = new StringBuilder();
            for (int i = 2; i < 7; i = i + 2) {
                if (fieldGame[i] == 'O') builder12.append(fieldGame[i]);
                if (fieldGame[i] == ' ') builder121.append(fieldGame[i]);
            }
            if (builder12.toString().equals("O") && builder121.toString().equals("  ")) {
                for (int i = 2; i < 7; i = i + 2) {
                    if (fieldGame[i] == ' ') return i;
                }
            }

            // ищем свободное поле для хода
            for (int i = 0; i < 9; i++) {
                if (fieldGame[i] == ' ') return i;
            }
        }
        return -1;
    }
}

