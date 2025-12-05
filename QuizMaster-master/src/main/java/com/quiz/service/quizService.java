package com.quiz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.Model.Question;
import com.quiz.repo.QuizRepo;

@Service
public class quizService {
	@Autowired
	QuizRepo quizRepo;

	public List<Question> getAllQuestions() {
        return quizRepo.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        List<Question> all = quizRepo.findAll();
        List<Question> filtered = new ArrayList<>();
        for (Question q : all) {
            if (q.getCategory().equalsIgnoreCase(category)) {
                filtered.add(q);
            }
        }
        return filtered;
    }

    public Map<String, Object> calculateResult(List<Map<String, String>> answers) {
        List<Question> allQuestions = quizRepo.findAll();
        int correctCount = 0;

        for (Map<String, String> ans : answers) {
            Integer qId = Integer.parseInt(ans.get("questionId"));
            String selected = ans.get("selectedOption");

            Optional<Question> qOpt = allQuestions.stream().filter(q -> q.getId().equals(qId)).findFirst();
            if (qOpt.isPresent()) {
                Question q = qOpt.get();
                if (q.getRightAnswer().equalsIgnoreCase(selected)) {
                    correctCount++;
                }
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalQuestions", allQuestions.size());
        result.put("correctAnswers", correctCount);
        result.put("wrongAnswers", allQuestions.size() - correctCount);
        result.put("score", (correctCount * 100) / allQuestions.size());

        return result;
    }
}
