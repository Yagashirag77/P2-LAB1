package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/** Test témoin conservé avec le squelette Maven d'origine. */
public class AppTest 
    extends TestCase
{
    /** Construit le test témoin demandé par l'ancienne structure Maven. */
    public AppTest( String testName )
    {
        super( testName );
    }

    /** Regroupe ce test pour le moteur JUnit 3 déjà configuré. */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /** Confirme simplement que l'ancien environnement de test reste opérationnel. */
    public void testApp()
    {
        assertTrue( true );
    }
}
