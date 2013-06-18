package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.planung;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kino;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.kinosaalauswaehler.KinosaalAuswaehlWerkzeug;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenauswaehler.WochenAuswaehlWerkzeug;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan.WochenplanWerkzeug;

/**
 * Das Planungswerkzeug. Mit diesem Werkzeug kann die Benutzerin oder der Benutzer
 * für jeden Kinosaal Wochenpläne für Vorstellungen erstellen.
 * 
 * @author Jim Martens
 * @version 18.06.2013
 * @copyright 2013 Jim Martens
 */
public class PlanungsWerkzeug implements Observer
{
    private PlanungsWerkzeugUI _ui;
    private Kino _kino;
    
    private WochenAuswaehlWerkzeug _wochenAuswaehlWerkzeug;
    private KinosaalAuswaehlWerkzeug _kinosaalAuswaehlWerkzeug;
    private WochenplanWerkzeug _wochenplanWerkzeug;
    
    public PlanungsWerkzeug(Kino kino)
    {
        _kino = kino;
        
        _wochenAuswaehlWerkzeug = new WochenAuswaehlWerkzeug();
        _kinosaalAuswaehlWerkzeug = new KinosaalAuswaehlWerkzeug(_kino);
        _wochenplanWerkzeug = new WochenplanWerkzeug(_kino);
        
        _wochenAuswaehlWerkzeug.addObserver(this);
        _kinosaalAuswaehlWerkzeug.addObserver(this);
        
        _ui = new PlanungsWerkzeugUI(
            _wochenplanWerkzeug.getUIPanel(), 
            _wochenAuswaehlWerkzeug.getUIPanel(), 
            _kinosaalAuswaehlWerkzeug.getUIPanel()
        );
        
        _wochenplanWerkzeug.setWoche(_wochenAuswaehlWerkzeug.getSelektierteWoche());
        _wochenplanWerkzeug.setKinosaal(_kinosaalAuswaehlWerkzeug.getAusgewaehlterKinosaal());
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
    
    @Override
    public void update(Observable o, Object arg)
    {
        if (o instanceof WochenAuswaehlWerkzeug)
        {
            _wochenplanWerkzeug.setWoche(_wochenAuswaehlWerkzeug.getSelektierteWoche());
        }
        
        if (o instanceof KinosaalAuswaehlWerkzeug)
        {
            _wochenplanWerkzeug.setKinosaal(_kinosaalAuswaehlWerkzeug.getAusgewaehlterKinosaal());
        }
    }
    
}
