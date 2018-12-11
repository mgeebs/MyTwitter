package murach.sql;

import dataaccess.*;
import java.sql.*;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class ConnectionPool
{
    
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    public static String getHtmlTable(ResultSet results)
            throws SQLException
    {
        StringBuilder htmlTable = new StringBuilder();
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();

        htmlTable.append("<table>");
        
        // add header row
        htmlTable.append("<tr>");
        for (int i = 1; i <= columnCount; i++) {
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnName(i));
            htmlTable.append("</th>");
        }
        htmlTable.append("</tr>");
        
        // add all other rows
        while (results.next())
        {
            htmlTable.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                htmlTable.append("<td>");
                htmlTable.append(results.getString(i));
                htmlTable.append("</td>");
            }
            htmlTable.append("</tr>");
        }
        
        htmlTable.append("</table>");
        return htmlTable.toString();
    }    
    

    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) 
                ic.lookup("java:/comp/env/jdbc/twitterdb");
        } catch (NamingException e) {
            System.out.println(e);
        }
    }
    
    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
