package application.modele;

import application.modele.casesPlateau.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YohanBoichut on 08/12/15.
 */
public class Plateau {



    private Map<Character,ICase[]> plateau;



    private Bateau[] mesBateaux;
    private ArrayList<Integer> bateauxAPlacer;



    private HashMap<ICase,Bateau> localisationCasesBateaux;




    private static char[] alphabet = new char[]{'A','B','C','D','E','F','G','H','I','J'};
    private static int[] taillesBateaux = new int[]{5,4,3,3,2};


    private static int toInt(char c){
        return (int)c-(int)'A';
    }

    public Plateau() {
        this.plateau = new HashMap<Character,ICase[]>();
        for(int i = 0;i<alphabet.length;i++) {

            ICase[] colonne = new ICase[10];
            this.plateau.put(alphabet[i],colonne);

        }
        this.localisationCasesBateaux = new HashMap<>();
        this.mesBateaux = new Bateau[taillesBateaux.length];
        this.bateauxAPlacer = new ArrayList<>();
        for(int i = 0; i < taillesBateaux.length; i++)
            bateauxAPlacer.add(taillesBateaux[i]);
    }


    @Override
    public String toString() {

        String res = "|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |\n";
        for (int i=0;i<10;i++) {
            res += "| "+alphabet[i]+" |";
            for (int j=0;j<10;j++) {
                res += " "+this.getCase(alphabet[i],j)+" |";
            }
            res +="\n";
        }

        res += "\n";

        for (Bateau b : this.mesBateaux) {
            res += b + "\n";
        }
        return res;
    }

    public ICase getCase(char x, int y) {
        return this.plateau.get(x)[y];
    }

    public ArrayList<Integer> getBateauxAPlacer() {
        return bateauxAPlacer;
    }

    private void setCase(char x, int y, ICase c) {
        this.plateau.get(x)[y]=c;
    }


    private Bateau disposerBateauVertical(int taille) {
        boolean place = true;
        int i;
        int j;
        boolean nonOccupe;
        do {
            nonOccupe=true;
            i = (int)(Math.random()*10);
            j = (int)(Math.random()*(10-taille));
            int indice = j;
            while (indice<(j+taille)&& nonOccupe) {
                if (this.getCase(alphabet[i],indice)!= null) {
                    nonOccupe = false;
                }
                indice++;
            }
        } while(!nonOccupe);

        List<ICaseBateau> casesBateau = new ArrayList<>();
        Bateau monBateau = new Bateau(casesBateau,alphabet[i],j, IGestionBatailleNavalle.Orientation.VERTICAL);
        for (int x =j;x<(j+taille);x++) {
            ICaseBateau nouvelle = new CaseBateau(monBateau);
            casesBateau.add(nouvelle);
            this.setCase(alphabet[i],x,new CaseNonExploree(nouvelle));

        }
        return monBateau;
    }

    private Bateau disposerBateauHorizontal(int taille) {
        boolean place = true;
        int i;
        int j;
        boolean nonOccupe;
        do {
            nonOccupe=true;
            i = (int)(Math.random()*(10-taille));
            j = (int)(Math.random()*(10));
            int indice = j;
            while (indice<(i+taille)&& nonOccupe) {
                if (this.getCase(alphabet[indice],j)!= null) {
                    nonOccupe = false;
                }
                indice++;
            }
        } while(!nonOccupe);

        System.out.println("i:"+i);
        System.out.println("j:"+j);
        System.out.println("taille:"+taille);
        List<ICaseBateau> casesBateau = new ArrayList<>();
        Bateau monBateau = new Bateau(casesBateau,alphabet[i],j, IGestionBatailleNavalle.Orientation.VERTICAL);
        for (int x =i;x<(i+taille);x++) {
            ICaseBateau nouvelle = new CaseBateau(monBateau);
            casesBateau.add(nouvelle);
            this.setCase(alphabet[x],j,new CaseNonExploree(nouvelle));

        }
        return monBateau;
    }

