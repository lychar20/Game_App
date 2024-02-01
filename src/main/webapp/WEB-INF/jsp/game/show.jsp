<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${game.name}"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container">
    <h1 class=" header_review">${game.name}</h1>
    <div class="row">
        <div class="col-md-6 col-sm-12">
            <div class="container-img p-3">
                <img alt="${game.name}" src="${game.image}">
            </div>
        </div>
        <div class="col-md-6 col-sm-12">
            <h1>
                ${game.name}
                <security:authorize access="hasRole('MODERATOR')">
                    <a class="ms-1 link-green" href="${UrlRoute.URL_GAME_UPLOAD_IMAGE}/${game.slug}">
                        <i class="fa-solid fa-upload"></i>
                    </a>
                </security:authorize>
            </h1>
            <p class="m-0">Sortie le : ${dateUtils.getDateFormat(game.publishedAt, "dd/MM/yyyy")}</p>
            <p class="m-0">Editeur : <a class="link-if" href="#">${game.publisher.name}</a></p>
            <p class="m-0">Genre : <a class="link-if" href="#">${game.genre.name}</a></p>
            <p class="m-0">Modèle économique : <a class="link-if" href="#">${game.businessModel.name}</a></p>
            <p class="m-0">Classification : <a class="link-if" href="#">${game.classification.name}</a></p>

            <c:if test="${game.platforms.size() > 0}">
                <p class="m-0">Disponible sur :</p>
                <ul class="list-unstyled">
                    <c:forEach items="${game.platforms}" var="platform">
                        <li>
                            <a class="link-if" href="#">
                                    ${platform.name}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
    </div>

    <div>
        <h2>
            Commentaires

            <security:authorize access="!hasRole('MODERATOR')">
                <button class="ms-2 btn btn-link"
                        title="Ajouter un commentaire"
                        data-hide-show-button="formReview"
                >
                    <i class="fa fa-pen fa-2x"></i>
                </button>
            </security:authorize>
        </h2>


        <div class="my-3 d-none"
             data-hide-show-container="formReview"
        >
            <f:form cssClass="col-md-8 col-sm-12 mx-auto"
                    action="${currentUrl}"
                    method="post"
                    modelAttribute="reviewDTO"
            >
                <div class="mb-3 row">
                    <f:label path="description" class="col-sm-2 col-form-label">Description</f:label>
                    <div class="col-sm-10">
                        <f:textarea cssClass="form-control" path="description"/>
                        <f:errors path="description" cssClass="invalid-feedback"/>
                    </div>
                </div>
                <div class="my-3 row">
                    <f:label path="rating" class="col-sm-2 col-form-label">Note</f:label>
                    <div class="col-sm-10">
                        <f:input type="number" step="0.5" cssClass="form-control" path="rating"/>
                        <f:errors path="rating" cssClass="invalid-feedback"/>
                    </div>
                </div>
                <f:button type="submit" class="btn btn-success">
                    <i class="fa fa-check"></i> Ajouter
                </f:button>
            </f:form>
        </div>



        <div class="d-flex justify-content-between">
            <div class="d-flex">
                <!-- Label à afficher -->
                <c:set var="label" scope="request" value="Date"/>
                <!-- Sur quelle propriété de l'objet on souhaite trier -->
                <c:set var="sortable" value="createdAt"/>
                <%@ include file="../component/sortable.jsp" %>

                <c:set var="label" scope="request" value="Note"/>
                <c:set var="sortable" value="rating"/>
                <%@ include file="../component/sortable.jsp" %>

                <c:set var="label" scope="request" value="Joueur"/>
                <c:set var="sortable" value="gamer.nickname"/>
                <%@ include file="../component/sortable.jsp" %>

                <%@ include file="../component/filter-reset.jsp" %>
            </div>

            <c:set var="page" scope="request" value="${pageReviews}"/>
            <%@ include file="../component/pagination-number.jsp" %>
        </div>
        <div class="row">
            <c:forEach items="${pageReviews.content}" var="review">
                <div class="col-lg-4 col-md-6 col-sm-12 mt-4">
                    <%@ include file="../component/entity/review-card.jsp" %>
                </div>
            </c:forEach>
        </div>
        <%@ include file="../component/pagination.jsp" %>
    </div>


</div>

<%@ include file="../footer.jsp" %>
