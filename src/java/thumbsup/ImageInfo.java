package thumbsup;

public class ImageInfo {
    private final int id;
    private final String fileName;
    private final String contentType;
    
    public ImageInfo(int id, String fileName, String contentType) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }  
}
