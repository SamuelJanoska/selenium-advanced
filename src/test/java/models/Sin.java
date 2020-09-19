package models;

import enumerators.SinType;

import java.util.List;

public class Sin {

    private String title;
    private String author;
    private String message;
    private Boolean forgiven;

    private List<SinType> tags;


    public Sin(String title, String author, String message, Boolean forgiven){
        this.title = title;
        this.author = author;
        this.message = message;
        this.forgiven = false;
    }

    public String getTitle() { return title;    }

    public String getAuthor() { return author;    }

    public String getMessage() { return message;    }


    public Boolean getForgiven() {
        return forgiven;
    }

    public void setTitle(String title) { this.title = title;    }

    public void setForgiven(Boolean forgiven) {
        this.forgiven = forgiven;
    }


    public List<SinType> getTags() {
        return tags;
    }

    public void setTags(List<SinType> tags) {
        this.tags = tags;
    }
}
