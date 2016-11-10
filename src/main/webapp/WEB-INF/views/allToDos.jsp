<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<html>
<head>
	<title>ToDo</title>
	<style>
        tr:first-child{
            font-weight: bold;
            background-color: gray;
        }
        .done_false{
            background-color: yellow;
        }
        .done_true{
            background-color: limegreen;
        }
	</style>
</head>
<body>
	<h2>Список дел</h2>
	<p>
		<a href="<c:url value='/list' />">Все дела</a>
		&nbsp
		<a href="<c:url value='/list/done' />">Выполненные</a>
		&nbsp
		<a href="<c:url value='/list/actual' />">Не выполненные</a>
	</p>
	<table border="1">
		<tr>
			<td>Id</td>
			<td>Создано</td>
			<td>Описание</td>
			<td>Крайний срок</td>
			<td>Редактировать</td>
            <td>Удалить</td>
            <td>Закончено</td>
		</tr>
		<c:forEach items="${toDos}" var="toDo">
			<tr class="done_${toDo.done}">
				<td>${toDo.id}</td>
				<td>
                    <fmt:formatDate value="${toDo.created}" pattern="dd/MM/yyyy HH:mm:ss"/>
                </td>
                <td>${fn:replace(toDo.text, newLineChar, "<br>")}</td>
				<td>
                    <fmt:formatDate value="${toDo.deadLine}" pattern="dd/MM/yyyy"/>
                </td>
                <td>
					<c:if test="${!toDo.done}">
						<a href="<c:url value='/todo/edit/${toDo.id}' />">Редактировать</a>
					</c:if>
				</td>
                <td><a href="<c:url value='/todo/delete/${toDo.id}' />">Удалить</a></td>
                <td>
					<c:choose>
						<c:when test="${!toDo.done}">
							<a href="<c:url value='/todo/done/${toDo.id}' />">Закончить</a>
						</c:when>
						<c:otherwise>
							Дело сделано
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/todo/new' />">Добавить новое дело</a>
</body>
</html>