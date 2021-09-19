package org.example.JukeBox.DaoClass;

import org.example.JukeBox.DatabaseSetup;
import org.example.JukeBox.Interface.AccountInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountImpl implements AccountInterface {

    public void createAccount(String name, int age, String email, String password) {
        int userId = 0;
        try(Connection connection = DatabaseSetup.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user(username,age,emailId,userPassword)values(?,?,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,password);

            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected>0){
                System.out.println("Account created Successfully");
            }
            else{
                System.out.println("Error while creating account");
            }

        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public int getUserId(String email, String password){
        int userId = 0;
        try(Connection connection = DatabaseSetup.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("Select userId from user where emailId = ? and userPassword = ?");
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                userId = resultSet.getInt(1);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return userId;
    }

    public boolean checkIfUserHaveAccount(int userId, String password) {
        int gotUserId = 0;
        String gotPassword = "";
        try(Connection connection = DatabaseSetup.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("Select userId, userpassword from user where userId = ? and userpassword = ?");
            preparedStatement.setInt(1,userId);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                gotUserId = resultSet.getInt(1);
                gotPassword = resultSet.getString(2);
            }
            if(gotUserId == userId && gotPassword.equalsIgnoreCase(password)){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        return false;
    }


}
