package seedu.addressbook.ui;

import static seedu.addressbook.common.Messages.MESSAGE_GOODBYE;
import static seedu.addressbook.common.Messages.MESSAGE_INIT_FAILED;
import static seedu.addressbook.common.Messages.MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE;
import static seedu.addressbook.common.Messages.MESSAGE_WELCOME;

import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.Main;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Formats inputs/outputs of the application.
 */
public class Formatter {

    /**
     * A decorative prefix added to the beginning of lines printed by
     * AddressBook
     */
    private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    /** Offset required to convert between 1-indexing and 0-indexing. */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** Format of a comment input line. Comment lines are silently consumed when
     * reading user input.
     */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    /** Default constructor */
    public Formatter() {
    }

    /** Returns format of message asking for user command. */
    public static String getUserCommandFormat() {
        return LINE_PREFIX + "Enter command: ";
    }

    /** Returns true if the String line is a comment line. */
    public static boolean isCommentLine(String line) {
        return line.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    /** Returns format of message asking for user command. */
    public static String echoUserCommandFormat(String fullInputLine) {
        return LINE_PREFIX + "[Command entered:" + fullInputLine + "]" + LINE_SEPARATOR;
    }

    /** Formats welcome message to user */
    public static String welcomeMessageFormat(String storageFileInfo) {
        return showToUserFormat(DIVIDER, DIVIDER, MESSAGE_WELCOME, Main.VERSION, MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE,
                storageFileInfo, DIVIDER);
    }

    /** Formats goodbye message to user */
    public static String goodByeMessageFormat() {
        return showToUserFormat(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
    }

    /** Formats initFailed message to user */
    public static String initFailedMessageFormat() {
        return showToUserFormat(MESSAGE_INIT_FAILED, DIVIDER, DIVIDER);
    }

    /** Formats message(s) to the user */
    public static String showToUserFormat(String... message) {
        final StringBuilder formattedString = new StringBuilder();
        for (String m : message) {
            formattedString.append(LINE_PREFIX + m.replace("\n", LINE_SEPARATOR + LINE_PREFIX)).append(LINE_SEPARATOR);
        }
        return formattedString.toString();
    }

    /** Formats result to user format to user */
    public static String showResultToUserFormat(CommandResult result) {
        return showToUserFormat(result.feedbackToUser, DIVIDER);
    }

    /** Formats a list of persons as an indexed list.Private contact details are hidden.
     * 
     * @return the indexed list as a formatted string.
     */
    public static String showPersonListView(List<? extends ReadOnlyPerson> persons) {
        String[] formattedPersons = new String[persons.size()];
        int i = 0;
        for (ReadOnlyPerson person : persons) {
            formattedPersons[i] = person.getAsTextHidePrivate();
            i++;
        }
        return showToUserAsIndexedList(formattedPersons);
    }

    /** Formats a list of strings to the user as an indexed list. */
    private static String showToUserAsIndexedList(String[] list) {
        return showToUserFormat(getIndexedListForViewing(list)) + LINE_PREFIX + LINE_SEPARATOR;
    }

    /** Formats a list of strings as a viewable indexed list. */
    private static String[] getIndexedListForViewing(String[] listItems) {
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        int i = 0;
        for (String listItem : listItems) {
            listItems[i] = getIndexedListItem(displayIndex, listItem);
            displayIndex++;
            i++;
        }
        return listItems;
    }

    /** Formats a string as a viewable indexed list item.
     * 
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}
