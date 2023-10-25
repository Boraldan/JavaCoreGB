import java.util.Comparator;

public class ComporatorSalary implements Comparator<Headhunter> {
    @Override
    public int compare(Headhunter o1, Headhunter o2) {
//        if (o1.getSalary() < o2.getSalary()) return -1;
//        else if (o1.getSalary() > o2.getSalary()) return 1;
//        else return 0;
//
        return Long.compare(o1.getSalary(), o2.getSalary());
    }
}
