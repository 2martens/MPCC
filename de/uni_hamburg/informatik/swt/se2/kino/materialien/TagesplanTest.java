package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Reinigungszeit;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Uhrzeit;

public class TagesplanTest
{
    private static final Reinigungszeit _reinigungszeit = new Reinigungszeit(10);
    private static final Datum _datum = new Datum(1, 1, 2010);
    private static final Film _film = new Film("", 1, FSK.FSK0, false, false);
    private static final Kinosaal _kinosaal = new Kinosaal("Saal 1", 1, 1,
            _reinigungszeit);
    private static final Kinosaal _kinosaal2 = new Kinosaal("Saal 2", 1, 1,
            _reinigungszeit);
    private static final Uhrzeit _startzeit = new Uhrzeit(0, 0);
    private static final Uhrzeit _startzeit2 = new Uhrzeit(2, 0);
    private static final Vorstellung _vorstellung = new Vorstellung(_kinosaal,
            _film, _startzeit, _datum, 0);
    private static final Vorstellung _vorstellung2 = new Vorstellung(_kinosaal,
            _film, _startzeit2, _datum, 0);
    private static final Vorstellung _vorstellung3 = new Vorstellung(
            _kinosaal2, _film, _startzeit2, _datum, 0);
    
    private Tagesplan _t;
    
    @Before
    public void setUp()
    {
        _t = new Tagesplan(_datum);
    }
    
    @Test
    public void testTagesplanKenntSeinDatum()
    {
        assertEquals(_datum, _t.getDatum());
    }
    
    @Test
    public void testNeuerTagesplanIstLeer()
    {
        assertTrue(_t.getVorstellungen().isEmpty());
    }
    
    @Test
    public void testVorstellungenHinzufuegen()
    {
        _t.fuegeVorstellungHinzu(_vorstellung);
        assertEquals(1, _t.getVorstellungen().size());
        assertTrue(_t.istVorhanden(_vorstellung));
    }
    
    @Test
    public void testVorstellungenWerdenNachAnfangszeitSortiert()
    {
        _t.fuegeVorstellungHinzu(_vorstellung2);
        _t.fuegeVorstellungHinzu(_vorstellung);
        assertEquals(_vorstellung, _t.getVorstellungen().get(0));
        assertEquals(_vorstellung2, _t.getVorstellungen().get(1));
    }
    
    @Test
    public void testGleichzeitigeVorstellungenInVerschiedenenSaelenMoeglich()
    {
        _t.fuegeVorstellungHinzu(_vorstellung2);
        _t.fuegeVorstellungHinzu(_vorstellung3);
        assertEquals(2, _t.getVorstellungen().size());
    }
    
    @Test
    public void testVorstellungEntfernen()
    {
        _t.fuegeVorstellungHinzu(_vorstellung);
        assertTrue(_t.istVorhanden(_vorstellung));
        _t.entferneVorstellung(_vorstellung);
        assertFalse(_t.istVorhanden(_vorstellung));
    }
}
