package application.modele.casesPlateau;

/**
 * Created by YohanBoichut on 08/12/15.
 */
public class CaseNonExploree implements ICase {

    private ICase caseCachee;


    public CaseNonExploree(ICase caseCachee) {
        this.caseCachee = caseCachee;
    }

    @Override
    public ICase touche() {
        return this.caseCachee.touche();
    }

    @Override
    public boolean estExploree() {
        return false;
    }

    @Override
    public boolean estTouche() {
        return false;
    }

    public ICase getCaseCachee(){
        return this.caseCachee;
    }

    @Override
    public String toString() {
        return " ";
    }
}
