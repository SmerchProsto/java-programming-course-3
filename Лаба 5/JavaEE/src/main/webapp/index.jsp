<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.webapp.javaee.Calculator" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP calculator</title>
</head>
<body>
<%
    String solutionNum = "Нет ответа";
    String num1 = request.getParameter("num1");
    String num2 = request.getParameter("num2");
    String operator = request.getParameter("operator");
    Calculator calc = new Calculator();
    solutionNum = calc.solve(num1, num2, operator);

    try {
        if (operator.equalsIgnoreCase("/") && num2.equalsIgnoreCase("0")) {
            String site = new String("error-jsp");
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        } else if (num1.equalsIgnoreCase("") && num2.equalsIgnoreCase("")) {
            String site = new String("error-employees-jsp");
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        }
    } catch (Exception ex) {

    }
%>
<form action="index.jsp" method="get">
    <label for="first-number">Первое число: <input name="num1" id="first-number" type="number" value="<%= num1 %>"></label>
    <label for="second-number">Второе число: <input name="num2" id="second-number" type="number" value="<%= num2 %>"></label>
    <input name="operator" type="submit" value="+">
    <input name="operator" type="submit" value="-">
    <input name="operator" type="submit" value="*">
    <input name="operator" type="submit" value="/">
</form>



<p><%= "" + solutionNum %></p>
<p><a href="employees-jsp">второе задание</a></p>
</body>
</html>