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
  <f:form method="POST" modelAttribute="userForm" class="form-signin">
    <h1 class=" login text-center form-signin-heading">Créer votre compte</h1>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <f:input type="text" path="nickname" class="form-control" placeholder="Nickname"
                    autofocus="true"/>
        <f:errors path="nickname" cssClass="invalid-feedback"/>
      </div>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <f:input type="text" path="email" class="form-control" placeholder="Email"
                    autofocus="true"/>
<%--        <f:errors path="name" cssClass="invalid-feedback"/>--%>
      </div>
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <f:input type="password" path="password" class="form-control" placeholder="Password"/>
        <f:errors path="password" cssClass="invalid-feedback"/>
      </div>
    <div class="form-group ${status.error ? 'has-error' : ''}">
      <f:input type="date" path="birthAt" class="form-control" placeholder="Date_de_naissance"/>
      <f:errors path="birthAt" cssClass="invalid-feedback"/>
    </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
  </f:form>
</div>

</body>
</html>