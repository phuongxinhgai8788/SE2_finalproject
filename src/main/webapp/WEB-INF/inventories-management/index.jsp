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
                        .data('id'))
                    const url = '${pageContext.request.contextPath}/inventories-management/details?id=' + id
                    window.location = url;
                })
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="card mt-3">
            <div class="card-content">
                <div class="card-body">
                    <h5 class="card-title">
                        Inventories Management
                        <a href="${pageContext.request.contextPath}/inventories-management/create"
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
                                <th scope="col">Price</th>
                                <th scope="col">Source</th>
                                <th scope="col">Thumbnail</th>
                                <th scope="col">Tags</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${dataList}"
                                       var="item">
                                <tr data-id="${item.id}">
                                    <th scope="row">${item.id}</th>
                                    <td>${item.name}</td>
                                    <td>${item.price}</td>
                                    <td>${item.source}</td>
                                    <td><img src="images/image/${item.thumbnailUrl}" alt = "Image of product" width="70" height="50"></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/inventories-management/update?id=${item.id}"
                                           class="btn btn-sm btn-light">
                                            <i class="icon-lock"></i> Edit
                                        </a>
                                        <a href="${pageContext.request.contextPath}/inventories-management/delete?id=${item.id}"
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