package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

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
    
    /**
     * Initialisiert einen Tag.
     * 
     * @param datum Ein Datum.
     * @param wochentag Ein Wochentag.
     * 
     * @require datum != null
     * @require wochentag != null
     * 
     * @ensure getDatum() == datum
     * @ensure getWochentag() == wochentag
     */
    public Tag(Datum datum, Wochentag wochentag)
    {
        assert datum != null : "Vorbedingung verletzt: datum != null";
        assert wochentag != null : "Vorbedingung verletzt: wochentag != null";
        _datum = datum;
        _wochentag = wochentag;
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
}
