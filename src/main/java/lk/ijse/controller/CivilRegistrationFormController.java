package lk.ijse.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CivilRegistrationFormController {
    public AnchorPane  CivilRPane;
    public void btnHomeOnAction(ActionEvent actionEvent) {
        openView ("homeRegistrationForm");

    }




    public void btncandidateOnAction(ActionEvent actionEvent) {
        openView ("candidateForm");
    }

    public void btnlandOnAction(ActionEvent actionEvent) {
    }

    public void btnIndivOnAction(ActionEvent actionEvent) throws IOException {
        openView ("individualForm");
    }

    private void openView (String view ){
        Stage stage = (Stage)CivilRPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/"+view+".fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(view);
        stage.centerOnScreen();
    }
}
