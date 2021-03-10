package towa;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Tests unitaires de CoordoneesCasesAPortee
 * 
 * @author spart
 */
public class CoordonneesCasesAPorteeTest {
    
    /*
     * Une instance de l'objet dont on va tester la validité des méthodes
     */
    CoordonneesCasesAPortee coordsATester;
    
    /*
     * Le plateau qui va nous permettre de faire les tests
     */
    static final Case[][] plateau = Utils.plateauDepuisTexte(JoueurTowaTest.PLATEAU_NIVEAU3_4_5_6_7_8);
    
    /*
     * Une coordonnée qui n'est pas dans la plateau qui va nous permettre de faire les tests
     */
    static final Coordonnees coordVide = new Coordonnees(-1, -1);
    
    @Test
    public void lancerTests() {
        testObtenirCoordonneesCasesLigne();
        testObtenirCoordonneesCasesLigneDepuisVers();
        testObtenirCoordonneesCasesColonne();
        testObtenirCoordonneesCasesColonneDepuisVers();
        testObtenirCoordonneesPremiereTour();
        testObtenirCoordonneesCasesColonneDepuisVers();
        testObtenirCoordonneesAPortee();
    }
    
    void testObtenirCoordonneesPremiereTour() {
        Coordonnees coord;
        
        //Test pour une ligne avec plusieurs tours
        Coordonnees[] ligneTours = {
            Coordonnees.depuisCars('c', 'I'),
            Coordonnees.depuisCars('c', 'J'),
            Coordonnees.depuisCars('c', 'K'),
            Coordonnees.depuisCars('c', 'L')};
        coord = Coordonnees.depuisCars('c', 'K');
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        assertEquals(this.coordsATester.obtenirCoordonneesPremiereTour(plateau, ligneTours), coord);
        
        //Test pour une ligne avec une seule tour
        Coordonnees[] ligneTour = {
            Coordonnees.depuisCars('a', 'E'),
            Coordonnees.depuisCars('a', 'F'),
            Coordonnees.depuisCars('a', 'G'),
            Coordonnees.depuisCars('a', 'H')};
        coord = Coordonnees.depuisCars('a', 'G');
         this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        assertEquals(this.coordsATester.obtenirCoordonneesPremiereTour(plateau, ligneTour), coord);
        
        //Test pour une ligne vide
        Coordonnees[] ligneVide = {
            Coordonnees.depuisCars('a', 'A'),
            Coordonnees.depuisCars('a', 'B'),
            Coordonnees.depuisCars('a', 'C'),
            Coordonnees.depuisCars('a', 'D')};
         this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        assertEquals(this.coordsATester.obtenirCoordonneesPremiereTour(plateau, ligneVide), coordVide);
        
        //Test pour une colonne avec plusieurs tours
        Coordonnees[] colonneTours = {
            Coordonnees.depuisCars('b', 'A'),
            Coordonnees.depuisCars('c', 'A'),
            Coordonnees.depuisCars('d', 'A'),
            Coordonnees.depuisCars('e', 'A')};
        coord = Coordonnees.depuisCars('b', 'A');
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        assertEquals(this.coordsATester.obtenirCoordonneesPremiereTour(plateau, colonneTours), coord);
        
        //Test pour une colonne avec une seule tour
        Coordonnees[] colonneTour = {
            Coordonnees.depuisCars('b', 'C'),
            Coordonnees.depuisCars('c', 'C'),
            Coordonnees.depuisCars('d', 'C'),
            Coordonnees.depuisCars('e', 'C')};
        coord = Coordonnees.depuisCars('c', 'C');
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        assertEquals(this.coordsATester.obtenirCoordonneesPremiereTour(plateau, colonneTour), coord);
        
        //Test pour une colonne vide
        Coordonnees[] colonneVide = {
            Coordonnees.depuisCars('a', 'O'),
            Coordonnees.depuisCars('b', 'O'),
            Coordonnees.depuisCars('c', 'O'),
            Coordonnees.depuisCars('d', 'O')};
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        assertEquals(this.coordsATester.obtenirCoordonneesPremiereTour(plateau, colonneVide), coordVide);
    }
    
