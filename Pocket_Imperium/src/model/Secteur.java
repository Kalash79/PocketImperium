package model;

import java.util.ArrayList;
import java.util.List;

public class Secteur {
    private int niveau;
    private List<Vaisseau> vaisseaux;
    private List<Systeme> systemes;

    public Secteur(int niveau) {
        this.niveau = niveau;
        this.vaisseaux = new ArrayList<>();
        this.systemes = new ArrayList<>();

        // Ajout des systèmes en fonction du niveau
        if (niveau == 3) {
            systemes.add(new Systeme("Système 1", 4)); // Système de niveau 3
        } else {
            systemes.add(new Systeme("Système 1", 2)); // Systèmes de niveaux 1 et 2
            systemes.add(new Systeme("Système 2", 2));
        }
    }

    public int getNiveau() {
        return niveau;
    }

    public List<Vaisseau> getVaisseaux() {
        return new ArrayList<>(vaisseaux); // Retourne une copie pour éviter les modifications externes
    }

    public void ajouterVaisseau(Vaisseau vaisseau) {
        vaisseaux.add(vaisseau);
    }

    public void retirerVaisseau(Vaisseau vaisseau) {
        vaisseaux.remove(vaisseau);
    }

    public List<Systeme> getSystemes() {
        return new ArrayList<>(systemes); // Retourne une copie pour éviter les modifications externes
    }
}
