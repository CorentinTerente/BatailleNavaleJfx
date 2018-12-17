package application.modele.casesPlateau;

import application.modele.Bateau;

/**
 * Created by YohanBoichut on 08/12/15.
 */
public class CaseBateau extends ICaseBateau {
    public CaseBateau(Bateau b) {
        super(b);
    }

    @Override
    public ICase touche() {
        ICaseBateau nouvelle = new CaseBateauTouchee(this.bateauConcerne);
        this.bateauConcerne.changeCase(this,nouvelle);
        return nouvelle;
    }

    @Override
    public boolean estExploree() {
        return true;
    }


    @Override
    public String toString() {
        return "B";
    }
}
