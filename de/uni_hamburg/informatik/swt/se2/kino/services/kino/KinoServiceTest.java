package de.uni_hamburg.informatik.swt.se2.kino.services.kino;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Reinigungszeit;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Uhrzeit;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Woche;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Film;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kino;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Tagesplan;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Werbeblock;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Wochenplan;

/**
 * Testklasse für KinoService.
 * 
 * @author Jim Martens
 * @version 24.06.2013
 * @copyright 2013 Jim Martens
 */
public class KinoServiceTest
{
    private KinoService _kinoService;
    private Kino _kino;
    
    private Film _filmVorhanden;
    private Film _filmVorhanden2;
    private Film _filmVorhanden3;
    private Film _filmVorhanden4;
    private Film _filmNichtVorhanden;
    private Vorstellung _vorstellungVorhanden;
    private Vorstellung _vorstellungVorhanden2;
    private Vorstellung _vorstellungNichtVorhanden;
    private Vorstellung _vorstellungNichtHinzufuegbar;
    private Kinosaal _kinosaal1;
    private Kinosaal _kinosaal2;
    private Kinosaal _kinosaal3;
    private Uhrzeit _1130;
    private Uhrzeit _1500;
    private Uhrzeit _1730;
    private Uhrzeit _2230;
    private Datum _heute;
    private Werbeblock _blockKlein;
    private Werbeblock _blockGross;
    private Reinigungszeit _zeitKlein;
    private Reinigungszeit _zeitGross;
    
    @Before
    public void setUp() throws Exception
    {
        _1130 = new Uhrzeit(11, 30);
        _1500 = new Uhrzeit(15, 00);
        _1730 = new Uhrzeit(17, 30);
        _2230 = new Uhrzeit(22, 30);
        _heute = Datum.heute();
        _blockKlein = new Werbeblock(15, FSK.FSK0);
        _blockGross = new Werbeblock(30, FSK.FSK6);
        _zeitKlein = new Reinigungszeit(15);
        _zeitGross = new Reinigungszeit(30);
        
        _kinosaal1 = new Kinosaal("Kino A", 20, 20, _zeitKlein);
        _kinosaal2 = new Kinosaal("Kino B", 30, 30, _zeitGross);
        _kinosaal3 = new Kinosaal("Kino C", 40, 40, _zeitGross);
        _filmVorhanden = new Film("Wilder Westen", 90, FSK.FSK18, false, false);
        _filmVorhanden2 = new Film("Star Ship", 180, FSK.FSK12, true, false);
        _filmVorhanden3 = new Film("Sommerliebe", 90, FSK.FSK12, false, false);
        _filmVorhanden4 = new Film("Test", 130, FSK.FSK12, false, false);
        _filmNichtVorhanden = new Film("Lahmer Boden", 120, FSK.FSK6, false,
                false);
        _vorstellungVorhanden = new Vorstellung(_kinosaal1, _filmVorhanden3,
                _1500, _heute, _kinosaal1.getReinigungszeit(), _blockKlein);
        _vorstellungVorhanden2 = new Vorstellung(_kinosaal3, _filmVorhanden3,
                _1730, _heute);
        _vorstellungNichtVorhanden = new Vorstellung(_kinosaal1,
                _filmVorhanden2, _1730, _heute, _kinosaal1.getReinigungszeit(),
                _blockGross);
        _vorstellungNichtHinzufuegbar = new Vorstellung(_kinosaal1,
                _filmVorhanden2, _1500, _heute, _kinosaal1.getReinigungszeit(),
                _blockGross);
        
        _kino = new Kino(new Kinosaal[] { _kinosaal1, _kinosaal3 },
                new Vorstellung[] { _vorstellungVorhanden,
                        _vorstellungVorhanden2 }, new Film[] { _filmVorhanden,
                        _filmVorhanden2, _filmVorhanden3, _filmVorhanden4 });
        
        _kinoService = new KinoServiceImpl(_kino);
    }
    
