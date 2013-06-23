package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Tag;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Woche;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kino;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Tagesplan;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Wochenplan;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan.subwerkzeuge.tag.TagWerkzeug;

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
    
    private TagWerkzeug _donnerstagWerkzeug;
    private TagWerkzeug _freitagWerkzeug;
    private TagWerkzeug _samstagWerkzeug;
    private TagWerkzeug _sonntagWerkzeug;
    private TagWerkzeug _montagWerkzeug;
    private TagWerkzeug _dienstagWerkzeug;
    private TagWerkzeug _mittwochWerkzeug;
    
    /**
     * Initialisiert dieses Werkzeug.
     * 
     * @param kino
     * @param kinosaal
     * @param woche
     * 
     * @require kino != null
     * @require kinosaal != null
     * @require woche != null
     */
    public WochenplanWerkzeug(Kino kino, Kinosaal kinosaal, Woche woche)
    {
        assert kino != null : "Vorbedingung verletzt: kino != null";
        assert kinosaal != null : "Vorbedingung verletzt: kinosaal != null";
        assert woche != null : "Vorbedingung verletzt: woche != null";
        
        _kino = kino;
        _woche = woche;
        _kinosaal = kinosaal;
        
        // Subwerkzeuge initialisieren
        _donnerstagWerkzeug = new TagWerkzeug(_kino, _kinosaal);
        _freitagWerkzeug = new TagWerkzeug(_kino, _kinosaal);
        _samstagWerkzeug = new TagWerkzeug(_kino, _kinosaal);
        _sonntagWerkzeug = new TagWerkzeug(_kino, _kinosaal);
        _montagWerkzeug = new TagWerkzeug(_kino, _kinosaal);
        _dienstagWerkzeug = new TagWerkzeug(_kino, _kinosaal);
        _mittwochWerkzeug = new TagWerkzeug(_kino, _kinosaal);
        
        _ui = new WochenplanWerkzeugUI(_donnerstagWerkzeug.getUIPanel(),
                _freitagWerkzeug.getUIPanel(), _samstagWerkzeug.getUIPanel(),
                _sonntagWerkzeug.getUIPanel(), _montagWerkzeug.getUIPanel(),
                _dienstagWerkzeug.getUIPanel(), _mittwochWerkzeug.getUIPanel());
        
        aktualisiereWochenplan();
    }
    
    /**
     * Setzt die Woche.
     * 
     * @param woche
     *            Die zu setzende Woche.
     * 
     * @require woche != null
     */
    public void setWoche(Woche woche)
    {
        assert woche != null : "Vorbedingung verletzt: woche != null";
        _woche = woche;
        aktualisiereWochenplan();
    }
    
    /**
     * Setzt den Kinosaal.
     * 
     * @param kinosaal
     *            Der zu setzende Kinosaal.
     * 
     * @require kinosaal != null
     */
    public void setKinosaal(Kinosaal kinosaal)
    {
        assert kinosaal != null : "Vorbedingung verletzt: kinosaal != null";
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
            
            // aktualisiere Subwerkzeug-UIs
            for (Tag tag : tage)
            {
                Tagesplan tagesplan = _wochenplan.getTagesplan(tag.getDatum());
                switch (tag.getWochentag())
                {
                    case DONNERSTAG:
                        _donnerstagWerkzeug.setTagesplan(tagesplan);
                        _donnerstagWerkzeug.setKinosaal(_kinosaal);
                        break;
                    case FREITAG:
                        _freitagWerkzeug.setTagesplan(tagesplan);
                        _freitagWerkzeug.setKinosaal(_kinosaal);
                        break;
                    case SAMSTAG:
                        _samstagWerkzeug.setTagesplan(tagesplan);
                        _samstagWerkzeug.setKinosaal(_kinosaal);
                        break;
                    case SONNTAG:
                        _sonntagWerkzeug.setTagesplan(tagesplan);
                        _sonntagWerkzeug.setKinosaal(_kinosaal);
                        break;
                    case MONTAG:
                        _montagWerkzeug.setTagesplan(tagesplan);
                        _montagWerkzeug.setKinosaal(_kinosaal);
                        break;
                    case DIENSTAG:
                        _dienstagWerkzeug.setTagesplan(tagesplan);
                        _dienstagWerkzeug.setKinosaal(_kinosaal);
                        break;
                    case MITTWOCH:
                        _mittwochWerkzeug.setTagesplan(tagesplan);
                        _mittwochWerkzeug.setKinosaal(_kinosaal);
                        break;
                }
            }
        }
    }
}
