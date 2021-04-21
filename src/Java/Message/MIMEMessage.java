package Java.Message;

import Java.Constants.ConnectionConstants;
import Java.Constants.ContentTypeConstants;
import Java.Constants.EmailPartsConstants;
import Java.Constants.SMTPCommands;

// CLASS UNIMPLEMENTED
public class MIMEMessage {
    public String boundary = "thisIsBoundary";

    private String message = "";
    private String header = "";
    private String body = "";

    public MIMEMessage(String sender, String recipient, String subject, String body, String file) {

        // message += EmailPartsConstants.USER_AGENT_HEADER_FIELD + ConnectionConstants.LF;
        // message += EmailPartsConstants.SUBJECT_HEADER_FIELD + " " + subject + ConnectionConstants.LF;
        this.body += getHeader(subject) + ConnectionConstants.CRLF;

        this.body += ConnectionConstants.CRLF + "--" + boundary + ConnectionConstants.LF;
        this.body += getTextPart(body);
        this.body += ConnectionConstants.CRLF + "--" + boundary + ConnectionConstants.LF;
        this.body += getPngPart(file);
        this.body += ConnectionConstants.CRLF + "--" + boundary + "--" + ConnectionConstants.LF;
        message += this.body + SMTPCommands.MESSAGE_END;
    }


    /*
    Content-Type: image/png;
    name="index.png"
    Content-Transfer-Encoding: base64
    Content-Disposition: attachment;
    filename="index.png"
    */
    private String getPngPart(String file) {
        String pngPart = "Content-Type: image/png; name=\"index.png\"";
        pngPart += ConnectionConstants.LF + "Content-Transfer-Encoding: base64";
        pngPart += ConnectionConstants.LF + "Content-Disposition: attachment; filename=\"index.png\"";
        pngPart += ConnectionConstants.LF + ConnectionConstants.LF;
        pngPart += MIMEBase64.encode(file);

        return pngPart;
    }


    /* MIME-Version: 1.0
        Content-Type: multipart/mixed;
        boundary="------------C19BD9E2A1CB11E6630FE6B2"
        Content-Language: en-US*/
    private String getHeader(String subject) {
        // header += EmailPartsConstants.USER_AGENT_HEADER_FIELD + ConnectionConstants.LF;
        header += EmailPartsConstants.SUBJECT_HEADER_FIELD + " " + subject + ConnectionConstants.LF;
        header += EmailPartsConstants.MIME_VERSION_HEADER_FIELD + ConnectionConstants.LF;
        header += EmailPartsConstants.CONTENT_TYPE_HEADER_FIELD + " " + ContentTypeConstants.MULTIPART + ContentTypeConstants.MIXED + 
                  ConnectionConstants.LF + " boundary=\"" + boundary + "\"";

        return header;
    }


    /*This is a multi-part message in MIME format.
    --------------C19BD9E2A1CB11E6630FE6B2
    Content-Type: text/plain; charset=utf-8; format=flowed
    Content-Transfer-Encoding: 7bit
    */
    private String getTextPart(String text) {
        String textPart = "Content-Type: text/plain; charset=utf-8; format=flowed";
        textPart += ConnectionConstants.LF + "Content-Transfer-Encoding: 7bit";
        textPart += ConnectionConstants.LF + ConnectionConstants.LF;
        textPart += text;

        return textPart;
    }

    public String getMessage() {
        return message;
    }

    
}
