package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jim Martens
 * @version 13.06.2013
 * @copyright 2013 Jim Martens
 */
public class WocheTest
{
    private List<Tag> _wochentageGueltig;
    private List<Tag> _wochentageUngueltig;
    
    @Before
    public void setUp()
    {
        _wochentageGueltig = new ArrayList<>(7);
        _wochentageGueltig.add(new Tag(new Datum(13, 6, 2013)));
        _wochentageGueltig.add(new Tag(new Datum(14, 6, 2013)));
        _wochentageGueltig.add(new Tag(new Datum(15, 6, 2013)));
        _wochentageGueltig.add(new Tag(new Datum(16, 6, 2013)));
        _wochentageGueltig.add(new Tag(new Datum(17, 6, 2013)));
        _wochentageGueltig.add(new Tag(new Datum(18, 6, 2013)));
        _wochentageGueltig.add(new Tag(new Datum(19, 6, 2013)));
        
        _wochentageUngueltig = new ArrayList<>(7);
        _wochentageUngueltig.add(new Tag(new Datum(13, 6, 2013)));
        _wochentageUngueltig.add(new Tag(new Datum(13, 6, 2013)));
        _wochentageUngueltig.add(new Tag(new Datum(13, 6, 2013)));
        _wochentageUngueltig.add(new Tag(new Datum(13, 6, 2013)));
        _wochentageUngueltig.add(new Tag(new Datum(13, 6, 2013)));
        _wochentageUngueltig.add(new Tag(new Datum(13, 6, 2013)));
        _wochentageUngueltig.add(new Tag(new Datum(13, 6, 2013)));
    }
    
    @Test
    public void testKonstruktor()
    {
        Woche woche = new Woche(_wochentageGueltig);
        assertSame(_wochentageGueltig, woche.getWochentage());
    }
    
    @Test
    public void testIstGueltig()
    {
        boolean gueltig = Woche.istGueltig(_wochentageGueltig);
        assertTrue(gueltig);
        gueltig = Woche.istGueltig(_wochentageUngueltig);
        assertFalse(gueltig);
    }
    
    @Test
    public void testDieseWoche()
    {
        assertNotNull(Woche.dieseWoche());
    }
    
}
