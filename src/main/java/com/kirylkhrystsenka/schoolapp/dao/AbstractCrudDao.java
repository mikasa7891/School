package com.kirylkhrystsenka.schoolapp.dao;

import com.kirylkhrystsenka.schoolapp.models.HasId;

public abstract class AbstractCrudDao<T extends HasId<K>, K>  {
    protected abstract T create(T entity);

    protected abstract T update(T entity);
}
