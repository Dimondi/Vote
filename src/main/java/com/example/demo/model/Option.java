package com.example.demo.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    private long id;
    private String value;
    private long rate;

    public Option(String value, long rate) {
        this.value = value;
        this.rate = rate;
    }

    public Option(String value) {
        this.value = value;
    }

    public Option() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", rate=" + rate +
                '}';
    }
}
