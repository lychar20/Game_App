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


    <h1 class=" my-5">Les Jeux par genre
        <security:authorize access="hasRole('MODERATOR')">
            <a href="${UrlRoute.URL_GAME_NEW}" class="ms-2" title="Ajouter un jeu">
                <i class="fa fa-circle-plus link-green"></i>
            </a>
        </security:authorize>
    </h1>


    <div class="d-flex my-5">

        <div class="row">
            <c:forEach items="${genre}" var="genre">
                <h2> Test </h2>
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

<%--        <select class="form-select sortable-select">--%>


<%--            <c:forEach items="${gameDTO.name}" var="review">--%>

<%--                <option value="sort=moderator,asc"--%>
<%--                        data-filter-url="${jspUtils.generateUrlFrom(currentUrl, currentQuery, "sort=moderator,asc")}"--%>
<%--                >--%>
<%--                    ${genre.name}--%>
<%--                </option>--%>

<%--                </div>--%>
<%--            </c:forEach>--%>

<%--            <option value="sort=moderator,asc"--%>
<%--                    data-filter-url="${jspUtils.generateUrlFrom(currentUrl, currentQuery, "sort=moderator,asc")}"--%>
<%--            >--%>
<%--                Editeur--%>
<%--            </option>--%>


<%--        </select>--%>

<%--        <div class="col-md-4 col-sm-12">--%>
<%--            <f:label class="col-form-label" path="genre">--%>
<%--                Genre--%>
<%--            </f:label>--%>
<%--            <f:select path="genre"--%>
<%--                      items="${genres}"--%>
<%--                      cssClass="form-select"--%>
<%--                      itemLabel="name"--%>
<%--            >--%>
<%--            </f:select>--%>
<%--            <f:errors path="genre" cssClass="invalid-feedback"/>--%>
<%--        </div>--%>


    </div>




    </div>

</div>



<%@ include file="../footer.jsp" %>