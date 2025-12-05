package com.quiz.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.Model.Question;

public interface QuizRepo extends JpaRepository<Question, Integer> {

	List<Question> findBycategory(String category);


}
