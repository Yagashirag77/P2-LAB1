package presentation;

import junit.framework.TestCase;
import metier.IMetier;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/** Contrôle l'assemblage dynamique de l'exemple F1 et ses erreurs. */
public class Presentation2Test extends TestCase {

    public void testAssemblageAvecLeDaoDistant() throws Exception {
        Path configuration = creerConfiguration("dao.DaoImpl2", "metier.MetierImpl");
        try {
            IMetier metier = Presentation2.construireMetier(configuration);
            assertEquals(183.6, metier.calcul(), 0.001);
        } finally {
            Files.deleteIfExists(configuration);
        }
    }

    public void testClasseQuiNeRespectePasLeContrat() throws Exception {
        Path configuration = creerConfiguration("java.lang.String", "metier.MetierImpl");
        try {
            try {
                Presentation2.construireMetier(configuration);
                fail("Une classe étrangère au contrat IDao devait être refusée.");
            } catch (IllegalArgumentException attendu) {
                assertTrue(attendu.getMessage().contains("dao.IDao"));
            }
        } finally {
            Files.deleteIfExists(configuration);
        }
    }

    public void testConfigurationIncomplete() throws Exception {
        Path configuration = creerConfiguration("dao.DaoImpl");
        try {
            try {
                Presentation2.construireMetier(configuration);
                fail("Une configuration incomplète devait être refusée.");
            } catch (IllegalArgumentException attendu) {
                assertTrue(attendu.getMessage().contains("exactement"));
            }
        } finally {
            Files.deleteIfExists(configuration);
        }
    }

    private Path creerConfiguration(String... classes) throws Exception {
        Path fichier = Files.createTempFile("simulation-f1-", ".txt");
        Files.write(fichier, Arrays.asList(classes), StandardCharsets.UTF_8);
        return fichier;
    }
}
