package com.jeremydyer.gpio.dao.jdbc;

import com.jeremydyer.gpio.core.Location;
import com.jeremydyer.gpio.dao.LocationDao;
import com.jeremydyer.gpio.dao.support.BaseDaoImpl;

/**
 * Created with IntelliJ IDEA.
 * User: jeremydyer
 * Date: 1/30/14
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class LocationDaoJdbc
    extends BaseDaoImpl<Location>
    implements LocationDao {

    public LocationDaoJdbc() {
        super(LocationDaoJdbcMapper.class);
    }
}
