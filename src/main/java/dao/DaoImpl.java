package dao;

/** Première source de chronométrage utilisée dans notre démonstration F1. */
public class DaoImpl implements IDao {

    private final String nomPilote;
    private final String ecurie;
    private final double chronoTourSecondes;

    public DaoImpl() {
        this("Yassine Amrani", "Atlas Racing", 92.6);
    }

    public DaoImpl(String nomPilote, String ecurie, double chronoTourSecondes) {
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
