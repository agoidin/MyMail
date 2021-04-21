package Java.Constants;

/**
 * IMAPCommands
 */
public class IMAPCommands {

    public static final String LOGIN = "LOGIN";
    public static final String LOGOUT = "LOGOUT";

    public static final String SELECT_FOLDER = "SELECT";
    public static final String OPEN_INBOX_FOLDER = "SELECT \"INBOX\"";
    
    public static final String FETCH = "FETCH";
    public static final String SEARCH = "SEARCH";
    public static final String STORE = "STORE";

    public static final String ADD_FLAG = "+FLAGS";
    public static final String REMOVE_FLAG = "-FLAGS";


    // public static final String ALL_FLAG = "ALL";
    // public static final String UNSEEN_FLAG = "UNSEEN";
    public static final String SEEN_FLAG = "\\Seen";
    public static final String ANSWERED_FLAG = "\\Answered";
    public static final String FLAGGED_FLAG = "\\Flagged";
    // public static final String DELETED_FLAG = "DELETED";
    // public static final String DRAFT_FLAG = "DRAFT";
    
    public static final String MESSAGE_BODY = "BODY";
    public static final String MESSAGE_BODY_PEEK = "BODY.PEEK";

    
    public static final String MESSAGE_FLAGS = "FLAGS";
    
    public static final String ID_FIELD = "[HEADER.FIELDS (Message-id)]";
    public static final String SUBJECT_FIELD = "[HEADER.FIELDS (SUBJECT)]";
    public static final String SENDER_FIELD = "[HEADER.FIELDS (FROM)]";
    public static final String RECEIVER_FIELD = "[HEADER.FIELDS (TO)]";
    public static final String DATE_FIELD = "[HEADER.FIELDS (DATE)]";
    public static final String MESSAGE_CONTENT_FIELD = "[TEXT]";
    public static final String MIME_MESSAGE_CONTENT_FIELD = "[1]";
    // f fetch 11 body[
}