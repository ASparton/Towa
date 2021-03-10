package towa;

/**
 * Classe qui permet d'obtenir et de parcourrir les 3, 5, ou 8 cases adjacentes d'une case donnée
 * 
 * @author Alexandre Sparton
 */
public final class CoordonneesCasesAdjacentes {
    
    /*
     * Tableaux contenant les coordonnées des cases adjacentes
     */
    Coordonnees[] coordonnees;
    
    /*
     * La coordonnée de référence des cases adjacentes
     */
    Coordonnees coordonneeRef;
    
    /**
     * Constructeur
     *
     * @param coordRef la coordonnée de référence. Les cases adjacentes seront relatives à celle-ci
     */
    CoordonneesCasesAdjacentes(Coordonnees coordRef) {
        this.coordonneeRef = coordRef;
        this.coordonnees = obtenirCoordonneesCasesAdjacentes();
    }
    
    /**
     * Calcule les coordonnée valide des case adjacentes à une coordonnée de
     * référence en partant d'en haut à gauche
     * Suppose que la coordonnée est valide
     *
     * @return un tableau contenant les coordonnees des cases adjacentes
     */
    Coordonnees[] obtenirCoordonneesCasesAdjacentes() {
        Coordonnees[] coordValides = new Coordonnees[9];
        int nbCoordonneesValides = 0;
        //Ajout des coordonées adjacentes valides
        Coordonnees coordActuel = new Coordonnees(this.coordonneeRef.ligne - 1, this.coordonneeRef.colonne - 1);
        boolean coordsEqual;
        while (coordActuel.ligne <= this.coordonneeRef.ligne + 1) {
            coordsEqual = (coordActuel.ligne == this.coordonneeRef.ligne) &&
                    (coordActuel.colonne == this.coordonneeRef.colonne);
            /*Si la coordonnée adjacente que l'on teste est dans le plateau,
            alors on ajoute celle ci dans les coordonnées valides*/
            if (JoueurTowa.coordonneeEstDansPlateau(coordActuel) && !coordsEqual) {
                coordValides[nbCoordonneesValides] = new Coordonnees(coordActuel.ligne, coordActuel.colonne);
                nbCoordonneesValides++;
            }
            //Après chaque teste, on passe à la coordonnée adjacente suivante
            coordActuel.colonne++;
            if (coordActuel.colonne > this.coordonneeRef.colonne + 1) {
                coordActuel.colonne = this.coordonneeRef.colonne - 1;
                coordActuel.ligne++;
            }
        }
        /*Le tableau n'étant possiblement pas plein,
        on recréer un tableau avec la bonne taille*/
        Coordonnees[] coords = new Coordonnees[nbCoordonneesValides];
        System.arraycopy(coordValides, 0, coords, 0, nbCoordonneesValides);
        return coords;
    }
}
