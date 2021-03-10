package towa;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Joueur implémentant les actions possibles à partir d'un plateau, pour un
 * niveau donné.
 */
public class JoueurTowa implements IJoueurTowa {

    static final int TOURHAUTEURMAX = 4;

    /**
     * Cette méthode renvoie, pour un plateau donné et un joueur donné, toutes
     * les actions possibles pour ce joueur.
     *
     * @param plateau le plateau considéré
     * @param joueurNoir vrai si le joueur noir joue, faux si c'est le blanc
     * @param niveau le niveau de la partie à jouer
     * @return l'ensemble des actions possibles
     */
    @Override
    public String[] actionsPossibles(Case[][] plateau, boolean joueurNoir, int niveau) {
        // afficher l'heure de lancement
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        System.out.println("actionsPossibles : lancement le " + format.format(new Date()));
        // on compte le nombre de pions sur le plateau avant action
        int nbPionsNoirs = nbPions(plateau, true);
        int nbPionsBlancs = nbPions(plateau, false);
        // calculer les actions possibles
        String actions[] = new String[1028];
        int nbActions = 0;
        // pour chaque ligne
        for (int lig = 0; lig < Coordonnees.NB_LIGNES; lig++) {
            // pour chaque colonne
            for (int col = 0; col < Coordonnees.NB_COLONNES; col++) {
                Coordonnees coord = new Coordonnees(lig, col);
                Case caseATester = plateau[coord.ligne][coord.colonne];
                // si la pose d'un pion de cette couleur est possible sur cette case
                if (posePossible(plateau, coord, joueurNoir)) {
                    // on ajoute l'action "pose" dans les actions possibles
                    actions = realiserAction('P', plateau, actions, nbActions,
                            joueurNoir, coord, nbPionsNoirs, nbPionsBlancs);
                    nbActions++;
                }
                if (plateau[coord.ligne][coord.colonne].tourPresente) {
                    /* on ajoute l'action "activer" et "fusion" dans les actions possibles, 
                    si c'est une tour de la couleur correspondante au joueur*/
                    if (caseEstMemeCouleurJoueur(caseATester, joueurNoir)) {
                        actions = realiserAction('F', plateau, actions, nbActions,
                            joueurNoir, coord, nbPionsNoirs, nbPionsBlancs);
                        nbActions++;
                        actions = realiserAction('A', plateau, actions, nbActions,
                            joueurNoir, coord, nbPionsNoirs, nbPionsBlancs);
                        nbActions++;
                    }
                }
            }
        }
        System.out.println("actionsPossibles : fin");
        return Utils.nettoyerTableau(actions);
    }

    /**
     * Indique s'il est possible de poser un pion sur une case pour ce plateau,
     * ce joueur, dans ce niveau.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param joueurNoir vrai si il s'agit du tour du joueur noir
     * @return vrai si la pose d'un pion sur cette case est autorisée dans ce
     * niveau
     */
    boolean posePossible(Case[][] plateau, Coordonnees coord, boolean joueurNoir) {

        boolean possible = false;
        //Pour mieux comprendre la condition
        boolean estDansPlateau = coordonneeEstDansPlateau(coord);
        boolean contientTour = (plateau[coord.ligne][coord.colonne].tourPresente);
        boolean estMemeCouleur = caseEstMemeCouleurJoueur(plateau[coord.ligne][coord.colonne], joueurNoir);
        boolean hauteurMaxDepasse = (plateau[coord.ligne][coord.colonne].hauteur > TOURHAUTEURMAX - 1);

        /* Détermine s'il est possible de poser le pion*/
        if (estDansPlateau) {
            if (!contientTour) {
                possible = true;
            } else {
                if (estMemeCouleur && !hauteurMaxDepasse) {
                    possible = true;
                }
            }
        }
        return possible;
    }
    
