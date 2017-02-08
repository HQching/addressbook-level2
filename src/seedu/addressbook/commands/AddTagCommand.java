package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.Tagging;
import seedu.addressbook.data.tag.UniqueTagList.DuplicateTagException;

/** Adds a tag to a person in the AddressBook.
 *  Person is identified by the index number in the last shown person listing.
 */
public class AddTagCommand extends Command {
    
    public static final String COMMAND_WORD = "addTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to a person in the address book. "
            + "Person is identified by the index number in the last shown person listing.\n"
            + "Parameters: INDEX TAG_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 secSchFriend";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "New tag to person added: %1$s, %2$s";
    
    private String newTagName;
    
    /** Constructs an AddTagCommand object */
    public AddTagCommand(int index,String tagName) {
        super(index);
        newTagName = tagName;
    }
    
    @Override
    public CommandResult execute() {
        try { 
            final ReadOnlyPerson target = getTargetPerson();
            Tag tagToAdd = new Tag(newTagName);
            target.getTags().add(tagToAdd);
            addressBook.addTagging(Tagging.TAGGING_SIGN_ADD, (Person) target, tagToAdd);
            return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, target.getName().toString(), newTagName));
        
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (DuplicateTagException e) {
            return new CommandResult(e.getMessage());
        } catch (IllegalValueException e) {
            return new CommandResult(e.getMessage());
        } 
    }

}
