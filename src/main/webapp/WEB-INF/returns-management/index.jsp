<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib prefix="t"
           tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="styles">
        <style>
            tbody tr {
                cursor: pointer;
            }
        </style>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script>
          $('table tr')
            .on('click', function (e) {
              const id = $(this)
                .data('id')
              const url = '${pageContext.request.contextPath}/returns-management/details?id=' + id
              window.location = url
            })
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="card mt-3">
            <div class="card-content">
                <div class="card-body">
                    <h5 class="card-title">
                        Returns Management
                    </h5>
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Customer Name</th>
                                <th scope="col">Created Date</th>
                                <th scope="col">Transporter Unit</th>
                                <th scope="col">Total Cost</th>
                                <th scope="col">Status</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${dataList}"
                                       var="item">
                                <tr data-id="${item.id}">
                                    <th scope="row">${item.id}</th>
                                    <td>${item.customerName}</td>
                                    <td>${item.createdDate.toString()}</td>
                                    <td>${item.transportingUnit}</td>
                                    <td>$${item.totalCost}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.status == 'PENDING'}">
                                                <span class="badge badge-warning">${item.status}</span>
                                            </c:when>
                                            <c:when test="${item.status == 'SHIPPING'}">
                                                <span class="badge badge-info">${item.status}</span>
                                            </c:when>
                                            <c:when test="${item.status == 'FAILED'}">
                                                <span class="badge badge-danger">${item.status}</span>
                                            </c:when>
                                            <c:when test="${item.status == 'PENDING'}">
                                                <span class="badge badge-success">${item.status}</span>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/returns-management/delete?id=${item.id}"
                                           class="btn btn-sm btn-light">
                                            <i class="icon-lock"></i> Delete
                                        </a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/returns-management/change_status?id=${item.id}"
                                           class="btn btn-sm btn-light">
                                            <i class="icon-lock"></i> Change Status
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




