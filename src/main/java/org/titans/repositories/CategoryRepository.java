package org.titans.repositories;

import org.titans.dao.impl.CategoryDAOImp;
import org.titans.dao.impl.TaskDAOImp;
import org.titans.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    CategoryDAOImp categoryDAOImp = new CategoryDAOImp();
    List<Category> categoryList = new ArrayList<>();

    List<Category> getAllCategoryRepo(){
        categoryList=categoryDAOImp.getAllCategory();
        return categoryList;}

    void addCategoryRep(Category category){

        categoryDAOImp.addCategory(category);
    }

    Category updateCategoryRepo(String id,Category category ){
        Category category1= new Category();
        category1= categoryDAOImp.updateCategory(id,category);

        return category1;}

    void deleteCategoryRepo(String id){
        categoryDAOImp.deleteCategory(id);
        categoryList.stream().filter(category -> category.getId().equals(id)).findFirst().ifPresent(categoryList::remove);

    }
}
