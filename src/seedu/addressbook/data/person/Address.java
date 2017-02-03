package seedu.addressbook.data.person;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book. Guarantees: immutable; is
 * valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    /**
     * Represents an address block. Guarantees: immutable; is valid as declared
     * in {@link #isValidBlock(String)}
     */
    private class Block {
        public static final String EXAMPLE = "123";
        public static final String MESSAGE_BLOCK_CONSTRAINTS = "Address blocks should be only numbers with an optional letter at the end";
        public static final String BLOCK_VALIDATION_REGEX = "\\d+[a-zA-Z]?";
        private String value;

        public Block(String blkNum) throws IllegalValueException {
            if (!this.isValidBlock(blkNum)) {
                throw new IllegalValueException(MESSAGE_BLOCK_CONSTRAINTS);
            }
            value = blkNum;
        }

        public boolean isValidBlock(String test) {
            return test.matches(BLOCK_VALIDATION_REGEX);
        }

        public String getBlock() {
            return value;
        }

    }

    /**
     * Represents an address street. Guarantees: immutable; is valid as declared
     * in {@link #isValidStreet(String)}
     */
    private class Street {

        public static final String EXAMPLE = "Clementi Ave 3";
        public static final String MESSAGE_STREET_CONSTRAINTS = "Address streets should only alphanumeric characters";
        public static final String STREET_VALIDATION_REGEX = ".+";
        private String value;

        public Street(String streetName) throws IllegalValueException {
            if (!this.isValidStreet(streetName)) {
                throw new IllegalValueException(MESSAGE_STREET_CONSTRAINTS);
            }
            value = streetName;
        }

        public boolean isValidStreet(String test) {
            return test.matches(STREET_VALIDATION_REGEX);
        }

        public String getStreet() {
            return value;
        }
    }

    /**
     * Represents an address unit. Guarantees: immutable; is valid as declared
     * in {@link #isValidUnit(String)}
     */
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

    /**
     * Represents an address postal code. Guarantees: immutable; is valid as
     * declared in {@link #isValidPostalCode(String)}
     */
    private class PostalCode {

        public static final String EXAMPLE = "231534";
        public static final String MESSAGE_POSTAL_CODE_CONSTRAINTS = "Address postal codes should contain 6 digits";
        private static final String POSTAL_CODE_VALIDATION_REGEX = "\\d{6}";
        private String value;

        public PostalCode(String value) throws IllegalValueException {
            if (!this.isValidPostalCode(value)) {
                throw new IllegalValueException(MESSAGE_POSTAL_CODE_CONSTRAINTS);
            }
            this.value = value;
        }

        public boolean isValidPostalCode(String test) {
            return test.matches(POSTAL_CODE_VALIDATION_REGEX);
        }

        public String getPostalCode() {
            return value;
        }
    }

    public static final String ADDRESS_DELIMITER = ", ";
    public static final String EXAMPLE = Block.EXAMPLE + ADDRESS_DELIMITER + Street.EXAMPLE + ADDRESS_DELIMITER
            + Unit.EXAMPLE + ADDRESS_DELIMITER + PostalCode.EXAMPLE;
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must have format BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final Pattern ADDRESS_VALIDATION_REGEX = Pattern.compile("(?<BLOCK>.+)" + ADDRESS_DELIMITER
                                       + "(?<STREET>.+)" + ADDRESS_DELIMITER + "(?<UNIT>.+)" + ADDRESS_DELIMITER + "(?<POSTALCODE>.+)");

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
        if (!isValidAddress(address.trim())) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        setAddressParams(isPrivate, address.trim());
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return ADDRESS_VALIDATION_REGEX.matcher(test).matches();
    }

    /**
     * Splits address into block, street, unit and postal code
     * 
     * @throws IllegalValueException if any of the above have invalid format
     */
    private void setAddressParams(boolean isPrivate, String addressParams) throws IllegalValueException {
        this.isPrivate = isPrivate;
        final Matcher matcher = ADDRESS_VALIDATION_REGEX.matcher(addressParams);
        matcher.matches();
        block = new Block(matcher.group("BLOCK"));
        street = new Street(matcher.group("STREET"));
        unit = new Unit(matcher.group("UNIT"));
        postalCode = new PostalCode(matcher.group("POSTALCODE"));
    }

    @Override
    public String toString() {
        return block.getBlock() + ADDRESS_DELIMITER + street.getStreet() + ADDRESS_DELIMITER + unit.getUnit()
                + ADDRESS_DELIMITER + postalCode.getPostalCode();
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
