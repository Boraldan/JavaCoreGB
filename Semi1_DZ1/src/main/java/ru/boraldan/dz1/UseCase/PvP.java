package ru.boraldan.dz1.UseCase;

import ru.boraldan.dz1.Domen.FieldGame;
import ru.boraldan.dz1.Domen.Player;

public class PvP {
    private Player player1;
    private Player player2;
    private FieldGame fieldGame;

    public PvP(Player player1, Player player2, FieldGame fieldGame) {
        this.player1 = player1;
        this.player2 = player2;
        this.fieldGame = fieldGame;
    }
    public String gamePvP() {

        fieldGame.printField(fieldGame.fieldStart);
        int step = 1;

        while (step < 10) {
            if (step % 2 != 0) {
                int move = fieldGame.move(player1.name + " : X ");
                if (move == -1) return "Победа игрока " + player2.name;
                fieldGame.fieldGame[move] = player1.fishka;
                fieldGame.printField(fieldGame.fieldGame);
                if (fieldGame.checkWin()) return "Победа игрока " + player1.name;
            } else {
                int move = fieldGame.move(player2.name + " : O ");
                if (move == -1) return "Победа игрока " + player1.name;
                fieldGame.fieldGame[move] = player2.fishka;
                fieldGame.printField(fieldGame.fieldGame);
                if (fieldGame.checkWin()) return "Победа игрока " + player2.name;
            }
            step++;
        }
        return "----- НИЧЬЯ! -----";
    }
}
