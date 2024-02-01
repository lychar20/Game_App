<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="La-hype-des-jeux"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<html>
<head>
    <title>Avis</title>
</head>

<div class="container">
    <h2 class=" header_review">Avis</h2>
    <div class="container_review ">

                <div class="card_review">
                    <div class="card_review_image ">
                        <img alt="${review.game.image}" src="${review.game.image}">
                    </div>
                    <div class="d-flex justify-content-between text-review">
                        <p>${review.game.name}</p>

                    </div>
                </div>

                <div class="info_review">
                    <p class="description_review"> ${review.description} </p><br>
                    <p class="nickname_review"> ${review.gamer.nickname} </p><br>
                    <p class="rating_review"> ${review.rating} </p><br>

                    <p class="date_review"> ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")} </p>

                </div>
    </div>



</div>

<body>

<%@ include file="footer.jsp" %>
