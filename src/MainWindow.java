import io.qt.gui.QIcon;
import io.qt.widgets.QMainWindow;

public class MainWindow extends QMainWindow {
    public MainWindow(){
        setWindowTitle("Task Manager");
        setWindowIcon(new QIcon("src/resources/task-manager-window-icon.png"));
        setupGUI();
    }

    private void setupGUI(){

    }
}
