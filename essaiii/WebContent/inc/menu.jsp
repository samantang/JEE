<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu</title>
   <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />	
</head>
<body>
		<p class="context">Creation de contexts</p>
		<p><strong>Indiquez le nombre d'objets et d'attributs à creer</strong></p>
     
       <form  method="post" action="<c:url value="/creerContext"/>" >
           <table>
               <tr>
                   <th>Nombre d'objets</th>	
                   <td>
                       <input type="text" name="objet" id="objet" value="" size="3"/>
                   </td>
               </tr>
               <tr>
                   <th>Nombre d'attributs</th>
                   <td>
                        <input type="text" name="attribut" id="attribut" value="" size="3"  />
                   </td>
               </tr>
           </table>
             
                <input type="submit" value=creer>
       </form>
</body>
</html>