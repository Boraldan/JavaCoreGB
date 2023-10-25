import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ServiceHH implements Iterable<Headhunter> {

    private List<Headhunter> listHH;

    public ServiceHH(List<Headhunter> listHH) {
        this.listHH = listHH;
    }

    public List<Headhunter> getListHH() {
        return listHH;
    }

    @Override
    public Iterator<Headhunter> iterator() {
        return new  IteratorHH(listHH);
    }
}
