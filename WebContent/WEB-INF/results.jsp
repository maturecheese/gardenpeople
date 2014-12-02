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
<title>Search Results</title>



</head>

<body data-loginerror="<c:if test="${!empty requestScope.loginError}">loginError</c:if>">


	<!--navbar -->
	<div style="">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<!--navbar-static-top will make it disappear if you scroll horizontally -->
			<div class="container">
				<!--navbar-brand is used for titles - it has larger text -->

				<a href="" class="navbar-brand">Gardener Website</a>

				<!-- button
            this button will appear if screen collapses (smaller screen)
            -->
				<button class="navbar-toggle" data-toggle="collapse"
					data-target=".navHeaderCollapse">
					<span class="glyphicon glyphicon-th-list"></span>
				</button>

				<div class="collapse navbar-collapse navHeaderCollapse">`
					<!--navbar-nav gives styling and navbar-right aligns it to the right-->
					<ul class="nav navbar-nav navbar-right">
						<c:choose>


							<c:when test="${empty sessionScope.user}">

								<li class="dropdown"><a class="dropdown-toggle" href=""
									data-toggle="dropdown" id="loginDropdown">Sign In <strong class="caret"></strong></a>

									<div class="dropdown-menu"
										style="padding: 10px; min-width: 240px;">


										<form action="login" method="post" role="form"
											class="form-horizontal">

											<input class="form-control" id="inputUsername"
												name="inputUsername" placeholder="Username" type="text"
												style="margin-bottom: .5em"> <input
												class="form-control" id="inputPassword" name="inputPassword"
												placeholder="Password" type="password"
												style="margin-bottom: .5em">
											<c:if test="${!empty requestScope.loginError}">
												<div class="error">${requestScope.loginError}</div>

											</c:if>

											<div class="checkbox">
												<label><input type="checkbox"> Remember me</label>
											</div>


											<input class="btn btn-primary"
												style="margin-top: .75em; width: 100%; height: 32px; font-size: 13px;"
												type="submit" name="commit" value="Sign In">
										</form>


									</div></li>
								<li class=""><a class="" href="Register">Register</a></li>
								<li class=""><a class="" href="findGardener">Find a gardener</a></li>
							</c:when>
							<c:otherwise>
								<li class=""><a class="" href="#">${sessionScope.user.username}</a></li>
								<li class="dropdown"><a class="dropdown-toggle" href=""
									data-toggle="dropdown">My Account <strong class="caret"></strong></a>

									<ul class="dropdown-menu">
										<li><a href="details">my details</a></li>
										<c:if test="${sessionScope.user.gardener}">
											<li><a href="profile">my public profile</a></li>
											<li><a href="photos">my photos</a></li>
										</c:if>
									</ul></li>
								<li class=""><a class="" href="logout">Logout</a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</div>

			</div>
			
		</div>
	</div>
	<!--end navbar-->



<div style = "margin-bottom:100px"></div>
<div class = "container">
		<h1>Results</h1>
		
		 <table border="1">
            <th>UserName</th>
           <!--  <th>Last Name</th>--> 
            <c:forEach items="${allGardeners}" var="gard">
                <tr>
                    <td>${gard.username}</td>
                   <%--   <td>${gard.lastName}</td>--%> 
                </tr>
            </c:forEach>
        </table>  







</div>
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