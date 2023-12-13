/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.titans.services;

import java.util.List;
import org.titans.entities.Category;

/**
 *
 * @author hp
 */
public interface CategoryDAO {
    
    List<Category> getAllCategory();
    void addCategory(Category category);
    Category updateCategory(String id,Category category );
    void deleteCategory(String id);
    
}
