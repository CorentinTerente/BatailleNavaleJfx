package application.modele;

/**
 * Created by YohanBoichut on 08/12/15.
 */
public class Joueur {


    private int id;
    private String nom;

    public Joueur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
