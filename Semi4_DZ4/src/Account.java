import MyException.InsufficientFundsException;

public abstract class Account {

    protected String name;
    protected int money;

    protected Account(String name, int money) {
        this.name = name;
        this.money = money;
    }

    protected String getName() {
        return name;
    }

    protected int getMoney() {
        return money;
    }

    protected void addMoney(int money) throws IllegalArgumentException {
        if (money < 0) throw new IllegalArgumentException("Сумма пополнения не должна быть меньше нуля.");
        this.money += money;
        System.out.printf("Счёт %s пополнен на сумму %d. Баланс счёта: %d\n", name, money, this.money);
    }

    protected void takeMoney(int money) throws InsufficientFundsException {
        if ((this.money - money) < 0)
            throw new InsufficientFundsException("Сумма перевода %d превышает лимит депозита %s".formatted(money, name));
        this.money -= money;
        System.out.printf("Счёт %s опустошён на сумму %d. Баланс счёта: %d\n", name, money, this.money);
    }

    protected void showMoney() {
        System.out.printf("На счету %s: %d\n", name, money);
    }
}
