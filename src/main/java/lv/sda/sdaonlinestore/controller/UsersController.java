package lv.sda.sdaonlinestore.controller;

import lv.sda.sdaonlinestore.entity.StoreUser;
import lv.sda.sdaonlinestore.service.StoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    //localhost:8080/users -> POST StoreUser //add user
    //localhost:8080/users -> GET  //List of all users
    //localhost:8080/users PUT/POST StoreUser change
    //localhost:8080/users DELETE

    @Autowired
    private StoreUserService userService;

    @GetMapping
    public List<StoreUser> displayUsers() {
        List<StoreUser> allStoreUsers = userService.getAllStoreUsers();
        return allStoreUsers;
    }

    @GetMapping("/byId") //    /users/byId
    public List<StoreUser> displayUserById(@RequestParam Long userId) {
        StoreUser userById = userService.findById(userId);
        List<StoreUser> lst = new ArrayList<>();
        lst.add(userById);
        return lst;
    }

    @PostMapping //    /users/addOrUpdate
    public List<StoreUser> addUser(@RequestBody StoreUser user) {
        userService.saveOrUpdate(user);
        List<StoreUser> lst = new ArrayList<>();
        lst.add(user);
        return lst;
    }

    @DeleteMapping //    /users/delete
    public String deleteUser(@RequestBody StoreUser user) {
        userService.delete(user);
        return "User deleted";
    }

}

