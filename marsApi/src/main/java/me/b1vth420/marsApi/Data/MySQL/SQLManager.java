package me.b1vth420.marsApi.Data.MySQL;


import me.b1vth420.marsApi.Api;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLManager {

    private final Api plugin;
    private final ConnectionPoolManager pool;

    public SQLManager(Api plugin) {
        this.plugin = plugin;
        pool = new ConnectionPoolManager(plugin);
    }

    public void createTable(String statement) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement(statement);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
    }

    public void saveData(String statement, String[] data) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement(statement);
            for (int i = 1; i < data.length + 1; i++) {
                ps.setString(i, data[i - 1]);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
    }

    public String loadData(String statement, String[] data) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        String s = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement(statement);
            for (int i = 1; i < data.length + 1; i++) {
                ps.setString(i, data[i - 1]);
            }
            result = ps.executeQuery();
            if (result.next()) {
                ResultSetMetaData rsmd = result.getMetaData();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    s += result.getString(i + 1) + " ";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, result);
        }
        return s;
    }

    public int checkNumber(String statement, String[] data) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        int s = 0;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement(statement);
            for (int i = 1; i < data.length + 1; i++) {
                ps.setString(i, data[i - 1]);
            }
            result = ps.executeQuery();
            if (result.next()) {
                s++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, result);
        }
        return s;
    }

    public List<String> loadData(String statement) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        List<String> results = new ArrayList<>();
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement(statement);
            result = ps.executeQuery();
            while (result.next()) {
                String s = "";
                ResultSetMetaData rsmd = result.getMetaData();
                for (int i = 0; i < rsmd.getColumnCount(); i++)
                    s += result.getString(i + 1) + " ";
                results.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, result);
        }
        return results;
    }

    public void onDisable() {
        pool.closePool();
    }

}
