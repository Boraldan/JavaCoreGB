import MyException.InsufficientFundsException;

public class Main {
    public static void main(String[] args) {
        Account account = null;

        try {
            account = DebitAccount.newAccount("Tom", 1000);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (account != null) account.addMoney(-99);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (account != null) account.takeMoney(150);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        Account debitAccount = null;
        Account creditAccount = null;

        try {
            debitAccount = DebitAccount.newAccount("Kat", 1000);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            creditAccount = CreditAccount.newAccount("Alex", 1000);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Transaction.trans(creditAccount, debitAccount, 500);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Transaction.trans(creditAccount, debitAccount, 5_000_000);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}