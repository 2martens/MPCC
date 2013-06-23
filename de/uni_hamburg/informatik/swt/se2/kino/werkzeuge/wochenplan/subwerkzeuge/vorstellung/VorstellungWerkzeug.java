package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan.subwerkzeuge.vorstellung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Reinigungszeit;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Film;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Werbeblock;

/**
 * Mit diesem Werkzeug können Vorstellungen bearbeitet werden.
 * 
 * Das Kontextwerkzeug kann sich als Beobachter über Änderungen bezüglich
 * <code>Vorstellung-create</code> (Vorstellung erzeugt) und
 * <code>Vorstellung-remove</code> (Vorstellung entfernt) informieren.
 * 
 * Diese Schlüsselwerte werden im zweiten Parameter als String der
 * update-Methode übergeben.
 * 
 * @author Jim Martens
 * @version 19.06.2013
 * @copyright 2013 Jim Martens
 */
public class VorstellungWerkzeug extends Observable
{
    private VorstellungWerkzeugUI _ui;
    private Vorstellung _vorstellung;
    private Reinigungszeit _reinigungszeit;
    
    private Film _selectedFilm;
    private int _werbeblockMinuten;
    private FSK _werbeblockFSK;
    
    /**
     * Initialisiert das Werkzeug.
     * 
     * @param filme
     *            Eine Liste aller verfügbaren Filme.
     * @param kinosaalReinigungszeit
     * 
     * @require !filme.isEmpty()
     * @require kinosaalReinigungszeit != null
     */
    public VorstellungWerkzeug(List<Film> filme,
            Reinigungszeit kinosaalReinigungszeit)
    {
        assert !filme.isEmpty() : "Vorbedingung verletzt: !filme.isEmpty()";
        assert kinosaalReinigungszeit != null : "Vorbedingung verletzt: kinosaalReinigungszeit != null";
        
        _ui = new VorstellungWerkzeugUI();
        _reinigungszeit = kinosaalReinigungszeit;
        
        _werbeblockMinuten = 0;
        _werbeblockFSK = FSK.FSK0;
        _selectedFilm = filme.get(0);
        initGUI(filme);
        
        registriereUIAktionen();
    }
    
    /**
     * Setzt die Vorstellung.
     * 
     * @param vorstellung
     *            Die zu setzende Vorstellung (auch <code>null</code>).
     * 
     */
    public void setVorstellung(Vorstellung vorstellung)
    {
        _vorstellung = vorstellung;
        aktualisiereUI();
    }
    
    /**
     * Setzt die Kinosaal-gebundene Reinigungszeit.
     * 
     * @param reinigungszeit
     * 
     * @require reinigungszeit != null
     */
    public void setReinigungszeit(Reinigungszeit reinigungszeit)
    {
        assert reinigungszeit != null : "Vorbedingung verletzt: reinigungszeit != null";
        _reinigungszeit = reinigungszeit;
    }
    
    /**
     * Gibt die aktuelle Vorstellung zurück.
     */
    public Vorstellung getVorstellung()
    {
        return _vorstellung;
    }
    
    /**
     * Gibt den aktuell ausgewählten Film zurück.
     * 
     * @ensure result != null
     */
    public Film getSelectedFilm()
    {
        return _selectedFilm;
    }
    
    /**
     * Gibt die Dauer des Werbeblocks in Minuten zurück.
     */
    public int getWerbeblockMinuten()
    {
        return _werbeblockMinuten;
    }
    
    /**
     * Gibt die ausgewählte Werbeblock-FSK zurück.
     */
    public FSK getWerbeblockFSK()
    {
        return _werbeblockFSK;
    }
    
    /**
     * Gibt das UI-Panel zurück.
     */
    public JPanel getUIPanel()
    {
        return _ui.getUIPanel();
    }
    
    /**
     * Initialisiert die GUI.
     * 
     * @param filme
     */
    private void initGUI(List<Film> filme)
    {
        initialisiereFilmBox(filme);
        _ui.getVorstellungGruppe().hide();
    }
    