    /**
     * Détermine si la suppression de la tour de la case de coordonnée donnée est possible
     * Suppose que coordActivation et coord sont valides
     *
     * @param plateau le plateau
     * @param coord les coordonnées de la case à tester
     * @param coordActivation les coordonnées de la tour qui est activée
     * @param joueurNoir vrai s'il s'agit du tour du joueur noir
     * @return vrai si la tour en coord peut-être supprimée
     */
    static boolean suppressionPossible(
            Case[][] plateau,
            Coordonnees coord,
            Coordonnees coordActivation,
            boolean joueurNoir) {
        
        Case caseATester = plateau[coord.ligne][coord.colonne];
        Case caseActivation = plateau[coordActivation.ligne][coordActivation.colonne];
        boolean suppressionPossible = false;
        boolean memeCouleur = caseEstMemeCouleurJoueur(caseATester, joueurNoir);
        //Si c'est une tour adverse de plus petite hauteur alors la suppresion est possible
        if (caseATester.tourPresente) {
            if (!memeCouleur && hauteurPlusPetite(caseATester, caseActivation)) {
                suppressionPossible = true;
            }
        }
        return suppressionPossible;
    }
    
    /**
     * Détermine si la fusion avec la case donnée est possible
     *
     * @param plateau le plateau
     * @param coordAFusionner la coordonéée dont on doit tester si la fusion est possible
     * @param joueurNoir vrai si c'est le tour du joueur noir
     * @return vrai si la fusion est possible
     * -> si la case à fusionner contient une tour de même couleur que le joueur
     */
    static boolean fusionPossible(Case[][] plateau, Coordonnees coordAFusionner, boolean joueurNoir) {
        Case caseATester = plateau[coordAFusionner.ligne][coordAFusionner.colonne];
        //Pour mieux comprendre la condition
        boolean memeCouleur = caseEstMemeCouleurJoueur(caseATester, joueurNoir);
        boolean contientTour = caseATester.tourPresente;
        
        return (memeCouleur && contientTour);
    }
    
    /**
     * Determine si le pion de la case donnée est de la même couleur que celle
     * du joueur
     *
     * @param caseATest la case à considérer
     * @param joueurNoir vrai s'il s'agit du tour du joueur noir
     * @return vrai si le pion de la case est de la même couleur que celle du
     * joueur
     */
    static boolean caseEstMemeCouleurJoueur(Case caseATest, boolean joueurNoir) {
        boolean estMemeCouleur = false;
        //S'il y a une tour présente, alors on test la couleur
        if (caseATest.tourPresente) {
            if (caseATest.estNoire) {
                estMemeCouleur = joueurNoir;
            } else {
                estMemeCouleur = !joueurNoir;
            }
        }
        return estMemeCouleur;
    }

    /**
     * Determine si la case correspondant à la coordonnée donnée est dans le
     * plateau
     *
     * @param coord coordonnées de la case à considérer
     * @return vrai si la case est dans le plateau
     */
    static boolean coordonneeEstDansPlateau(Coordonnees coord) {
        return ((coord.ligne >= 0 && coord.ligne < Coordonnees.NB_LIGNES)
                && (coord.colonne >= 0 && coord.colonne < Coordonnees.NB_COLONNES));
    }

    /**
     * Determine si la hauteur de la tour dans case1 est inférieur à celle dans case2
     * Suppose que les cases contiennent une tour
     *
     * @param case1 la case qui va être comparée à case2
     * @param case2 la case qui va être le comparant
     * @return vrai si case1.hauteur est plus petite que case2.hauteur
     */
    static boolean hauteurPlusPetite(Case case1, Case case2) {
        return case1.hauteur < case2.hauteur;
    }
    
    /**
     * Détermine s'il y a une tour adverse parmis les cases adjacentes
     * d'une case de référence
     *
     * @param plateau le plateau
     * @param coord la coordonnée de référence
     * @param joueurNoir vrai si c'est le tour du joueur noir
     * @return vrai si une des cases adjacentes à la case de référence
     * contient une tour adverse
     */
    static boolean existeTourAdverseAdjacente(Case[][] plateau, Coordonnees coord, boolean joueurNoir) {
        CoordonneesCasesAdjacentes coordsCaseAdjacentes = new CoordonneesCasesAdjacentes(coord);
        //Pour mieux comprendre la condition
        boolean couleurOposee;
        boolean contientTourAdverse = false;
        //On teste si une des cases adjacentes contient une tour adverse
        int i = 0;
        while (i < coordsCaseAdjacentes.coordonnees.length && !contientTourAdverse) {
            Case caseAdjacenteCourante =
                    plateau[coordsCaseAdjacentes.coordonnees[i].ligne][coordsCaseAdjacentes.coordonnees[i].colonne];
            couleurOposee = !caseEstMemeCouleurJoueur(caseAdjacenteCourante, joueurNoir);
            
            if (caseAdjacenteCourante.tourPresente && couleurOposee)
                contientTourAdverse = true;
            i++;
        }
        return contientTourAdverse;
    }
    
