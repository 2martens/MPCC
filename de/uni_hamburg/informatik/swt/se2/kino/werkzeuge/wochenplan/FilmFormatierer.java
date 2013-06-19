package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Film;

/**
 * Formatierer für einen {@link Film}.
 * 
 * @author Jim Martens
 * @version 19.06.2013
 * @copyright 2013 Jim Martens
 */
class FilmFormatierer
{
    private Film _film;
    
    /**
     * Initialisiert einen Formatierer für den angegebenen Film.
     * 
     * @param film Der Film, der von diesem Formatierer dargestellt wird.
     * 
     * @require film != null
     */
    public FilmFormatierer(Film film)
    {
        assert film != null : "Vorbedingung verletzt: film != null";
        _film = film;
    }
    
    /**
     * Gibt den Film zurück, der von diesem Formatierer dargestellt wird.
     */
    Film getFilm()
    {
        return _film;
    }
    
    @Override
    public String toString()
    {
        return _film.getFormatiertenString();
    }
}
