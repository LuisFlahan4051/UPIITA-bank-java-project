package database;

import java.sql.*;

public class database {

    public static Connection connect(String databaseName) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:./"+databaseName+".db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static boolean testConnectionToDB(String databaseName) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:./"+databaseName+".db";
            conn = DriverManager.getConnection(url);
            System.out.println(databaseName+" connected");
            return true;
        } catch (SQLException e) {
            System.out.println(new Exception("No database found, database:" +databaseName+" created").getMessage());
            return false;
        } finally {
            try {
                if (conn != null) {
                    Statement stmt = null;
                    try {
                        stmt = conn.createStatement();
                        String sql = "CREATE TABLE \"users\" (\n" +
                                "\t\"id\"\tINTEGER,\n" +
                                "\t\"account\"\tTEXT NOT NULL UNIQUE,\n" +
                                "\t\"username\"\tTEXT NOT NULL UNIQUE,\n" +
                                "\t\"password\"\tTEXT NOT NULL,\n" +
                                "\t\"name\"\tTEXT,\n" +
                                "\t\"lastname\"\tREAL,\n" +
                                "\t\"phone\"\tTEXT,\n" +
                                "\t\"email\"\tTEXT,\n" +
                                "\t\"content\"\tREAL DEFAULT 0,\n" +
                                "\t\"age\"\tINTEGER,\n" +
                                "\tPRIMARY KEY(\"id\")\n" +
                                ");";
                        stmt.execute(sql);
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
