package dao;

import junit.framework.TestCase;

/** Vérifie les données personnelles portées par les deux DAO. */
public class DaoImplementationsTest extends TestCase {

    public void testPremierPilote() {
        DaoImpl chronometre = new DaoImpl("Nora Alaoui", "Rif Racing", 93.2);

        assertEquals("Nora Alaoui", chronometre.getNomPilote());
        assertEquals("Rif Racing", chronometre.getEcurie());
        assertEquals(93.2, chronometre.getData(), 0.001);
    }

    public void testDeuxiemePilote() {
        DaoImpl2 chronometre = new DaoImpl2("Omar Tazi", "Sahara GP", 90.4);

        assertEquals("Omar Tazi", chronometre.getNomPilote());
        assertEquals("Sahara GP", chronometre.getEcurie());
        assertEquals(90.4, chronometre.getData(), 0.001);
    }
}
