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
                                Item #${data.id}
                            </h5>
                            <p class="font-weight-normal">
                                Name: ${data.name}
                            </p>
                        </div>
                        <div class="col-md-6 font-weight-normal text-right">
                            <image src="/images/image/${data.thumbnailUrl}"/>
                        </div>
                    </div>
                </div>

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Source</th>
                        <th scope="col">Price</th>
                        <th scope="col">Qty</th>
                    </tr>
                    </thead>
                    <tbody>

                            <td>${data.source}</td>
                            <td>$${data.price}</td>
                            <td class="text-right">
                                $${item.price * item.quantity}
                            </td>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

    <section class="card mt-3">
        <div class="card-content">
            <div class="card-body">
                <h5 class="card-title">Store</h5>

                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">No.</th>
                        <th scope="col">Address</th>
                        <th scope="col">Manager</th>
                        <th scope="col">Quantity</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach stores="${storeDetails}"
                               var="store">
                        <tr>
                            <td>${store.id}</td>
                            <td>${store.address}</td>
                            <td>${store.manager}</td>
                            <td>${store.quantity}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </section>
</t:template>

