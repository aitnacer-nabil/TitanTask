package org.titans;


import java.util.List;
import org.titans.entities.Category;
import org.titans.entities.Task;
import org.titans.services.CategoryDAOImp;
import org.titans.services.TaskDAOImp;

public class Main {
    public static void main(String[] args) {
       TaskDAOImp taskDAOImp = new TaskDAOImp();
     // Task task = new Task("Enrigster","Write sql query",null,Priority.HAUTE);
    // taskDAOImp.addTask(task);
     // taskDAOImp.addPriorityToTask(14,Priority.BASSE);
  
  /*List<Task> tasks=taskDAOImp.filterByCategory("development");
    for(Task task:tasks){
        System.out.println(task);
    }*/
  
    //CategoryDAOImp catM =new CategoryDAOImp();
       List<Task> fil= taskDAOImp.filterByCategory("development");
          for(Task ts:fil){
        System.out.println(ts);
    } 
//    Category category =new Category("st","ddst");
//   catM.addCategory(category);
    
   
  
    }
}