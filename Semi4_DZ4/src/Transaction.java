import MyException.InsufficientFundsException;

public class Transaction {
    public static void trans(Account donater, Account recipient, int money) throws InsufficientFundsException, IllegalArgumentException {
        if (money < 0) throw new IllegalArgumentException("Сумма перевода не должна быть меньше нуля.");
        if (donater == null) throw new IllegalArgumentException("Не существует счета отправителя.");
        if (recipient == null) throw new IllegalArgumentException("Не существует счета получателя.");
        donater.takeMoney(money);
        recipient.addMoney(money);
    }
}
