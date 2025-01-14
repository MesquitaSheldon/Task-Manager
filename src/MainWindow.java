import io.qt.core.QList;
import io.qt.core.Qt;
import io.qt.gui.*;
import io.qt.widgets.*;

public class MainWindow extends QMainWindow {
    public MainWindow(){
        setWindowTitle("Task Manager");
        setWindowIcon(new QIcon("src/resources/task-manager-window-icon.png"));
        setupSize();
        setupGUI();
    }

    private void setupSize(){
        resize(350, 400);
        setMinimumSize(350,150);
    }

    /*
+--------------------------------------+
| New Task: [__________] [Add]         |
|                                      |
| Task List:                           |
| [ ] Buy Milk                         |
| [ ] Study Qt Jambi                   |
|                                      |
| [Remove Selected Task]               |
+--------------------------------------+

*/
    private void setupGUI() {
        QWidget centralWidget = new QWidget();
        QWidget Task = new QWidget();

        QFormLayout layout = new QFormLayout();

        QFormLayout innerLayout = new QFormLayout();

        QPushButton editButton = new QPushButton("Add");
        editButton.setMaximumSize(100, 25);
        editButton.setMinimumSize(8, 25);

        //Enter Line
        QLineEdit entry = new QLineEdit();
        entry.setMaximumSize(500, 25);
        entry.setMinimumSize(8, 25);
        innerLayout.addRow(entry,editButton);
        layout.addRow("New Task: ", innerLayout);

        // Filters
        QPushButton checkedFilter = new QPushButton("Checkeds");
        checkedFilter.setMinimumSize(100, 20);
        QPushButton uncheckedFilter = new QPushButton("Uncheckeds");
        uncheckedFilter.setMinimumSize(100, 20);
        QPushButton allTasksFilter = new QPushButton("All Tasks");
        allTasksFilter.setMinimumSize(100, 20);
        QFormLayout innerLayout2 = new QFormLayout();
        innerLayout2.addRow(checkedFilter, uncheckedFilter);
        layout.addRow(allTasksFilter, innerLayout2);

        // Lista de Tarefas
        QListWidget taskList = new QListWidget();
        layout.addRow(taskList);
        editButton.clicked.connect(() -> {
            if(entry.getText().isBlank() || entry.getText().isEmpty()){
                warningWindow(1);
            } else {
                QListWidgetItem newItem = new QListWidgetItem(entry.getText());
                newItem.setCheckState(Qt.CheckState.Unchecked);
                taskList.addItem(newItem);
                entry.setText("");
            }
        });

        //Butão de esclusão
        QPushButton remove = new QPushButton("Remove Selected Task");
        layout.addRow(remove);

        remove.clicked.connect(() -> deleteItem(taskList));

        centralWidget.setLayout(layout);
        setCentralWidget(centralWidget);
    }

    private static void deleteItem(QListWidget taskList){
        QListWidgetItem selectedItem = taskList.currentItem();
        taskList.takeItem(taskList.row(selectedItem));
    }

    private static void warningWindow(int i){
        QMainWindow warningWindow = new QMainWindow();
        warningWindow.setWindowTitle("Error");
        warningWindow.setWindowIcon(new QIcon("src/resources/red-warning.png"));
        warningWindow.setWindowModality(Qt.WindowModality.WindowModal);

        switch (i){
            case 1: {
                QFormLayout layout = new QFormLayout();
                layout.addRow(new QLabel("Your input is invalid!\n\nPlease enter a valid text."));
                layout.setAlignment(Qt.AlignmentFlag.AlignAbsolute);
                QWidget centralWidget = new QWidget();

                centralWidget.setLayout(layout);
                warningWindow.setCentralWidget(centralWidget);
            }
            default:
                warningWindow.resize(200,100);
                warningWindow.show();
        }

    }
}