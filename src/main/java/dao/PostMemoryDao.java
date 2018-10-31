//package dao;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//import javax.annotation.PostConstruct;
//
//import model.Post;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class PostMemoryDao implements PostDao {
//
//    private Long counter = 1L;
//
//    private List<Post> postStore = new ArrayList<>();
//
//    @PostConstruct
//    public void init() {
//        postStore.add(new Post(counter++, "Post 1", "Post 1 content"));
//        postStore.add(new Post(counter++, "Post 2", "Post 2 content"));
//    }
//
//    @Override
//    public Post save(Post post) {
//        if (post.getId() != null) {
//            delete(post.getId());
//        } else {
//            post.setId(counter++);
//        }
//        postStore.add(post);
//
//        return post;
//    }
//
//    @Override
//    public List<Post> findAll() {
//        return Collections.unmodifiableList(postStore);
//    }
//
//    @Override
//    public void delete(Long postId) {
//        postStore = postStore.stream()
//                .filter(p -> !postId.equals(p.getId()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Post findById(Long postId) {
//        return postStore.stream()
//                .filter(p -> postId.equals(p.getId()))
//                .findFirst()
//                .orElseThrow(() -> new IllegalStateException("can't find: " + postId));
//    }
//}
