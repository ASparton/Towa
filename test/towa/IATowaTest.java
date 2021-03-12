package towa;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de test de la classe IATowa
 * @author Arthur Pascal
 * @author Alexandre Sparton
 */
public class IATowaTest {
    
    @Test
    public void lancerTests() {
        testObtenirCoordCoinPlusProche();
        testPlateauContientTourAllieeHauteur4();
        testObtenirCoordAdjPlusProche();
        testFusionInteressante();
    }
    
    void testObtenirCoordCoinPlusProche() {
        final Coordonnees coordCoinNordOuest = new Coordonnees(0, 0);
        final Coordonnees coordCoinSudOuest = new Coordonnees(15, 0);
        final Coordonnees coordCoinNordEst = new Coordonnees(0, 15);
        final Coordonnees coordCoinSudEst = new Coordonnees(15, 15);
        Coordonnees coordTest;
        IATowa iaTest = new IATowa("", 0, true);
        
        //Test pour une coordonnée proche du coin nord ouest
        coordTest = new Coordonnees(2, 2);
        Assert.assertEquals(coordCoinNordOuest, iaTest.obtenirCoordCoinPlusProche(coordTest));
        
        //Test pour une coordonnée proche du coin nord est
        coordTest = new Coordonnees(5, 10);
        Assert.assertEquals(coordCoinNordEst, iaTest.obtenirCoordCoinPlusProche(coordTest));
        
        //Test pour une coordonnée proche du coin sud ouest
        coordTest = new Coordonnees(12, 4);
        Assert.assertEquals(coordCoinSudOuest, iaTest.obtenirCoordCoinPlusProche(coordTest));
        
        //Test pour une coordonnée proche du coin sud est
        coordTest = new Coordonnees(10, 10);
        Assert.assertEquals(coordCoinSudEst, iaTest.obtenirCoordCoinPlusProche(coordTest));
    }
    
    @Test
    public void testPlateauContientTourAllieeHauteur4() {
        IATowa iaTest = new IATowa("", 0, false);
        Case[][] plateau = Utils.plateauDepuisTexte(JoueurTowaTest.PLATEAU_NIVEAU1);
        Assert.assertTrue(iaTest.plateauContientTourAllieeHauteur4(plateau, false));
    }
    
    @Test
    public void testTourEnnemieAdjHauteur3() {
        IATowa iaTest = new IATowa("", 0, false);
        Case[][] plateau = Utils.plateauDepuisTexte(JoueurTowaTest.PLATEAU_NIVEAU1);
        Assert.assertTrue(iaTest.tourEnnemieAdjHauteur3(plateau, new Coordonnees(1, 1), false));
    }
    
    void testObtenirCoordAdjPlusProche() {
        IATowa iaTest = new IATowa("", 0, true);
        System.out.println("ici");
        System.out.println(iaTest.obtenirCoordAdjPlusProche(new Coordonnees(15, 15)).ligne + " " + iaTest.obtenirCoordAdjPlusProche(new Coordonnees(2, 2)).colonne);
    }
    
    @Test
    public void testFusionInteressante() {
        IATowa iaTest = new IATowa("", 0, false);
        Case[][] plateau = Utils.plateauDepuisTexte(JoueurTowaTest.PLATEAU_NIVEAU3_4_5_6_7_8);
        Assert.assertTrue(iaTest.fusionInteressante(plateau, Coordonnees.depuisCars('b', 'J'), false));
    }
    
    @Test
    public void testJouer() throws IOException {
        IATowa iaTest = new IATowa("", 0, false);
        Case[][] plateau = Utils.plateauDepuisTexte(JoueurTowaTest.PLATEAU_NIVEAU1);
        
        iaTest.ajouterNouvelleActionAdverse("PaA");
        iaTest.ajouterNouvelleActionAllie("PbA");
        iaTest.ajouterNouvelleActionAdverse("PaA");
        iaTest.ajouterNouvelleActionAllie("PaB");
        iaTest.ajouterNouvelleActionAdverse("PaA");
        iaTest.ajouterNouvelleActionAllie("FbA");
        iaTest.ajouterNouvelleActionAdverse("PaB");
        
        System.out.println(iaTest.jouer(plateau, 7));
    }
}
