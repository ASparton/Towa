package towa;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Tests unitaires de la classe CoordonneesCasesAdjacentes
 * 
 * @author Alexandre Sparton
 */
public class CoordonneesCasesAdjacentesTest {
    
    @Test
    public void lancerTests() {
        testObtenirCoordonneesAdjacentes();
    }
    
    void testObtenirCoordonneesAdjacentes() {
        Coordonnees coord;
        Coordonnees[] coordsAdjacentes = new Coordonnees[8];
        int nbCoord;
        CoordonneesCasesAdjacentes coordsTest;
        
        //on teste une coordonnée loin des bords du plateau
        coord = Coordonnees.depuisCars('i', 'C');
        coordsAdjacentes[0] = new Coordonnees(coord.ligne - 1, coord.colonne - 1);
        coordsAdjacentes[1] = new Coordonnees(coord.ligne - 1, coord.colonne);
        coordsAdjacentes[2] = new Coordonnees(coord.ligne - 1, coord.colonne + 1);
        coordsAdjacentes[3] = new Coordonnees(coord.ligne, coord.colonne - 1);
        coordsAdjacentes[4] = new Coordonnees(coord.ligne, coord.colonne + 1);
        coordsAdjacentes[5] = new Coordonnees(coord.ligne + 1, coord.colonne - 1);
        coordsAdjacentes[6] = new Coordonnees(coord.ligne + 1, coord.colonne);
        coordsAdjacentes[7] = new Coordonnees(coord.ligne + 1, coord.colonne + 1);
        nbCoord = 8;
        coordsTest = new CoordonneesCasesAdjacentes(coord);
        for (int i = 0; i < nbCoord; i++)
            assertEquals(coordsTest.coordonnees[i], coordsAdjacentes[i]);
        
        //on teste une coordonnée sur le bord gauche
        coord = Coordonnees.depuisCars('c', 'A');
        nbCoord = 5;
        coordsAdjacentes[0] = new Coordonnees(coord.ligne - 1, coord.colonne);
        coordsAdjacentes[1] = new Coordonnees(coord.ligne - 1, coord.colonne + 1);
        coordsAdjacentes[2] = new Coordonnees(coord.ligne, coord.colonne + 1);
        coordsAdjacentes[3] = new Coordonnees(coord.ligne + 1, coord.colonne);
        coordsAdjacentes[4] = new Coordonnees(coord.ligne + 1, coord.colonne + 1);
        coordsTest = new CoordonneesCasesAdjacentes(coord);
        for (int i = 0; i < nbCoord; i++)
            assertEquals(coordsTest.coordonnees[i], coordsAdjacentes[i]);
        
        //on teste une coordonnée sur le bord droit
        coord = Coordonnees.depuisCars('d', 'P');
        nbCoord = 5;
        coordsAdjacentes[0] = new Coordonnees(coord.ligne - 1, coord.colonne - 1);
        coordsAdjacentes[1] = new Coordonnees(coord.ligne - 1, coord.colonne);
        coordsAdjacentes[2] = new Coordonnees(coord.ligne, coord.colonne - 1);
        coordsAdjacentes[3] = new Coordonnees(coord.ligne + 1, coord.colonne - 1);
        coordsAdjacentes[4] = new Coordonnees(coord.ligne + 1, coord.colonne);
        coordsTest = new CoordonneesCasesAdjacentes(coord);
        for (int i = 0; i < nbCoord; i++)
            assertEquals(coordsTest.coordonnees[i], coordsAdjacentes[i]);
        
        //on teste une coordonnée sur le bord haut
        coord = Coordonnees.depuisCars('a', 'F');
        coordsAdjacentes[0] = new Coordonnees(coord.ligne, coord.colonne - 1);
        coordsAdjacentes[1] = new Coordonnees(coord.ligne, coord.colonne + 1);
        coordsAdjacentes[2] = new Coordonnees(coord.ligne + 1, coord.colonne - 1);
        coordsAdjacentes[3] = new Coordonnees(coord.ligne + 1, coord.colonne);
        coordsAdjacentes[4] = new Coordonnees(coord.ligne + 1, coord.colonne + 1);
        nbCoord = 5;
        coordsTest = new CoordonneesCasesAdjacentes(coord);
        for (int i = 0; i < nbCoord; i++)
            assertEquals(coordsTest.coordonnees[i], coordsAdjacentes[i]);
        
        //on teste une coordonnée sur le bord bas
        coord = Coordonnees.depuisCars('p', 'K');
        coordsAdjacentes[0] = new Coordonnees(coord.ligne - 1, coord.colonne - 1);
        coordsAdjacentes[1] = new Coordonnees(coord.ligne - 1, coord.colonne);
        coordsAdjacentes[2] = new Coordonnees(coord.ligne - 1, coord.colonne + 1);
        coordsAdjacentes[3] = new Coordonnees(coord.ligne, coord.colonne - 1);
        coordsAdjacentes[4] = new Coordonnees(coord.ligne, coord.colonne + 1);
        nbCoord = 5;
        coordsTest = new CoordonneesCasesAdjacentes(coord);
        for (int i = 0; i < nbCoord; i++)
            assertEquals(coordsTest.coordonnees[i], coordsAdjacentes[i]);
        
        //on teste la coordonnée du coin haut gauche
        coord = Coordonnees.depuisCars('a', 'A');
        coordsAdjacentes[0] = new Coordonnees(coord.ligne, coord.colonne + 1);
        coordsAdjacentes[1] = new Coordonnees(coord.ligne + 1, coord.colonne);
        coordsAdjacentes[2] = new Coordonnees(coord.ligne + 1, coord.colonne + 1);
        nbCoord = 3;
        coordsTest = new CoordonneesCasesAdjacentes(coord);
        for (int i = 0; i < nbCoord; i++)
            assertEquals(coordsTest.coordonnees[i], coordsAdjacentes[i]);
        
        //on teste la coordonnée du coin haut droit
        coord = Coordonnees.depuisCars('a', 'P');
        coordsAdjacentes[0] = new Coordonnees(coord.ligne, coord.colonne - 1);
        coordsAdjacentes[1] = new Coordonnees(coord.ligne + 1, coord.colonne - 1);
        coordsAdjacentes[2] = new Coordonnees(coord.ligne + 1, coord.colonne);
        nbCoord = 3;
        coordsTest = new CoordonneesCasesAdjacentes(coord);
        for (int i = 0; i < nbCoord; i++)
            assertEquals(coordsTest.coordonnees[i], coordsAdjacentes[i]);
        
        //on teste la coordonnée du coin bas gauche
        coord = Coordonnees.depuisCars('p', 'A');
        coordsAdjacentes[0] = new Coordonnees(coord.ligne - 1, coord.colonne);
        coordsAdjacentes[1] = new Coordonnees(coord.ligne - 1, coord.colonne + 1);
        coordsAdjacentes[2] = new Coordonnees(coord.ligne, coord.colonne + 1);
        nbCoord = 3;
        coordsTest = new CoordonneesCasesAdjacentes(coord);
        for (int i = 0; i < nbCoord; i++)
            assertEquals(coordsTest.coordonnees[i], coordsAdjacentes[i]);
        //on teste la coordonnée du coin bas droit
        coord = Coordonnees.depuisCars('p', 'P');
        coordsAdjacentes[0] = new Coordonnees(coord.ligne - 1, coord.colonne - 1);
        coordsAdjacentes[1] = new Coordonnees(coord.ligne - 1, coord.colonne);
        coordsAdjacentes[2] = new Coordonnees(coord.ligne, coord.colonne - 1);
        nbCoord = 3;
        coordsTest = new CoordonneesCasesAdjacentes(coord);
        for (int i = 0; i < nbCoord; i++)
            assertEquals(coordsTest.coordonnees[i], coordsAdjacentes[i]);
    }
}
