package com.ilp04.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ilp04.entity.Customer;

public class CustomerDAO {

    public Connection getConnection() {
        String connectionURL = "jdbc:mysql://localhost:3306/bankdb?useSSL=false";
        String userName = "root";
        String password = "experion@123";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionURL, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> getAllCustomers(Connection connection) {
        ArrayList<Customer> customerList = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM customer");
            while (resultSet.next()) {
                int customerCode = resultSet.getInt(1);
                String customerFirstname = resultSet.getString(2);
                String customerLastname = resultSet.getString(3);
                String address = resultSet.getString(4);
                long phNumber = resultSet.getLong(5);
                long aadhaarNo = resultSet.getLong(6);
                Customer customer = new Customer(customerCode, customerFirstname, customerLastname, address, phNumber, aadhaarNo);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public int insertCustomer(Connection connection, Customer customer) {
        String insertSQL = "INSERT INTO customer (customer_firstname, customer_lastname, address, phone_number, aadhar_number) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, customer.getCustomerFirstname());
            preparedStatement.setString(2, customer.getCustomerLastname());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setLong(4, customer.getPhNumber());
            preparedStatement.setLong(5, customer.getAadharNo());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateCustomer(Connection connection, Customer customer) {
        String updateSQL = "UPDATE customer SET customer_firstname = ?, customer_lastname = ?, address = ?, phone_number = ?, aadhar_number = ? WHERE customer_code = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, customer.getCustomerFirstname());
            preparedStatement.setString(2, customer.getCustomerLastname());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setLong(4, customer.getPhNumber());
            preparedStatement.setLong(5, customer.getAadharNo());
            preparedStatement.setInt(6, customer.getCustomerCode());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
