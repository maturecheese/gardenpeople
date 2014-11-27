<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--COPY HEAD TAGS INTO ALL FILES-->
<head>
		<title>Gardeners Website</title>
		
		<meta name = "viewport" content = "width=device-width, initial-scale=1.0" >
		<!--use min file or else it will take a long time to load -->
		<link href="${pageContext.request.contextPath}/resources/bootstrap.min.css" rel = "stylesheet">
		<!--custom styles -->
		<link href="${pageContext.request.contextPath}/resources/styles.min.css" rel = "stylesheet">
		<!-- <link href = "css/styles.css" rel = "stylesheet"> -->
		<link href="${pageContext.request.contextPath}/resources/font-awesome.min.css" rel="stylesheet" type="text/css">
		<!--<link href="css/scrolling-nav.css" rel="stylesheet">-->
		
		
		<!--load the Google Places library using the libraries parameter in the bootstrap URL for the Google Maps JavaScript API.-->
	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>

	<script type="text/javascript" src="scripts/map.js"></script>
		<script>
			google.maps.event.addDomListener(window, 'load', initialize);
		</script>
		
</head>




<body>
<!--navbar -->
<div style = "margin-bottom:9vw">
<div class = "navbar navbar-inverse navbar-fixed-top"> <!--navbar-static-top will make it disappear if you scroll horizontally -->
			<div class = "container">
				<!--navbar-brand is used for titles - it has larger text -->
				
				<!--<a href = '/index.php' class="navbar-brand" >Gardener Website</a>-->
				
				<!-- button 
				this button will appear if screen collapses (smaller screen) 
				-->
				<button class = "navbar-toggle" data-toggle = "collapse" data-target = ".navHeaderCollapse">
					<span class = "glyphicon glyphicon-th-list"></span>
				</button>
		
				<div class = "collapse navbar-collapse navHeaderCollapse">
					<!--navbar-nav gives styling and navbar-right aligns it to the right-->				
					

						<ul class="nav navbar-nav ">
						  <li role="" ><a href="index.html"  >Home</a></li>
						  <li role=""><a href="findGardener.html">Find a gardener</a></li>
						  <li role=""><a href="listCompany.html">List Your Company</a></li>
						  <li role=""><a href="">Help and Advice</a></li>
					</ul>

					
			</div>
			
			</div>
			<!--end of nav bar-->
		</div>	
	</div>
<!--end navbar-->





