package service;

import dao.StorageDao;
import domain.Fixlog;
import domain.Page;
import domain.Putin;

import java.util.List;
import java.util.Map;

public class StorageService {
    StorageDao storageDao=new StorageDao();
    public Page find(String type) {
        Page page=new Page(0,0);
        List<Map<String,Object>> list=storageDao.find(type);
        page.setList(list);
        System.out.println(page);
        return page;
    }

    public Page findWorker(String flag) {
        Page page=new Page(0,0);
        List<Map<String,Object>> list=storageDao.findWorker(flag);
        page.setList(list);
        System.out.println(page);
        return page;
    }

    public Fixlog findFixLogByUsername(String username) {
        return storageDao.findFixLogByUsername(username);
    }

    public void updateFixLog(Fixlog fixlog) {
        storageDao.updateFixLog(fixlog);
    }

    public int findPartsCount(String partName) {
        return storageDao.fingPartsCount(partName);
    }

    public void updateStock(Fixlog fixlog) {
        storageDao.updateStock(fixlog);
    }

    public void insideNew(Putin putin) {
        storageDao.insideNew(putin);
    }

    public int isExist(Putin putin) {
        return storageDao.isExist(putin);
    }

    public void addNew(Putin putin) {
        storageDao.addNew(putin);
    }

    public void putin(Putin putin) {
        storageDao.putin(putin);
    }


    public Fixlog findFixLogByID(int id) {
        return storageDao.findFixLogByID(id);
    }

    public void returnFixLog(Fixlog fixlog) {
        storageDao.returnFixLog(fixlog);
    }

    public void returnStock(Fixlog fixlog) {
        storageDao.returnStock(fixlog);
    }

    public Fixlog findFixLogByID2(int id) {
        return storageDao.findFixLogByID2(id);
    }
}