    @Test
    public void testIstFilmZeigenMoeglich()
    {
        // Endzeit überschneidet sich mit Anfangszeit bereits vorhandener
        // Vorstellung
        boolean result = _kinoService.istFilmZeigenMoeglich(_filmVorhanden2,
                _blockGross, _zeitKlein, _kinosaal1, _heute, _1130);
        assertFalse(result);
        
        // Anfangszeit ist nach Endzeit vorangehender Vorstellung, daher kein
        // Problem
        result = _kinoService.istFilmZeigenMoeglich(_filmVorhanden2,
                _blockGross, _zeitKlein, _kinosaal1, _heute, _1730);
        assertTrue(result);
        
        // Endzeit ist vor Anfangszeit nachfolgender Vorstellung, daher kein
        // Problem
        result = _kinoService.istFilmZeigenMoeglich(_filmVorhanden4, null,
                null, _kinosaal3, _heute, _1500);
        assertTrue(result);
        
        // gleiche Anfangszeit aber verschiedene Kinosäle müssen problemlos
        // funktionieren
        result = _kinoService.istFilmZeigenMoeglich(_filmVorhanden3,
                _blockGross, _zeitGross, _kinosaal3, _heute, _1500);
        assertTrue(result);
        
        // Filme mit FSK 18 dürfen erst ab 22:00 gezeigt werden
        result = _kinoService.istFilmZeigenMoeglich(_filmVorhanden,
                _blockKlein, _zeitKlein, _kinosaal1, _heute, _1730);
        assertFalse(result);
        
        result = _kinoService.istFilmZeigenMoeglich(_filmVorhanden,
                _blockGross, _zeitKlein, _kinosaal1, _heute, _2230);
        assertTrue(result);
    }
    
    @Test
    public void testFuegeVorstellungHinzu()
    {
        assertFalse(_kinoService
                .istVorstellungVorhanden(_vorstellungNichtVorhanden));
        _kinoService.fuegeVorstellungHinzu(_vorstellungNichtVorhanden, _heute,
                _kinosaal1, _1730);
        assertTrue(_kinoService
                .istVorstellungVorhanden(_vorstellungNichtVorhanden));
    }
    
    @Test
    public void testIstHinzufuegenMoeglich()
    {
        assertFalse(_kinoService.istHinzufuegenMoeglich(_vorstellungVorhanden));
        
        // Vorstellung nicht vorhanden und keine zeitlichen/räumlichen
        // Überschneidungen
        assertTrue(_kinoService
                .istHinzufuegenMoeglich(_vorstellungNichtVorhanden));
        
        // Vorstellung nicht vorhanden, aber zeitliche und räumliche
        // Überschneidung mit vorhandener Vorstellung
        assertFalse(_kinoService
                .istHinzufuegenMoeglich(_vorstellungNichtHinzufuegbar));
    }
    
    @Test
    public void testIstVorstellungVorhanden()
    {
        assertTrue(_kinoService.istVorstellungVorhanden(_vorstellungVorhanden));
        assertFalse(_kinoService
                .istVorstellungVorhanden(_vorstellungNichtVorhanden));
    }
    
    @Test
    public void testAktualisiereVorstellung()
    {
        Vorstellung neueVorstellung = new Vorstellung(_kinosaal3,
                _filmVorhanden3, _1500, _heute, _kinosaal1.getReinigungszeit(),
                _blockKlein);
        assertTrue(_kinoService.istVorstellungVorhanden(_vorstellungVorhanden));
        assertFalse(_kinoService.istVorstellungVorhanden(neueVorstellung));
        _kinoService.aktualisiereVorstellung(_vorstellungVorhanden,
                neueVorstellung);
        assertFalse(_kinoService.istVorstellungVorhanden(_vorstellungVorhanden));
        assertTrue(_kinoService.istVorstellungVorhanden(neueVorstellung));
    }
    
    @Test
    public void testEntferneVorstellung()
    {
        assertTrue(_kinoService.istVorstellungVorhanden(_vorstellungVorhanden));
        _kinoService.entferneVorstellung(_vorstellungVorhanden);
        assertFalse(_kinoService.istVorstellungVorhanden(_vorstellungVorhanden));
    }
    
    @Test
    public void testGetVorstellung()
    {
        Vorstellung v = _kinoService.getVorstellung(_kinosaal1, _heute, _1500);
        assertSame(_vorstellungVorhanden, v);
    }
    
