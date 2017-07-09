/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author INT303
 */
public class ConnectionBuilder {

    private static org.apache.derby.jdbc.ClientDataSource ds = null;

    public static Connection getConnection() {
        Connection con = null;
        try {
            Context ctx = null;
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/sample");
            con = ds.getConnection();
        } catch (NamingException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return con;
    }

    public static Connection getConnectionDs() {
        Connection conn = null;
        try {
            if (ds == null) {
                Properties props = new Properties();
                FileInputStream fis = null;

                fis = new FileInputStream("db.properties");
                props.load(fis);

                ds = new org.apache.derby.jdbc.ClientDataSource();

                ds.setServerName(props.getProperty("DERBY_SERVER_NAME"));
                ds.setPortNumber(Integer.parseInt(props.getProperty("DERBY_SERVER_PORT")));
                ds.setDatabaseName(props.getProperty("DERBY_DB_NAME"));
                ds.setUser(props.getProperty("DERBY_DB_USERNAME"));
                ds.setPassword(props.getProperty("DERBY_DB_PASSWORD"));
            }
            conn = ds.getConnection();

        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return conn;
    }

    public static Connection getMySqlCond() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        return conn;
    }
}
