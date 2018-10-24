package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import test.dao.OrdersDao;
import test.dao.ReportDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("api/orders/report")
public class HelloServlet3 extends HttpServlet {

    private ReportDao reportDao = new ReportDao();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().print(new ObjectMapper().writeValueAsString(reportDao.getFullReport()));

    }
}
