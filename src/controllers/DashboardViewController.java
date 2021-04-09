package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Professor;
import models.Student;
import utilities.DBUtility;
import utilities.SceneChanger;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardViewController implements Initializable {

    @FXML
    private Label studentsLabel;

    @FXML
    private ListView<Student> studentListView;

    @FXML
    private Label professorsLabel;

    @FXML
    private ListView<Professor> professorsListView;

    @FXML
    private Label coursesLabel;

    //    @FXML
//    private ListView<Course> coursesListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            studentListView.getItems().addAll(DBUtility.getStudentsFromDB());
            professorsListView.getItems().addAll(DBUtility.getProfessorsFromDB());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        studentsLabel.setText(String.format("Students: %d",studentListView.getItems().size()));


        professorsLabel.setText(String.format("Professors: %d",professorsListView.getItems().size()));
    }

    @FXML
    private void createNewStudentButton(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "../views/createStudentView.fxml","EdMuse - Create New Student");
//        Parent root = FXMLLoader.load(getClass().getResource("../views/createStudentView.fxml"));
//        Scene scene = new Scene(root);
//
//        //get the stage from the event that was passed in
//        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//        stage.setScene(scene);
//        stage.setTitle("EdMuse - Create New Student");
//        stage.show();
    }

    @FXML
    private void createNewProfessorButton(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "../views/createProfessorView.fxml","EdMuse - Create New Professor");
    }




}