    @Test
    public void testIstKinosaalVorhanden()
    {
        assertTrue(_kinoService.istKinosaalVorhanden(_kinosaal1));
        assertFalse(_kinoService.istKinosaalVorhanden(_kinosaal2));
    }
    
    @Test
    public void testIstFilmVorraetig()
    {
        assertTrue(_kinoService.istFilmVorraetig(_filmVorhanden));
        assertFalse(_kinoService.istFilmVorraetig(_filmNichtVorhanden));
    }
    
    @Test
    public void testGetFilme()
    {
        assertEquals(_kino.getFilme(), _kinoService.getFilme());
    }
    
    @Test
    public void testGetTagesplan()
    {
        Tagesplan sollPlan = _kino.getTagesplan(_heute);
        Tagesplan istPlan = _kinoService.getTagesplan(_heute);
        assertSame(sollPlan, istPlan);
    }
    
    @Test
    public void testGetWochenplan()
    {
        Wochenplan sollPlan = _kino.getWochenplan(_kinosaal1,
                Woche.dieseWoche());
        Wochenplan istPlan = _kino
                .getWochenplan(_kinosaal1, Woche.dieseWoche());
        assertSame(sollPlan, istPlan);
    }
    
    @Test
    public void testSetWochenplan()
    {
        Woche woche = Woche.dieseWoche().naechsteWoche();
        assertFalse(_kinoService.istWochenplanVorhanden(_kinosaal1, woche));
        Wochenplan wochenplan = new Wochenplan(woche, _kinosaal1);
        _kinoService.setWochenplan(wochenplan, _kinosaal1, woche);
        assertTrue(_kinoService.istWochenplanVorhanden(_kinosaal1, woche));
    }
    
    @Test
    public void testIstWochenplanVorhanden()
    {
        assertTrue(_kinoService.istWochenplanVorhanden(_kinosaal1,
                Woche.dieseWoche()));
        assertFalse(_kinoService.istWochenplanVorhanden(_kinosaal1,
                Woche.wocheMitDiesemTag(new Datum(1, 1, 1970))));
    }
    
    @Test
    public void testGetKinosaele()
    {
        assertEquals(_kino.getKinosaele(), _kinoService.getKinosaele());
    }
    
    @Test
    public void testIstVorstellungEntfernenMoeglich()
    {
        assertTrue(_kinoService
                .istVorstellungEntfernenMoeglich(_vorstellungVorhanden));
        _vorstellungVorhanden.verkaufePlatz(new Platz(10, 10));
        assertFalse(_kinoService
                .istVorstellungEntfernenMoeglich(_vorstellungVorhanden));
    }
    
    @Test
    public void testGetWerbeblockMaximalDauer()
    {
        int dauer = 0;
        dauer += _vorstellungVorhanden.getFilm().getLaenge();
        dauer += _vorstellungVorhanden.getReinigungszeit().getDauer();
        int maximalZeit = _1730.minutenSeit(_1500);
        maximalZeit = maximalZeit - dauer;
        
        assertEquals(maximalZeit,
                _kinoService.getWerbeblockMaximalDauer(_vorstellungVorhanden));
    }
    
    @Test
    public void testIstVorstellungErstellbar()
    {
        _vorstellungVorhanden.setFilm(_filmVorhanden2);
        assertFalse(_kinoService.istVorstellungErstellbar(_kinosaal1, _heute,
                _1730, _filmVorhanden3));
        assertTrue(_kinoService.istVorstellungErstellbar(_kinosaal1, _heute,
                _1130, _filmVorhanden3));
        _vorstellungVorhanden.setFilm(_filmVorhanden3);
        assertTrue(_kinoService.istVorstellungErstellbar(_kinosaal1, _heute,
                _1730, _filmVorhanden3));
        assertTrue(_kinoService.istVorstellungErstellbar(_kinosaal1, _heute,
                _1130, _filmVorhanden3));
        
        assertFalse(_kinoService.istVorstellungErstellbar(_kinosaal3, _heute,
                _1730, _filmVorhanden3));
    }
}
