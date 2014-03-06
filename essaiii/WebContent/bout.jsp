<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>vue contexte</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />

</head>
<body>
       <table>
              <%-- parcourt de la liste des attributs pour creer les linges --%>
              <c:forEach begin="0" end="${ lignes }" var="ligneV" varStatus="boucleL" >
              	<tr>
              		<c:forEach begin="0" end="${ colonnes}" var="colonneV" varStatus="boucleC">
              			<td>
              				<c:choose>
              					<c:when test="${ligneV==0 }">
              						<c:if test="${colonneV==0 }">
              							<c:out value=" " />
              						</c:if>
              						<c:if test="${colonneV>0 }">
              							<c:out value="A${colonneV }" />
              						</c:if>
              					</c:when>
              					<c:when test="${ligneV>0 }">
              						<c:if test="${colonneV==0 }">
              							<c:out value="O${ligneV }" />
              						</c:if>
              						<c:if test="${colonneV>0 }">
              							<c:out value="0" />
              						</c:if>
              					</c:when>
              				</c:choose>
              			</td>
              		</c:forEach>
              	</tr>
                   
              </c:forEach>
              
       </table>
       
    <p><a href="<c:url value="/menu2.jsp"/>">Créer un nouveau context</a></p>
</body>
</html>