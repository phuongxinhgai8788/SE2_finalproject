<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t"
           tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <section class="card mt-3">
        <div class="card-content">
            <div class="card-body">
                <div class="card-title"
                     style="font-size: 14px;">
                    <div class="row">
                        <div class="col-md-6">
                            <h5 class="mb-1 d-flex align-items-center">
                                Order #${data.id}
                                <c:choose>
                                    <c:when test="${data.status == 'PENDING'}">
                                        <span class="badge badge-warning">${data.status}</span>
                                    </c:when>
                                    <c:when test="${data.status == 'SHIPPING'}">
                                        <span class="badge badge-info">${data.status}</span>
                                    </c:when>
                                    <c:when test="${data.status == 'FAILED'}">
                                        <span class="badge badge-danger">${data.status}</span>
                                    </c:when>
                                    <c:when test="${data.status == 'PENDING'}">
                                        <span class="badge badge-success">${data.status}</span>
                                    </c:when>
                                </c:choose>
                            </h5>
                            <p class="font-weight-normal">
                                Address: ${data.address}
                            </p>
                        </div>
                        <div class="col-md-6 font-weight-normal text-right">
                            <p>
                                Created at:
                                <b>${data.createdDate}</b>
                            </p>
                            <p>Phone Number: ${data.phoneNumber}</p>
                        </div>
                    </div>
                </div>

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Product</th>
                        <th scope="col">Price</th>
                        <th scope="col">Qty</th>
                        <th scope="col"
                            class="text-right">
                            Sub-total
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orderDetails}"
                               var="item">
                        <tr>
                            <th scope="row">
                                <a href="${item.source}">${item.name}</a>
                            </th>
                            <td>$${item.price}</td>
                            <td>x${item.quantity}</td>
                            <td class="text-right">
                                $${item.price * item.quantity}
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td></td>
                        <td class="text-right">Additional fee:</td>
                        <td class="text-right">
                            $0
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td class="text-right">Grand total:</td>
                        <td class="text-right">
                            $${data.totalCost}
                        </td>
                    </tr>
                    </tbody>
                </table>

                <div class="block"> ${data.notes} </div>
            </div>
        </div>
    </section>

    <section class="card mt-3">
        <div class="card-content">
            <div class="card-body">
                <h5 class="card-title">Delivery status</h5>

                <table class="table table-hover">
                    <tbody>
                    <c:forEach items="${deliveryDetails}"
                               var="item">
                        <tr>
                            <td>${item.updatedDate}</td>
                            <td>${item.notes}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </section>
</t:template>

