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
        <form action="/ComplaintServlet" method="post">
            <div class="formtitle"><span>投诉信息</span></div>
            <input type="hidden" name="action" value="add"/>
            <ul class="forminfo">
                <li><label>产品名</label><input name="productname" id="productname" placeholder="请输入产品名称" type="text" class="dfinput" /><i>${pdnameerror}</i></li>
                <li><label>投诉原因</label><input name="reason" type="text" class="dfinput" /><i>${reasonerror}</i></li>
                <li><label>客户姓名</label><input name="customername" type="text" class="dfinput" /><i>${cmnameerror}</i></li>
                <li><label>联系电话</label><input name="phone" type="number" class="dfinput" /><i>${phoneerror}</i></li>
                <li><label>客户地址</label><textarea name="address" cols="" rows=""  class="textinput"></textarea></li>
                <li><label>&nbsp;</label><input type="submit" class="btn" value="提交信息"/>${msg}</li>
            </ul>

        </form>
    </div>
</div>
</html>
</html>
