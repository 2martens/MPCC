package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.kinosaalauswaehler;

import java.util.List;
import java.util.Observable;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;

/**
 * Mit diesem Werkzeug kann ein Kinosaal ausgewählt werden.
 * 
 * Dieses Werkzeug ist ein eingebettetes Subwerkzeug. Es benachrichtigt sein
 * Kontextwerkzeug, wenn sich der ausgewählte Kinosaal geändert hat.
 * 
 * @author Jim Martens
 * @version 18.06.2013
 * @copyright 2013 Jim Martens
 */
public class KinosaalAuswaehlWerkzeug extends Observable
{
    private KinosaalAuswaehlWerkzeugUI _ui;
    
    /**
     * Initialisiert das Werkzeug.
     * 
     * @param kinoSaele Eine Liste der Kinosäle.
     * 
     * @require !kinoSaele.isEmpty()
     */
    public KinosaalAuswaehlWerkzeug(List<Kinosaal> kinoSaele)
    {
        assert !kinoSaele.isEmpty() : "Vorbedingung verletzt: !kinoSaele.isEmpty()";
        
        _ui = new KinosaalAuswaehlWerkzeugUI();
        initialisiereAngezeigteKinosaalListe(kinoSaele);
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
     * Gibt den derzeit ausgewählten Kinosaal zurück. Wenn kein Kinosaal
     * ausgewählt ist, wird <code>null</code> zurückgegeben.
     */
    public Kinosaal getAusgewaehlterKinosaal()
    {
        Kinosaal result = null;
        KinosaalFormatierer adapter = (KinosaalFormatierer) _ui
                .getKinosaalAuswahlList().getSelectedValue();
        if (adapter != null)
        {
            result = adapter.getKinosaal();
        }

        return result;
    }
    
    /**
     * Diese Methode wird aufgerufen, wenn ein Kinosaal ausgewählt wurde.
     */
    private void kinosaalWurdeAusgewaehlt()
    {
        setChanged();
        notifyObservers();
    }
    
    /**
     * Initialisiert die Liste der Kinosäle.
     */
    private void initialisiereAngezeigteKinosaalListe(
            List<Kinosaal> kinosaele)
    {
        KinosaalFormatierer[] varray = new KinosaalFormatierer[kinosaele
                .size()];
        for (int i = 0; i < kinosaele.size(); i++)
        {
            varray[i] = new KinosaalFormatierer(kinosaele.get(i));
        }
        _ui.getKinosaalAuswahlList().setListData(varray);
        _ui.getKinosaalAuswahlList().setSelectedIndex(0);
    }
    
    /**
     * 
     * Verbindet die fachlichen Aktionen mit den Interaktionselementen der
     * graphischen Benutzungsoberfläche.
     */
    private void registriereUIAktionen()
    {
        _ui.getKinosaalAuswahlList().addListSelectionListener(
            new ListSelectionListener()
            {
                @Override
                public void valueChanged(ListSelectionEvent event)
                {
                    if (!event.getValueIsAdjusting())
                    {
                        kinosaalWurdeAusgewaehlt();
                    }
                }
            }
        );
    }
}
