package crud;

import database.database;
import objects.user;

import java.sql.*;
import java.util.ArrayList;

public class userCRUD {
    String databaseName;
    public userCRUD(String databaseName){
        this.databaseName = databaseName;
    }
    private int countRowsOfTable(){
        int size = 0;
        Connection connection = database.connect(databaseName);
        try{
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT COUNT(*) FROM "+"users"+";");
            while (results.next()){
                size = results.getInt(1);
            }
            return size;
        }catch (SQLException exception){
            System.out.printf("\nFailed to get the number of rows from "+"users"+"\n"+exception+"\n");
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return size;
    }

    private int countColumnsOfTable(){
        int size = 0;
        Connection connection = database.connect(databaseName);
        try{
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = '"+"users"+"';");
            while (results.next()){
                size = results.getInt(1);
            }
            return size;
        }catch (SQLException exception){
            System.out.printf("\nFailed to get the number of columns from "+"users"+"\n"+exception+"\n");
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return size;
    }
    /* ------------------------------------------------------------------------------- */
    public boolean saveOne(user user){
        Connection connection = database.connect(databaseName);
        boolean result = false;

        try {
            PreparedStatement preStatement;
            preStatement = connection.prepareStatement("INSERT INTO "+"users"+"(account, username, password, name, lastname, phone, email, content, age) VALUES (?,?,?,?,?,?,?,?,?);");
            preStatement.setString(1, user.getAccount());
            preStatement.setString(2, user.getUsername());
            preStatement.setString(3, user.getPassword());
            preStatement.setString(4, user.getName());
            preStatement.setString(5, user.getLast_name());
            preStatement.setString(6, user.getPhone());
            preStatement.setString(7, user.getEmail());
            preStatement.setDouble(8, user.getContent());
            preStatement.setInt(9, user.getAge());
            preStatement.execute();
            preStatement.close();
            return true;
        }catch (SQLException exception){
            System.out.printf("\nFailed to save the "+user.getClass().getName()+"\n"+exception+"\n");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    public user getById(int id){
        try{
            Connection connection = database.connect(databaseName);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM "+"users"+" WHERE "+"id"+" = '"+id+"';");
            user newUser = new user();
            if (result.next()){
                newUser.setAccount(result.getString("account"));
                newUser.setUsername(result.getString("username"));
                newUser.setPassword(result.getString("password"));
                newUser.setName(result.getString("name"));
                newUser.setLast_name(result.getString("lastname"));
                newUser.setPhone(result.getString("phone"));
                newUser.setEmail(result.getString("email"));
                newUser.setContent(result.getDouble("content"));
                newUser.setAge(result.getInt("age"));
            }
            statement.close();
            connection.close();
            return newUser;
        }catch (SQLException exception){
            System.out.printf("\nFailed to get the id "+id+"\n"+exception+"\n");
        }
        return null;
    }
    public user getByAccount(String account){
        try{
            Connection connection = database.connect(databaseName);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM "+"users"+" WHERE "+"account"+" = '"+account+"';");
            user newUser = new user();
            if (result.next()){
                newUser.setAccount(result.getString("account"));
                newUser.setUsername(result.getString("username"));
                newUser.setPassword(result.getString("password"));
                newUser.setName(result.getString("name"));
                newUser.setLast_name(result.getString("lastname"));
                newUser.setPhone(result.getString("phone"));
                newUser.setEmail(result.getString("email"));
                newUser.setContent(result.getDouble("content"));
                newUser.setAge(result.getInt("age"));
            }
            statement.close();
            connection.close();
            return newUser;
        }catch (SQLException exception){
            System.out.printf("\nFailed to get the account "+account+"\n"+exception+"\n");
        }
        return null;
    }
    public user getByUsername(String username, String password){
        try{
            Connection connection = database.connect(databaseName);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM "+"users"+" WHERE "+"username"+" = '"+username+"' AND password = '"+password+"';");
            user newUser = new user();
            if (result.next()){
                newUser.setAccount(result.getString("account"));
                newUser.setUsername(result.getString("username"));
                newUser.setPassword(result.getString("password"));
                newUser.setName(result.getString("name"));
                newUser.setLast_name(result.getString("lastname"));
                newUser.setPhone(result.getString("phone"));
                newUser.setEmail(result.getString("email"));
                newUser.setContent(result.getDouble("content"));
                newUser.setAge(result.getInt("age"));
            }
            statement.close();
            connection.close();
            return newUser;
        }catch (SQLException exception){
            System.out.printf("\nFailed to get the account "+username+"\n"+exception+"\n");
        }
        return null;
    }
    public boolean deleteById(int id){
        boolean result = false;

        try {
            Connection connection = database.connect(databaseName);
            PreparedStatement preStatement;
            preStatement = connection.prepareStatement("DELETE FROM "+"users"+" WHERE "+"id"+" = ?");
            preStatement.setInt(1, id);
            preStatement.execute();
            preStatement.close();
            connection.close();
            return true;
        }catch (SQLException exception){
            System.out.printf("\nFailed to delete the register\n"+exception+"\n");
        }

        return result;
    }
    public boolean deleteByAccount(String account){
        boolean result = false;

        try {
            Connection connection = database.connect(databaseName);
            PreparedStatement preStatement;
            preStatement = connection.prepareStatement("DELETE FROM "+"users"+" WHERE "+"account"+" = ?");
            preStatement.setString(1, account);
            preStatement.execute();
            preStatement.close();
            connection.close();
            return true;
        }catch (SQLException exception){
            System.out.printf("\nFailed to delete the register\n"+exception+"\n");
        }

        return result;
    }

    public boolean updateOne(user user){
        PreparedStatement preStatement;
        boolean result = false;

        try {
            Connection connection = database.connect(databaseName);
            preStatement = connection.prepareStatement("UPDATE " + "users" + " SET "
                    + "username = ?, "
                    + "password = ?, "
                    + "name = ?, "
                    + "lastname = ?, "
                    + "phone = ?, "
                    + "email = ?, "
                    + "content = ? "
                    + "WHERE " + "account" + " = ?"
            );
            preStatement.setString(1, user.getUsername());
            preStatement.setString(2, user.getPassword());
            preStatement.setString(3, user.getName());
            preStatement.setString(4, user.getLast_name());
            preStatement.setString(5, user.getPhone());
            preStatement.setString(6, user.getEmail());
            preStatement.setDouble(7, user.getContent());
            preStatement.setString(8, user.getAccount());
            preStatement.execute();
            preStatement.close();
            connection.close();
            return true;
        }catch (SQLException exception){
            System.out.printf("\nFailed to update the register\n"+exception+"\n");
        }
        return result;
    }

    public user[] getList(){
        ArrayList<user> userList = new ArrayList<>();

        user[] users = new user[countRowsOfTable()];

        try {
            Connection connection = database.connect(databaseName);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM "+"users"+";");

            while (results.next()){
                user newUser = new user();
                newUser.setAccount(results.getString("account"));
                newUser.setUsername(results.getString("username"));
                newUser.setPassword(results.getString("password"));
                newUser.setName(results.getString("name"));
                newUser.setLast_name(results.getString("lastname"));
                newUser.setPhone(results.getString("phone"));
                newUser.setEmail(results.getString("email"));
                newUser.setContent(results.getDouble("content"));
                newUser.setAge(results.getInt("age"));
                userList.add(newUser);
            }
            statement.close();
            connection.close();
            userList.toArray(users);
            return users;
        } catch (SQLException exception) {
            System.out.printf("\nFailed to get the list of "+"users"+"\n"+exception+"\n");
        }

        return null;
    }
}
