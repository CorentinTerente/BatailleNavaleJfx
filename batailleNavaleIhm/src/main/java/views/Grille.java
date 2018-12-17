package views;

import application.modele.Joueur;
import application.modele.Plateau;
import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Grille {
    private Controleur monControleur;
    @FXML
    private VBox maVbox;


    public static Grille creerEtAfficher(Controleur controleur, Stage primaryStage) {
        URL location = Grille.class.getResource("/views/grille.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Grille vue = fxmlLoader.getController();
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

    public void afficherGrille(Plateau grille, Joueur joueur) {
        GridPane gridPane = new GridPane();
        Label nomJoueur= new Label();
        nomJoueur.setText("Joueur actuel: "+joueur.getNom());
        char alpha[]=new char[]{'A','B','C','D','E','F','G','H','I','J'};
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Label colonne=new Label();
                Label ligne=new Label();
                colonne.setText(String.valueOf(alpha[i]));
                colonne.setAlignment(Pos.TOP_CENTER);
                ligne.setText(String.valueOf(j));
                ligne.setAlignment(Pos.CENTER_LEFT);
                gridPane.add(colonne,i,0);
                gridPane.add(ligne,0,j);
                Button monBouton = new Button();
                if (!grille.getCase(alpha[i], j).estExploree() && !grille.getCase(alpha[i], j).estTouche()) {
                    monBouton.setText(" ");
                    final int ii = i;
                    final int jj = j;
                    monBouton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            monControleur.tirer(alpha[ii], jj);
                        }
                    });

                }
                if (grille.getCase(alpha[i], j).estTouche()) {
                    monBouton.setText("toucher");
                    monBouton.setDisable(true);
                } else if (grille.getCase(alpha[i], j).estExploree()) {
                    monBouton.setText("rien");
                    monBouton.setDisable(true);
                }
                gridPane.add(monBouton, i, j);
            }
            gridPane.setAlignment(Pos.CENTER);
            this.maVbox.getChildren().setAll(gridPane);
            Button joueurSuivant=new Button();
            joueurSuivant.setText("Joueur Suivant");
            joueurSuivant.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    monControleur.joueurSuivant();
                }
            });
            this.maVbox.getChildren().add(joueurSuivant);
            this.maVbox.getChildren().add(nomJoueur);
        }
    }
}
