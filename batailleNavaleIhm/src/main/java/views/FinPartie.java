package views;

import application.modele.Joueur;
import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class FinPartie {

    private Controleur monControleur;
    @FXML
    private VBox maVbox;
    @FXML
    private Button bouton1;
    @FXML
    private Button bouton2;
    @FXML
    private Button bouton3;

    public static FinPartie creerEtAfficher(Controleur controleur, Stage primaryStage) {
        URL location = FinPartie.class.getResource("/views/finPartie.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        FinPartie vue = fxmlLoader.getController();
        //Stage primaryStage = new Stage();
        primaryStage.setTitle("Bienvenue");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        vue.setMonControleur(controleur);
        return vue;
    }

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    public void afficherGagant(Joueur gagnant){
        Label monLabel= new Label();
        monLabel.setText("Le gagnant est: "+gagnant.getNom());
        monLabel.setAlignment(Pos.TOP_CENTER);
        maVbox.getChildren().add(monLabel);

    }

    public void recommancer(ActionEvent actionEvent) {
        monControleur.recommancer();
    }

    public void recommancerNouveauxJoueur(ActionEvent actionEvent) {
        monControleur.recommancerNvJoueurs();

    }

    public void quitter(ActionEvent actionEvent) {
        monControleur.quitter();
    }
}
