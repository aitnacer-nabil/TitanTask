/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.titans.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.titans.entities.Category;
import org.titans.dao.CategoryDAO;
import org.titans.util.ConnectionDB;

/**
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
            String getAllQuery = "SELECT * FROM category ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllQuery);
            while (resultSet.next()) {
                String id_category = resultSet.getString("id_category");
                String name_category = resultSet.getString("name_category");

                Category category = new Category(id_category, name_category);
                categoryList.add(category);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;

    }

    @Override
    public boolean addCategory(Category category) {


        String sqlInsertt = "INSERT INTO category (id_category, name_category) values (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertt)) {
            preparedStatement.setString(1, category.getId());
            preparedStatement.setString(2, category.getNom());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                System.out.println("category added successfully");
                return true;
            } else {
                System.out.println("Error ");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
return  false;

    }



    @Override
    public boolean updateCategory(String id ,Category category) {



        try {
            String sql = "UPDATE category SET name_category=? WHERE id_category=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, category.getNom());
            preparedStatement.setString(2, id);

            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                System.out.println("category updated successfully");
                return true;
            }
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean deleteCategory(String id) {
        String deleteQuery = "DELETE FROM category WHERE id_category=?";
        if (connection == null) {
            System.out.println("Couldn't get connection to the database");
            return false;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, id);

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected == 1) {
                System.out.println("Category deleted successfully");
                return true;
            } else {
                System.out.println("Category not Found");
            }

        } catch (SQLException se) {
            se.printStackTrace();

        }

return false;
    }

}
