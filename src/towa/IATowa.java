/**
* Fonction exécutée lorsque c'est à notre tour de jouer. Cette fonction
* renvoie l'action que l'on choisit de jouer.
*
* @param plateau le plateau de jeu
* @param nbToursJeu numéro du tour de jeu
* @return l'action que l'on souhaite jouer
* @throws IOException exception sur les entrées / sorties
*/
String jouer(Case[][] plateau, int nbToursJeu) throws IOException {
    String actionJouee = "ABANDON";
    
    if (this.nbActionsAdverses > 0) {
        String derActionAdverse = this.actionsAdverse[this.nbActionsAdverses - 1];
        Coordonnees coordDerActionAdverse =
                Coordonnees.depuisCars(derActionAdverse.charAt(1), derActionAdverse.charAt(2));
        if (plateauContientTourAllieeHauteur4(plateau, this.estNoir)) {
            for (int ligne = 0; ligne < TAILLE; ligne++) {
                for (int colonne = 0; colonne < TAILLE; colonne++) {
                    Coordonnees coordCourante = new Coordonnees(ligne, colonne);
                    if (JoueurTowa.caseEstMemeCouleurJoueur(
                            plateau[coordCourante.ligne][coordCourante.colonne], this.estNoir)) {
                        if (tourEnnemieAdjHauteur3(plateau, coordCourante, this.estNoir)) {
                            actionJouee = obtenirActionActivation(coordCourante);
                            return actionJouee;
                        } else {
                            actionJouee = obtenirPoserPlusProche(plateau, coordDerActionAdverse, 0);
                        }
                    }
                    else {
                        actionJouee = obtenirPoserPlusProche(plateau, coordDerActionAdverse, 0);
                    }
                }
            }
        } else if (advDejaPoseMemeEndroit(derActionAdverse)
                || derniereActionAdverseFusion()) {
            /*On obtient d'abord la coordonnée la plus proche du coin
        par rapport à la coordonnée de la dernière action adverse*/
            Coordonnees caseAdjPlusProche = obtenirCoordAdjPlusProche(coordDerActionAdverse);
            if (fusionInteressante(plateau, caseAdjPlusProche, this.estNoir)) {
                actionJouee = obtenirActionFusion(caseAdjPlusProche);
            } else {
                actionJouee = obtenirPoserPlusProche(plateau, coordDerActionAdverse, 0);
            }
        } else {
            actionJouee = obtenirPoserPlusProche(plateau, coordDerActionAdverse, 0);
        }
    } else {
        actionJouee = "PaA";
    }
    return actionJouee;
}

/**
 * Détermine si la dernière action jouée par l'adversaire est une fusion.
 *
 * @return vrai si la dernière action jouée par l'adversaire est une fusion
 */
boolean derniereActionAdverseFusion() {
    return 'F' == this.actionsAdverse[nbActionsAdverses - 1].charAt(0);
}

/**
 * Détermine si le plateau contient une tour alliée de hauteur 4.
 *
 * @param plateau le plateau
 * @param courantEstNoir vrai si le joueur courant est de couleur noir
 * @return si le plateau contient une tour alliée de hauteur 4
 */
boolean plateauContientTourAllieeHauteur4(Case[][] plateau, boolean courantEstNoir) {
    for (int ligne = 0; ligne < TAILLE; ligne++) {
        for (int colonne = 0; colonne < TAILLE; colonne++) {
            Case caseCourante = plateau[ligne][colonne];
            if (caseCourante.tourPresente &&
                    JoueurTowa.caseEstMemeCouleurJoueur(caseCourante, courantEstNoir) &&
                    caseCourante.hauteur == 4)
                return true;
        }
    }
    return false;
}

/**
 * Détermine si le joueur adverse a déjà posé sur une case où il avait déjà posé auparavant
 * 
 * @param actionAdverse la dernière action du joueur
 * @return vrai si le joueur a déjà posé sur la même case auparavant
 */
boolean advDejaPoseMemeEndroit(String actionAdverse){
    String actionJouees;
    for (int i = 0; i < this.nbActionsAdverses; i++) {
        actionJouees = this.actionsAdverse[i];
        if (actionJouees.charAt(0) == 'P' && actionJouees.equals(actionAdverse)) {
            return true;
        }
    }
    return false;
}

/**
 * Détermine si une case adjactente ennemie à une case allié de hauteur 4 est 
 * de hauteur 3
 * @param plateau le plateau
 * @param coordAllies les coordonnees de notre case
 * @param estNoir vrai si le joueur courant est noir
 * @return vrai si c'est le cas 
 */
