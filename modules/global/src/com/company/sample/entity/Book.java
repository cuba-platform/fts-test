package com.company.sample.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@NamePattern("%s|title")
@Table(name = "SAMPLE_BOOK")
@Entity(name = "sample_Book")
public class Book extends StandardEntity {
    private static final long serialVersionUID = -5806446591006927080L;

    @Column(name = "TITLE")
    protected String title;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EBOOK_ID")
    protected FileDescriptor ebook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    protected Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public FileDescriptor getEbook() {
        return ebook;
    }

    public void setEbook(FileDescriptor ebook) {
        this.ebook = ebook;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}