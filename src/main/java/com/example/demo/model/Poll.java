package com.example.demo.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    private long id;
    private String pollTitle;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Option> options;
    @OneToOne
    private User author;

    public Poll(String pollTitle, List<Option> options, User author) {
        this.id = id;
        this.pollTitle = pollTitle;
        this.options = options;
        this.author = author;
    }

    public Poll() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPollTitle() {
        return pollTitle;
    }

    public void setPollTitle(String pollTitle) {
        this.pollTitle = pollTitle;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public User getOwner() {
        return author;
    }

    public void setOwner(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", pollTitle='" + pollTitle + '\'' +
                ", options=" + options +
                ", author=" + author +
                '}';
    }
}
