package com.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	@GetMapping("/")
    public String home() {
        return "redirect:/Home.html";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "redirect:/admin.html";
    }

    @GetMapping("/quiz")
    public String quizPage() {
        return "redirect:/index.html";
    }
}
