package test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("api/orders")
public class HelloServlet extends HttpServlet {

    Bank bank = new Bank();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getParameter("id");
        Long id = Long.valueOf(request.getParameter("id"));
        Order order = Bank.orderHash.get(id);

        String json = new ObjectMapper().writeValueAsString(order);

        response.setHeader("Content-Type", "application/json");

        response.getWriter().print(json);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        String string = Util.asString(req.getInputStream());
        Order post = new ObjectMapper().readValue(string, Order.class);

        bank.bankAdd(post);
        String json = new ObjectMapper().writeValueAsString(post);

        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().print(json);

    }
}
