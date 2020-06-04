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
        <form action="/FixLogServlet?id=${fixlog.id}&username=${fixlog.username}" method="post">
            <div class="formtitle"><span>报修信息</span></div>
            <input type="hidden" name="action" value="findAll"/>
            <ul class="forminfo">
                <li><label>产品名</label><input id="productname" readonly="readonly"
                                             value="${fixlog.productname}" type="text" class="dfinput"/><i></i></li>
                <li><label>报修原因</label><input type="text" readonly="readonly" value="${fixlog.reason} "
                                              class="dfinput"/><i></i></li>
                <li><label>客户姓名</label><input readonly="readonly" type="text"
                                              value="${fixlog.customername}" class="dfinput"/><i></i></li>
                <li><label>联系电话</label><input readonly="readonly" type="number" value="${fixlog.customerphone}"
                                              class="dfinput"/><i></i></li>
                <li><label>客户地址</label><textarea readonly="readonly" cols="" rows=""
                                                 class="textinput">${fixlog.customeraddress}</textarea></li>
                <li><label>维修人</label>
                    <input type="text" readonly="readonly" value="${fixlog.workername}" class="dfinput"/>
                    <i></i></li>
                <li><label>工号</label>
                    <input type="text" readonly="readonly" value="${fixlog.username}" class="dfinput"/>
                    <i></i></li>
                <li><label>工作时长</label>
                    <input type="text" readonly="readonly" value="${fixlog.borrowtime}至${fixlog.returntime}"
                           class="dfinput"/>
                    <i></i></li>
                <li><label>使用工具</label>
                    <input type="text" readonly="readonly" value="${fixlog.toolname}-${fixlog.toolcount}个"
                           class="dfinput"/>
                    <i></i></li>
                <li><label>带走配件</label>
                    <input type="text" readonly="readonly" value="${fixlog.partsname}-${fixlog.partscount}个"
                           class="dfinput"/>
                    <i></i></li>
                <li><label>入库旧件</label>
                    <input type="text" readonly="readonly" value="${fixlog.oldparts}-${fixlog.opcount}个"
                           class="dfinput"/>
                    <i></i></li>
                <li><label>工人奖金</label><input name="bonus" type="text" value="${fixlog.bonus}" readonly="readonly"
                                              class="dfinput"/><i></i></li>
                <li><label>维修费用</label><input name="cost"  value="${fixlog.cost}" readonly="readonly" type="number"
                                              class="dfinput"/><i></i></li>
                <c:choose>
                    <c:when test="${fixlog.state==1}">
                        <li><label>维修结果</label><input   value="已修好" readonly="readonly" type="text"
                                                      class="dfinput"/><i></i></li>
                    </c:when>
                    <c:otherwise>
                        <li><label>维修结果</label><input  value="未修好" readonly="readonly" type="text"
                                                      class="dfinput"/><i></i></li>
                    </c:otherwise>
                </c:choose>
                <li><label>&nbsp;</label><input type="submit" class="btn" value="确认"/></li>
            </ul>

        </form>
    </div>
</div>
</html>
</html>
