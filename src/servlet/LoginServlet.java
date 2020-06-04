package servlet;

import dao.LoginDao;
import domain.User;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet" ,urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginService loginService=new LoginService();
    private LoginDao loginDao=new LoginDao();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        System.out.println(action);
        if(action.equals("login")){
            try {
                Login(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(action+"已执行");
        }
    }

    private void Login(HttpServletRequest request, HttpServletResponse response)throws Exception {
        String username=request.getParameter("username");
        System.out.println(username+"工号");
        String password=request.getParameter("password");
        String role=request.getParameter("role");
        System.out.println(role+"身份");
        if(username==null || username.equals("")){
            HttpSession session = request.getSession();
            session.setAttribute("username", "");
            session.setAttribute("unerror", "用户名不得为空");
            session.setAttribute("pwderror", "");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        if(password==null || password.equals("")){
            HttpSession session = request.getSession();
            if (username==null || username.equals("")){
                session.setAttribute("username", "");
                session.setAttribute("unerror", "用户名不得为空");
                session.setAttribute("pwderror", "");
            }else{
                session.setAttribute("username", username);
                session.setAttribute("unerror", "");
                session.setAttribute("pwderror", "密码不得为空");
            }

            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        User user1= loginService.state(user,role);
        int state=0;
        int id=0;
        if(user1.getId()!=0){
            id=user1.getId();
        }
        if(id!=0){
            if(user1.getPassword().equals(user.getPassword())){
                state=1;
            }else {
                state=-1;
            }
        }

        if(state==1){


            HttpSession session = request.getSession();
            session.setAttribute("name", user1.getName());
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            System.out.println(user1.getName()+"    "+username);
            request.getRequestDispatcher("/main.jsp").forward(request, response);
        }
        if(state==0){
            HttpSession session = request.getSession();
            session.setAttribute("username", "");
            session.setAttribute("unerror", "用户不存在，请重新输入");
            session.setAttribute("pwderror", "");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        if(state==-1){
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("unerror", "");
            session.setAttribute("pwderror", "密码输入有误，请重新输入");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

}
