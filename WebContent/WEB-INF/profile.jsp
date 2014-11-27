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

    <div class="error">
        <c:if test="${fn:length(errors) > 0}">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>
    </div>


    <form role="form" action="UploadPhoto" method="post"
          enctype="multipart/form-data">

        <p>choose photo to upload:</p>

        <div class="input-group">
				<span class="input-group-btn"> <span
                        class="btn btn-primary btn-file"> Browse&hellip; <input
                        type="file" multiple name="fileName">
				</span>
				</span> <input type="text" class="form-control" readonly>
        </div>


        <button type="submit" class="btn btn-default upload">Upload</button>
    </form>
    <br>

    <form role="form" action="details" method="post">


        <!-- Form Name -->
        <legend>Describe your services</legend>

        <!-- Text input-->
        <div class="form-group">
            <label for="company">Your Company or Trading Name</label>

            <input id="company" name="company" class="form-control" type="text" placeholder="Your Name or Company Name">

        </div>
        <div class="form-group">
            <label for="description">Description</label>

            <textarea class="form-control" id="description" name="description"
                      placeholder="additional information for your customers"></textarea>


        </div>


        <!-- Multiple Checkboxes -->
        <div class="form-group">
            <label>Do You have any of the following?</label>

            <div class="form-group">
                <label class="checkbox-inline">
                    <input type="checkbox" id="inlineCheckbox1" value="option1"> RHS level 1
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" id="inlineCheckbox2" value="option1"> RHS level 2
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" id="inlineCheckbox3" value="option1"> RHS level 3
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" id="inlineCheckbox3" value="option1"> RHS Master of horticulture
                </label>


            </div>

            <!-- Select Multiple -->
            <div class="form-group">
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
                        <%--<c:when test="${county.id = user.}"--%>
                        <option value="${county.id}">${county.name}</option>
                    </c:forEach>
                    <option value="1" selected="selected">January</option>
                    <option value="2" disabled="disabled">February</option>
                    <option value="3" selected="selected" disabled="disabled">March</option>
                    ...
                </select>

            </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-12 control-label" for="singlebutton">Single
                    Button</label>

                <div class="col-md-12">
                    <button id="singlebutton" name="singlebutton"
                            class="btn btn-primary">Button
                    </button>
                </div>
            </div>


    </form>


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


</body>