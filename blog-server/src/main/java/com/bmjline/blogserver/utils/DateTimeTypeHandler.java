package com.bmjline.blogserver.utils;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author bmj
 */
@MappedJdbcTypes(JdbcType.TIMESTAMP_WITH_TIMEZONE)
@MappedTypes(String[].class)
public class DateTimeTypeHandler implements TypeHandler<DateTime> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, DateTime dateTime, JdbcType jdbcType) throws SQLException {
        preparedStatement.setTimestamp(i, new Timestamp(dateTime.getMillis()));
    }

    @Override
    public DateTime getResult(ResultSet resultSet, String s) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(s);
        if (timestamp == null) {
            return DateTime.now();
        }
        return new DateTime(timestamp);
    }

    @Override
    public DateTime getResult(ResultSet resultSet, int i) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(i);
        if (timestamp == null) {
            return DateTime.now();
        }
        return new DateTime(timestamp);
    }

    @Override
    public DateTime getResult(CallableStatement callableStatement, int i) throws SQLException {
        Timestamp timestamp = callableStatement.getTimestamp(i);
        if (timestamp == null) {
            return DateTime.now();
        }
        return new DateTime(timestamp);
    }
}
