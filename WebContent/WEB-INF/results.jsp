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

<c:forEach items="${allGardeners}" var="gard">
                
                <div class="panel panel-default">
			  <div class="panel-heading">
			  <span class="pull-right clickable"><a>View Full Details</a></span>
			  <div class = "row">
			  	<div class = "col-md-2">
			  		 <em>Photo here</em>
			  	</div>
			  	<div class = "col-md-2">
			  		 <b>Username:</b>${gard.username}
			  	</div>
			  	<div class = "col-md-4">
			  		<b>Full name: </b>${gard.firstName} ${gard.lastName}<br>
			  		 <b>Postcode:</b>${gard.postcode}<br>
			  		 <span class = "glyphicon glyphicon-envelope">    </span> ${gard.email}<br>
			  		  <span class = "glyphicon glyphicon-earphone">   </span><em> phone number here</em>
			  	</div>
			  	<div class = "col-md-4">
			  		<b>Rating:</b>
			  	</div>
			  </div>
			   
			   
				
			  </div>
			  <div class = "">
			  <div class="panel-body panel-collapsed">
			  	<div class = "row">
			  		<div class = "col-lg-7">
			  			<b>Description:</b>${gard.description}<br>
			  		</div>
			  		<div class = "col-lg-5">
			  			<b>Gardener checklist</b>
			  			
			  			<br>
            <label class="checkbox-inline">
                <input type="checkbox" name="rhs1" value="1" disabled="true" <c:if test="${gard.publicProfile.rhs1}">checked</c:if>> RHS
                level 1
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="rhs2" value="1" disabled="true"<c:if test="${gard.publicProfile.rhs2}">checked</c:if>> RHS
                level 2
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="rhs3" value="1" disabled="true"<c:if test="${gard.publicProfile.rhs3}">checked</c:if>> RHS
                level 3
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="rhsMaster" value="1"disabled="true"
                       <c:if test="${gard.publicProfile.rhsMaster}">checked</c:if>> RHS Master of horticulture
            </label>
			  			
			  			<br><br><b>Services offered</b>
			  			
			  			 <br>
            <label class="checkbox-inline">
                <input type="checkbox" name="maintenance" value="1"disabled="true"
                       <c:if test="${gard.publicProfile.maintenanceOffered}">checked</c:if>> General Maintenance
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="design" value="1"disabled="true"
                       <c:if test="${gard.publicProfile.designOffered}">checked</c:if>> Garden Design
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="treesurgery" value="1"disabled="true"
                       <c:if test="${gard.publicProfile.treeSurgeryOffered}">checked</c:if>> Tree surgery
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="waterfeatures" value="1"disabled="true"
                       <c:if test="${gard.publicProfile.waterFeaturesOffered}">checked</c:if>> Water Features
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="fencing" value="1"disabled="true"
                       <c:if test="${gard.publicProfile.fencingOffered}">checked</c:if>> Fencing
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="paving" value="1"disabled="true"
                       <c:if test="${gard.publicProfile.pavingOffered}">checked</c:if>> Paving
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="decking" value="1"disabled="true"
                       <c:if test="${gard.publicProfile.deckingOffered}">checked</c:if>> Decking
            </label>
			  			
			  		</div>
			  	</div>
				
				
			  </div></div>
		</div>
                
                    
                   <%--   <td>${gard.lastName}</td>--%> 
                
            </c:forEach>










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
		
	<script type="text/javascript">
    jQuery(function ($) {
    	
         
        $('.panel-heading span.clickable').on("click", function (e) {
            if ($(this).hasClass('panel-collapsed')) {
                // expand the panel
                $(this).parents('.panel').find('.panel-body').slideDown();
                $(this).removeClass('panel-collapsed');
                //$(this).find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
            }
            else {
                // collapse the panel
                $(this).parents('.panel').find('.panel-body').slideUp();
                $(this).addClass('panel-collapsed');
                //$(this).find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
            }
        });
    });
    
    
    $(document).ready(function(){
    	
    	$('.panel-collapsed').parents('.panel').find('.panel-body').slideUp();
        $('.panel-collapsed').addClass('panel-collapsed');
           
        
		
	    
	});
</script>	
		

</body>

</html>