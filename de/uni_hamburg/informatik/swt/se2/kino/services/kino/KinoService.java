package de.uni_hamburg.informatik.swt.se2.kino.services.kino;

import java.util.List;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Reinigungszeit;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Uhrzeit;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Woche;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Film;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Tagesplan;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Werbeblock;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Wochenplan;
import de.uni_hamburg.informatik.swt.se2.kino.services.ObservableService;

/**
 * Der Kino-Service erlaubt es Vorstellungen hinzuzufügen, zu bearbeiten und zu
 * entfernen.
 * 
 * KinoService ist ein ObservableService. Als solcher bietet er die Möglichkeit
 * über Vorstellungsänderungen zu informieren. Beobachter müssen das Interface
 * ServiceObserver implementieren.
 * 
 * @author Jim Martens
 * @version 24.06.2013
 * @copyright 2013 Jim Martens
 */
public interface KinoService extends ObservableService
{
    /**
     * Prüft, ob das Zeigen eines Films um die gegebene Uhrzeit möglich ist.
     * 
     * @param film
     *            Der zu überprüfende Film
     * @param werbeblock
     *            Der vor dem Film gezeigte Werbeblock (auch <code>null</code>)
     * @param reinigungszeit
     *            Die nach dem Film eingeplante Reinigungszeit (auch
     *            <code>null</code>)
     * @param kinosaal
     *            Der Kinosaal der Vorstellung
     * @param datum
     *            Das Datum der Vorstellung
     * @param startzeit
     *            Die Startzeit der Vorstellung
     * 
     * @require filmVorraetig(film)
     * @require istKinosaalVorhanden(kinosaal)
     * @require datum != null
     * @require startzeit != null
     * 
     * @return <code>true</code> wenn das Zeigen des Films zu der Startzeit mit
     *         dem Werbeblock möglich ist, <code>false</code> sonst
     */
    boolean istFilmZeigenMoeglich(Film film, Werbeblock werbeblock,
            Reinigungszeit reinigungszeit, Kinosaal kinosaal, Datum datum,
            Uhrzeit startzeit);
    
    /**
     * Fügt eine Vorstellung in das Kinosystem ein.
     * 
     * @param vorstellung
     *            Die einzufügende Vorstellung.
     * @param datum
     *            Der Tag der Vorstellung.
     * @param kinosaal
     *            Der Kinosaal der Vorstellung.
     * @param startzeit
     *            Die Startzeit der Vorstellung.
     * 
     * @require istHinzufuegenMoeglich(vorstellung)
     * @require datum != null
     * @require istKinosaalVorhanden(kinosaal)
     * @require startzeit != null
     * 
     * @ensure getVorstellung(kinosaal, datum, startzeit) == vorstellung
     * @ensure istVorstellungVorhanden(vorstellung)
     */
    void fuegeVorstellungHinzu(Vorstellung vorstellung, Datum datum,
            Kinosaal kinosaal, Uhrzeit startzeit);
    
    /**
     * Aktualisiert die Vorstellung im Kinosystem. Sollte aufgerufen werden,
     * wenn sich der Kinosaal der Vorstellung geändert hat.
     * 
     * @param alteVorstellung
     *            Alte Vorstellung.
     * @param neueVorstellung
     *            Neue Vorstellung.
     * 
     * @require istVorstellungVorhanden(alteVorstellung)
     * @require !istVorstellungVorhanden(neueVorstellung)
     * @require bis auf den Kinosaal müssen beide Vorstellungen gleich sein
     * 
     * @ensure !istVorstellungVorhanden(alteVorstellung)
     * @ensure istVorstellungVorhanden(neueVorstellung)
     */
    void aktualisiereVorstellung(Vorstellung alteVorstellung, Vorstellung neueVorstellung);
    
    /**
     * Prüft, ob das Hinzufügen der Vorstellung möglich ist.
     * 
     * @param vorstellung
     *            Die Vorstellung, für die das Hinzufügen geprüft werden soll.
     * 
     * @require vorstellung != null
     * 
     * @return <code>true</code>, wenn die Vorstellung hinzugefügt werden kann,
     *         <code>false</code> sonst
     */
    boolean istHinzufuegenMoeglich(Vorstellung vorstellung);
    
    /**
     * Prüft, ob eine Vorstellung vorhanden ist.
     * 
     * @param vorstellung
     *            Die Vorstellung auf deren Vorhandensein geprüft werden soll.
     * 
     * @require vorstellung != null
     * 
     * @return <code>true</code>, wenn die Vorstellung vorhanden ist,
     *         <code>false</code> sonst
     */
    boolean istVorstellungVorhanden(Vorstellung vorstellung);
    
    /**
     * Entfernt eine Vorstellung aus dem Kinosystem.
     * 
     * @param vorstellung
     *            Die zu entfernende Vorstellung
     * 
     * @require istVorstellungVorhanden(vorstellung)
     * 
     * @ensure !istVorstellungVorhanden(vorstellung)
     */
    void entferneVorstellung(Vorstellung vorstellung);
    
