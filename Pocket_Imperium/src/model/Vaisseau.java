package model;

public class Vaisseau {
    private Joueur controleur;

    public Vaisseau(Joueur controleur) {
        this.controleur = controleur;
    }

    public Joueur getControleur() {
        return controleur;
    }
}
