package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;

/**
 * Formatierer für {@link FSK}.
 * 
 * @author Jim Martens
 * @version 19.06.2013
 * @copyright 2013 Jim Martens
 */
class FSKFormatierer
{
    private FSK _fsk;
    
    /**
     * Initialisiert einen Formatierer für die angegebene FSK.
     * 
     * @param fsk Die FSK-Angabe, die von diesem Formatierer dargestellt wird.
     * 
     * @require fsk != null
     */
    public FSKFormatierer(FSK fsk)
    {
        assert fsk != null : "Vorbedingung verletzt: fsk != null";
        _fsk = fsk;
    }
    
    /**
     * Gibt die FSK zurück, die von diesem Formatierer dargestellt wird.
     */
    FSK getFSK()
    {
        return _fsk;
    }
    
    @Override
    public String toString()
    {
        return _fsk.toString();
    }
}
