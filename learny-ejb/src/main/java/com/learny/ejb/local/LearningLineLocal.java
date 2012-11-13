package com.learny.ejb.local;

import java.util.List;

import javax.ejb.Local;

import com.learny.persistence.entity.Record;

@Local
public interface LearningLineLocal {

    List<Record> getRecords(Integer startCount);

}