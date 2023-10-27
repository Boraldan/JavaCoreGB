import java.util.Iterator;
import java.util.List;

public class AllWorker  implements Iterable<Woker> {
    /**
     * Лист работников.
     */
    private List<Woker> workersList;
    /**
     * Счетчик для использования итератора.
     */
    private int counter;

    /**
     * Конструктор класса.
     *
     * @param list коллекция работников.
     */
    public AllWorker(List<Woker> list) {
        this.workersList = list;
        counter = 0;
    }

    /**
     * Переопределенный метод итератора.
     *
     * @return
     */
    @Override
    public Iterator<Woker> iterator() {
        return new Iterator<Woker>() {
            // В анонимном классе определяем логику итерации

            /**
             * Проверка, что есть по чему итерироваться.
             * @return true - если не вышли за пределы коллекции, иначе - false.
             */
            @Override
            public boolean hasNext() {
                return counter < workersList.size();
            }

            /**
             * Возвращаем элемент из коллекции, при корректной проверке возможности итерации
             * и увеличиваем счетчик на 1.
             * @return объект коллекции.
             */
            @Override
            public Woker next() {
                if (!hasNext()) {
                    return null;
                } else {
                    return workersList.get(counter++);
                }
            }
        };
    }
}
