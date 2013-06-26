package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.kinosaalauswaehler;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;

/**
 * Formatierer für einen Kinosaal.
 * 
 * @author Jim Martens
 * @version 18.06.2013
 * @copyright 2013 Jim Martens
 */
class KinosaalFormatierer
{
    private Kinosaal _kinosaal;

    /**
     * Initialisiert einen Formatierer für den angegebenen Kinosaal.
     * 
     * @param kinosaal Der Kinosaal, der von diesem Formatierer
     *            dargestellt wird.
     */
    public KinosaalFormatierer(Kinosaal kinosaal)
    {
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
