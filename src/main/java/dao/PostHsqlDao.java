package dao;

import model.Post;
import model.PostRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostHsqlDao implements PostDao {

    @Autowired
    private JdbcTemplate template;

    @Override
    public Post save(Post post) {
        //WORKS
        String sql = "INSERT into orders (orderNumber) values (?)";
        String sql2 = "INSERT into ordersRows (id, itemName, quantity, price) values (?, ?, ?, ?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, post.getOrderNumber());
            return ps;
        }, holder);

        post.setId(holder.getKey().longValue());

        if (post.getOrderRows() != null) {

            template.update(conn -> {
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                conn.setAutoCommit(false);

                for (PostRow listOfOrderRowsObject : post.getOrderRows()) {
                    ps2.setLong(1, post.getId());
                    ps2.setString(2, listOfOrderRowsObject.getItemName());
                    ps2.setLong(3, listOfOrderRowsObject.getQuantity());
                    ps2.setLong(4, listOfOrderRowsObject.getPrice());
                    ps2.addBatch();
                }

                ps2.executeBatch();
                conn.commit();
                return ps2;
            });
        }

        return post;
    }

    @Override
    public List<Post> findAll() {
        //TODO
        String sql = "Select * from orders LEFT JOIN ordersRows ON orders.id = ordersRows.id";

        List<Post> listToRestuen = new ArrayList<>();

        template.query(sql, (rs, rowNum) -> {

            System.out.println(rs.getString(1));
            while (rs.next()) {
                Post post = findById(rs.getLong(1));
                if (checkIfInList(listToRestuen, post)) {
                    listToRestuen.add(post);
                }
            }
            return listToRestuen;
        });

        return listToRestuen;
    }

    private boolean checkIfInList(List<Post> listik, Post post) {

        for (Post aListik : listik) {

            if (aListik.getId().equals(post.getId())) {
                return false;
            }
        }
        return true;
    }


    @Override
    public Post findById(Long id) {

        String sql = "Select * from orders LEFT JOIN ordersRows ON orders.id = ordersRows.id WHERE orders.id = (?)";

        return template.queryForObject(sql, new Object[]{id}, (resultSet, rowNum) -> {
            Post order = new Post();

            List<PostRow> listOfOrderRows = new ArrayList<>();

            order.setId(resultSet.getLong(1));
            order.setOrderNumber(resultSet.getString("orderNumber"));
            PostRow orderRow1 = new PostRow();
            orderRow1.setItemName(resultSet.getString("itemName"));
            orderRow1.setQuantity(resultSet.getInt("quantity"));
            orderRow1.setPrice(resultSet.getInt("price"));

            if (resultSet.getString("itemName") != null) {
                listOfOrderRows.add(orderRow1);
            }


            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
                order.setOrderNumber(resultSet.getString("orderNumber"));
                PostRow orderRow = new PostRow();
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
        });

    }

    @Override
    public void delete(Long id) {
        //DONE
        String sql = "delete from orders where id = ?";
        String sql2 = "Delete from ordersRows where ordersRows.id = (?)";

        template.update(sql, id);
        template.update(sql2, id);
    }


}
