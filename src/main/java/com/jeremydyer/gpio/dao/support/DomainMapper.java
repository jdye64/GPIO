package com.jeremydyer.gpio.dao.support;

import org.springframework.jdbc.core.RowMapper;

import java.util.Map;

/**
 * User: Jeremy Dyer
 * Date: 1/14/14
 * Time: 3:26 PM
 */
interface DomainMapper<T> extends RowMapper<T> {

    public abstract String getColumn(String attributeName);

    public abstract Map<String, Object> insertParameters(T item);

    public abstract Map<String, Object> updateParameters(T item);

    public abstract String getTablename();

    public abstract String getPrimaryKeyName();
    public abstract Object getPrimaryKeyValue(T item);

    public abstract void setPrimaryKey(Object newId, T item);

}
