package towa;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests sur la classe JoueurTowa.
 */
public class JoueurTowaTest {

    /**
     * Test de la fonction actionsPossibles. Commentez les appels aux tests des
     * niveaux inférieurs, n'activez que le test du niveau à valider.
     */
    @Test
    public void testActionsPossibles() {
        //testActionsPossibles_niveau1();
        //testActionsPossibles_niveau2();
        //testActionPossibles_niveau3();
        //testActionsPossibles_niveau4();
        //testActionsPossibles_niveau5();
        //testActionsPossibles_niveau6();
        //testActionPossibles_niveau7();
        testActionPossibles_niveau8();
    }

    /**
     * Test de la fonction actionsPossibles, au niveau 1.
     */
    public void testActionsPossibles_niveau1() {
        JoueurTowa joueur = new JoueurTowa();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        // on choisit la couleur du joueur
        boolean estNoir = true;
        // on choisit le niveau
        int niveau = 1;
        // on lance actionsPossibles
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        // on peut afficher toutes les actions possibles calculées :
        Utils.afficherActionsPossibles(actionsPossibles);
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaB,1,0"));
        // on peut aussi tester si une action n'est pas dans les actions 
        // possibles :
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PaQ,1,0"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PaA,0,0"));
        // testons les 4 coins :
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaA,1,0"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PpA,1,0"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaP,1,0"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PpP,1,0"));
        // vérifions s'il y a le bon nombre d'actions possibles :
        assertEquals(Coordonnees.NB_LIGNES * Coordonnees.NB_COLONNES,
                actionsPossibles.length);
    }

    /**
     * Test de la fonction actionsPossibles, au niveau 2.
     */
    public void testActionsPossibles_niveau2() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        boolean estNoir = true;
        int niveau = 2;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        // on lance actionsPossibles pour un joueur noir
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        Coordonnees coord;
        // pose sur case vide : possible
        coord = Coordonnees.depuisCars('a', 'B');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
        // pose sur case de même couleur : possible
        coord = Coordonnees.depuisCars('b', 'A');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
        // pose sur case de couleur opposée : impossible
        coord = Coordonnees.depuisCars('a', 'G');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
        // pose sur case de même couleur et hauteur > 1 : possible
        coord = Coordonnees.depuisCars('k', 'J');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
        
        // on lance actionsPossibles pour un joueur blanc
        actionsPossibles = joueur.actionsPossibles(plateau, !estNoir, niveau);
        // pose sur case vide : possible
        coord = Coordonnees.depuisCars('a', 'B');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir, nbPionsBlancs + 2)));
        // pose sur case de même couleur : possible
        coord = Coordonnees.depuisCars('a', 'G');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir, nbPionsBlancs + 1)));
        // pose sur case de couleur opposée : impossible
        coord = Coordonnees.depuisCars('b', 'A');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir, nbPionsBlancs + 1)));
        // pose sur case de même couleur et hauteur > 1 : possible
        coord = Coordonnees.depuisCars('m', 'F');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir, nbPionsBlancs + 1)));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 3.
     */
    public void testActionsPossibles_niveau3() {
        //Passe car modifiée et permet d'économiser des lignes de codes
        testActionsPossibles_niveau2();
        
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        boolean estNoir = true;
        int niveau = 3;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        // on lance actionsPossibles pour un joueur noir
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        Coordonnees coord;
        // pose sur case de même couleur et hauteur supérieur ou égale à 4 : impossible
        coord = Coordonnees.depuisCars('i', 'B');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
        // pose sur une case de même couleur et hauteur inférieur à 4 : possible
        coord = Coordonnees.depuisCars('l', 'E');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
        
        // on lance actionsPossibles pour un joueur blanc
        actionsPossibles = joueur.actionsPossibles(plateau, !estNoir, niveau);
        // pose sur case de même couleur et hauteur supérieur ou égale à 4 : impossible
        coord = Coordonnees.depuisCars('l', 'F');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir, nbPionsBlancs + 1)));
        /// pose sur une case de même couleur et hauteur inférieur à 4 : possible
        coord = Coordonnees.depuisCars('m', 'F');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir, nbPionsBlancs + 1)));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 4.
     */
    public void testActionsPossibles_niveau4() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        boolean estNoir = true;
        int niveau = 4;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        // on lance actionsPossibles pour un joueur noir
        String[] actionsPossibles = joueur.actionsPossibles(plateau, estNoir, niveau);
        Coordonnees coord;
        
        //activation sur une case vide : impossible
        coord = Coordonnees.depuisCars('a', 'A');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs - 3)));
        /*activation sur une case de même couleur
        sans autre(s) tour(s) adverse(s) adjacente(s): possible*/
        coord = Coordonnees.depuisCars('b', 'A');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs)));
        /*activation sur une case de même couleur
        avec autre(s) tour(s) adverse(s) adjacente(s) moins haute: possible*/
        coord = Coordonnees.depuisCars('l', 'E');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs - 3)));
        /*activation sur une case de même couleur
        avec autre(s) tour(s) adverse(s) adjacente(s) plus haute: possible*/
        coord = Coordonnees.depuisCars('n', 'F');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs)));
        //activation sur une case de couleur opposée: impossible
        coord = Coordonnees.depuisCars('n', 'F');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir - 3, nbPionsBlancs)));
        
        // on lance actionsPossibles pour un joueur blanc
        actionsPossibles = joueur.actionsPossibles(plateau, !estNoir, niveau);
        //activation sur case vide: impossoble
        coord = Coordonnees.depuisCars('a', 'A');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir - 3, nbPionsBlancs)));
        /*activation sur une case de même couleur
        sans autre(s) tour(s) adverse(s) adjacente(s): possible*/
        coord = Coordonnees.depuisCars('d', 'I');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs)));
        /*activation sur une case de même couleur
        avec autre(s) tour(s) adverse(s) adjacente(s) moins haute: possible*/
        coord = Coordonnees.depuisCars('l', 'F');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir - 5, nbPionsBlancs)));
        /*activation sur une case de même couleur
        avec autre(s) tour(s) adverse(s) adjacente(s) plus haute: possible*/
        coord = Coordonnees.depuisCars('c', 'L');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs)));
        //activation sur une case de couleur opposée: impossible
        coord = Coordonnees.depuisCars('c', 'K');
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs - 15)));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 5.
     */
    void testActionsPossibles_niveau5() {
        //Testes de l'action activer, et de l'action pose basique
        //testActionsPossibles_niveau4();
        
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        Coordonnees coord;
        boolean joueurNoir;
        int niveau = 5;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        
        // on lance actionsPossibles pour un joueur noir
        joueurNoir = true;
        String[] actionsPossibles = joueur.actionsPossibles(plateau, joueurNoir, niveau);
        //on teste pour une case vide qui n'a pas de tours adverses adjacentes
        coord = Coordonnees.depuisCars('n', 'B');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 1, nbPionsBlancs)));
        //on teste pour une case vide qui a des tours adverses adjacentes
        coord = Coordonnees.depuisCars('c', 'M');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir + 2, nbPionsBlancs)));
        
        // on lance actionsPossibles pour un joueur blanc
        joueurNoir = false;
        actionsPossibles = joueur.actionsPossibles(plateau, joueurNoir, niveau);
        //on teste pour une case vide qui n'a pas de tours adverses adjacentes
        coord = Coordonnees.depuisCars('p', 'P');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir, nbPionsBlancs + 1)));
        //on teste pour une case vide qui a des tours adverses adjacentes
        coord = Coordonnees.depuisCars('i', 'K');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionPose(coord, nbPionsNoir, nbPionsBlancs + 2)));
    }
    
    /**
     * Test de la fonction actionsPossibles, au niveau 6.
     */
    void testActionsPossibles_niveau6() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        Coordonnees coord;
        boolean joueurNoir;
        int niveau = 6;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        
        //on lance actionsPossibles pour un joueur noir
        joueurNoir = true;
        String[] actionsPossibles = joueur.actionsPossibles(plateau, joueurNoir, niveau);
        //on teste une activation sans tours ennemies adjacentes/lignes/colonnes
        coord = Coordonnees.depuisCars('i', 'M');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs)));
        //on teste une activation avec tours ennemies adjacentes/lignes/colonnes
        coord = Coordonnees.depuisCars('c', 'K');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs - 5)));
        
        //on lance actionsPossibles pour un joueur blanc
        joueurNoir = false;
        actionsPossibles = joueur.actionsPossibles(plateau, joueurNoir, niveau);
        //on teste une activation sans tours ennemies adjacentes/lignes/colonnes
        coord = Coordonnees.depuisCars('k', 'O');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs)));
        //on teste une activation avec tours ennemies adjacentes/lignes/colonnes
        coord = Coordonnees.depuisCars('o', 'C');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir - 3, nbPionsBlancs)));
    }
    
    void testActionPossibles_niveau7() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        Coordonnees coord;
        boolean joueurNoir;
        int niveau = 7;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        
        //on lance actionsPossibles pour un joueur noir
        joueurNoir = true;
        String[] actionsPossibles = joueur.actionsPossibles(plateau, joueurNoir, niveau);
        //on teste une activation sans tours ennemies adjacentes + lignes + colonnes
        coord = Coordonnees.depuisCars('i', 'N');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs)));
        //on teste une activation avec tours ennemies adjacentes + lignes + colonnes
        coord = Coordonnees.depuisCars('c', 'K');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs - 4)));
        //on teste une activation avec tours ennemies que sur les cases adjacentes
        coord = Coordonnees.depuisCars('l', 'E');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs - 3)));
        
        //on lance actionsPossibles pour un joueur blanc
        joueurNoir = false;
        actionsPossibles = joueur.actionsPossibles(plateau, joueurNoir, niveau);
        //on teste une activation sans tours ennemies adjacentes + lignes + colonnes
        coord = Coordonnees.depuisCars('c', 'C');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir, nbPionsBlancs)));
        //on teste une activation avec tours ennemies adjacentes + lignes + colonnes
        coord = Coordonnees.depuisCars('o', 'C');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir - 3, nbPionsBlancs)));
        //on teste une activation avec tours ennemies que sur les cases adjacentes
        coord = Coordonnees.depuisCars('m', 'F');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionActiver(coord, nbPionsNoir - 3, nbPionsBlancs)));
    }
    
    void testActionPossibles_niveau8() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        Coordonnees coord;
        boolean joueurNoir;
        int niveau = 8;
        int nbPionsNoir = JoueurTowa.nbPions(plateau, true);
        int nbPionsBlancs = JoueurTowa.nbPions(plateau, false);
        
        //on lance actionsPossibles pour un joueur noir
        joueurNoir = true;
        String[] actionsPossibles = joueur.actionsPossibles(plateau, joueurNoir, niveau);
        //on teste une fusion sans avec tours alliées adjacentes + colonne + ligne
        coord = Coordonnees.depuisCars('m', 'J');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir - 1, nbPionsBlancs)));
        //on teste une fusion avec uniquement des tours alliées adjacentes
        coord = Coordonnees.depuisCars('n', 'E');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir, nbPionsBlancs)));
        //on teste une fusion avec uniquement des tours alliées sur la ligne et/ou la colonne
        coord = Coordonnees.depuisCars('p', 'B');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir - 1, nbPionsBlancs)));
        //on teste une fusion avec aucune tour alliée à portée
        coord = Coordonnees.depuisCars('c', 'K');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir, nbPionsBlancs)));
        
        //on lance actionsPossibles pour un joueur blanc
        joueurNoir = false;
        actionsPossibles = joueur.actionsPossibles(plateau, joueurNoir, niveau);
        //on teste une fusion sans avec tours alliées adjacentes + colonne + ligne
        coord = Coordonnees.depuisCars('b', 'L');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir, nbPionsBlancs)));
        //on teste une fusion avec uniquement des tours alliées adjacentes
        coord = Coordonnees.depuisCars('m', 'F');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir, nbPionsBlancs - 4)));
        //on teste une fusion avec uniquement des tours alliées sur la ligne et/ou la colonne
        coord = Coordonnees.depuisCars('c', 'C');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir, nbPionsBlancs)));
        //on teste une fusion avec aucune tour alliée à portée
        coord = Coordonnees.depuisCars('p', 'G');
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles,
                JoueurTowa.chaineActionFusion(coord, nbPionsNoir, nbPionsBlancs)));
    }
    
    @Test
    public void testsMethodesBooleennes() {
        testPosePossible();
        testEstMemeCouleurJoueur();
        testCoordonneeEstDansPlateau();
        testHauteurPlusPetite();
        testExisteTourAdverseAdjacente();
        testSuppressionPossible();
        testFusionPossible();
    }
    
    void testPosePossible() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        boolean estNoir = true;
        
        //on teste pour un joueur noir
        Coordonnees coord;
        // pose sur case vide : possible
        coord = Coordonnees.depuisCars('a', 'B');
        assertTrue(joueur.posePossible(plateau, coord, estNoir));
        // pose sur case de même couleur : possible
        coord = Coordonnees.depuisCars('b', 'A');
        assertTrue(joueur.posePossible(plateau, coord, estNoir));
        // pose sur case de couleur opposée : impossible
        coord = Coordonnees.depuisCars('a', 'G');
        assertFalse(joueur.posePossible(plateau, coord, estNoir));
        // pose sur case de même couleur et hauteur > 1 : possible
        coord = Coordonnees.depuisCars('k', 'J');
        assertTrue(joueur.posePossible(plateau, coord, estNoir));
        
        // on lance actionsPossibles pour un joueur blanc
        estNoir = false;
        // pose sur case vide : possible
        coord = Coordonnees.depuisCars('a', 'B');
        assertTrue(joueur.posePossible(plateau, coord, estNoir));
        // pose sur case de même couleur : possible
        coord = Coordonnees.depuisCars('a', 'G');
        assertTrue(joueur.posePossible(plateau, coord, estNoir));
        // pose sur case de couleur opposée : impossible
        coord = Coordonnees.depuisCars('b', 'A');
        assertFalse(joueur.posePossible(plateau, coord, estNoir));
        // pose sur case de même couleur et hauteur > 1 : possible
        coord = Coordonnees.depuisCars('m', 'F');
        assertTrue(joueur.posePossible(plateau, coord, estNoir));
    }
    
    void testSuppressionPossible() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        boolean joueurNoir;
        Coordonnees coordActivation, coordATester;
        
        //Test pour le joueur noir
        joueurNoir = true;
        //Suppression possible pour une tour blanche de plus petite hauteur
        coordActivation = Coordonnees.depuisCars('c', 'K');
        coordATester = Coordonnees.depuisCars('c', 'L');
        assertTrue(JoueurTowa.suppressionPossible(plateau, coordATester, coordActivation, joueurNoir));
        //Suppression impossible pour une tour blanche de même hauteur
        coordActivation = Coordonnees.depuisCars('c', 'K');
        coordATester = Coordonnees.depuisCars('l', 'F');
        assertFalse(JoueurTowa.suppressionPossible(plateau, coordATester, coordActivation, joueurNoir));
        //Suppression impossible pour une tour noir
        coordActivation = Coordonnees.depuisCars('c', 'K');
        coordATester = Coordonnees.depuisCars('b', 'A');
        assertFalse(JoueurTowa.suppressionPossible(plateau, coordATester, coordActivation, joueurNoir));
        //Suppression impossible pour une case vide
        coordActivation = Coordonnees.depuisCars('c', 'K');
        coordATester = Coordonnees.depuisCars('a', 'B');
        assertFalse(JoueurTowa.suppressionPossible(plateau, coordATester, coordActivation, joueurNoir));
        
        //Test pour le joueur blanc
        joueurNoir = false;
        //Suppression possible pour une tour noire de plus petite hauteur
        coordActivation = Coordonnees.depuisCars('f', 'F');
        coordATester = Coordonnees.depuisCars('g', 'J');
        assertTrue(JoueurTowa.suppressionPossible(plateau, coordATester, coordActivation, joueurNoir));
        //Suppression impossible pour une tour noire de même hauteur
        coordActivation = Coordonnees.depuisCars('f', 'F');
        coordATester = Coordonnees.depuisCars('l', 'E');
        assertFalse(JoueurTowa.suppressionPossible(plateau, coordATester, coordActivation, joueurNoir));
        //Suppression impossible pour une tour blanche
        coordActivation = Coordonnees.depuisCars('f', 'F');
        coordATester = Coordonnees.depuisCars('a', 'G');
        assertFalse(JoueurTowa.suppressionPossible(plateau, coordATester, coordActivation, joueurNoir));
        //Suppression impossible pour une case vide
        coordActivation = Coordonnees.depuisCars('f', 'F');
        coordATester = Coordonnees.depuisCars('a', 'B');
        assertFalse(JoueurTowa.suppressionPossible(plateau, coordATester, coordActivation, joueurNoir));
    }
    
    void testFusionPossible() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        Coordonnees coord;
        boolean joueurNoir;
        
        //Test pour un joueur noir
        joueurNoir = true;
        //Test pour une tour de même couleur : possible
        coord = Coordonnees.depuisCars('i', 'C');
        assertTrue(JoueurTowa.fusionPossible(plateau, coord, joueurNoir));
        
        //Test pour une tour de couleur opposée : impossible
        coord = Coordonnees.depuisCars('a', 'G');
        assertFalse(JoueurTowa.fusionPossible(plateau, coord, joueurNoir));
        
        //Test pour une case vide: impossible
        coord = Coordonnees.depuisCars('a', 'A');
        assertFalse(JoueurTowa.fusionPossible(plateau, coord, joueurNoir));
        
        //Test pour un joueur blanc
        joueurNoir = false;
        //Test pour une tour de même couleur : possible
        coord = Coordonnees.depuisCars('a', 'G');
        assertTrue(JoueurTowa.fusionPossible(plateau, coord, joueurNoir));
        
        //Test pour une tour de couleur opposée : impossible
        coord = Coordonnees.depuisCars('i', 'C');
        assertFalse(JoueurTowa.fusionPossible(plateau, coord, joueurNoir));
        
        //Test pour une case vide: impossible
        coord = Coordonnees.depuisCars('a', 'A');
        assertFalse(JoueurTowa.fusionPossible(plateau, coord, joueurNoir));
    }
    
    void testEstMemeCouleurJoueur() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        Case caseATest;
        Coordonnees coord;
        
        boolean estNoir = true;
        //on teste pour le joueur noir
        //la case est de la même couleur : possible
        coord = Coordonnees.depuisCars('b', 'A');
        caseATest = plateau[coord.ligne][coord.colonne];
        assertTrue(JoueurTowa.caseEstMemeCouleurJoueur(caseATest, estNoir));
        //la case n'est pas de la même couleur : impossible
        coord = Coordonnees.depuisCars('a', 'G');
        caseATest = plateau[coord.ligne][coord.colonne];
        assertFalse(JoueurTowa.caseEstMemeCouleurJoueur(caseATest, estNoir));
        
        estNoir = false;
        //on teste pour le joueur blanc
        //la case est de la même couleur : possible
        coord = Coordonnees.depuisCars('a', 'G');
        caseATest = plateau[coord.ligne][coord.colonne];
        assertTrue(JoueurTowa.caseEstMemeCouleurJoueur(caseATest, estNoir));
        //la case n'est pas de la même couleur : impossible
        coord = Coordonnees.depuisCars('b', 'A');
        caseATest = plateau[coord.ligne][coord.colonne];
        assertFalse(JoueurTowa.caseEstMemeCouleurJoueur(caseATest, estNoir));
    }
    
    void testCoordonneeEstDansPlateau() {
        //x et y de la coordonnée dans le plateau : possible
        assertTrue(JoueurTowa.coordonneeEstDansPlateau(new Coordonnees(2, 7)));
        //uniquement x de la coordonnée dans le plateau : impossible
        assertFalse(JoueurTowa.coordonneeEstDansPlateau(new Coordonnees(10, 20)));
        //uniquement y de la coordonnée dans le plateau : impossible
        assertFalse(JoueurTowa.coordonneeEstDansPlateau(new Coordonnees(18, 4)));
        //x et y de la coordonnée pas dans le plateau : impossible
        assertFalse(JoueurTowa.coordonneeEstDansPlateau(new Coordonnees(16, 17)));
    }
    
    void testHauteurPlusPetite() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        Case caseTour1;
        Case caseTour2;
        Coordonnees coord1;
        Coordonnees coord2;
        
        //on teste pour le joueur noir
        //la tour est plus petite : possible
        coord1 = Coordonnees.depuisCars('b', 'A');
        coord2 = Coordonnees.depuisCars('l', 'F');
        caseTour1 = plateau[coord1.ligne][coord1.colonne];
        caseTour2 = plateau[coord2.ligne][coord2.colonne];
        assertTrue(JoueurTowa.hauteurPlusPetite(caseTour1, caseTour2));
        //la tour est plus grande : impossible
        coord1 = Coordonnees.depuisCars('l', 'E');
        coord2 = Coordonnees.depuisCars('m', 'E');
        caseTour1 = plateau[coord1.ligne][coord1.colonne];
        caseTour2 = plateau[coord2.ligne][coord2.colonne];
        assertFalse(JoueurTowa.hauteurPlusPetite(caseTour1, caseTour2));
        
        //on teste pour le joueur blanc
        //la tour est plus petite : possible
        coord1 = Coordonnees.depuisCars('m', 'E');
        coord2 = Coordonnees.depuisCars('l', 'E');
        caseTour1 = plateau[coord1.ligne][coord1.colonne];
        caseTour2 = plateau[coord2.ligne][coord2.colonne];
        assertTrue(JoueurTowa.hauteurPlusPetite(caseTour1, caseTour2));
        //la tour est plus grande : impossible
        coord1 = Coordonnees.depuisCars('l', 'F');
        coord2 = Coordonnees.depuisCars('b', 'A');
        caseTour1 = plateau[coord1.ligne][coord1.colonne];
        caseTour2 = plateau[coord2.ligne][coord2.colonne];
        assertFalse(JoueurTowa.hauteurPlusPetite(caseTour1, caseTour2));
    }
    
    void testExisteTourAdverseAdjacente() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        Coordonnees coord;
        boolean existeTourAdverseAdjacente, joueurNoir;
        
        //testes pour un joueur noir
        joueurNoir = true;
        //on teste pour une case où il y a au moins une tour adverse adjacente
        coord = Coordonnees.depuisCars('o', 'H');
        existeTourAdverseAdjacente = true;
        assertEquals(
                JoueurTowa.existeTourAdverseAdjacente(plateau, coord, joueurNoir), 
                existeTourAdverseAdjacente);
        //on teste pour une case où il n'y a que des tours alliées adjacentes
        coord = Coordonnees.depuisCars('i', 'B');
        existeTourAdverseAdjacente = false;
        assertEquals(
                JoueurTowa.existeTourAdverseAdjacente(plateau, coord, joueurNoir),
                existeTourAdverseAdjacente);
        
        //on teste pour une case où il n'y a que des cases vides adjacentes
        coord = Coordonnees.depuisCars('j', 'H');
        existeTourAdverseAdjacente = false;
        assertEquals(
                JoueurTowa.existeTourAdverseAdjacente(plateau, coord, joueurNoir),
                existeTourAdverseAdjacente);
        
        //testes pour un joueur blanc
        joueurNoir = false;
        //on teste pour une case où il y a au moins une tour adverse adjacente
        coord = Coordonnees.depuisCars('i', 'L');
        existeTourAdverseAdjacente = true;
        assertEquals(
                JoueurTowa.existeTourAdverseAdjacente(plateau, coord, joueurNoir),
                existeTourAdverseAdjacente);
        //on teste pour une case où il n'y a que des tours alliées adjacentes
        coord = Coordonnees.depuisCars('b', 'H');
        existeTourAdverseAdjacente = false;
        assertEquals(
                JoueurTowa.existeTourAdverseAdjacente(plateau, coord, joueurNoir),
                existeTourAdverseAdjacente);
        //on teste pour une case où il n'y a que des cases vides adjacentes
        coord = Coordonnees.depuisCars('d', 'I');
        existeTourAdverseAdjacente = false;
        assertEquals(
                JoueurTowa.existeTourAdverseAdjacente(plateau, coord, joueurNoir),
                existeTourAdverseAdjacente);
    }
    
    @Test
    public void testsMethodesNbPions() {
        testCalculerNbPionsSiPose();
        testCalculerNbPionsSiActiver();
        testCalculerNbPionsSiFusion();
        testCalculerPionsSiAjoutHauteur();
        testNbPions();
    }
    
    void testCalculerNbPionsSiPose() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        Coordonnees coord;
        int nbPions[] = new int[2];
        nbPions[0] = JoueurTowa.nbPions(plateau, true);
        nbPions[1] = JoueurTowa.nbPions(plateau, false);
        
        //on teste pour le joueur noir
        //ajoute un pion sur une case de même couleur
        coord = Coordonnees.depuisCars('m', 'J');
        nbPions[0]++;
        Assert.assertArrayEquals(
                JoueurTowa.calculerNbPionsSiPose(plateau, coord,
                        nbPions[0] - 1, nbPions[1], true),
                nbPions);
        //ajoute un pion sur une case vide avec tour adverse adjacente
        coord = Coordonnees.depuisCars('i', 'L');
        nbPions[0] += 2;
        Assert.assertArrayEquals(
                JoueurTowa.calculerNbPionsSiPose(plateau, coord,
                        nbPions[0] - 2, nbPions[1], true),
                nbPions);
        
        //on teste pour une pose du joueur blanc
        //ajoute un pion sur une case de même couleur
        coord = Coordonnees.depuisCars('m', 'F');
        nbPions[1]++;
        Assert.assertArrayEquals(
                JoueurTowa.calculerNbPionsSiPose(plateau, coord,
                        nbPions[0], nbPions[1] - 1, false),
                nbPions);
        //ajoute un pion sur une case vide avec tour adverse adjacente
        coord = Coordonnees.depuisCars('i', 'L');
        nbPions[1] += 2;
        Assert.assertArrayEquals(
                JoueurTowa.calculerNbPionsSiPose(plateau, coord,
                        nbPions[0], nbPions[1] - 2, false),
                nbPions);
    }
    
    void testCalculerNbPionsSiActiver() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        Coordonnees coordActiver;
        int nbTourAdverseASuppr;
        int nbPions[] = new int[2];
        nbPions[0] = JoueurTowa.nbPions(plateau, true);
        nbPions[1] = JoueurTowa.nbPions(plateau, false);
        
        //on teste pour un joueur noir
        //Il y a des tours adjacentes/lignes/colonnes blanches moins hautes
        coordActiver = Coordonnees.depuisCars('c', 'K');
        nbTourAdverseASuppr = 4;
        nbPions[1] -= nbTourAdverseASuppr;
        Assert.assertArrayEquals(
                nbPions,
                JoueurTowa.calculerNbPionsSiActiver(plateau, coordActiver,
                        nbPions[0], nbPions[1]+nbTourAdverseASuppr, true));
        //Il n'y a aucune tour adjacente blanche moins haute
        coordActiver = Coordonnees.depuisCars('o', 'H');
        nbTourAdverseASuppr = 0;
        nbPions[1] -= nbTourAdverseASuppr;
        Assert.assertArrayEquals(
                nbPions,
                JoueurTowa.calculerNbPionsSiActiver(plateau, coordActiver,
                        nbPions[0], nbPions[1]+nbTourAdverseASuppr, true));
        
        //on teste pour un joueur blanc
        //Il y a des tours adjacentes noires moins hautes
        coordActiver = Coordonnees.depuisCars('m', 'F');
        nbTourAdverseASuppr = 3;
        nbPions[0] -= nbTourAdverseASuppr;
        Assert.assertArrayEquals(
                nbPions,
                JoueurTowa.calculerNbPionsSiActiver(plateau, coordActiver,
                        nbPions[0]+nbTourAdverseASuppr, nbPions[1], false));
        //Il n'y a aucune tour adjacente noire moins haute
        coordActiver = Coordonnees.depuisCars('p', 'G');
        nbTourAdverseASuppr = 0;
        nbPions[0] -= nbTourAdverseASuppr;
        Assert.assertArrayEquals(
                nbPions,
                JoueurTowa.calculerNbPionsSiActiver(plateau, coordActiver,
                        nbPions[0]+nbTourAdverseASuppr, nbPions[1], false));
    }
    
    void testCalculerNbPionsSiFusion() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        Coordonnees coordFusion;
        int nbTourAdverseASuppr;
        int nbPions[] = new int[2];
        nbPions[0] = JoueurTowa.nbPions(plateau, true);
        nbPions[1] = JoueurTowa.nbPions(plateau, false);
        boolean joueurNoir;
        
        //on teste pour un joueur noir
        joueurNoir = true;
        //Il y a des tours adjacentes/lignes/colonnes alliées, et le tout n'excède pas 4
        coordFusion = Coordonnees.depuisCars('g', 'J');
        nbTourAdverseASuppr = 0;
        nbPions[0] -= nbTourAdverseASuppr;
        Assert.assertArrayEquals(
                nbPions,
                JoueurTowa.calculerNbPionsSiFusion(plateau, coordFusion,
                        nbPions[0]-nbTourAdverseASuppr, nbPions[1], joueurNoir));
        
        //Il n'y aucune tour alliée
        coordFusion = Coordonnees.depuisCars('c', 'K');
        nbTourAdverseASuppr = 0;
        nbPions[0] -= nbTourAdverseASuppr;
        Assert.assertArrayEquals(
                nbPions,
                JoueurTowa.calculerNbPionsSiFusion(plateau, coordFusion,
                        nbPions[0]-nbTourAdverseASuppr, nbPions[1], joueurNoir));
        
        //La tour de fusion est égale à 4 de hauteur
        coordFusion = Coordonnees.depuisCars('i', 'B');
        nbTourAdverseASuppr = 2;
        nbPions[0] -= nbTourAdverseASuppr;
        Assert.assertArrayEquals(
                nbPions,
                JoueurTowa.calculerNbPionsSiFusion(plateau, coordFusion,
                        nbPions[0]+nbTourAdverseASuppr, nbPions[1], joueurNoir));
        
        //on teste pour un joueur blanche
        joueurNoir = false;
        //Il y a des tours adjacentes/lignes/colonnes alliées, et le tout n'excède pas 4
        coordFusion = Coordonnees.depuisCars('a', 'G');
        nbTourAdverseASuppr = 0;
        nbPions[1] -= nbTourAdverseASuppr;
        Assert.assertArrayEquals(
                nbPions,
                JoueurTowa.calculerNbPionsSiFusion(plateau, coordFusion,
                        nbPions[0], nbPions[1]+nbTourAdverseASuppr, joueurNoir));
        
        //Il n'y aucune tour alliée
        coordFusion = Coordonnees.depuisCars('d', 'I');
        nbTourAdverseASuppr = 0;
        nbPions[1] -= nbTourAdverseASuppr;
        Assert.assertArrayEquals(
                nbPions,
                JoueurTowa.calculerNbPionsSiFusion(plateau, coordFusion,
                        nbPions[0], nbPions[1]+nbTourAdverseASuppr, joueurNoir));
        
        //La tour de fusion est égale à 4 de hauteur
        coordFusion = Coordonnees.depuisCars('l', 'F');
        nbTourAdverseASuppr = 7;
        nbPions[1] -= nbTourAdverseASuppr;
        Assert.assertArrayEquals(
                nbPions,
                JoueurTowa.calculerNbPionsSiFusion(plateau, coordFusion,
                        nbPions[0], nbPions[1]+nbTourAdverseASuppr, joueurNoir));
    }
    
    void testCalculerPionsSiAjoutHauteur() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3_4_5_6_7_8);
        Coordonnees coordAjout;
        int hauteurAAjouter;
        int nbPionsAjoutes;
        
        //Test pour un ajout de 1 pions sur une hauteur strictement inférieur à 4
        coordAjout = Coordonnees.depuisCars('f', 'F');
        hauteurAAjouter = 1;
        nbPionsAjoutes = 1;
        assertEquals(nbPionsAjoutes, JoueurTowa.calculerPionsSiAjoutHauteur(plateau, coordAjout, hauteurAAjouter));
        
        //Test pour un ajout de pions sur une hauteur égale 4
        coordAjout = Coordonnees.depuisCars('c', 'K');
        hauteurAAjouter = 2;
        nbPionsAjoutes = 0;
        assertEquals(nbPionsAjoutes, JoueurTowa.calculerPionsSiAjoutHauteur(plateau, coordAjout, hauteurAAjouter));
        
        //Test pour un ajout de pions supérieur à la hauteur d'une tour de hauteur strictement inférieur à 4
        coordAjout = Coordonnees.depuisCars('n', 'G');
        hauteurAAjouter = 3;
        nbPionsAjoutes = 2;
        assertEquals(nbPionsAjoutes, JoueurTowa.calculerPionsSiAjoutHauteur(plateau, coordAjout, hauteurAAjouter));
    }
    
    void testNbPions() {
        // plateau1 : 0 noir, 0 blanc
        Case[][] plateau1 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        assertEquals(0, JoueurTowa.nbPions(plateau1, true));
        assertEquals(0, JoueurTowa.nbPions(plateau1, false));
        // plateau2 : 27 noirs, 20 blancs
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        assertEquals(27, JoueurTowa.nbPions(plateau2, true));
        assertEquals(20, JoueurTowa.nbPions(plateau2, false));
    }
    
    @Test
    public void testChaineAction() {
        testChaineActionPose();
        testChaineActionActiver();
        testChaineActionFusion();
        testVerifierNomAction();
    }
    
    void testVerifierNomAction() {
        assertTrue(JoueurTowa.verifierNomAction('P'));
        assertTrue(JoueurTowa.verifierNomAction('A'));
        assertTrue(JoueurTowa.verifierNomAction('F'));
        assertFalse(JoueurTowa.verifierNomAction('3'));
    }
    
    void testChaineActionPose() {
        assertEquals("PfK,3,8", 
                JoueurTowa.chaineActionPose(Coordonnees.depuisCars('f', 'K'), 3, 8));
        assertEquals("PaA,0,0", 
                JoueurTowa.chaineActionPose(Coordonnees.depuisCars('a', 'A'), 0, 0));
        assertEquals("PpP,10,10", 
                JoueurTowa.chaineActionPose(Coordonnees.depuisCars('p', 'P'), 10, 10));
        assertFalse("PdC,14,4".equals(JoueurTowa.chaineActionPose(Coordonnees.depuisCars('p', 'P'), -6, 4)));
        assertFalse("PdC,14,4".equals(JoueurTowa.chaineActionPose(Coordonnees.depuisCars('e', 'P'), 14, 4)));
    }
    
    void testChaineActionActiver() {
        assertEquals("AjP,11,5", 
                JoueurTowa.chaineActionActiver(Coordonnees.depuisCars('j', 'P'), 11, 5));
        assertEquals("AaA,1,7", 
                JoueurTowa.chaineActionActiver(Coordonnees.depuisCars('a', 'A'), 1, 7));
        assertEquals("ApP,10,10", 
                JoueurTowa.chaineActionActiver(Coordonnees.depuisCars('p', 'P'), 10, 10));
        assertFalse("AdC,14,4".equals(JoueurTowa.chaineActionActiver(Coordonnees.depuisCars('p', 'P'), -6, 4)));
        assertFalse("PdC,14,4".equals(JoueurTowa.chaineActionActiver(Coordonnees.depuisCars('n', 'P'), 14, 4)));
    }
    
    void testChaineActionFusion() {
        assertEquals("FjP,11,5", 
                JoueurTowa.chaineActionFusion(Coordonnees.depuisCars('j', 'P'), 11, 5));
        assertEquals("FaA,1,7", 
                JoueurTowa.chaineActionFusion(Coordonnees.depuisCars('a', 'A'), 1, 7));
        assertEquals("FpP,10,10", 
                JoueurTowa.chaineActionFusion(Coordonnees.depuisCars('p', 'P'), 10, 10));
        assertFalse("FdC,14,4".equals(JoueurTowa.chaineActionFusion(Coordonnees.depuisCars('p', 'P'), -6, 4)));
        assertFalse("FdC,14,4".equals(JoueurTowa.chaineActionFusion(Coordonnees.depuisCars('n', 'P'), 14, 4)));
    }
    
    /**
     * Un plateau de base, sous forme de chaîne. Pour construire une telle
     * chaîne depuis votre sortie.log, déclarez simplement : final String
     * MON_PLATEAU = ""; puis copiez le plateau depuis votre sortie.log, et
     * collez-le entre les guillemets. Puis Alt+Shift+f pour mettre en forme.
     */
    static final String PLATEAU_NIVEAU1
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+";

    static final String PLATEAU_NIVEAU2
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|N1 |   |   |   |   |   |   |B1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |B1 |   |B1 |   |   |   |   |   |N5 |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|B1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |B1 |   |   |   |   |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |N1 |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |N1 |N1 |   |   |   |   |   |   |   |   |   |N1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |N1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |N1 |   |   |   |   |N2 |   |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |N3 |B4 |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |B1 |B2 |N1 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |N1 |N1 |N2 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |N1 |   |   |   |   |   |N1 |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";
    
     static final String PLATEAU_NIVEAU3_4_5_6_7_8
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |N1 |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|N1 |   |   |   |   |   |   |B1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |B1 |   |B1 |   |   |   |   |   |N4 |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|B1 |   |   |   |   |   |   |   |   |   |B1 |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |B3 |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |B1 |   |   |   |   |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |N1 |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |N4 |N1 |   |   |   |   |   |   |   |   |   |   |N1 |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |N1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |N1 |   |   |   |   |N2 |   |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |N3 |B4 |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |B1 |B2 |N1 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |N1 |N1 |N2 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |   |B2 |   |   |   |   |N1 |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |N1 |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";
}
