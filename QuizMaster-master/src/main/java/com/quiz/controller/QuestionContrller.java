package com.quiz.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.Model.Question;
import com.quiz.service.quizService;


@RestController
@RequestMapping("/api/quizzes")
public class QuestionContrller {

    @Autowired
    private quizService quizService;

    // Get all questions
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(quizService.getAllQuestions());
    }

    // Get questions by category
    @GetMapping("/questions/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(quizService.getQuestionsByCategory(category));
    }

    // Submit quiz and get result
    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitQuiz(@RequestBody List<Map<String, String>> answers) {
        Map<String, Object> result = quizService.calculateResult(answers);
        return ResponseEntity.ok(result);
    }

}
