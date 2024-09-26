package model;

import java.util.ArrayList;
import java.util.List;

public class Flotte {
    private List<Vaisseau> vaisseaux = new ArrayList<>();

    public void ajouterVaisseau(Vaisseau vaisseau) {
        vaisseaux.add(vaisseau);
    }

    public void retirerVaisseau(Vaisseau vaisseau) {
        vaisseaux.remove(vaisseau);
    }

    public List<Vaisseau> getVaisseaux() {
        return new ArrayList<>(vaisseaux); // Retourne une copie pour éviter les modifications externes
    }
}
