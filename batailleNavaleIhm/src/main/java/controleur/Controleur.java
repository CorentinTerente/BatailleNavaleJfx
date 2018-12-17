package controleur;

import application.Main;
import application.modele.GestionBatailleNavale;
import application.modele.Joueur;
import application.modele.Plateau;
import javafx.application.Platform;
import javafx.stage.Stage;
import views.Connexion;
import views.FinPartie;
import views.Grille;
import views.GrilleInactive;

public class Controleur {

    private Stage stagePrincipal;
    private Connexion connexion;
    private GestionBatailleNavale gestionBatailleNavale;
    private Grille grille;
    private FinPartie finPartie;
    private GrilleInactive grilleInactive;
    public Controleur(Stage stage) {

        this.gestionBatailleNavale = new GestionBatailleNavale();
        this.stagePrincipal = stage;
        this.connexion = connexion.creerEtAfficher(this, stage);

    }

    public void connexion(String pseudo1, String pseudo2) {
        gestionBatailleNavale.initialiserPartie(pseudo1,pseudo2);
        gestionBatailleNavale.disposerBateauxRandom();
        this.grille=grille.creerEtAfficher(this,stagePrincipal);
        Plateau grilleJoueur=gestionBatailleNavale.getPlateauJoueurCourant();
        Joueur joueurCourant=gestionBatailleNavale.getJoueurCourant();
        grille.afficherGrille(grilleJoueur,joueurCourant);
    }

    public void tirer(char ii, int jj) {
        Joueur joueurATirer=gestionBatailleNavale.getJoueurCourant();
        gestionBatailleNavale.tirer(ii,jj);
        this.grilleInactive=grilleInactive.creerEtAfficher(this,stagePrincipal);
        Plateau grilleJoueur=gestionBatailleNavale.getPlateau(joueurATirer.getId());
        if(gestionBatailleNavale.partieTerminee()){
            this.finPartie=finPartie.creerEtAfficher(this,stagePrincipal);
            Joueur gagant=gestionBatailleNavale.getGagnant();
            finPartie.afficherGagant(gagant);
        }
        else {
            grilleInactive.afficherGrille(grilleJoueur, joueurATirer);
        }
    }

    public void recommancer() {
       Joueur[] lesJoueurs= gestionBatailleNavale.getLesJoueurs();
       gestionBatailleNavale.initialiserPartie(lesJoueurs[0].getNom(),lesJoueurs[1].getNom());
       this.grille=grille.creerEtAfficher(this,stagePrincipal);
       Plateau grilleJoueur=gestionBatailleNavale.getPlateauJoueurCourant();
       Joueur joueurCourant=gestionBatailleNavale.getJoueurCourant();
       grille.afficherGrille(grilleJoueur,joueurCourant);
    }

    public void recommancerNvJoueurs() {
        this.connexion=connexion.creerEtAfficher(this,stagePrincipal);
    }

    public void quitter() {
        Platform.exit();
    }

    public void joueurSuivant() {

        this.grille=grille.creerEtAfficher(this,stagePrincipal);
        Plateau grilleJoueur=gestionBatailleNavale.getPlateauJoueurCourant();
        Joueur joueurCourant=gestionBatailleNavale.getJoueurCourant();
        grille.afficherGrille(grilleJoueur,joueurCourant);
    }
}
