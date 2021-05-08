<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t"
           tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="action"
       scope="request"
       value='${requestScope["javax.servlet.forward.servlet_path"].endsWith("create")
       ? "create"
       : "update".concat("?id=").concat(param.id)}' />

<t:template>
    <form action="${pageContext.request.contextPath}/inventories-management/${action}"
          method="POST">
        <section class="card mt-3">
            <div class="card-content">
                <div class="card-body">
                    <h5 class="card-title">
                        Inventory Form
                        <span class="float-right text-success">${msg}</span>
                    </h5>
                    <hr />


                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="id">Id</label>
                            <c:if test="${id_errmsg != null}">
                                -
                                <span class="text-danger">${id_errmsg}</span>
                            </c:if>
                            <input type="text"
                                   class="form-control form-control-rounded"
                                   placeholder="not editable"
                                   id="id"
                                   name="id"
                                   value="${data.id}"
                                   readonly />
                        </div>


                        <div class="form-group col-md-6">
                            <label for="name">Name</label>
                            <c:if test="${name_errmsg != null}">
                                -
                                <span class="text-danger">${name_errmsg}</span>
                            </c:if>
                            <input type="text"
                                   class="form-control form-control-rounded"
                                   placeholder="enter name"
                                   id="name"
                                   name="name"
                                   value="${data.name}" />
                        </div>



                        <div class="form-group col-md-6">
                            <label for="email">Email</label>
                            <c:if test="${email_errmsg != null}">
                                -
                                <span class="text-danger">${email_errmsg}</span>
                            </c:if>
                            <input type="email"
                                   class="form-control form-control-rounded"
                                   placeholder="enter email"
                                   id="email"
                                   name="email"
                                   value="${data.email}" />
                        </div>

                        <div class="form-group col-md-6">
                            <label for="password">Password</label>
                            <c:if test="${password_errmsg != null}">
                                -
                                <span class="text-danger">${password_errmsg}</span>
                            </c:if>
                            <input type="password"
                                   class="form-control form-control-rounded"
                                   placeholder="enter password"
                                   id="password"
                                   name="password"
                                   value="${data.password}" />
                        </div>
                    </div>

                </div>
            </div>
        </section>

        <section class="card mt-3">
            <div class="card-content">
                <div class="card-body">
                    <h5 class="card-title">Job</h5>
                    <hr />

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="roleId">Role</label>
                            <c:if test="${roleId_errmsg != null}">
                                -
                                <span class="text-danger">${roleId_errmsg}</span>
                            </c:if>
                            <select id="roleId"
                                    name="roleId"
                                    class="form-control">
                                <c:forEach items="${roles}"
                                           var="item">
                                    <option value="${item.id}"
                                        ${item.id == data.roleId ? "selected" : ""}>${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="transportingUnitId">
                                Transporting Unit
                            </label>
                            <c:if test="${transportingUnitId_errmsg != null}">
                                -
                                <span class="text-danger">${transportingUnitId_errmsg}</span>
                            </c:if>
                            <select id="transportingUnitId"
                                    name="transportingUnitId"
                                    class="form-control">
                                <c:forEach items="${transportingUnits}"
                                           var="item">
                                    <option value="${item.id}"
                                        ${item.id == data.transportingUnitId ? "selected" : ""}>${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="card mt-3">
            <div class="card-content">
                <div class="card-body">
                    <h5 class="card-title"></h5>
                    <hr />
                    <div class="form-group text-right">
                        <a href="${pageContext.request.contextPath}/labors-management"
                           class="btn btn-light btn-round px-5">
                            <i class="icon-lock"></i> Back to list
                        </a>
                        <button type="submit"
                                class="btn btn-light btn-round px-5">
                            <i class="icon-lock"></i> Save
                        </button>
                    </div>
                </div>
            </div>
        </section>
    </form>
</t:template>