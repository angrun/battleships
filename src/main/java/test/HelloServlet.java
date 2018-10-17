package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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
        }

        else {
            response.getWriter().print(new ObjectMapper().writeValueAsString(ordersDao.getOrderById(id)));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        String string = Util.asString(req.getInputStream());
        Order order = new ObjectMapper().readValue(string, Order.class);

        Order newOrder = ordersDao.insertOrder(order);
        System.out.println(newOrder);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().print(new ObjectMapper().writeValueAsString(newOrder));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        if (id != null) {
            ordersDao.deleteById(id);
        }

        resp.setHeader("Content-Type", "application/json");


    }
}
