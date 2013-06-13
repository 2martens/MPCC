package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Woche;

/**
 * Stellt einen Wochenplan dar.
 * 
 * @author Jim Martens
 * @version 13.06.2013
 * @copyright 2013 Jim Martens
 */
public class Wochenplan
{
    private Woche _woche;
    private Kinosaal _kinosaal;
    
    /**
     * Initialisiert einen Wochenplan.
     * 
     * @param woche Die Woche dieses Plans.
     * @param kinosaal Der Kinosaal für diesen Plan.
     * 
     * @require woche != null
     * @require kinosaal != null
     * 
     * @ensure getWoche() == woche
     * @ensure getKinosaal() == kinosaal
     */
    public Wochenplan(Woche woche, Kinosaal kinosaal)
    {
        assert woche != null : "Vorbedingung verletzt: woche != null";
        assert kinosaal != null : "Vorbedingung verletzt: kinosaal != null";
        _woche = woche;
        _kinosaal = kinosaal;
    }
    
    /**
     * Gibt die Woche dieses Wochenplans zurück.
     * 
     * @ensure result != null
     */
    public Woche getWoche()
    {
        return _woche;
    }
    
    /**
     * Gibt den Kinosaal dieses Wochenplans zurück.
     * 
     * @ensure result != null
     */
    public Kinosaal getKinosaal()
    {
        return _kinosaal;
    }
}
