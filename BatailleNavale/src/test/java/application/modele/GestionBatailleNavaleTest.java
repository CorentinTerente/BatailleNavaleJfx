package application.modele;

import org.junit.Test;

public class GestionBatailleNavaleTest {

    /*
    On lance une partie avec les bateaux positionnés aléatoirement.
    Les joueurs tirent sur une position aléatoire jusqu'à ce qu'un deux gagne.
     */
    @Test
    public void testRandom() {

        // On initialise le modele
        IGestionBatailleNavalle model = new GestionBatailleNavale();

        // On initialise une partie
        model.initialiserPartie("Anais", "Vivien");

        // On positionne les bateaux au pif
        model.disposerBateauxRandom();

        // Affichages
        System.out.println(model.getJoueurCourant().getNom()+" commence.");
        System.out.println("Plateau joueur courant : ");
        System.out.println(model.getPlateauJoueurCourant());
        System.out.println("Plateau autre joueur : ");
        System.out.println(model.getPlateau((model.getJoueurCourant().getId()+1)%2));

        char x;
        int y;

        // Tant que la partie n'est pas finie
        while(!model.partieTerminee()) {

            // On choisi une position au pif
            x = (char)(Math.random()*10+'A');
            y = (int)(Math.random()*10);

            // On tire
            System.out.println(model.getJoueurCourant().getNom() + " tire en "+x+y);
            model.tirer(x, y);

            // On affiche
            System.out.println("Plateau joueur courant : ");
            System.out.println(model.getPlateauJoueurCourant());
            System.out.println("Plateau autre joueur : ");
            System.out.println(model.getPlateau((model.getJoueurCourant().getId() + 1) % 2));

        }

        // On affiche le gagnant
        System.out.println(model.getGagnant().getNom()+" a gagné !");

        // On relance une partie avec les même joueurs
        model.recommencerUneNouvellePartieAvecLesMemesJoueurs();

        // On positionne les bateaux au pif
        model.disposerBateauxRandom();

        // Affichages
        System.out.println(model.getJoueurCourant().getNom()+" commence.");
        System.out.println("Plateau joueur courant : ");
        System.out.println(model.getPlateauJoueurCourant());
        System.out.println("Plateau autre joueur : ");
        System.out.println(model.getPlateau((model.getJoueurCourant().getId()+1)%2));

    }

    @Test
    public void testPositionMain() {

        // On initialise le modele
        IGestionBatailleNavalle model = new GestionBatailleNavale();

        // On initialise une partie
        model.initialiserPartie("Anais", "Vivien");

        char x;
        int y;
        IGestionBatailleNavalle.Orientation orientation;
        int taille;

        // On positionne les bateaux
        while(!model.bateauRestantPositionement().isEmpty()) {

            // On choisi une position au pif
            x = (char)(Math.random()*10+'A');
            y = (int)(Math.random()*10);
            orientation = (Math.random() > 0.5d ? IGestionBatailleNavalle.Orientation.VERTICAL : IGestionBatailleNavalle.Orientation.HORIZONTAL);
            taille = model.bateauRestantPositionement().get(0);

            try {

                // On essaie de placer le bateau
                model.disposerBateau(x, y, orientation, taille);
                System.out.println("Bateau de taille "+taille+" placé en "+x+y+" à "+orientation+" pour "+model.getJoueurCourant().getNom()+".");

            } catch (CoordonneesInvalidesException e) {
                // Si ça loupe on rééssaie ^^
            } catch (PasDeBateauDeCetteTailleAPlacerException e) {
                // Si ça loupe on rééssaie ^^
            }

        }

        // Affichages
        System.out.println(model.getJoueurCourant().getNom()+" commence.");
        System.out.println("Plateau joueur courant : ");
        System.out.println(model.getPlateauJoueurCourant());
        System.out.println("Plateau autre joueur : ");
        System.out.println(model.getPlateau((model.getJoueurCourant().getId()+1)%2));

        // Tant que la partie n'est pas finie
        while(!model.partieTerminee()) {

            // On choisi une position au pif
            x = (char)(Math.random()*10+'A');
            y = (int)(Math.random()*10);

            // On tire
            System.out.println(model.getJoueurCourant().getNom() + " tire en "+x+y);
            model.tirer(x, y);

            // On affiche
            System.out.println("Plateau joueur courant : ");
            System.out.println(model.getPlateauJoueurCourant());
            System.out.println("Plateau autre joueur : ");
            System.out.println(model.getPlateau((model.getJoueurCourant().getId() + 1) % 2));

        }

        // On affiche le gagnant
        System.out.println(model.getGagnant().getNom()+" a gagné !");

        // On relance une partie avec les même joueurs
        model.recommencerUneNouvellePartieAvecLesMemesJoueurs();

        // On positionne les bateaux
        while(!model.bateauRestantPositionement().isEmpty()) {

            // On choisi une position au pif
            x = (char)(Math.random()*10+'A');
            y = (int)(Math.random()*10);
            orientation = (Math.random() > 0.5d ? IGestionBatailleNavalle.Orientation.VERTICAL : IGestionBatailleNavalle.Orientation.HORIZONTAL);
            taille = model.bateauRestantPositionement().get(0);

            try {

                // On essaie de placer le bateau
                model.disposerBateau(x, y, orientation, taille);
                System.out.println("Bateau de taille "+taille+" placé en "+x+y+" à "+orientation+" pour "+model.getJoueurCourant().getNom()+".");

            } catch (CoordonneesInvalidesException e) {
                // Si ça loupe on rééssaie ^^
            } catch (PasDeBateauDeCetteTailleAPlacerException e) {
                // Si ça loupe on rééssaie ^^
            }

        }

        // Affichages
        System.out.println(model.getJoueurCourant().getNom()+" commence.");
        System.out.println("Plateau joueur courant : ");
        System.out.println(model.getPlateauJoueurCourant());
        System.out.println("Plateau autre joueur : ");
        System.out.println(model.getPlateau((model.getJoueurCourant().getId()+1)%2));

    }