    /**
     * Nombre de pions d'une couleur donnée sur le plateau.
     *
     * @param plateau le plateau
     * @param joueurNoir vrai si on compte les pions noirs, faux sinon
     * @return le nombre de pions de cette couleur sur le plateau
     */
    static int nbPions(Case[][] plateau, boolean joueurNoir) {
        int nbPions = 0;
        for (int i = 0; i < Coordonnees.NB_LIGNES; i++) {
            for (int j = 0; j < Coordonnees.NB_COLONNES; j++) {
                if (joueurNoir) {
                    if (plateau[i][j].estNoire) {
                        nbPions += plateau[i][j].hauteur;
                    }
                } else {
                    if (!plateau[i][j].estNoire) {
                        nbPions += plateau[i][j].hauteur;
                    }
                }
            }
        }
        return nbPions;
    }
    
    /**
     * Calcule le nombre de pions ajoutés au plateau après l'ajout de hauteur sur une tour de coordonnée coord
     * Suppose que la case correspondant à la coordonnée de la tour n'est pas vide.
     *
     * @param plateau le plateau
     * @param coord la coordonnée de la tour dont on doit ajouté la hauteur
     * @param hauteur la hauteur que l'on doit ajouté
     * @return le nombre de pions sur la plateau après ajout
     */
    static int calculerPionsSiAjoutHauteur(Case[][] plateau, Coordonnees coord, int hauteur) {
        Case caseAAjouterHauteur = plateau[coord.ligne][coord.colonne];
        if (hauteur + caseAAjouterHauteur.hauteur <= TOURHAUTEURMAX)
            return hauteur;
        else
            return TOURHAUTEURMAX - caseAAjouterHauteur.hauteur;
    }
    
    /**
     * Calcule le nombre de pions après une action donnée
     *
     * @param nomAction caractère représentant le nom de l'action possible
     * @param plateau le plateau
     * @param coordAction la coordonnée de l'action
     * @param nbPionsNoirs le nombre de pions noirs avant action
     * @param nbPionsBlancs le nombre de pions blancs avant action
     * @param joueurNoir vrai si c'est le tour du joueur noir
     * @return le nombre de pions calculé après action
     */
    int[] calculerNbPionsApresAction(char nomAction, Case[][] plateau,
            Coordonnees coordAction, int nbPionsNoirs, int nbPionsBlancs, boolean joueurNoir) {
        int[] nbPions = new int[2];

        switch (nomAction) {
            case 'P':
                nbPions = calculerNbPionsSiPose(plateau, coordAction, nbPionsNoirs, nbPionsBlancs, joueurNoir);
                break;
            case 'A':
                nbPions = calculerNbPionsSiActiver(plateau, coordAction, nbPionsNoirs, nbPionsBlancs, joueurNoir);
                break;
            case 'F':
                nbPions = calculerNbPionsSiFusion(plateau, coordAction, nbPionsNoirs, nbPionsBlancs, joueurNoir);
                break;
            default:
                System.err.println("Action invalide, impossible de calculer le nombre de pions.");
        }

        return nbPions;
    }
    
