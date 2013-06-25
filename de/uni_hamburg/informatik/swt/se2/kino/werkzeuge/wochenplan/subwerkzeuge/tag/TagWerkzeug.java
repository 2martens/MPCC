package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan.subwerkzeuge.tag;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Uhrzeit;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Film;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Tagesplan;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.services.kino.KinoService;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan.subwerkzeuge.vorstellung.VorstellungWerkzeug;

/**
 * Mit diesem Werkzeug können Tagespläne bearbeitet werden.
 * 
 * @author Jim Martens
 * @version 20.06.2013
 * @copyright 2013 Jim Martens
 */
public class TagWerkzeug implements Observer
{
    private TagWerkzeugUI _ui;
    private Tagesplan _tagesplan;
    private KinoService _kinoService;
    private Kinosaal _kinosaal;
    
    // Subwerkzeuge
    private VorstellungWerkzeug _1100werkzeug;
    private VorstellungWerkzeug _1500werkzeug;
    private VorstellungWerkzeug _1730werkzeug;
    private VorstellungWerkzeug _2000werkzeug;
    private VorstellungWerkzeug _2230werkzeug;
    
    /**
     * Initialisiert das TagWerkzeug.
     * 
     * @param kinoService
     *            Der KinoService, mit dem gearbeitet wird
     * @param kinosaal
     *            Der aktuelle Kinosaal
     * @param datum
     *            Das aktuelle Datum
     * 
     * @require kinoService != null
     * @require kinosaal != null
     * @require datum != null
     */
    public TagWerkzeug(KinoService kinoService, Kinosaal kinosaal, Datum datum)
    {
        assert kinoService != null : "Vorbedingung verletzt: kinoService != null";
        assert kinosaal != null : "Vorbedingung verletzt: kinosaal != null";
        assert datum != null : "Vorbedingung verletzt: datum != null";
        
        _kinoService = kinoService;
        _kinosaal = kinosaal;
        _tagesplan = _kinoService.getTagesplan(datum);
        
        // Subwerkzeuge initialisieren
        _1100werkzeug = new VorstellungWerkzeug(_kinoService, _kinosaal,
                new Uhrzeit(11, 00), _tagesplan.getDatum());
        _1500werkzeug = new VorstellungWerkzeug(_kinoService, _kinosaal,
                new Uhrzeit(15, 00), _tagesplan.getDatum());
        _1730werkzeug = new VorstellungWerkzeug(_kinoService, _kinosaal,
                new Uhrzeit(17, 30), _tagesplan.getDatum());
        _2000werkzeug = new VorstellungWerkzeug(_kinoService, _kinosaal,
                new Uhrzeit(20, 00), _tagesplan.getDatum());
        _2230werkzeug = new VorstellungWerkzeug(_kinoService, _kinosaal,
                new Uhrzeit(22, 30), _tagesplan.getDatum());
        
        // bei Subwerkzeugen als Beobachter melden
        _1100werkzeug.addObserver(this);
        _1500werkzeug.addObserver(this);
        _1730werkzeug.addObserver(this);
        _2000werkzeug.addObserver(this);
        _2230werkzeug.addObserver(this);
        
        _ui = new TagWerkzeugUI(_1100werkzeug.getUIPanel(),
                _1500werkzeug.getUIPanel(), _1730werkzeug.getUIPanel(),
                _2000werkzeug.getUIPanel(), _2230werkzeug.getUIPanel());
    }
    
    /**
     * Setzt den Kinosaal.
     * 
     * @param kinosaal
     * 
     * @require kinosaal != null
     */
    public void setKinosaal(Kinosaal kinosaal)
    {
        assert kinosaal != null : "Vorbedingung verletzt: kinosaal != null";
        _kinosaal = kinosaal;
        aktualisiereKinosaele();
    }
    
    /**
     * Setzt den Tagesplan.
     * 
     * @param tagesplan
     * 
     * @require tagesplan != null
     */
    public void setTagesplan(Tagesplan tagesplan)
    {
        assert tagesplan != null : "Vorbedingung verletzt: tagesplan != null";
        _tagesplan = tagesplan;
        aktualisiereUI();
    }
    
    /**
     * Aktualisiert die Vorstellungen der Subwerkzeuge.
     */
    public void aktualisiereVorstellungen()
    {
        _1100werkzeug.aktualisiereVorstellung();
        _1500werkzeug.aktualisiereVorstellung();
        _1730werkzeug.aktualisiereVorstellung();
        _2000werkzeug.aktualisiereVorstellung();
        _2230werkzeug.aktualisiereVorstellung();
    }
    
