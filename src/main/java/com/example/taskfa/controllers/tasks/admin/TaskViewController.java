package com.example.taskfa.controllers.tasks.admin;

import com.example.taskfa.controllers.tasks.TaskStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class TaskViewController {
    @FXML
    private Button assignTask;

    @FXML
    private TableView<TaskItemModelController> doneTable;

    @FXML
    private TableColumn<TaskItemModelController, String> firstNameDone;

    @FXML
    private TableView<TaskItemModelController> inProgressTable;

    @FXML
    private TableColumn<TaskItemModelController, String> lastNameDone;

    @FXML
    private TableColumn<TaskItemModelController, String> lastNameInProgress;

    @FXML
    private TableColumn<TaskItemModelController, String> lastNameToDo;

    @FXML
    private TableColumn<TaskItemModelController, String> statusDone;

    @FXML
    private TableColumn<TaskItemModelController, String> statusInProgress;

    @FXML
    private TableColumn<TaskItemModelController, String> statusTodo;

    @FXML
    private TableColumn<TaskItemModelController, String> taskDone;

    @FXML
    private TableColumn<TaskItemModelController, String> taskInProgress;

    @FXML
    private TableColumn<TaskItemModelController, String> taskToDo;

    @FXML
    private TableView<TaskItemModelController> todoTable;

    @FXML
    private TextField filterBox;

    private ObservableList<TaskItemModelController> tasksDone = null;



    public void loadFXML(int projectIdPassed) {
        tasksDone = FXCollections.observableArrayList();
        tasksDone.add(new TaskItemModelController("Aissam","Boussoufiane","This is a new task to fix", TaskStatus.DONE));
        tasksDone.add(new TaskItemModelController("Zeeshan","Mahoney","This is a new task to fix", TaskStatus.DONE));
        tasksDone.add(new TaskItemModelController("Blaine ","Miller","This is a new task to fix", TaskStatus.DONE));
        tasksDone.add(new TaskItemModelController("Tanya ","Storey","This is a new task to fix", TaskStatus.DONE));
        tasksDone.add(new TaskItemModelController("Ashleigh","Davenport","This is a new task to fix", TaskStatus.DONE));
        tasksDone.add(new TaskItemModelController("Veer ","Battle","This is a new task to fix", TaskStatus.DONE));
        tasksDone.add(new TaskItemModelController("Karol ","Ibarra","This is a new task to fix", TaskStatus.DONE));
        tasksDone.add(new TaskItemModelController("Shanai ","Villa","This is a new task to fix", TaskStatus.DONE));
        tasksDone.add(new TaskItemModelController("Timothy ","Clements","This is a new task to fix", TaskStatus.DONE));
        tasksDone.add(new TaskItemModelController("Aissam","Boussoufiane","This is a new task to fix", TaskStatus.DONE));
        tasksDone.add(new TaskItemModelController("Aissam","Boussoufiane","This is a new task to fix", TaskStatus.DONE));

        firstNameDone.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameDone.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        taskDone.setCellValueFactory(new PropertyValueFactory<>("task"));
        statusDone.setCellValueFactory(new PropertyValueFactory<>("taskStatus"));
        doneTable.setItems(tasksDone);

        lastNameInProgress.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        taskInProgress.setCellValueFactory(new PropertyValueFactory<>("task"));
        statusInProgress.setCellValueFactory(new PropertyValueFactory<>("taskStatus"));
        inProgressTable.setItems(tasksDone);

        lastNameToDo.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        taskToDo.setCellValueFactory(new PropertyValueFactory<>("task"));
        statusTodo.setCellValueFactory(new PropertyValueFactory<>("taskStatus"));
        todoTable.setItems(tasksDone);

        FilteredList<TaskItemModelController>  filtredData = new FilteredList<>(tasksDone, b -> true);

        filterBox.textProperty().addListener(((observableValue, oldValue, newValue) -> {
               filtredData.setPredicate(taskItemModelController -> {
                   if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                       return true;
                   }

                   String searchKeyword = newValue.toLowerCase();
                   if (taskItemModelController.getLastName().toLowerCase().indexOf(searchKeyword) > -1) {
                       return true;
                   } else if (taskItemModelController.getFirstName().toLowerCase().indexOf(searchKeyword) > -1) {
                       return true;
                   } else {
                       return false;
                   }
               });
        } ));
        SortedList<TaskItemModelController> sortedData = new SortedList<>(filtredData);
        sortedData.comparatorProperty().bind(doneTable.comparatorProperty());
        doneTable.setItems(sortedData);
        sortedData.comparatorProperty().bind(inProgressTable.comparatorProperty());
        inProgressTable.setItems(sortedData);
        sortedData.comparatorProperty().bind(todoTable.comparatorProperty());
        todoTable.setItems(sortedData);
    }

    public void assignTaskClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/popups/assignTask.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load());
        } catch (IOException ex) {
            System.out.println("ERROR WHATCH OUT !");
        }
        Stage inputStage = new Stage();
        inputStage.initOwner(filterBox.getScene().getWindow());
        inputStage.setScene(newScene);
        inputStage.showAndWait();
    }
}
