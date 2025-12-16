package com.example.demo16._2._5;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Controller
@RestController
public class APIController {


    List<User> users = new ArrayList<>();



    //1 creating user
    //curl -X POST localhost:8080/users -H "Content-Type: application/json" -d "{ \"username\": \"Andl4l\", \"password\": \"pass\", \"age\": \"4\"}"
    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody User new_user){
        for (int i = 0; i < users.size(); i++) {
            if(new_user.equals(users.get(i))){
                return new ResponseEntity<>("Resource with this UseR already exists.", HttpStatus.CONFLICT);
            }
        }
        users.add(new_user);
        return ResponseEntity.ok().build();
    }

    //2 get user by ID
    // curl -X GET localhost:8080/users/3
    @GetMapping("/users/{index}")
    public ResponseEntity<User> getUser_by_ID(@PathVariable("index") Integer id){
        if (id<0 || id > users.size() - 1){
            return ResponseEntity.notFound().build();
        }
        else {
            User user_with_null_password = new User();
            user_with_null_password.setAge(users.get(id).getAge());
            user_with_null_password.setName(users.get(id).getName());
            return ResponseEntity.ok(user_with_null_password);
        }
    }

    //3
    // curl -X DELETE localhost:8080/users/2
    @DeleteMapping("/users/{index}")
    public ResponseEntity<Void> deleteUser_by_ID(@PathVariable("index") Integer id){
        if (id<0 || id > users.size() - 1){
            return ResponseEntity.notFound().build();
        }
        else {
            users.remove(id);
            return ResponseEntity.ok().build();
        }
    }

    //4
    //curl -X PUT localhost:8080/users/2 -H "Content-Type: application/json" -d "{ \"username\": \"Andl4l\", \"password\": \"pass\", \"age\": \"4\"}"
    @PutMapping("/users/{index}")
    public ResponseEntity<Void> updateUser_by_ID(@PathVariable("index") Integer id,
                                                 @RequestBody User new_new_user){
        if (id<0 || id > users.size() - 1){
            return ResponseEntity.notFound().build();
        }
        else {
            User user1 = new_new_user;
            users.set(id, user1);
            return ResponseEntity.ok().build();
        }
    }

    //5
    // curl -X DELETE localhost:8080/posts/2
    @DeleteMapping("/posts/{index}")
    public ResponseEntity<Void> deletePost_by_ID(@PathVariable("index") Integer id){
        posts.remove(id);
        return ResponseEntity.ok().build();
    }

    //6
    // curl -X DELETE localhost:8080/posts
    @DeleteMapping("/posts")
    public ResponseEntity<Void> delete_all_Posts(@PathVariable("index") Integer id){
        posts.clear();
        return ResponseEntity.ok().build();
    }

    //7
    // curl -X GET localhost:8080/posts/count
    @GetMapping("/posts/count")
    public ResponseEntity<Integer> get_Number_of_Posts(){
        return ResponseEntity.ok(posts.size());
    }


    //8
    // curl -X GET localhost:8080/posts/author/vladimir
    @GetMapping("/posts/author/{author}")
    public ResponseEntity<List<Post>> get_Number_of_Posts(@RequestBody String author){
        LinkedList<Post> authors_posts= new LinkedList<>();
        for (int i = 0; i < posts.size(); i++) {
            if(posts.get(i).getAuthor() == author){
                authors_posts.add(posts.get(i));
            }
        }
        return ResponseEntity.ok(authors_posts);
    }

    //на пятерку






}
