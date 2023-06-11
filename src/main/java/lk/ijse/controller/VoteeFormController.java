package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.VoteeBO;
import lk.ijse.bo.custom.impl.VoteeBOImpl;
import lk.ijse.dto.DetailDTO;
import lk.ijse.dao.custom.impl.util.OpenView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.VoteLoginFormController.civilID;

public class VoteeFormController implements Initializable {
    public AnchorPane Pane;
    public Label lblDate;
    public CheckBox cb1;
    public CheckBox cb2;
    public CheckBox cb3;
    public Label lblElection;
    VoteeBO voteeBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.VOTEEBO);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOrderDate();
    }

    private void setOrderDate() {
      lblDate.setText(String.valueOf(LocalDate.now()));
    }

    public void lblLogOnAction(MouseEvent mouseEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            DetailDTO detail = new DetailDTO("Logged out", "bethmi", LocalTime.now(), LocalDate.now(),"");
            try {
                voteeBO.saveDetail(detail);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            OpenView.openView("loginForm", Pane);
        }
    }



    public void lblVoteOnAction(MouseEvent mouseEvent) {
        OpenView.openView("voteLoginForm",Pane);
    }



    public void cb1OnAction(ActionEvent actionEvent) {
        if(cb2.isSelected() || cb3.isSelected()){
            cb1.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            new Alert(Alert.AlertType.ERROR,"You can only select one candidate!").show();
        }
    }

    public void cb2OnAction(ActionEvent actionEvent) {
        if(cb1.isSelected() || cb3.isSelected()){
            cb2.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            new Alert(Alert.AlertType.ERROR,"You can only select one candidate!").show();
        }
    }

    public void cb3OnAction(ActionEvent actionEvent) {
        if(cb1.isSelected() || cb2.isSelected()){
            cb3.setStyle("-fx-border-color:  #ef0d20; -fx-font-size: 16px;");
            new Alert(Alert.AlertType.ERROR,"You can only select one candidate!").show();
        }
    }

    public void btnVoteOnAction(ActionEvent actionEvent) {

        if(cb1.isSelected() || cb2.isSelected() || cb3.isSelected()){
            Integer candidate_id = null;

            if(cb1.isSelected())
                candidate_id = 1;
            else if (cb2.isSelected())
                candidate_id = 2;
            else if(cb3.isSelected())
                candidate_id = 3;

            try {
                boolean isSaved = voteeBO.saveVote(lblElection.getText(), candidate_id ,civilID);
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"Your vote has been recorded!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }else
            new Alert(Alert.AlertType.ERROR,"Please select a candidate!").show();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        OpenView.openView("voteLoginForm",Pane);
    }
}
