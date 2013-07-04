package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan.subwerkzeuge.vorstellung;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;

/**
 * Formatierer für einen {@link Kinosaal}.
 * 
 * @author Jim Martens
 * @version 19.06.2013
 * @copyright 2013 Jim Martens
 */
class KinosaalFormatierer
{
    private Kinosaal _kinosaal;
    
    /**
     * Initialisiert einen Formatierer für den angegebenen Kinosaal.
     * 
     * @param kinosaal Der Kinosaal, der von diesem Formatierer dargestellt wird.
     * 
     * @require kinosaal != null
     */
    public KinosaalFormatierer(Kinosaal kinosaal)
    {
        assert kinosaal != null : "Vorbedingung verletzt: kinosaal != null";
        _kinosaal = kinosaal;
    }
    
    /**
     * Gibt den Kinosaal zurück, der von diesem Formatierer dargestellt wird.
     */
    Kinosaal getKinosaal()
    {
        return _kinosaal;
    }
    
    @Override
    public String toString()
    {
        return _kinosaal.getName();
    }
}