<section id="findGardener" class="bg-light-gray">
    <div class="container"><br><br><br>

		<h1>FIND A GARDENER</h1>
		
		 <div class="panel-body" style = "font-size:20px">
			 
			  <span class = "glyphicon glyphicon-ok"></span> We have registered professionals with a wide range of skills<br>
			  <span class = "glyphicon glyphicon-ok"></span> View professionals who work in your area.<br>
			  <span class = "glyphicon glyphicon-ok"></span> View detailed profiles and user feedback to help you decide who to hire.<br>
			 <br>
			 </div>
		
		
		<form role = "form" action = "" method = "post">
		<h3>Looking for a particular gardener? Enter their name below.</h3>
			<div class = "form-group">
				<input type="text" class="form-control" name="" placeholder="Enter the gardener's name here" required >
			</div>
			 <button type="submit" class="btn btn-default" style = "margin-bottom : 5vw">Search</button>
		</form>
		
		
		<h3>Find gardeners near me</h3>
		<form role="form" action = "" method = "post">
		<div class = "row"><!--row for "find a gardener in:"-->
			<div class = "col-md-6">
				<label>Find a gardener in:</label>
				<div class = "form-group">
					<select class = "form-control" name = "location" required>
						<option value="" disabled selected>Select your option</option>
						<option value="londonNorth">London, North</option>				  
					</select>
				</div>
			</div>
			
			<div class = "col-md-6">
				<label>Or Within:</label>
				<div class = "form-inline">
					<div class = "form-group">
						<select class = "form-control" name = "nationality" required>
							<option value="" disabled selected>Select your option</option>
							<option value="5miles">5 miles</option>
						</select>
						<label>of</label>
					</div>
				</div>			
			</div>
		</div><!--row for "find a gardener in:"-->
		
		<div class ="row"><!--map-->
			<div class = "col-lg-12">	
				<div class = "form-group">
					<label>Location</label>
					<input id="pac-input" class="form-control" name = "Location" type="text" placeholder="Enter a location and the details will be filled in" required>
				</div>

			<!--table for address form-->
			<table id="address">
				<tr class = "spaceUnder">
					<td>Address</td> <!-- if location doesnt work use address instead-->
					<td colspan = "3"><input class="form-control" id="Address" name="Address" disabled = "true"/>  </td>
				</tr>
				
				<tr class="spaceUnder" >
					<td>Street address</td>
					<td><input class="form-control" id="street_number" name = "StreetNumber" disabled="true"></input></td>
					<td colspan="2"><input class="form-control"id="route" name = "Route" disabled="true"></input></td> <!-- route is street name e.g. gower street -->
				</tr>
		   
				<tr tr class="spaceUnder">
					<td>City</td>
					<td colspan="3"><input class="form-control" id="locality" name = "City" disabled="true"></input></td>
				</tr>
				
				<tr tr class="spaceUnder">
					<td>State</td>
					<td><input class="form-control" id="administrative_area_level_1" name = "State" disabled="true"></input></td>
					<td>Zip code</td>
					<td><input class="form-control" id="postal_code" name = "ZipCode" disabled="true"></input></td>
				</tr>
				
				<tr tr class="spaceUnder">
					<td>Country</td>
					<td colspan="3"><input class="form-control" id="country" name = "Country" disabled="true"></input></td>	
				</tr>
		  
				<tr tr class="spaceUnder">
					<td>Latitude</td>
					<td><input id="Latitude" class="field" name="Latitude" readonly /></td>
					<td>Longitude</td>
					<td><input  id="Longitude" class="field" name="Longitude" readonly /></td>
				</tr>
			</table>
			
		</div>	
	</div><!--maprow-->
			
			
		   <br><br><br>
		   
		   
		<div class="panel-group" id="accordionb" >	
		<div class= "panel panel-default" id = "gardener" >
			<div class="panel-heading">
			  <h4 class="panel-title">
			   Advanced Search
				<a data-toggle="collapse" data-parent="#accordionb"  href="#collapse1b"  >
				 <span class = "glyphicon glyphicon-chevron-down pull-right"></span>
				</a>
				
			  </h4>
			</div>
			<div id="collapse1b" class="panel-collapse collapse ">
			  <div class="panel-body">
					   <label>Show Only If:</label>
		   <div class = "form-group">
				<label class="checkbox-inline" >
				<input id = "" type="checkbox" name="filter" value = "photo"  required> Has Personal Photo
				</label>
				
				<label class="checkbox-inline" >
				<input id = "" type="checkbox" name="filter" value="feedback"   > Has User Feedback
				</label>
				
				<label class="checkbox-inline" >
				<input id = "" type="checkbox" name="filter" value = "previous"  required> Photos of previous jobs
				</label>
				
				<label class="checkbox-inline" >
				<input id = "" type="checkbox" name="filter" value = "rhs"  required> Holds RHS Level 2/3 Certificate
				</label>
				
				<label class="checkbox-inline" >
				<input id = "" type="checkbox" name="filter" value = "liability"  required> Liability insurance
				</label>
				
				<label class="checkbox-inline" >
				<input id = "" type="checkbox" name="filter" value = "vat"  required>VAT registered
				</label>
				
			</div>
		   
		   <label>What Type of Service are you looking for?</label>
		   
		   <div class = "form-group">
				<select class = "form-control" name = "" required>
					  <option value="" disabled selected>Select your option</option>
					  <option value="">Lawn and General Maintenance</option>
				</select>
			</div>
			
			 
			<div class="form-group">
			<label>This job is suited to:</label>
				<label class="radio-inline" >
				<input type="radio" name="suited" value = "everyone"  > Everyone
				</label>
				
				<label class="radio-inline" >
				<input type="radio" name="suited" value="single"> Single person
				</label>
				<label class="radio-inline" >
				<input type="radio" name="suited" value="single"> Team of gardeners
				</label>
			</div>	
		   
			 </div>
			</div>
		</div>
		</div>
		   <button type="submit" class="btn btn-default" style = "margin-bottom : 5vw">Search</button>
		
		   
		   </form>
     </div>
</section>



<div id="map-canvas" style = "margin-right:10vw; margin-left:10vw">




<div style = "margin-bottom:30vw"><br></div>
		<!-- jquery-->
		<script src="js/jquery-1.11.0.js"></script>
		<!-- javascript-->
		<script src = "js/bootstrap.js"></script>
		
		
		<!-- Scrolling Nav JavaScript -->
    <script src="js/jquery.easing.min.js"></script>
    <script src="js/scrolling-nav.js"></script>
		
		
		
</body>


</html>

Displaying findGardener.html.