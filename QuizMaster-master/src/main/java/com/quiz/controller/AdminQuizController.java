package com.quiz.controller;

	import com.quiz.Model.Question;
	import com.quiz.repo.QuizRepo;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;
	import java.util.*;

	@RestController
	@RequestMapping("/api/admin")
	@CrossOrigin(origins = "*")
	public class AdminQuizController {

	    @Autowired
	    private QuizRepo quizRepo;

	    // ✅ Get all questions
	    @GetMapping("/questions")
	    public ResponseEntity<List<Question>> getAllQuestions() {
	        return ResponseEntity.ok(quizRepo.findAll());
	    }

	    // ✅ Add question
	    @PostMapping("/add")
	    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
	        return ResponseEntity.ok(quizRepo.save(question));
	    }

	    // ✅ Update question
	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> updateQuestion(@PathVariable Integer id, @RequestBody Question updated) {
	        Optional<Question> opt = quizRepo.findById(id);
	        if (opt.isEmpty()) return ResponseEntity.status(404).body("❌ Question not found");

	        Question q = opt.get();
	        q.setQuestiontitle(updated.getQuestiontitle());
	        q.setOption1(updated.getOption1());
	        q.setOption2(updated.getOption2());
	        q.setOption3(updated.getOption3());
	        q.setOption4(updated.getOption4());
	        q.setRightAnswer(updated.getRightAnswer());
	        q.setDifficultylevel(updated.getDifficultylevel());
	        q.setCategory(updated.getCategory());
	        quizRepo.save(q);
	        return ResponseEntity.ok("✅ Question updated successfully!");
	    }

	    // ✅ Delete question
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
	        if (!quizRepo.existsById(id)) {
	            return ResponseEntity.status(404).body("❌ Question not found");
	        }
	        quizRepo.deleteById(id);
	        return ResponseEntity.ok("✅ Question deleted successfully!");
	    }
	}


