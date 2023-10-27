import MyException.InsufficientFundsException;

public class Account {

    protected String name;
    protected int money;

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public static Account newAccount( String name, int money) throws IllegalArgumentException {
        if (money < 0) throw new IllegalArgumentException("Начальная сумма счёта не должна быть меньше нуля.");
        System.out.printf("Создан новый счет %s с суммой: %d\n", name, money);
        return new Account(name, money);
    }

    public void addMoney(int money) throws IllegalArgumentException {
        if (money < 0) throw new IllegalArgumentException("Сумма пополнения не должна быть меньше нуля.");
        this.money += money;
        System.out.printf("Счёт %s пополнен на сумму %d. Баланс счёта: %d\n", name, money, this.money);
    }

    public void takeMoney(int money) throws InsufficientFundsException {
        if ((this.money - money) < 0) throw new InsufficientFundsException("Сумма перевода %d превышает лимит депозита %s".formatted(money, name));
        this.money -= money;
        System.out.printf("Счёт %s опустошён на сумму %d. Баланс счёта: %d\n", name, money, this.money);
    }

    public void showMoney() {
        System.out.printf("На счету %s: %d\n", name, money);
    }
}
