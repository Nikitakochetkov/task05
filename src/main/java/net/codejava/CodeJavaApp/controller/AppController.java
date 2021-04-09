package net.codejava.CodeJavaApp.controller;

import net.codejava.CodeJavaApp.entity.Status;
import net.codejava.CodeJavaApp.entity.User;
import net.codejava.CodeJavaApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private UserRepository repo;

    @GetMapping
    public String viewPage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPassword = encoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
        repo.save(user);

        return "register_sucess";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model) {
        List<User> listUsers = repo.findAll();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("users", listUsers);

        return "users";
    }

    @PostMapping(value = "unblock/{someID}")
    public String inBlock(@PathVariable(value = "someID") Long id, ModelMap map) {
        try {
            Optional<User> optionalUser = repo.findById(id);
            User user = optionalUser.isPresent() ? optionalUser.get() : new User();
            user.setStatus(Status.UNBLOCK);
            repo.save(user);
        } catch (Exception e) {
            map.put("error", e.getMessage());
        }
        return "users";
    }

    @PostMapping(value = "block/{someID}")
    public void block(@PathVariable(value = "someID") Long id , ModelMap map) {
        try {
            Optional<User> optionalUser = repo.findById(id);
            User user = optionalUser.isPresent() ? optionalUser.get() : new User();
            user.setStatus(Status.BLOCK);
            repo.save(user);
        } catch (Exception e) {
            map.put("error", e.getMessage());
        }
//        return "redirect:/list_users";
    }

    @DeleteMapping(value = "delete/{id}")
    public String deleteCustomer(@PathVariable String id) {
        repo.deleteById((long) Integer.parseInt(id));
        return "redirect:/list_users";
    }

}

