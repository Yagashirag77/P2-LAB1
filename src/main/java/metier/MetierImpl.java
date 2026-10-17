package metier;

import dao.IDao;

/** Calcule le temps estimé pour deux tours à partir du chrono fourni par le DAO. */
public class MetierImpl implements IMetier {

    private static final int NOMBRE_TOURS = 2;

    private IDao dao;

    /** Constructeur vide indispensable pour l'instanciation par réflexion. */
    public MetierImpl() {
    }

    /** Permet également de montrer une injection manuelle par constructeur. */
    public MetierImpl(IDao dao) {
        setDao(dao);
    }

    public void setDao(IDao dao) {
        if (dao == null) {
            throw new IllegalArgumentException("Le DAO de chronométrage est obligatoire.");
        }
        this.dao = dao;
    }

    @Override
    public double calcul() {
        if (dao == null) {
            throw new IllegalStateException("Le DAO de chronométrage n'a pas été injecté.");
        }

        double chronoTour = dao.getData();
        if (!Double.isFinite(chronoTour) || chronoTour <= 0.0) {
            throw new IllegalArgumentException("Le chrono doit être un nombre positif et fini.");
        }

        double resultat = chronoTour * NOMBRE_TOURS;
        System.out.println("[METIER-F1] Estimation pour " + NOMBRE_TOURS
                + " tours : " + resultat + " secondes");
        return resultat;
    }
}