    @Test(expected = CoordonneesInvalidesException.class)
    public void testSortBot() throws Exception {

        // On initialise le modele
        IGestionBatailleNavalle model = new GestionBatailleNavale();

        // On initialise une partie
        model.initialiserPartie("Anais", "Vivien");

        // On essaie de placer le bateau
        model.disposerBateau('I', 0, IGestionBatailleNavalle.Orientation.VERTICAL, 5);

    }

    @Test(expected = CoordonneesInvalidesException.class)
    public void testSortTop() throws Exception {

        // On initialise le modele
        IGestionBatailleNavalle model = new GestionBatailleNavale();

        // On initialise une partie
        model.initialiserPartie("Anais", "Vivien");

        // On essaie de placer le bateau
        model.disposerBateau((char)('A'-1), 0, IGestionBatailleNavalle.Orientation.VERTICAL, 5);

    }

    @Test(expected = CoordonneesInvalidesException.class)
    public void testSortDroite() throws Exception {

        // On initialise le modele
        IGestionBatailleNavalle model = new GestionBatailleNavale();

        // On initialise une partie
        model.initialiserPartie("Anais", "Vivien");

        // On essaie de placer le bateau
        model.disposerBateau('A', 6, IGestionBatailleNavalle.Orientation.HORIZONTAL, 5);

    }

    @Test(expected = CoordonneesInvalidesException.class)
    public void testSortGauche() throws Exception {

        // On initialise le modele
        IGestionBatailleNavalle model = new GestionBatailleNavale();

        // On initialise une partie
        model.initialiserPartie("Anais", "Vivien");

        // On essaie de placer le bateau
        model.disposerBateau('A', -1, IGestionBatailleNavalle.Orientation.HORIZONTAL, 5);

    }

    @Test(expected = CoordonneesInvalidesException.class)
    public void testChevauche() throws Exception {

        // On initialise le modele
        IGestionBatailleNavalle model = new GestionBatailleNavale();

        // On initialise une partie
        model.initialiserPartie("Anais", "Vivien");

        // On essaie de placer le bateau
        model.disposerBateau('E', 4, IGestionBatailleNavalle.Orientation.HORIZONTAL, 2);

        // On essaie de placer le bateau
        model.disposerBateau('A', 5, IGestionBatailleNavalle.Orientation.VERTICAL, 5);

    }

    @Test(expected = PasDeBateauDeCetteTailleAPlacerException.class)
    public void testDejaMis() throws Exception {

        // On initialise le modele
        IGestionBatailleNavalle model = new GestionBatailleNavale();

        // On initialise une partie
        model.initialiserPartie("Anais", "Vivien");

        // On essaie de placer le bateau
        model.disposerBateau('E', 4, IGestionBatailleNavalle.Orientation.HORIZONTAL, 5);

        // On essaie de placer le bateau
        model.disposerBateau('A', 0, IGestionBatailleNavalle.Orientation.VERTICAL, 5);

    }

}
