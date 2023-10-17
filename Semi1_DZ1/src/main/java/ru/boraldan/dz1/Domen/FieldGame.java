package ru.boraldan.dz1.Domen;

import java.util.Scanner;

public class FieldGame {
    public char[] fieldStart = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public char[] fieldGame = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

    public void printField(char[] field) {
        for (int i = 0; i < 9; i = i + 3) {
            StringBuilder builder = new StringBuilder();
            System.out.println("- - - - - - -");
            builder.append('|');
            for (int j = i; j < i + 3; j++) {
                builder.append(' ');
                builder.append(field[j]);
                builder.append(' ');
                builder.append('|');
            }
            System.out.println(builder);
        }
        System.out.println("- - - - - - -");
    }

    public int move(String name) {
        System.out.println("Ход игрока " + name + " --> ");
        System.out.print("Введите число: ");
        int count = 0;
        while (count < 3) {
            int num = inNum(fieldGame.length + 1);
            if (num == -1) {
                System.out.println("Вы проиграли, потому что вводите не те значения");
                return -1;
            }
            if (fieldGame[num - 1] == ' ') return num - 1;
            System.out.println("Выберите другую ячейку");
            count++;
        }
        return -1;
    }

    public boolean checkWin() {
        for (int i = 0; i < 9; i = i + 3) {
            StringBuilder builder = new StringBuilder();
            for (int j = i; j < i + 3; j++) {
                if (fieldGame[j] != ' ') builder.append(fieldGame[j]);
            }
            if ((builder.toString().equals("XXX")) || (builder.toString().equals("OOO"))) return true;
        }

        for (int i = 0; i < 3; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = i; j < 9; j = j + 3) {
                if (fieldGame[j] != ' ') builder.append(fieldGame[j]);
            }
            if (builder.toString().equals("XXX") || builder.toString().equals("OOO")) return true;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(fieldGame[0]).append(fieldGame[4]).append(fieldGame[8]);
        if (builder.toString().equals("XXX") || builder.toString().equals("OOO")) return true;

        StringBuilder builder2 = new StringBuilder();
        builder2.append(fieldGame[2]).append(fieldGame[4]).append(fieldGame[6]);
        if (builder2.toString().equals("XXX") || builder2.toString().equals("OOO")) return true;

        return false;
    }

    public int inNum(int size) {
        int num;
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            try {
                num = Integer.parseInt(scan.nextLine());
                if (num > 0 && num < size) return num;
            } catch (Exception e) {
            }
            System.out.println("Введите корректное число");
        }
        scan.close();
        return -1;
    }
}
