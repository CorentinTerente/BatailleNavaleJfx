package application.modele.casesPlateau;

/**
 * Created by YohanBoichut on 08/12/15.
 */
public class CaseMer implements ICase {
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
        return false;
    }

    @Override
    public String toString() {
        return "M";
    }
}
