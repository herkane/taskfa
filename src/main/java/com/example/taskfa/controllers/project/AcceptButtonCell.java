package com.example.taskfa.controllers.project;

import com.example.taskfa.modelDao.UserDAO;
import com.example.taskfa.utils.UserSession;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;

public class AcceptButtonCell implements Callback<TableColumn<InvitationModelTable, String>, TableCell<InvitationModelTable, String>> {

    private final ProjectViewController projectViewController;

    public AcceptButtonCell(ProjectViewController projectViewController){
        this.projectViewController = projectViewController;
    }

    @Override
    public TableCell<InvitationModelTable, String> call(TableColumn<InvitationModelTable, String> arg) {

        TableCell<InvitationModelTable, String> cell = new TableCell<InvitationModelTable, String>() {

            private final Button button;

            {
                button = new Button();
                button.setOnAction(evt -> {
                    InvitationModelTable item = getTableRow().getItem();
                });
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        button.setText("Accepted");
                        button.setDisable(true);
                        Image img = new Image(getClass().getResourceAsStream("/media/blue_notification_success.png"));
                        InvitationModelTable invitationModelTable = getTableRow().getItem();
                        System.out.println(invitationModelTable);
                        getTableRow().setDisable(true);
                        Notifications notificationBuilder = Notifications.create()
                                .title("Invitation")
                                .text("You have joined Project : "+invitationModelTable.getTitle()+" succesfly.")
                                .graphic(new ImageView(img))
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_RIGHT)
                                .onAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        System.out.println("clicked on notification");
                                    }
                                });
                        notificationBuilder.show();
                        try {
                            System.out.println("LOK LOK : " +invitationModelTable.getProjectId() + " " +  invitationModelTable.getUserId());
                            UserDAO.updateInvitation(invitationModelTable.getProjectId(), UserSession.getCurrentUser().getIdUser(), 1);
                            try {
                                projectViewController.getData();
                            } catch (SQLException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                            Notifications notificationBuilderSQL = Notifications.create()
                                    .title("Invitation")
                                    .text("SQL Problem trying " +
                                            "to join Project : "+invitationModelTable.getTitle()+".")
                                    .graphic(new ImageView(img))
                                    .hideAfter(Duration.seconds(4))
                                    .position(Pos.TOP_RIGHT)
                                    .onAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent actionEvent) {
                                            System.out.println("clicked on notification");
                                        }
                                    });
                            notificationBuilderSQL.show();
                        }

                    }
                });
                DropShadow shadow = new DropShadow();
                button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                        new EventHandler<MouseEvent>() {
                            @Override public void handle(MouseEvent e) {
                                button.setEffect(shadow);
                            }
                        });
                button.addEventHandler(MouseEvent.MOUSE_EXITED,
                        new EventHandler<MouseEvent>() {
                            @Override public void handle(MouseEvent e) {
                                button.setEffect(null);
                            }
                        });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                if (empty) {
                    // remove button
                    setGraphic(null);
                } else {
                    // set up button based on item
                    button.getStyleClass().remove("addBobOk");
                    if (item != null && !item.isEmpty()) {
                        button.getStyleClass().add("addBobOk");
                        button.setText("Approved");
                    } else {
                        button.setText("Accept");
                    }
                    setGraphic(button);
                }
            }
        };
        return cell;
    }
}
