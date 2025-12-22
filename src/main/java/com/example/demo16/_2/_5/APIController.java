package com.example.demo16._2._5;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


@Controller
@RestController
public class APIController {


    List<User> users = new ArrayList<>();


    //1 creating user
    //curl -X POST localhost:8080/users -H "Content-Type: application/json" -d "{ \"username\": \"Andl4l\", \"password\": \"pass\", \"age\": \"4\"}"
    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody User new_user) {
        for (int i = 0; i < users.size(); i++) {
            if (new_user.equals(users.get(i))) {
                return new ResponseEntity<>("Resource with this UseR already exists.", HttpStatus.CONFLICT);
            }
        }
        users.add(new_user);
        return ResponseEntity.ok().build();
    }

    //2 get user by ID
    // curl -X GET localhost:8080/users/3
    @GetMapping("/users/{index}")
    public ResponseEntity<User> getUser_by_ID(@PathVariable("index") Integer id) {
        if (id < 0 || id > users.size() - 1) {
            return ResponseEntity.notFound().build();
        } else {
            User user_with_null_password = new User();
            user_with_null_password.setAge(users.get(id).getAge());
            user_with_null_password.setName(users.get(id).getName());
            return ResponseEntity.ok(user_with_null_password);
        }
    }

    //3
    // curl -X DELETE localhost:8080/users/2
    @DeleteMapping("/users/{index}")
    public ResponseEntity<Void> deleteUser_by_ID(@PathVariable("index") Integer id) {
        if (id < 0 || id > users.size() - 1) {
            return ResponseEntity.notFound().build();
        } else {
            users.remove(id);
            return ResponseEntity.ok().build();
        }
    }

    //4
    //curl -X PUT localhost:8080/users/2 -H "Content-Type: application/json" -d "{ \"username\": \"Andl4l\", \"password\": \"pass\", \"age\": \"4\"}"
    @PutMapping("/users/{index}")
    public ResponseEntity<Void> updateUser_by_ID(@PathVariable("index") Integer id,
                                                 @RequestBody User new_new_user) {
        if (id < 0 || id > users.size() - 1) {
            return ResponseEntity.notFound().build();
        } else {
            User user1 = new_new_user;
            users.set(id, user1);
            return ResponseEntity.ok().build();
        }
    }

    //на 4

    //1.1
    // curl -X Post localhost:8080/posts/2 -H "Content-Type: application/json" -d "{ \"username\": \"Andl4l\", \"password\": \"pass1\", \"repeatPassword\": \"pass1\", \"age\": \"4\"}"
    @PostMapping("/users")
    public ResponseEntity<String> create_new_user(@RequestBody User_with_repeat_password new_new_user_with_repest_password) {

        if (new_new_user_with_repest_password.getPassword() == new_new_user_with_repest_password.getRepeatPassword()) {
            User user1 = new User(new_new_user_with_repest_password.getAge(), new_new_user_with_repest_password.getName(), new_new_user_with_repest_password.getPassword());
            users.add(user1);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Не сошлось");
        }

    }

    //1.2
    // curl -X Put localhost:8080/posts/2 -H "Content-Type: application/json" -d "{ \"username\": \"Andl4l\", \"password\": \"pass1\", \"repeatPassword\": \"pass1\", \"age\": \"4\"}"
    @PutMapping("/users/{index}")
    public ResponseEntity<String> update_user(@PathVariable("index") Integer id,
                                              @RequestBody User_with_repeat_password new_new_user_with_repest_password) {
        if (id < 0 || id > users.size() - 1) {
            return ResponseEntity.notFound().build();
        } else {
            if (new_new_user_with_repest_password.getPassword() == new_new_user_with_repest_password.getRepeatPassword()) {
                User user1 = new User(new_new_user_with_repest_password.getAge(), new_new_user_with_repest_password.getName(), new_new_user_with_repest_password.getPassword());
                users.set(id, user1);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Не сошлось");
            }
        }
    }

    //2
    // curl -X GET localhost:8080/users/35
    @GetMapping("/users/{age_index}")
    public ResponseEntity<List<User>> get_users_in_a_bound(@PathVariable("age_index") Integer new_age) {
        List<User> users_age_in_a_bound = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if ((users.get(i).getAge() < new_age + 5) && (new_age - 5 < users.get(i).getAge())) {
                users_age_in_a_bound.add(users.get(i));
            }
        }
        return ResponseEntity.ok(users_age_in_a_bound);
    }

    //на 5

    //1,2,3
    // curl -X GET localhost:8080/users/35
    @GetMapping("/users/{age_index}")
    public ResponseEntity<List<User>> get_sorted_users_list(@RequestParam(value = "age", required = true) Integer new_age,
                                                            @RequestParam(value = "sortBy", required = false) String sortBy,
                                                            @RequestParam(value = "direction", required = false, defaultValue = "up") String direction,
                                                            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                            @RequestParam(value = "size", required = false) Integer size) {
        List<User> users_age_in_a_bound = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if ((users.get(i).getAge() < new_age + 5) && (new_age - 5 < users.get(i).getAge())) {
                users_age_in_a_bound.add(users.get(i));
            }
        }
        if (sortBy != null) {
            if ("name".equalsIgnoreCase(sortBy)) {
                    users_age_in_a_bound.sort(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        int dirMultiplier = "up".equalsIgnoreCase(direction) ? 1 : -1;
                        int nameCompare = o1.getName().compareTo(o2.getName());

                        if (nameCompare == 0) {
                            return Integer.compare(o1.getAge(), o2.getAge());
                        }

                        return dirMultiplier * nameCompare;
                    }
                });
            } else if ("age".equalsIgnoreCase(sortBy)) {
                users_age_in_a_bound.sort(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        int dirMultiplier = "up".equalsIgnoreCase(direction) ? 1 : -1;
                        int ageCompare = Integer.compare(o1.getAge(), o2.getAge());

                        if (ageCompare == 0) {
                            return o1.getName().compareTo(o2.getName());
                        }

                        return dirMultiplier * ageCompare;
                    }
                });
            }
        }

        if (size != null && size > 0) {
            int start = page * size;
            int end = Math.min(start + size, users_age_in_a_bound.size());

            if (start >= users_age_in_a_bound.size()) {
                users_age_in_a_bound = new ArrayList<>();
            } else {
                users_age_in_a_bound = users_age_in_a_bound.subList(start, end);
            }
        }

        return ResponseEntity.ok(users_age_in_a_bound);

    }
}
