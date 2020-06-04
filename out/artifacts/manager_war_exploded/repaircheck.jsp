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
        <form action="/RepairServlet?id=${complaint.id}" method="post">
            <div class="formtitle"><span>报修信息</span></div>
            <input type="hidden" name="action" value="checksubmit"/>
            <ul class="forminfo">
                <li><label>产品名</label><input name="productname" id="productname" readonly="readonly"
                                             value="${complaint.productname}" type="text" class="dfinput"/><i></i></li>
                <li><label>报修原因</label><input name="reason" type="text" readonly="readonly" value="${complaint.reason} "
                                              class="dfinput"/><i></i></li>
                <li><label>客户姓名</label><input name="customername" readonly="readonly" type="text"
                                              value="${complaint.customername}" class="dfinput"/><i></i></li>
                <li><label>联系电话</label><input name="phone" readonly="readonly" type="number" value="${complaint.phone}"
                                              class="dfinput"/><i></i></li>
                <li><label>客户地址</label><textarea name="address" readonly="readonly" cols="" rows=""
                                                 class="textinput">${complaint.address}</textarea></li>
                <c:choose>
                    <c:when test="${complaint.state==0}">
                        <li><label>维修状态</label>
                            <input name="state" type="text" readonly="readonly" value="未维修" class="dfinput"/>

                            <i></i>
                        </li>
                        <li><label>空闲工人</label>
                            <select id="role" name="workerUsername" class="loginrole">
                               <c:forEach items="${worker.list}" var="workers">
                                   <option value="${workers.username}">${workers.name}</option>
                               </c:forEach>
                            </select>
                        </li>
                        <li><label>&nbsp;</label><input type="submit" class="btn" value="确认派遣"/></li>
                    </c:when>
                    <c:when test="${complaint.state==2}">
                        <li><label>维修状态</label>
                            <input name="state" type="text" readonly="readonly" value="未修好" class="dfinput"/>

                            <i></i>
                        </li>
                        <li><label>空闲工人</label>
                            <select id="role1" name="workerUsername" class="loginrole">
                                <c:forEach items="${worker.list}" var="workers">
                                    <option value="${workers.username}">${workers.name}</option>
                                </c:forEach>
                            </select>
                        </li>
                        <li><label>&nbsp;</label><input type="submit" class="btn" value="确认派遣"/></li>
                    </c:when>
                    <c:when test="${complaint.state==3}">
                        <li><label>维修状态</label>
                            <input name="state" type="text" readonly="readonly" value="维修中" class="dfinput"/>

                            <i></i>
                        </li>
                        <li><label>&nbsp;</label><input type="submit" class="btn" value="确认"/></li>
                    </c:when>
                    <c:otherwise>
                        <li><label>维修状态</label>
                            <input name="reason" type="text" readonly="readonly" value="已修好" class="dfinput"/>
                            <input type="hidden" name="state" value="1"/>
                            <i></i>
                        </li>
                        <li><label>&nbsp;</label><input type="submit" class="btn" value="确认"/></li>
                    </c:otherwise>
                </c:choose>
            </ul>

        </form>
    </div>
</div>
</html>
</html>
