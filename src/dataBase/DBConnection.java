/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rifat
 */
public class DBConnection {

    Properties properties = new Properties();
    InputStream inputStream;
    Long startConnect = new Long(new Date().getTime());
    String instanceConnectionName = "belajarfirebase-57a76:us-central1:pembukuan-prod";

    String databaseName = "pembukuan";
    String username = "root";
    String password = "tomkins051294";
    public Connection con;

    String url;
    String user;
    String pass;
    String unicode = "?useUnicode=true&characterEncoding=UTF-8";
//    String unicode = "";

    public void loadPropertiesFile() {
        try {
            inputStream = new FileInputStream("database.properties");
            properties.load(inputStream);
            url = "jdbc:mysql://" + properties.getProperty("host") + ":" + properties.getProperty("port") + "/";
//            url = String.format("jdbc:mysql://google/%s?cloudSqlInstance=%s"
//                    + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
//                    databaseName,
//                    instanceConnectionName);
            user = properties.getProperty("user");
            pass = properties.getProperty("password");
            System.out.println(url);
        } catch (IOException e) {
            System.out.println("DDDD");
        }
    }

    public Connection mkDataBase() throws SQLException {
        loadPropertiesFile();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                Long endConnect = new Long(new Date().getTime());
                Logger.getLogger(DBConnection.class.getName()).log(Level.INFO, endConnect - startConnect + "ms", startConnect - endConnect + "ms");
            }
            System.out.println(url);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
        return con;
    }

    public Connection geConnection() {
        loadPropertiesFile();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url + unicode, user, pass);
            if (con != null) {
                Long endConnect = new Long(new Date().getTime());
                Logger.getLogger(DBConnection.class.getName()).log(Level.INFO, endConnect - startConnect + "ms", startConnect - endConnect + "ms");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
