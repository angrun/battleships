package test.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import test.Report;
import test.ServletDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReportDao {

    public static String URL = "jdbc:hsqldb:mem:localhost";
    private DataSource db = ServletDataSource.getDataSource();

    public int getNumberOfOrders() {

        String sql = "SELECT COUNT(orderNumber) FROM orders";

        try (Connection conn = db.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet r = stmt.executeQuery(sql);

            r.next();
            return r.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;

    }

    public int getFullPrice() {

        String sql = "SELECT price, quantity FROM ordersRows";

        try (Connection conn = db.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet r = stmt.executeQuery(sql);
            long sum = 0;
            while (r.next()) {
                sum += r.getInt(1) * r.getLong(2);
            }
            return (int) sum;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Report getFullReport() {

        System.out.println(getNumberOfOrders() + " " + getFullPrice());
        return new Report(getNumberOfOrders(), getFullPrice());
    }

}
