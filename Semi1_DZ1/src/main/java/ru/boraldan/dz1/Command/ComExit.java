package ru.boraldan.dz1.Command;

public class ComExit implements Option {
    @Override
    public String info() {
        String text = "Выход";
        return text;
    }

    @Override
    public void doit() {
        System.out.println("Выход");
    }
}
