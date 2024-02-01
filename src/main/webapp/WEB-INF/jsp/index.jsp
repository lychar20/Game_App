<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="La-hype-des-jeux"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>



<div class="container">

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item active" aria-current="page">Accueil</li>
            <li class="breadcrumb-item"><a class="btn-link" href="${contextPath}${UrlRoute.URL_GAME}">Les jeux</a></li>
        </ol>
    </nav>

    <div class="d-flex justify-content-between">
        <div class="d-flex">
            <!-- Label à afficher -->
            <c:set var="label" scope="request" value="Date"/>
            <!-- Sur quelle propriété de l'objet on souhaite trier -->
            <c:set var="sortable" value="createdAt"/>
            <%@ include file="component/sortable.jsp" %>

            <c:set var="label" scope="request" value="Note"/>
            <c:set var="sortable" value="rating"/>
            <%@ include file="component/sortable.jsp" %>

            <c:set var="label" scope="request" value="Jeu"/>
            <c:set var="sortable" value="game.name"/>
            <%@ include file="component/sortable.jsp" %>

            <c:set var="label" scope="request" value="Joueur"/>
            <c:set var="sortable" value="gamer.nickname"/>
            <%@ include file="component/sortable.jsp" %>

            <security:authorize access="hasRole('MODERATOR')">
                <div class="sort-filter mt-4 me-3">
                    <select class="form-select sortable-select">
                        <option value="all" data-filter-url="${currentUrl}">
                            Tous les commentaires
                        </option>
                        <option value="sort=moderator,desc"
                                data-filter-url="${jspUtils.generateUrlFrom(currentUrl, currentQuery, "sort=moderator,desc")}"
                        >
                            Modérés
                        </option>
                        <option value="sort=moderator,asc"
                                data-filter-url="${jspUtils.generateUrlFrom(currentUrl, currentQuery, "sort=moderator,asc")}"
                        >
                            À modérer
                        </option>
                    </select>
                </div>
            </security:authorize>

            <%@ include file="component/filter-reset.jsp" %>
        </div>

        <c:set var="page" scope="request" value="${pageReviews}"/>
        <%@ include file="component/pagination-number.jsp" %>
    </div>
    <div class="row">
        <c:forEach items="${pageReviews.content}" var="review">
            <div class="col-lg-4 col-md-6 col-sm-12 mt-4">
                <%@ include file="component/entity/review-card.jsp" %>
            </div>
        </c:forEach>
    </div>
    <security:authorize access="hasRole('MODERATOR')">
        <div>
            <a href="${UrlRoute.URL_EXPORT}" class="btn btn-link">
                <i class="fa-solid fa-file-excel me-1"></i>
                Télécharger export Excel
            </a>
        </div>
    </security:authorize>
    <%@ include file="component/pagination.jsp" %>

    <div class="carousel_custom">
        <div id="carouselExampleControls" class="carousel slide col-lg-6 col-md-8 col-sm-12 " data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="https://cdn.thegamesdb.net/images/thumb/boxart/front/32185-1.jpg" class="w-100 d-block cover" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="https://cdn.thegamesdb.net/images/thumb/boxart/front/151-1.jpg" class="w-100 d-block cover" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="https://cdn.thegamesdb.net/images/thumb/boxart/front/14517-1.jpg" class="w-100 d-block cover" alt="...">
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>

    </div>
</div>

<%@ include file="footer.jsp" %>
