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
        assertSimilar("John K Smith");
        assertSimilar("john k smith");
        assertSimilar("JOHN K SMITH");
        assertSimilar("jOhN k sMITh");
        
        // different ordering
        assertSimilar("K Smith John");
        assertSimilar("SmiTH k john");
        assertSimilar("K JoHN SMitH");
        
    }
    
   /* @Test
    public void nameIsNotSimilar() throws IllegalValueException {
        assertNotSimilar("Insert name to compare here");
    }*/
    
    private void assertSimilar(String other) throws IllegalValueException {
        assertTrue(name.isSimilar(new Name(other)));
    }
    
    /*private void assertNotSimilar(String other) throws IllegalValueException {
        assertFalse(name.isSimilar(new Name(other)));
    }*/

}