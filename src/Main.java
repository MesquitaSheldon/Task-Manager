import io.qt.core.*;
import io.qt.widgets.QApplication;

public class Main {
    public static void main(String[] args) {
        QApplication.initialize(args);

        System.out.println("Hello, World!");

        QApplication.exec();
        QApplication.shutdown();
    }
}