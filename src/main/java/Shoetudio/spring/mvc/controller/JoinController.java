package Shoetudio.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    @GetMapping("/join/agree")
    public String agree() {
        return "agree";
    }

    @GetMapping("/join/joinme")
    public String joinme() {
        return "joinme";
    }

    @GetMapping("/join/joinok")
    public String joinok() {
        return "joinok";
    }

}
