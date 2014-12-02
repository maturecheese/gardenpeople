<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/allpages.css">
<title>Insert title here</title>
</head>
<body>
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

			<div class="collapse navbar-collapse navHeaderCollapse">
				<!--navbar-nav gives styling and navbar-right aligns it to the right-->
				<ul class="nav navbar-nav navbar-right">
					<c:choose>


						<c:when test="${empty sessionScope.user}">
							<li class="dropdown"><a class="dropdown-toggle" href=""
													data-toggle="dropdown">Sign In <strong class="caret"></strong></a>

								<div class="dropdown-menu"
									 style="padding: 10px; min-width: 240px;">


									<form action="login" method="post" role="form"
										  class="form-horizontal">

										<input class="form-control" id="inputUsername" name="inputUsername"
											   placeholder="Username" type="text" style="margin-bottom: .5em">

										<input class="form-control" id="inputPassword" name="inputPassword"
											   placeholder="Password" type="password"
											   style="margin-bottom: .5em">

										<div class="checkbox">
											<label><input type="checkbox"> Remember me</label>
										</div>

										<input class="btn btn-primary"
											   style="margin-top: .75em; width: 100%; height: 32px; font-size: 13px;"
											   type="submit" name="commit" value="Sign In">
									</form>


								</div>
							</li>
							<li class=""><a class="" href="Register">Register</a></li>
						</c:when>
						<c:otherwise>
							<li class=""><a class="" href="#">${sessionScope.user.username}</a></li>
							<li class="dropdown"><a class="dropdown-toggle" href=""
													data-toggle="dropdown">My Account <strong
									class="caret"></strong></a>

								<ul class="dropdown-menu">
									<li><a href="#">Edit Profile</a></li>

								</ul>
							</li>
							<li class=""><a class="" href="logout">Logout</a></li>
						</c:otherwise>
					</c:choose>


					<!-- <li class=""><a class="page-scroll" href="#whatWeDo">Our Services</a></li>
                    <li class=""><a class="page-scroll" href="#review">Customer Reviews</a></li> -->
				</ul>
			</div>

		</div>
		<!--end of nav bar-->
	</div>
</div>
<div class="confirmation">

	<c:choose>
		<c:when test="${user.gardener}">
			<h3>Congratulations ${user.username}, your account is
				successfully created. Thank you for choosing to advertise your services with us.
				 Please log in and navigate to your account to
				build your profile</h3>
    	</c:when>
		<c:otherwise>
    		<h3>Congratulations ${user.username}, your account is
				successfully created. Please log in</h3>
  		</c:otherwise>
	</c:choose>

	<a href="home">Home</a>
</div>




</body>
</html>