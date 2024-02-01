<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="La-hype-des-jeux"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="element_banner">
</div>

<div class="container position-relative container-game">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a class="btn-link" href="${contextPath}/">Accueil</a></li>
            <li class="breadcrumb-item active" aria-current="page">Les jeux</li>
        </ol>
    </nav>

    <h1 class=" my-5">Les Jeux
        <security:authorize access="hasRole('MODERATOR')">
            <a href="${UrlRoute.URL_GAME_NEW}" class="ms-2" title="Ajouter un jeu">
                <i class="fa fa-circle-plus link-green"></i>
            </a>
        </security:authorize>
    </h1>


<%--    <div class="d-flex my-5">--%>


<%--        <select class="form-select sortable-select">--%>
<%--            <option value="sort=moderator,desc"--%>
<%--                    data-filter-url="${UrlRoute.URL_GAME_GENRE}"--%>
<%--            >--%>
<%--                Genre--%>
<%--            </option>--%>
<%--            <option value="sort=moderator,asc"--%>
<%--                    data-filter-url="${jspUtils.generateUrlFrom(currentUrl, currentQuery, "sort=moderator,asc")}"--%>
<%--            >--%>
<%--                Editeur--%>
<%--            </option>--%>
<%--        </select>--%>


<%--    </div>--%>


    <div class="row">
        <c:forEach items="${games}" var="game">
            <a class="col-lg-4 col-md-6 col-sm-12 mt-2 main-game-card" href="${UrlRoute.URL_GAME}/${game.slug}">
                <div class="game-card">
                    <div class="game-card-img" >
<%--                        style="background-image: url(${game.image})"--%>
                        <img alt="${game.name}" src="${game.image}">
                    </div>
                    <div class="d-flex justify-content-between">
                        <p>${game.name}</p>

                    </div>
                </div>
            </a>
        </c:forEach>
    </div>
</div>



<%@ include file="../footer.jsp" %>