package com.jeremydyer.gpio.dao.jdbc;

import com.jeremydyer.gpio.core.Location;
import com.jeremydyer.gpio.dao.support.BaseDomainMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jeremydyer
 * Date: 1/30/14
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class LocationDaoJdbcMapper
    extends BaseDomainMapper<Location> {

    private static final Logger logger = LoggerFactory.getLogger(LocationDaoJdbcMapper.class);

    public LocationDaoJdbcMapper() {
        super.buildMap(Location.class);
    }

    @Override
    public Map<String, Object> insertParameters(Location item) {
        Map<String, Object> params = new HashMap<String, Object>();
        return params;
    }

    @Override
    public Map<String, Object> updateParameters(Location item) {
        Map<String, Object> params = new HashMap<String, Object>();
        return params;
    }

    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location location = new Location();
        return location;
    }
}
