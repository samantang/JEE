<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu2 context</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
</head>
<body>
			
		       					       <c:import url="/WEB-INF/vue_traiter.jsp"></c:import>
		       					
		
     
       <form  method="post" action="<c:url value="/creerContext2"/>" >
       		
           <table>
               <tr>
                   <th>Nombre d'objets</th>
                   <td>
                       <input type="text" name="objeta" id="objet" value="" size="3"/>
                   </td>
               </tr>
               <tr>
                   <th>Nombre d'attributs</th>
                   <td>
                        <input type="text" name="attributa" id="attribut" value="" size="3"  />
                   </td>
               </tr>
           </table>
             
                <input type="submit" value=creer>
       </form>
</body>
</html>