    /**
     * Calcule le nombre de pions si l'action-mesure "pose" est réalisée en
     * fonction de la couleur du joueur
     *
     * @param plateau le plateau
     * @param coordPose les coordonnées de la case
     * où l'action possible sera réalisée
     * @param nbPionsNoirs nombre de pions noirs avant action-mesure
     * @param nbPionsBlancs nombre de pions blancs avant action-mesure
     * @param joueurNoir vrai s'il s'agit du tour du joueur noir
     * @return un tableau de taille 2 contenant
     * - en [0] : nombre de pions noirs après "activer"
     * - en [1] : nombre de pions blancs après "activer"
     */
    static int[] calculerNbPionsSiPose(Case[][] plateau, Coordonnees coordPose,
            int nbPionsNoirs, int nbPionsBlancs, boolean joueurNoir) {
        //Le nombre de pions actuels sur le plateau
        int[] nbPions = {nbPionsNoirs, nbPionsBlancs};
        //Le nombre de pions qu'il faudra ajouté après action-mesure
        int pionsAjoutes;
        
        if (!plateau[coordPose.ligne][coordPose.colonne].tourPresente) {
            boolean contientTourAdverse = existeTourAdverseAdjacente(plateau, coordPose, joueurNoir);
            if (contientTourAdverse)
                pionsAjoutes = 2;
            else
                pionsAjoutes = 1;
        }
        else
            pionsAjoutes = 1;
        
        if (joueurNoir) {
            nbPions[0] += pionsAjoutes;
        } else {
            nbPions[1] += pionsAjoutes;
        }
        
        return nbPions;
    }
    
    /**
     * Calcule le nombre de pions si l'action-mesure "activer" est réalisée sur
     * un pion d'une case donnée, suppose qu'il y a une tour de la couleur
     * correspondante au joueur.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param nbPionsNoirs nombre de pions noirs avant action-mesure
     * @param nbPionsBlancs nombre de pions blancs avant action-mesure
     * @param joueurNoir vrai s'il s'agit du tour du joueur noir
     * @return un tableau de taille 2 contenant - en [0] : nombre de pions noirs
     * après "activer" - en [1] : nombre de pions blancs après "activer"
     */
    static int[] calculerNbPionsSiActiver(Case[][] plateau, Coordonnees coord, int nbPionsNoirs,
            int nbPionsBlancs, boolean joueurNoir) {
        //Le nombre de pions avant l'action
        int[] nbPions = {nbPionsNoirs, nbPionsBlancs};
        CoordonneesCasesAPortee coordsAPortee = new CoordonneesCasesAPortee(plateau, coord);
        /*On calcule les pions restants après activation.
        On soustrait la hauteur si la suppression de la case est possible*/
        Case caseATester;
        for (Coordonnees coordAPortee : coordsAPortee.coordonnees) {
            if (suppressionPossible(plateau, coordAPortee, coord, joueurNoir)) {
                caseATester = plateau[coordAPortee.ligne][coordAPortee.colonne];
                if (joueurNoir)
                    nbPions[1] -= caseATester.hauteur;
                else
                    nbPions[0] -= caseATester.hauteur;
            }
        }        
        return nbPions;
    }
    
    /**
     * Calcule le nombre de pions si l'action-mesure "activer" est réalisée sur
     * un pion d'une case donnée, suppose qu'il y a une tour de la couleur
     * correspondante au joueur.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param nbPionsNoirs nombre de pions noirs avant action-mesure
     * @param nbPionsBlancs nombre de pions blancs avant action-mesure
     * @param joueurNoir vrai s'il s'agit du tour du joueur noir
     * @return un tableau de taille 2 contenant - en [0] : nombre de pions noirs
     * après "activer" - en [1] : nombre de pions blancs après "fusion"
     */
    static int[] calculerNbPionsSiFusion(Case[][] plateau, Coordonnees coord, int nbPionsNoirs,
            int nbPionsBlancs, boolean joueurNoir) {
        //Le nombre de pions avant l'action
        int[] nbPions = {nbPionsNoirs, nbPionsBlancs};
        int nbPionsAjoutes = 0;
        CoordonneesCasesAPortee coordsAPortee = new CoordonneesCasesAPortee(plateau, coord);
        /*On calcule les pions restants après fusion.
        On soustrait la hauteur si la fusion de la case est possible, puis on l'ajoute avec un maximum de 4*/
        Case caseATester;
        for (Coordonnees coordAPortee : coordsAPortee.coordonnees) {
            if (fusionPossible(plateau, coordAPortee, joueurNoir)) {
                caseATester = plateau[coordAPortee.ligne][coordAPortee.colonne];
                if (joueurNoir)
                    //On "élimine" la tour a portée et on ajoute sa hauteur à la case à fusionner
                    nbPions[0] -= caseATester.hauteur;
                else
                    nbPions[1] -= caseATester.hauteur;
                
                nbPionsAjoutes += calculerPionsSiAjoutHauteur(plateau, coord, caseATester.hauteur);
            }
        }
        //Il faut maintenant ajouter au plateau le nombre de nouveaux pions ajoutés
        int hauteurAAjouter = calculerPionsSiAjoutHauteur(plateau, coord, nbPionsAjoutes);
        if (joueurNoir)
            nbPions[0] += hauteurAAjouter;
        else
            nbPions[1] += hauteurAAjouter;
        
        return nbPions;
    }
    
