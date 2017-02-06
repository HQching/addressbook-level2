package seedu.addressbook.data.person;

/**
 * Represents a Person's contact detail in the address book.
 * Guarantees: immutable
 */
public class Contact {

    public final String value;
    protected boolean isPrivate;
    
    public Contact(String contact, boolean isPrivate) {
        this.isPrivate = isPrivate;
        String trimmedContact = contact.trim();
        this.value = trimmedContact;
    }
    
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Contact// instanceof handles nulls
                && this.value.equals(((Contact) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }

}
