package com.learny.ejb.dao.local;

import javax.ejb.Local;

import com.learny.ejb.dao.core.Dao;
import com.learny.persistence.entity.vocabulary.DeExample;

@Local
public interface DeExampleDaoLocal extends Dao<DeExample> {

}
