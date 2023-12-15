package org.titans.repositories;

import org.checkerframework.checker.units.qual.C;
import org.titans.dao.impl.CategoryDAOImp;
import org.titans.dao.impl.TaskDAOImp;
import org.titans.entities.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CategoryRepository {
    CategoryDAOImp categoryDAOImp = new CategoryDAOImp();
    List<Category> categoryList ;

    public CategoryRepository() {
        this.categoryList = categoryDAOImp.getAllCategory();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void addCategoryRep(Category category){

        if (categoryDAOImp.addCategory(category)){
            categoryList.add(category);
        };
    }

    public  void updateCategoryRepo(String id,Category category ) throws Exception {

        if (categoryDAOImp.updateCategory(id, category)) {
            int index = IntStream.range(0, categoryList.size())
                    .filter(i -> categoryList.get(i).getId().equals(id))
                    .findFirst()
                    .orElseThrow(Exception::new);
            categoryList.set(index, category);
        }

        }

    public  void deleteCategoryRepo(String id){
        if(categoryDAOImp.deleteCategory(id)){
            categoryList.stream().filter(category -> category.getId().equals(id)).findFirst().ifPresent(categoryList::remove);
        }


    }
    public Category getCategoryBy(String id){
        return  categoryDAOImp.getCategoryById(id);
    }
}
