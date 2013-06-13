package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Reinigungszeit;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Woche;

/**
 * @author Jim Martens
 * @version 13.06.2013
 * @copyright 2013 Jim Martens
 */
public class WochenplanTest
{
    private Woche _woche;
    private Kinosaal _kinosaal;
    
    @Before
    public void setUp()
    {
        _woche = Woche.dieseWoche();
        _kinosaal = new Kinosaal("Kino1", 10, 10, new Reinigungszeit(15));
    }
    
    @Test
    public void testKonstruktor()
    {
        Wochenplan wochenplan = new Wochenplan(_woche, _kinosaal);
        assertSame(_woche, wochenplan.getWoche());
        assertSame(_kinosaal, wochenplan.getKinosaal());
    }
    
}
