package view;

import model.*;

public class VueJeu {
    public void afficherPlateau(Plateau plateau) {
        System.out.println("État du plateau :");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Secteur secteur = plateau.getSecteur(i * 3 + j);
                System.out.print("Secteur " + (i * 3 + j) + " (Niveau " + secteur.getNiveau() + "): ");
                System.out.print("Vaisseaux: " + secteur.getVaisseaux().size() + ", Systèmes: ");
                for (Systeme systeme : secteur.getSystemes()) {
                    System.out.print(systeme.getNom() + (systeme.estOccupe() ? " (Occupé)" : " (Libre)") + " ");
                }
                System.out.println();
            }
        }
    }

    public void afficherActionJoueur(String nomJoueur, String action) {
        System.out.println(nomJoueur + " a choisi l'action : " + action);
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
