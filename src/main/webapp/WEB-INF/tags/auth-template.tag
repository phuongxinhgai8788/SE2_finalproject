<%@tag description="asncanwuocao"
       pageEncoding="UTF-8" %>
<%@attribute name="styles"
             fragment="true" %>
<%@attribute name="scripts"
             fragment="true" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible"
          content="IE=edge" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <title>Supply Chain System Login</title>

    <!--favicon-->
    <link rel="icon"
          href="${pageContext.request.contextPath}/images/favicon.ico"
          type="image/x-icon">
    <!-- Bootstrap core CSS-->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
          rel="stylesheet" />
    <!-- animate CSS-->
    <link href="${pageContext.request.contextPath}/css/animate.css"
          rel="stylesheet"
          type="text/css" />
    <!-- Icons CSS-->
    <link href="${pageContext.request.contextPath}/css/icons.css"
          rel="stylesheet"
          type="text/css" />
    <!-- Custom Style-->
    <link href="${pageContext.request.contextPath}/css/app-style.css"
          rel="stylesheet" />

    <jsp:invoke fragment="styles" />
</head>

<body class="bg-theme bg-theme2">

<!-- Start wrapper-->
<div id="wrapper">

    <jsp:doBody />

    <!--Start Back To Top Button-->
    <a href="javaScript:void(0)"
       class="back-to-top"><i class="fa fa-angle-double-up"></i>
    </a>
    <!--End Back To Top Button-->

</div>
<!--wrapper-->

<!-- Bootstrap core JavaScript-->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!-- sidebar-menu js -->
<script src="${pageContext.request.contextPath}/js/sidebar-menu.js"></script>

<!-- Custom scripts -->
<script src="${pageContext.request.contextPath}/js/app-script.js"></script>

<jsp:invoke fragment="scripts" />

</body>

</html>