import java.util.Iterator;
import java.util.List;

public class IteratorHH implements Iterator<Headhunter> {
    private List<Headhunter> listHH;
    public IteratorHH(List<Headhunter> listHH) {
        this.listHH = listHH;
    }
    public List<Headhunter> getListHH() {
        return listHH;
    }
    private int count = 0;

    @Override
    public boolean hasNext() {
        return count < listHH.size()                ;
    }

    @Override
    public Headhunter next() {
        return listHH.get(count++);
    }
}
