package application.modele;

/**
 * Created by YohanBoichut on 08/12/15.
 */


import java.util.ArrayList;

/**
 * Le joueur avec l'ID 0 est en position 0 dans le tableau des joueurs. Ainsi
 * le joueur avec l'ID 1 est en position 1.
 */
public class GestionBatailleNavale implements IGestionBatailleNavalle {

    private Joueur[] joueurs;
    private int indiceJoueurCourant;
    private Plateau[] plateauxJoueurs;

    private Joueur gagnant = null;


    @Override
    public void initialiserPartie(String joueur1, String joueur2) {

        this.joueurs = new Joueur[2];
        this.joueurs[0] = new Joueur(0,joueur1);
        this.joueurs[1] = new Joueur(1,joueur2);
        this.plateauxJoueurs = new Plateau[2];
        this.plateauxJoueurs[0] = new Plateau();
        this.plateauxJoueurs[1] = new Plateau();
        this.indiceJoueurCourant = ((int)(Math.random()*100))%2;

    }

    @Override
    public void disposerBateauxRandom() {
        for(Plateau p : plateauxJoueurs)
            p.disposerBateaux();
    }

    @Override
    public void disposerBateau(char x, int y, Orientation orientation, int tailleBateau) throws CoordonneesInvalidesException, PasDeBateauDeCetteTailleAPlacerException {
        plateauxJoueurs[indiceJoueurCourant].disposerBateau(x, y, orientation, tailleBateau);

        if(plateauxJoueurs[indiceJoueurCourant].getBateauxAPlacer().isEmpty()) {

            plateauxJoueurs[indiceJoueurCourant].initialiserMer();
            this.indiceJoueurCourant = (this.indiceJoueurCourant+1)%2;

        }

    }

    @Override
    public ArrayList<Integer> bateauRestantPositionement() {
        return plateauxJoueurs[indiceJoueurCourant].getBateauxAPlacer();
    }

    @Override
    public void recommencerUneNouvellePartieAvecLesMemesJoueurs() {
        this.plateauxJoueurs[0] = new Plateau();
        this.plateauxJoueurs[1] = new Plateau();
        this.indiceJoueurCourant = ((int)(Math.random()*100))%2;
    }

    @Override
    public void tirer(char x, int y) {
        this.plateauxJoueurs[this.indiceJoueurCourant].attaquer(x,y);
        this.indiceJoueurCourant = (this.indiceJoueurCourant+1)%2;

    }

    @Override
    public Joueur[] getLesJoueurs() {
        return this.joueurs;
    }

    @Override
    public Plateau getPlateau(int id) {
        return this.plateauxJoueurs[id];
    }

    @Override
    public Joueur getJoueurCourant() {
        return this.joueurs[this.indiceJoueurCourant];
    }

    @Override
    public Plateau getPlateauJoueurCourant() {
        return this.plateauxJoueurs[this.indiceJoueurCourant];
    }

    @Override
    public Joueur getGagnant() {
        return this.gagnant;
    }

    @Override
    public boolean partieTerminee() {
        if (this.plateauxJoueurs[0].tousCoules()) {
            this.gagnant = this.joueurs[0];
            return true;
        }

        if (this.plateauxJoueurs[1].tousCoules()) {
            this.gagnant = this.joueurs[1];
            return true;
        }

        return false;
    }
}
