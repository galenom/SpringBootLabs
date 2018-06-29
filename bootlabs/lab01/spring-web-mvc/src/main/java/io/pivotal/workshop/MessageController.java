package io.pivotal.workshop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {

    @RequestMapping("/message")
    public String message(Model model) {
        model.addAttribute("message", "message! ");
        return "message";
    }
}
