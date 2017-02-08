package seedu.addressbook.commands;

/** Adds a tag to a person in the AddressBook.
 *  Person is identified by the index number in the last shown person listing.
 */
public class AddTagCommand extends Command {
    
    public static final String COMMAND_WORD = "addTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to a person in the address book. "
            + "Person is identified by the index number in the last shown person listing.\n"
            + "Parameters: INDEX TAG_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 secSchFriend";

    public static final String MESSAGE_SUCCESS = "New tag to person added: %1$s, %2$s";
    
    private int personIndex;
    private String newTagName;
    
    /** Constructs an AddTagCommand object */
    public AddTagCommand(int index,String tagName) {
        personIndex = index;
        newTagName = tagName;
    }
    
    @Override
    public CommandResult execute() {
        // TODO Auto-generated method stub
        return null;
    }

}
