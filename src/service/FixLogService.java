package service;

import dao.FixLogDao;
import domain.Complaint;
import domain.Fixlog;
import domain.Page;

import java.util.List;
import java.util.Map;

public class FixLogService {
    FixLogDao fixLogDao =new FixLogDao();
    public Page findAll(String ed) {
        Page page =new Page(0,0);
        List<Map<String,Object>> list=fixLogDao.findAll(ed);
        page.setList(list);
        System.out.println(page);
        return page;
    }

    public void updateFixLog(Fixlog fixlog) {
        fixLogDao.updateFixLog(fixlog);
    }

    public void updateComplaint(Complaint complaint) {
        fixLogDao.updateComplaint(complaint);
    }

    public void updateWorker(String username) {
        fixLogDao.updateWorker(username);
    }
}
