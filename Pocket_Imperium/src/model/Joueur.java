package model;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private String nom;
    private List<Vaisseau> flotte;
    private int points;

    public Joueur(String nom) {
        this.nom = nom;
        this.flotte = new ArrayList<>();
        this.points = 0;
    }

    public String getNom() {
        return nom;
    }

    public List<Vaisseau> getFlotte() {
        return new ArrayList<>(flotte); // Retourne une copie pour éviter les modifications externes
    }

    public void ajouterVaisseau(Vaisseau vaisseau) {
        flotte.add(vaisseau);
    }

    public void retirerVaisseau(Vaisseau vaisseau) {
        flotte.remove(vaisseau);
    }

    public int getPoints() {
        return points;
    }

    public void ajouterPoints(int points) {
        this.points += points;
    }
}
