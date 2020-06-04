<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>


<div class="place">

    <div class="formbody">
        <form action="/StorageServlet?ID=${fixlog.id}&username=${fixlog.username}" method="post">
            <div class="formtitle"><span>报修信息</span></div>
            <input type="hidden" name="action" value="outsideSubmit"/>
            <ul class="forminfo">
                <li><label>产品名</label><input name="productname" id="productname" readonly="readonly"
                                             value="${fixlog.productname}" type="text" class="dfinput"/><i></i></li>
                <li><label>报修原因</label><input name="reason" type="text" readonly="readonly" value="${fixlog.reason} "
                                              class="dfinput"/><i></i></li>
                <li><label>客户姓名</label><input name="customername" readonly="readonly" type="text"
                                              value="${fixlog.customername}" class="dfinput"/><i></i></li>
                <li><label>联系电话</label><input name="phone" readonly="readonly" type="number" value="${fixlog.customerphone}"
                                              class="dfinput"/><i></i></li>
                <li><label>客户地址</label><textarea name="address" readonly="readonly" cols="" rows=""
                                                 class="textinput">${fixlog.customeraddress}</textarea></li>
                <li><label>工具列表</label>
                    <select id="role1" name="toolName" class="loginrole">
                        <c:forEach items="${tools.list}" var="tool">
                            <option value="${tool.name}">${tool.name}</option>
                        </c:forEach>
                    </select>
                    <i></i></li>
                <li><label>配件列表</label>
                    <select id="role" name="partName" class="loginrole">
                        <c:forEach items="${parts.list}" var="part">
                            <option value="${part.name}">${part.name}-剩余${part.count}个</option>
                        </c:forEach>
                    </select>
                    <i></i></li>
                <li><label>出库数量</label>
                    <input name="partsCount" type="number"  min="0" class="dfinput"/>
                    <i>${countError}</i></li>
                <li><label>&nbsp;</label><input type="submit" class="btn" value="确认"/></li>
            </ul>

        </form>
    </div>
</div>
</html>
</html>
