<%@ page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu resultat</title>
</head>
<body>
    <form  method="post" action="<c:url value="/resultat"/>" >
    	<c:import url="/WEB-INF/vue_context2.jsp"></c:import>
    	<p>je suis le menu resultat</p>
    	<input type="submit" value="resultat">
    	
    </form>
</body>
</html>