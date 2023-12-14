package org.titans.repositories;

import org.titans.dao.HistoryActionDao;
import org.titans.dao.impl.HistoryActionDaoImp;
import org.titans.entities.Task;
import org.titans.entities.TaskHistoryAction;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueRepository {
    HistoryActionDaoImp historyActionDaoImp;
    List<TaskHistoryAction> historyList;



    public HistoriqueRepository() {
        this.historyActionDaoImp = new HistoryActionDaoImp();
        this.historyList = historyActionDaoImp.getHistory();
    }

    public List<TaskHistoryAction> getHistoryList() {
        return historyList;
    }
    public void deleteHistory(){
        //TODO impl delete history from dao
    }
}
