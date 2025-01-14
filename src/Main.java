import io.qt.widgets.QApplication;

public class Main {
    public static void main(String[] args) {
        QApplication.initialize(args);

        MainWindow mainWindow = new MainWindow();
        mainWindow.show();

        QApplication.exec();
    }
}