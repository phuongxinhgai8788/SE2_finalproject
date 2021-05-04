<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t"
           tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <div class="row mt-3">
        <div class="col-lg-4">
            <div class="card profile-card-2">
                <div class="card-img-block">
                    <img class="img-fluid"
                         src="https://via.placeholder.com/800x500"
                         alt="Card image cap">
                </div>
                <div class="card-body pt-5">
                    <img src="https://via.placeholder.com/110x110"
                         alt="profile-image"
                         class="profile">
                    <h5 class="card-title">${user.name}</h5>
                    <c:if test="${role != null}">
                        <p class="card-text">${role.name}
                            at ${transportingUnit.name}</p>
                    </c:if>
                    <div class="icon-block">
                        <a href="javascript:void(0)">
                            <i class="fa fa-facebook bg-facebook text-white"></i>
                        </a>
                        <a href="javascript:void(0)">
                            <i class="fa fa-twitter bg-twitter text-white"></i>
                        </a>
                        <a href="javascript:void(0)">
                            <i class="fa fa-google-plus bg-google-plus text-white"></i>
                        </a>
                    </div>
                </div>

                <div class="card-body border-top border-light">
                    <p>Some quick example text to build on the card title and
                       make up the bulk of the card's content.</p>
                </div>
            </div>

        </div>

        <div class="col-lg-8">
            <div class="card">
                <div class="card-body">
                    <form method="POST"
                          action="${pageContext.request.contextPath}/profile">
                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">Name</label>
                            <div class="col-lg-9">
                                <input type="text"
                                       id="name"
                                       name="name"
                                       class="form-control form-control-rounded"
                                       value="${user.name}" />
                                <span class="text-danger">${name_errmsg}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">
                                Birth Date
                            </label>
                            <div class="col-lg-9">
                                <input type="date"
                                       id="dateOfBirth"
                                       name="dateOfBirth"
                                       class="form-control form-control-rounded"
                                       value="${user.dateOfBirth}" />
                                <span class="text-danger">${dateOfBirth_errmsg}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">
                                Phone
                            </label>
                            <div class="col-lg-9">
                                <input type="text"
                                       id="phoneNumber"
                                       name="phoneNumber"
                                       class="form-control form-control-rounded"
                                       value="${user.phoneNumber}" />
                                <span class="text-danger">${phoneNumber_errmsg}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">
                                Email
                            </label>
                            <div class="col-lg-9">
                                <input type="text"
                                       class="form-control form-control-rounded"
                                       value="${user.email}"
                                       readonly />
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">
                                Password
                            </label>
                            <div class="col-lg-9">
                                <input type="password"
                                       id="password"
                                       name="password"
                                       class="form-control form-control-rounded" />
                                <span class="text-danger">${pw_password_errmsg}</span>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-3 col-form-label form-control-label">
                                Confirm Password
                            </label>
                            <div class="col-lg-9">
                                <input type="password"
                                       id="passwordConfirm"
                                       name="passwordConfirm"
                                       class="form-control form-control-rounded" />
                                <span class="text-danger">${pw_passwordConfirm_errmsg}</span>
                                <span class="text-danger">${pw_valid_errmsg}</span>
                            </div>
                        </div>

                        <div class="form-group row text-right">
                            <label class="col-lg-3 col-form-label form-control-label"></label>
                            <div class="col-lg-9">
                                <button type="submit"
                                        class="btn btn-primary">
                                    Save Changes
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <tbody>
                            <c:forEach items="${orders}"
                                       var="item">
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/orders-management/details?id=${item.id}">
                                            Order
                                            <strong>#1</strong> at ${item.createdDate}
                                            -
                                            <strong>$${item.totalCost}</strong>
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
    </div>
</t:template>