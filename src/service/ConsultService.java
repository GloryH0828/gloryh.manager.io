package service;

import dao.ConsultDao;
import domain.Consult;
import domain.Page;

import java.util.List;
import java.util.Map;

public class ConsultService {
    ConsultDao consultDao=new ConsultDao();
    public void add(Consult consult) {
        consultDao.add(consult);
    }

    public Page findAll(int current) {
        int total=consultDao.repairCount();
        Page page=new Page(current,total);
        List<Map<String,Object>> list=consultDao.find(page.getStartIndex(),page.getPageSize());
        page.setList(list);
        System.out.println(page);
        return page;
    }
}
