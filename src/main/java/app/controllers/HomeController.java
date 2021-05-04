package app.controllers;

import app.helpers.ItemForChart;
import app.models.dao.ForecastingDao;
import app.utils.InventoryUtil;
import app.utils.OrderUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet("/home")
public class HomeController extends BaseController {
    private OrderUtil orderUtil;
    private InventoryUtil inventoryUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        inventoryUtil = new InventoryUtil();
        orderUtil = new OrderUtil();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        var itemsSum = orderUtil.getBoughtItemsSum();

        var itemsForChart = itemsSum.stream()
            .map(s -> new ItemForChart(s.getCreatedDate(), s.getSum()))
            .collect(Collectors.toMap(
                ItemForChart::getCreatedDate,
                Function.identity(),
                (left, right) -> {
                    left.setSum(left.getSum() + right.getSum());
                    return left;
                }
            )).values();
        req.setAttribute("itemsForChart", itemsForChart);


        var dates = itemsSum.stream()
            .map(ForecastingDao::getCreatedDate)
            .distinct()
            .collect(Collectors.toList());
        req.setAttribute("dates", dates);

        var tags = itemsSum.stream()
            .map(ForecastingDao::splitTaggy)
            .flatMap(Arrays::stream)
            .distinct()
            .collect(Collectors.toList());

        var trendingItems = inventoryUtil.getByTags(tags);
        req.setAttribute("trendingItems", trendingItems);

        loadView(req, res, "dashboard.jsp");
    }
}
