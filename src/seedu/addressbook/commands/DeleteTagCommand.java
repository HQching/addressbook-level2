package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.Tagging;
import seedu.addressbook.data.tag.UniqueTagList.DuplicateTagException;
import seedu.addressbook.data.tag.UniqueTagList.TagNotFoundException;

/** Deletes a tag from a person in the AddressBook.
 *  Person is identified by the index number in the last shown person listing.
 */
public class DeleteTagCommand extends Command {
        public static final String COMMAND_WORD = "deleteTag";

        public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a tag to a person in the address book. "
                + "Person is identified by the index number in the last shown person listing.\n"
                + "Parameters: INDEX TAG_NAME\n"
                + "Example: " + COMMAND_WORD + " 1 secSchFriend";

        public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tag from person: %1$s, %2$s";
        
        private String newTagName;
        
        /** Constructs an DeleteTagCommand object */
        public DeleteTagCommand(int index,String tagName) {
            super(index);
            newTagName = tagName;
        }
        
        @Override
        public CommandResult execute() {
            try { 
                final ReadOnlyPerson target = getTargetPerson();
                Tag tagToDelete = new Tag(newTagName);
                target.getTags().remove(tagToDelete);
                addressBook.addTagging(Tagging.TAGGING_SIGN_DELETE, (Person) target, tagToDelete);
                return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, target.getName().toString(), newTagName));
            
            } catch (IndexOutOfBoundsException ie) {
                return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            } catch (DuplicateTagException e) {
                return new CommandResult(e.getMessage());
            } catch (IllegalValueException e) {
                return new CommandResult(e.getMessage());
            } catch (TagNotFoundException e) {
                return new CommandResult(e.getMessage());
            } 
        }


}
