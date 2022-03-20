package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.repositories.PostRepositoryMem;
import ru.job4j.forum.store.PostRepository;

@Controller
public class IndexControl {

    private final PostRepository posts;

    public IndexControl(PostRepository posts) {
        this.posts = posts;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", posts.findAll());
        return "index";
    }
}
