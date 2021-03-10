package towa;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Classe de test de la classe MyUtils
 * 
 * @author Alexandre Sparton
 */
public class MyUtilsTest {
    
    /**
     * Tests des fontions de MyUtils
     */
    @Test
    public void testMyUtils() {
        testFusionTableauxCoordonnees();
        testSupprimerDoublonsCoordonnees();
        testRechercheTableauCoordonnees();
    }
    /*
     * Test de la fonction fusion Tableaux Coordonnees
     */
    void testFusionTableauxCoordonnees() {
        //test avec deux tableaux pleins
        Coordonnees[] tab1 = {
            Coordonnees.depuisCars('a', 'A'), 
            Coordonnees.depuisCars('a', 'B')};
        Coordonnees[] tab2 = {
            Coordonnees.depuisCars('a', 'A'), 
            Coordonnees.depuisCars('a', 'B')};
        Coordonnees[] tabFusion1 = {
            Coordonnees.depuisCars('a', 'A'), 
            Coordonnees.depuisCars('a', 'B'),
            Coordonnees.depuisCars('a', 'A'), 
            Coordonnees.depuisCars('a', 'B')};
        Assert.assertArrayEquals(
                tabFusion1,
                MyUtils.fusionTableauxCoordonnees(tab1, tab2));
        
        //test avec un tableau plein et un tableau vide
        Coordonnees[] tabZero = new Coordonnees[0];
        Coordonnees[] tabFusion2 = {
            Coordonnees.depuisCars('a', 'A'), 
            Coordonnees.depuisCars('a', 'B')};
        Assert.assertArrayEquals(
                tabFusion2,
                MyUtils.fusionTableauxCoordonnees(tab1, tabZero));
        
        //test avec un tableau plein et un tableau vide (deuxième sens)
        Coordonnees[] tabFusion3 = {
            Coordonnees.depuisCars('a', 'A'), 
            Coordonnees.depuisCars('a', 'B')};
        Assert.assertArrayEquals(
                tabFusion3,
                MyUtils.fusionTableauxCoordonnees(tabZero, tab1));
        
        //test avec deux tableaux vides
        Coordonnees[] tabFusion4 = new Coordonnees[0];
        Assert.assertArrayEquals(
                tabFusion4,
                MyUtils.fusionTableauxCoordonnees(tabZero, tabZero));
    }
    
    /*
     * Test de la fonction fusion Tableaux Coordonnees
     */
    void testSupprimerDoublonsCoordonnees() {
        //test avec un tableau comportant plusieurs fois le même doublon
        Coordonnees[] tab = {
            Coordonnees.depuisCars('a', 'A'), 
            Coordonnees.depuisCars('a', 'B'),
            Coordonnees.depuisCars('a', 'A'), 
            Coordonnees.depuisCars('a', 'A')};
        Coordonnees[] tabSansDoublon = {
            Coordonnees.depuisCars('a', 'A'),
            Coordonnees.depuisCars('a', 'B')};
        Coordonnees doublon = Coordonnees.depuisCars('a', 'A');
        Assert.assertArrayEquals(
                tabSansDoublon,
                MyUtils.supprimerDoublonsCoordonnees(tab, doublon));
        
        //test avec un tableau ne comportant qu'un seul doublon
        Coordonnees[] tab2 = {
            Coordonnees.depuisCars('a', 'A'), 
            Coordonnees.depuisCars('a', 'B'),
            Coordonnees.depuisCars('a', 'A')};
        Assert.assertArrayEquals(
                tabSansDoublon,
                MyUtils.supprimerDoublonsCoordonnees(tab2, doublon));
        
        //test avec un tableau ne comportant aucun doublon
        Coordonnees[] tab3 = {
            Coordonnees.depuisCars('a', 'A'), 
            Coordonnees.depuisCars('a', 'B')};
        Assert.assertArrayEquals(
                tabSansDoublon,
                MyUtils.supprimerDoublonsCoordonnees(tab3, doublon));
        
        //test avec un tableaux vides
        Coordonnees[] tab4 = new Coordonnees[0];
        Assert.assertArrayEquals(
                tab4,
                MyUtils.supprimerDoublonsCoordonnees(tab4, doublon));
    }
    
    void testRechercheTableauCoordonnees() {
        //Test pour une coordonnée existante au milieu du tableau
        Coordonnees[] tab = {
            Coordonnees.depuisCars('a', 'A'),
            Coordonnees.depuisCars('c', 'K'),
            Coordonnees.depuisCars('i', 'P')};
        Coordonnees coord = Coordonnees.depuisCars('c', 'K');
        int index = 1;
        assertEquals(MyUtils.rechercheTableauCoordonnees(tab, coord), index);
        
        //Test pour une coordonnée existante au début du tableau
        coord = Coordonnees.depuisCars('a', 'A');
        index = 0;
        assertEquals(MyUtils.rechercheTableauCoordonnees(tab, coord), index);
        
        //Test pour une coordonnée existante à la fin du tableau
        coord = Coordonnees.depuisCars('i', 'P');
        index = tab.length - 1;
        assertEquals(MyUtils.rechercheTableauCoordonnees(tab, coord), index);
        
        //Test pour une coordonnée n'exsitant pas dans le tableau
        coord = Coordonnees.depuisCars('m', 'N');
        index = -1;
        assertEquals(MyUtils.rechercheTableauCoordonnees(tab, coord), index);
        //Test pour une coordonnée n'exsitant pas dans le tableau (2)
        coord = new Coordonnees(-1, 72);
        index = -1;
        assertEquals(MyUtils.rechercheTableauCoordonnees(tab, coord), index);
    }
}
