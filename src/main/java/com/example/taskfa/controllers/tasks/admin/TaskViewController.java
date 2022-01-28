package com.example.taskfa.controllers.tasks.admin;

import com.example.taskfa.controllers.chat.ChatViewController;
import com.example.taskfa.controllers.tasks.TaskStatus;
import com.example.taskfa.controllers.tasks.user.TasksModel;
import com.example.taskfa.modelDao.TaskDAO;
import com.example.taskfa.modelDao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskViewController {
    @FXML
    private Button assignTask;

    @FXML
    private TableView<TaskItemModel> doneTable;

    @FXML
    private TableColumn<TaskItemModel, String> firstNameDone;

    @FXML
    private TableView<TaskItemModel> inProgressTable;

    @FXML
    private TableColumn<TaskItemModel, String> lastNameDone;

    @FXML
    private TableColumn<TaskItemModel, String> lastNameInProgress;

    @FXML
    private TableColumn<TaskItemModel, String> lastNameToDo;

    @FXML
    private TableColumn<TaskItemModel, String> statusDone;

    @FXML
    private TableColumn<TaskItemModel, String> statusInProgress;

    @FXML
    private TableColumn<TaskItemModel, String> statusTodo;

    @FXML
    private TableColumn<TaskItemModel, String> taskDone;

    @FXML
    private TableColumn<TaskItemModel, String> taskInProgress;

    @FXML
    private TableColumn<TaskItemModel, String> taskToDo;

    @FXML
    private TableView<TaskItemModel> todoTable;

    @FXML
    private TextField filterBox;

    private ObservableList<TaskItemModel> tasks = null;
    private ObservableList<TaskItemModel> tasksDone, tasksInProgress,tasksToDo;
    private ObservableList<TaskItemModel> filterList(ObservableList<TaskItemModel> list, TaskStatus taskStatus) {
        ObservableList<TaskItemModel> tasksModels = FXCollections.observableArrayList();

        for (TaskItemModel taskItemModel : list) {
            if (taskItemModel.getTaskStatus() == taskStatus) {
                tasksModels.add(taskItemModel);
            }
        }
        return tasksModels;
    }
    private int projectId;

    public void loadFXML(int projectIdPassed) {
        projectId = projectIdPassed;
        try {
            tasks = TaskDAO.getTasksForAdmin(projectIdPassed);
            tasksDone = filterList(tasks, TaskStatus.DONE);
            tasksInProgress = filterList(tasks, TaskStatus.PENDING);
            tasksToDo = filterList(tasks, TaskStatus.NOTSTARTED);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        firstNameDone.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameDone.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        taskDone.setCellValueFactory(new PropertyValueFactory<>("task"));
        statusDone.setCellValueFactory(new PropertyValueFactory<>("taskStatus"));
        doneTable.setItems(tasksDone);

        lastNameInProgress.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        taskInProgress.setCellValueFactory(new PropertyValueFactory<>("task"));
        statusInProgress.setCellValueFactory(new PropertyValueFactory<>("taskStatus"));
        inProgressTable.setItems(tasksInProgress);

        lastNameToDo.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        taskToDo.setCellValueFactory(new PropertyValueFactory<>("task"));
        statusTodo.setCellValueFactory(new PropertyValueFactory<>("taskStatus"));
        todoTable.setItems(tasksToDo);

        FilteredList<TaskItemModel>  filtredData = new FilteredList<>(tasksDone, b -> true);
        FilteredList<TaskItemModel>  filtredData2 = new FilteredList<>(tasksInProgress, b -> true);
        FilteredList<TaskItemModel>  filtredData3 = new FilteredList<>(tasksToDo, b -> true);

        filterBox.textProperty().addListener(((observableValue, oldValue, newValue) -> {
               filtredData.setPredicate(taskItemModel -> {
                   if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                       return true;
                   }

                   String searchKeyword = newValue.toLowerCase();
                   if (taskItemModel.getLastName().toLowerCase().indexOf(searchKeyword) > -1) {
                       return true;
                   } else if (taskItemModel.getFirstName().toLowerCase().indexOf(searchKeyword) > -1) {
                       return true;
                   } else {
                       return false;
                   }
               });
            filtredData2.setPredicate(taskItemModel -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (taskItemModel.getLastName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (taskItemModel.getFirstName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else {
                    return false;
                }
            });
            filtredData3.setPredicate(taskItemModel -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (taskItemModel.getLastName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (taskItemModel.getFirstName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else {
                    return false;
                }
            });

        } ));
        SortedList<TaskItemModel> sortedData = new SortedList<>(filtredData);
        SortedList<TaskItemModel> sortedData2 = new SortedList<>(filtredData2);
        SortedList<TaskItemModel> sortedData3 = new SortedList<>(filtredData3);


        sortedData.comparatorProperty().bind(doneTable.comparatorProperty());
        doneTable.setItems(sortedData);
        sortedData.comparatorProperty().bind(inProgressTable.comparatorProperty());
        inProgressTable.setItems(sortedData2);
        sortedData.comparatorProperty().bind(todoTable.comparatorProperty());
        todoTable.setItems(sortedData3);
    }

    public void assignTaskClick() {
        AssignTaskController assignTaskController;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/views/popups/assignTask.fxml"));
        Pane overview = null;
        try {
            overview = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(overview);
        assignTaskController = fxmlLoader.getController();
        assignTaskController.loadFXML(projectId, this);
        Stage inputStage = new Stage();
        inputStage.initOwner(filterBox.getScene().getWindow());
        inputStage.setScene(scene);
        inputStage.showAndWait();
    }
}
