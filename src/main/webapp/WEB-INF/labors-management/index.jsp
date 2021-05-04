<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@taglib prefix="t"
          tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="styles">
        <style>
            .btn-details {
                border-radius: 50%;
                padding: 6px 11px;
            }

            tbody tr {
                cursor: pointer;
            }
        </style>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script>
          $('table tr')
            .on('click', function (e) {
              console.log($(this)
                .data('id'));
            })
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="card mt-3">
            <div class="card-content">
                <div class="card-body">
                    <h5 class="card-title">
                        Labors Management
                        <a href="${pageContext.request.contextPath}/labors-management/create"
                           class="btn btn-sm btn-dark float-right">
                            <i class="icon-lock"></i> Add
                        </a>
                    </h5>
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Name</th>
                                <th scope="col">Phone Number</th>
                                <th scope="col">Role</th>
                                <th scope="col">Transporting Unit</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${dataList}"
                                       var="item">
                                <tr data-id="${item.id}">
                                    <th scope="row">${item.id}</th>
                                    <td>${item.name}</td>
                                    <td>${item.phoneNumber}</td>
                                    <td>${item.role}</td>
                                    <td>${item.transportingUnit}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/labors-management/update?id=${item.id}"
                                           class="btn btn-sm btn-light">
                                            <i class="icon-lock"></i> Edit
                                        </a>
                                        <a href="${pageContext.request.contextPath}/labors-management/delete?id=${item.id}"
                                           class="btn btn-sm btn-light">
                                            <i class="icon-lock"></i> Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>