package ru.boraldan.dz1.Command;

import ru.boraldan.dz1.Domen.FieldGame;
import ru.boraldan.dz1.Domen.Ii;
import ru.boraldan.dz1.Domen.Player;
import ru.boraldan.dz1.UseCase.LogicIi;
import ru.boraldan.dz1.UseCase.PvIi;
import java.util.Scanner;

public class Com3_PvIi implements Option {
    @Override
    public String info() {
        String text = "Режим PvIi";
        return text;
    }

    @Override
    public void doit() {
        System.out.print("Введите имя первого игрока: ");
        Scanner scan = new Scanner(System.in);
        String name1 = scan.nextLine();
        Player player1 = new Player(name1, 'X');
        Ii ii = new Ii(new LogicIi());
        FieldGame fieldGame = new FieldGame();
        PvIi pvIi = new PvIi(player1, ii, fieldGame);
        System.out.println(pvIi.gamePvIi());
        scan.close();
    }
}