    /**
     * Initialisiert die Film-Box.
     * 
     * @param filme
     */
    private void initialisiereFilmBox(List<Film> filme)
    {
        List<FilmFormatierer> filmFormatierer = new ArrayList<FilmFormatierer>();
        JComboBox<FilmFormatierer> filmBox = _ui.getFilmBox();
        for (Film film : filme)
        {
            filmFormatierer.add(new FilmFormatierer(film));
        }
        for (FilmFormatierer formatierer : filmFormatierer)
        {
            filmBox.addItem(formatierer);
        }
        filmAusgewaehlt();
    }
    
    /**
     * Aktualisiert die UI.
     */
    private void aktualisiereUI()
    {
        if (_vorstellung != null)
        {
            _ui.getVorstellungCheckBox().setSelected(true);
            
            // Film aktualisieren
            Film film = _vorstellung.getFilm();
            JComboBox<FilmFormatierer> filmBox = _ui.getFilmBox();
            
            for (int i = 0; i < filmBox.getItemCount(); i++)
            {
                FilmFormatierer formatierer = filmBox.getItemAt(i);
                if (formatierer.getFilm().equals(film))
                {
                    filmBox.setSelectedItem(formatierer);
                    break;
                }
            }
            aktualisiereFSKBox();
            aktualisiereWerbeblockMinuten();
            aktualisiereReinigungszeit();
        }
        else
        {
            _ui.getVorstellungCheckBox().setSelected(false);
        }
    }
    
