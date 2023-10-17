package ru.boraldan.dz1.Command;

import ru.boraldan.dz1.Domen.FieldGame;
import ru.boraldan.dz1.Domen.Player;
import ru.boraldan.dz1.UseCase.PvP;
import java.util.Scanner;

public class Com2_PvP implements Option {
    @Override
    public String info() {
        String text = "Режим PvP";
        return text;
    }

    @Override
    public void doit() {
        System.out.print("Введите имя первого игрока: ");
        Scanner scan = new Scanner(System.in);
        String name1 = scan.nextLine();
        Player player1 = new Player(name1, 'X');
        System.out.print("Введите имя второго игрока: ");
        String name2 = scan.nextLine();
        Player player2 = new Player(name2, 'O');
        FieldGame fieldGame = new FieldGame();
        PvP pvp = new PvP(player1, player2, fieldGame);
        System.out.println(pvp.gamePvP());
        scan.close();
    }
}

