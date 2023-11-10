package com.bookadaisical.model;

import com.bookadaisical.utils.TargetAudience;

public class Book {
    private int uniqueId;
    private String title;
    private String author;
    private String description;
    private TargetAudience targetAudience;

    public Book(int uniqueId, String title, String author, String description, TargetAudience targetAudience)
    {
        this.uniqueId = uniqueId;
        this.title = title;
        this.author = author;
        this.description = description;
        this.targetAudience = targetAudience;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public TargetAudience getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(TargetAudience targetAudience) {
        this.targetAudience = targetAudience;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

}
