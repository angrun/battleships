package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/orders")
public class HelloServlet extends HttpServlet {
    private static long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().print("Hello!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String string = Util.asString(req.getInputStream());
        System.out.println(string);

        String[] temporaryArrayString = string.replace(",", ":")
                .replace("}", "")
                .replaceAll("\\s+", "")
                .replace("{", "")
                .split(":");

        ArrayList<String> splitted = new ArrayList<String>(Arrays.asList(temporaryArrayString));

        System.out.println("SPLITTED LIST " + splitted);

        if (splitted.contains("\"id\"")) {
            System.out.println("YES");
            System.out.println(splitted.size());
            splitted.remove(0);
            splitted.remove(0);
        }

        String toReturn = String.format("{ \"id\": %s, %s: %s }", serialVersionUID, splitted.get(0), splitted.get(1));
        serialVersionUID++;

        System.out.println(toReturn);

        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().print(toReturn);

    }
}
