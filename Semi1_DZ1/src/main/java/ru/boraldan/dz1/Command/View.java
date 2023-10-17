package ru.boraldan.dz1.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    private List<Option> menuList;


    public View() {
        this.menuList = new ArrayList<>();
        menuList.add(new Com2_PvP());
        menuList.add(new Com3_PvIi());
        menuList.add(new ComExit());
    }

    public void printMenu() {
        System.out.println("Игра Крестики-Нолики");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println(i + 1 + " - " + menuList.get(i).info());
        }

        System.out.println("Введите номер запроса: ");

        int num = inNum(menuList.size() + 1);

        if (num == -1) {
            System.out.println("Вы вводите некорректные значения. Программа закрылась");
            num = menuList.size() - 1;
        }
        menuList.get(num).doit();
    }

    public int inNum(int size) {
        int num;
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            try {
                num = Integer.parseInt(scan.nextLine());
                if (num > 0 && num < size) return num -1;
            } catch (Exception e) {
            }
            System.out.println("Введите корректное число");
        }
        scan.close();
        return -1;
    }
}
