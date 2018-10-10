package test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("api/orders")
public class HelloServlet extends HttpServlet {
    private OrdersDao ordersDao = new OrdersDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setHeader("Content-Type", "application/json");
            response.getWriter().print(new ObjectMapper().writeValueAsString(ordersDao.getOrderNumberList()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        String string = Util.asString(req.getInputStream());
        Order order = new ObjectMapper().readValue(string, Order.class);

        try {
            ordersDao.insertOrder(order);
        } catch (SQLException e) {

        }

        String json = new ObjectMapper().writeValueAsString(order);

        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().print(json);
    }

}
