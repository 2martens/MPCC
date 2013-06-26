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

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Reinigungszeit;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Uhrzeit;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Film;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Werbeblock;
import de.uni_hamburg.informatik.swt.se2.kino.services.kino.KinoService;

/**
 * Mit diesem Werkzeug können Vorstellungen bearbeitet werden.
 * 
 * Das Kontextwerkzeug kann sich als Beobachter über Änderungen informieren. Mit
 * dem Schlüsselwort "Frame" werden Änderungen gekennzeichnet, die an das
 * KinoWerkzeug weitergeleitet werden müssen.
 * 
 * @author Jim Martens
 * @version 19.06.2013
 * @copyright 2013 Jim Martens
 */
public class VorstellungWerkzeug extends Observable
{
    private KinoService _kinoService;
    private VorstellungWerkzeugUI _ui;
    private Uhrzeit _startzeit;
    private List<Film> _filme;
    
    private Datum _datum;
    private Kinosaal _kinosaal;
    private Vorstellung _vorstellung;
    
    private Film _selectedFilm;
    private int _werbeblockMinuten;
    private int _werbeblockMaxMinuten;
    private FSK _werbeblockFSK;
    private Reinigungszeit _reinigungszeit;
    private Werbeblock _werbeblock;
    
    private List<FilmFormatierer> _formatierer;
    private boolean _aufVorstellungsCheckboxReagieren;
    private boolean _aufReinigungsCheckboxReagieren;
    private boolean _aufFilmauswahlReagieren;
    private boolean _aufFSKAuswahlReagieren;
    private boolean _aufWerbeblockEingabeReagieren;
    
    /**
     * Initialisiert das Werkzeug.
     * 
     * @param kinoService
     *            Der KinoService, mit dem gearbeitet wird
     * @param kinosaal
     *            Der Kinosaal der Vorstellung, die dieses Werkzeug bearbeitet
     * @param startzeit
     *            Die Startzeit der Vorstellung, die dieses Werkzeug bearbeitet
     * @param datum
     *            Der Tag der Vorstellung, die dieses Werkzeug bearbeitet
     * @param vorstellung
     *            Die Vorstellung, die dieses Werkzeug bearbeitet (auch
     *            <code>null</code>)
     * 
     * @require kinoService != null
     * @require kinosaal != null
     * @require startzeit != null
     * @require datum != null
     */
    public VorstellungWerkzeug(KinoService kinoService, Kinosaal kinosaal,
            Uhrzeit startzeit, Datum datum, Vorstellung vorstellung)
    {
        assert kinoService != null : "Vorbedingung verletzt: kinoService != null";
        assert kinosaal != null : "Vorbedingung verletzt: kinosaal != null";
        assert startzeit != null : "Vorbedingung verletzt: startzeit != null";
        assert datum != null : "Vorbedingung verletzt: datum != null";
        
        _ui = new VorstellungWerkzeugUI();
        _kinosaal = kinosaal;
        _kinoService = kinoService;
        _startzeit = startzeit;
        _datum = datum;
        _vorstellung = vorstellung;
        _werbeblockMinuten = 0;
        _werbeblockMaxMinuten = 0;
        _werbeblockFSK = FSK.FSK0;
        _reinigungszeit = null;
        _werbeblock = null;
        _filme = _kinoService.getFilme();
        _selectedFilm = _filme.get(0);
        
        _aufVorstellungsCheckboxReagieren = true;
        _aufReinigungsCheckboxReagieren = true;
        _aufFilmauswahlReagieren = true;
        _aufFSKAuswahlReagieren = true;
        _aufWerbeblockEingabeReagieren = true;
        
        registriereUIAktionen();
        if (_vorstellung != null)
        {
            if (_vorstellung.hatReinigungszeit())
            {
                _reinigungszeit = _vorstellung.getReinigungszeit();
            }
            if (_vorstellung.hatWerbeblock())
            {
                _werbeblock = _vorstellung.getWerbeblock();
            }
            _selectedFilm = _vorstellung.getFilm();
        }
        initGUI();
        
        if (_vorstellung != null)
        {
            aktualisiereVorstellungsCheckbox();
            aktualisiereUI();
        }
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
        if (_vorstellung != null)
        {
            _selectedFilm = vorstellung.getFilm();
            if (_vorstellung.hatWerbeblock())
            {
                _werbeblock = _vorstellung.getWerbeblock();
            }
            if (_vorstellung.hatReinigungszeit())
            {
                _reinigungszeit = _vorstellung.getReinigungszeit();
            }
        }
        else
        {
            _werbeblock = null;
            _reinigungszeit = null;
        }
        aktualisiereVorstellungsCheckbox();
        aktualisiereUI();
    }
    