    public void disposerBateau(char x, int y, IGestionBatailleNavalle.Orientation orientation, int tailleBateau)
            throws PasDeBateauDeCetteTailleAPlacerException, CoordonneesInvalidesException {

        if(!bateauxAPlacer.contains(tailleBateau))
            throw new PasDeBateauDeCetteTailleAPlacerException();

        if(x < 'A' || x > 'J' || y < 0 || y > 9)
            throw new CoordonneesInvalidesException();

        int i = x - 'A';

        switch (orientation) {

            case HORIZONTAL: {
                boolean nonOccupe = true;
                int indice = y;
                while (indice < (y + tailleBateau) && nonOccupe) {
                    if (indice >= plateau.get(alphabet[i]).length || this.getCase(alphabet[i], indice) != null) {
                        nonOccupe = false;
                    }
                    indice++;
                }

                if (!nonOccupe)
                    throw new CoordonneesInvalidesException();

                List<ICaseBateau> casesBateau = new ArrayList<>();

                Bateau monBateau = new Bateau(casesBateau, alphabet[i], y, IGestionBatailleNavalle.Orientation.HORIZONTAL);
                mesBateaux[mesBateaux.length-bateauxAPlacer.size()] = monBateau;

                for (int k = y; k < (y + tailleBateau); k++) {
                    ICaseBateau nouvelle = new CaseBateau(monBateau);
                    casesBateau.add(nouvelle);
                    this.setCase(alphabet[i], k, new CaseNonExploree(nouvelle));

                }

                bateauxAPlacer.remove((Integer)tailleBateau);

                break;
            }
            case VERTICAL:
            {
                boolean nonOccupe = true;
                int indice = i;
                while (indice < (i + tailleBateau) && nonOccupe) {
                    if (indice >= plateau.size() || this.getCase(alphabet[indice], y) != null) {
                        nonOccupe = false;
                    }
                    indice++;
                }

                if (!nonOccupe)
                    throw new CoordonneesInvalidesException();

                List<ICaseBateau> casesBateau = new ArrayList<>();

                Bateau monBateau = new Bateau(casesBateau, alphabet[i], y, IGestionBatailleNavalle.Orientation.VERTICAL);
                mesBateaux[mesBateaux.length-bateauxAPlacer.size()] = monBateau;

                for (int k = i; k < (i + tailleBateau); k++) {
                    ICaseBateau nouvelle = new CaseBateau(monBateau);
                    casesBateau.add(nouvelle);
                    this.setCase(alphabet[k], y, new CaseNonExploree(nouvelle));

                }

                bateauxAPlacer.remove((Integer)tailleBateau);

                break;
            }

        }

    }

    public void disposerBateaux() {

        for(int i = 0; i<taillesBateaux.length;i++) {
            int orienter = ((int) (Math.random() * 100)) % 2;
            if (orienter==0) {
                mesBateaux[i] = this.disposerBateauHorizontal(taillesBateaux[i]);
            }
            else {
                mesBateaux[i]=this.disposerBateauVertical(taillesBateaux[i]);
            }
        }

        //Initialiser la mer autour des bateaux
        initialiserMer();
    }


    public void initialiserMer() {
        for(int i = 0;i<alphabet.length;i++) {
            for (int j = 0; j < 10; j++) {
                if (this.getCase(alphabet[i],j)==null) {
                    this.setCase(alphabet[i], j, new CaseNonExploree(new CaseMer()));
                }
            }
        }
    }


    public void attaquer(char x, int y) {

        ICase avant = this.getCase(x, y);
        ICase nouvelle = avant.touche();
        if (avant != nouvelle) {
            this.setCase(x,y,nouvelle);
        }

    }



    public boolean tousCoules() {
        for (Bateau b : this.mesBateaux) {
            if (!b.bateauCoule()) {
                return false;
            }
        }
        return true;

    }

}
