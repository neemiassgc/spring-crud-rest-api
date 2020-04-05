package web.spring.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.spring.crud.database.UserBind;
import web.spring.crud.entity.User;

import java.util.Map;

@Controller
public final class MainPageController implements RestAdapter {

    @Autowired
    private UserBind userBind;

    @Override
    @GetMapping(path = "/")
    public String get(Model model) {
        model.addAttribute("usersList", userBind.getAllUsers());
        return "index";
    }

    @Override
    @ResponseBody
    @PostMapping(path = "/createUser", consumes = "application/json")
    public ResponseEntity<String> post(@RequestBody User user) {
        String responseMsg = "";

        try {
            userBind.createUser(user);
            responseMsg = "ok";
        }
        catch(Exception error) {
            responseMsg = error.getMessage();
        }
        finally {
            return ResponseEntity
                    .ok()
                    .header("Content-Type", "text/plain")
                    .body(responseMsg);
        }
    }

    @Override
    @ResponseBody
    @PutMapping(path = "/editUser", consumes = "application/json")
    public ResponseEntity<String> put(@RequestBody Map<String, Object> payload) {
        String responseMsg = "";
        User newUser = new User();

        newUser.setId(new Integer((String) payload.get("id")));
        newUser.setUsername((String) payload.get("username"));
        newUser.setPhone((String) payload.get("phone"));
        newUser.setEmail((String) payload.get("email"));
        newUser.setCountry((String) payload.get("country"));

        try {
            userBind.updateUser(newUser);

            responseMsg = "ok";
        }
        catch(Exception error) {
            responseMsg = error.getMessage();
        }
        finally {
            return ResponseEntity
                    .ok()
                    .header("Content-Type", "text/plain")
                    .body(responseMsg);
        }
    }

    @Override
    @ResponseBody
    @DeleteMapping(path = "/deleteUser")
    public ResponseEntity<String> delete(@RequestParam(value = "id", defaultValue = "-1") int id) {
        String responseMsg = "";

        try {
            if(id == -1) {
                throw new Exception("Id is invalid");
            }

            userBind.deleteUser(id);
            responseMsg = "ok";
        }
        catch(Exception error) {
            responseMsg = error.getMessage();
        }
        finally {
            return ResponseEntity
                    .ok()
                    .header("Content-Type", "text/plain")
                    .body(responseMsg);
        }
    }
}
