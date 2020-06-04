package servlet;

import dao.LoginDao;
import dao.RepairDao;
import domain.Complaint;
import domain.Fixlog;
import domain.Page;
import domain.User;
import javafx.concurrent.Worker;
import service.LoginService;
import service.RepairService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "RepairServlet",urlPatterns = "/RepairServlet")
public class RepairServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RepairService repairService =new RepairService();
    private RepairDao repairDao=new RepairDao();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        System.out.println(action);
        if(action.equals("add")){
            try {
                AddRepair(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if(action.equals("checksubmit")){
            try {
                checkSubmit(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(action.equals("check")){
            check(request,response);
        }
        if(action.equals("findAll")){
            try {
                findAll(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(action+"已执行");
    }

    private void checkSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String state=request.getParameter("state");
        String reason=request.getParameter("reason");
        int id=Integer.valueOf(request.getParameter("id")).intValue();
        System.out.println("id"+id+"state:"+state);
        String customername=request.getParameter("customername");
        String phone=request.getParameter("phone");
        String address=request.getParameter("address");
        String productname=request.getParameter("productname");
        if(state.equals("未维修")||state.equals("未修好")){
            String username=request.getParameter("workerUsername");
            System.out.println("workerid:"+username+"-"+customername+"-"+phone+"-"+address+"--"+productname);
            LoginDao loginDao=new LoginDao();
            User worker=loginDao.loginState(username,"worker");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            Date date=new Date();
            Fixlog fixlog=new Fixlog();
            fixlog.setBorrowtime(date);
            fixlog.setUsername(worker.getUsername());
            fixlog.setWorkername(worker.getName());
            fixlog.setReason(reason);
            fixlog.setCustomername(customername);
            fixlog.setToolname("无");
            fixlog.setToolcount(0);
            fixlog.setPartsname("无");
            fixlog.setPartscount(0);
            fixlog.setCustomerphone(phone);
            fixlog.setCustomeraddress(address);
            fixlog.setProductname(productname);
            fixlog.setState(2);
            repairService.creatFixLog(fixlog);
            repairService.updateRepair(id);
            repairService.updateWorkerState(worker.getUsername());
            findAll(request, response);
            /*




            维修工人已经派出去维修，还未去仓库取工具



             */
        }else{
            findAll(request,response);
        }
    }

    private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.valueOf(request.getParameter("id")).intValue();
        Complaint complaint=repairService.findByID(id);
        Page page=repairService.findFreeWorker();
        request.setAttribute("worker",page);
        request.setAttribute("complaint",complaint);
        request.getRequestDispatcher("/repaircheck.jsp").forward(request, response);
    }

    private void AddRepair(HttpServletRequest request, HttpServletResponse response)throws Exception {
        String productname= request.getParameter("productname");
        String reason= request.getParameter("reason");
        String customername= request.getParameter("customername");
        String phone= request.getParameter("phone");
        String address= request.getParameter("address");
        System.out.println(productname+"-"+reason+"-"+customername+"-"+phone+"-"+address);
        boolean flag=true;
        HttpSession session = request.getSession();
        if(productname==null||productname.equals("")){
            session.setAttribute("pdnameerror", "产品名不得为空！");
            flag=false;
        }else{
            session.setAttribute("pdnameerror", "");
        }
        if(reason==null||reason.equals("")){
            session.setAttribute("reasonerror", "维修原因不得为空！");
            flag=false;
        }else{
            session.setAttribute("reasonerror", "");
        }
        if(customername==null||customername.equals("")){
            session.setAttribute("cmnameerror", "客户名不得为空！");
            flag=false;
        }else{
            session.setAttribute("cmnameerror", "");
        }
        if(phone==null||phone.equals("")){
            session.setAttribute("phoneerror", "电话不得为空！");
            flag=false;
        }else{
            session.setAttribute("phoneerror", "");
        }
        if(flag==false){
            request.getRequestDispatcher("/repair.jsp").forward(request, response);
        }else {
            Complaint complaint=new Complaint();
            complaint.setAddress(address);
            complaint.setCustomername(customername);
            complaint.setPhone(phone);
            complaint.setReason(reason);
            complaint.setProductname(productname);
            complaint.setState(0);
            complaint.setType(0);
            repairService.add(complaint);
            findAll(request,response);
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response)throws Exception {
        String currentPage=request.getParameter("currentPage");
        //有当前页，CurrentPage，无，默认为1
        int current;
        try {
            current=Integer.parseInt(currentPage);
        } catch (Exception e) {
            // TODO: handle exception
            current=1;
        }
        Page page=repairService.findAll(current);
        System.out.println("tets"+page+"");
        request.setAttribute("page",page);
        request.getRequestDispatcher("/repairlist.jsp").forward(request, response);
    }
}
