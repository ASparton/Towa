package towa;

/**
 * Classe contenant des fonctions utiles aux projets
 * 
 * @author spart
 */
public class MyUtils {
    
    /**
     * Créer un nouveau tableau de Coordonnees 
     * qui correspond à la fusion de tab1 et tab2
     *
     * @param tab1 le premier tableau de coordonnées
     * @param tab2 le second tableau de coordonnées
     * @return un tableau de coordonnees qui correspond
     * à l'ajout du tableau 1 et du tableau 2
     */
    static Coordonnees[] fusionTableauxCoordonnees(
            Coordonnees[] tab1, Coordonnees[] tab2) {
        
        Coordonnees[] fusion = new Coordonnees[tab1.length + tab2.length];
        int nbElements = 0;
        for (Coordonnees coord : tab1) {
            fusion[nbElements] = coord;
            nbElements++;
        }
        for (Coordonnees coord : tab2) {
            fusion[nbElements] = coord;
            nbElements++;
        }
        return fusion;
    }
    
    /**
     * Créer un nouveau tableau de Coordonnees 
     * tab mais en supprimant les possibles doublons element
     *
     * @param tab le tableau de coordonnées
     * @param element le possible doublon à supprimer
     * @return un tableau de coordonnees qui correspond
     * à tab mais sans doublons
     */
    static Coordonnees[] supprimerDoublonsCoordonnees(
            Coordonnees[] tab, Coordonnees element) {
        
        Coordonnees[] sansDoublonTailleEgale = new Coordonnees[tab.length];
        int nbElements = 0;
        boolean dejaApparu = false;
        
        /*On parcour le tableau, et si l'élément est déjà vu une fois
        alors on le copie pas*/
        for (Coordonnees coord : tab) {
            if (coord.ligne == element.ligne && coord.colonne == element.colonne) {
                if (!dejaApparu) {
                    dejaApparu = true;
                    sansDoublonTailleEgale[nbElements] = coord;
                    nbElements++;
                }
            }
            else {
                sansDoublonTailleEgale[nbElements] = coord;
                nbElements++;
            }
        }
        //On refait un tableau avec la bonne taille
        Coordonnees[] sansDoublon = new Coordonnees[nbElements];
        System.arraycopy(sansDoublonTailleEgale, 0, sansDoublon, 0, nbElements);
        return sansDoublon;
    }
    
    /**
     * Recherche une coordonnée dans un tableau de coordonnées,
     * Si l'élément est présent, renvoi le PREMIER index et -1 sinon.
     *
     * @param coords le tableau dans lequel on recherche la coordonnée
     * @param coordAChercher la coordonnée que l'on cherche dans le tableau
     * @return le PREMIER index de coordAChercher, et -1 s'il n'est pas présent
     */
    static int rechercheTableauCoordonnees(Coordonnees[] coords, Coordonnees coordAChercher) {
        int index = -1;
        boolean trouve = false;
        int i = 0;
        while(!trouve && i < coords.length) {
            if (coords[i].ligne == coordAChercher.ligne && coords[i].colonne == coordAChercher.colonne) {
                index = i;
                trouve = true;
            }
            i++;
        }
        return index;
    }
}
