package seedu.addressbook.commands;

/** Deletes a tag from a person in the AddressBook.
 *  Person is identified by the index number in the last shown person listing.
 */
public class DeleteTagCommand extends Command {
        public static final String COMMAND_WORD = "deleteTag";

        public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a tag to a person in the address book. "
                + "Person is identified by the index number in the last shown person listing.\n"
                + "Parameters: INDEX TAG_NAME\n"
                + "Example: " + COMMAND_WORD + " 1 secSchFriend";

        public static final String MESSAGE_SUCCESS = "Deleted tag from person: %1$s, %2$s";
        
        private int personIndex;
        private String newTagName;
        
        /** Constructs an DeleteTagCommand object */
        public DeleteTagCommand(int index,String tagName) {
            personIndex = index;
            newTagName = tagName;
        }
        
        @Override
        public CommandResult execute() {
            // TODO Auto-generated method stub
            return null;
        }


}
