package com.learny.persistence.entity.vocabulary;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = DeWord.TABLE_NAME, uniqueConstraints = @UniqueConstraint(columnNames = {
        AbstractDefaultWord.VALUE_COLUMN, AbstractDefaultWord.DESC_COLUMN, AbstractGenderWord.GENDER_COLUMN }))
@NamedQueries(@NamedQuery(name = DeWord.QUERY_SEARCH_BY_VALUE, query = "select o from DeWord o where lower(o.value) like :"
        + DeWord.PARAM_SEARCH_VALUE + " order by o.value"))
public class DeWord extends AbstractGenderWord {

    private static final long serialVersionUID = -4817501817595969432L;

    public final static String TABLE_NAME = "DE_WORD";

    public final static String QUERY_SEARCH_BY_VALUE = "DeWord.querySearchByValue";
    public final static String PARAM_SEARCH_VALUE = "paramSearchValue";

    @JsonIgnore
    @ManyToMany(targetEntity = EnWord.class, fetch = FetchType.LAZY)
    @JoinTable(name = TABLE_NAME + UNDERSCORE + EnWord.TABLE_NAME, joinColumns = { @JoinColumn(name = TABLE_NAME
            + UNDERSCORE + ID) }, inverseJoinColumns = { @JoinColumn(name = EnWord.TABLE_NAME + UNDERSCORE + ID) })
    private Set<EnWord> enWords = new HashSet<>();

    @JsonIgnore
    @ManyToMany(targetEntity = DeExample.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = TABLE_NAME + UNDERSCORE + EnExample.TABLE_NAME, joinColumns = { @JoinColumn(name = TABLE_NAME
            + UNDERSCORE + ID) }, inverseJoinColumns = { @JoinColumn(name = EnExample.TABLE_NAME + UNDERSCORE + ID) })
    private Set<DeExample> deExamples = new HashSet<>();

    public Set<EnWord> getEnWords() {
        return enWords;
    }

    public void setEnWords(Set<EnWord> en_words) {
        this.enWords = en_words;
    }

    public Set<DeExample> getDeExamples() {
        return deExamples;
    }

    public void setDeExamples(Set<DeExample> de_examples) {
        this.deExamples = de_examples;
    }

    public void addDeExample(DeExample de_example) {
        deExamples.add(de_example);
    }

}
