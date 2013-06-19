package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Tag;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Woche;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Film;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kino;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Wochenplan;

/**
 * Mit diesem Werkzeug können Wochenpläne bearbeitet werden. Es können
 * Vorstellungen hinzugefügt und entfernt werden.
 * 
 * @author Jim Martens
 * @version 18.06.2013
 * @copyright 2013 Jim Martens
 */
public class WochenplanWerkzeug
{
    private WochenplanWerkzeugUI _ui;
    private Wochenplan _wochenplan;
    private Kinosaal _kinosaal;
    private Woche _woche;
    private Kino _kino;
    
    /**
     * Initialisiert dieses Werkzeug.
     * 
     * @param kino
     * 
     * @require kino != null
     */
    public WochenplanWerkzeug(Kino kino)
    {
        assert kino != null : "Vorbedingung verletzt: kino != null";
        
        _ui = new WochenplanWerkzeugUI();
        _kino = kino;
        
        registriereUIAktionen();
        _woche = null;
        _kinosaal = null;
        aktualisiereWochenplan();
        aktualisiereUI();
        
        initFilmListen();
    }
    
    /**
     * Setzt die Woche.
     * 
     * @param woche Die zu setzende Woche (auch <code>null</code>).
     */
    public void setWoche(Woche woche)
    {
        _woche = woche;
        aktualisiereWochenplan();
    }
    
    /**
     * Setzt den Kinosaal.
     * 
     * @param woche Der zu setzende Kinosaal (auch <code>null</code>).
     */
    public void setKinosaal(Kinosaal kinosaal)
    {
        _kinosaal = kinosaal;
        aktualisiereWochenplan();
    }

    /**
     * Gibt das Panel dieses Subwerkzeugs zurück. Das Panel sollte von einem
     * Kontextwerkzeug eingebettet werden.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _ui.getUIPanel();
    }
    
    /**
     * Aktualisiert den Wochenplan.
     */
    private void aktualisiereWochenplan()
    {
        if (_woche != null && _kinosaal != null)
        {
            if (_kino.istWochenplanVorhanden(_kinosaal, _woche))
            {
                _wochenplan = _kino.getWochenplan(_kinosaal, _woche);
            }
            else
            {
                _wochenplan = new Wochenplan(_woche, _kinosaal);
                _kino.setWochenplan(_kinosaal, _woche, _wochenplan);
            }
            aktualisiereUI();
        }
    }
    
    /**
     * Aktualisiert die Anzeige.
     */
    private void aktualisiereUI()
    {
        if (_wochenplan != null)
        {
            // TODO: UI die nötigen Infos übergeben
            // aktualisiere Datumsanzeigen
            List<Tag> tage = _woche.getWochentage();
            List<String> datumsTexte = new ArrayList<String>(7);
            datumsTexte.add(tage.get(0).getDatum().getFormatiertenString());
            datumsTexte.add(tage.get(1).getDatum().getFormatiertenString());
            datumsTexte.add(tage.get(2).getDatum().getFormatiertenString());
            datumsTexte.add(tage.get(3).getDatum().getFormatiertenString());
            datumsTexte.add(tage.get(4).getDatum().getFormatiertenString());
            datumsTexte.add(tage.get(5).getDatum().getFormatiertenString());
            datumsTexte.add(tage.get(6).getDatum().getFormatiertenString());
            _ui.aktualisiereDatumsLabel(datumsTexte);
            
            
        }    
        else
        {
            // TODO: keine Veranstaltungen anzeigen
        }
    }
    
    /**
     * Initialisiert die Filmlisten.
     */
    private void initFilmListen()
    {
        List<JComboBox<FilmFormatierer>> filmListen = _ui.getFilmListen();
        for (Film film : _kino.getFilme())
        {
            FilmFormatierer formatierer = new FilmFormatierer(film);
            for (JComboBox<FilmFormatierer> filmBox : filmListen)
            {
                filmBox.addItem(formatierer);
            }
        }
    }

    /**
     * Registriert die UI-Aktionen.
     */
    private void registriereUIAktionen()
    {
        
    }
}
