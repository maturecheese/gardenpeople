<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="generator" content="Bootply" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/allpages.css">
<title>Home</title>
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
								<li class=""><a class="" href="FindGardener">Find a gardener</a></li>
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
	<!--end navbar-->
	<!--  
	<div class="btn-group" data-toggle="buttons">
		<label class="btn btn-primary"> <input type="radio"
			name="options" id="option1">Find a Gardener
		</label> <label class="btn btn-primary"> <input type="radio"
			name="options" id="option2"><a href="Register"></a>
		</label>

	</div>
-->
	<header>
	<div class="container">
	 <br><br><br><br><br><br>
		<h1 style="font-size: 7vw;">Welcome To Garden People</h1>
	 <br><br><br><br><br><br>
	</div>
	</header>


	<section id="findGardener" class="">
	<br>
	<br>
	<br>

	<div class="row"
		style="margin-left: 2vw; margin-right: 2vw; text-align: center">
		<div class="col-md-6">
			<div class="jumbotron">
				<h1>Are You A Gardener?</h1>
				<br> <br> <br> <a class="btn btn-info">Click Here</a>
			</div>
		</div>

		<div class="col-md-6">
			<div class="jumbotron">
				<h1>Looking For A Gardener</h1>
				<br> <br> <br> <a class="btn btn-info"
					href="findGardener.html">Click Here</a>
			</div>
		</div>
	</div>

	</section>


	<section id="whatWeDo" class="bg-light-gray">
	<div class="container" style="text-align: center">
		<br> <br> <br>

		<h1>
			<strong>OUR SERVICES</strong>
		</h1>
		<br> <br> <br> <br>

		<div class="row">
			<div class="col-sm-4">
				<span class="fa-stack fa-3x"> <i
					class="fa fa-circle fa-stack-2x text-primary"></i> <i
					class="fa fa-book fa-stack-1x fa-inverse"></i>
				</span>
				<h4>Service A</h4>
				<a href="" class="btn btn-warning">Read More</a>
			</div>

			<div class="col-sm-4">
				<span class="fa-stack fa-3x"> <i
					class="fa fa-circle fa-stack-2x text-primary"></i> <i
					class="fa fa-book fa-stack-1x fa-inverse"></i>
				</span>
				<h4>Service B</h4>
				<a href="" class="btn btn-warning">Read More</a>
			</div>

			<div class="col-sm-4">
				<span class="fa-stack fa-3x"> <i
					class="fa fa-circle fa-stack-2x text-primary"></i> <i
					class="fa fa-book fa-stack-1x fa-inverse"></i>
				</span>
				<h4>Service B</h4>
				<a href="" class="btn btn-warning">Read More</a>
			</div>

		</div>
	</div>
	<br>
	<br>
	<br>
	</section>

	<section id="review">
	<div class="container">
		<h1 style="text-align: center">
			<strong>HAPPY CUSTOMERS AND TRUSTED GARDENERS</strong>
		</h1>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>
	</div>
	</section>


	<div style="margin-bottom: 20vw">
		<br>
	</div>

	<!-- jquery-->
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var loginError = $("body").attr("data-loginError")
			//alert(valuePassedFromJSP);
			if(loginError.length > 0){
				$("#loginDropdown").click();
				//alert(loginError);

			}
		});
		//alert(myObject);
	</script>
	<!-- javascript-->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>


	<!-- Scrolling Nav JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>
	<script
			src="${pageContext.request.contextPath}/resources/js/checklogin.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/scrolling-nav.js"></script>


</body>

</html>