    /**
     * Gibt eine Vorstellung zurück.
     * 
     * @param saal
     *            Der Kinosaal der Vorstellung.
     * @param datum
     *            Das Datum der Vorstellung.
     * @param startzeit
     *            Die Startzeit der Vorstellung.
     * 
     * @require istKinosaalVorhanden(saal)
     * @require datum != null
     * @require startzeit != null
     * @require Eine Vorstellung in dem Kinosaal, an dem gegebenen Tag zur
     *          gegebenen Uhrzeit muss existieren.
     * 
     * @ensure result != null
     */
    Vorstellung getVorstellung(Kinosaal saal, Datum datum, Uhrzeit startzeit);
    
    /**
     * Prüft, ob ein Kinosaal vorhanden ist.
     * 
     * @param kinosaal
     *            Der zu überprüfende Kinosaal.
     * 
     * @require kinosaal != null
     * 
     * @return <code>true</code>, wenn der Kinosaal vorhanden ist,
     *         <code>false</code> sonst
     */
    boolean istKinosaalVorhanden(Kinosaal kinosaal);
    
    /**
     * Prüft, ob ein Film im Bestand des Kinosystems ist.
     * 
     * @param film
     *            Der zu überprüfende Film.
     * 
     * @require film != null
     * 
     * @return <code>true</code>, wenn der Film vorrätig ist, <code>false</code>
     *         sonst
     */
    boolean istFilmVorraetig(Film film);
    
    /**
     * Gibt eine Liste aller verfügbaren Filme zurück.
     * 
     * @ensure result != null
     */
    List<Film> getFilme();
    
    /**
     * Gibt den Tagesplan für den gegebenen Tag zurück.
     * 
     * @param datum
     * 
     * @require datum != null
     * 
     * @ensure result != null
     */
    Tagesplan getTagesplan(Datum datum);
    
    /**
     * Gibt den Wochenplan für die Woche und den Kinosaal zurück.
     * 
     * @param kinosaal
     *            Der Kinosaal des Wochenplans
     * @param woche
     *            Die Woche des Wochenplans
     * 
     * @require istKinosaalVorhanden(kinosaal)
     * @require woche != null
     * @require istWochenplanVorhanden(kinosaal, woche)
     * 
     * @ensure result != null
     */
    Wochenplan getWochenplan(Kinosaal kinosaal, Woche woche);
    
    /**
     * Prüft, ob ein Wochenplan mit dem gegebenen Kinosaal und der gegebenen
     * Woche vorhanden ist.
     * 
     * @param kinosaal
     *            Der Kinosaal des Wochenplans
     * @param woche
     *            Die Woche des Wochenplans
     * 
     * @require istKinosaalVorhanden(kinosaal)
     * @require woche != null
     * 
     * @return <code>true</code>, wenn ein Wochenplan vorhanden ist,
     *         <code>false</code> sonst
     */
    boolean istWochenplanVorhanden(Kinosaal kinosaal, Woche woche);
    
    /**
     * Setzt den Wochenplan für den gegebenen Kinosaal und die gegebene Woche.
     * 
     * @param wochenplan
     *            Der zu setzende Wochenplan
     * @param kinosaal
     *            Der Kinosaal des Wochenplans
     * @param woche
     *            Die Woche des Wochenplans
     * 
     * @require !istWochenplanVorhanden(kinosaal, woche)
     * @require istKinosaalVorhanden(kinosaal)
     * @require woche != null
     * 
     * @ensure getWochenplan(kinosaal, woche) == wochenplan
     */
    void setWochenplan(Wochenplan wochenplan, Kinosaal kinosaal, Woche woche);
    
    /**
     * Gibt eine Liste aller Kinosäle zurück.
     * 
     * @ensure result != null
     */
    List<Kinosaal> getKinosaele();
    
    /**
     * Prüft, ob eine Vorstellung entfernt werden kann.
     * 
     * @param vorstellung
     *            Die auf Entfernbarkeit zu überprüfende Vorstellung
     * 
     * @require istVorstellungVorhanden(Vorstellung vorstellung)
     * 
     * @return <code>true</code>, wenn die Vorstellung entfernt werden kann,
     *         <code>false</code> sonst
     */
    boolean istVorstellungEntfernenMoeglich(Vorstellung vorstellung);
    
    /**
     * Prüft, ob eine Vorstellung erstellbar ist mit den angegebenen Daten.
     * 
     * @param kinosaal
     *            Der Kinosaal der Vorstellung
     * @param datum
     *            Das Datum der Vorstellung
     * @param startzeit
     *            Die Startzeit der Vorstellung
     * @param film
     *            Der Film der Vorstellung
     * 
     * @require istKinosaalVorhanden(kinosaal)
     * @require datum != null
     * @require startzeit != null
     * @require istFilmVorraetig(film)
     */
    boolean istVorstellungErstellbar(Kinosaal kinosaal, Datum datum,
            Uhrzeit startzeit, Film film);
    
    /**
     * Berechnet die maximale Dauer, die ein Werbeblock einer Vorstellung dauern
     * kann.
     * 
     * @param vorstellung
     *            Die Vorstellung, für die die maximale Werbeblocklänge
     *            berechnet werden soll.
     * 
     * @require istVorstellungVorhanden(vorstellung)
     * 
     * @ensure result >= 0
     */
    int getWerbeblockMaximalDauer(Vorstellung vorstellung);
}
