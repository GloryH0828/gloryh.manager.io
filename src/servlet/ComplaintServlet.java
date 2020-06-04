package servlet;

import dao.RepairDao;
import domain.Complaint;
import domain.Page;
import service.ComplaintService;
import service.RepairService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ComplaintServlet",urlPatterns = "/ComplaintServlet")
public class ComplaintServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ComplaintService complaintService=new ComplaintService();
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
                AddComplaint(request,response);
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
        if(action.equals("findAll")){
            try {
                findAll(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(action.equals("complaintcheck")){
            try {
                check(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(action+"已执行");
    }

    private void checkSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String state=request.getParameter("state");
        int id=Integer.valueOf(request.getParameter("id")).intValue();
        System.out.println("id"+id+"state:"+state);
        if(state.equals("no")){
            complaintService.updateState(id);
        }
        findAll(request, response);
    }

    private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.valueOf(request.getParameter("id"));
        Complaint complaint=complaintService.findByID(id);
        request.setAttribute("complaint",complaint);
        request.getRequestDispatcher("/complaintcheck.jsp").forward(request, response);
    }

    private void AddComplaint(HttpServletRequest request, HttpServletResponse response)throws Exception {
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
            complaint.setType(1);
            complaintService.add(complaint);
            request.setAttribute("msg","投诉信息录入成功");
            request.getRequestDispatcher("/consult.jsp").forward(request, response);
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
        Page page=complaintService.findAll(current);
        System.out.println("tets"+page+"");
        request.setAttribute("page",page);
        request.getRequestDispatcher("/complaintlist.jsp").forward(request, response);
    }
}
