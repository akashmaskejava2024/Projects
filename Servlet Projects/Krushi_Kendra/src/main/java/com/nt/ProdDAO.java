package com.nt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdDAO {

    private static Connection getconn() {
        Connection conn = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure you have the correct driver class

            // Establish connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Admin");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    static int InsertProduct(String name, int batch, int price, int stock, String type) {
        int r = 0;
        try (Connection conn = getconn();
             PreparedStatement ps = conn.prepareStatement("insert into product (name, batch, price, stock, type) values (?,?,?,?,?)")) {

            ps.setString(1, name);
            ps.setInt(2, batch);
            ps.setInt(3, price);
            ps.setInt(4, stock);
            ps.setString(5, type);

            r = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    static int AddSale(int id) {
        int r = 0;
        try (Connection conn = getconn();
             PreparedStatement ps = conn.prepareStatement("update product set stock = stock - 1 where id = ?")) {

            ps.setInt(1, id);

            r = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    static int ReturnProduct(int id) {
        int r = 0;
        try (Connection conn = getconn();
             PreparedStatement ps = conn.prepareStatement("update product set stock = stock + 1 where id = ?")) {

            ps.setInt(1, id);

            r = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    static ResultSet check_lessthan_stock(int no) {
        ResultSet rs = null;
        try {
            Connection conn = getconn();
            PreparedStatement ps = conn.prepareStatement(Prod_Query.check_stock_with_lessthan_stock);
            ps.setInt(1, no);

            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    static ResultSet check_stock_lessthan_price(int price) {
        ResultSet rs = null;
        try {
            Connection conn = getconn();
            PreparedStatement ps = conn.prepareStatement(Prod_Query.check_stock_with_lessthan_price);
            ps.setInt(1, price);

            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    static ResultSet check_stock_greaterthan_price(int price) {
        ResultSet rs = null;
        try {
            Connection conn = getconn();
            PreparedStatement ps = conn.prepareStatement(Prod_Query.check_stock_with_greaterthan_price);
            ps.setInt(1, price);

            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    static ResultSet check_stock_with_type(String type) {
        ResultSet rs = null;
        try {
            Connection conn = getconn();
            PreparedStatement ps = conn.prepareStatement(Prod_Query.check_stock_with_type);
            ps.setString(1, type);

            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    static ResultSet check_stock_with_first_letter(String letter) {
        ResultSet rs = null;
        try {
            Connection conn = getconn();
            PreparedStatement ps = conn.prepareStatement(Prod_Query.check_prod_with_let);
            String check = letter + "%";
            ps.setString(1, check);

            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
