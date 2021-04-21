package Java.Constants;

public class ContentTypeConstants {

    // Types
    public static final String TEXT = "text/";
    public static final String IMAGE = "image/";
    public static final String AUDIO = "audio/";
    public static final String VIDEO = "video/";
    public static final String APPLICATION = "application/";
    public static final String MULTIPART = "muptipart/";

    // Subtypes
    // TEXT type
    public static final String PLAIN = "plain";
    public static final String HTML = "html";

    // IMAGE type
    public static final String PNG = "png";
    public static final String GIF = "gif";
    public static final String JPEG = "jpeg";

    // AUDIO type
    public static final String BASIC = "basic";  
    public static final String WAV = "wav";  

    // VIDEO type
    public static final String MPEG = "mpeg";  

    // APPLICATION type
    public static final String PDF = "pdf";  
    public static final String ZIP = "zip";  


    // MIXED type
    public static final String MIXED = "mixed";

    public static final String[] SUPPORTING_TYPES = {
        TEXT + PLAIN, IMAGE + PNG, MULTIPART + MIXED
    };
}
