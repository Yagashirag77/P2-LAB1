package dao;

/** Deuxième source permettant de changer de pilote sans modifier le métier. */
public class DaoImpl2 implements IDao {

    private final String nomPilote;
    private final String ecurie;
    private final double chronoTourSecondes;

    public DaoImpl2() {
        this("Salma Idrissi", "Maghreb Motorsport", 91.8);
    }

    public DaoImpl2(String nomPilote, String ecurie, double chronoTourSecondes) {
        this.nomPilote = nomPilote;
        this.ecurie = ecurie;
        this.chronoTourSecondes = chronoTourSecondes;
    }

    @Override
    public double getData() {
        System.out.println("[DAO-F1] Chrono de " + nomPilote + " - " + ecurie
                + " : " + chronoTourSecondes + " secondes");
        return chronoTourSecondes;
    }

    public String getNomPilote() {
        return nomPilote;
    }

    public String getEcurie() {
        return ecurie;
    }
}
