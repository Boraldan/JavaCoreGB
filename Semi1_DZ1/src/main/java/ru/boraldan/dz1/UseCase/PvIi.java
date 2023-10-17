package ru.boraldan.dz1.UseCase;

import ru.boraldan.dz1.Domen.FieldGame;
import ru.boraldan.dz1.Domen.Ii;
import ru.boraldan.dz1.Domen.Player;

public class PvIi {

    private Player player1;
    private Ii playerIi;

    private FieldGame fieldGame;

    public PvIi(Player player1, Ii player2, FieldGame fieldGame) {
        this.player1 = player1;
        this.playerIi = player2;
        this.fieldGame = fieldGame;
    }

    public String gamePvIi() {

        fieldGame.printField(fieldGame.fieldStart);
        int step = 1;
        int fist = (int) (Math.random() * 9);

        if (fist % 2 == 0) {
            while (step < 10) {
                if (step % 2 != 0) {
                    int move = fieldGame.move(player1.name + " : X ");
                    if (move == -1) return "Победа игрока " + playerIi.name;
                    fieldGame.fieldGame[move] = player1.fishka;
                    fieldGame.printField(fieldGame.fieldGame);
                    if (fieldGame.checkWin()) return "Победа игрока " + player1.name;
                } else {
                    int move = playerIi.logicIi.moveIi(fieldGame.fieldGame, step);
                    if (move == -1) return "Победа игрока " + player1.name;
                    fieldGame.fieldGame[move] = playerIi.fishka;
                    fieldGame.printField(fieldGame.fieldGame);
                    if (fieldGame.checkWin()) return "Победа игрока " + playerIi.name;
                }
                step++;
            }
        } else {
            while (step < 10) {
                if (step % 2 != 0) {
                    int move = playerIi.logicIi.moveIi(fieldGame.fieldGame, step);
                    if (move == -1) return "Победа игрока " + player1.name;
                    fieldGame.fieldGame[move] = playerIi.fishka;
                    fieldGame.printField(fieldGame.fieldGame);
                    if (fieldGame.checkWin()) return "Победа игрока " + playerIi.name;
                } else {
                    int move = fieldGame.move(player1.name + " : X ");
                    if (move == -1) return "Победа игрока " + playerIi.name;
                    fieldGame.fieldGame[move] = player1.fishka;
                    fieldGame.printField(fieldGame.fieldGame);
                    if (fieldGame.checkWin()) return "Победа игрока " + player1.name;
                }
                step++;
            }
        }
        return "----- НИЧЬЯ! -----";
    }
}