    /**
     * Gibt das UI-Panel zurück.
     */
    public JPanel getUIPanel()
    {
        return _ui.getUIPanel();
    }
    
    /**
     * Aktualisiert die Kinosäle der Subwerkzeuge.
     */
    private void aktualisiereKinosaele()
    {
        _1100werkzeug.setKinosaal(_kinosaal);
        _1500werkzeug.setKinosaal(_kinosaal);
        _1730werkzeug.setKinosaal(_kinosaal);
        _2000werkzeug.setKinosaal(_kinosaal);
        _2230werkzeug.setKinosaal(_kinosaal);
    }
    
    /**
     * Leitet die Vorstellungen des Tagesplans an die Subwerkzeuge weiter.
     */
    private void aktualisiereUI()
    {
        List<Vorstellung> vorstellungen = _tagesplan.getVorstellungen();
        _1100werkzeug.setVorstellung(null);
        _1500werkzeug.setVorstellung(null);
        _1730werkzeug.setVorstellung(null);
        _2000werkzeug.setVorstellung(null);
        _2230werkzeug.setVorstellung(null);
        for (Vorstellung vorstellung : vorstellungen)
        {
            Uhrzeit anfang = vorstellung.getAnfangszeit();
            int stunden = anfang.getStunden();
            switch (stunden)
            {
                case 11:
                    _1100werkzeug.setVorstellung(vorstellung);
                    break;
                case 15:
                    _1500werkzeug.setVorstellung(vorstellung);
                    break;
                case 17:
                    _1730werkzeug.setVorstellung(vorstellung);
                    break;
                case 20:
                    _2000werkzeug.setVorstellung(vorstellung);
                    break;
                case 22:
                    _2230werkzeug.setVorstellung(vorstellung);
                    break;
            }
        }
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        if (o instanceof VorstellungWerkzeug)
        {
            VorstellungWerkzeug vorstellungWerkzeug = (VorstellungWerkzeug) o;
            String parameter = (String) arg;
            if (vorstellungWerkzeug == _1100werkzeug)
            {
                reagiereAufAenderung(vorstellungWerkzeug, new Uhrzeit(11, 00),
                        parameter);
            }
            else if (vorstellungWerkzeug == _1500werkzeug)
            {
                reagiereAufAenderung(vorstellungWerkzeug, new Uhrzeit(15, 00),
                        parameter);
            }
            else if (vorstellungWerkzeug == _1730werkzeug)
            {
                reagiereAufAenderung(vorstellungWerkzeug, new Uhrzeit(17, 30),
                        parameter);
            }
            else if (vorstellungWerkzeug == _2000werkzeug)
            {
                reagiereAufAenderung(vorstellungWerkzeug, new Uhrzeit(20, 00),
                        parameter);
            }
            else if (vorstellungWerkzeug == _2230werkzeug)
            {
                reagiereAufAenderung(vorstellungWerkzeug, new Uhrzeit(22, 30),
                        parameter);
            }
        }
    }
    
    /**
     * Reagiert auf eine Änderung der Subwerkzeuge.
     * 
     * @param werkzeug
     *            Das Subwerkzeug, von dem die Änderung ausgeht.
     * @param startZeit
     *            Die Startzeit für Vorstellungen des Subwerkzeuges.
     * @param parameter
     *            Der Eventparameter, der darlegt, was passiert ist.
     */
    private void reagiereAufAenderung(VorstellungWerkzeug werkzeug,
            Uhrzeit startZeit, String parameter)
    {
        switch (parameter)
        {
            case "Vorstellung-create":
                Film film = werkzeug.getSelectedFilm();
                Vorstellung neueVorstellung = new Vorstellung(_kinosaal, film,
                        startZeit, _tagesplan.getDatum(),
                        Vorstellung.TICKETPREIS);
                _kinoService.fuegeVorstellungHinzu(neueVorstellung,
                        _tagesplan.getDatum(), _kinosaal, startZeit);
                werkzeug.setVorstellung(neueVorstellung);
                aktualisiereVorstellungen();
                break;
            case "Vorstellung-remove":
                _kinoService.entferneVorstellung(werkzeug.getVorstellung());
                break;
            case "Vorstellungsanzeige":
                aktualisiereVorstellungen();
                break;
        }
    }
}
