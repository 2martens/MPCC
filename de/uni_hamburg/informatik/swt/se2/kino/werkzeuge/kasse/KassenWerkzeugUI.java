package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.kasse;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * Das UI des {@link KassenWerkzeug}.
 * 
 * @author SE2-Team
 * @version SoSe 2013
 */
class KassenWerkzeugUI
{
    /*
     * Die in der Oberflaeche verwendeten Icons stammen aus dem Tango-Desktop-
     * Project und dürfen frei verwendet werden. Siehe
     * http://tango.freedesktop.org/
     */

    // Die Widgets, aus denen das UI sich zusammensetzt
    private JPanel _hauptPanel;

    /**
     * Initialisert die Oberfläche. Die Parameter sind die UIs der Subwerkzeuge,
     * die eingebettet werden.
     */
    public KassenWerkzeugUI(JPanel platzVerkaufsPanel,
            JPanel datumAuswaehlPanel, JPanel vorstellungAuswaehlPanel)
    {
        _hauptPanel = new JPanel();
        JComponent leftPanel = erstelleVorstellungsauswahlPanel(
                datumAuswaehlPanel, vorstellungAuswaehlPanel);
        JComponent rightPanel = platzVerkaufsPanel;

        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                leftPanel, rightPanel);
        _hauptPanel.add(splitter, BorderLayout.CENTER);
    }
    
    /**
     * Gibt das Haupt-Panel der UI zurück.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _hauptPanel;
    }

    /**
     * Erzeugt das Panel, in dem das Datum, der Kinosaal und die Vorstellung
     * ausgewählt werden.
     * 
     * Als Parameter werden mehrere Panel übergeben, diese werden in das hier
     * erzeugte VorstellungsauswahlPanel eingebettet.
     */
    private JPanel erstelleVorstellungsauswahlPanel(JPanel datumAuswaehlPanel,
            JPanel vorstellungAuswaehlPanel)
    {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        datumAuswaehlPanel.setBorder(BorderFactory
                .createEmptyBorder(5, 5, 5, 5));
        vorstellungAuswaehlPanel.setBorder(BorderFactory.createEmptyBorder(5,
                5, 5, 5));

        leftPanel.add(datumAuswaehlPanel, BorderLayout.NORTH);
        leftPanel.add(vorstellungAuswaehlPanel, BorderLayout.CENTER);

        return leftPanel;
    }
}
