package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.PostDao;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostDao dao;

    //GET
    @GetMapping("orders") //GET
    public List<Post> getPosts() {
        return dao.findAll();
    }

    //GET BY ID
    @GetMapping("orders/{postId}") //GET
    public Post getById(@PathVariable("postId") Long postId) {
        return dao.findById(postId);
    }

    //POST
    @PostMapping("orders")
    public Post save(@RequestBody @Valid Post post) { //Paneb validaatorit automaatselt k'ima
        dao.save(post);
        return post;
    }

    //DELETE
    @DeleteMapping("orders/{postId}")
    public void deletePost(@PathVariable("postId") Long postId) {
        dao.delete(postId);

    }

//    @PostMapping("test")
//    public String getPost(@RequestBody Post post) throws JsonProcessingException {
//        return new ObjectMapper().writeValueAsString(post);
//    }
//    @GetMapping("test")
//    public Anya getPostw()  {
//        return new Anya();
//    }

}