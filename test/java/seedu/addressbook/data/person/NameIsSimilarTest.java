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

        // subset or superset
        assertSimilar(new Name("John"));
        assertSimilar(new Name("jOHn Harry SMITH"));
        assertSimilar(new Name("Sally Smith"));
        assertSimilar(new Name("J K Rowling"));
        assertSimilar(new Name("Harry JOHN jOe Smith Tom K"));

    }

    @Test
    public void nameIsNotSimilar() throws IllegalValueException {
        // null 
        assertNotSimilar(null);
    }

    private void assertSimilar(Name other) {
        assertTrue(name.isSimilar(other));
    }

    private void assertNotSimilar(Name other) {
        assertFalse(name.isSimilar(other));
    }

}