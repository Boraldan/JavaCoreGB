import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Headhunter> hh = new ArrayList<>();
        hh.add(new Woker("Kat", 30000));
        hh.add(new Freelancer("Alex", 1000));
        hh.add(new Woker("Alis", 35000));
        hh.add(new Freelancer("Tom", 500));


        Collections.sort(hh);
        System.out.println(hh);


        Collections.sort(hh, new ComporatorSalary());
        System.out.println(hh);

        System.out.println("Список Woker");
        for (Headhunter el : hh) {
            if (el instanceof Woker) {
                System.out.println(el);
            }
        }

        System.out.println("Список Freelancer");
        for (Headhunter el : hh) {
            if (el instanceof Freelancer) {
                System.out.println(el);
            }
        }

        System.out.println("Общий список сотрудников");
        Iterator<Headhunter> iterator = hh.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Запуск собственного итератора  --->");
        ServiceHH serviceHH = new ServiceHH(hh);
        for (Headhunter el : serviceHH) {
            System.out.println(el.getName()  + "  " + el.salary());
        }

    }
}