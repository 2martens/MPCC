package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.hauptwerkzeug;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import de.uni_hamburg.informatik.swt.se2.kino.services.kino.KinoService;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.kasse.KassenWerkzeug;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.planung.PlanungsWerkzeug;

/**
 * Das Gesamtwerkzeug des Kinosystems, das die anderen Werkzeuge zusammenfasst.
 * 
 * @author Jim Martens
 * @version 18.06.2013
 * @copyright 2013 Jim Martens
 */
public class KinoWerkzeug
{
    /**
     * Enthält das Anwendungsframe.
     */
    public static JFrame APP_FRAME; 
    
    private KinoWerkzeugUI _ui;
    
    private KassenWerkzeug _kassenWerkzeug;
    private PlanungsWerkzeug _planungsWerkzeug;
    
    /**
     * Initialisiert das Werkzeug.
     * 
     * @param kinoService
     * 
     * @require kino != null
     */
    public KinoWerkzeug(KinoService kinoService)
    {
        assert kinoService != null : "Vorbedingung verletzt: kinoService != null";
        
        _ui = new KinoWerkzeugUI();
        APP_FRAME = _ui.getUIFrame();
        
        _kassenWerkzeug = new KassenWerkzeug(kinoService);
        _planungsWerkzeug = new PlanungsWerkzeug(kinoService);
        
        _ui.initGUI(_kassenWerkzeug.getUIPanel(),
                _planungsWerkzeug.getUIPanel());
        
        registriereUIAktionen();
        _ui.zeigeFenster();
    }
    
    /**
     * Fügt die Funktionalitat zum Beenden-Button hinzu.
     */
    private void registriereUIAktionen()
    {
        _ui.getBeendenButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                reagiereAufBeendenButton();
            }
        });
        registriereZeigeKasseAktion();
        registriereZeigePlanungAktion();
    }
    
    /**
     * Registriert die Aktion, die ausgeführt wird, um die Planung anzuzeigen.
     */
    private void registriereZeigePlanungAktion()
    {
        _ui.getPlanungsButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                _ui.zeigePlanung();
                _planungsWerkzeug.aktualisiereVorstellungen();
            }
        });
    }

    /**
     * Registriert die Aktion, die ausgeführt wird, um die Kasse anzuzeigen.
     */
    private void registriereZeigeKasseAktion()
    {
        _ui.getKassenButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                _ui.zeigeKasse();
                _kassenWerkzeug.aktualisiereTagesplan();
            }
        });
    }

    /**
     * Beendet die Anwendung.
     */
    private void reagiereAufBeendenButton()
    {
        _ui.schliesseFenster();
    }
}
