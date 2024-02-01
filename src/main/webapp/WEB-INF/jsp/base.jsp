<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<%
    Object title = request.getAttribute("title");
    if (title == null) {
        title = "TestGame";
    }
    request.setAttribute("title", title);
%>

<html>
    <head>
        <title>${title}</title>
        <link href="${contextPath}/css/main.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
        <script type="text/javascript" src="${contextPath}/js/main.js"></script>
        <script type="text/javascript" src="${contextPath}/js/lib/bootstrap/bootstrap.js"></script>

        <script type="text/javascript" src="${contextPath}/js/init-sortable.js"></script>
        <script type="text/javascript" src="${contextPath}/js/hide-form.js"></script>
        <script type="text/javascript" src="${contextPath}/js/alert.js"></script>
    </head>

    <body>
        <security:authorize access="isAuthenticated()">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="row w-100">
                    <div class="col-3">
                        <a class="navbar-brand ms-3" href="${contextPath}/">
                            <i class="fa-brands fa-steam fa-3x"></i>
                        </a>
                    </div>
                    <div class="col-9 my-auto">
                        <div class="d-flex justify-content-end">
                            <span class="ms-2">
                                Bienvenue
                                <a class="logged-user btn-link" href="${UrlRoute.URL_USER}/${userLogged.uuid}">
                                        ${userLogged.nickname}
                                </a>
                            </span>
                        </div>
                        <div class="d-flex justify-content-end">
                            <form method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
                                <button type="submit" tabindex="3" class="btn btn-link" title="Se déconnecter">
                                    <i class="fa-solid fa-right-from-bracket"></i>
                                </button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </div>
                    </div>
                </div>
            </nav>
        </security:authorize>
        <c:if test="${not empty flashMessage.message}">
            <div class="container">
                <div class="alert alert-${flashMessage.type}">
                        ${flashMessage.message}
                </div>
            </div>
        </c:if>