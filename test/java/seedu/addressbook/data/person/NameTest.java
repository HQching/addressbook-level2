package seedu.addressbook.data.person;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.exception.IllegalValueException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class NameTest {

    private Name name;

    @Before
    public void setUp() throws IllegalValueException {
        name = new Name("John K Smith");
    }

    @Test
    public void isSimilar_differentCase_returnsTrue() throws IllegalValueException {
        assertSimilar(new Name("John K Smith"));
        assertSimilar(new Name("john k smith"));
        assertSimilar(new Name("JOHN K SMITH"));
        assertSimilar(new Name("jOhN k sMITh"));
    }
    
    @Test
    public void isSimilar_differentOrder_returnsTrue() throws IllegalValueException {
        assertSimilar(new Name("K Smith John"));
        assertSimilar(new Name("SmiTH k john"));
        assertSimilar(new Name("K JoHN SMitH"));
        assertSimilar(new Name("   john    smith   k   "));
    }

    @Test
    public void isSimilar_subsetOfFullName_returnsTrue() throws IllegalValueException {
        assertSimilar(new Name("John"));
        assertSimilar(new Name("k"));
        assertSimilar(new Name("Smith John"));
    }
    
    @Test
    public void isSimilar_initialsOrSubsetOfInitials_returnsTrue() throws IllegalValueException {  
        assertSimilar(new Name("Jks"));
        assertSimilar(new Name("s"));
        assertSimilar(new Name("kS"));
        assertSimilar(new Name("J"));
    }

    @Test
    public void isSimilar_null_returnsFalse() throws IllegalValueException {
        assertNotSimilar(null);
    }
    
    @Test
    public void isSimilar_wrongSpellingOrSpacing_returnsFalse() throws IllegalValueException {  
        assertNotSimilar(new Name("johnK Smiht"));
        assertNotSimilar(new Name("ksmit hjohn"));
    }
    
    @Test
    public void isSimilar_subsetButWithExtraWords_returnsFalse() throws IllegalValueException { 
        // having part of the name similar
        assertNotSimilar(new Name("Sally Smith"));
        assertNotSimilar(new Name("J K Rowling"));
        assertNotSimilar(new Name("John Smith Harry JR"));
    }
    
    @Test
    public void isSimilar_differentName_returnsFalse() throws IllegalValueException {
        assertNotSimilar(new Name("Mary Janes"));
    }
    
    @Test
    public void isSimilar_subsetOfAWordInOriginalName_returnsFalse() throws IllegalValueException {    
        assertNotSimilar(new Name("Oh"));
        assertNotSimilar(new Name("m"));
    }
        
    @Test
    public void isSimilar_initialsInWrongOrder_returnsFalse() throws IllegalValueException {
        assertNotSimilar(new Name("jsk"));
        assertNotSimilar(new Name("sjk"));
        assertNotSimilar(new Name("kj"));
    }
        
 
    private void assertSimilar(Name other) {
        assertTrue(name.isSimilar(other));
    }

    private void assertNotSimilar(Name other) {
        assertFalse(name.isSimilar(other));
    }

}