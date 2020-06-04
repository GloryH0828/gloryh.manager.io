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
                <li><a href="#tab1" class="selected">工具信息列表</a></li>
                <li><a href="#tab2">工具待补货列表</a></li>
                <li><a href="#tab3">配件信息列表</a></li>
                <li><a href="#tab4">配件待补货列表</a></li>
                <li><a href="#tab5">坏旧配件列表</a></li>
                <li><a href="#tab6">坏旧工具列表</a></li>
            </ul>
        </div>

        <div id="tab1" class="tabson">


            <table class="tablelist">
                <thead>

                <tr>
                    <th><input name="" type="checkbox" value="" checked="checked"/></th>
                    <th>编号<i class="sort"><img src="images/px.gif"/></i></th>
                    <th>名称</th>
                    <th>剩余数量</th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${tools1.list}" var="tools1">
                    <tr>
                        <td><input name="" type="checkbox" value="${tools1.id}"/></td>
                        <td>${tools1.id}</td>
                        <td>${tools1.name}</td>
                        <td>${tools1.count}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>


        </div>
        <div id="tab2" class="tabson">


            <table class="tablelist">
                <thead>

                <tr>
                    <th><input name="" type="checkbox" value="" checked="checked"/></th>
                    <th>编号<i class="sort"><img src="images/px.gif"/></i></th>
                    <th>名称</th>
                    <th>数量</th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${tools2.list}" var="tools2">
                    <tr>
                        <td><input name="" type="checkbox" value="${tools2.id}"/></td>
                        <td>${tools2.id}</td>
                        <td>${tools2.name}</td>
                        <td>${tools2.count}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>


        </div>
        <div id="tab3" class="tabson">


            <table class="tablelist">
                <thead>

                <tr>
                    <th><input name="" type="checkbox" value="" checked="checked"/></th>
                    <th>编号<i class="sort"><img src="images/px.gif"/></i></th>
                    <th>名称</th>
                    <th>数量</th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${parts1.list}" var="parts1">
                    <tr>
                        <td><input name="" type="checkbox" value="${parts1.id}"/></td>
                        <td>${parts1.id}</td>
                        <td>${parts1.name}</td>
                        <td>${parts1.count}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>


        </div>
        <div id="tab4" class="tabson">


            <table class="tablelist">
                <thead>

                <tr>
                    <th><input name="" type="checkbox" value="" checked="checked"/></th>
                    <th>编号<i class="sort"><img src="images/px.gif"/></i></th>
                    <th>名称</th>
                    <th>数量</th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${parts2.list}" var="parts2">
                    <tr>
                        <td><input name="" type="checkbox" value="${parts2.id}"/></td>
                        <td>${parts2.id}</td>
                        <td>${parts2.name}</td>
                        <td>${parts2.count}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>


        </div>
        <div id="tab5" class="tabson">


            <table class="tablelist">
                <thead>

                <tr>
                    <th><input name="" type="checkbox" value="" checked="checked"/></th>
                    <th>编号<i class="sort"><img src="images/px.gif"/></i></th>
                    <th>名称</th>
                    <th>数量</th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${parts3.list}" var="parts3">
                    <tr>
                        <td><input name="" type="checkbox" value="${parts3.id}"/></td>
                        <td>${parts3.id}</td>
                        <td>${parts3.name}</td>
                        <td>${parts3.count}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>


        </div>
        <div id="tab6" class="tabson">


            <table class="tablelist">
                <thead>

                <tr>
                    <th><input name="" type="checkbox" value="" checked="checked"/></th>
                    <th>编号<i class="sort"><img src="images/px.gif"/></i></th>
                    <th>名称</th>
                    <th>数量</th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${tools3.list}" var="tools3">
                    <tr>
                        <td><input name="" type="checkbox" value="${tools3.id}"/></td>
                        <td>${tools3.id}</td>
                        <td>${tools3.name}</td>
                        <td>${tools3.count}</td>
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