    /**
     * Ajoute une action donnée possible
     *
     * @param nomAction le caractère de l'action dont on doit tester
     * la validité
     * @return vrai si le nom de l'action est valide
     */
    static boolean verifierNomAction(char nomAction) {
        return nomAction == 'A' || nomAction == 'P' || nomAction == 'F';
    }
    
    /**
     * Ajoute une action donnée possible
     *
     * @param nomAction caractère représentant le nom de l'action possible à réaliser
     * @param plateau le plateau
     * @param actions le tableau contenant les actions possibles
     * @param nbActions le nombre d'actions possibles du tableau (taille)
     * @param coordAction la coordonnée de l'action
     * @param nbPionsNoirs le nombre de pions noirs avant action
     * @param nbPionsBlancs le nombre de pions blancs avant action
     * @param joueurNoir vrai si c'est le tour du joueur noir
     * @return le tableau contenant la nouvelle action possible
     */
    String[] realiserAction(char nomAction, Case[][] plateau, String[] actions, int nbActions,
            boolean joueurNoir, Coordonnees coordAction, int nbPionsNoirs, int nbPionsBlancs) {
        if (verifierNomAction(nomAction)) {
            int[] nbPionsSiAction = calculerNbPionsApresAction(nomAction, plateau, coordAction,
                    nbPionsNoirs, nbPionsBlancs, joueurNoir);

            switch (nomAction) {
                case 'P':
                    actions[nbActions] = chaineActionPose(coordAction,
                            nbPionsSiAction[0], nbPionsSiAction[1]);
                    break;
                case 'A':
                    actions[nbActions] = chaineActionActiver(
                            coordAction, nbPionsSiAction[0], nbPionsSiAction[1]);
                    break;
                case 'F':
                    actions[nbActions] = chaineActionFusion(
                            coordAction, nbPionsSiAction[0], nbPionsSiAction[1]);
                    break;    
                default:
            }
        }
        else
            System.err.println("Action invalide, impossible de calculer le nombre de pions.");
        
        return actions;
    }

    /**
     * Chaîne de caractères correspondant à une action-mesure de pose
     *
     * @param coord coordonnées de la case où poser le pion
     * @param nbPionsNoirs nombre de pions noirs si cette action était jouée
     * @param nbPionsBlancs nombre de pions blancs si cette action était jouée
     * @return la chaîne codant cette action-mesure
     */
    static String chaineActionPose(Coordonnees coord, int nbPionsNoirs,
            int nbPionsBlancs) {
        return "P" + coord.carLigne() + coord.carColonne() + ","
                + nbPionsNoirs + "," + nbPionsBlancs;
    }

    /**
     * Chaîne de caractères correspondant à une action-mesure de activer
     *
     * @param coord coordonnées de la case où un pion peut s'activer
     * @param nbPionsNoirs nombre de pions noirs si cette action était jouée
     * @param nbPionsBlancs nombre de pions blancs si cette action était jouée
     * @return la chaîne codant cette action-mesure
     */
    static String chaineActionActiver(Coordonnees coord, int nbPionsNoirs,
            int nbPionsBlancs) {
        return "A" + coord.carLigne() + coord.carColonne() + ","
                + nbPionsNoirs + "," + nbPionsBlancs;
    }
    
    /**
     * Chaîne de caractères correspondant à une action-mesure de activer
     *
     * @param coord coordonnées de la case où un pion peut s'activer
     * @param nbPionsNoirs nombre de pions noirs si cette action était jouée
     * @param nbPionsBlancs nombre de pions blancs si cette action était jouée
     * @return la chaîne codant cette action-mesure
     */
    static String chaineActionFusion(Coordonnees coord, int nbPionsNoirs,
            int nbPionsBlancs) {
        return "F" + coord.carLigne() + coord.carColonne() + ","
                + nbPionsNoirs + "," + nbPionsBlancs;
    }
}
