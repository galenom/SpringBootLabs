package io.pivotal.workshop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {

    @RequestMapping("/message")
    public ModelAndView message() {
        ModelAndView model = new ModelAndView("message");
        model.addObject("message", "Here's a Spring Boot message");
        return model;
    }

}
