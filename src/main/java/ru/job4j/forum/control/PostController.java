package ru.job4j.forum.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postRepositoryService;

    @PostMapping("/create")
    public String create(@ModelAttribute Post post) {
        postRepositoryService.create(post);
        return "redirect:/index";
    }

    @GetMapping("/createPost")
    public String createReference() {
        return "create";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable long id, Model model) {
        Post post = postRepositoryService.findById(id).get();
        if (post == null) {
            return null;
        }
        model.addAttribute("post", post);
        return "edit";
    }

    @GetMapping("/posts")
    public List<Post> getAll() {
        return (List<Post>) postRepositoryService.getAll();
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> getPost(@PathVariable long id) {
        return postRepositoryService.findById(id);
    }

    @GetMapping("/setposts/{id}")
    public String setPost(@ModelAttribute Post post) {
        postRepositoryService.setPost(post);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable long id, Model model) {
        postRepositoryService.deletePost(id);
        return "redirect:/index";
    }
}
