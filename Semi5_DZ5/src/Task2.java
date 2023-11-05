import java.io.File;

public class Task2 {

    //TODO: Доработайте класс Tree и метод print который мы разработали на семинаре.
    // Ваш метод должен распечатать полноценное дерево директорий и файлов относительно корневой директории.
    public static void main(String[] args) {
        print(new File("./Semi5_DZ5"), "", true);
//        print(new File("."), "", true);
    }

    static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subFileTotal = 0;
        for (int j = 0; j < files.length; j++) {
            if (files[j].isFile()) {
                subFileTotal++;
            }
        }

        int subFileCounter = 0;
        for (int j = 0; j < files.length; j++) {
            if (files[j].isFile()) {
                subFileCounter++;
                print(files[j], indent, subFileTotal == subFileCounter);
            }
        }

        int subDirTotal = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory())
                subDirTotal++;
        }

        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                subDirCounter++;

                print(files[i], indent, subDirCounter == subDirTotal);

            }
        }
    }
}
