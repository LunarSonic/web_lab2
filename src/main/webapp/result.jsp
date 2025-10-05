<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="web.lunarsonic.models.Result" %>
<%@ page import="web.lunarsonic.models.ResultHistory" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Результаты проверки</title>
    <link rel="icon" type="image/png" href="images/duck.PNG">
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Old+Standard+TT:ital,wght@0,400;0,700;1,400&family=Playfair+Display:ital,wght@0,400..900;1,400..900&family=Viaoda+Libre&display=swap" rel="stylesheet">
</head>
<body>
<div id="main_container">
    <div id="background_image"></div>
    <div id="blur"></div>
    <header id="lab">
        <h1>Веб-программирование. Лабораторная работа №2. Вариант 63164</h1>
        <div id="container_with_image">
            <h2>Абдуллаева София Улугбековна. P3208</h2>
            <a href="https://github.com/LunarSonic/web_lab2" target="_blank">
                <img id="githubIcon" src="images/github.svg" alt="">
            </a>
        </div>
    </header>
    <% Result newResult = (Result) request.getAttribute("newResult");
    String historyJson = (String) request.getAttribute("historyJson");
    if (historyJson == null) historyJson = "[]";
    ResultHistory historyBean = (ResultHistory) request.getServletContext().getAttribute("resultHistory");
    List<Result> history = (historyBean != null) ? historyBean.getHistory() : null;
    String currentR = (newResult != null) ? String.format(Locale.US, "%.1f", newResult.r()) : null; %>
    <div id="canvas_container" data-current-r = "<%= currentR%>" data-history-json = '<%= historyJson%>'>
        <div id="left_column">
            <div id="current_result_details">
                <h3 id="result_header">Детали текущей проверки</h3>
                <% if (newResult != null) { %>
                <table>
                    <tr>
                        <th>X</th>
                        <th>Y</th>
                        <th>R</th>
                        <th>Попали ли вы?</th>
                        <th>Текущая дата</th>
                        <th>Время работы скрипта (нс)</th>
                    </tr>
                    <tr>
                        <td><%= newResult.x() %></td>
                        <td><%= newResult.y() %></td>
                        <td><%= newResult.r() %></td>
                        <td><%= newResult.hit() ? "Да" : "Нет" %></td>
                        <td><%= newResult.serverTime() %></td>
                        <td><%= newResult.scriptTime() %></td>
                    </tr>
                </table>
                <% } %>
                <div id="navigation">
                    <a href="index.jsp">Отправить новый запрос</a>
                </div>
            </div>
            <h3 id="history_header">История проверок</h3>
            <div id="table_container">
                <table>
                    <thead>
                    <tr>
                        <th>X</th>
                        <th>Y</th>
                        <th>R</th>
                        <th>Попали ли вы?</th>
                        <th>Текущая дата</th>
                        <th>Время работы скрипта (нс)</th>
                    </tr>
                    </thead>
                    <tbody id="body_for_table">
                    <%
                        if (history != null) {
                            for (Result res : history) {
                    %>
                    <tr>
                        <td><%= res.x() %></td>
                        <td><%= res.y() %></td>
                        <td><%= res.r() %></td>
                        <td><%= res.hit() ? "Да" : "Нет" %></td>
                        <td><%= res.serverTime() %></td>
                        <td><%= res.scriptTime() %></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
        <div id="main_canvas">
            <canvas id="coordinate_plane" width="400" height="400"></canvas>
        </div>
    </div>
</body>
<script type="module" src="js/result.js"></script>
</html>