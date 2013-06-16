package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Stellt einen Tag dar.
 * 
 * @author Jim Martens
 * @version 13.06.2013
 * @copyright 2013 Jim Martens
 */
public final class Tag implements Comparable<Tag>
{
    private final Datum _datum;
    private final Wochentag _wochentag;
    
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
     * Initialisiert einen Tag.
     * 
     * @param datum Ein Datum.
     * 
     * @require datum != null
     * 
     * @ensure getDatum() == datum
     */
    public Tag(Datum datum)
    {
        assert datum != null : "Vorbedingung verletzt: datum != null";
        _datum = datum;
        Wochentag wochentagVonDatum;
        synchronized (CALENDAR)
        {
            CALENDAR.clear();
            CALENDAR.set(datum.getJahr(), datum.getMonat() - 1, datum.getTag());
            int dayOfWeek = CALENDAR.get(Calendar.DAY_OF_WEEK);
            wochentagVonDatum = getWochentagVonDayOfWeek(dayOfWeek);
        }
        _wochentag = wochentagVonDatum;
    }
    
    /**
     * Gibt das Datum dieses Tages zurück.
     * 
     * @ensure result != null
     */
    public Datum getDatum()
    {
        return _datum;
    }
    
    /**
     * Gibt den Wochentag dieses Tages zurück.
     * 
     * @ensure result != null
     */
    public Wochentag getWochentag()
    {
        return _wochentag;
    }
    
    /**
     * Gibt an, wieviele Tage seit dem gegebenen Tag bis zu diesem Tag vergangen sind.
     * 
     * @param tag Der Tag, für den berechnet werden soll, wieviele Tage vergangen sind bis zu diesem.
     * 
     * @require tag != null
     */
    public int tageSeit(Tag tag)
    {
        assert tag != null : "Vorbedingung verletzt: tag != null";
        return _datum.tageSeit(tag.getDatum());
    }

    /**
     * Vergleicht diesen Tag mit einem anderen Tag.
     * 
     * @param tag der andere Tag.
     * @return einen Wert kleiner als 0 falls dieser Tag kleiner als tag
     *         ist, einen Wert größer als 0, falls dieser Tag größer als tag
     *         ist, sonst 0.
     */
    @Override
    public int compareTo(Tag tag)
    {
        return _datum.compareTo(tag.getDatum());
    }
    
    @Override
    public boolean equals(Object o)
    {
        boolean result = false;
        if (o instanceof Tag)
        {
            Tag vergleichsTag = (Tag) o;
            result = _datum.equals(vergleichsTag.getDatum());
        }
        return result;
    }
    
    @Override
    public int hashCode()
    {
        return _datum.hashCode();
    }
    
    /**
     * Wandelt den int Wert dayOfWeek von Calendar in einen Wochentag-Wert um.
     * 
     * @param dayOfWeek Der Tag der Woche (1, 2, ..., 7)
     * 
     * @require dayOfWeek >= 1
     * @require dayOfWeek <= 7
     */
    private static Wochentag getWochentagVonDayOfWeek(int dayOfWeek)
    {
        assert dayOfWeek >= 1 : "Vorbedingung verletzt: dayOfWeek >= 1";
        assert dayOfWeek <= 7 : "Vorbedingung verletzt: dayOfWeek <= 7";
        
        Wochentag wochentag = null;
        switch (dayOfWeek)
        {
            case 1:
                wochentag = Wochentag.SONNTAG;
                break;
            case 2:
                wochentag = Wochentag.MONTAG;
                break;
            case 3:
                wochentag = Wochentag.DIENSTAG;
                break;
            case 4:
                wochentag = Wochentag.MITTWOCH;
                break;
            case 5:
                wochentag = Wochentag.DONNERSTAG;
                break;
            case 6:
                wochentag = Wochentag.FREITAG;
                break;
            case 7:
                wochentag = Wochentag.SAMSTAG;
                break;
        }
        return wochentag;
    }
}
