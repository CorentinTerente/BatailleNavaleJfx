package application.modele.casesPlateau;

import application.modele.Bateau;

/**
 * Created by YohanBoichut on 08/12/15.
 */
public abstract class ICaseBateau implements ICase {

    protected Bateau bateauConcerne;

    public ICaseBateau(Bateau bateauConcerne) {
        this.bateauConcerne = bateauConcerne;
    }

    @Override
    public boolean estTouche() {
        return false;
    }
}
