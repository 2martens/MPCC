package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

/**
 * Eine Reinigungszeit, die die Dauer der Reinigung darstellt.
 * 
 * @author Jim Martens
 * @version 13.06.2013
 * @copyright 2013 Jim Martens
 */
public final class Reinigungszeit
{
    // Dauer der Reinigung
    private final int _dauer;
    
    /**
     * Initialisiert die Reinigungszeit.
     * 
     * @param dauer Die Dauer der Reinigung in Minuten.
     * 
     * @require istGueltig(dauer)
     * 
     * @ensure getDauer() == dauer
     */
    public Reinigungszeit(int dauer)
    {
        assert istGueltig(dauer) : "Vorbedingung verletzt: istGueltig(dauer)";
        _dauer = dauer;
    }
    
    /**
     * Gibt die Reinigungsdauer zurück.
     * 
     * @return  die Reinigungsdauer
     */
    public int getDauer()
    {
        return _dauer;
    }
    
    /**
     * Prüft, ob die angegebene Dauer in Minuten gültig ist.
     * 
     * @param dauer Die Dauer in Minuten (1, 2, ...).
     * @return <code>true</code> wenn die Dauer eine gültige Dauer ist,
     *          <code>false</code> andernfalls
     */
    public static boolean istGueltig(int dauer)
    {
        return (dauer >= 1);
    }
    
    @Override
    public boolean equals(Object o)
    {
        boolean result = false;
        if (o instanceof Reinigungszeit)
        {
            Reinigungszeit vergleichszeit = (Reinigungszeit) o;
            result = (vergleichszeit.getDauer() == getDauer());
        }
        return result;
    }
    
    @Override
    public int hashCode()
    {
        return _dauer * 59;
    }
}
