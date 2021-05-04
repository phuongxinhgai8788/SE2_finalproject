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
    <div class="card mt-3">
        <div class="card-content">
            <div class="card-body">
                <h5 class="card-title">
                    Role Form
                    <c:if test="${data.laborsNum != null}">
                        <span class="text-info">(${data.laborsNum} labors)</span>
                    </c:if>
                    <span class="float-right text-success">${msg}</span>
                </h5>
                <hr />

                <form action="${pageContext.request.contextPath}/roles-management/${action}"
                      method="POST">
                    <div class="form-group">
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

                    <div class="form-group">
                        <label for="name">Name</label>
                        <c:if test="${name_errmsg != null}">
                            -
                            <span class="text-danger">${name_errmsg}</span>
                        </c:if>
                        <input type="text"
                               class="form-control form-control-rounded"
                               placeholder="enter role name"
                               id="name"
                               name="name"
                               value="${data.name}" />
                    </div>

                    <div class="form-group text-right">
                        <a href="${pageContext.request.contextPath}/roles-management"
                           class="btn btn-light btn-round px-5">
                            <i class="icon-lock"></i> Back to list
                        </a>
                        <button type="submit"
                                class="btn btn-light btn-round px-5">
                            <i class="icon-lock"></i> Save
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</t:template>