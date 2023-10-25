public class Woker extends Headhunter {
    public Woker(String name, long salary) {
        super(name, salary);
    }

    @Override
    public void salary() {
        System.out.println(salary);
    }


}
