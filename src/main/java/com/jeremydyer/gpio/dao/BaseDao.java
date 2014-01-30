package com.jeremydyer.gpio.dao;

import com.jeremydyer.gpio.dao.support.Criteria;
import com.jeremydyer.gpio.dao.support.PagedRequest;
import com.jeremydyer.gpio.dao.support.PagedResponse;
import com.jeremydyer.gpio.dao.support.SortBy;
import com.jeremydyer.gpio.exception.DaoException;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 1/14/14
 * Time: 3:16 PM
 */

public interface BaseDao<T, ID> {

    public abstract T save(T item) throws DaoException;
    public abstract boolean exists(ID id) throws DaoException;
    public abstract boolean exists(List<Criteria> criterias) throws DaoException;
    public abstract T update(T item) throws DaoException;
    public abstract T create(T item) throws DaoException;

    public abstract PagedResponse<T> find(PagedRequest request, List<Criteria> criterias, List<SortBy> sortbys) throws DaoException;
    public abstract PagedResponse<T> find(PagedRequest request, Criteria criteria, List<SortBy> sortbys) throws DaoException;
    public abstract PagedResponse<T> find(PagedRequest request) throws DaoException;
    public abstract T find(ID id) throws DaoException;

    public abstract void deleteById(ID id) throws DaoException;
    public abstract void delete(List<Criteria> criterias) throws DaoException;
    public abstract void deleteAll() throws DaoException;
    void delete(Criteria criteria);
}