    void testObtenirCoordonneesCasesLigne() {
        Coordonnees coord;
        Coordonnees[] coordsLigne = new Coordonnees[Coordonnees.NB_COLONNES - 1];
        int nbCoord = 0;
        //test à partir du coin haut gauche
        coord = Coordonnees.depuisCars('a', 'A');
        for (int i = 0; i < Coordonnees.NB_COLONNES; i++) {
            if (i != 0) {
                coordsLigne[nbCoord] = new Coordonnees(0, i);
                nbCoord++;
            }
        }
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        Assert.assertArrayEquals(this.coordsATester.obtenirCoordonneesCasesLigne(), coordsLigne);
        
        //test à partir du coin haut droit
        coord = Coordonnees.depuisCars('a', 'P');
        nbCoord = 0;
        for (int i = 0; i < Coordonnees.NB_COLONNES; i++) {
            if (i != 15) {
                coordsLigne[nbCoord] = new Coordonnees(0, i);
                nbCoord++;
            }
        }
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        Assert.assertArrayEquals(this.coordsATester.obtenirCoordonneesCasesLigne(), coordsLigne);
        
        //test à partir du coin bas gauche
        nbCoord = 0;
        coord = Coordonnees.depuisCars('p', 'A');
        for (int i = 0; i < Coordonnees.NB_COLONNES; i++) {
            if (i != 0) {
                coordsLigne[nbCoord] = new Coordonnees(15, i);
                nbCoord++;
            }
        }
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        Assert.assertArrayEquals(this.coordsATester.obtenirCoordonneesCasesLigne(), coordsLigne);
        
        //test à partir du coin bas droit
        coord = Coordonnees.depuisCars('p', 'P');
        nbCoord = 0;
        for (int i = 0; i < Coordonnees.NB_COLONNES; i++) {
            if (i != 15) {
                coordsLigne[nbCoord] = new Coordonnees(15, i);
                nbCoord++;
            }
        }
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        Assert.assertArrayEquals(this.coordsATester.obtenirCoordonneesCasesLigne(), coordsLigne);
    }
    
    void testObtenirCoordonneesCasesLigneDepuisVers() {
        Coordonnees coordRef;
        int colonneDepart, colonneArrive;
        
        //test de la gauche vers la droite
        Coordonnees[] coordsLigne = new Coordonnees[2];
        coordRef = Coordonnees.depuisCars('a', 'A');
        colonneDepart = coordRef.colonne;
        colonneArrive = 2;
        coordsLigne[0] = Coordonnees.depuisCars('a', 'B');
        coordsLigne[1] = Coordonnees.depuisCars('a', 'C');
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coordRef);
        Assert.assertArrayEquals(coordsLigne,
                this.coordsATester.obtenirCoordonneesCasesLigneDepuisVers(colonneDepart, colonneArrive));
        
