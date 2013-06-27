package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

/**
 * Die UI des {@link PlatzVerkaufsWerkzeug}.
 * 
 * @author SE2-Team
 * @version SoSe 2013
 */
class PlatzVerkaufsWerkzeugUI
{
    // Die Widgets, aus denen das UI sich zusammensetzt
    private JPanel _hauptPanel;
    private JLabel _preisLabel;
    private JButton _verkaufenButton;
    private JButton _stornierenButton;
    private JPlatzplan _platzplan;
    private JRadioButton _schueler;
    private JRadioButton _erwachsener;
    private ButtonGroup _kaeufergruppeGroup;

    /**
     * Initialisiert die UI.
     */
    public PlatzVerkaufsWerkzeugUI()
    {
        _hauptPanel = erstellePanel();
    }

    /**
     * Erzeugt das Panel, in dem der Kinosaal mit den Sitzplätzen dargestellt
     * wird.
     */
    private JPanel erstellePanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        _platzplan = new JPlatzplan();
        panel.add(new JScrollPane(_platzplan), BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));

        JPanel preisPanel = new JPanel(new BorderLayout());
        _preisLabel = new JLabel();
        preisPanel.add(_preisLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        _verkaufenButton = new JButton("Verkaufen");
        buttonPanel.add(_verkaufenButton);
        _stornierenButton = new JButton("Stornieren");
        buttonPanel.add(_stornierenButton);
        
        _kaeufergruppeGroup = new ButtonGroup();
        _schueler = new JRadioButton("Schüler?");
        _erwachsener = new JRadioButton("Erwachsener?");
        _kaeufergruppeGroup.add(_schueler);
        _kaeufergruppeGroup.add(_erwachsener);
        
        JPanel southWestPanel = new JPanel(new BorderLayout());
        JPanel buttonGroup = new JPanel();
        buttonGroup.add(_schueler);
        buttonGroup.add(_erwachsener);
        southWestPanel.add(_preisLabel, BorderLayout.CENTER);
        southWestPanel.add(buttonGroup, BorderLayout.EAST);
        
        southPanel.add(southWestPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.EAST);

        panel.add(southPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Gibt den Platzplan zurück.
     */
    public JPlatzplan getPlatzplan()
    {
        return _platzplan;
    }

    /**
     * Gibt das Label für die Preisanzeige zurück.
     */
    public JLabel getPreisLabel()
    {
        return _preisLabel;
    }

    /**
     * Gibt den Stornieren-Button zurück.
     */
    public JButton getStornierenButton()
    {
        return _stornierenButton;
    }

    /**
     * Gibt den Verkaufen-Button zurück.
     */
    public JButton getVerkaufenButton()
    {
        return _verkaufenButton;
    }
    
    /**
     * Gibt den Schüler-RadioButton zurück.
     */
    public JRadioButton getSchuelerButton()
    {
        return _schueler;
    }
    
    /**
     * Gibt den Erwachsener-RadioButton zurück.
     */
    public JRadioButton getErwachsenerButton()
    {
        return _erwachsener;
    }

    /**
     * Gibt das Panel zurück, in dem die Widgets angeordnet sind.
     */
    public JPanel getUIPanel()
    {
        return _hauptPanel;
    }

}
