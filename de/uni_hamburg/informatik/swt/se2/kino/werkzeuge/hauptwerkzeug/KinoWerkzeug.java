package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.hauptwerkzeug;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kino;
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
    private KinoWerkzeugUI _ui;
    private Kino _kino;
    
    private KassenWerkzeug _kassenWerkzeug;
    private PlanungsWerkzeug _planungsWerkzeug;
    
    /**
     * Initialisiert das Werkzeug.
     * 
     * @param kino
     * 
     * @require kino != null
     */
    public KinoWerkzeug(Kino kino)
    {
        assert kino != null : "Vorbedingung verletzt: kino != null";
        
        _kino = kino;
        
        _kassenWerkzeug = new KassenWerkzeug(_kino);
        _planungsWerkzeug = new PlanungsWerkzeug(_kino);
        
        _ui = new KinoWerkzeugUI(_kassenWerkzeug.getUIPanel(),
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
