public class Freelancer extends Headhunter {


    public Freelancer(String name, long salary) {
        super(name, salary);
    }

    @Override
    public void salary() {
        System.out.println(20.8 * 8 * salary);
    }


}
