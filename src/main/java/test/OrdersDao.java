package test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class OrdersDao {

    public static String URL = "jdbc:hsqldb:mem:localhost";
    private DataSource db = ServletDataSource.getDataSource();

    public List<Order> getOrderNumberList() {

        LinkedList<Order> ordersList = new LinkedList<>();

        try (Connection conn = db.getConnection()) {
            Statement stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery("select * from orders");

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setOrderNumber(resultSet.getString("orderNumber"));
                ordersList.add(order);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordersList;
    }

    public Order getOrderById(Long id) {

        String sql = "select id, orderNumber from orders where id  = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pr = conn.prepareStatement(sql)) {

            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("id"));
                order.setOrderNumber(rs.getString("orderNumber"));
                pr.setString(1, order.getOrderNumber());
                pr.executeUpdate();
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order insertOrder(Order order) throws SQLException {

        String sql = "INSERT into orders (orderNumber) values (?)";

        try (Connection conn = db.getConnection()) {

            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, order.getOrderNumber());
            pr.executeUpdate();

            return order;
        }
    }
}

