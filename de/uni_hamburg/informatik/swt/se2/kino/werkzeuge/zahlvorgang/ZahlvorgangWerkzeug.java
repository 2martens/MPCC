package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.zahlvorgang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Geldbetrag;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.hauptwerkzeug.KinoWerkzeug;

/**
 * Mit diesem Werkzeug kann der Benutzer einen Bar-Zahlvorgang durchführen.
 * 
 * Dieses Werkzeug ist ein Subwerkzeug. Es benachrichtigt seine Beobachter, wenn
 * der Zahlvorgang abgeschlossen wurde.
 * 
 * @author Jim Martens, Julian Tobergte, Lina Kaine, Cansu Kartoglu
 * @version 27.06.2013
 */
public class ZahlvorgangWerkzeug
{
    private ZahlvorgangWerkzeugUI _ui;
    
    private Geldbetrag _zuZahlen;
    private boolean _bezahlt;
    
    /**
     * Initialisiert dieses Werkzeug.
     * 
     * @param elternPanel
     * 
     * @require elternPanel != null
     */
    public ZahlvorgangWerkzeug(JPanel elternPanel)
    {
        assert elternPanel != null : "Vorbedingung verletzt: elternPanel != null";
        
        _ui = new ZahlvorgangWerkzeugUI(elternPanel, KinoWerkzeug.APP_FRAME);
        _bezahlt = false;
        
        initGUI();
        registriereUIAktionen();
    }
    
    /**
     * Startet einen Zahlvorgang. Diese Methode ist erst beendet, wenn der
     * Benutzer den Zahlungsvorgang erfolgreich beendet oder abgebrochen hat.
     * 
     * @param zuZahlen
     *            Der zu zahlende Geldbetrag
     * 
     * @require zuZahlen != null
     */
    public void starteZahlvorgang(Geldbetrag zuZahlen)
    {
        assert zuZahlen != null : "Vorbedingung verletzt: zuZahlen != null";
        _zuZahlen = zuZahlen;
        aktualisiereUI();
        _ui.zeigeDialog();
    }
    
    /**
     * Gibt an, ob die Karten bezahlt wurden.
     * 
     * @return <code>true</code>, wenn Zahlungsvorgang erfolgreich abgeschlossen
     *         wurde, <code>false</code>, wenn abgebrochen wurde
     */
    public boolean wurdeBezahlt()
    {
        return _bezahlt;
    }
    
    /**
     * Registriert UI-Aktionen.
     */
    private void registriereUIAktionen()
    {
        _ui.getOKButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                _bezahlt = true;
                _ui.versteckeDialog();
            }
        });
        _ui.getAbbrechenButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                _bezahlt= false;
                _ui.versteckeDialog();
            }
        });
        
        _ui.getGegebenEuroTextField().getDocument()
                .addDocumentListener(new DocumentListener()
                {
                    @Override
                    public void insertUpdate(DocumentEvent e)
                    {
                        gegebenVeraendert();
                    }
                    
                    @Override
                    public void removeUpdate(DocumentEvent e)
                    {
                        gegebenVeraendert();
                    }
                    
                    @Override
                    public void changedUpdate(DocumentEvent e)
                    {
                    }
                    
                });
        _ui.getGegebenCentTextField().getDocument()
                .addDocumentListener(new DocumentListener()
                {
                    @Override
                    public void insertUpdate(DocumentEvent e)
                    {
                        gegebenVeraendert();
                    }
                    
                    @Override
                    public void removeUpdate(DocumentEvent e)
                    {
                        gegebenVeraendert();
                    }
                    
                    @Override
                    public void changedUpdate(DocumentEvent e)
                    {
                    }
                    
                });
    }
    
    /**
     * Wird aufgerufen, wenn der gegebene Betrag sich geändert hat.
     */
    private void gegebenVeraendert()
    {
        String euroEingabe = _ui.getGegebenEuroTextField().getText();
        String centEingabe = _ui.getGegebenCentTextField().getText();
        int eurocentGegeben = Integer.parseInt(euroEingabe + centEingabe);
        String zuZahlenEuro = String.valueOf(_zuZahlen.getEuroAnteil());
        String zuZahlenCent;
        if (_zuZahlen.getCentAnteil() < 10)
        {
            zuZahlenCent = "0" + String.valueOf(_zuZahlen.getCentAnteil());
        }
        else
        {
            zuZahlenCent = String.valueOf(_zuZahlen.getCentAnteil());
        }
        
        String zuZahlenString = zuZahlenEuro + zuZahlenCent;
        int eurocentZuZahlen = Integer.parseInt(zuZahlenString);
        int eurocentRueckgeld = eurocentGegeben - eurocentZuZahlen;
        
        _ui.getOKButton()
                .setEnabled(istAbschliessenMoeglich(eurocentRueckgeld));
        
        if (eurocentRueckgeld >= 0)
        {
            Geldbetrag rueckgeld = new Geldbetrag(eurocentRueckgeld);
            _ui.getRueckgeldLabel().setText(rueckgeld.getFormatiertenString());
        }
        else
        {
            _ui.getRueckgeldLabel().setText("-");
        }
    }
    
    /**
     * Aktualisiert die UI.
     */
    private void aktualisiereUI()
    {
        _ui.getZuZahlenLabel().setText(_zuZahlen.getFormatiertenString());
    }
    
    /**
     * Initialisiert die GUI.
     */
    private void initGUI()
    {
        _ui.getOKButton().setEnabled(false);
    }
    
    /**
     * Prüft, ob das erfolgreiche Abschließen des Zahlvorganges möglich ist.
     * 
     * @param eurocentRueckgeld
     * 
     * @return <code>true</code>, wenn ein erfolgreiches Abschließen möglich
     *         ist, <code>false</code>, wenn nicht
     */
    private boolean istAbschliessenMoeglich(int eurocentRueckgeld)
    {
        if (eurocentRueckgeld >= 0)
        {
            return true;
        }
        return false;
    }
}
