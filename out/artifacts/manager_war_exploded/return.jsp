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
        <form action="/StorageServlet?id=${fixlog.id}&username=${fixlog.username}" method="post">
            <div class="formtitle"><span>报修信息</span></div>
            <input type="hidden" name="action" value="returnSubmit"/>
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
                <li><label>使用工具</label>
                    <input name="toolname"  readonly="readonly"
                           value="${fixlog.toolname}" type="text" class="dfinput"/>
                    <i></i></li>
                <li><label>维修配件</label>
                    <input name="partsname"  readonly="readonly"
                    value="${fixlog.partsname}-共${fixlog.partscount}个" type="text" class="dfinput"/>
                    <i></i></li>
                <li><label>旧件数量</label>
                    <input name="oldCount" type="number" max="${fixlog.partscount}"  min="0" class="dfinput"/>
                    <i>${countError}</i></li>
                <li><label>剩余新件</label>
                    <input name="newCount" type="number" max="${fixlog.partscount}"  min="0" class="dfinput"/>
                    <i>${countError}</i></li>
                <li><label></label><input type="submit" class="btn" value="归还/入库"/></li>
            </ul>

        </form>
    </div>
</div>
</html>
</html>
