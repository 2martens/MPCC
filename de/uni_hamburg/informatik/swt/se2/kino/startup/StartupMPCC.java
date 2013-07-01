package de.uni_hamburg.informatik.swt.se2.kino.startup;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Reinigungszeit;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Uhrzeit;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Film;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kino;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.services.kino.KinoService;
import de.uni_hamburg.informatik.swt.se2.kino.services.kino.KinoServiceImpl;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.hauptwerkzeug.KinoWerkzeug;

/**
 * Startet die Anwendung.
 * 
 * @author SE2-Team
 * @version SoSe 2013
 */
public class StartupMPCC
{
    /**
     * Die Main-Methode.
     * 
     * @param args
     *            die Aufrufparameter.
     */
    public static void main(String[] args)
    {
        final Kino kino = erzeugeKinoMitBeispieldaten();
        final KinoService kinoService = new KinoServiceImpl(kino);
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    UIManager.setLookAndFeel(UIManager
                            .getSystemLookAndFeelClassName());
                }
                catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e)
                {
                    e.printStackTrace();
                }
                new KinoWerkzeug(kinoService);
            }
        });
    }
    
    /**
     * Erzeugt ein Kino mit einigen Vorstellungen.
     */
    private static Kino erzeugeKinoMitBeispieldaten()
    {
        Reinigungszeit rZeitGross = new Reinigungszeit(30);
        Reinigungszeit rZeitMittel = new Reinigungszeit(20);
        Reinigungszeit rZeitKlein = new Reinigungszeit(10);
        
        final Kinosaal[] saele = {
                new Kinosaal("Grosses Kino", 20, 25, rZeitGross),
                new Kinosaal("Oberes Kino", 16, 20, rZeitMittel),
                new Kinosaal("Kleines Kino", 10, 16, rZeitKlein) };
        
        // Filme: Top-5 Deutschland laut kino.de in der Kalenderwoche 20, 2011.
        Film[] filme = {
                new Film("Pirates of the Caribbean - Fremde Gezeiten", 136,
                        FSK.FSK12, true, false),
                new Film("Fast & Furious Five", 130, FSK.FSK12, true, false),
                new Film("Rio", 96, FSK.FSK0, false, false),
                new Film("Wasser für die Elefanten", 120, FSK.FSK12, false,
                        false),
                new Film("Thor", 115, FSK.FSK12, false, false),
                new Film("Star Trek: Into Darkness", 132, FSK.FSK12, true, true) };
        
        Uhrzeit nachmittag = new Uhrzeit(17, 30);
        Uhrzeit abend = new Uhrzeit(20, 0);
        Uhrzeit spaet = new Uhrzeit(22, 30);
        
        Datum d1 = Datum.heute();
        Datum d2 = d1.naechsterTag();
        Datum d3 = d2.naechsterTag();
        
        final Vorstellung[] vorstellungen = {
                // Heute
                new Vorstellung(saele[0], filme[2], nachmittag, d1),
                new Vorstellung(saele[0], filme[0], abend, d1),
                new Vorstellung(saele[0], filme[0], spaet, d1),
                
                new Vorstellung(saele[1], filme[3], nachmittag, d1),
                new Vorstellung(saele[1], filme[1], spaet, d1),
                
                new Vorstellung(saele[2], filme[3], abend, d1),
                new Vorstellung(saele[2], filme[4], spaet, d1),
                
                // Morgen
                new Vorstellung(saele[0], filme[0], abend, d2),
                new Vorstellung(saele[0], filme[0], spaet, d2),
                
                new Vorstellung(saele[1], filme[2], nachmittag, d2),
                new Vorstellung(saele[1], filme[4], abend, d2),
                
                new Vorstellung(saele[2], filme[3], nachmittag, d2),
                new Vorstellung(saele[2], filme[1], spaet, d2),
                
                // Übermorgen
                new Vorstellung(saele[0], filme[1], abend, d3),
                new Vorstellung(saele[0], filme[1], spaet, d3),
                
                new Vorstellung(saele[1], filme[2], nachmittag, d3),
                new Vorstellung(saele[1], filme[0], abend, d3),
                
                new Vorstellung(saele[2], filme[3], abend, d3),
                new Vorstellung(saele[2], filme[4], spaet, d3) };
        
        return new Kino(saele, vorstellungen, filme);
    }
}
