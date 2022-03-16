package ru.job4j.forum.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repositories.UserRepository;

public class RegControl {
    private final UserRepository userRepository;

    public RegControl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
