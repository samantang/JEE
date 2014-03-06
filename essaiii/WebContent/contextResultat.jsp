<%@ page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Context Resultat</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
</head>
<body>
	
	<c:import url="/WEB-INF/vue_context2.jsp"></c:import>
	
	<form action="">
		<table>
			<c:forEach begin="0" end="${ colonnes}" var="colonneV" varStatus="boucleC">
				<tr>
					<c:forEach begin="0" end="${ colonnesa}" var="colonneVV" varStatus="boucleCC">
					<td>
						<c:choose>
							<c:when test="${colonneV==0 }">
								<c:if test="${colonnesVV==0 }">
									<c:out value=" ">
									</c:out>
								</c:if>
								<c:if test="${colonneVV > 0}">
									<c:out value="O${colonneVV }_C2"></c:out>
								</c:if>
							</c:when>
							<c:when test="${colonneVV == 0 }">
								<c:if test="${colonneV==0 }">
									<c:out value=" "></c:out>
								</c:if>
								<c:if test="${colonneV > 0 }">
									<c:out value="O${colonneV }_C1"></c:out>
								</c:if>
							</c:when>
						</c:choose>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>

</html>