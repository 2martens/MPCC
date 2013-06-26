package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.planung;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Woche;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;
import de.uni_hamburg.informatik.swt.se2.kino.services.kino.KinoService;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.kinosaalauswaehler.KinosaalAuswaehlWerkzeug;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenauswaehler.WochenAuswaehlWerkzeug;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan.WochenplanWerkzeug;

/**
 * Das Planungswerkzeug. Mit diesem Werkzeug kann die Benutzerin oder der
 * Benutzer für jeden Kinosaal Wochenpläne für Vorstellungen erstellen.
 * 
 * @author Jim Martens
 * @version 18.06.2013
 * @copyright 2013 Jim Martens
 */
public class PlanungsWerkzeug implements Observer
{
    private PlanungsWerkzeugUI _ui;
    
    private WochenAuswaehlWerkzeug _wochenAuswaehlWerkzeug;
    private KinosaalAuswaehlWerkzeug _kinosaalAuswaehlWerkzeug;
    private WochenplanWerkzeug _wochenplanWerkzeug;
    
    /**
     * Initialisiert das Planungswerkzeug.
     * 
     * @param kinoService
     *            Der KinoService, mit dem das Werkzeug arbeitet.
     * 
     * @require kinoService != null
     */
    public PlanungsWerkzeug(KinoService kinoService)
    {
        assert kinoService != null : "Vorbedingung verletzt: kinoService != null";
        
        _wochenAuswaehlWerkzeug = new WochenAuswaehlWerkzeug();
        _kinosaalAuswaehlWerkzeug = new KinosaalAuswaehlWerkzeug(
                kinoService.getKinosaele());
        
        Woche woche = _wochenAuswaehlWerkzeug.getSelektierteWoche();
        Kinosaal kinosaal = _kinosaalAuswaehlWerkzeug
                .getAusgewaehlterKinosaal();
        
        _wochenplanWerkzeug = new WochenplanWerkzeug(kinoService, kinosaal,
                woche);
        
        _wochenAuswaehlWerkzeug.addObserver(this);
        _kinosaalAuswaehlWerkzeug.addObserver(this);
        
        _ui = new PlanungsWerkzeugUI(_wochenplanWerkzeug.getUIPanel(),
                _wochenAuswaehlWerkzeug.getUIPanel(),
                _kinosaalAuswaehlWerkzeug.getUIPanel());
    }
    
    /**
     * Gibt das Haupt-Panel der UI zurück.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _ui.getUIPanel();
    }
    
    /**
     * Aktualisiert die Vorstellungen.
     */
    public void aktualisiereVorstellungen()
    {
        _wochenplanWerkzeug.aktualisiereVorstellungen();
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        if (o instanceof WochenAuswaehlWerkzeug)
        {
            _wochenplanWerkzeug.setWoche(_wochenAuswaehlWerkzeug
                    .getSelektierteWoche());
        }
        
        if (o instanceof KinosaalAuswaehlWerkzeug)
        {
            _wochenplanWerkzeug.setKinosaal(_kinosaalAuswaehlWerkzeug
                    .getAusgewaehlterKinosaal());
        }
    }
    
}
