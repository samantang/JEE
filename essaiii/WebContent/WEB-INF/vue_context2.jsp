<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>vue contexte2</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />

</head>
<body>
		      <c:import url="/WEB-INF/vue_traiter.jsp"></c:import>
		      
       <form action="<c:url value="/traiter_vue2"/>" method="post">
      
      <p class="infos">veuillez renommer vos objets et attributs</p>
      
        <table>

              <c:forEach begin="0" end="${ lignesa }" var="ligneV" varStatus="boucleL" >
              	<tr>
              		<c:forEach begin="0" end="${ colonnesa}" var="colonneV" varStatus="boucleC">
              			<td>
              				<c:choose>
              					<c:when test="${ligneV==0 }">
              						<c:if test="${colonneV==0 }">
              							<input type="text" value=" " size="1"/>
              							
              						</c:if>
              						<c:if test="${colonneV>0 }">
              							
              							<input type="text" value="A${colonneV }" size="1" name="caseA2" />
              						</c:if>
              					</c:when>
              					<c:when test="${ligneV>0 }">
              						<c:if test="${colonneV==0 }">
              							
              							<input type="text" value="O${ligneV }" size="1" name="caseO2" />
              						</c:if>
              						<c:if test="${colonneV>0 }">
              							
              						<%-- 	<input type="checkbox"  name="case" id="l${ligneV }c${colonneV}" size="2"/>		--%>
              							<input type="number" value="0" name="nombre2" min="0" max="1" size="1" />
              						</c:if>
              					</c:when>
              				</c:choose>
              			</td>
              		</c:forEach>
              	</tr>
                   
              </c:forEach>
              
       </table>
      			<input type="submit" value="valider">
      	</form>		
</body>
</html>