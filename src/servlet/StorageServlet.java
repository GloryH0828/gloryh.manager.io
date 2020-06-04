package servlet;

import domain.Fixlog;
import domain.Page;
import domain.Putin;
import domain.Stock;
import service.ComplaintService;
import service.StorageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "StorageServlet" ,urlPatterns = "/StorageServlet")
public class StorageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StorageService storageService=new StorageService();
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
        if(action.equals("findWorker")){
            findWorker(request,response);
        }
        if(action.equals("insideNew")){
            insideNew(request,response);
        }
        if(action.equals("outside")){
            outside(request,response);
        }
        if(action.equals("outsideSubmit")){
            outsideSubmit(request,response);
        }
        if(action.equals("workerReturn")){
            workerReturn(request,response);
        }
        if(action.equals("return")){
            returnn(request,response);
        }
        if(action.equals("returnSubmit")){
            returnSubmit(request,response);
        }
        System.out.println(action+"已执行");

    }

    private void returnSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.valueOf(request.getParameter("id"));
        Fixlog fixlog=storageService.findFixLogByID2(id);
        String oldcount1   = request.getParameter("oldCount");
        String newCount1  = request.getParameter("newCount");

        int oldCount=0;
        int newCount=0;
        if(oldcount1.equals("") || oldcount1==null){
            oldCount=0;
        }else {
            oldCount=Integer.valueOf(oldcount1);
        }
        if(newCount1.equals("") || newCount1==null){
            newCount=0;
        }else {
            newCount=Integer.valueOf(oldcount1);
        }
        fixlog.setOldparts(fixlog.getPartsname());
        fixlog.setOpcount(oldCount);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        Date date=new Date();
        fixlog.setReturntime(date);
        fixlog.setPartscount(newCount);
        storageService.returnFixLog(fixlog);
        storageService.returnStock(fixlog);
        workerReturn(request, response);

    }

    private void returnn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.valueOf(request.getParameter("id"));
        Fixlog fixlog=storageService.findFixLogByID2(id);
        request.setAttribute("fixlog",fixlog);
        request.getRequestDispatcher("/return.jsp").forward(request, response);


    }

    private void workerReturn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page fixlog=storageService.findWorker("ing");
        request.setAttribute("fixlog",fixlog);
        request.getRequestDispatcher("/inside.jsp").forward(request, response);
    }

    private void insideNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name  = request.getParameter("name");
        String count1  = request.getParameter("count");
        String price1  = request.getParameter("price");
        String use  = request.getParameter("use");
        session.setAttribute("Tips", "");
        boolean flag=true;

        if(name == null || name.equals("")){
            flag=false;
            session.setAttribute("nameError", "名称不得为空！");
        }else {
            session.setAttribute("nameError", "");
            session.setAttribute("name", name);
        }
        if(count1 == null || count1.equals("")){
            flag=false;
            session.setAttribute("countError", "数量不得为空或0！");
        }else {
            session.setAttribute("countError", "");
            session.setAttribute("count", count1);
        }
        if(price1 == null || price1.equals("")){
            flag=false;
            session.setAttribute("priceError", "价格不得为空或0！");
        }else {
            session.setAttribute("priceError", "");
            session.setAttribute("price", price1);
        }
        if(use == null || use.equals("")){
            flag=false;
            session.setAttribute("useError", "用途不得为空！");
        }else {
            session.setAttribute("useError", "");
            session.setAttribute("use", use);
        }
        if(flag==false){
            request.getRequestDispatcher("/inside.jsp").forward(request, response);
        }else{
            int type= Integer.valueOf(request.getParameter("type"));
            int count= Integer.valueOf(count1);
            int price= Integer.valueOf(price1);
            Putin putin=new Putin();
            putin.setCount(count);
            putin.setName(name);
            putin.setUse(use);
            putin.setState(0);
            putin.setType(type);
            putin.setPrice(price);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            Date date=new Date();
            putin.setTime(date);
            storageService.insideNew(putin);
            int exist = storageService.isExist(putin);
            System.out.println("exist="+exist);
            if(exist==0){
                storageService.addNew(putin);
            }else {
                storageService.putin(putin);
            }
            session.setAttribute("Tips", "入库完成！");
            request.getRequestDispatcher("/inside.jsp").forward(request, response);
        }


    }

    private void outsideSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.valueOf(request.getParameter("ID"));
        int count= Integer.valueOf(request.getParameter("partsCount"));
        String toolName  = request.getParameter("toolName");
        String partName  = request.getParameter("partName");
        int totalCount=storageService.findPartsCount(partName);
        System.out.println(",pc="+count+",pn="+partName+",tn="+toolName+",id="+id);
        if(totalCount<=count){
            request.setAttribute("countError","对不起，超出库存数量");
            outside(request, response);
        }else if(count<=0) {
            request.setAttribute("countError","出库数量不得小于1");
            outside(request, response);
        }else {
                Fixlog fixlog =new Fixlog();
                fixlog.setId(id);
                fixlog.setPartscount(count);
                fixlog.setPartsname(partName);
                fixlog.setToolname(toolName);
                fixlog.setToolcount(1);
            System.out.println("fixlog="+fixlog);
                storageService.updateFixLog(fixlog);
                storageService.updateStock(fixlog);
                findWorker(request, response);
        }
    }

    private void outside(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username  = request.getParameter("username");
        System.out.println(username);
        int id= Integer.valueOf(request.getParameter("ID"));
        Fixlog fixlog=storageService.findFixLogByID2(id);
        Page Tools=storageService.find("tools1");
        Page Parts=storageService.find("parts1");
        request.setAttribute("tools",Tools);
        request.setAttribute("parts",Parts);
        request.setAttribute("fixlog",fixlog);
        request.getRequestDispatcher("/outside.jsp").forward(request, response);
    }

    private void findWorker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag="ed";
        Page worker=storageService.findWorker(flag);
        request.setAttribute("worker",worker);
        request.getRequestDispatcher("/workerlist.jsp").forward(request, response);
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page  tools1=storageService.find("tools1");
        Page  tools2=storageService.find("tools2");
        Page  tools3=storageService.find("tools3");
        Page  parts1=storageService.find("parts1");
        Page  parts2=storageService.find("parts2");
        Page  parts3=storageService.find("parts3");
        request.setAttribute("tools1",tools1);
        request.setAttribute("tools2",tools2);
        request.setAttribute("tools3",tools3);
        request.setAttribute("parts1",parts1);
        request.setAttribute("parts2",parts2);
        request.setAttribute("parts3",parts3);
        request.getRequestDispatcher("/storage.jsp").forward(request, response);
    }
}