boolean tourEnnemieAdjHauteur3(Case[][] plateau, Coordonnees coordAllies, boolean estNoir){
    CoordonneesCasesAdjacentes coordAdjacente = new CoordonneesCasesAdjacentes(coordAllies);
    Coordonnees laCoord;
    for (int i = 0; i < coordAdjacente.coordonnees.length; i++) {
        laCoord=coordAdjacente.coordonnees[i];
        if(plateau[laCoord.ligne][laCoord.colonne].estNoire != estNoir &&
                (plateau[laCoord.ligne][laCoord.colonne].hauteur == 3 ||
                 plateau[laCoord.ligne][laCoord.colonne].hauteur == 2) &&
                plateau[coordAllies.ligne][coordAllies.colonne].hauteur == 4){
            return true;
        }
    }
    return false;
}

/**
 * Détermine si il est intéressant de fusioner
 * @param plateau le plateau
 * @param coordAllies les coordonnees de notre case
 * @param estNoir vrai si le joueur courant est de couleur noir
 * @return vrai si il n'y a au maximum qu'un case a fusioner 
 */
  boolean fusionInteressante(Case[][] plateau, Coordonnees coordAllies, boolean estNoir) {
    CoordonneesCasesAPortee coordAPortee = new CoordonneesCasesAPortee(plateau, coordAllies);
    Coordonnees laCoord;
    Case caseTest = plateau[coordAllies.ligne][coordAllies.colonne];
    if (caseTest.tourPresente && JoueurTowa.caseEstMemeCouleurJoueur(caseTest, estNoir)) {
        for (int i = 0; i < coordAPortee.coordonnees.length; i++) {
            laCoord = coordAPortee.coordonnees[i];
            if (plateau[laCoord.ligne][laCoord.colonne].hauteur
                    > plateau[coordAllies.ligne][coordAllies.colonne].hauteur
                    && plateau[laCoord.ligne][laCoord.colonne].estNoire
                    != caseTest.estNoire) {
                return true;
            }
        }
    }
    return false;
}

/**
 * Renvoie les coordonnées des cases adjacentes libres à la coordonnée donnée en paramètre
 * 
 * @param plateau le plateau
 * @param coord la coordonnée en question
 * @return les coordonnées des cases adjacentes libres à coord, renvoie une coordonnée(-1;-1) s'il n'y en a pas
 */
Coordonnees[] obtenirCoordonneesAdjLibres(Case[][] plateau, Coordonnees coord) {
    Coordonnees[] coordsAdjLibres = new Coordonnees[8];
    coordsAdjLibres[0] = new Coordonnees(-1, -1);
    int nbCoordsAdjLibres = 0;
    //On parcour les cases adjacentes, et dès qu'il y en a une de libre on la rajoute au tableau
    CoordonneesCasesAdjacentes coordsAdj = new CoordonneesCasesAdjacentes(coord);
    for (Coordonnees coordAdjCourante : coordsAdj.coordonnees) {
        if (!plateau[coordAdjCourante.ligne][coordAdjCourante.colonne].tourPresente) {
            coordsAdjLibres[nbCoordsAdjLibres] = coordAdjCourante;
            nbCoordsAdjLibres++;
        }
    }
    //Le tableau n'étant possiblement pas plein, on en recréer à la bonne taille
    Coordonnees[] coords = new Coordonnees[nbCoordsAdjLibres];
    for (int i = 0; i < coords.length; i++) {
        coords[i] = coordsAdjLibres[i];
    }
    return coords;
}

/**
 * Renvoie la distance séparant deux coordonnées (sous forme distanceLigne + distanceColonne)
 * 
 * @param coord1 la première coordonnée
 * @param coord2 la seconde coordonnée
 * @return la distance entre coord1 et coord2
 */
int obtenirDistance(Coordonnees coord1, Coordonnees coord2) {
    int distanceLigne = 0, distanceColonne = 0;
    
    //On obtient la distance entre les colonnes
    if (coord2.colonne > coord1.colonne)
        distanceColonne = coord2.colonne - coord1.colonne;
    else if (coord2.colonne < coord1.colonne)
        distanceColonne = coord1.colonne - coord2.colonne;
    //On obtient la distance entre les lignes
    if (coord2.ligne > coord1.ligne)
        distanceLigne = coord2.ligne - coord1.ligne;
    else if (coord2.ligne < coord1.ligne)
        distanceLigne = coord1.ligne - coord2.ligne;
    
    return distanceLigne + distanceColonne;
}

/**
 * Renvoie la coordonnée du coin du plateau le plus proche de la coordonnée donnée en paramètre
 * 
 * @param coord la coordonnée dont on veut obtenir le coin le plus proche
 * @return  la coordonnée du coin du plateau le plus proche de coord
 */
