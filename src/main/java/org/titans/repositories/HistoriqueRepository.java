package org.titans.repositories;

import org.titans.dao.HistoryActionDao;
import org.titans.dao.impl.HistoryActionDaoImp;
import org.titans.entities.Task;
import org.titans.entities.TaskHistoryAction;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueRepository {
    HistoryActionDaoImp historyActionDaoImp= new HistoryActionDaoImp();
    List<TaskHistoryAction> historyList = new ArrayList<>();

    void insertRepo(TaskHistoryAction taskHistoryAction){
        historyActionDaoImp.insert(taskHistoryAction);
    }
    List<TaskHistoryAction> getHistoryRepo(){
        historyList= historyActionDaoImp.getHistory();
        return historyList;}
}
