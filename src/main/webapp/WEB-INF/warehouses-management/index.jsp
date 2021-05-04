<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib prefix="t"
           tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <div class="card mt-3">
        <div class="card-content">
            <div class="card-body">
                <h5 class="card-title">
                    Warehouses Management
                    <a href="${pageContext.request.contextPath}/warehouses-management/create"
                       class="btn btn-sm btn-dark float-right">
                        <i class="icon-lock"></i> Add
                    </a>
                </h5>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Address</th>
                            <th scope="col">Manager</th>
                            <th scope="col"># of Items</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${dataList}"
                                   var="item">
                            <tr data-id="${item.id}">
                                <th scope="row">${item.id}</th>
                                <td>${item.address}</td>
                                <td>${item.managerName}</td>
                                <td class="text-success">${item.itemsNum}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/warehouses-management/update?id=${item.id}"
                                       class="btn btn-sm btn-light">
                                        <i class="icon-lock"></i> Edit
                                    </a>
                                    <a href="${pageContext.request.contextPath}/warehouses-management/delete?id=${item.id}"
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