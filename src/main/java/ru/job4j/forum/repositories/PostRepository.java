package ru.job4j.forum.repositories;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PostRepository {

    private Map<Integer, Post> posts = new HashMap();

    public PostRepository() {
        posts.put(1, Post.of("1"));
        posts.put(2, Post.of("2"));
        posts.put(3, Post.of("3"));
        posts.put(4, Post.of("4"));
        posts.get(1).setId(1);
        posts.get(2).setId(2);
        posts.get(3).setId(3);
        posts.get(4).setId(4);
        posts.get(1).setDescription("discript1");
        posts.get(2).setDescription("discript2");
        posts.get(3).setDescription("discript3");
        posts.get(4).setDescription("discript4");
    }

    public Post save(Post post) {
        posts.putIfAbsent(post.getId(), post);
        return post;
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> findById(int id) {
        return Optional.ofNullable(posts.get(id));
    }

    public void deleteById(int id) {
        posts.remove(id);
    }
}
