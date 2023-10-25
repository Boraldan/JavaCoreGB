import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class Headhunter implements Comparable<Headhunter> {

    protected String name;
    protected long salary;

    public Headhunter(String name, long salary) {
        this.name = name;
        this.salary = salary;
    }

    public abstract void salary();

    public String getName() {
        return name;
    }

    public long getSalary() {
        return salary;
    }


    @Override
    public int compareTo(Headhunter o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "name " + name + "  :  salary " + salary;
    }


}
