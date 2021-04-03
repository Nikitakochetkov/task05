package net.codejava.CodeJavaApp.controller;

import net.codejava.CodeJavaApp.entity.User;
import net.codejava.CodeJavaApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        model.addAttribute("user", listUsers.get(0));
        model.addAttribute("users", listUsers);

        return "users";
    }


    @PostMapping(value = "/delete/{someID}")
    public String delete(@PathVariable(value="someID") String id, HttpServletRequest request, ModelMap map) {
        try {
            System.out.println("gtht");
//            if (request.getParameterValues("userId") != null) {
//                for (String userId : request.getParameterValues("userId")) ;
                repo.deleteById((long) Integer.parseInt(id));
//            }
//            return ":users.html";
        } catch (Exception e) {
            map.put("eror", e.getMessage());
            map.put("listUsers", repo.findAll());
        }
        System.out.println("gtht");
        return "redirect:/list_users";
    }


//    @PostMapping("/delete/{userId}")
//    public String deleteUser(@PathVariable("userId") long id, Model model) {
//        User user = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        repo.delete(user);
//
//        return "redirect:/users";
//    }
}

