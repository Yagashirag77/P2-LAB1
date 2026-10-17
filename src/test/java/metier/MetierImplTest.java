package metier;

import dao.IDao;
import junit.framework.TestCase;

/** Vérifie le calcul simple demandé et ses validations indispensables. */
public class MetierImplTest extends TestCase {

    public void testCalculPourDeuxTours() {
        MetierImpl metier = new MetierImpl(chronoFixe(92.6));

        assertEquals(185.2, metier.calcul(), 0.001);
    }

    public void testChronoNegatifRefuse() {
        verifierChronoInvalide(-1.0);
    }

    public void testChronoNonNumeriqueRefuse() {
        verifierChronoInvalide(Double.NaN);
    }

    public void testCalculSansDaoRefuse() {
        try {
            new MetierImpl().calcul();
            fail("Le calcul devait refuser l'absence de DAO.");
        } catch (IllegalStateException attendu) {
            assertTrue(attendu.getMessage().contains("injecté"));
        }
    }

    public void testInjectionNulleRefusee() {
        try {
            new MetierImpl(null);
            fail("Un DAO nul devait être refusé.");
        } catch (IllegalArgumentException attendu) {
            assertTrue(attendu.getMessage().contains("obligatoire"));
        }
    }

    private void verifierChronoInvalide(double chrono) {
        try {
            new MetierImpl(chronoFixe(chrono)).calcul();
            fail("Le chrono devait être refusé : " + chrono);
        } catch (IllegalArgumentException attendu) {
            assertTrue(attendu.getMessage().contains("positif"));
        }
    }

    private IDao chronoFixe(final double chrono) {
        return new IDao() {
            @Override
            public double getData() {
                return chrono;
            }
        };
    }
}
