<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>relation</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />

</head>
<body>
			<section id="c1c2">
     		      <c:import url="/WEB-INF/vue_traiter2.jsp"></c:import>
     		</section>
     		
     		<section id="cr">
     		<p class="context">Relation</p>
     		<form action="<c:url value="/rcf"/>" method="post">
     	     <p class="titre">Context Relation </p>
     	
       <table>
       		<c:forEach items="${nomsOA }" var="na" varStatus="va">
       			<tr>
       				<c:forEach items="${nomsOA2 }" var="no" varStatus="vo">
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
                              		<input type="number" name="nombreR" value="0" min="0" max="1" size="2">
                              		</c:if>
                              	</c:when>
                              </c:choose>
       					</td>
       				</c:forEach>
       			</tr>
       		</c:forEach>
       </table>
      				<input type="submit" value="creation du fichier">
        </form>
         </section>
        
        
</body>
</html>