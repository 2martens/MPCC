package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jim Martens
 * @version 13.06.2013
 * @copyright 2013 Jim Martens
 */
public class TagTest
{
    private Datum _datum1;
    private Datum _datum2;
    
    @Before
    public void setUp()
    {
        _datum1 = new Datum(20, 5, 1994);
        _datum2 = new Datum(3, 4, 1994);
    }
    
    @Test
    public void testKonstruktor()
    {
        Tag tag = new Tag(_datum1, Wochentag.MONTAG);
        assertSame(_datum1, tag.getDatum());
        assertEquals(Wochentag.MONTAG, tag.getWochentag());
    }
    
    @Test
    public void testEqualsUndHashCode()
    {
        Tag tag = new Tag(_datum1, Wochentag.MONTAG);
        Tag tag1 = new Tag(_datum1, Wochentag.MONTAG);
        assertTrue(tag.equals(tag1));
        assertTrue(tag.hashCode() == tag1.hashCode());
        
        Tag tag2 = new Tag(_datum2, Wochentag.MONTAG);
        assertFalse(tag.equals(tag2));
    }
    
    @Test
    public void testCompareTo()
    {
        Tag tag1 = new Tag(_datum1, Wochentag.FREITAG);
        Tag tag2 = new Tag(_datum2, Wochentag.SONNTAG);
        assertTrue(tag1.compareTo(tag2) > 0);
        assertTrue(tag2.compareTo(tag1) < 0);
        assertTrue(tag1.compareTo(tag1) == 0);
    }
}
