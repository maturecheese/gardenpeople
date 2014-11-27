<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/allpages.css">
<title>Insert title here</title>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/map.js"></script>
		<script>
			google.maps.event.addDomListener(window, 'load', initialize);
		</script>

</head>
<body>
<body>
	<!--navbar -->
<div style="margin-bottom:9vw">
<div class="navbar navbar-inverse navbar-fixed-top"> <!--navbar-static-top will make it disappear if you scroll horizontally -->
			<div class="container">
				<!--navbar-brand is used for titles - it has larger text -->
				
				<!--<a href = '/index.php' class="navbar-brand" >Gardener Website</a>-->
				
				<!-- button 
				this button will appear if screen collapses (smaller screen) 
				-->
				<button class="navbar-toggle" data-toggle="collapse"
					data-target=".navHeaderCollapse">
					<span class="glyphicon glyphicon-th-list"></span>
				</button>
		
				<div class="collapse navbar-collapse navHeaderCollapse">
					<!--navbar-nav gives styling and navbar-right aligns it to the right-->				
					

						<ul class="nav navbar-nav ">
						  <li role=""><a href="Home">Home</a></li>
						  <li role=""><a href="FindGardener">Find a gardener</a></li>
						  <li role=""><a href="listCompany.html">List Your Company</a></li>
						  <li role=""><a href="">Help and Advice</a></li>
					</ul>

					
			</div>
			
			</div>
			<!--end of nav bar-->
		</div>	
	</div>
<!--end navbar-->





		<h1>Results</h1>
		
		 <table border="1">
            <th>First Name</th>
            <th>Last Name</th>
            <c:forEach items="${allGardeners}" var="gard">
                <tr>
                    <td>${gard.firstName}</td>
                    <td>${gard.lastName}</td>
                </tr>
            </c:forEach>
        </table>  



<div id="map-canvas" style="margin-right:10vw; margin-left:10vw">




<div style="margin-bottom:30vw">
			<br>
		</div>
		<!-- jquery-->
		<script src="resources/js/jquery-1.11.0.js"></script>
		<!-- javascript-->
		<script src="resources/js/bootstrap.js"></script>
		
		
		<!-- Scrolling Nav JavaScript -->
    <script src="resources/js/jquery.easing.min.js"></script>
    <script src="resources/js/scrolling-nav.js"></script>
		
		
		

</body>

</html>