        //test de la droite vers la gauche
        coordRef = Coordonnees.depuisCars('c', 'K');
        colonneDepart = coordRef.colonne + 2;
        colonneArrive = coordRef.colonne;
        coordsLigne[0] = Coordonnees.depuisCars('c', 'M');
        coordsLigne[1] = Coordonnees.depuisCars('c', 'L');
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coordRef);
         Assert.assertArrayEquals(coordsLigne,
                this.coordsATester.obtenirCoordonneesCasesLigneDepuisVers(colonneDepart, colonneArrive));
        
        //test sur la ligne entière de la gauche vers la droite
        coordRef = Coordonnees.depuisCars('p', 'C');
        Coordonnees[] coordsLigne2 = new Coordonnees[15];
        coordsLigne2[0] = Coordonnees.depuisCars('p', 'P');
        coordsLigne2[1] = Coordonnees.depuisCars('p', 'O');
        coordsLigne2[2] = Coordonnees.depuisCars('p', 'N');
        coordsLigne2[3] = Coordonnees.depuisCars('p', 'M');
        coordsLigne2[4] = Coordonnees.depuisCars('p', 'L');
        coordsLigne2[5] = Coordonnees.depuisCars('p', 'K');
        coordsLigne2[6] = Coordonnees.depuisCars('p', 'J');
        coordsLigne2[7] = Coordonnees.depuisCars('p', 'I');
        coordsLigne2[8] = Coordonnees.depuisCars('p', 'H');
        coordsLigne2[9] = Coordonnees.depuisCars('p', 'G');
        coordsLigne2[10] = Coordonnees.depuisCars('p', 'F');
        coordsLigne2[11] = Coordonnees.depuisCars('p', 'E');
        coordsLigne2[12] = Coordonnees.depuisCars('p', 'D');
        coordsLigne2[13] = Coordonnees.depuisCars('p', 'B');
        coordsLigne2[14] = Coordonnees.depuisCars('p', 'A');
        colonneDepart = 15;
        colonneArrive = 0;
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coordRef);
        Assert.assertArrayEquals(coordsLigne2,
            this.coordsATester.obtenirCoordonneesCasesLigneDepuisVers(colonneDepart, colonneArrive));
    }
    
    void testObtenirCoordonneesCasesColonne() {
        Coordonnees coord;
        Coordonnees[] coordsColonne = new Coordonnees[Coordonnees.NB_LIGNES - 1];
        int nbCoord = 0;
        //test à partir du coin haut gauche
        coord = Coordonnees.depuisCars('a', 'A');
        for (int i = 0; i < Coordonnees.NB_LIGNES; i++) {
            if (i != 0) {
                coordsColonne[nbCoord] = new Coordonnees(i, 0);
                nbCoord++;
            }
        }
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        Assert.assertArrayEquals(this.coordsATester.obtenirCoordonneesCasesColonne(), coordsColonne);
        
        //test à partir du coin haut droit
        coord = Coordonnees.depuisCars('a', 'P');
        nbCoord = 0;
        for (int i = 0; i < Coordonnees.NB_LIGNES; i++) {
            if (i != 0) {
                coordsColonne[nbCoord] = new Coordonnees(i, 15);
                nbCoord++;
            }
        }
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        Assert.assertArrayEquals(this.coordsATester.obtenirCoordonneesCasesColonne(), coordsColonne);
        
        //test à partir du coin bas gauche
        nbCoord = 0;
        coord = Coordonnees.depuisCars('p', 'A');
        for (int i = 0; i < Coordonnees.NB_LIGNES; i++) {
            if (i != 15) {
                coordsColonne[nbCoord] = new Coordonnees(i, 0);
                nbCoord++;
            }
        }
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        Assert.assertArrayEquals(this.coordsATester.obtenirCoordonneesCasesColonne(), coordsColonne);
        
        //test à partir du coin bas droit
        coord = Coordonnees.depuisCars('p', 'P');
        nbCoord = 0;
        for (int i = 0; i < Coordonnees.NB_COLONNES; i++) {
            if (i != 15) {
                coordsColonne[nbCoord] = new Coordonnees(i, 15);
                nbCoord++;
            }
        }
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coord);
        Assert.assertArrayEquals(this.coordsATester.obtenirCoordonneesCasesColonne(), coordsColonne);
    }
    
    void testObtenirCoordonneesCasesColonneDepuisVers() {
        Coordonnees coordRef;
        int ligneDepart, ligneArrive;
        
        //test du haut vers le bas
        Coordonnees[] coordsColonne = new Coordonnees[2];
        coordRef = Coordonnees.depuisCars('d', 'M');
        ligneDepart = coordRef.ligne;
        ligneArrive = 5;
        coordsColonne[0] = Coordonnees.depuisCars('e', 'M');
        coordsColonne[1] = Coordonnees.depuisCars('f', 'M');
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coordRef);
        Assert.assertArrayEquals(coordsColonne,
                this.coordsATester.obtenirCoordonneesCasesColonneDepuisVers(ligneDepart, ligneArrive));
        
        //test du bas vers le haut
        coordRef = Coordonnees.depuisCars('h', 'I');
        ligneDepart = coordRef.ligne + 2;
        ligneArrive = coordRef.ligne;
        coordsColonne[0] = Coordonnees.depuisCars('j', 'I');
        coordsColonne[1] = Coordonnees.depuisCars('i', 'I');
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coordRef);
        Assert.assertArrayEquals(coordsColonne,
                this.coordsATester.obtenirCoordonneesCasesColonneDepuisVers(ligneDepart, ligneArrive));
        
        //test sur la colonne entière du haut vers le bas
        coordRef = Coordonnees.depuisCars('m', 'P');
        Coordonnees[] coordsColonne2 = new Coordonnees[15];
        coordsColonne2[0] = Coordonnees.depuisCars('a', 'P');
        coordsColonne2[1] = Coordonnees.depuisCars('b', 'P');
        coordsColonne2[2] = Coordonnees.depuisCars('c', 'P');
        coordsColonne2[3] = Coordonnees.depuisCars('d', 'P');
        coordsColonne2[4] = Coordonnees.depuisCars('e', 'P');
        coordsColonne2[5] = Coordonnees.depuisCars('f', 'P');
        coordsColonne2[6] = Coordonnees.depuisCars('g', 'P');
        coordsColonne2[7] = Coordonnees.depuisCars('h', 'P');
        coordsColonne2[8] = Coordonnees.depuisCars('i', 'P');
        coordsColonne2[9] = Coordonnees.depuisCars('j', 'P');
        coordsColonne2[10] = Coordonnees.depuisCars('k', 'P');
        coordsColonne2[11] = Coordonnees.depuisCars('l', 'P');
        coordsColonne2[12] = Coordonnees.depuisCars('n', 'P');
        coordsColonne2[13] = Coordonnees.depuisCars('o', 'P');
        coordsColonne2[14] = Coordonnees.depuisCars('p', 'P');
        ligneDepart = 0;
        ligneArrive = 15;
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coordRef);
        Assert.assertArrayEquals(coordsColonne2,
                this.coordsATester.obtenirCoordonneesCasesColonneDepuisVers(ligneDepart, ligneArrive));
    }
    
    void testObtenirCoordonneesAPortee() {
        Coordonnees coordRef;
        Coordonnees[] coordsAPortee = new Coordonnees[12];
        int nbCoords;
        //Test avec uniquement des cases adjacentes
        coordRef = Coordonnees.depuisCars('m', 'F');
        nbCoords = 8;
        coordsAPortee[0] = Coordonnees.depuisCars('l', 'E');
        coordsAPortee[1] = Coordonnees.depuisCars('l', 'F');
        coordsAPortee[2] = Coordonnees.depuisCars('l', 'G');
        coordsAPortee[3] = Coordonnees.depuisCars('m', 'E');
        coordsAPortee[4] = Coordonnees.depuisCars('m', 'G');
        coordsAPortee[5] = Coordonnees.depuisCars('n', 'E');
        coordsAPortee[6] = Coordonnees.depuisCars('n', 'F');
        coordsAPortee[7] = Coordonnees.depuisCars('n', 'G');
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coordRef);
        for (int i = 0; i < nbCoords; i++)
            assertEquals(coordsAPortee[i], this.coordsATester.obtenirCoordonneesAPortee(plateau, coordRef)[i]);
        
        //Test dans le coin haut droit
        coordRef = Coordonnees.depuisCars('a', 'P');
        nbCoords = 4;
        coordsAPortee[0] = Coordonnees.depuisCars('a', 'O');
        coordsAPortee[1] = Coordonnees.depuisCars('b', 'O');
        coordsAPortee[2] = Coordonnees.depuisCars('b', 'P');
        coordsAPortee[3] = Coordonnees.depuisCars('a', 'G');
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coordRef);
        for (int i = 0; i < nbCoords; i++)
            assertEquals(coordsAPortee[i], this.coordsATester.obtenirCoordonneesAPortee(plateau, coordRef)[i]);
        
        //Test avec cases adjacentes + les 4 premières cases avec tours de 4 directtions
        coordRef = Coordonnees.depuisCars('k', 'J');
        nbCoords = 12;
        coordsAPortee[0] = Coordonnees.depuisCars('j', 'I');
        coordsAPortee[1] = Coordonnees.depuisCars('j', 'J');
        coordsAPortee[2] = Coordonnees.depuisCars('j', 'K');
        coordsAPortee[3] = Coordonnees.depuisCars('k', 'I');
        coordsAPortee[4] = Coordonnees.depuisCars('k', 'K');
        coordsAPortee[5] = Coordonnees.depuisCars('l', 'I');
        coordsAPortee[6] = Coordonnees.depuisCars('l', 'J');
        coordsAPortee[7] = Coordonnees.depuisCars('l', 'K');
        coordsAPortee[8] = Coordonnees.depuisCars('k', 'E');
        coordsAPortee[9] = Coordonnees.depuisCars('k', 'O');
        coordsAPortee[10] = Coordonnees.depuisCars('m', 'J');
        coordsAPortee[11] = Coordonnees.depuisCars('g', 'J');
        this.coordsATester = new CoordonneesCasesAPortee(plateau, coordRef);
        for (int i = 0; i < nbCoords; i++)
            assertEquals(coordsAPortee[i], this.coordsATester.obtenirCoordonneesAPortee(plateau, coordRef)[i]);
    }
}
