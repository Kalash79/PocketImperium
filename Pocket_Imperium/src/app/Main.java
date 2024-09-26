package app;

import model.*;
import view.VueJeu;
import controller.ControleurJeu;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Plateau plateau = new Plateau(); // Crée un plateau 3x3
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur("Joueur A"));
        joueurs.add(new Joueur("Joueur B"));
        joueurs.add(new Joueur("Joueur C"));
        
        VueJeu vue = new VueJeu();
        ControleurJeu controleur = new ControleurJeu(plateau, joueurs, vue);
        
        controleur.demarrerJeu();
    }
}
