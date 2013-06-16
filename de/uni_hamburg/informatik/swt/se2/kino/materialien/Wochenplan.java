package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Tag;
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
    private Map<Datum,Tagesplan> _tagesplaene;
    
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
        List<Tag> wochentage = _woche.getWochentage();
        _tagesplaene = new HashMap<>(7);
        _tagesplaene.put(wochentage.get(0).getDatum(), new Tagesplan(wochentage.get(0).getDatum()));
        _tagesplaene.put(wochentage.get(1).getDatum(), new Tagesplan(wochentage.get(1).getDatum()));
        _tagesplaene.put(wochentage.get(2).getDatum(), new Tagesplan(wochentage.get(2).getDatum()));
        _tagesplaene.put(wochentage.get(3).getDatum(), new Tagesplan(wochentage.get(3).getDatum()));
        _tagesplaene.put(wochentage.get(4).getDatum(), new Tagesplan(wochentage.get(4).getDatum()));
        _tagesplaene.put(wochentage.get(5).getDatum(), new Tagesplan(wochentage.get(5).getDatum()));
        _tagesplaene.put(wochentage.get(6).getDatum(), new Tagesplan(wochentage.get(6).getDatum()));
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
    
    /**
     * Fügt eine Vorstellung in den Wochenplan ein.
     * 
     * @param vorstellung Die einzufügende Vorstellung.
     * 
     * @require !istVorhanden(vorstellung)
     * 
     * @ensure istVorhanden(vorstellung)
     */
    public void fuegeVorstellungHinzu(Vorstellung vorstellung)
    {
        assert !istVorhanden(vorstellung) : "Vorbedingung verletzt: !istVorhanden(vorstellung)";
        Datum datum = vorstellung.getDatum();
        _tagesplaene.get(datum).fuegeVorstellungHinzu(vorstellung);
    }
    
    /**
     * Entfernt die angegebene Vorstellung vom Wochenplan.
     * 
     * @param vorstellung Die zu entfernende Vorstellung.
     * 
     * @require istVorhanden(vorstellung)
     * 
     * @ensure !istVorhanden(vorstellung)
     */
    public void entferneVorstellung(Vorstellung vorstellung)
    {
        assert istVorhanden(vorstellung) : "Vorbedingung verletzt: istVorhanden(vorstellung)";
        Datum datum = vorstellung.getDatum();
        _tagesplaene.get(datum).entferneVorstellung(vorstellung);
    }
    
    /**
     * Prüft, ob eine Vorstellung im Wochenplan vorhanden ist.
     * 
     * @param vorstellung Die zu überprüfende Vorstellung.
     * 
     * @require vorstellung != null
     */
    public boolean istVorhanden(Vorstellung vorstellung)
    {
        assert vorstellung != null : "Vorbedingung verletzt: vorstellung != null";
        Datum datum = vorstellung.getDatum();
        List<Vorstellung> vorstellungen = _tagesplaene.get(datum).getVorstellungen();
        
        return vorstellungen.contains(vorstellung);
    }
    
    /**
     * Prüft ob das gegebene Datum in diesem Wochenplan vorkommt.
     * 
     * @param datum
     * @return <code>true</code> wenn das Datum vorkommt,
     *          <code>false</code> wenn nicht
     * 
     * @require datum != null
     */
    public boolean istTagInDiesemPlan(Datum datum)
    {
        assert datum != null : "Vorbedingung verletzt: datum != null";
        boolean result = false;
        List<Tag> wochentage = _woche.getWochentage();
        for (Tag tag : wochentage)
        {
            if (tag.getDatum().equals(datum))
            {
                result = true;
                break;
            }
        }
        return result;
    }
}
