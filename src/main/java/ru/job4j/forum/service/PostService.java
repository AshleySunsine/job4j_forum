package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repositories.PostRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository posts) {
        this.postRepository = posts;
    }

    public List<Post> getAll() {
        return (List<Post>) postRepository.findAll();
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public void create(Post post) {
        post.setCreated(Calendar.getInstance());
        postRepository.save(post);
    }

    public Optional<Post> getPost(long id) {
        return postRepository.findById(id);
    }

    public void setPost(Post post) {
        post.setCreated(Calendar.getInstance());
        postRepository.save(post);
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}

