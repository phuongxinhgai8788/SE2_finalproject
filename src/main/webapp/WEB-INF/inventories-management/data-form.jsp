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
          method="POST" enctype ="multipart/form-data">
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
                            <label for="price">Price</label>
                            <c:if test="${price_errmsg != null}">
                                -
                                <span class="text-danger">${price_errmsg}</span>
                            </c:if>
                            <input type="number"
                                   class="form-control form-control-rounded"
                                   placeholder="enter price"
                                   id="price"
                                   name="price"
                                   min="20"
                                   value="${data.price}" />
                        </div>

                        <div class="form-group col-md-6">
                            <label for="source">Source</label>
                            <c:if test="${source_errmsg != null}">
                                -
                                <span class="text-danger">${source_errmsg}</span>
                            </c:if>
                            <input type="text"
                                   class="form-control form-control-rounded"
                                   placeholder="enter source"
                                   id="source"
                                   name="source"
                                   value="${data.source}" />
                        </div>
                        <div class="form-group col-md-6">
                            <label for="tags">Tags</label>
                            <c:if test="${tags_errmsg != null}">
                                -
                                <span class="text-danger">${tags_errmsg}</span>
                            </c:if>
                            <input type="text"
                                   class="form-control form-control-rounded"
                                   placeholder="enter tag"
                                   id="tags"
                                   name="tags"
                                   value="${data.tags}" />
                        </div>
                        <div class="form-group col-md-6">
                            <label for="thumbnailUrl">Choose image</label>
                            <input type="file" class="form-control form-control-rounded" id="thumbnailUrl" onchange="processSelectedFiles(this) value=${data.thumbnailUrl}" >

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
                        <a href="${pageContext.request.contextPath}/inventories-management"
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
    <script>
        function processSelectedFiles(fileInput){
            var files = fileInput.files;
            for (var i = 0; i< files.length;i++){
                ${data.thumbnailUrl}= files[i].name;
            }
        }
    </script>
</t:template>