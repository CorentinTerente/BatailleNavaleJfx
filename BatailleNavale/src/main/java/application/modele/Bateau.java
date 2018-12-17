package application.modele;

import application.modele.casesPlateau.ICaseBateau;

import java.util.List;

/**
 * Created by YohanBoichut on 08/12/15.
 */
public class Bateau {

    private List<ICaseBateau> bateau;
    private char superieurGaucheLettre;
    private int superieurGaucheChiffre;

    private IGestionBatailleNavalle.Orientation orientation;


    public Bateau(List<ICaseBateau> bateau, char superieurGaucheLettre, int superieurGaucheChiffre, IGestionBatailleNavalle.Orientation orientation) {
        this.bateau = bateau;
        this.superieurGaucheLettre = superieurGaucheLettre;
        this.superieurGaucheChiffre = superieurGaucheChiffre;
        this.orientation = orientation;
    }

    public int longeurBateau() {
        return this.bateau.size();
    }


    public List<ICaseBateau> getBateau() {
        return bateau;
    }

    public char getSuperieurGaucheLettre() {
        return superieurGaucheLettre;
    }

    public int getSuperieurGaucheChiffre() {
        return superieurGaucheChiffre;
    }

    public IGestionBatailleNavalle.Orientation getOrientation() {
        return orientation;
    }


    public boolean bateauCoule() {
        for (ICaseBateau b: this.bateau) {
            if (!b.estTouche()) {
                return false;
            }
        }
        return true;
    }


    @Override
    public String toString(){
        String res ="";

        for(ICaseBateau b :this.bateau) {
            res = res + " " + b;
        }
        return res;
    }

    public void changeCase(ICaseBateau maCase, ICaseBateau maCaseTouchee) {

        if (this.bateau.contains(maCase)) {
            int indice = this.bateau.indexOf(maCase);
            this.bateau.remove(indice);
            this.bateau.add(indice,maCaseTouchee);
        }
    }


}
