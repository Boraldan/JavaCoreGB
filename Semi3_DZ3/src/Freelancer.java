public class Freelancer extends Headhunter {


    public Freelancer(String name, long salary) {
        super(name, salary);
    }

    @Override
    public double salary() {
        return 20.8 * 8 * salary;
    }


}
