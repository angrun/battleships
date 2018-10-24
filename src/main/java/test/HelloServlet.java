package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import test.dao.OrdersDao;
import test.error.Code;
import test.error.ValueError;
import test.order.Order;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("api/orders")
public class HelloServlet extends HttpServlet {
    private OrdersDao ordersDao = new OrdersDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        response.setHeader("Content-Type", "application/json");
        System.out.println(id);

        if (id == null) {
            List<Order> orders = ordersDao.getOrderNumberList();
            response.getWriter().print(new ObjectMapper().writeValueAsString(orders));
        } else {
            response.getWriter().print(new ObjectMapper().writeValueAsString(ordersDao.getOrderById(id)));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        String string = Util.asString(req.getInputStream());
        Order order = new ObjectMapper().readValue(string, Order.class);
        resp.setHeader("Content-Type", "application/json");
        if (order.getOrderNumber().length() < 2) {
            Code code = new Code();
            ValueError valueError = new ValueError();
            valueError.setErrors(Collections.singletonList(code));
            resp.setStatus(400);
            resp.getWriter().print(new ObjectMapper().writeValueAsString(valueError));
        } else {


            Order newOrder = ordersDao.insertOrder(order);
            resp.getWriter().print(new ObjectMapper().writeValueAsString(newOrder));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        if (id != null) {
            ordersDao.deleteById(id);
        } else {
            ordersDao.deleteAllTable();
        }

        resp.setHeader("Content-Type", "application/json");

    }

}
