<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>vue traiter2</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />

</head>
<body>
			<section id="context1et2">
     		<c:import url="/WEB-INF/vue_traiter.jsp"></c:import>
     		<p class="titre">Context 2 </p>
			<form action="/relation" method="post">
				<table>
					<c:forEach items="${formate2 }" var="va2" >
						<tr>
							<c:forEach items="${va2 }" var="v2">
								<td><c:out value="${v2.valeur}" /></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
<%--        <table>
       		<c:forEach items="${nomsOA2 }" var="na" varStatus="va">
       			<tr>
       				<c:forEach items="${nomsAA2 }" var="no" varStatus="vo">
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
       </form>
       </section> 
            <p><a href="<c:url value="/relation.jsp"/>">Créer le context relation</a></p> 
</body>
</html>