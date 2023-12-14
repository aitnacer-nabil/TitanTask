package org.titans.repositories;

import org.titans.dao.impl.CategoryDAOImp;
import org.titans.dao.impl.TaskDAOImp;
import org.titans.entities.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CategoryRepository {
    CategoryDAOImp categoryDAOImp = new CategoryDAOImp();
    List<Category> categoryList = new ArrayList<>();

    public CategoryRepository() {
        this.categoryList = categoryDAOImp.getAllCategory();
    }



    void addCategoryRep(Category category){

        if (categoryDAOImp.addCategory(category)){
            categoryList.add(category);
        };
    }

    void updateCategoryRepo(String id,Category category ) throws Exception {

        if (categoryDAOImp.updateCategory(id, category)) {
            int index = IntStream.range(0, categoryList.size())
                    .filter(i -> categoryList.get(i).getId().equals(id))
                    .findFirst()
                    .orElseThrow(Exception::new);
            categoryList.set(index, category);
        }

        }

    void deleteCategoryRepo(String id){
        if(categoryDAOImp.deleteCategory(id)){
            categoryList.stream().filter(category -> category.getId().equals(id)).findFirst().ifPresent(categoryList::remove);
        }


    }
}
