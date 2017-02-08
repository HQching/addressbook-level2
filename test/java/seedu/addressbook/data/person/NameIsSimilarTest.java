package seedu.addressbook.data.person;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.exception.IllegalValueException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class NameIsSimilarTest {

    private Name name;

    @Before
    public void setUp() throws IllegalValueException {
        name = new Name("John K Smith");
    }

    @Test
    public void nameIsSimilar() throws IllegalValueException {
        // case insensitive name
        assertSimilar(new Name("John K Smith"));
        assertSimilar(new Name("john k smith"));
        assertSimilar(new Name("JOHN K SMITH"));
        assertSimilar(new Name("jOhN k sMITh"));

        // different ordering
        assertSimilar(new Name("K Smith John"));
        assertSimilar(new Name("SmiTH k john"));
        assertSimilar(new Name("K JoHN SMitH"));
        assertSimilar(new Name("   john    smith   k   "));

        // subset 
        assertSimilar(new Name("John"));
        assertSimilar(new Name("k"));
        assertSimilar(new Name("Smith John"));
    }

    @Test
    public void nameIsNotSimilar() throws IllegalValueException {
        // null 
        assertNotSimilar(null);
        
        // wrong spacing or spelling
        assertNotSimilar(new Name("johnK Smiht"));
        assertNotSimilar(new Name("ksmit hjohn"));
        
        // having part of the name similar
        assertNotSimilar(new Name("Sally Smith"));
        assertNotSimilar(new Name("J K Rowling"));
        assertNotSimilar(new Name("John Smith Harry JR"));
        
        // not similar at all
        assertNotSimilar(new Name("Mary Janes"));
        
        // subset of the name that is not a word by itself in the original name
        assertNotSimilar(new Name("Oh"));
        assertNotSimilar(new Name("m"));
        
        
    }

    private void assertSimilar(Name other) {
        assertTrue(name.isSimilar(other));
    }

    private void assertNotSimilar(Name other) {
        assertFalse(name.isSimilar(other));
    }

}