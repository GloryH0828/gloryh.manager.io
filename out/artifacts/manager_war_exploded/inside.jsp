<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/3
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="css/select.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="js/select-ui.min.js"></script>
    <script type="text/javascript" src="editor/kindeditor.js"></script>

    <script type="text/javascript">
        KE.show({
            id: 'content7',
            cssPath: './index.css'
        });
    </script>

    <script type="text/javascript">
        $(document).ready(function (e) {
            $(".select1").uedSelect({
                width: 345
            });
            $(".select2").uedSelect({
                width: 167
            });
            $(".select3").uedSelect({
                width: 100
            });
        });
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">系统设置</a></li>
    </ul>
</div>

<div class="formbody">


    <div id="usual1" class="usual">

        <div class="itab">
            <ul>
                <li><a href="#tab1" class="selected">仓库补货</a></li>
                <li><a href="#tab2">工人归还</a></li>
            </ul>
        </div>

        <div id="tab1" class="tabson">
            <form action="/StorageServlet?action=insideNew" method="post">
                <ul class="forminfo">
                    <li><label>名称<b>*</b></label><input name="name" type="text" value="${name}" class="dfinput" placeholder="请输入补货物品的名称"
                                                        style="width:518px;"/>${nameError}</li>
                    <li><label>数量<b>*</b></label><input name="count" type="number" value="${count}" class="dfinput"
                                                        placeholder="请输入补货物品的数量" style="width:518px;"/>${countError}
                    </li>
                    <li><label>类型<b>*</b></label>

                        <div class="vocation">
                            <select class="select1" name="type">
                                <option value="0">工具</option>
                                <option value="1">产品配件</option>
                            </select>
                        </div>
                    </li>
                    <li><label>用途<b>*</b></label><input name="use" value="${use}" type="text" class="dfinput"
                                                        style="width:518px;"/>${useError}</li>
                    <li><label>价格<b>*</b></label><input name="price" type="number" value="${price}" class="dfinput"
                                                        style="width:518px;"/>${priceError}</li>
                    <li><label>注意<b>*</b></label><font class="dfinput">请注意检验每件物品是否正常使用</font></li>

                    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="入库"/>${Tips}</li>
                </ul>
            </form>
        </div>
        <div id="tab2" class="tabson">
            <table class="tablelist">
                <thead>
                <tr>
                    <th><input name="" type="checkbox" value="" checked="checked"/></th>
                    <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
                    <th>工人姓名</th>
                    <th>工人工号</th>
                    <th>借走工具</th>
                    <th>借走零件</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${fixlog.list}" var="fixlogs">
                <tr>
                    <td><input name="" type="checkbox" value="${fixlogs.id}" /></td>
                    <td>${fixlogs.id}</td>
                    <td>${fixlogs.workername}</td>
                    <td>${fixlogs.username}</td>
                    <td>${fixlogs.toolname}</td>
                    <td>${fixlogs.partsname}-共${fixlogs.partscount}个</td>
                    <td><a href="${pageContext.request.contextPath }/StorageServlet?action=return&id=${fixlogs.id}" class="tablelink">归还工具并记录旧件信息</a>
                </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>


    </div>

    <script type="text/javascript">
        $("#usual1 ul").idTabs();
    </script>

    <script type="text/javascript">
        $('.tablelist tbody tr:odd').addClass('odd');
    </script>


</div>

</body>
</html>
