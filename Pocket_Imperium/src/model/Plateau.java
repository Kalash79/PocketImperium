package model;

public class Plateau {
    private Secteur[][] secteurs;

    public Plateau() {
        secteurs = new Secteur[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Niveau 3 est au centre, niveaux 1 et 2 ailleurs
                if (i == 1 && j == 1) {
                    secteurs[i][j] = new Secteur(3); // Un seul secteur de niveau 3
                } else {
                    secteurs[i][j] = new Secteur((i + j) % 2 + 1); // Niveaux 1 et 2 alternés
                }
            }
        }
    }

    public Secteur getSecteur(int index) {
        int row = index / 3;
        int col = index % 3;
        return secteurs[row][col];
    }
}
