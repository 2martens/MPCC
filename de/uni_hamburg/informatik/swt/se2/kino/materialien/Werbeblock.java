package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;

/**
 * @author Jim Martens
 * @version 13.06.2013
 * @copyright 2013 Jim Martens
 */
public class Werbeblock
{
    private int _laenge;
    private FSK _fsk;
    
    
    /**
     * Initialisiert einen Werbeblock.
     * 
     * @param laenge Die Länge des Werbeblocks.
     * @param fsk Die FSK-Einstufung.
     * 
     * @require laenge > 0
     * @require fsk != null
     * 
     * @ensure getLaenge() == laenge
     * @ensure getFSK() == fsk
     */
    public Werbeblock(int laenge, FSK fsk)
    {
        assert laenge > 0 : "Vorbedingung verletzt: laenge > 0";
        assert fsk != null : "Vorbedingung verletzt: fsk != null";
        
        _laenge = laenge;
        _fsk = fsk;
    }

    /**
     * Gibt die Länge des Werbeblocks zurück.
     * 
     * @ensure result > 0
     */
    public int getLaenge()
    {
        return _laenge;
    }
    
    /**
     * Gibt die FSK-Einstufung des Werbeblocks zurück.
     * 
     * @ensure result != null
     */
    public FSK getFSK()
    {
        return _fsk;
    }
    
}
