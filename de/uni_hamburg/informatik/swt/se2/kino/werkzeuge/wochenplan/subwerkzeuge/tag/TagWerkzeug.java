package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan.subwerkzeuge.tag;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Reinigungszeit;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Uhrzeit;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Film;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kino;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Tagesplan;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
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
    private Kino _kino;
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
     * @param kino
     * @param kinosaal
     * 
     * @require kino != null
     * @require kinosaal != null
     */
    public TagWerkzeug(Kino kino, Kinosaal kinosaal)
    {
        assert kino != null : "Vorbedingung verletzt: kino != null";
        assert kinosaal != null : "Vorbedingung verletzt: kinosaal != null";
        
        _kino = kino;
        _kinosaal = kinosaal;
        _tagesplan = null;
        
        List<Film> filme = _kino.getFilme();
        Reinigungszeit kinosaalReinigungszeit = _kinosaal.getReinigungszeit();
        
        // Subwerkzeuge initialisieren
        _1100werkzeug = new VorstellungWerkzeug(filme, kinosaalReinigungszeit);
        _1500werkzeug = new VorstellungWerkzeug(filme, kinosaalReinigungszeit);
        _1730werkzeug = new VorstellungWerkzeug(filme, kinosaalReinigungszeit);
        _2000werkzeug = new VorstellungWerkzeug(filme, kinosaalReinigungszeit);
        _2230werkzeug = new VorstellungWerkzeug(filme, kinosaalReinigungszeit);
        
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
        aktualisiereReinigungszeiten();
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
     * Gibt das UI-Panel zurück.
     */
    public JPanel getUIPanel()
    {
        return _ui.getUIPanel();
    }
    
    /**
     * Aktualisiert die Reinigungszeiten der Subwerkzeuge.
     */
    private void aktualisiereReinigungszeiten()
    {
        _1100werkzeug.setReinigungszeit(_kinosaal.getReinigungszeit());
        _1500werkzeug.setReinigungszeit(_kinosaal.getReinigungszeit());
        _1730werkzeug.setReinigungszeit(_kinosaal.getReinigungszeit());
        _2000werkzeug.setReinigungszeit(_kinosaal.getReinigungszeit());
        _2230werkzeug.setReinigungszeit(_kinosaal.getReinigungszeit());
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
            case "Filmauswahl":
                _tagesplan.entferneVorstellung(werkzeug.getVorstellung());
            case "Vorstellung-create":
                Film film = werkzeug.getSelectedFilm();
                Uhrzeit endZeit = berechneEndZeit(startZeit, film.getLaenge());
                Vorstellung neueVorstellung = new Vorstellung(_kinosaal, film,
                        startZeit, endZeit, _tagesplan.getDatum(),
                        Vorstellung.TICKETPREIS);
                _tagesplan.fuegeVorstellungHinzu(neueVorstellung);
                werkzeug.setVorstellung(neueVorstellung);
                break;
            case "Vorstellung-remove":
                _tagesplan.entferneVorstellung(werkzeug.getVorstellung());
                break;
        }
    }
    
    /**
     * Berechnet die Endzeit für eine gegebene Startzeit und eine Länge.
     * 
     * @param startZeit
     *            Die Uhrzeit, von der ausgegangen wird.
     * @param laenge
     *            Die Dauer in Minuten, die hinzuaddiert werden soll.
     * 
     * @ensure result != null
     */
    private Uhrzeit berechneEndZeit(Uhrzeit startZeit, int laenge)
    {
        Uhrzeit endZeit;
        int stunden = startZeit.getStunden();
        stunden += laenge / 60;
        
        int minuten = startZeit.getMinuten();
        minuten += laenge % 60;
        
        stunden += minuten / 60;
        minuten = minuten % 60;
        
        stunden = (stunden >= 24 ? stunden - 24 : stunden);
        
        endZeit = new Uhrzeit(stunden, minuten);
        return endZeit;
    }
}
