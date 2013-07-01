package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.hauptwerkzeug;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.UIConstants;

/**
 * Das Hauptfenster für die Anwendung.
 * 
 * @author Jim Martens
 * @version 18.06.2013
 * @copyright 2013 Jim Martens
 */
class KinoWerkzeugUI
{
    public static final String KASSE = "Kasse";
    public static final String PLANUNG = "Planung";
    public static final String NAME = "SE2-Kinokartenverkauf MPCC";
    
    private CardLayout _hauptLayout;
    private JPanel _hauptPanel;
    private JPanel _menuPanel;
    private JButton _beendenButton;
    
    private JPanel _kassenPanel;
    private JButton _kassenButton;
    private JButton _planungsButton;
    private JPanel _planungsPanel;
    
    private JPanel _spacerPanel;
    private JLabel _titelLabel;
    
    private JFrame _frame;
    
    /**
     * Initialisiert die UI.
     * 
     * @ensure getUIFrame kann aufgerufen werden
     */
    public KinoWerkzeugUI()
    {
        _frame = new JFrame(NAME);
        _frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        _frame.setTitle(NAME);
    }
    
    /**
     * Initialisiert die Oberfläche des Kinosystems. Diese Methode muss nach dem
     * Konstruktoraufruf aufgerufen werden, bevor irgendeine andere Methode
     * aufgerufen werden darf.
     * 
     * @param kassenPanel
     *            Das Panel der Kasse.
     * @param planungsPanel
     *            Das Panel der Planung.
     * 
     * @require kassenPanel != null
     * @require planungsPanel != null
     */
    public void initGUI(JPanel kassenPanel, JPanel planungsPanel)
    {
        assert kassenPanel != null : "Vorbedingung verletzt: kassenPanel != null";
        assert planungsPanel != null : "Vorbedingung verletzt: planungsPanel != null";
        
        _kassenPanel = kassenPanel;
        _planungsPanel = planungsPanel;
        
        _hauptPanel = erstellePanel();
        initGUI();
        Dimension size = new Dimension(1088, 577);
        _kassenPanel.setSize(size);
        _kassenPanel.setPreferredSize(size);
        _kassenPanel.setMinimumSize(size);
        _planungsPanel.setSize(size);
        _planungsPanel.setPreferredSize(size);
        _planungsPanel.setMinimumSize(size);
    }
    
    /**
     * Gibt den Kassenbutton zurück.
     * 
     * @ensure result != null
     */
    public JButton getKassenButton()
    {
        return _kassenButton;
    }
    
    /**
     * Gibt den Planungsbutton zurück.
     * 
     * @ensure result != null
     */
    public JButton getPlanungsButton()
    {
        return _planungsButton;
    }
    
    /**
     * Gibt den Beenden-Button zurück.
     */
    public JButton getBeendenButton()
    {
        return _beendenButton;
    }
    
    /**
     * Gibt das JFrame der UI zurück.
     * 
     * @return Das JFrame der UI.
     */
    public JFrame getUIFrame()
    {
        return _frame;
    }
    
    /**
     * Zeigt die Kassen-Sicht
     */
    public void zeigeKasse()
    {
        zeigeAn(KinoWerkzeugUI.KASSE);
    }
    
    /**
     * Zeigt die Planungs-Sicht
     */
    public void zeigePlanung()
    {
        zeigeAn(KinoWerkzeugUI.PLANUNG);
    }
    
