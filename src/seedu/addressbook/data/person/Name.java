package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String EXAMPLE = "John Doe";
    public static final String MESSAGE_NAME_CONSTRAINTS = "Person names should be spaces or alphabetic characters";
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alpha} ]+";
    public final String fullName;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Name(String name) throws IllegalValueException {
        String trimmedName = name.trim();
        if (!isValidName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.fullName = trimmedName;
    }

    /**
     * Returns true if a given string is a valid person name.
     */
    public static boolean isValidName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }

    /**
     * Retrieves a listing of every word in the name, in order.
     */
    public List<String> getWordsInName() {
        return Arrays.asList(fullName.split("\\s+"));
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.fullName.equals(((Name) other).fullName)); // state check
    }
    
    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
    
    /**
     * Returns true of the other name is very similar to this name.
     * Two names are considered similar if they only have different cases, are reordered,
     * one is a subset of the other or is the initials (partial or whole) of the other.
     */
     public boolean isSimilar(Name other) {
        if (other == null) {
            return false;
        }
        
        String thisName = this.fullName.toLowerCase();
        String otherName = other.fullName.toLowerCase();
        
        String[] thisNameParts = thisName.split("\\s+");
        String[] otherNameParts = otherName.split("\\s+");
        
        return (isEqualCaseInsensitive(thisName, otherName) || isReorderedOrSubset(thisNameParts, otherNameParts) ||
                 isInitials(thisNameParts, otherName)); 
     }

     /** Returns true of the other name is case insensitively equal to this name. */
     private boolean isEqualCaseInsensitive(String thisName, String otherName) {
        return thisName.equals(otherName);
    }
    
     /** Returns true of the other name is reordered from or a subset of the current name. */
     private boolean isReorderedOrSubset(String[] thisNameParts, String[] otherNameParts) {
        int count = 0;
        for (String part : otherNameParts) {
            for (String p : thisNameParts) {
                if (isEqualCaseInsensitive(part, p)) {
                    count++;
                    break;
                }
            }
        }
        return (count == otherNameParts.length);
    }
    
    /** Returns true of the other name is the part or whole initials of the current name. */
    private boolean isInitials(String[] thisNameParts, String otherName) {
        final int NAME_INITIALS_INDEX = 0;
        String initials = "";
        for (String p : thisNameParts) {
            initials += p.substring(NAME_INITIALS_INDEX, NAME_INITIALS_INDEX + 1);
        }
        return initials.contains(otherName);
    }
}
        
