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
          href="${pageContext.request.contextPath}/resources/css/bootstrap-select.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/reviewform.css">
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
                                        <li><a href="photos">my portfolio photos</a></li>
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

<div class="container">

    <div class="largetext"><a href="findGardener">Search Page</a> -> <a href="${searchTerm}">Results</a>
        -> ${gardener.publicProfile.tradename}'s Profile
    </div>

    <div class="row">


        <div class="col-xs-12 col-sm-6 col-md-9">
            <div class="well well-sm">
                <div class="row">
                    <div class="col-sm-6 col-md-4">
                        <c:if test="${gardener.publicProfile.profileImage.path eq null}">
                            <img class="img-rounded" src="http://localhost:8080/images/no_photo.jpg" width="150px"
                                 class="img-rounded img-responsive"/>
                        </c:if>
                        <c:if test="${gardener.publicProfile.profileImage.path ne null}">

                            <img class="img-rounded"
                                 src="http://localhost:8080${gardener.publicProfile.profileImage.path}"
                                 <%--width="175px" height="120px"--%>width="150px"/>
                        </c:if>
                        <%--<img src="http://placehold.it/380x500" alt="" class="img-rounded img-responsive" />--%>
                    </div>
                    <div class="col-sm-6 col-md-8">
                        <h4>
                            ${gardener.publicProfile.tradename}</h4>

                        <p>
                            <i class="glyphicon glyphicon-envelope"></i>${gardener.email}
                            <br/>

                            <i class="glyphicon glyphicon-phone"></i>${gardener.phone}
                            <br/><b>Postcode: </b>${gardener.postcode}
                            <br/>

                        </p>
                        <b>Qualifications</b>

                        <br>
                        <c:if test="${gardener.publicProfile.rhs1}">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="rhs1" value="1" disabled="true" checked="checked"> RHS
                                level 1
                            </label>
                        </c:if>
                        <c:if test="${gardener.publicProfile.rhs2}">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="rhs2" value="1" disabled="true" checked="checked"> RHS
                                level 2
                            </label>
                        </c:if>
                        <c:if test="${gardener.publicProfile.rhs3}">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="rhs3" value="1" disabled="true" checked="checked"> RHS
                                level 3
                            </label>
                        </c:if>
                        <c:if test="${gardener.publicProfile.rhsMaster}">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="rhs4" value="1" disabled="true" checked="checked"> RHS
                                Master
                            </label>
                        </c:if>

                        <br/>
                        <b>Services Offered</b>

                        <br>
                        <c:if test="${gardener.publicProfile.maintenanceOffered}">
                            <label class="checkbox-inline">
                                <input type="checkbox" disabled="true" checked="checked"> General Maintenance

                            </label>
                        </c:if>
                        <c:if test="${gardener.publicProfile.designOffered}">
                            <label class="checkbox-inline">
                                <input type="checkbox" disabled="true" checked="checked"> Garden Design
                                level 1
                            </label>
                        </c:if>
                        <c:if test="${gardener.publicProfile.treeSurgeryOffered}">
                            <label class="checkbox-inline">
                                <input type="checkbox" disabled="true" checked="checked"> Tree Surgery

                            </label>
                        </c:if>
                        <c:if test="${gardener.publicProfile.waterFeaturesOffered}">
                            <label class="checkbox-inline">
                                <input type="checkbox" disabled="true" checked="checked"> Water Features

                            </label>
                        </c:if>
                        <c:if test="${gardener.publicProfile.fencingOffered}">
                            <label class="checkbox-inline">
                                <input type="checkbox" disabled="true" checked="checked"> Fencing

                            </label>
                        </c:if>
                        <c:if test="${gardener.publicProfile.pavingOffered}">
                            <label class="checkbox-inline">
                                <input type="checkbox" disabled="true" checked="checked"> Paving

                            </label>
                        </c:if>
                        <c:if test="${gardener.publicProfile.deckingOffered}">
                            <label class="checkbox-inline">
                                <input type="checkbox" disabled="true" checked="checked"> Decking

                            </label>
                        </c:if>


                        <!-- Split button -->

                    </div>
                </div>
                <div class="row">
                    <br/>

                    <div class="col-lg-10">
                        ${gardener.publicProfile.description}
                    </div>
                </div>
            </div>

        </div>


    </div>

    <div class="row">
        <h3>Examples of Previous Work</h3>
        <br>


        <div class="col-md-9">
            <div class="carousel slide" id="myCarousel">
                <div class="carousel-inner">
                    <c:forEach items="${gardener.publicProfile.images}" var="image" varStatus="loop">
                        <div class="item <c:if test="${loop.index eq 0}">active</c:if>">
                            <div class="col-lg-4 col-xs-4 col-md-4 col-sm-4">
                                <img src="${image.path}" class="img-responsive"></div>
                        </div>
                    </c:forEach>


                </div>
                <a class="left carousel-control" href="#myCarousel" data-slide="prev"><i
                        class="glyphicon glyphicon-chevron-left"></i></a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next"><i
                        class="glyphicon glyphicon-chevron-right"></i></a>
            </div>
        </div>


    </div>


    <br>





    <div class="row">

        <div class="col-md-5">
        <h3>User Ratings</h3>

        <div>Average Score: ${gardener.avgReviewScore} from ${gardener.reviewCount} reviews</div>

        <c:if test="${fn:length(gardener.reviews) > 0}">


                <c:forEach items="${gardener.reviews}" var="review" varStatus="loop">


                        <div class="well">
                            <div class="media">

                                <div class="media-body">
                                    <h4 class="media-heading">Review ${loop.index +1}</h4>

                                    <p class="text-right">By ${review.authorUsername}</p>

                                    <p>${review.text}</p>
                                    <ul class="list-inline list-unstyled">
                                        <li><span><i
                                                class="glyphicon glyphicon-calendar"></i> ${review.dateString} </span>
                                        </li>
                                        <li>|</li>
                                        <li>
                                            <span class="glyphicon glyphicon-star<c:if test="${review.rating <1}">-empty</c:if>"></span>
                                            <span class="glyphicon glyphicon-star<c:if test="${review.rating <2}">-empty</c:if>"></span>
                                            <span class="glyphicon glyphicon-star<c:if test="${review.rating <3}">-empty</c:if>"></span>
                                            <span class="glyphicon glyphicon-star<c:if test="${review.rating <4}">-empty</c:if>"></span>
                                            <span class="glyphicon glyphicon-star<c:if test="${review.rating <5}">-empty</c:if>"></span>
                                        </li>

                                    </ul>
                                </div>
                            </div>

                   </div>


                </c:forEach>

        </c:if>
        </div>
        <div class="col-md-6">
            <h3>Leave a Review</h3>

            <div class="well well-sm">
                <div class="text-right">
                    <a class="btn btn-success btn-green" href="#reviews-anchor" id="open-review-box">Leave a Review</a>
                </div>

                <div class="row" id="post-review-box" style="display:none;">
                    <div class="col-md-12">
                        <form accept-charset="UTF-8" action="showProfile" method="post">
                            <input id="ratings-hidden" name="rating" type="hidden">
                            <input name="gardener_username" type="hidden" value="${gardener.username}">
                            <input name="search_term" type="hidden" value="${searchTerm}">
                            <input name="lat" type="hidden" value="${lat}">
                            <input name="lng" type="hidden" value="${lng}">
                            <input name="name" type="hidden" value="${name}">
                            <textarea class="form-control animated" cols="50" id="new-review" name="comment"
                                      placeholder="Enter your review here..." rows="5"></textarea>

                            <div class="text-right">
                                <div class="stars starrr" data-rating="0"></div>
                                <a class="btn btn-danger btn-sm" href="#" id="close-review-box"
                                   style="display:none; margin-right: 10px;">
                                    <span class="glyphicon glyphicon-remove"></span>Cancel</a>
                                <button class="btn btn-success btn-lg" type="submit">Save</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>


    </div>


</div>


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
<script
        src="${pageContext.request.contextPath}/resources/js/reviewform.js"></script>


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