    /**
     * Zeigt das Hauptfenster an.
     */
    public void zeigeFenster()
    {
        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);
    }
    
    /**
     * Schließt das Fenster.
     */
    public void schliesseFenster()
    {
        _frame.dispose();
    }
    
    /**
     * Erstellt das Panel.
     */
    private JPanel erstellePanel()
    {
        JPanel panel = new JPanel();
        _hauptLayout = new CardLayout();
        panel.setLayout(_hauptLayout);
        panel.add(_kassenPanel, KASSE);
        panel.add(_planungsPanel, PLANUNG);
        return panel;
    }
    
    /**
     * Erzeugt den Kassenbutton für das Menü.
     */
    private void erzeugeKassenButton()
    {
        _kassenButton = new JButton();
        initialisiereMenuButton(_kassenButton, KASSE);
        _kassenButton.setSelected(true);
    }
    
    /**
     * Erzeugt den Planungsbutton für das Menü.
     */
    private void erzeugePlanungsButton()
    {
        _planungsButton = new JButton();
        initialisiereMenuButton(_planungsButton, PLANUNG);
    }
    
    /**
     * Erzeugt das Menü mit Kassen- und Planungs-Button und Titel.
     */
    private void erzeugeMenuPanel()
    {
        _menuPanel = new JPanel();
        FlowLayout auswahlPanelLayout = new FlowLayout();
        auswahlPanelLayout.setAlignment(FlowLayout.LEFT);
        _menuPanel.setLayout(auswahlPanelLayout);
        _frame.getContentPane().add(_menuPanel, BorderLayout.NORTH);
        _menuPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        erzeugeKassenButton();
        erzeugePlanungsButton();
        erzeugeTitel();
    }
    
    /**
     * Erzeugt den Titel für das Menü.
     */
    private void erzeugeTitel()
    {
        _spacerPanel = new JPanel();
        _menuPanel.add(_spacerPanel);
        _spacerPanel.setPreferredSize(new java.awt.Dimension(100, 10));
        _spacerPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        
        _titelLabel = new JLabel();
        _menuPanel.add(_titelLabel);
        _titelLabel.setText(NAME);
        _titelLabel.setBackground(UIConstants.BACKGROUND_COLOR);
        _titelLabel.setFont(UIConstants.TITLE_FONT);
        _titelLabel.setForeground(UIConstants.TITLE_COLOR);
    }
    
    /**
     * Erzeugt das Panel mit dem Beenden-Button.
     */
    private void erstelleBeendenPanel()
    {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        _beendenButton = new JButton("Beenden");
        bottomPanel.add(_beendenButton);
        _frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Initialisiert die GUI.
     */
    private void initGUI()
    {
        _frame.getContentPane().add(_hauptPanel, BorderLayout.CENTER);
        erzeugeMenuPanel();
        erstelleBeendenPanel();
        _frame.pack();
        zeigeAn(KASSE);
    }
    
    /**
     * Zeigt das Werkzeug mit dem angebenen Namen.
     * 
     * @param werkzeugName
     *            Der Name eines Werkzeugs.
     * 
     * @require werkzeugName != null
     */
    private void zeigeAn(String werkzeugName)
    {
        assert werkzeugName != null : "Vorbedingung verletzt: werkzeugName != null";
        _hauptLayout.show(_hauptPanel, werkzeugName);
        JButton buttonToSelect;
        JButton buttonToDeselect;
        if (werkzeugName.equals(KASSE))
        {
            buttonToSelect = getKassenButton();
            buttonToDeselect = getSelectedButton();
        }
        else if (werkzeugName.equals(PLANUNG))
        {
            buttonToSelect = getPlanungsButton();
            buttonToDeselect = getSelectedButton();
        }
        else
        {
            throw new IllegalArgumentException("Werkzeugname unbekannt: "
                    + werkzeugName);
        }
        buttonToDeselect.setSelected(false);
        buttonToDeselect.setBackground(UIConstants.BUTTON_COLOR);
        buttonToSelect.setSelected(true);
        buttonToSelect.setBackground(UIConstants.BUTTON_SELECTED_COLOR);
        aktualisiereLayout();
    }
    
    /**
     * Gibt den aktuell selektierten Menu-Button urück.
     * 
     * @return Den aktuell selektierten Menu-Button.
     */
    private JButton getSelectedButton()
    {
        JButton result = _kassenButton;
        for (int i = 0; i < _menuPanel.getComponentCount(); i++)
        {
            Component component = _menuPanel.getComponent(i);
            if (component instanceof JButton)
            {
                JButton button = (JButton) component;
                if (button.isSelected())
                {
                    result = button;
                    break;
                }
            }
        }
        return result;
    }
    
    /**
     * Initialisiert einen Menü-Button.
     * 
     * @param button
     *            Der Button.
     * @param buttonText
     *            Der Text der auf dem Button stehen soll.
     */
    private void initialisiereMenuButton(JButton button, String buttonText)
    {
        _menuPanel.add(button);
        button.setText(buttonText);
        button.setPreferredSize(new java.awt.Dimension(180, 50));
        button.setFont(UIConstants.BUTTON_FONT);
        button.setBackground(UIConstants.BUTTON_COLOR);
    }
    
    /**
     * Aktualisiert das Layout.
     */
    private void aktualisiereLayout()
    {
        _frame.validate();
    }
}
