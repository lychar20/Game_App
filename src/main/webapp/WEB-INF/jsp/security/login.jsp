<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>

<html>
<head>
  <title>${title}</title>
  <link href="${contextPath}/css/main.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
  <script type="text/javascript" src="${contextPath}/js/main.js"></script>
  <script type="text/javascript" src="${contextPath}/js/lib/bootstrap/bootstrap.js"></script>
</head>

<body>
  <div class="container-login">
    <div class="login"></div>
    <form method="POST" action="${UrlRoute.URL_LOGIN}" class="form-signin">
      <h1 class="text-center">Connexion</h1>
      <div class="form-group ${error != null ? 'has-error' : ''}">
        <span>${message}</span>
        <input name="username" type="text" class="form-control" placeholder="Username"
               autofocus="true"/>
        <input name="password" type="password" class="form-control" placeholder="Password"/>
        <p class="invalid-feedback">${error}</p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary" type="submit">Se connecter</button>
        <div class="text-center">
          <a href="${UrlRoute.URL_REGISTER}" class="btn-link">
            Créer un compte
          </a>
        </div>
      </div>
    </form>
  </div>
  </body>
</html>