    /**
     * Registriert die UI-Aktionen.
     */
    private void registriereUIAktionen()
    {
        _ui.getFilmBox().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                filmAusgewaehlt();
            }
        });
        
        _ui.getFSKBox().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                fskAusgewaehlt();
            }
        });
        
        _ui.getWerbeblockMinutenInput().getDocument()
                .addDocumentListener(new DocumentListener()
                {
                    @Override
                    public void insertUpdate(DocumentEvent e)
                    {
                        werbeblockDauerGeaendert();
                    }
                    
                    @Override
                    public void removeUpdate(DocumentEvent e)
                    {
                        werbeblockDauerGeaendert();
                    }
                    
                    @Override
                    public void changedUpdate(DocumentEvent e)
                    {
                        werbeblockDauerGeaendert();
                    }
                });
        
        _ui.getVorstellungCheckBox().addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if (e.getStateChange() == ItemEvent.SELECTED)
                {
                    _ui.getVorstellungGruppe().show();
                    erzeugeVorstellung();
                }
                else
                {
                    _ui.getVorstellungGruppe().hide();
                    entferneVorstellung();
                }
            }
        });
        
        _ui.getReinigungszeitCheckBox().addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if (e.getStateChange() == ItemEvent.SELECTED)
                {
                    reinigungszeitEinplanen();
                }
                else
                {
                    reinigungszeitEntfernen();
                }
            }
        });
        
    }
    
    /**
     * Wird aufgerufen, wenn ein neuer Film ausgewählt wurde.
     */
    private void filmAusgewaehlt()
    {
        JComboBox<FilmFormatierer> filmBox = _ui.getFilmBox();
        Object selected = filmBox.getSelectedItem();
        if (selected instanceof FilmFormatierer)
        {
            FilmFormatierer selectedFormatierer = (FilmFormatierer) selected;
            _selectedFilm = selectedFormatierer.getFilm();
        }
        aktualisiereFSKBox();
        if (_vorstellung != null)
        {
            _vorstellung.setFilm(_selectedFilm);
        }
    }
    
    /**
     * Wird aufgerufen, wenn eine neue Werbeblock-FSK ausgewählt wurde.
     */
    private void fskAusgewaehlt()
    {
        JComboBox<FSKFormatierer> fskBox = _ui.getFSKBox();
        Object selected = fskBox.getSelectedItem();
        if (selected instanceof FSKFormatierer)
        {
            FSKFormatierer selectedFormatierer = (FSKFormatierer) selected;
            _werbeblockFSK = selectedFormatierer.getFSK();
        }
        if (_werbeblockMinuten == 0 && _vorstellung.hatWerbeblock())
        {
            _vorstellung.entferneWerbeblock();
        }
        else if (_werbeblockMinuten > 0)
        {
            Werbeblock werbeblock = new Werbeblock(_werbeblockMinuten,
                    _werbeblockFSK);
            _vorstellung.setWerbeblock(werbeblock);
        }
    }
    
    /**
     * Wird aufgerufen, wenn die Werbeblock-Dauer verändert wird.
     */
    private void werbeblockDauerGeaendert()
    {
        JTextField werbeblockMinuten = _ui.getWerbeblockMinutenInput();
        String input = werbeblockMinuten.getText();
        _werbeblockMinuten = Integer.parseInt(input);
        if (_werbeblockMinuten == 0 && _vorstellung.hatWerbeblock())
        {
            _vorstellung.entferneWerbeblock();
        }
        else if (_werbeblockMinuten > 0)
        {
            Werbeblock werbeblock = new Werbeblock(_werbeblockMinuten,
                    _werbeblockFSK);
            _vorstellung.setWerbeblock(werbeblock);
        }
    }
    
    /**
     * Aktualisiert die FSK-Box.
     */
    private void aktualisiereFSKBox()
    {
        JComboBox<FSKFormatierer> fskBox = _ui.getFSKBox();
        fskBox.removeAllItems();
        FSK filmFSK = _selectedFilm.getFSK();
        switch (filmFSK)
        {
            case FSK18:
                FSKFormatierer formatierer18 = new FSKFormatierer(FSK.FSK18);
                fskBox.addItem(formatierer18);
            case FSK16:
                FSKFormatierer formatierer16 = new FSKFormatierer(FSK.FSK16);
                fskBox.addItem(formatierer16);
            case FSK12:
                FSKFormatierer formatierer12 = new FSKFormatierer(FSK.FSK12);
                fskBox.addItem(formatierer12);
            case FSK6:
                FSKFormatierer formatierer6 = new FSKFormatierer(FSK.FSK6);
                fskBox.addItem(formatierer6);
            case FSK0:
                FSKFormatierer formatierer0 = new FSKFormatierer(FSK.FSK0);
                fskBox.addItem(formatierer0);
                break;
        }
    }
    
    /**
     * Aktualisiert das Werbeblockdauer-Textfeld.
     */
    private void aktualisiereWerbeblockMinuten()
    {
        JTextField werbeblockMinuten = _ui.getWerbeblockMinutenInput();
        if (_vorstellung.hatWerbeblock())
        {
            Werbeblock werbeblock = _vorstellung.getWerbeblock();
            werbeblockMinuten.setText(String.valueOf(werbeblock.getLaenge()));
        }
    }
    
    /**
     * Aktualisiert die Reinigungszeit.
     */
    private void aktualisiereReinigungszeit()
    {
        if (_vorstellung.hatReinigungszeit())
        {
            _ui.getReinigungszeitCheckBox().setSelected(true);
        }
        else
        {
            _ui.getReinigungszeitCheckBox().setSelected(false);
        }
    }
    
    /**
     * Wird aufgerufen, wenn eine Vorstellung eingeplant werden soll.
     */
    private void erzeugeVorstellung()
    {
        if (_vorstellung == null)
        {
            setChanged();
            notifyObservers("Vorstellung-create");
        }
    }
    
    /**
     * Entfernt die Vorstellung.
     */
    private void entferneVorstellung()
    {
        if (_vorstellung != null)
        {
            setChanged();
            notifyObservers("Vorstellung-remove");
            
            _vorstellung = null;
        }
    }
    
    /**
     * Entfernt die Reinigungszeit.
     */
    private void reinigungszeitEntfernen()
    {
        _vorstellung.entferneReinigungszeit();
    }
    
    /**
     * Plant die Reinigungszeit ein.
     */
    private void reinigungszeitEinplanen()
    {
        _vorstellung.setReinigungszeit(_reinigungszeit);
    }
}
