package dao;

/** Source abstraite d'un chrono de Formule 1. */
public interface IDao {

    /**
     * Fournit le temps de référence exploitable par le simulateur de course.
     *
     * @return chrono d'un tour en secondes
     */
    double getData();
}
