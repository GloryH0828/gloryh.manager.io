<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/2
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<head>
    <title>Title</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>


<div class="place">

    <div class="formbody">
        <form action="/ConsultServlet" method="post">
            <div class="formtitle"><span>咨询信息</span></div>
            <input type="hidden" name="action" value="add"/>
            <ul class="forminfo">
                <li><label>问题</label><input name="question" id="productname"  type="text" class="dfinput" /><i>${questionerror}</i></li>
                <li><label>客户姓名</label><input name="customername" type="text" class="dfinput" /><i>${cmnameerror}</i></li>
                <li><label>联系电话</label><input name="phone" type="number" class="dfinput" /><i>${phoneerror}</i></li>
                <li><label>回答</label><textarea name="answer" cols="" rows=""  class="textinput"></textarea></li>
                <li><label>&nbsp;</label><input type="submit" class="btn" value="提交信息"/>${msg}</li>
            </ul>

        </form>
    </div>
</div>
</html>
</html>
