package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenauswaehler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JPanel;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Woche;

/**
 * Mit diesem Werkzeug kann eine Woche ausgewählt werden.
 * 
 * Dieses Werkzeug ist ein eingebettetes Subwerkzeug. Es benachrichtigt sein
 * Kontextwerkzeug, wenn sich die ausgewählte Woche geändert hat.
 * 
 * @author Jim Martens
 * @version 18.06.2013
 * @copyright 2013 Jim Martens
 */
public class WochenAuswaehlWerkzeug extends Observable
{
    private WochenAuswaehlWerkzeugUI _ui;
    private Woche _ausgewaehlteWoche;
    
    /**
     * Initialisiert dieses Werkzeug. Die initial ausgewählte Woche ist die aktuelle Woche.
     */
    public WochenAuswaehlWerkzeug()
    {
        _ausgewaehlteWoche = Woche.dieseWoche();
        _ui = new WochenAuswaehlWerkzeugUI(
                _ausgewaehlteWoche.getFormatiertenString()
        );
        registriereUIAktionen();
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
     * Gibt die im Werkzeug ausgewählte Woche zurück.
     * 
     * @ensure result != null
     */
    public Woche getSelektierteWoche()
    {
        return _ausgewaehlteWoche;
    }
    
    /**
     * Diese Methode wird aufgerufen, wenn der Zurueck-Button gedrueckt wurde.
     */
    private void zurueckButtonWurdeGedrueckt()
    {
        _ausgewaehlteWoche = _ausgewaehlteWoche.letzteWoche();
        _ui.getDatumLabel()
                .setText(_ausgewaehlteWoche.getFormatiertenString());
        setChanged();
        notifyObservers();
    }
    
    /**
     * Diese Methode wird aufgerufen, wenn der Weiter-Button gedrueckt wurde.
     */
    private void weiterButtonWurdeGedrueckt()
    {
        _ausgewaehlteWoche = _ausgewaehlteWoche.naechsteWoche();
        _ui.getDatumLabel()
                .setText(_ausgewaehlteWoche.getFormatiertenString());
        setChanged();
        notifyObservers();
    }

    /**
     * Verbindet die fachlichen Aktionen mit den Interaktionselementen der
     * grafischen Benutzungsoberfläche.
     */
    private void registriereUIAktionen()
    {
        _ui.getZurueckButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                zurueckButtonWurdeGedrueckt();
            }
        });

        _ui.getWeiterButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                weiterButtonWurdeGedrueckt();
            }
        });     
    }
}
