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
    <title>Supply Chain Management System</title>

    <!--favicon-->
    <link rel="icon"
          href="${pageContext.request.contextPath}/images/favicon.ico"
          type="image/x-icon">
    <!-- simplebar CSS-->
    <link href="${pageContext.request.contextPath}/plugins/simplebar/css/simplebar.css"
          rel="stylesheet" />
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
    <!-- Sidebar CSS-->
    <link href="${pageContext.request.contextPath}/css/sidebar-menu.css"
          rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/plugins/select2/css/select2.min.css"
          rel="stylesheet" />
    <!-- Custom Style-->
    <link href="${pageContext.request.contextPath}/css/app-style.css"
          rel="stylesheet" />

    <jsp:invoke fragment="styles" />
</head>

<body class="bg-theme bg-theme2">

<!-- Start wrapper-->
<div id="wrapper">

    <!--Start sidebar-wrapper-->
    <div id="sidebar-wrapper"
         data-simplebar=""
         data-simplebar-auto-hide="true">
        <div class="brand-logo">
            <a href="${pageContext.request.contextPath}">
                <img src="${pageContext.request.contextPath}/images/logo-icon.png"
                     class="logo-icon"
                     alt="logo icon">
                <h5 class="logo-text">Supply Chain 2db2db</h5>
            </a>
        </div>
        <ul class="sidebar-menu do-nicescrol">
            <li class="sidebar-header">MAIN NAVIGATION</li>
            <li>
                <a href="${pageContext.request.contextPath}/home">
                    <i class="zmdi zmdi-view-dashboard"></i>
                    <span>Dashboard</span>
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/roles-management">
                    <i class="zmdi zmdi-invert-colors"></i>
                    <span>Roles</span>
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/transporting-units-management">
                    <i class="zmdi zmdi-format-list-bulleted"></i>
                    <span>Transporting Units</span>
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/labors-management">
                    <i class="zmdi zmdi-grid"></i>
                    <span>Labors</span>
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/warehouses-management">
                    <i class="zmdi zmdi-calendar-check"></i>
                    <span>Warehouses</span>
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/orders-management">
                    <i class="zmdi zmdi-face"></i>
                    <span>Orders</span>
                </a>
            </li>

        </ul>

    </div>
    <!--End sidebar-wrapper-->

    <!--Start topbar header-->
    <header class="topbar-nav">
        <nav class="navbar navbar-expand fixed-top">
            <ul class="navbar-nav mr-auto align-items-center">
                <li class="nav-item">
                    <a class="nav-link toggle-menu"
                       href="javascript:void(0)">
                        <i class="icon-menu menu-icon"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <form class="search-bar">
                        <input type="text"
                               class="form-control"
                               placeholder="not implemented" />
                        <a href="javascript:void(0)"><i class="icon-magnifier"></i></a>
                    </form>
                </li>
            </ul>

            <ul class="navbar-nav align-items-center right-nav-link">
                <li class="nav-item">
                    <a class="nav-link dropdown-toggle dropdown-toggle-nocaret"
                       data-toggle="dropdown"
                       href="#">
                        <span class="user-profile">
                            <img src="https://via.placeholder.com/110x110"
                                 class="img-circle"
                                 alt="user avatar"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li class="dropdown-item user-details">
                            <a href="${pageContext.request.contextPath}/profile">
                                <div class="media">
                                    <div class="avatar">
                                        <img class="align-self-start mr-3"
                                             src="https://via.placeholder.com/110x110"
                                             alt="user avatar">
                                    </div>
                                    <div class="media-body">
                                        <h6 class="mt-2 user-title">${user_name}</h6>
                                        <p class="user-subtitle">${user_email}</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="dropdown-divider"></li>
                        <li class="dropdown-item">
                            <form id="logoutForm"
                                  method="post"
                                  action="${pageContext.request.contextPath}/logout">
                                <a href="javascript:void(0)"
                                   onclick="document.getElementById('logoutForm').submit();">
                                    <i class="icon-power mr-2"></i> Logout
                                </a>
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
    </header>
    <!--End topbar header-->

    <div class="clearfix"></div>

    <div class="content-wrapper">
        <div class="container-fluid">

            <!--Start Dashboard Content-->

            <jsp:doBody />

            <!--End Dashboard Content-->

            <!--start overlay-->
            <div class="overlay toggle-menu"></div>
            <!--end overlay-->

        </div>
        <!-- End container-fluid-->

    </div>
    <!--End content-wrapper-->

    <!--Start Back To Top Button-->
    <a href="javaScript:void();"
       class="back-to-top"><i class="fa fa-angle-double-up"></i>
    </a>
    <!--End Back To Top Button-->

    <!--Start footer-->
    <footer class="footer">
        <div class="container">
            <div class="text-center">
                Copyright © 2021 dio1d912gd.
            </div>
        </div>
    </footer>
    <!--End footer-->

</div>
<!--End wrapper-->

<!-- Bootstrap core JavaScript-->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!-- simplebar js -->
<script src="${pageContext.request.contextPath}/plugins/simplebar/js/simplebar.js"></script>
<!-- sidebar-menu js -->
<script src="${pageContext.request.contextPath}/js/sidebar-menu.js"></script>
<!-- Custom scripts -->
<script src="${pageContext.request.contextPath}/js/app-script.js"></script>
<!-- Moment js -->
<script src="${pageContext.request.contextPath}/plugins/moment.min.js"></script>
<!-- Chart js -->
<script src="${pageContext.request.contextPath}/plugins/Chart.js/Chart.min.js"></script>

<script src="${pageContext.request.contextPath}/plugins/select2/js/select2.min.js"></script>

<jsp:invoke fragment="scripts" />

</body>

</html>