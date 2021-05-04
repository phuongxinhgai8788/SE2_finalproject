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
    <jsp:attribute name="scripts">
        <script>
          $('#managerId')
            .select2({
              width: '100%',
              templateResult: s => {
                const $el = $(s.element)

                const template = `<div class="media">
                        <div class="media-body">
                          <span>\${s.text}</span>
                        </div>
                      </div>`

                return $(template)
              }
            })
          <c:if test="${action.startsWith('create')}">
          $('#managerId')
            .val(null)
            .trigger('change')
          </c:if>

          $('#btn-remove-item')
            .on('click', function () {
              $(this)
                .closest('tr')
                .remove()
            })

          const $itemRow = $(
            `<tr>
                <td>
                    <select id="wi_itemIds[]"
                            name="wi_itemIds[]"
                            class="form-control form-control-sm select2">
                        <c:forEach items="${inventories}"
                                   var="item">
                            <option value="${item.id}"
                                    data-thumbnail-url="${item.thumbnailUrl}"
                                    data-source-url="${item.source}"
                                    data-price="${item.price}">${item.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <input type="number"
                           id="wi_itemQuantities[]"
                           name="wi_itemQuantities[]"
                           class="form-control form-control-sm"
                           placeholder="enter qty"
                           value="1"/>
                </td>
                <td>
                    <button id="btn-remove-item"
                            class="btn btn-light btn-sm">
                        <i class="zmdi zmdi-minus"></i>
                    </button>
                </td>
            </tr>`)
          $('#btn-add-item')
            .on('click', function (e) {
              e.preventDefault()

              const $theRow = $(this)
                .closest('tr')
              const $newRow = $itemRow.clone();

              const $select2 = $newRow.find('.select2')
              $select2.select2({
                width: '100%',
                templateResult: s => {
                  const $el = $(s.element)

                  const template =
                          `<div class="media">
                                <img src="\${$el.data('thumbnail-url')}" class="mr-2" style="width: 36px; height: 36px;">
                                <div class="media-body">
                                    <p class="mt-0">\${s.text} - \${$el.data('price')}</p>
                                    <a target="_blank" href="\${$el.data('source-url')}" class="btn btn-sm btn-outline-dark">check</a>
                                </div>
                            </div>`
                  return $(template)
                }
              })
              $select2.val(null)
                .trigger('change')

              $newRow.find('#btn-remove-item')
                .on('click', function () {
                  $newRow.remove();
                })

              $newRow.insertBefore($theRow)
            })
        </script>
    </jsp:attribute>

    <jsp:body>
        <form action="${pageContext.request.contextPath}/warehouses-management/${action}"
              method="POST">
            <section class="card mt-3">
                <div class="card-content">
                    <div class="card-body">
                        <h5 class="card-title">
                            Warehouse Form
                            <c:if test="${action.startsWith('update')}">
                                <span class="text-success">(${data.itemsNum} items)</span>
                            </c:if>
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
                                <label for="managerId">
                                    Manager
                                </label>
                                <c:if test="${managerId_errmsg != null}">
                                    -
                                    <span class="text-danger">${managerId_errmsg}</span>
                                </c:if>
                                <select id="managerId"
                                        name="managerId"
                                        class="form-control">
                                    <c:forEach items="${managers}"
                                               var="item">
                                        <option value="${item.id}"
                                            ${item.id == data.managerId ? "selected" : ""}>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="address">Address</label>
                            <c:if test="${address_errmsg != null}">
                                -
                                <span class="text-danger">${address_errmsg}</span>
                            </c:if>
                            <textarea id="address"
                                      name="address"
                                      class="form-control"
                                      rows="1"
                                      placeholder="enter address">${data.address}</textarea>
                        </div>

                    </div>
                </div>
            </section>

            <section class="card mt-3">
                <div class="card-content">
                    <div class="card-body">
                        <h5 class="card-title">
                            Items
                            <c:if test="${items_itemId_errmsg != null}">
                                <span class="text-danger"> - ${items_itemId_errmsg}</span>
                            </c:if>
                            <c:if test="${items_itemQty_errmsg != null}">
                                <span class="text-danger"> - ${items_itemQty_errmsg}</span>
                            </c:if>
                        </h5>

                        <div class="form-group">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th scope="col"
                                        style="width: 40%">Name
                                    </th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${items}"
                                           var="_item">
                                    <tr>
                                        <td>
                                            <select id="wi_itemIds[]"
                                                    name="wi_itemIds[]"
                                                    class="form-control form-control-sm">
                                                <c:forEach items="${inventories}"
                                                           var="item">
                                                    <option value="${item.id}"
                                                            data-thumbnail-url="${item.thumbnailUrl}"
                                                            data-source-url="${item.source}"
                                                            data-price="${item.price}"
                                                        ${item.id == _item.id ? "selected" : ""}>${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="number"
                                                   id="wi_itemQuantities[]"
                                                   name="wi_itemQuantities[]"
                                                   class="form-control form-control-sm"
                                                   placeholder="enter qty"
                                                   value="${_item.quantity}" />
                                        </td>
                                        <td>
                                            <button id="btn-remove-item"
                                                    class="btn btn-light btn-sm">
                                                <i class="zmdi zmdi-minus"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <button id="btn-add-item"
                                                class="btn btn-light btn-sm">
                                            <i class="zmdi zmdi-plus"></i>
                                        </button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </section>

            <div class="card mt-3">
                <div class="card-content">
                    <div class="card-body">
                        <h5 class="card-title"></h5>
                        <hr />

                        <div class="form-group text-right">
                            <a href="${pageContext.request.contextPath}/warehouses-management"
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
            </div>

        </form>
    </jsp:body>
</t:template>

