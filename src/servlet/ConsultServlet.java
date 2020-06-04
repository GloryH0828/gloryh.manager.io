package servlet;

import dao.RepairDao;
import domain.Complaint;
import domain.Consult;
import domain.Page;
import service.ConsultService;
import service.RepairService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ConsultServlet",urlPatterns = "/ConsultServlet")
public class ConsultServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ConsultService consultService=new ConsultService();
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
        if(action.equals("findAll")){
            try {
                findAll(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(action+"已执行");
    }

    private void AddRepair(HttpServletRequest request, HttpServletResponse response)throws Exception {
        String question= request.getParameter("question");
        String customername= request.getParameter("customername");
        String phone= request.getParameter("phone");
        String answer= request.getParameter("answer");
        System.out.println(question+"-"+customername+"-"+phone+"-"+answer);
        boolean flag=true;
        HttpSession session = request.getSession();
        if(question==null||question.equals("")){
            session.setAttribute("questionerror", "问题不得为空！");
            flag=false;
        }else{
            session.setAttribute("questionerror", "");
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
            request.getRequestDispatcher("/consult.jsp").forward(request, response);
        }else {
            Consult consult=new Consult();
            consult.setAnswer(answer);
            consult.setCustomername(customername);
            consult.setPhone(phone);
            consult.setQuestion(question);
            consultService.add(consult);
            request.setAttribute("msg","咨询信息录入成功");
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
        Page page=consultService.findAll(current);
        System.out.println("tets"+page+"");
        request.setAttribute("page",page);
        request.getRequestDispatcher("/consultlist.jsp").forward(request, response);
    }
}
