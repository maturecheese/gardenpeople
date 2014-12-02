<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/allpages.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/bootstrap-select.css.css">
    <title>Insert title here</title>

    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/map.js"></script>
    <script>
        google.maps.event.addDomListener(window, 'load', initialize);
    </script>

</head>
<body>


<!--navbar -->
<div style="">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <!--navbar-static-top will make it disappear if you scroll horizontally -->
        <div class="container">
            <!--navbar-brand is used for titles - it has larger text -->

            <a href="home" class="navbar-brand">Gardener Website</a>

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
                                    <li><a href="details">my details</a></li>
                                    <c:if test="${sessionScope.user.gardener}">
                                        <li><a href="profile">my public profile</a></li>
                                        <li><a href="photos">my photos</a></li>
                                    </c:if>
                                </ul>
                            </li>
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
    <h1>My Profile</h1>

    <c:if test="${not user.profileRecordedOnDatabase}">
        <div class="warning">You have no profile saved in our records. Please create one</div>
    </c:if>
    <c:if test="${user.profileRecordedOnDatabase}">
        <c:if test="${empty confirmation}">
           <div class="info">Your profile was last edited at ${user.publicProfile.formattedUpdatedAt}</div>
        </c:if>
        <c:if test="${not empty confirmation}">
            <div class="success">${confirmation}</div>
        </c:if>
    </c:if>


    <c:if test="${fn:length(errors) > 0}">
        <div class="error">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>




    <form role="form" action="profile" method="post">


        <!-- Form Name -->
        <legend>Describe your services</legend>

        <!-- Text input-->
        <div class="form-group">
            <label for="tradename">Your Company or Trading Name</label>

            <input id="tradename" name="tradename" class="form-control" type="text"
                   placeholder="Your Name or Company Name" value="${user.publicProfile.tradename}">

        </div>
        <div class="form-group">
            <label for="description">Description</label>

            <textarea class="form-control" id="description" name="description"
                      placeholder="additional information for your customers"><c:out
                    value='${user.publicProfile.description}'/>
            </textarea>


        </div>


        <!-- Multiple Checkboxes -->
        <div class="form-group">
            <label>Do You have any of the following?</label>

            <br>
            <label class="checkbox-inline">
                <input type="checkbox" name="rhs1" value="1" <c:if test="${user.publicProfile.rhs1}">checked</c:if>> RHS
                level 1
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="rhs2" value="1" <c:if test="${user.publicProfile.rhs2}">checked</c:if>> RHS
                level 2
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="rhs3" value="1" <c:if test="${user.publicProfile.rhs3}">checked</c:if>> RHS
                level 3
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="rhsMaster" value="1"
                       <c:if test="${user.publicProfile.rhsMaster}">checked</c:if>> RHS Master of horticulture
            </label>


        </div>

        <div class="form-group">
            <label>Please check services you offer</label>

            <br>
            <label class="checkbox-inline">
                <input type="checkbox" name="maintenance" value="1"
                       <c:if test="${user.publicProfile.maintenanceOffered}">checked</c:if>> General Maintenance
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="design" value="1"
                       <c:if test="${user.publicProfile.designOffered}">checked</c:if>> Garden Design
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="treesurgery" value="1"
                       <c:if test="${user.publicProfile.treeSurgeryOffered}">checked</c:if>> Tree surgery
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="waterfeatures" value="1"
                       <c:if test="${user.publicProfile.waterFeaturesOffered}">checked</c:if>> Water Features
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="fencing" value="1"
                       <c:if test="${user.publicProfile.fencingOffered}">checked</c:if>> Fencing
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="paving" value="1"
                       <c:if test="${user.publicProfile.pavingOffered}">checked</c:if>> Paving
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" name="decking" value="1"
                       <c:if test="${user.publicProfile.deckingOffered}">checked</c:if>> Decking
            </label>


        </div>
        <div class="form-group">
                <span>

                    <label for="radius">
                        Return my profile for jobs within :
                    </label><br>

                    <select id="radius" name="radius" class="form-control">
                        <option value="5" <c:if test="${user.publicProfile.radius == 5}">selected="selected"</c:if>>5
                            miles
                        </option>
                        <option value="10" <c:if test="${user.publicProfile.radius == 10}">selected="selected"</c:if>>10
                            miles
                        </option>
                        <option value="20" <c:if test="${user.publicProfile.radius == 20}">selected="selected"</c:if>>20
                            miles
                        </option>
                        <option value="30" <c:if test="${user.publicProfile.radius == 30}">selected="selected"</c:if>>30
                            miles
                        </option>
                        <option value="40" <c:if test="${user.publicProfile.radius == 40}">selected="selected"</c:if>>40
                            miles
                        </option>
                        <option value="50" <c:if test="${user.publicProfile.radius == 50}">selected="selected"</c:if>>50
                            miles
                        </option>

                    </select> <label>
                    Of
                </label>
                    <input id="pac-input" class="form-control location" name="Location"
                           type="text"
                           placeholder="Please enter postcode"
                           value="<c:out value='${user.publicProfile.googleLocation}'/>"
                           required>
                </span>

        </div>


        <input id="Latitude" class="field" name="Latitude"
               readonly value="<c:out value='${user.publicProfile.latitude}'/>"/>

        <input id="Longitude" class="field" name="Longitude"
               readonly value="<c:out value='${user.publicProfile.longitude}'/>"/>


        <!-- Select Multiple -->
        <%--<div class="form-group">
            <label for="areas">Select
                Multiple</label>


                <select id="areas" name="areas"
                        class="form-control" multiple size="10">
                    <c:forEach items="${applicationScope.counties}" var="county">

                        <option value="${county.id}">${county.name}</option>
                    </c:forEach>

                </select>

            <select multiple="multiple">
                <c:forEach items="${applicationScope.counties}" var="county">
                    &lt;%&ndash;<c:when test="${county.id = user.}"&ndash;%&gt;
                    <option value="${county.id}">${county.name}</option>
                </c:forEach>
                <option value="1" selected="selected">January</option>
                <option value="2" disabled="disabled">February</option>
                <option value="3" selected="selected" disabled="disabled">March</option>
                ...
            </select>

        </div>
--%>
        <!-- Button -->
        <div class="form-group">

            <div class="col-md-12">
                <button id="save" name="save"
                        class="btn btn-primary">Save
                </button>
            </div>
        </div>


    </form>
    <div id="map-canvas" style="margin-right:10vw; margin-left:10vw"></div>

</div>
<!-- jquery-->


<script
        src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
<!-- javascript-->
<script
        src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script
        src="${pageContext.request.contextPath}/resources/js/fileinput.js"></script>

<!-- Scrolling Nav JavaScript -->
<script
        src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>
<script
        src="${pageContext.request.contextPath}/resources/js/scrolling-nav.js"></script>
<script
        src="${pageContext.request.contextPath}/resources/js/bootstrap-select.js"></script>


<script type="text/javascript">


    $(document).ready(function () {
        var event = $.Event("keydown");
        event.which = 13;
        $('#pac-input').trigger(event);

        $('.selectpicker').selectpicker();
        $(window).keydown(function (event) {
            if (event.keyCode == 13) {
                event.preventDefault();
                return false;
            }
        });

        $('#pac-input').change(function () {

            var s = document.getElementById('Latitude');

            console.log(s);

        });
    });
    // $('#pac-input').closest('.form-group').append('<div class="col-lg-1"><img src="http://alittlecode.com/files/jQuery-Validate-Demo/assets/img/valid.png" alt=">>OK!"></div>')
</script>

</body>