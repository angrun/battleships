package test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


@WebServlet("orders/form")
public class HelloServlet2 extends HttpServlet {

    Bank bank = new Bank();


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String string = Util.asString(req.getInputStream());
        System.out.println(string);
        System.out.println(string);
        String[] to_Json = string.split("=");
        System.out.println(Arrays.toString(to_Json));
        Order order = new Order();
        order.setOrderNumber(to_Json[1]);
        bank.bankAdd(order);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().print(Bank.id);


    }
}
