<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t"
           tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="action"
       scope="request"
       value='${requestScope["javax.servlet.forward.servlet_path"].endsWith("create")
       ? "create"
       : "change_status".concat("?id=").concat(param.id)}' />

<t:template>
    <jsp:attribute name="scripts">
    <script>
      let selectedTransportingUnitId = null

      $('#transportingUnitId')
        .select2({width: '100%'})
      $('#transportingUnitId')
        .on('select2:select', function (e) {
          selectedTransportingUnitId = parseInt(e.params.data.id)
          $('#transporterId')
            .val(null)
            .trigger('change')
          $("#transporterId")
            .prop("readonly", false)
        })
      $("#transportingUnitId")
        .prop("readonly", true)

      $('#transporterId')
        .select2({
          placeholder: 'select an unit',
          width: '100%',
          templateResult: s => {
            const $el = $(s.element)

            if (selectedTransportingUnitId !== null
              && $el.data('unit-id') !== selectedTransportingUnitId) {
              return null
            }

            const template =
                    `<div class="media">
                        <div class="media-body">
                          <span>\${s.text}</span>
                        </div>
                      </div>`

            return $(template)
          }
        })

      $('#btn-update-delivery-status')
        .on('click', function (e) {
          e.preventDefault()

          const $icon = $(this)
            .find('.zmdi')
          const $theRow = $(this)
            .closest('tr')
          const $timeCol = $theRow.find('.col-time')
          const $notesCol = $theRow.find('.col-notes')

          if ($icon.hasClass('zmdi-plus')) {
            $timeCol.text('{auto update}')
            $notesCol.append('<input id="odd_notes" name="odd_notes" type="text" class="form-control form-control-sm" />')

            $icon.removeClass('zmdi-plus')
            $icon.addClass('zmdi-minus')
          } else {
            $timeCol.text(null)
            $notesCol.empty()

            $icon.removeClass('zmdi-minus')
            $icon.addClass('zmdi-plus')
          }
        })

      const $itemRow = $(
        `<tr>
                <td>
                    <select id="od_itemIds[]"
                            name="od_itemIds[]"
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
                           id="od_itemPrices[]"
                           name="od_itemPrices[]"
                           class="form-control form-control-sm"
                           placeholder="enter qty"
                           min="1" />
                </td>
                <td>
                    <input type="number"
                           id="od_itemQuantities[]"
                           name="od_itemQuantities[]"
                           class="form-control form-control-sm"
                           placeholder="enter qty"
                           min="1"
                           value="1" />
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
          $select2.on('select2:select', function (e) {
            const $el = $(e.params.data.element)
            const price = $el.data('price')
            $el.closest('tr')
              .find('[id="od_itemPrices[]"]')
              .val(price)
          })

          $newRow.find('#btn-remove-item')
            .on('click', function () {
              $newRow.remove();
            })

          $newRow.insertBefore($theRow)
        })
    </script>
  </jsp:attribute>

    <jsp:body>
        <form action="${pageContext.request.contextPath}/returns-management/${action}"
              method="POST">
            <section class="card mt-3">
                <div class="card-content">
                    <div class="card-body">
                        <h5 class="card-title">

                                Order #${data.id}
                                <input type="hidden"
                                       value="${data.id}">
                                <span class="text-info">(${data.createdDate})</span>


                        </h5>
                        <hr />

                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-6">
                                    <label for="status">Status</label>
                                    <c:if test="${status_errmsg != null}">
                                        -
                                        <span class="text-danger">${status_errmsg}</span>
                                    </c:if>
                                    <select id="status"
                                            name="status"
                                            class="form-control">
                                        <option value="PENDING" ${data.status == "PENDING" ? "selected" : ""}>
                                            PENDING
                                        </option>
                                        <option value="SHIPPING" ${data.status == "SHIPPING" ? "selected" : ""}>
                                            SHIPPING
                                        </option>
                                    </select>
                                </div>

                                <div class="col-md-6">
                                    <label for="phoneNumber">
                                        Phone Number
                                    </label>
                                    <input type="text"
                                           id="phoneNumber"
                                           name="phoneNumber"
                                           class="form-control form-control-rounded"
                                           placeholder="enter phone number"
                                           readonly
                                           value="${data.phoneNumber}" />
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="customerName">Customer Name</label>
                            <input type="text"
                                   id="customerName"
                                   name="customerName"
                                   class="form-control form-control-rounded"
                                   placeholder="enter customer name"
                                   readonly
                                   value="${data.customerName}" />
                        </div>

                        <div class="form-group">
                            <label for="address">Address</label>
                            <textarea id="address"
                                      name="address"
                                      class="form-control"
                                      rows="1"
                                      readonly
                                      placeholder="enter address">${data.address}</textarea>
                        </div>

                        <div class="form-group">
                            <label for="notes">Notes</label>
                            <c:if test="${notes_errmsg != null}">
                                -
                                <span class="text-danger">${notes_errmsg}</span>
                            </c:if>
                            <textarea id="notes"
                                      name="notes"
                                      class="form-control"
                                      rows="1"
                                      placeholder="enter notes">${data.notes}</textarea>
                        </div>

                    </div>
                </div>
            </section>

            <section class="card mt-3">
                <div class="card-content">
                    <div class="card-body">
                        <h5 class="card-title">Transportation</h5>
                        <hr />

                        <div class="form-row">
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

                            <div class="form-group col-md-6">
                                <label for="transporterId">
                                    Transporter
                                </label>
                                <c:if test="${transporterId_errmsg != null}">
                                    -
                                    <span class="text-danger">${transporterId_errmsg}</span>
                                </c:if>
                                <select id="transporterId"
                                        name="transporterId"
                                        class="form-control">
                                    <c:forEach items="${transporters}"
                                               var="item">
                                        <option data-unit-id="${item.id}"
                                                value="${item.id}"
                                            ${item.id == data.transporterId ? "selected" : ""}>${item.name}</option>
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
                        <h5 class="card-title">Details</h5>

                        <c:if test="${items_itemId_errmsg != null}">
                            <span class="text-danger"> - ${items_itemId_errmsg}</span>
                        </c:if>
                        <c:if test="${items_itemPrice_errmsg != null}">
                            <span class="text-danger"> - ${items_itemPrice_errmsg}</span>
                        </c:if>
                        <c:if test="${items_itemQty_errmsg != null}">
                            <span class="text-danger"> - ${items_itemQty_errmsg}</span>
                        </c:if>

                        <div class="form-group">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th scope="col"
                                        style="width: 300px">Item name
                                    </th>
                                    <th scope="col">Price ($)</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${orderDetails}"
                                           var="item">
                                    <tr>
                                        <td>${item.name}</td>
                                        <td>$${item.price}</td>
                                        <td>${item.quantity}</td>
                                        <td></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </section>

            <section class="card mt-3">
                <div class="card-content">
                    <div class="card-body">
                        <h5 class="card-title">
                            Delivery Status
                            <c:if test="${deliveryDetail_errmsg != null}">
                                <span class="text-danger">${deliveryDetail_errmsg}</span>
                            </c:if>
                        </h5>

                        <div class="form-group">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th scope="col">Updated Date</th>
                                    <th scope="col">Notes</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>

                                    <c:forEach items="${deliveryDetails}"
                                               var="item">
                                        <tr>
                                            <td>${item.updatedDate}</td>
                                            <td>${item.notes}</td>
                                            <td></td>
                                        </tr>
                                    </c:forEach>

                                <tr>
                                    <td class="col-time"></td>
                                    <td class="col-notes"></td>
                                    <td>
                                        <button id="btn-update-delivery-status"
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

            <section class="card mt-3">
                <div class="card-content">
                    <div class="card-body">
                        <h5 class="card-title"></h5>
                        <hr />

                        <div class="form-group text-right">
                            <a href="${pageContext.request.contextPath}/returns-management"
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
    </jsp:body>
</t:template>