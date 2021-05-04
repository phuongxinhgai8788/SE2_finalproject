<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t"
           tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="scripts">
        <script>
          $(document).ready(e => {
            const ctx = document.getElementById('theChart').getContext('2d')

            let start = new Date(),
                end   = new Date()
            start.setDate(start.getDate() - 15);
            start.setHours(0, 0, 0, 0);

            const myChart = new Chart(
              ctx,
              {
                type: 'line',
                data: {
                  labels: [
                    <c:forEach var="item" items="${dates}">
                    '${item.toString()}',
                    </c:forEach>
                  ],
                  datasets: [{
                    data: [
                      <c:forEach var="item" items="${itemsForChart}">
                      {
                        t: '${item.createdDate.toString()}',
                        y: ${item.sum}
                      },
                      </c:forEach>
                    ],
                    backgroundColor: '#fff',
                    borderColor: "transparent",
                    pointRadius: "0",
                    borderWidth: 3
                  }]
                },
                options: {
                  scales: {
                    xAxes: [{
                      type: 'time',
                      time: {
                        min: start,
                        max: end,
                        unit: "day"
                      }
                    }]
                  },
                  legend: {
                    display: false
                  },
                },
              })
          })
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="card mt-3">
            <div class="card-header">Site Traffic</div>
            <div class="card-body">
                <ul class="list-inline">
                    <li class="list-inline-item">
                        <i class="fa fa-circle mr-2 text-white"></i>
                        Products bought in a period of time
                    </li>
                </ul>

                <canvas id="theChart"></canvas>
            </div>

            <div class="row m-0 row-group text-center border-top border-light-3">
                <c:forEach items="${trendingItems}"
                           var="item">
                    <div class="col-12 col-lg-4">
                        <div class="p-3">
                            <h5 class="mb-0">
                                <a href="link-to-item">${item.name}</a>
                            </h5>
                            <small class="mb-0">$${item.price}
                                <span data-for="${item.id}">
                                    <i class="fa fa-arrow-up"></i>
                                </span>
                                <script>
                                  document.querySelector('[data-for="${item.id}"]').innerHTML +=
                                    Math.floor((Math.random() * 10) + 1) + ' new orders'
                                </script>
                            </small>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </jsp:body>
</t:template>
