package com.jeremydyer.gpio.dao.support;


import com.jeremydyer.gpio.util.ACWebUtil;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Jeremy Dyer
 * Date: 1/14/14
 * Time: 3:32 PM
 */
public abstract class BaseDomainMapper<T> implements DomainMapper<T>{

    protected Map<String, String> columnMap = new HashMap<String, String>();
    protected String tablename;
    protected String primaryKeyName;
    protected Field primaryKeyField;

    public BaseDomainMapper() {
        super();
    }

    protected void buildMap(Class<T> c) {
        if (c.isAnnotationPresent(Table.class)){
            Table table = c.getAnnotation(Table.class);
            tablename = table.name();
        }
        for (Field f : c.getDeclaredFields()) {
            if (f.isAnnotationPresent(Column.class)) {
                Column column = f.getAnnotation(Column.class);
                columnMap.put(f.getName(), column.name());
                if (f.isAnnotationPresent(Id.class)) {
                    primaryKeyField = f;
                    f.setAccessible(true);
                    primaryKeyName = column.name();
                }
            }
        }
    }
    @Override
    public Object getPrimaryKeyValue(T item) {
        try {
            return primaryKeyField.get(item);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void setPrimaryKey(Object newId, T item) {
        try {
            primaryKeyField.set(item, newId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getColumn(String attributeName) {

        // 2013-07-31 modified this to handle cases where we know the column name, but
        // there's no annotation for it.

        if (columnMap.containsKey(attributeName)) {
            return columnMap.get(attributeName);
        } else {
            return attributeName;
        }
    }

    @Override
    public String getTablename() {
        return tablename;
    }

    @Override
    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public Long getLong(ResultSet rs, String columnName) throws SQLException {
        return ACWebUtil.getLongOrNull(rs, columnName);
    }
    public Integer getInt(ResultSet rs, String columnName) throws SQLException {
        return ACWebUtil.getIntOrNull(rs, columnName);
    }
    public Short getShort(ResultSet rs, String columnName) throws SQLException {
        return ACWebUtil.getShortOrNull(rs, columnName);
    }

    public Boolean getBoolean(ResultSet rs, String columnName) throws SQLException {
        return ACWebUtil.getBooleanOrNull(rs, columnName);
    }
    public Double getDouble(ResultSet rs, String columnName) throws SQLException {
        return ACWebUtil.getDoubleOrNull(rs, columnName);
    }
}
