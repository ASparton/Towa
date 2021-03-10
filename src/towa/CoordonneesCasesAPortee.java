package towa;

/**
 * Classe qui permet d'obtenir et de parcourrir les cases à portée d'une case donnée
 *
 * @author Alexandre Sparton
 */
public final class CoordonneesCasesAPortee {
    
    /*
     * Le tableaux contenant les coordonnées des cases à portée de la case de référence
     */
    Coordonnees[] coordonnees;
    
    /*
     * La coordonnée de référence des cases à portée
     */
    Coordonnees coordonneeRef;
    
    /**
     * Constructeur
     *
     * @param plateau le plateau -> nécessaire à la création de l'objet
     * @param coordRef la coordonnée de référence. Les cases adjacentes seront relatives à celle-ci
     */
    CoordonneesCasesAPortee(Case[][] plateau, Coordonnees coordRef) {
        this.coordonneeRef = coordRef;
        this.coordonnees = obtenirCoordonneesAPortee(plateau, coordRef);
    }
    
    /**
     * Calcule les coordonnée des case sur la même ligne que la coordonnée de référence
     * Suppose que la coordonnée de référence est valide
     *
     * @return un tableau contenant les coordonnees des cases de la même ligne
     */
    Coordonnees[] obtenirCoordonneesCasesLigne() {
        Coordonnees[] coords = new Coordonnees[Coordonnees.NB_COLONNES - 1];
        int nbCoord = 0;

        for (int colonne = 0; colonne < Coordonnees.NB_COLONNES; colonne++) {
            if (colonne != this.coordonneeRef.colonne) {
                coords[nbCoord] = new Coordonnees(this.coordonneeRef.ligne, colonne);
                nbCoord++;
            }
        }
        return coords;
    }

    /**
     * Calcule les coordonnée des case sur la même ligne que la coordonnée de référence,
     * à partir d'une colonne de départ VERS une colonne d'arrivé
     * Suppose que les colonnes et la coordonnée sont valides
     *
     * @param colonneDepart la colonne de départ (représente la première case du tableau renvoyé)
     * @param colonneArrive la colonne d'arrivé (représente la dernière case du tableau renvoyé)
     * @return un tableau contenant les coordonnees des cases de la même ligne
     * que la case de référence, à partir de colonneDepart jusque colonneArrive comprise
     */
    Coordonnees[] obtenirCoordonneesCasesLigneDepuisVers(int colonneDepart, int colonneArrive) {
        Coordonnees[] coords;
        int nbCoord = 0;

        if (colonneArrive > colonneDepart) {
            coords = new Coordonnees[colonneArrive - colonneDepart];
            for (int colonne = colonneDepart; colonne <= colonneArrive; colonne++) {
                if (colonne != this.coordonneeRef.colonne) {
                    coords[nbCoord] = new Coordonnees(this.coordonneeRef.ligne, colonne);
                    nbCoord++;
                }
            }
        } else if (colonneArrive < colonneDepart) {
            coords = new Coordonnees[colonneDepart - colonneArrive];
            for (int colonne = colonneDepart; colonne >= colonneArrive; colonne--) {
                if (colonne != this.coordonneeRef.colonne) {
                    coords[nbCoord] = new Coordonnees(this.coordonneeRef.ligne, colonne);
                    nbCoord++;
                }
            }
        } else {
            coords = new Coordonnees[1];
            coords[0] = new Coordonnees(-1, -1);
        }
        return coords;
    }

    /**
     * Calcule les coordonnée des case sur la même colonne que la coordonnée de référence
     * Suppose que la coordonnée de référence est valide
     *
     * @return un tableau contenant les coordonnees des cases de la même colonne que la coordonnée de référence
     */
    Coordonnees[] obtenirCoordonneesCasesColonne() {
        Coordonnees[] coords = new Coordonnees[Coordonnees.NB_LIGNES - 1];
        int nbCoord = 0;

        for (int ligne = 0; ligne < Coordonnees.NB_LIGNES; ligne++) {
            if (ligne != this.coordonneeRef.ligne) {
                coords[nbCoord] = new Coordonnees(ligne, this.coordonneeRef.colonne);
                nbCoord++;
            }
        }
        return coords;
    }

    /**
     * Calcule les coordonnée des case sur la même colonne que la coordonnée de référence,
     * à partir d'une ligne de départ VERS une ligne d'arrivé Suppose que les lignes et la coordonnée sont valides
     *
     * @param ligneDepart la ligne de départ (représente la première case du tableau renvoyé)
     * @param ligneArrive la ligne d'arrivé (représente la dernière case du tableau renvoyé)
     * 
     * @return un tableau contenant les coordonnees des cases de la même colonne
     * que la case de référence, à partir de ligneDepart jusque ligneArrive comprise
     */
    Coordonnees[] obtenirCoordonneesCasesColonneDepuisVers(int ligneDepart, int ligneArrive) {
        Coordonnees[] coords;
        int nbCoord = 0;

        if (ligneArrive > ligneDepart) {
            coords = new Coordonnees[ligneArrive - ligneDepart];
            for (int ligne = ligneDepart; ligne <= ligneArrive; ligne++) {
                if (ligne != this.coordonneeRef.ligne) {
                    coords[nbCoord] = new Coordonnees(ligne, this.coordonneeRef.colonne);
                    nbCoord++;
                }
            }
        } else if (ligneArrive < ligneDepart) {
            coords = new Coordonnees[ligneDepart - ligneArrive];
            for (int ligne = ligneDepart; ligne >= ligneArrive; ligne--) {
                if (ligne != this.coordonneeRef.ligne) {
                    coords[nbCoord] = new Coordonnees(ligne, this.coordonneeRef.colonne);
                    nbCoord++;
                }
            }
        } else {
            coords = new Coordonnees[1];
            coords[0] = new Coordonnees(-1, -1);
        }
        return coords;
    }

