/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.titans.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.titans.entities.Category;
import org.titans.util.ConnectionDB;

/**
 *
 * @author hp
 */
public class CategoryDAOImp implements CategoryDAO {

    Connection connection;

    public CategoryDAOImp() {
        this.connection = ConnectionDB.getConnectionDB();
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> categoryList = new ArrayList<>();

        try {
            String getAllQuery = "SELECT * FROM `category` ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllQuery);
            while (resultSet.next()) {
                String ref = resultSet.getString("ref");
                String name_category = resultSet.getString("name_category");

                Category category = new Category(ref, name_category);
                categoryList.add(category);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;

    }

    @Override
    public void addCategory(Category category) {

        String sqlCheckExisist = "SELECT * from category WHERE name_category=?";
        String sqlInsertt = "INSERT INTO category (ref, name_category) values (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCheckExisist)) {
            preparedStatement.setString(1, category.getNom());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Category already exist");
                    return;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertt)) {
            preparedStatement.setString(1, category.getId());
            preparedStatement.setString(2, category.getNom());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                System.out.println("category added successfully");
            } else {
                System.out.println("Error ");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Category updateCategory(String ref, Category category) {
        try {
         String sqlCheckExisist = "SELECT * from category WHERE name_category=?";
           try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCheckExisist)) {
            preparedStatement.setString(1, category.getNom());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Category you want to update with already exist");
                    return null;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }

            String sql = "UPDATE category SET ref=?,name_category=? WHERE ref=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category.getId());
            preparedStatement.setString(2, category.getNom());
            preparedStatement.setString(3, ref);

            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                System.out.println("category updated successfully");
                return category;
            }
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteCategory(String ref) {
             String deleteQuery = "DELETE FROM category WHERE ref=?";
        if (connection == null) {
            System.out.println("Couldn't get connection to the database");
            return;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, ref);

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected == 1) {
                System.out.println("Category deleted successfully");
            } else {
                System.out.println("Category not Found");
            }

        } catch (SQLException se) {
            se.printStackTrace();

        }
        

    }

}
