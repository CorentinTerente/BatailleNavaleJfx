package application.modele.casesPlateau;

import application.modele.Bateau;

/**
 * Created by YohanBoichut on 08/12/15.
 */
public class CaseBateauTouchee extends ICaseBateau {
    public CaseBateauTouchee(Bateau bateauConcerne) {
        super(bateauConcerne);
    }

    @Override
    public ICase touche() {
        return this;
    }

    @Override
    public boolean estExploree() {
        return true;
    }

    @Override
    public boolean estTouche() {
        return true;
    }

    @Override
    public String toString() {
        return "T";
    }
}
