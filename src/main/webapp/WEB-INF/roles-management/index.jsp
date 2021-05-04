<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t"
           tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <div class="card mt-3">
        <div class="card-content">
            <div class="card-body">
                <h5 class="card-title">
                    Roles Management
                    <a href="${pageContext.request.contextPath}/roles-management/create"
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
                            <th scope="col"
                                class="text-info"># of Labors
                            </th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${dataList}"
                                   var="item">
                            <tr>
                                <th scope="row">${item.id}</th>
                                <td>${item.name}</td>
                                <td>${item.laborsNum}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/roles-management/update?id=${item.id}"
                                       class="btn btn-sm btn-light">
                                        <i class="icon-lock"></i> Edit
                                    </a>
                                    <a href="${pageContext.request.contextPath}/roles-management/delete?id=${item.id}"
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
</t:template>
