package com.example.taskfa.controllers.tasks.user;

import com.example.taskfa.controllers.project.ProjectItemController;
import com.example.taskfa.controllers.tasks.TaskStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.DOMError;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class TaskViewController implements Initializable {
    @FXML
    private Label lblGreeting1;

    @FXML
    private Label lblName1;

    @FXML
    private Label lblToday;

    @FXML
    private Label lblUpcoming;

    @FXML
    private VBox vTaskItems;

    @FXML
    private VBox vTaskItemsupcoming;

    @FXML
    private VBox vTaskItemsInProgress;

    private ObservableList<TasksModel> listOfTasks;

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        ArrayList<TasksModel> model = new ArrayList<>();
        model.add(new TasksModel("Fix bug on issue #87", true, TaskStatus.NOTSTARTED));
        model.add(new TasksModel("Fix bug on issue #84", false,TaskStatus.PENDING));
        model.add(new TasksModel("Fix Label - Bug ", true,TaskStatus.NOTSTARTED));
        model.add(new TasksModel("Fix bug 7", true, TaskStatus.NOTSTARTED));
        model.add(new TasksModel("Fix bug on issue #87", true,TaskStatus.DONE));
        model.add(new TasksModel("Remove Button on FXML Project 23", false, TaskStatus.DONE));
        model.add(new TasksModel("Fix bug on issue #87", true, TaskStatus.NOTSTARTED));
        model.add(new TasksModel("Fix bug on issue #84", false,TaskStatus.PENDING));
        model.add(new TasksModel("Fix Label - Bug ", true,TaskStatus.NOTSTARTED));
        model.add(new TasksModel("Fix bug 7", true, TaskStatus.NOTSTARTED));
        model.add(new TasksModel("Fix bug on issue #87", true,TaskStatus.DONE));
        model.add(new TasksModel("Remove Button on FXML Project 23", false, TaskStatus.DONE));




        //load task items to vbox
        Node[] nodes = new Node[model.size()];
        System.out.println("nodes size : " + nodes.length);
        for (int i = 0; i < nodes.length - 6; i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/TaskItem.fxml"));
                nodes[i] = fxmlLoader.load();
                TaskItemController controller = fxmlLoader.getController();
                controller.setTasksModelConsumer(new Consumer<TasksModel>() {
                    @Override
                    public void accept(TasksModel tasksModel) {
                        System.out.println(tasksModel.getTitle() + " : " +  tasksModel.getStatus());
                        updateTask(tasksModel);
                    }
                });
                controller.setTask(model.get(i));
                /*
                //load specific item
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TaskItem.fxml"));
                TaskItemController controller = new TaskItemController();
                loader.setController(controller);
                 */
                vTaskItems.getChildren().add(nodes[i]);
               // controller.setTask(model.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        for (int i = 6; i < nodes.length - 3; i++) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/TaskItem.fxml"));
                nodes[i] = fxmlLoader.load();
                TaskItemController controller = fxmlLoader.getController();
                controller.setTasksModelConsumer(new Consumer<TasksModel>() {
                    @Override
                    public void accept(TasksModel tasksModel) {
                        System.out.println(tasksModel.getTitle() + " : " + tasksModel.getStatus());
                        updateTask(tasksModel);
                    }
                });
                controller.setTask(model.get(i));
                vTaskItemsInProgress.getChildren().add(nodes[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        for (int i = 9; i < nodes.length; i++) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/views/TaskItem.fxml"));
                    nodes[i] = fxmlLoader.load();
                    TaskItemController controller = fxmlLoader.getController();
                    controller.setTasksModelConsumer(new Consumer<TasksModel>() {
                        @Override
                        public void accept(TasksModel tasksModel) {
                            System.out.println(tasksModel.getTitle() + " : " +  tasksModel.getStatus());
                            updateTask(tasksModel);
                        }
                    });
                    controller.setTask(model.get(i));
                    vTaskItemsupcoming.getChildren().add(nodes[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }
    }

    private void updateTask(TasksModel tasksModel) {
        if (tasksModel.getStatus() == TaskStatus.NOTSTARTED) {
            vTaskItems.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                }
            });
        }
        if (tasksModel.getStatus() == TaskStatus.PENDING) {

        }
    }
/*
    private final Task<List<TasksModel>> fetchList = new Task() {

        @Override
        protected List<TasksModel> call() throws Exception {
            List<TasksModel> list = null;
            try {
                String url = readUrl(Constants.JSON_URL);
                System.out.println(url);
                list = new Gson().fromJson(url, new TypeToken<List<TasksModel>>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

    };

    private static String readUrl(String urlString) throws Exception {

        @Cleanup
        BufferedReader reader = null;

        URL url = new URL(urlString);
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder buffer = new StringBuilder();
        int read;
        char[] chars = new char[1024];
        while ((read = reader.read(chars)) != -1) {
            buffer.append(chars, 0, read);
        }

        return buffer.toString();
    }

 */
}
