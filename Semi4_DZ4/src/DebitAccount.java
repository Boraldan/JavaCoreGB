public class DebitAccount extends Account {
    public DebitAccount(String name, int money) {
        super(name, money);
    }

    public static Account newAccount(String name, int money) throws IllegalArgumentException {
        if (money < 0) throw new IllegalArgumentException("Начальная сумма счёта не должна быть меньше нуля.");
        System.out.printf("Создан новый счет %s с суммой: %d\n", name, money);
        return new DebitAccount(name, money);
    }
}
