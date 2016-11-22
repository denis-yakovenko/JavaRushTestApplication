<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://javascriptools.sourceforge.net/js/JavaScriptUtil.js"></script>
<script src="http://javascriptools.sourceforge.net/js/Parsers.js"></script>
<script src="http://javascriptools.sourceforge.net/js/original/InputMask.js"></script>
<script language="JavaScript">
    function setup() {
        var dateMask1 = new DateMask("dd/MM/yyyy", "deadLine");
        dateMask1.validationMessage = "Invalid date. Expected format: dd/mm/yyyy";
    }
</script>
<html>
<head>
    <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
	<title>ToDo</title>
	<style>
		.error {
			color: #ff0000;
		}
	</style>
</head>
<body onLoad="setup()">
	<h2>
		<c:choose>
			<c:when test="${edit}">
				Редактирование дела
			</c:when>
			<c:otherwise>
				Добавление дела
			</c:otherwise>
		</c:choose>
	</h2>
 	<form:form method="POST" modelAttribute="toDo">
		<form:input type="hidden" path="id" id="id"/>
		<table>
			<tr>
				<td><label for="text">Описание: </label> </td>
				<td><form:textarea cols="50" rows="10" path="text" id="text"/></td>
				<td><form:errors path="text" cssClass="error"/></td>
		    </tr>
			<tr>
				<td><label for="deadLine">Крайний срок: </label> </td>
				<td><form:input path="deadLine" id="deadLine" name="deadLine"/></td>
				<td><form:errors path="deadLine" cssClass="error"/></td>
			</tr>
				<c:if test="${edit}">
					<tr>
						<td><label for="done">Закончено: </label> </td>
						<td><form:checkbox path="done" id="done"/></td>
						<td><form:errors path="done" cssClass="error"/></td>
					</tr>
				</c:if>
			<tr>
				<td colspan="3">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Сохранить"/>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Добавить"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</form:form>
	<br/>
	<br/>
	<a href="<c:url value='/list' />">Вернуться к списку дел</a>
</body>
</html>