package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jim Martens
 * @version 13.06.2013
 * @copyright 2013 Jim Martens
 */
public class ReinigungszeitTest
{
    private int _gueltigeMinuten;
    private int _ungueltigeMinuten;
    
    
    @Before
    public void setUp()
    {
        _gueltigeMinuten = 1;
        _ungueltigeMinuten = 0;
    }
    
    @Test
    public void testKonstruktor()
    {
        Reinigungszeit reinigungszeit = new Reinigungszeit(_gueltigeMinuten);
        assertEquals(_gueltigeMinuten, reinigungszeit.getDauer());
    }
    
    @Test
    public void testIstGueltig()
    {
        boolean gueltig = Reinigungszeit.istGueltig(_gueltigeMinuten);
        assertTrue(gueltig);
        
        gueltig = Reinigungszeit.istGueltig(_ungueltigeMinuten);
        assertFalse(gueltig);
    }
    
    @Test
    public void testEqualsUndHashCode()
    {
        Reinigungszeit reinigungszeit = new Reinigungszeit(_gueltigeMinuten);
        Reinigungszeit reinigungszeit2 = new Reinigungszeit(_gueltigeMinuten);
        assertTrue(reinigungszeit.equals(reinigungszeit2));
        assertTrue(reinigungszeit.hashCode() == reinigungszeit2.hashCode());
    }
    
}
