package model;

public class Systeme {
    private String nom;
    private Joueur controleur;
    private int capacite;

    public Systeme(String nom, int capacite) {
        this.nom = nom;
        this.capacite = capacite;
        this.controleur = null; // Aucune occupation par défaut
    }

    public String getNom() {
        return nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public boolean estOccupe() {
        return controleur != null;
    }

    public Joueur getControleur() {
        return controleur;
    }

    public void occuper(Joueur joueur) {
        if (!estOccupe() && capacite > 0) {
            controleur = joueur;
            capacite--;
        }
    }

    public void liberer() {
        controleur = null; // Libération du système
    }
}
