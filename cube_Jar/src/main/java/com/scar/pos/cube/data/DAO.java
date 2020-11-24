package com.scar.pos.cube.data;

import com.scar.pos.cube.model.Cube;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private final String db_user = "usr";
    private final String db_pw = "pwd";

    private static DAO instance = null;

    private DAO() {  }

    public static DAO getInstance() {
        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    }

    private Connection createConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("*** IFX *** driver loaded successfully");
            con = DriverManager.getConnection("jdbc:mysql://ProductsDB:3306/db", db_user, db_pw);
            System.out.println("*** IFX *** connected successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

    public List<Cube> getAll() {
        List<Cube> all = new ArrayList<>();
        Connection con = this.createConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, sides, material, corners, edges FROM Cubes");
            while (rs.next()) {
                all.add(new Cube(rs.getInt("id"), rs.getInt("sides"), rs.getString("material"), rs.getInt("corners"), rs.getFloat("edges")));
            }
            System.out.println("*** IFX *** read " +all.size() +" products");
        } catch (SQLException e) {
            System.out.println("Could not read cubes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return all;
    }
}