    /**
     * Setzt den Kinosaal.
     * 
     * @param kinosaal
     *            Der Kinosaal für die Vorstellung
     * 
     * @require kinosaal!= null
     */
    public void setKinosaal(Kinosaal kinosaal)
    {
        assert kinosaal != null : "Vorbedingung verletzt: kinosaal != null";
        _kinosaal = kinosaal;
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
     * Aktualisiert die Vorstellung.
     */
    public void aktualisiereVorstellung()
    {
        aktualisiereVorstellungsCheckbox();
        aktualisiereWerbeblockMaxMinuten();
        aktualisiereFilmauswahl();
        aktualisiereReinigungsCheckbox();
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
     */
    private void initGUI()
    {
        initialisiereFilmBox();
        versteckeVorstellungsdetails();
    }
    
    /**
     * Initialisiert die Film-Box.
     */
    private void initialisiereFilmBox()
    {
        List<FilmFormatierer> filmFormatierer = new ArrayList<FilmFormatierer>();
        JComboBox<FilmFormatierer> filmBox = _ui.getFilmBox();
        for (Film film : _filme)
        {
            boolean result = _kinoService
                    .istFilmZeigenMoeglich(film, _werbeblock, _reinigungszeit,
                            _kinosaal, _datum, _startzeit);
            
            if (result)
            {
                filmFormatierer.add(new FilmFormatierer(film));
            }
        }
        _aufFilmauswahlReagieren = false;
        for (FilmFormatierer formatierer : filmFormatierer)
        {
            filmBox.addItem(formatierer);
            if (_vorstellung != null
                    && formatierer.getFilm().equals(_selectedFilm))
            {
                filmBox.setSelectedItem(formatierer);
            }
        }
        _aufFilmauswahlReagieren = true;
        _formatierer = filmFormatierer;
        _selectedFilm = ((FilmFormatierer) filmBox.getSelectedItem()).getFilm();
    }
    
    /**
     * Aktualisiert die UI.
     */
    private void aktualisiereUI()
    {
        if (_vorstellung != null)
        {
            // Film aktualisieren
            JComboBox<FilmFormatierer> filmBox = _ui.getFilmBox();
            for (int i = 0; i < filmBox.getItemCount(); i++)
            {
                FilmFormatierer formatierer = ((FilmFormatierer) filmBox
                        .getItemAt(i));
                if (formatierer.getFilm().equals(_selectedFilm))
                {
                    _aufFilmauswahlReagieren = false;
                    filmBox.setSelectedItem(formatierer);
                    _aufFilmauswahlReagieren = true;
                    break;
                }
            }
            
            aktualisiereFSKBox();
            aktualisiereWerbeblockMinuten();
            aktualisiereReinigungszeit();
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
                if (_aufFilmauswahlReagieren)
                {
                    if (_ui.getFilmBox().getItemCount() > 0
                            && _vorstellung != null)
                    {
                        filmAusgewaehlt();
                    }
                }
            }
        });
        
        _ui.getFSKBox().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (_aufFSKAuswahlReagieren)
                {
                    if (_ui.getFSKBox().getItemCount() > 0)
                    {
                        fskAusgewaehlt();
                    }
                }
            }
        });
        
        _ui.getWerbeblockMinutenInput().getDocument()
                .addDocumentListener(new DocumentListener()
                {
                    @Override
                    public void insertUpdate(DocumentEvent e)
                    {
                        if (_aufWerbeblockEingabeReagieren)
                        {
                            werbeblockDauerGeaendert();
                        }
                    }
                    
                    @Override
                    public void removeUpdate(DocumentEvent e)
                    {
                    }
                    
                    @Override
                    public void changedUpdate(DocumentEvent e)
                    {
                    }
                });
        
        _ui.getVorstellungCheckBox().addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if (_aufVorstellungsCheckboxReagieren)
                {
                    if (e.getStateChange() == ItemEvent.SELECTED)
                    {
                        zeigeVorstellungsdetails();
                        erzeugeVorstellung();
                    }
                    else
                    {
                        versteckeVorstellungsdetails();
                        entferneVorstellung();
                    }
                }
            }
        });
        
        _ui.getReinigungszeitCheckBox().addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if (_aufReinigungsCheckboxReagieren)
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
        aktualisiereReinigungsCheckbox();
        aktualisiereWerbeblockMaxMinuten();
        if (_vorstellung != null)
        {
            _vorstellung.setFilm(_selectedFilm);
            
            setChanged();
            notifyObservers();
        }
    }
    
    /**
     * Wird aufgerufen, wenn eine neue Werbeblock-FSK ausgewählt wurde.
     */
    private void fskAusgewaehlt()
    {
        if (_vorstellung != null)
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
                _werbeblock = null;
            }
            else if (_werbeblockMinuten > 0)
            {
                _werbeblock = new Werbeblock(_werbeblockMinuten, _werbeblockFSK);
                _vorstellung.setWerbeblock(_werbeblock);
                aktualisiereWerbeblockMinuten();
            }
        }
    }
    
    /**
     * Wird aufgerufen, wenn die Werbeblock-Dauer verändert wird.
     */
    private void werbeblockDauerGeaendert()
    {
        JTextField werbeblockMinuten = _ui.getWerbeblockMinutenInput();
        String input = werbeblockMinuten.getText();
        int werbeblockMinutenInt = Integer.parseInt(input);
        if (werbeblockMinutenInt > _werbeblockMaxMinuten)
        {
            werbeblockMinutenInt = _werbeblockMaxMinuten;
        }
        _werbeblockMinuten = werbeblockMinutenInt;
        
        if (_werbeblockMinuten == 0 && _vorstellung.hatWerbeblock())
        {
            _vorstellung.entferneWerbeblock();
            _werbeblock = null;
        }
        else if (_werbeblockMinuten > 0)
        {
            _werbeblock = new Werbeblock(_werbeblockMinuten, _werbeblockFSK);
            _vorstellung.setWerbeblock(_werbeblock);
        }
        setChanged();
        notifyObservers();
    }
    
    /**
     * Aktualisiert die FSK-Box.
     */
    private void aktualisiereFSKBox()
    {
        JComboBox<FSKFormatierer> fskBox = _ui.getFSKBox();
        _aufFSKAuswahlReagieren = false;
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
        _aufFSKAuswahlReagieren = true;
    }
    
    /**
     * Aktualisiert das Werbeblockdauer-Textfeld.
     */
    private void aktualisiereWerbeblockMinuten()
    {
        JTextField werbeblockMinuten = _ui.getWerbeblockMinutenInput();
        if (_vorstellung.hatWerbeblock())
        {
            _aufWerbeblockEingabeReagieren = false;
            werbeblockMinuten.setText(String.valueOf(_werbeblock.getLaenge()));
            _aufWerbeblockEingabeReagieren = true;
        }
    }
    
    /**
     * Aktualisiert die Reinigungszeit.
     */
    private void aktualisiereReinigungszeit()
    {
        _aufReinigungsCheckboxReagieren = false;
        if (_vorstellung.hatReinigungszeit())
        {
            _ui.getReinigungszeitCheckBox().setSelected(true);
        }
        else
        {
            _ui.getReinigungszeitCheckBox().setSelected(false);
        }
        _aufReinigungsCheckboxReagieren = true;
    }
    
    /**
     * Aktualisiert die Filmauswahl.
     */
    private void aktualisiereFilmauswahl()
    {
        if (_vorstellung == null)
        {
            List<FilmFormatierer> filmFormatierer = new ArrayList<FilmFormatierer>();
            JComboBox<FilmFormatierer> filmBox = _ui.getFilmBox();
            for (Film film : _filme)
            {
                if (_kinoService.istFilmZeigenMoeglich(film, _werbeblock,
                        _reinigungszeit, _kinosaal, _datum, _startzeit))
                {
                    filmFormatierer.add(new FilmFormatierer(film));
                }
            }
            _aufFilmauswahlReagieren = false;
            if (!filmFormatierer.equals(_formatierer))
            {
                filmBox.removeAllItems();
                for (FilmFormatierer formatierer : filmFormatierer)
                {
                    filmBox.addItem(formatierer);
                    if (formatierer.getFilm().equals(_selectedFilm))
                    {
                        filmBox.setSelectedItem(formatierer);
                    }
                }
                _formatierer = filmFormatierer;
            }
            _aufFilmauswahlReagieren = true;
        }
    }
    
    /**
     * Aktualisiert die maximal erlaubte Anzahl an Werbeblock-Dauer für die
     * aktuelle Situation.
     */
    private void aktualisiereWerbeblockMaxMinuten()
    {
        if (_vorstellung != null)
        {
            _werbeblockMaxMinuten = _kinoService
                    .getWerbeblockMaximalDauer(_vorstellung);
        }
    }
    
    /**
     * Aktualisiert die Vorstellungs-Checkbox.
     */
    private void aktualisiereVorstellungsCheckbox()
    {
        if (_vorstellung != null)
        {
            _aufVorstellungsCheckboxReagieren = false;
            _ui.getVorstellungCheckBox().setSelected(true);
            zeigeVorstellungsdetails();
            _aufVorstellungsCheckboxReagieren = true;
            if (!_kinoService.istVorstellungEntfernenMoeglich(_vorstellung))
            {
                _ui.getVorstellungCheckBox().setEnabled(false);
                _ui.getFilmBox().setEnabled(false);
            }
            else
            {
                _ui.getVorstellungCheckBox().setEnabled(true);
                _ui.getFilmBox().setEnabled(true);
            }
        }
        else
        {
            _aufVorstellungsCheckboxReagieren = false;
            versteckeVorstellungsdetails();
            _ui.getVorstellungCheckBox().setSelected(false);
            _aufVorstellungsCheckboxReagieren = true;
            if (!_kinoService.istVorstellungErstellbar(_kinosaal, _datum,
                    _startzeit, _selectedFilm))
            {
                _ui.getVorstellungCheckBox().setEnabled(false);
                for (Film film : _filme)
                {
                    if (_kinoService.istVorstellungErstellbar(_kinosaal,
                            _datum, _startzeit, film))
                    {
                        _selectedFilm = film;
                        _ui.getVorstellungCheckBox().setEnabled(true);
                        break;
                    }
                }
            }
            else
            {
                _ui.getVorstellungCheckBox().setEnabled(true);
            }
        }
    }
    
    /**
     * Aktualisiert die Reinigungszeit-Checkbox.
     */
    private void aktualisiereReinigungsCheckbox()
    {
        if (_vorstellung != null)
        {
            if (!_vorstellung.hatReinigungszeit())
            {
                boolean result = _kinoService.istFilmZeigenMoeglich(
                        _selectedFilm, _werbeblock,
                        _kinosaal.getReinigungszeit(), _kinosaal, _datum,
                        _startzeit);
                if (!result)
                {
                    _ui.getReinigungszeitCheckBox().setEnabled(false);
                }
                else
                {
                    _ui.getReinigungszeitCheckBox().setEnabled(true);
                }
            }
            else
            {
                boolean result = _kinoService.istFilmZeigenMoeglich(
                        _selectedFilm, _werbeblock, _reinigungszeit, _kinosaal,
                        _datum, _startzeit);
                if (!result)
                {
                    _aufReinigungsCheckboxReagieren = false;
                    _ui.getReinigungszeitCheckBox().setEnabled(false);
                    _ui.getReinigungszeitCheckBox().setSelected(false);
                    _aufReinigungsCheckboxReagieren = true;
                }
                else
                {
                    _ui.getReinigungszeitCheckBox().setEnabled(true);
                }
            }
        }
    }
    
    /**
     * Wird aufgerufen, wenn eine Vorstellung eingeplant werden soll.
     */
    private void erzeugeVorstellung()
    {
        if (_vorstellung == null)
        {
            Vorstellung neueVorstellung = new Vorstellung(_kinosaal,
                    _selectedFilm, _startzeit, _datum, Vorstellung.TICKETPREIS);
            _kinoService.fuegeVorstellungHinzu(neueVorstellung, _datum,
                    _kinosaal, _startzeit);
            _vorstellung = neueVorstellung;
            aktualisiereUI();
            
            setChanged();
            notifyObservers();
        }
    }
    
    /**
     * Entfernt die Vorstellung.
     */
    private void entferneVorstellung()
    {
        if (_vorstellung != null)
        {
            _kinoService.entferneVorstellung(_vorstellung);
            _vorstellung = null;
            _werbeblock = null;
            _reinigungszeit = null;
            
            setChanged();
            notifyObservers();
            
            aktualisiereFilmauswahl();
        }
    }
    
    /**
     * Entfernt die Reinigungszeit.
     */
    private void reinigungszeitEntfernen()
    {
        _vorstellung.entferneReinigungszeit();
        _reinigungszeit = null;
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * Plant die Reinigungszeit ein.
     */
    private void reinigungszeitEinplanen()
    {
        _reinigungszeit = _kinosaal.getReinigungszeit();
        _vorstellung.setReinigungszeit(_reinigungszeit);
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * Zeigt die Vorstellungsdetails.
     */
    private void zeigeVorstellungsdetails()
    {
        _ui.zeigeVorstellungsdetails();
    }
    
    /**
     * Versteckt die Vorstellungsdetails.
     */
    private void versteckeVorstellungsdetails()
    {
        _ui.versteckeVorstellungsdetails();
    }
}
