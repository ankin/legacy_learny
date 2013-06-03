package com.learny.ejb.dao.core;

import javax.persistence.NoResultException;

public class AbstractWordDao<T> extends AbstractDao<T> implements WordDao<T> {

    @Override
    public final T findByValue(final String value) {
        try {
            final String query = "select o from " + getPersistentClass().getSimpleName() + " o where o.value=:value";
            final T result = (T) getEntityManager().createQuery(query).setParameter("value", value).getSingleResult();
            return result;
        } catch (final NoResultException ex) {
            return null;
        }
    }

}
