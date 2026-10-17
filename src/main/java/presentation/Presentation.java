package presentation;

import dao.DaoImpl;
import dao.DaoImpl2;
import dao.IDao;
import metier.IMetier;
import metier.MetierImpl;

/** Montre l'injection manuelle avec chacune de nos sources F1. */
public class Presentation {

    private final IMetier metier;

    public Presentation(IMetier metier) {
        this.metier = metier;
    }

    public void afficherResultat() {
        System.out.println("Résultat = " + metier.calcul() + " secondes");
    }

    public static void main(String[] a) {
        IDao chronometrageCircuit = new DaoImpl();
        IMetier strategieLocale = new MetierImpl(chronometrageCircuit);
        new Presentation(strategieLocale).afficherResultat();

        IDao telemetrieDistante = new DaoImpl2();
        IMetier strategieTelemetrie = new MetierImpl(telemetrieDistante);
        new Presentation(strategieTelemetrie).afficherResultat();
    }
}
