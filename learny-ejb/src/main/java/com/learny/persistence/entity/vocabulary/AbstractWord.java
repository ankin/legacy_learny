package com.learny.persistence.entity.vocabulary;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.learny.persistence.entity.core.IdEntity;

@MappedSuperclass
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "VALUE", "TYPE" }))
public class AbstractWord extends IdEntity {

    private static final long serialVersionUID = 4357259868217746912L;

    @Column(name = "VALUE", nullable = false)
    private String value;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ABBREVIATION")
    private String abbreviation;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        AbstractWord abstractWord = (AbstractWord) obj;
        if (this.getValue() == null || abstractWord.getValue() == null || this.getType() == null
                || abstractWord.getType() == null) {
            return false;
        }

        return this.getValue().equals(abstractWord.getValue()) && this.getType().equals(abstractWord.getType());

    }
}