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
					<p class="context">Creation de context</p>
			<p><a href="<c:url value="/inc/menu.jsp"/>">Changer le context</a></p>
			
			      <p class="infos">veuillez renommer vos objets et attributs et indiquez les relations entre eux</p>
			      
			<form action="<c:url value="/traiter_vue"/>" method="post">
         <table>
              <%-- parcourt de la liste des attributs pour creer les linges --%>
              <c:forEach begin="0" end="${ lignes }" var="ligneV" varStatus="boucleL" >
              	<tr>
              		<c:forEach begin="0" end="${ colonnes}" var="colonneV" varStatus="boucleC">
              			<td>
              				<c:choose>
              					<c:when test="${ligneV==0 }">
              						<c:if test="${colonneV==0 }">
              						<input type="text" name="case" id="l${ligneV }c${colonneV}"  size="1"/>
              						</c:if>
              						<c:if test="${colonneV>0 }">
              						    <input type="text" value="A${colonneV }" id="l${ligneV }c${colonneV}" name="caseA" size="1"/>
              							
              						</c:if>
              					</c:when>
              					<c:when test="${ligneV>0 }">
              						<c:if test="${colonneV==0 }">
              							<input type="text" value="O${ligneV }" name="caseO" id="l${ligneV }c${colonneV}" size="1"/>
              							
              						</c:if>
              						<c:if test="${colonneV>0 }">
              						<%-- 	<input type="checkbox"  name="" id="l${ligneV }c${colonneV}" size="2"/>		--%>
              						<input type="number" name="nombre" value="0" min="0" max="1" size="1">
              							
              						</c:if>
              					</c:when>
              				</c:choose>
              			</td>
              		</c:forEach>
              	</tr>
                   
              </c:forEach>
              
       </table>
       			<input type="submit" value=valider>
       </form>
       			
</body>
</html>