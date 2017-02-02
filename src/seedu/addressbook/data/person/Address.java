package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book. Guarantees: immutable; is
 * valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    /**
     * Represents an address block. Guarantees: immutable; is
     * valid as declared in {@link #isValidBlock(String)}
     */
    private class Block {
        public static final String EXAMPLE = "123";
        public static final String MESSAGE_BLOCK_CONSTRAINTS = "Address blocks should be only numbers with an optional letter at the end";
        public static final String BLOCK_VALIDATION_REGEX = "\\d+[a-zA-Z]?";
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

    /**
     * Represents an address street. Guarantees: immutable; 
     * is valid as declared in {@link #isValidStreet(String)}
     */
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

    /**
     * Represents an address unit. Guarantees: immutable; 
     * is valid as declared in {@link #isValidUnit(String)}
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
     * Represents an address postal code. Guarantees: immutable; 
     * is valid as declared in {@link #isValidPostalCode(String)}
     */
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

    public static final String ADDRESS_DELIMITER = ",";
    public static final String EXAMPLE = Block.EXAMPLE + ADDRESS_DELIMITER + Street.EXAMPLE + ADDRESS_DELIMITER
                                       + Unit.EXAMPLE + ADDRESS_DELIMITER + PostalCode.EXAMPLE;
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must have format BLOCK, STREET, UNIT, POSTAL_CODE";
    
    private static final int ADDRESS_NUMBER_OF_PARAMS = 4;
    private static final int ADDRESS_BLOCK_INDEX = 0;
    private static final int ADDRESS_STREET_INDEX = 1;
    private static final int ADDRESS_UNIT_INDEX = 2;
    private static final int ADDRESS_POSTAL_CODE_INDEX = 3;
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
        String[] addressParams = address.trim().split(ADDRESS_DELIMITER);
        if (!isValidAddress(addressParams)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        setAddressParams(isPrivate, addressParams);
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String[] test) {        
        return (test.length == ADDRESS_NUMBER_OF_PARAMS);       
    }
    
    /**
     * Splits address into block, street, unit and postal code 
     * 
     * @throws IllegalValueException if any of the above have invalid format
     */
    private void setAddressParams(boolean isPrivate, String[] addressParams) throws IllegalValueException {
        this.isPrivate = isPrivate;  
        block = new Block(addressParams[ADDRESS_BLOCK_INDEX].trim());
        street = new Street(addressParams[ADDRESS_STREET_INDEX].trim());
        unit = new Unit(addressParams[ADDRESS_UNIT_INDEX].trim());
        postalCode = new PostalCode(addressParams[ADDRESS_POSTAL_CODE_INDEX].trim());
    }


    @Override
    public String toString() {
        return block.getBlock() + ADDRESS_DELIMITER + street.getStreet() + ADDRESS_DELIMITER + unit.getUnit() + ADDRESS_DELIMITER + postalCode.getPostalCode();
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
