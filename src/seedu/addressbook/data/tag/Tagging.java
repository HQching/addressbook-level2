package seedu.addressbook.data.tag;

import seedu.addressbook.data.person.Person;

/** Represents an adding or deleting of a tag from a person in the address book.  */
public class Tagging {
    
    public static final String TAGGING_SIGN_ADD = "+";
    public static final String TAGGING_SIGN_DELETE = "-";
    
    private String operator;
    private final Person personToTag;
    private final Tag newTag;
    
    /** Constructs a Tagging object containing information whether is an add or delete,
     * the Person being tagged, as well as the new Tag
     */
    public Tagging(String operator, Person personToTag, Tag newTag) {
        this.operator = operator;
        this.personToTag = personToTag;
        this.newTag = newTag;
    }
    
    @Override
    public String toString() {
        String personName = personToTag.getName().toString();
        String newTagName = newTag.toString();
        return operator + " " + personName + " " + newTagName;
    }

}
