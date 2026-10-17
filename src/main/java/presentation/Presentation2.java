package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Petit conteneur autonome : il charge puis relie les classes indiquées en configuration.
 */
public final class Presentation2 {

    private Presentation2() {
    }

    public static void main(String[] args) {
        Path configuration = args.length == 0 ? Paths.get("config.txt") : Paths.get(args[0]);
        try {
            IMetier metier = construireMetier(configuration);
            System.out.println("Résultat = " + metier.calcul() + " secondes");
        } catch (IOException | ReflectiveOperationException | IllegalArgumentException e) {
            System.err.println("Demarrage impossible : " + messageUtile(e));
        }
    }

    public static IMetier construireMetier(Path configuration)
            throws IOException, ReflectiveOperationException {
        List<String> nomsClasses = lireConfiguration(configuration);
        IDao dao = creerInstance(nomsClasses.get(0), IDao.class);
        System.out.println("[CONTAINER] DAO instancié : " + dao.getClass().getName());
        IMetier metier = creerInstance(nomsClasses.get(1), IMetier.class);
        System.out.println("[CONTAINER] Métier instancié : " + metier.getClass().getName());
        injecterDao(metier, dao);
        return metier;
    }

    private static List<String> lireConfiguration(Path configuration) throws IOException {
        List<String> lignesUtiles = new ArrayList<>();
        try (BufferedReader lecteur = Files.newBufferedReader(configuration, StandardCharsets.UTF_8)) {
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {
                String valeur = ligne.trim();
                if (!valeur.isEmpty() && !valeur.startsWith("#")) {
                    lignesUtiles.add(valeur);
                }
            }
        }
        if (lignesUtiles.size() != 2) {
            throw new IllegalArgumentException(
                    "La configuration doit contenir exactement un DAO et un service métier.");
        }
        return lignesUtiles;
    }

    private static <T> T creerInstance(String nomClasse, Class<T> contrat)
            throws ReflectiveOperationException {
        Class<?> classe = Class.forName(nomClasse);
        if (!contrat.isAssignableFrom(classe)) {
            throw new IllegalArgumentException(
                    nomClasse + " n'implémente pas " + contrat.getName() + ".");
        }
        Constructor<?> constructeur = classe.getDeclaredConstructor();
        Object instance = constructeur.newInstance();
        return contrat.cast(instance);
    }

    private static void injecterDao(IMetier metier, IDao dao)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method methodeInjection = metier.getClass().getMethod("setDao", IDao.class);
        methodeInjection.invoke(metier, dao);
    }

    private static String messageUtile(Exception erreur) {
        Throwable cause = erreur instanceof InvocationTargetException && erreur.getCause() != null
                ? erreur.getCause() : erreur;
        return cause.getMessage() == null ? cause.getClass().getSimpleName() : cause.getMessage();
    }
}
