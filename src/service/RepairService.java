package service;

import dao.RepairDao;
import domain.Complaint;
import domain.Fixlog;
import domain.Page;

import java.util.List;
import java.util.Map;

public class RepairService {
    RepairDao repairDao =new RepairDao();
    public void add(Complaint complaint) {
        repairDao.add(complaint);
    }

    public Page findAll(int current) {
        int total=repairDao.repairCount();
        Page page=new Page(current,total);
        List<Map<String,Object>> list=repairDao.find(page.getStartIndex(),page.getPageSize());
        page.setList(list);
        System.out.println(page);
        return page;
    }

    public Complaint findByID(int id) {
        return repairDao.findByID(id);
    }

    public Page findFreeWorker() {
        Page page=new Page(0,0);
        List<Map<String,Object>> list = repairDao.findFreeWorker();
        page.setList(list);
        System.out.println(page);
        return page;
    }

    public void creatFixLog(Fixlog fixlog) {
        repairDao.creatFixLog(fixlog);
    }

    public void updateRepair(int id) {
        repairDao.updateRepair(id);
    }

    public void updateWorkerState(String username) {
        repairDao.upadteWorkerState(username);
    }
}
