package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.List;

/**
 * Stellt eine Woche dar.
 * 
 * @author Jim Martens
 * @version 13.06.2013
 * @copyright 2013 Jim Martens
 */
public final class Woche
{
    private final List<Tag> _wochentage;
    
    /**
     * Initialisiert eine Woche.
     * 
     * @param wochentage Eine Liste der Wochentage.
     * 
     * @require istGueltig(wochentage)
     * 
     * @ensure getWochentage() == wochentage
     */
    public Woche(List<Tag> wochentage)
    {
        assert istGueltig(wochentage) : "Vorbedingung verletzt: istGueltig(wochentage)";
        _wochentage = wochentage;
    }
    
    /**
     * Gibt die Wochentage zurück.
     * 
     * @ensure istGueltig(result)
     */
    public List<Tag> getWochentage()
    {
        return _wochentage;
    }
    
    /**
     * Gibt an, ob eine Liste von Wochentagen gültig ist.<br>
     * <br>
     * Eine Liste ist gültig, wenn nur sieben Tage vorhanden sind
     * und die Tage aufeinander folgen.
     * 
     * @param wochentage Eine Liste von Wochentagen.
     * @return <code>true</code> wenn die Liste gültig ist,
     *          <code>false</code> sonst.
     */
    public static boolean istGueltig(List<Tag> wochentage)
    {
        boolean result = false;
        result = (wochentage != null);
        if (result)
        {
            result = (wochentage.size() == 7);
            if (result)
            {
                Tag tag0 = wochentage.get(0);
                Tag tag1 = wochentage.get(1);
                result = (tag1.tageSeit(tag0) == 1);
                if (!result)
                {
                    return result;
                }
                Tag tag2 = wochentage.get(2);
                result = (tag2.tageSeit(tag1) == 1);
                if (!result)
                {
                    return result;
                }
                Tag tag3 = wochentage.get(3);
                result = (tag3.tageSeit(tag2) == 1);
                if (!result)
                {
                    return result;
                }
                Tag tag4 = wochentage.get(4);
                result = (tag4.tageSeit(tag3) == 1);
                if (!result)
                {
                    return result;
                }
                Tag tag5 = wochentage.get(5);
                result = (tag5.tageSeit(tag4) == 1);
                if (!result)
                {
                    return result;
                }
                Tag tag6 = wochentage.get(6);
                result = (tag6.tageSeit(tag5) == 1);
            }
        }
        return result;
    }
}
