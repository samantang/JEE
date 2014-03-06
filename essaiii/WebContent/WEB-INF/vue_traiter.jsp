<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>vue traiter</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />

</head>
<body>
		<p class="context">Creation de context</p>
		<section id="context1et2">
		<p><a href="<c:url value="/inc/menu.jsp"/>">Changer le context</a></p>
		<P class="titre">Context 1</P>
			<form action="<c:url value="/creerContext2"/>" method="get">
				<table>
					<c:forEach items="${formate }" var="va" >
						<tr>
							<c:forEach items="${va }" var="v">
								<td><c:out value="${v.valeur}" /></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
 <%--     <table>
       		<c:forEach items="${nomsOA }" var="na" varStatus="va">
       			<tr>
       				<c:forEach items="${nomsAA }" var="no" varStatus="vo">
       					<td>
                              <c:choose>
                              	<c:when test="${va.index==0 }">
                              		<c:if test="${vo.index==0 }">
                              			<c:out value=" "></c:out>
                              		</c:if>
                              		<c:if test="${vo.index>0 }">
                              			<c:out value="${no }"></c:out>
                              		</c:if>
                              	</c:when>
                              	<c:when test="${va.index>0 }">
                              		<c:if test="${vo.index==0 }">
                              			<c:out value="${na }"></c:out>
                              		</c:if>
                              		<c:if test="${vo.index>0 }">
                              	 		<input type="number" name="nombre" value="0" size="2">		
                              		</c:if>
                              	</c:when>
                              </c:choose>
       					</td>
       				</c:forEach>
       			</tr>
       		</c:forEach>
       </table>			--%>  
       				<p><input type="submit" value="Nouveau context"></p>
       </form>
       </section>
</body>
</html>