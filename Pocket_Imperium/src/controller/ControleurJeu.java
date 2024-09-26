package controller;

import java.util.List;
import java.util.Scanner;
import view.VueJeu;
import model.*;

public class ControleurJeu {
    private Plateau plateau;
    private List<Joueur> joueurs;
    private VueJeu vue;
    private Scanner scanner;
    private int rounds;

    public ControleurJeu(Plateau plateau, List<Joueur> joueurs, VueJeu vue) {
        this.plateau = plateau;
        this.joueurs = joueurs;
        this.vue = vue;
        this.scanner = new Scanner(System.in);
        this.rounds = 0;
    }

    public void demarrerJeu() {
        while (rounds < 9) {
            for (Joueur joueur : joueurs) {
                vue.afficherPlateau(plateau);
                String action = obtenirActionJoueur(joueur);
                vue.afficherActionJoueur(joueur.getNom(), action);
                executerAction(joueur, action);
                vue.afficherPlateau(plateau);

                // Vérification de la fin de jeu seulement si ce n'est pas le premier tour
                if (rounds > 0) {
                    verifierFinJeu();
                }
            }
            rounds++;
            vue.afficherMessage("Fin du tour " + rounds + "\n");
        }
    }


    private String obtenirActionJoueur(Joueur joueur) {
        // La première action du premier round est limitée à "Etendre"
        if (rounds == 0) {
            System.out.print(joueur.getNom() + ", entrez votre action (Etendre) : ");
            return "Etendre"; // Action forcée
        } else {
            System.out.print(joueur.getNom() + ", entrez votre action (Etendre, Explorer, Exterminer) : ");
            return scanner.nextLine().trim();
        }
    }

    private void executerAction(Joueur joueur, String action) {
        switch (action.toLowerCase()) {
            case "etendre":
                etendre(joueur);
                break;
            case "explorer":
                explorer(joueur);
                break;
            case "exterminer":
                exterminer(joueur);
                break;
            default:
                vue.afficherMessage("Action inconnue. Veuillez essayer à nouveau.");
                break;
        }
    }

    private void etendre(Joueur joueur) {
        System.out.print("Choisissez un secteur à étendre (index de 0 à 8) : ");
        int index = scanner.nextInt();
        scanner.nextLine();
        Secteur secteur = plateau.getSecteur(index);
        
        // Vérification de la capacité du système avant d'ajouter les vaisseaux
        Systeme systemeChoisi = secteur.getSystemes().get(0); // On peut choisir un système spécifique ou random
        if (!systemeChoisi.estOccupe() && systemeChoisi.getCapacite() >= 2) {
            for (int i = 0; i < 2; i++) { // Ajouter deux vaisseaux
                Vaisseau vaisseau = new Vaisseau(joueur);
                secteur.ajouterVaisseau(vaisseau);
                joueur.ajouterVaisseau(vaisseau);
            }
            systemeChoisi.occuper(joueur); // On occupe le système avec le joueur
            vue.afficherMessage(joueur.getNom() + " a ajouté 2 vaisseaux dans le système 0 du secteur " + index + ".");
        } else {
            vue.afficherMessage("Impossible d'ajouter 2 vaisseaux dans le système 0 du secteur " + index + " (déjà occupé ou capacité insuffisante).");
        }
    }

    private void explorer(Joueur joueur) {
        System.out.print("Choisissez un secteur à explorer (index de 0 à 8) : ");
        int index = scanner.nextInt();
        scanner.nextLine();
        Secteur secteur = plateau.getSecteur(index);

        if (!secteur.getVaisseaux().isEmpty()) {
            System.out.print("Choisissez un système de départ pour déplacer vos vaisseaux (index de 0 à 2) : ");
            int systemeDepartIndex = scanner.nextInt();
            scanner.nextLine();

            if (systemeDepartIndex >= 0 && systemeDepartIndex < 3) {
                Systeme systemeDepart = secteur.getSystemes().get(systemeDepartIndex);
                if (systemeDepart.estOccupe() && systemeDepart.getControleur() == joueur) {
                    System.out.print("Choisissez un système d'arrivée (index de 0 à 2) : ");
                    int systemeArriveeIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (systemeArriveeIndex >= 0 && systemeArriveeIndex < 3) {
                        Systeme systemeArrivee = secteur.getSystemes().get(systemeArriveeIndex);
                        if (!systemeArrivee.estOccupe() && systemeArrivee.getCapacite() > 0) {
                            Vaisseau vaisseau = new Vaisseau(joueur);
                            systemeArrivee.occuper(joueur); // occuper le système
                            systemeDepart.liberer(); // libérer le système de départ
                            joueur.ajouterVaisseau(vaisseau);
                            vue.afficherMessage(joueur.getNom() + " a déplacé ses vaisseaux vers le système " + systemeArriveeIndex + " dans le secteur " + index + ".");
                        } else {
                            vue.afficherMessage("Le système d'arrivée est occupé ou plein.");
                        }
                    } else {
                        vue.afficherMessage("Index de système d'arrivée invalide.");
                    }
                } else {
                    vue.afficherMessage("Vous ne contrôlez pas le système de départ.");
                }
            } else {
                vue.afficherMessage("Index de système de départ invalide.");
            }
        } else {
            vue.afficherMessage("Aucun vaisseau à explorer dans le secteur " + index + ".");
        }
    }


    private void exterminer(Joueur joueur) {
        System.out.print("Choisissez un secteur pour exterminer (index de 0 à 8) : ");
        int index = scanner.nextInt();
        scanner.nextLine();
        Secteur secteur = plateau.getSecteur(index);

        List<Vaisseau> vaisseaux = secteur.getVaisseaux();
        if (!vaisseaux.isEmpty()) {
            // Logique d'extermination ici
            System.out.print("Choisissez un système à exterminer (index de 0 à 2) : ");
            int systemeIndex = scanner.nextInt();
            scanner.nextLine();
            Systeme systemeCible = secteur.getSystemes().get(systemeIndex);

            if (systemeCible.estOccupe() && systemeCible.getControleur() != joueur) {
                // On peut exterminer le vaisseau
                Vaisseau vaisseauExtermine = vaisseaux.get(0); // ou la logique pour choisir le vaisseau à exterminer
                secteur.retirerVaisseau(vaisseauExtermine);
                vue.afficherMessage(joueur.getNom() + " a exterminé un vaisseau dans le secteur " + index + ".");
            } else {
                vue.afficherMessage("Aucun vaisseau à exterminer ou vous ne pouvez pas attaquer ce système.");
            }
        } else {
            vue.afficherMessage("Aucun vaisseau à exterminer dans le secteur " + index + ".");
        }
    }


    private void verifierFinJeu() {
        // Vérifiez si ce n'est pas le premier tour
        if (rounds == 0) {
            return; // Ignore la vérification de fin de jeu au premier tour
        }

        for (Joueur joueur : joueurs) {
            if (joueur.getFlotte().isEmpty()) {
                vue.afficherMessage(joueur.getNom() + " a été exterminé ! Le jeu est terminé.");
                System.exit(0);
            }
        }

        if (rounds >= 9) {
            vue.afficherMessage("Le jeu est terminé après 9 tours !");
            System.exit(0);
        }
    }

}
