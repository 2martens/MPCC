package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;

public class WerbeblockTest
{
    private int _laenge;
    @Before
    public void setUp()
    {
        _laenge = 30;
    }
    
    @Test
    public void testKonstruktor()
    {
        Werbeblock wb = new Werbeblock(_laenge, FSK.FSK12);
        assertEquals(_laenge, wb.getLaenge());
        assertEquals(FSK.FSK12, wb.getFSK());
    }
    
}
