package com.learny.ejb.service.local;

import java.util.List;

import javax.ejb.Local;

import com.learny.persistence.entity.Language;
import com.learny.persistence.entity.Word;

@Local
public interface TranslatorLocal {



    void translate(List<Word> words, Language language);

    void translate(Word word, Language language);

}
