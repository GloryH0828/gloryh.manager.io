package service;

import dao.ComplaintDao;
import domain.Complaint;
import domain.Page;

import java.util.List;
import java.util.Map;

public class ComplaintService {
    ComplaintDao complaintDao =new ComplaintDao();

    public void add(Complaint complaint) {
        complaintDao.add(complaint);
    }

    public Page findAll(int current) {
        int total=complaintDao.complaintCount();
        Page page=new Page(current,total);
        List<Map<String,Object>> list=complaintDao.find(page.getStartIndex(),page.getPageSize());
        page.setList(list);
        System.out.println(page);
        return page;
    }

    public Complaint findByID(int id) {
        return complaintDao.findByID(id);
    }

    public void updateState(int id) {
        complaintDao.updateState(id);
    }
}
