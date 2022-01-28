package com.example.taskfa.controllers.tasks.user;

import com.example.taskfa.controllers.project.ProjectItemController;
import com.example.taskfa.controllers.tasks.TaskStatus;
import com.example.taskfa.modelDao.UserDAO;
import com.example.taskfa.utils.UserSession;
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
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TaskViewController {
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
    private VBox vTaskItemsdone;

    @FXML
    private VBox vTaskItemsInProgress;

    private int projectId;

    private ObservableList<TasksModel> model;

    ArrayList<TasksModel> taskNotStarted = null;
    ArrayList<TasksModel> taskInProgress = null;
    ArrayList<TasksModel> taskDone = null;

    private Node[] nodes1,nodes2,nodes3;

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }

    public void getData() {
        try {
            model = UserDAO.getTasks(projectId, UserSession.getCurrentUser().getIdUser());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void loadFXML(int projectIdpassed){
        projectId = projectIdpassed;
        getData();
        // TODO
        /*
        ArrayList<TasksModel> model = new ArrayList<>();

        model.add(new TasksModel("Fix bug on issue #87", false, TaskStatus.NOTSTARTED));
        model.add(new TasksModel("Fix bug on issue #84", false,TaskStatus.NOTSTARTED));
        model.add(new TasksModel("Fix bug on issue #50", false,TaskStatus.PENDING));
        model.add(new TasksModel("Fix bug on issue #51", false, TaskStatus.PENDING));
        model.add(new TasksModel("Fix bug on issue #60", true,TaskStatus.DONE));
        model.add(new TasksModel("Fix bug on issue #61", true, TaskStatus.DONE));

        model.add(new TasksModel("Fix bug on issue #87", true, TaskStatus.NOTSTARTED));
        model.add(new TasksModel("Fix bug on issue #84", false,TaskStatus.PENDING));
        model.add(new TasksModel("Fix Label - Bug ", true,TaskStatus.NOTSTARTED));
        model.add(new TasksModel("Fix bug 7", true, TaskStatus.NOTSTARTED));
        model.add(new TasksModel("Fix bug on issue #87", true,TaskStatus.DONE));
        model.add(new TasksModel("Remove Button on FXML Project 23", false, TaskStatus.DONE));
         */
        ArrayList<TasksModel> modelList = new ArrayList<>(model);
       taskNotStarted = filterList(modelList, TaskStatus.NOTSTARTED);
       taskInProgress = filterList( modelList, TaskStatus.PENDING);
       taskDone = filterList(modelList, TaskStatus.DONE);

        nodes1 = new Node[model.size()];
        nodes2 = new Node[model.size()];
        nodes3 = new Node[model.size()];

        for (int i = 0; i < taskNotStarted.size() ; i++) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/views/TaskItem.fxml"));
                    nodes1[i] = fxmlLoader.load();
                    TaskItemController controller = fxmlLoader.getController();
                    int finalI = i;
                    controller.setTasksModelConsumer(new Consumer<TaskItemController>() {
                        @Override
                        public void accept(TaskItemController taskItemController) {
                            TasksModel tasksModel = taskItemController.getTasksModel();
                            System.out.println(tasksModel.getTitle() + " : " +  tasksModel.getStatus());
                            // getLastIndex(nodes3);
                            updateTask(taskItemController, controller.getPosition());
                        }
                    });
                    controller.setTask(taskNotStarted.get(i),i);
                    vTaskItems.getChildren().add(nodes1[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        for (int i = 0; i < taskInProgress.size(); i++) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/views/TaskItem.fxml"));
                    nodes2[i] = fxmlLoader.load();
                    TaskItemController controller = fxmlLoader.getController();
                    controller.setTasksModelConsumer(new Consumer<TaskItemController>() {
                        @Override
                        public void accept(TaskItemController taskItemController) {
                            TasksModel tasksModel = taskItemController.getTasksModel();
                            System.out.println(tasksModel.getTitle() + " : " +  tasksModel.getStatus());
                            // getLastIndex(nodes3);
                            updateTask(taskItemController, controller.getPosition());
                        }
                    });
                    controller.setTask(taskInProgress.get(i),i);
                    vTaskItemsInProgress.getChildren().add(nodes2[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        for (int i = 0; i < taskDone.size(); i++) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/views/TaskItem.fxml"));
                    nodes3[i] = fxmlLoader.load();
                    TaskItemController controller = fxmlLoader.getController();
                    controller.setTasksModelConsumer(new Consumer<TaskItemController>() {
                        @Override
                        public void accept(TaskItemController taskItemController) {
                            TasksModel tasksModel = taskItemController.getTasksModel();
                            System.out.println(tasksModel.getTitle() + " : " +  tasksModel.getStatus());
                            // getLastIndex(nodes3);
                            updateTask(taskItemController, controller.getPosition());
                        }
                    });
                    controller.setTask(taskDone.get(i), i);
                    vTaskItemsdone.getChildren().add(nodes3[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

    }

    private void updateTask(TaskItemController taskItemController, int position) {
        System.out.println("-------------------------------------------");
        showStatus();
        System.out.println("-------------------------------------------");
        Node node;
        TasksModel tasksModel = taskItemController.getTasksModel();
        System.out.print(tasksModel.getTitle() + " ");
        if (tasksModel.getStatus() == TaskStatus.NOTSTARTED) {
            System.out.println("from DONE to NOT STARTED");
            System.out.println("Delete from position " + position);
            int validPosition = checkPosition(nodes3, position);
            try{
                vTaskItemsdone.getChildren().remove(validPosition);
                // vTaskItemsdone.getChildren().get(position).setVisible(false);
            } catch (Exception e) {
                System.out.println("Out of index " + validPosition + " in Done Box");
                // vTaskItemsdone.getChildren().get(position-1).setVisible(false);
            }
            node = nodes3[position];
            node.setVisible(true);
            nodes3[position] = null;
            int newPosition = getLastIndex(nodes1);
            System.out.println(tasksModel.getTitle()+" : nodes3["+position+ "] -> nodes1["+newPosition+"]");
            nodes1[newPosition] = node;
            vTaskItems.getChildren().add(node);
            taskItemController.setPosition(newPosition);
            showStatus();
            try {
                UserDAO.updateTask(tasksModel.getTaskId(), tasksModel.getStatus());
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return ;
        }
        if (tasksModel.getStatus() == TaskStatus.PENDING) {
            System.out.println("from STARTED to PENDING");
            System.out.println("Delete from position " + position);
            int validPosition = checkPosition(nodes1, position);
            try{
                vTaskItems.getChildren().remove(validPosition);
             //   vTaskItems.getChildren().get(position).setVisible(false);
            } catch (Exception e) {
                System.out.println("Out of index " + validPosition + " in Todo Box");
              //  vTaskItems.getChildren().get(position-1).setVisible(false);
            }
            node = nodes1[position];
            node.setVisible(true);
            nodes1[position] = null;
            int newPosition = getLastIndex(nodes2);
            System.out.println(tasksModel.getTitle()+" : nodes1["+position+ "] -> nodes2["+newPosition+"]");
            nodes2[newPosition] = node;
            vTaskItemsInProgress.getChildren().add(node);
            taskItemController.setPosition(newPosition);
            showStatus();
            try {
                UserDAO.updateTask(tasksModel.getTaskId(), tasksModel.getStatus());
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }
        else {
            System.out.println("from PENDING to DONE");
            System.out.println("Delete from position " + position);
            int validPosition = checkPosition(nodes2, position);
            try{
                vTaskItemsInProgress.getChildren().remove(validPosition);
                //vTaskItemsInProgress.getChildren().get(position).setVisible(false);
            } catch (Exception e) {
                System.out.println("Out of index " + validPosition + " in progress Box");
                //vTaskItemsInProgress.getChildren().get(position-1).setVisible(false);
            }
            node = nodes2[position];
            node.setVisible(true);
            nodes2[position] = null;
            int newPosition = getLastIndex(nodes3);
            System.out.println(tasksModel.getTitle()+" : nodes2["+position+ "] -> nodes3["+newPosition+"]");
            nodes3[newPosition] = node;
            vTaskItemsdone.getChildren().add(node);
            taskItemController.setPosition(newPosition);
            showStatus();
            try {
                UserDAO.updateTask(tasksModel.getTaskId(), tasksModel.getStatus());
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return ;
        }
    }
    private void showStatus() {
        System.out.print("Nodes 1 : ");
        for (int i=0; i< nodes1.length; i++) {
            if (nodes1[i] == null) {
                System.out.print(i+" ");
            }
        }
        System.out.println();
        System.out.print("Nodes 2 : ");
        for (int i=0; i< nodes2.length; i++) {
            if (nodes2[i] == null) {
                System.out.print(i+" ");
            }
        }
        System.out.println();
        System.out.print("Nodes 3 : ");
        for (int i=0; i< nodes3.length; i++) {
            if (nodes3[i] == null) {
                System.out.print(i+" ");
            }
        }
        System.out.println();
    }
    /*
    private void loadFXML() {

    }
     */
    private int checkPosition(Node[] array, int position) {
        int count = 0;
        for (int i=0; i < array.length; i++) {
            /*
            if (i == position && i-1>0 &&array[i-1] == null) {
                System.out.println("position : " + (i-1) +  " -> "+position);
                return (i-1);
            }
             */
            if (array[i] == null && i < position) {
                count++;
            }
        }
        if (position - count >= 0) return position - count;
        return position;
    }
    private int getLastIndex(Node[] array) {
        if (array[0] == null) return 0;
        for (int i=1; i < array.length; i++) {
            System.out.print(i + " ");
            if (array[i] == null) {
                return i;
            }
            if (i == array.length && array[i] != null) i = 0;
        }
        return array.length;
    }
    private ArrayList<TasksModel> filterList(ArrayList<TasksModel> list, TaskStatus taskStatus) {
        ArrayList<TasksModel> tasksModels = new ArrayList<>();
        for (TasksModel tasksModel : list) {
            if (tasksModel.getStatus() == taskStatus) {
                tasksModels.add(tasksModel);
            }
        }
        return tasksModels;
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
