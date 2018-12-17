package views;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Connexion {

    private Controleur monControleur;
    @FXML
    private TextField pseudo1;
    @FXML
    private TextField pseudo2;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    public void validerConnexion(ActionEvent actionEvent) {
            if(this.pseudo1.getText() == null || this.pseudo1.getText().length() < 3){
                Alert alert = new Alert(Alert.AlertType.ERROR, "La saisie de votre pseudo doit comporter au moins 3 caracteres", ButtonType.OK);
                alert.show();
            }
            if(this.pseudo2.getText() == null || this.pseudo2.getText().length() < 3) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "La saisie de votre pseudo doit comporter au moins 3 caracteres", ButtonType.OK);
                alert.show();
            }else{
                monControleur.connexion(this.pseudo1.getText(), this.pseudo2.getText());
            }

    }

    public static Connexion creerEtAfficher(Controleur controleur, Stage primaryStage){
        URL location = Connexion.class.getResource("/views/connexion.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try{
            root = (Parent) fxmlLoader.load();

        }catch(IOException e){
            e.printStackTrace();
        }
        Connexion vue = fxmlLoader.getController();
        //Stage primaryStage = new Stage();
        primaryStage.setTitle("Bienvenue");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        vue.setMonControleur(controleur);
        return vue;
    }
}
