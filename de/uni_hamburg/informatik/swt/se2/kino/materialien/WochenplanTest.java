package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Reinigungszeit;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Uhrzeit;
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
    private Vorstellung _vorstellung;
    
    @Before
    public void setUp()
    {
        _woche = Woche.dieseWoche();
        _kinosaal = new Kinosaal("Kino1", 10, 10, new Reinigungszeit(15));
        Film film = new Film("Hammersmith", 90, FSK.FSK12, false, false);
        Uhrzeit anfang = new Uhrzeit(17, 30);
        Datum datum = _woche.getWochentage().get(0).getDatum();
        _vorstellung = new Vorstellung(_kinosaal, film, anfang, datum);
    }
    
    @Test
    public void testKonstruktor()
    {
        Wochenplan wochenplan = new Wochenplan(_woche, _kinosaal);
        assertSame(_woche, wochenplan.getWoche());
        assertSame(_kinosaal, wochenplan.getKinosaal());
    }
    
    @Test
    public void testIstInDiesemPlan()
    {
        Wochenplan wochenplan = new Wochenplan(_woche, _kinosaal);
        Datum datumVorhanden = _woche.getWochentage().get(0).getDatum();
        Datum datumNichtVorhanden = new Datum(1, 1, 1909);
        assertTrue(wochenplan.istInDiesemPlan(datumVorhanden));
        assertFalse(wochenplan.istInDiesemPlan(datumNichtVorhanden));
    }
    
    @Test
    public void testFuegeVorstellungHinzu()
    {
        Wochenplan wochenplan = new Wochenplan(_woche, _kinosaal);
        assertFalse(wochenplan.istVorhanden(_vorstellung));
        wochenplan.fuegeVorstellungHinzu(_vorstellung);
        assertTrue(wochenplan.istVorhanden(_vorstellung));
    }
    
    @Test
    public void testEntferneVorstellung()
    {
        Wochenplan wochenplan = new Wochenplan(_woche, _kinosaal);
        wochenplan.fuegeVorstellungHinzu(_vorstellung);
        assertTrue(wochenplan.istVorhanden(_vorstellung));
        wochenplan.entferneVorstellung(_vorstellung);
        assertFalse(wochenplan.istVorhanden(_vorstellung));
    }
}
