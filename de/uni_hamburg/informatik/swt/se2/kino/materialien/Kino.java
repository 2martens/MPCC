package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Woche;

/**
 * Ein Kino mit mehreren Kinosälen, in denen Vorstellungen laufen koennen.
 * 
 * @author SE2-Team
 * @version SoSe 2013
 */
public class Kino
{
    private List<Kinosaal> _kinosaele;

    // Die Vorstellungspläne, sortiert nach Tagen.
    private SortedMap<Datum, Tagesplan> _tagesplaene;
    private Map<Kinosaal,Map<Woche,Wochenplan>> _wochenplaene;

    /**
     * Initialisiert ein Kino.
     * 
     * @param saele die Kinosäle des Kinos.
     * @param vorstellungen die Vorstellungen, die in dem Kino laufen.
     * 
     * @require saele != null
     * @require vorstellungen != null
     * @require saele enthaelt keine Nullpointer
     * @require vorstellungen enthaelt keine Nullpointer
     * @require alle Vorstellungen laufen in uebergebenen Kinosälen
     * @require alle Vorstellungen fangen zu unterschiedlichen Zeiten an
     */
    public Kino(Kinosaal[] saele, Vorstellung[] vorstellungen)
    {
        assert saele != null : "Vorbedingung verletzt: saele != null";
        assert vorstellungen != null : "Vorbedingung verletzt: vorstellungen != null";

        _kinosaele = new ArrayList<Kinosaal>(saele.length);
        _tagesplaene = new TreeMap<Datum, Tagesplan>();
        _wochenplaene = new HashMap<Kinosaal,Map<Woche,Wochenplan>>(3);

        for (Kinosaal saal : saele)
        {
            assert saal != null : "Vorbedingung verletzt: saele enthaelt keine Nullpointer";
            _kinosaele.add(saal);
            _wochenplaene.put(saal, new HashMap<Woche,Wochenplan>());
            _wochenplaene.get(saal).put(Woche.dieseWoche(), 
                    new Wochenplan(Woche.dieseWoche(), saal));
        }

        for (Vorstellung vorstellung : vorstellungen)
        {
            assert vorstellung != null : "Vorbedingung verletzt: vorstellungen enthaelt keine Nullpointer";
            Kinosaal saal = vorstellung.getKinosaal();
            assert _kinosaele.contains(saal) : "Vorbedingung verletzt: alle Vorstellungen laufen in uebergebenen Kinosaelen";
            Datum datum = vorstellung.getDatum();
            Tagesplan tagesplan = _tagesplaene.get(datum);
            if (tagesplan == null)
            {
                tagesplan = new Tagesplan(datum);
                _tagesplaene.put(datum, tagesplan);
            }
            tagesplan.fuegeVorstellungHinzu(vorstellung);
            
            Map<Woche,Wochenplan> plaene = _wochenplaene.get(saal);
            Woche woche = Woche.wocheMitDiesemTag(datum);
            Wochenplan plan = plaene.get(woche);
            if (plan == null)
            {
                plan = new Wochenplan(woche, saal);
                _wochenplaene.get(saal).put(woche, plan);
            }
            plan.fuegeVorstellungHinzu(vorstellung);
        }
    }

    /**
     * Prüft, ob der angegebene Kinosaal zu diesem Kino gehört.
     * 
     * @param kinosaal der Kinosaal.
     */
    public boolean hatKinosaal(Kinosaal kinosaal)
    {
        return _kinosaele.contains(kinosaal);
    }

    /**
     * Gibt die Kinosäle dieses Kinos zurück.
     * 
     * @ensure result != null
     */
    public List<Kinosaal> getKinosaele()
    {
        return new ArrayList<Kinosaal>(_kinosaele);
    }
    
    /**
     * Gibt den Wochenplan für die angegebene Woche zurück.
     * 
     * @param saal
     * @param woche
     * 
     * @require istWochenplanVorhanden(saal, woche)
     * 
     * @ensure result != null
     */
    public Wochenplan getWochenplan(Kinosaal saal, Woche woche)
    {
        assert istWochenplanVorhanden(saal, woche) : "Vorbedingung verletzt: istWochenplanVorhanden(saal, woche)";
        
        return _wochenplaene.get(saal).get(woche);
    }
    
    /**
     * Prüft, ob der Wochenplan für die angegebenen Kriterien vorhanden ist.
     * 
     * @param saal
     * @param woche
     * 
     * @require hatKinosaal(saal)
     * @require woche != null
     */
    public boolean istWochenplanVorhanden(Kinosaal saal, Woche woche)
    {
        assert hatKinosaal(saal) : "Vorbedingung verletzt: hatKinosaal(saal)";
        assert woche != null : "Vorbedingung verletzt: woche != null";
        
        return _wochenplaene.get(saal).get(woche) != null;
    }
    
    /**
     * Setzt den Wochenplan für die angegebenen Daten.
     * 
     * @param saal
     * @param woche
     * @param wochenplan
     * 
     * @require hatKinosaal(saal)
     * @require woche != null
     * @require wochenplan != null
     * 
     * @ensure getWochenplan(saal, woche) == wochenplan
     */
    public void setWochenplan(Kinosaal saal, Woche woche, Wochenplan wochenplan)
    {
        assert hatKinosaal(saal) : "Vorbedingung verletzt: hatKinosaal(saal)";
        assert woche != null : "Vorbedingung verletzt: woche != null";
        assert wochenplan != null : "Vorbedingung verletzt: wochenplan != null";
        
        _wochenplaene.get(saal).put(woche, wochenplan);
    }

    /**
     * Gibt den Tagesplan fuer das angegebene Datum zurück.
     * 
     * @param tag das Datum.
     * 
     * @require tag != null
     * @ensure result != null
     */
    public Tagesplan getTagesplan(Datum tag)
    {
        assert tag != null : "Vorbedingung verletzt: tag != null";

        Tagesplan tagesplan = _tagesplaene.get(tag);
        if (tagesplan == null)
        {
            tagesplan = new Tagesplan(tag);
        }
        return tagesplan;
    }
    
    /**
     * Setzt den Tagesplan für den angegebenen Tag.
     * 
     * @param datum
     * @param tagesplan
     * 
     * @require datum != null
     * @require tagesplan != null
     * 
     * @ensure getTagesplan(datum) == tagesplan
     */
    public void setTagesplan(Datum datum, Tagesplan tagesplan)
    {
        assert datum != null : "Vorbedingung verletzt: datum != null";
        assert tagesplan != null : "Vorbedingung verletzt: tagesplan != null";
        
        _tagesplaene.put(datum, tagesplan);
    }
}