Coordonnees obtenirCoordCoinPlusProche(Coordonnees coord) {
    int[] distanceCoins = new int[4];
    distanceCoins[0] = obtenirDistance(coord, new Coordonnees(0, 0));
    distanceCoins[1] = obtenirDistance(coord, new Coordonnees(0, 15));
    distanceCoins[2] = obtenirDistance(coord, new Coordonnees(15, 0));
    distanceCoins[3] = obtenirDistance(coord, new Coordonnees(15, 15));
    int distanceMin = MyUtils.obtenirMinimumEntier(distanceCoins);
    
    Coordonnees coordCoinPlusProche = new Coordonnees(-1, -1);
    for (int i = 0; i < distanceCoins.length; i++) {
        if (distanceCoins[i] == distanceMin) {
            switch(i) {
                case 0:
                    coordCoinPlusProche = new Coordonnees(0, 0);
                    break;
                case 1:
                    coordCoinPlusProche = new Coordonnees(0, 15);
                    break;
                case 2:
                    coordCoinPlusProche = new Coordonnees(15, 0);
                    break;
                case 3:
                    coordCoinPlusProche = new Coordonnees(15, 15);
                    break;
                default:
                    coordCoinPlusProche = new Coordonnees(-1, -1);
            }
        }
    }
    return coordCoinPlusProche;
}

/**
 * Renvoie la coordonnée du tableau la plus proche de la coordonnée donnée en paramètre
 * 
 * @param coords le tableau de coordonnées
 * @param coordRef la coordonnée de référence
 * @return la coordonnée de coords la plus proche de coordRef
 */
Coordonnees obtenirCoordPlusProche(Coordonnees[] coords, Coordonnees coordRef) {
    Coordonnees coordPlusProche = new Coordonnees(-1, -1);
    //On obtient d'abord les distances par rapport à chaque case
    int[] distancesCoords = new int[coords.length];
    for (int i = 0; i < coords.length; i++)
        distancesCoords[i] = obtenirDistance(coords[i], coordRef);
    //On obtient ensuite la case du tableau qui correspond à la distance minimum retirée
    int distanceMin = MyUtils.obtenirMinimumEntier(distancesCoords);
    for (int i = 0; i < coords.length; i++) {
        if (distancesCoords[i] == distanceMin)
            coordPlusProche = coords[i];
    }
    return coordPlusProche;
}

String obtenirPoserPlusProche(Case[][] plateau, Coordonnees coordRef, int compteur) {
    String action = new String();
    if (compteur == nbActionsAdverses) {
        for (int ligne = 0; ligne < TAILLE; ligne++) {
            for (int colonne = 0; colonne < TAILLE; colonne++) {
                Coordonnees coordCourante = new Coordonnees(ligne, colonne);
                if (!plateau[coordCourante.ligne][coordCourante.colonne].tourPresente) {
                    action = "P" + coordCourante.carLigne() + coordCourante.carColonne();
                }
            }
        }
    }
    else {
        //On obtient d'abord les coordonnées de la dernière action adverse
        if (plateau[coordRef.ligne][coordRef.colonne].tourPresente
                && !JoueurTowa.caseEstMemeCouleurJoueur(plateau[coordRef.ligne][coordRef.colonne], this.estNoir)) {
            Coordonnees[] coordsAdjLibres = obtenirCoordonneesAdjLibres(plateau, coordRef);
            if (coordsAdjLibres[0].ligne != -1) {
                Coordonnees coordCoinPlusProche = obtenirCoordCoinPlusProche(coordRef);
                Coordonnees coordCaseLibrePlusProche = obtenirCoordPlusProche(coordsAdjLibres, coordCoinPlusProche);
                action = "P" + coordCaseLibrePlusProche.carLigne() + coordCaseLibrePlusProche.carColonne();
            } else {
                
                Coordonnees coordAction
                        = new Coordonnees(this.actionsAdverse[compteur].charAt(1),
                                this.actionsAdverse[compteur].charAt(2));
                obtenirPoserPlusProche(plateau, coordAction, compteur++);
            }
        } else {
            Coordonnees coordAction
                        = new Coordonnees(this.actionsAdverse[compteur].charAt(1),
                                this.actionsAdverse[compteur].charAt(2));
                obtenirPoserPlusProche(plateau, coordAction, compteur++);
        }
    }
    return action;
}

Coordonnees obtenirCoordAdjPlusProche(Coordonnees coordRef) {
    CoordonneesCasesAdjacentes coordsAdj = new CoordonneesCasesAdjacentes(coordRef);
    Coordonnees coordCoinPlusProche = obtenirCoordCoinPlusProche(coordRef);
    return obtenirCoordPlusProche(coordsAdj.coordonnees, coordCoinPlusProche);
}