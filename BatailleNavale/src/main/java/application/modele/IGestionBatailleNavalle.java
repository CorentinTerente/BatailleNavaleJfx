package application.modele;

import java.util.ArrayList;

/**
 * Created by YohanBoichut on 08/12/15.
 */
public interface IGestionBatailleNavalle {

    /**
     * Enum des orientations.
     */
    enum Orientation {VERTICAL,HORIZONTAL}

    /**
     * Cette méthode crée les deux joueurs et initialise leurs tableaux d'attaque.
     * Le joueur devant commencer est tiré aléatoirement.
     * @param joueur1 Pseudo J1
     * @param joueur2 Pseudo J2
     */
    void initialiserPartie(String joueur1, String joueur2);

    /**
     * Disposer les bateaux aléatoirement.
     */
    void disposerBateauxRandom();

    /**
     * Positionne un bateau sur le plateau du joueur courant.
     * Quand tous les bateaux sont positionnés passe au joueur suivant
     * @param x ligne du haut du bateau
     * @param y colonne de la gauche du bateau
     * @param orientation vertical ou horizontal
     * @param tailleBateau taille du bateau à positionner
     *
     * @throws CoordonneesInvalidesException Bateau posé sur un bateau déjà posé ou sort du plateau.
     * @throws PasDeBateauDeCetteTailleAPlacerException Bateau inexistant.
     */
    void disposerBateau(char x, int y, Orientation orientation, int tailleBateau) throws CoordonneesInvalidesException, PasDeBateauDeCetteTailleAPlacerException;

    /**
     * Bateau restants à positionner du joueur courant
     * @return Liste des bateaux représentés par taille restant à position.
     */
    ArrayList<Integer> bateauRestantPositionement();

    /**
        Tout est dit dans le nom de la méthode
     */
    void recommencerUneNouvellePartieAvecLesMemesJoueurs();

    /**
     * Le joueur courant tire en coordonnées (x,y)
     * @param x Ligne
     * @param y Colonne
     * Une fois le coup effectué, le joueur courant change
     */
    void tirer(char x, int y);


    /**
     *
     * @return le tableau des joueurs présents dans la partie.
     */
    Joueur[] getLesJoueurs();


    /**
     * Retourne le plateau d'attaque du joueur avec l'identifiant id.
     * @param id Id joueur
     * @return plateau d'attaque du joueur id.
     */
    Plateau getPlateau(int id);


    /**
     *
     * @return le joueur courant (celui qui doit jouer le prochain coup).
     */
    Joueur getJoueurCourant();


    /**
     *
     * @return le plateau d'attaque du joueur courant
     */
    Plateau getPlateauJoueurCourant();

    /**
     *
     * @return le gagnant de la partie si celle ci est terminée et null sinon.
     */
    Joueur getGagnant();


    /**
     *
     * @return true si la partie est terminée et false sinon
     */
    boolean partieTerminee();

}
