package com.oraclejava.basedao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// BASE DAO 사용법
// DRIVER  URL  USER   USER만 바꿔주면됨
// BASE DAO를 만들어줬으면 DAO를 만들어줘야함.(abstract이므로 DAO에서 상속해서 사용해야함)


public abstract class BaseDAO<T> {
    public final String DRIVER = "oracle.jdbc.OracleDriver";
    public final String URL = "jdbc:oracle:thin:@localhost:1521:oraclejava";
    public final String USER = "hr";
    
    public final String PWD = "hr";

    protected Connection conn;
    protected PreparedStatement pstmt;
    protected ResultSet rs;

    private Class entityClass;

    public BaseDAO() {

        Type genericType = getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        Type actualType = actualTypeArguments[0];
        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConn() {
        try {
            //1.
            Class.forName(DRIVER);

            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setParams(PreparedStatement pstmt, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i=0; i<params.length; i++) {
                pstmt.setObject(i+1, params[i]);
            }
        }
    }

    private void setValue(Object obj, String property, Object propertyValue) {
        Class clazz = obj.getClass();
        try {
            Field field = clazz.getDeclaredField(property.toLowerCase());
            field.setAccessible(true);
            field.set(obj, propertyValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<T> executeQuery(String sql, Object... params) {
        List<T> list = new ArrayList<>();
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            setParams(pstmt, params);
            rs = pstmt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                T entity = (T)entityClass.newInstance();
                for(int i=0; i<columnCount; i++) {
                    String columnName = rsmd.getColumnName(i+1);
                    Object columnValue = rs.getObject(i+1);
                    setValue(entity, columnName, columnValue);

                }
                list.add(entity);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }

        return list;
    }





}
