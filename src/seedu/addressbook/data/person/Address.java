package seedu.addressbook.data.person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book. Guarantees: immutable; is
 * valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    private class Block {
        public static final String EXAMPLE = "123";
        public static final String MESSAGE_BLOCK_CONSTRAINTS = "Address blocks should only have alphanumeric characters";
        public static final String BLOCK_VALIDATION_REGEX = ".+";
        private String number;

        public Block(String blkNum) throws IllegalValueException {
            if (!this.isValidBlock(blkNum)) {
                throw new IllegalValueException(MESSAGE_BLOCK_CONSTRAINTS);
            }
            number = blkNum;
        }

        public boolean isValidBlock(String test) {
            return test.matches(BLOCK_VALIDATION_REGEX);
        }

        public String getBlock() {
            return number;
        }

    }

    private class Street {

        public static final String EXAMPLE = "Clementi Ave 3";
        public static final String MESSAGE_STREET_CONSTRAINTS = "Address streets should only alphanumeric characters";
        public static final String STREET_VALIDATION_REGEX = ".+";
        private String name;

        public Street(String streetName) throws IllegalValueException {
            if (!this.isValidStreet(streetName)) {
                throw new IllegalValueException(MESSAGE_STREET_CONSTRAINTS);
            }
            name = streetName;
        }

        public boolean isValidStreet(String test) {
            return test.matches(STREET_VALIDATION_REGEX);
        }

        public String getStreet() {
            return name;
        }
    }

    private class Unit {

        public static final String EXAMPLE = "#12-34";
        public static final String MESSAGE_UNIT_CONSTRAINTS = "Address units should start with '#' and contain two numbers separated by a '-'";
        private static final String UNIT_VALIDATION_REGEX = "#\\d+-\\d+";
        private String value;

        public Unit(String unitNum) throws IllegalValueException {
            if (!this.isValidUnit(unitNum)) {
                throw new IllegalValueException(MESSAGE_UNIT_CONSTRAINTS);
            }
            value = unitNum;
        }

        public boolean isValidUnit(String test) {
            return test.matches(UNIT_VALIDATION_REGEX);
        }

        public String getUnit() {
            return value;
        }
    }

    private class PostalCode {

        public static final String EXAMPLE = "231534";
        public static final String MESSAGE_POSTAL_CODE_CONSTRAINTS = "Address postal codes should contain 6 digits";
        private static final String POSTAL_CODE_VALIDATION_REGEX = "\\d{6}";
        private String numbers;

        public PostalCode(String value) throws IllegalValueException {
            if (!this.isValidPostalCode(value)) {
                throw new IllegalValueException(MESSAGE_POSTAL_CODE_CONSTRAINTS);
            }
            numbers = value;
        }

        public boolean isValidPostalCode(String test) {
            return test.matches(POSTAL_CODE_VALIDATION_REGEX);
        }

        public String getPostalCode() {
            return numbers;
        }
    }

    public static final String EXAMPLE = Block.EXAMPLE + ", " + Street.EXAMPLE + ", " + Unit.EXAMPLE + ","
            + PostalCode.EXAMPLE;
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must have format BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final Pattern ADDRESS_VALIDATION_REGEX = // ", " comma and
                                                           // whitespace is
                                                           // reserved for
                                                           // delimiter prefixes
            Pattern.compile(
                    "(?<BLOCK>.+)" + ", " + "(?<STREET>.+)" + ", " + "(?<UNIT>.+)" + ", " + "(?<POSTALCODE>.+)");

    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.isPrivate = isPrivate;
        final Matcher matcher = ADDRESS_VALIDATION_REGEX.matcher(address.trim());
        block = new Block(matcher.group("BLOCK"));
        street = new Street(matcher.group("STREET"));
        unit = new Unit(matcher.group("UNIT"));
        postalCode = new PostalCode(matcher.group("POSTALCODE"));
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return ADDRESS_VALIDATION_REGEX.matcher(test).matches();
    }

    @Override
    public String toString() {
        return block.getBlock() + ", " + street.getStreet() + ", " + unit.getUnit() + ", " + postalCode.getPostalCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instance of handles nulls
                        && this.toString().equals(((Address) other).toString())); // state
                                                                                  // check
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
