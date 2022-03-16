package ru.job4j.forum.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repositories.PostRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
public class EditController {
    @Autowired
    private PostRepository postRepository;

    @PostMapping("/create")
    public String create(Model model) {
        Post post = Post.of((String) model.getAttribute("postName"));
        post.setId(Integer.parseInt((String) model.getAttribute("postId")));
        post.setDescription((String) model.getAttribute("postDescript"));
        post.setCreated((Calendar) model.getAttribute("postCreated"));
        postRepository.save(post);
        return "redirect:/index";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
        Post post = postRepository.findById(id).get();
        if (post == null) {
            return null;
        }
        model.addAttribute("post", post);
        return "edit";
    }

    @GetMapping("/posts")
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> getPost(@PathVariable int id) {
        return postRepository.findById(id);
    }

    @GetMapping("/posts/{id}")
    public String setPost(Model model, @PathVariable int id) {
        Post post = Post.of((String) model.getAttribute("postName"));
        post.setId(Integer.parseInt((String) model.getAttribute("postId")));
        post.setDescription((String) model.getAttribute("postDescript"));
        post.setCreated((Calendar) model.getAttribute("postCreated"));
        postRepository.save(post);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable int id, Model model) {
        postRepository.deleteById(id);
        return "redirect:/index";
    }
}
