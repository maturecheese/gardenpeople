<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/allpages.css">
<title>Insert title here</title>
</head>
<body>

	
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

											<input class="form-control" id="inputUsername"
												name="inputUsername" placeholder="Username" type="text"
												style="margin-bottom: .5em"> <input
												class="form-control" id="inputPassword" name="inputPassword"
												placeholder="Password" type="password"
												style="margin-bottom: .5em">

											<div class="checkbox">
												<label><input type="checkbox"> Remember me</label>
											</div>

											<input class="btn btn-primary"
												style="margin-top: .75em; width: 100%; height: 32px; font-size: 13px;"
												type="submit" name="commit" value="Sign In">
										</form>


									</div></li>
								<li class=""><a class="" href="Register">Register</a></li>
							</c:when>
							<c:otherwise>
								<li class=""><a class="" href="#">${sessionScope.user.username}</a></li>
								<li class="dropdown"><a class="dropdown-toggle" href=""
									data-toggle="dropdown">My Account <strong class="caret"></strong></a>

									<ul class="dropdown-menu">
										<li><a href="details">my details</a></li>
										<c:if test="${sessionScope.user.gardener}">
											<li><a href="profile">my public profile</a></li>
										</c:if>
									</ul></li>
								<li class=""><a class="" href="logout">Logout</a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</div>

			</div>
			<!--end of nav bar-->
		</div>
	</div>

	<div class="register">
	<h1>My Details
	</h1>
		<div class="error">
			<c:if test="${fn:length(errors) > 0}">
				<ul>
					<c:forEach items="${errors}" var="error">
						<li>${error}</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
		<div class="confirmation">
		<c:if test="${!empty confirmation}">
		<ul>
			<li>${confirmation}
			</li>
			</ul>
		</c:if> 
		</div>


		<form role="form" action="details" method="post">
			
			<div class="form-group">
				<label for="email">Email Address:</label> <input type="email"
					class="form-control" id="email" name="email"
					placeholder="Enter email" value="${sessionScope.user.email}">
			</div>
			<div class="form-group">
				<label for="password1">New Password:</label> <input type="password"
					class="form-control" id="password1" name="password1"
					placeholder="Password">
			</div>
			<div class="form-group">
				<label for="password2">Confirm New Password:</label> <input type="password"
					class="form-control" id="password2" name="password2"
					placeholder="Password">
			</div>

			<div class="form-group">
				<label for="firstName">First Name:</label> <input type="text"
					class="form-control" id="firstName" name="firstName"
					placeholder="Enter first name" value="${sessionScope.user.firstName}">
			</div>
			<div class="form-group">
				<label for="lastName">Last Name:</label> <input type="text"
					class="form-control" id="lastName" name="lastName"
					placeholder="Enter last name" value="${sessionScope.user.lastName}">
			</div>
			<div class="form-group">
				<label for="house">House Number or Name:</label> <input type="text"
					class="form-control" id="house" name="house"
					placeholder="Enter house number \ name" value="${sessionScope.user.houseNumberName}">
			</div>
			<div class="form-group">
				<label for="street">Street Name:</label> <input type="text"
					class="form-control" id="street" name="street"
					placeholder="Enter street name" value="${sessionScope.user.street}">
			</div>
			<div class="form-group">
				<label for="postCode">Post Code:</label> <input type="text"
					class="form-control" id="postCode" name="postCode"
					placeholder="Enter post code" value="${sessionScope.user.postcode}">
			</div>
			<div class="form-group">
				<label for="county">Select County:</label> <select class="form-control"
					id="county" name="county" value="">
					
					<c:forEach items="${counties}" var="county">
						<c:choose>
							<c:when test="${county.id != sessionScope.user.county}">
								<option value="${county.id}">${county.name}</option>
							</c:when>
							<c:otherwise>
								<option selected value="${county.id}">${county.name}</option>
							</c:otherwise>
							
						
						</c:choose>
					</c:forEach>
					
				</select>
			</div>


			<button type="submit" class="btn btn-default">Save All Changes</button>
		</form>
		
	</div>
	<!-- jquery-->
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
<!-- javascript-->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>


<!-- Scrolling Nav JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/scrolling-nav.js"></script>

	

</body>
</html>