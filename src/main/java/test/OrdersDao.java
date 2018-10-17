package test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrdersDao {

    public static String URL = "jdbc:hsqldb:mem:localhost";
    private DataSource db = ServletDataSource.getDataSource();

    public List<Order> getOrderNumberList() {

        LinkedList<Order> ordersList = new LinkedList<>();

        String sql = "Select * from orders LEFT JOIN ordersRows ON orders.id = ordersRows.id";

        try (Connection conn = db.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();

            List<Long> listOfIdsUsed = new ArrayList<>();


            while (resultSet.next()) {
                Order order = getOrderById(String.valueOf(resultSet.getLong(1)));

                if (!listOfIdsUsed.contains(order.getId())) {
                        ordersList.add(order);
                        listOfIdsUsed.add(order.getId());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordersList.isEmpty() ? null : ordersList;

    }

    public Order getOrderById(String id) {

        long id1 = Long.valueOf(id);

        String sql = "Select * from orders LEFT JOIN ordersRows ON orders.id = ordersRows.id WHERE orders.id = (?)";
        Order order = new Order();

        try (Connection conn = db.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id1);

            ResultSet resultSet = stmt.executeQuery();

            List<OrderRow> listOfOrderRows = new ArrayList<>();


            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
                order.setOrderNumber(resultSet.getString("orderNumber"));

                OrderRow orderRow = new OrderRow();
                orderRow.setItemName(resultSet.getString("itemName"));
                orderRow.setQuantity(resultSet.getInt("quantity"));
                orderRow.setPrice(resultSet.getInt("price"));

                if (resultSet.getString("itemName") != null) {
                    listOfOrderRows.add(orderRow);
                }


            }

            if (listOfOrderRows.isEmpty()) {
                order.setOrderRows(null);
            } else {
                order.setOrderRows(listOfOrderRows);
            }

            return order;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;


    }

    public void deleteById(String id) {

        long id1 = Long.parseLong(id);

        String sql = "Delete from orders where orders.id = (?)";
        String sql2 = "Delete from ordersRows where ordersRows.id = (?)";

        try (Connection conn = db.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id1);
            stmt.executeUpdate();

            stmt = conn.prepareStatement(sql2);
            stmt.setLong(1, id1);
            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order insertOrder(Order order) {
//        System.out.println("I GET THIS OBJECT " + order);

        String sql = "INSERT into orders (orderNumber) values (?)";
        String sql2 = "INSERT into ordersRows (id, itemName, quantity, price) values (?, ?, ?, ?)";

        try (Connection conn = db.getConnection()) {

            //FIRST INSERTION
            PreparedStatement pr = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pr.setString(1, order.getOrderNumber());
            pr.executeUpdate();

            ResultSet rs = pr.getGeneratedKeys();

            if (rs.next()) {
                order.setId(rs.getLong(1));
            }

            //SECOND INSERTION
            pr = conn.prepareStatement(sql2);

            if (order.getOrderRows() != null) {

                for (OrderRow listOfOrderRowsObject : order.getOrderRows()) {
                    pr.setLong(1, order.getId());
                    pr.setString(2, listOfOrderRowsObject.getItemName());
                    pr.setLong(3, listOfOrderRowsObject.getQuantity());
                    pr.setLong(4, listOfOrderRowsObject.getPrice());
                    pr.executeUpdate();
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;

    }
}

