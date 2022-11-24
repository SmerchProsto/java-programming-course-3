<%@ page import="java.sql.Connection" %>
<%@ page import="com.webapp.javaee.ConnectionDB" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.webapp.javaee.Employeer" %>
<%@ page import="com.webapp.javaee.SearchFilter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Punk
  Date: 19.11.2022
  Time: 3:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Работа с БД</title>
</head>
<body>
<%
    ConnectionDB db = new ConnectionDB();
    ArrayList<Employeer> employeers = new ArrayList<>();
    ArrayList<Employeer> searchedEmployeers = new ArrayList<>();
    employeers.addAll(db.select());
    String filterValue = request.getParameter("listInformation");
    boolean isFilterValueAllInformation = true;
    String userCondition = request.getParameter("searchData");
    SearchFilter search = new SearchFilter();
    searchedEmployeers.addAll(search.getResult(employeers, filterValue, userCondition));
    request.setAttribute("list", searchedEmployeers);

    try {
        isFilterValueAllInformation = filterValue.equalsIgnoreCase("information");
        if (isFilterValueAllInformation && !(userCondition.equalsIgnoreCase(""))) {
            String site = new String("error-jsp");
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        }
    } catch (Exception ex) {

    }


%>
<form action="employees.jsp">
    <select name="listInformation" id="">
        <option name="option" value="number">По номеру</option>
        <option name="option" value="name">По имени</option>
        <option name="option" value="information">Вся информация</option>
    </select>
    <input name="searchData" type="text" placeholder="Введите данные">
    <input type="submit" value="Вывести">
</form>

<ul>
    <c:forEach var="listElement" items="${list}">
        <li><c:out value="
        id:${listElement.getId()} ${listElement.getName()} ${listElement.getPhone()}
        ${listElement.getManager()} ${listElement.getSalary()}
        ${listElement.getNumberDepartament()} ${listElement.getCityDepartament()}
        ${listElement.getRunk()}
"
        /></li>
    </c:forEach>
</ul>

</body>
</html>
