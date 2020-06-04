package servlet;

import domain.Complaint;
import domain.Fixlog;
import domain.Page;
import javafx.concurrent.Worker;
import service.FixLogService;
import service.StorageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FixLogServlet" , urlPatterns = "/FixLogServlet")
public class FixLogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FixLogService fixLogService =new FixLogService();
    private StorageService storageService= new StorageService();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        System.out.println(action);
        if(action.equals("findAll")){
            findAll(request,response);
        }
        if(action.equals("fixLogCheck")){
            fixLogCheck(request,response);
        }
        if(action.equals("updateFixLog")){
            updateFixLog(request,response);
        }
        if(action.equals("checkSubmit")){
            checkSubmit(request,response);
        }
        if(action.equals("update")){
            update(request,response);
        }
        System.out.println(action+"已执行");

    }

    private void fixLogCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.valueOf(request.getParameter("id"));
        Fixlog fixlog=storageService.findFixLogByID(id);
        request.setAttribute("fixlog",fixlog);
        request.getRequestDispatcher("/fixLogCheck.jsp").forward(request, response);
    }

    private void checkSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.valueOf(request.getParameter("id"));
        Fixlog fixlog=storageService.findFixLogByID(id);
        String reason =request.getParameter("reason");
        String bonus1 =request.getParameter("bonus");
        String cost1 =request.getParameter("cost");
        String check =request.getParameter("check");
        int cost =0;
        int bonus=0;
        int state =3;
        Complaint complaint =new Complaint();
        if(reason!=null || !reason.equals("")){
            fixlog.setReason(reason);
        }
        if(cost1!=null || !cost1.equals("")){
            cost=Integer.valueOf(cost1);
        }
        if(bonus1!=null || !bonus1.equals("")){
            bonus=Integer.valueOf(bonus1);
        }
        if(check.equals("no")){
            state=2;
            fixlog.setState(0);
        }else {
            state=1;
            fixlog.setState(1);
        }
        fixlog.setCost(cost);
        fixlog.setBonus(bonus);
        complaint.setState(state);
        complaint.setPhone(fixlog.getCustomerphone());
        complaint.setProductname(fixlog.getProductname());
        fixLogService.updateFixLog(fixlog);
        fixLogService.updateComplaint(complaint);
        fixLogService.updateWorker(fixlog.getUsername());
        findAll(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page fixlog =fixLogService.findAll("ing");
        request.setAttribute("fixlog",fixlog);
        request.getRequestDispatcher("/fixLoging.jsp").forward(request, response);
    }

    private void updateFixLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.valueOf(request.getParameter("id"));
        Fixlog fixlog=storageService.findFixLogByID(id);
        request.setAttribute("fixlog",fixlog);
        request.getRequestDispatcher("/updateFixLog.jsp").forward(request, response);
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page fixlog =fixLogService.findAll("ed");
        request.setAttribute("fixlog",fixlog);
        request.getRequestDispatcher("/fixLoged.jsp").forward(request, response);
    }
}