    /**
     * Calcule les coordonnées de la première tour présente sur la ligne ou colonne donnée en paramètre
     *
     * @param plateau le plateau
     * @param ligneColonne la ligne ou la colonne dont on recherche la possible tour à activer
     * @return la coordonnée de la prmière tour s'il y en a une, sinon une coordonnée égale à -1;-1
     */
    Coordonnees obtenirCoordonneesPremiereTour(Case[][] plateau, Coordonnees[] ligneColonne) {
        Coordonnees premiereTour = new Coordonnees(-1, -1);
        int i = 0;
        boolean premiereTourTrouve = false;
        while (!premiereTourTrouve && i < ligneColonne.length) {
            Case caseATest = plateau[ligneColonne[i].ligne][ligneColonne[i].colonne];
            if (caseATest.tourPresente) {
                premiereTourTrouve = true;
                premiereTour = new Coordonnees(ligneColonne[i].ligne, ligneColonne[i].colonne);
            }
            i++;
        }
        return premiereTour;
    }

    /**
     * Permet d'obtenir les coordonées des cases à portée de la case de référence
     *
     * @param plateau le plateau
     * @param coordCaseRef la coordonnée de la case dont on veut obtenir les cases à portée
     * @return un tableau de coordonnées comprenant les coordonnées des cases à portée de la case de référence
     */
    Coordonnees[] obtenirCoordonneesAPortee(Case[][] plateau, Coordonnees coordCaseRef) {
        //Initialisation des tableaux contenant les coordonnées dont on aura besoin
        CoordonneesCasesAdjacentes coordsCaseAdjacentes = new CoordonneesCasesAdjacentes(coordCaseRef);
        Coordonnees[] coordsCaseEst
                = this.obtenirCoordonneesCasesLigneDepuisVers(coordCaseRef.colonne, Coordonnees.NB_COLONNES - 1);
        Coordonnees[] coordsCaseOuest = this.obtenirCoordonneesCasesLigneDepuisVers(coordCaseRef.colonne, 0);
        Coordonnees[] coordsCaseSud
                = this.obtenirCoordonneesCasesColonneDepuisVers(coordCaseRef.ligne, Coordonnees.NB_LIGNES - 1);
        Coordonnees[] coordsCaseNord = this.obtenirCoordonneesCasesColonneDepuisVers(coordCaseRef.ligne, 0);
        //Tableaux rassemblant les 4 directions à tester
        Coordonnees[][] coordsDirections = {coordsCaseOuest, coordsCaseEst, coordsCaseSud, coordsCaseNord};

        //Tableaux des coordonnées des cases à portées (les 4 directions + les cases adjacentes)
        Coordonnees[] coordsATester = new Coordonnees[coordsCaseAdjacentes.coordonnees.length + 4];
        //On y ajoute les cases adjacentes
        int nbCoordsAActiver = coordsCaseAdjacentes.coordonnees.length;
        for (int i = 0; i < nbCoordsAActiver; i++) {
            coordsATester[i] = coordsCaseAdjacentes.coordonnees[i];
        }
        //On initialise les prochaines cases à -1;-1
        for (int i = nbCoordsAActiver; i < coordsATester.length; i++) {
            coordsATester[i] = new Coordonnees(-1, -1);
        }
        //On ajoute "possiblement" les premières cases qui contiennent une tour pour chaque direction
        Coordonnees coordRenvoye;
        for (Coordonnees[] coordsDirection : coordsDirections) {
            //Si le tableau contient -1 c'est qu'il n' y a pas de coordonnées valables dans cette direction
            if (coordsDirection[0].ligne != -1) {
                coordRenvoye = obtenirCoordonneesPremiereTour(plateau, coordsDirection);
                //On teste si la coordonnée est valable
                if (coordRenvoye.ligne != -1) {
                    int indexCoord = MyUtils.rechercheTableauCoordonnees(coordsATester, coordRenvoye);
                    /*Si indexCoord est égale à -1 c'est qu'il n'est pas dans les cases adjacentes
                    On peut alors l'ajouter à nos cases à tester. Permet d'éviter les doublons*/
                    if (indexCoord == -1) {
                        coordsATester[nbCoordsAActiver] = coordRenvoye;
                        nbCoordsAActiver++;
                    }
                }
            }
        }
        /*Le tableau n'étant possiblement pas plein, on en recréer un nouveau de la bonne taille
        pour renvoyer un tableau rempli*/
        Coordonnees[] coordsAPortee = new Coordonnees[nbCoordsAActiver];
        System.arraycopy(coordsATester, 0, coordsAPortee, 0, coordsAPortee.length);

        return coordsAPortee;
    }
}
