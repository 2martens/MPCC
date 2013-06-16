package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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
    
    // Für Gültigkeitsprüfungen und Datumsarithmetik wird intern ein Objekt vom
    // Typ Calendar verwendet.
    private static final Calendar CALENDAR = Calendar.getInstance();

    // "Static initializer", initialisiert Klassenvariablen nach der Erzeugung
    // des Klassenobjekts.
    static
    {
        CALENDAR.setLenient(false);
        CALENDAR.setTimeZone(TimeZone.getTimeZone("GMT"));
    }
    
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
     * Gibt die aktuelle Woche zurück.
     * 
     * @ensure result != null
     */
    public static Woche dieseWoche()
    {
        Datum datum = null;
        synchronized (CALENDAR)
        {
            CALENDAR.clear();
            CALENDAR.setTimeInMillis(System.currentTimeMillis());
            CALENDAR.setFirstDayOfWeek(Calendar.MONDAY);
            int dayOfWeek = CALENDAR.get(Calendar.DAY_OF_WEEK);
            // CALENDAR-Konstanten handeln
            if (dayOfWeek == 1)
            {
                dayOfWeek += 7;
            }
            while (dayOfWeek > CALENDAR.getFirstDayOfWeek()) {
                CALENDAR.add(Calendar.DATE, -1);
                dayOfWeek -= 1;
            }
            datum = new Datum(CALENDAR.get(Calendar.DAY_OF_MONTH),
                    CALENDAR.get(Calendar.MONTH) + 1,
                    CALENDAR.get(Calendar.YEAR));
        }
        return createWoche(datum);
    }
    
    /**
     * Gibt die letzte Woche zurück.
     * 
     * @ensure result != null
     */
    public Woche letzteWoche()
    {
        Datum startDatum = _wochentage.get(0).getDatum().minus(7);
        return createWoche(startDatum);
    }
    
    /**
     * Gibt die nächste Woche zurück.
     * 
     * @ensure result != null
     */
    public Woche naechsteWoche()
    {
        Datum startDatum = _wochentage.get(0).getDatum().plus(7);
        return createWoche(startDatum);
    }
    
    /**
     * Prüft, ob das gegebene Datum Teil der Woche ist.
     * 
     * @param datum Das zu prüfende Datum.
     * 
     * @require datum != null
     */
    public boolean istInWoche(Datum datum)
    {
        assert datum != null : "Vorbedingung verletzt: datum != null";
        Tag tag = new Tag(datum);
        return _wochentage.contains(tag);
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
    
    /**
     * Erzeugt eine Woche, startend von dem gegebenen Datum.
     * 
     * @param startDatum Das Startdatum.
     * 
     * @require startDatum != null
     * 
     * @ensure result != null
     */
    private static Woche createWoche(Datum startDatum)
    {
        assert startDatum != null : "Vorbedingung verletzt: startDatum != null";
        
        Woche woche = null;
        List<Tag> wochentage = new ArrayList<>(7);
        Tag tag1 = new Tag(startDatum);
        Tag tag2 = new Tag(tag1.getDatum().naechsterTag());
        Tag tag3 = new Tag(tag2.getDatum().naechsterTag());
        Tag tag4 = new Tag(tag3.getDatum().naechsterTag());
        Tag tag5 = new Tag(tag4.getDatum().naechsterTag());
        Tag tag6 = new Tag(tag5.getDatum().naechsterTag());
        Tag tag7 = new Tag(tag6.getDatum().naechsterTag());
        
        wochentage.add(tag1);
        wochentage.add(tag2);
        wochentage.add(tag3);
        wochentage.add(tag4);
        wochentage.add(tag5);
        wochentage.add(tag6);
        wochentage.add(tag7);
        
        woche = new Woche(wochentage);
        return woche;
    }
}
