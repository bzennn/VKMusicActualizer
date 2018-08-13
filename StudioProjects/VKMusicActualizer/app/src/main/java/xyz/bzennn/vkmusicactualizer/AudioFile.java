package xyz.bzennn.vkmusicactualizer;

public class AudioFile {
    private String name;
    private String author;
    private String url;
    private String  length;

    public AudioFile(String name, String author, String url, String length) {
        this.name = name;
        this.author = author;
        this.url = url;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getLength() {
        return length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
