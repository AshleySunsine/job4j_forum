package ru.job4j.forum.repositories;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.*;

@Repository
public class PostRepositoryMem {

    private Map<Integer, Post> posts = new HashMap();

    public PostRepositoryMem() {
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
        posts.putIfAbsent((int) post.getId(), post);
        return post;
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> findById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public void deleteById(long id) {
        posts.remove(id);
    }
}
