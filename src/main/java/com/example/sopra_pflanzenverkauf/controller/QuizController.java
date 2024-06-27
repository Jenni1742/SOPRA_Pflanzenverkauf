package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Question;
import com.example.sopra_pflanzenverkauf.entity.Quiz;
import com.example.sopra_pflanzenverkauf.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping
    public String showQuiz(Model model) {
        shuffleQuiz(quizService.getQuiz());
        model.addAttribute("quiz", quizService.getQuiz());
        return "quiz";
    }

    @GetMapping("/result")
    public String showQuizResult(Model model) {
        return "quizResult";
    }

    @PostMapping("/submit")
    public String submitQuiz(@RequestParam Map<String, String> allParams, Model model) {
        Map<Long, Long> userAnswers = allParams.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("_csrf"))
                .filter(entry -> {
                    try {
                        Long.parseLong(entry.getKey());
                        Long.parseLong(entry.getValue());
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .collect(Collectors.toMap(
                        entry -> Long.parseLong(entry.getKey()),
                        entry -> Long.parseLong(entry.getValue())
                ));

        int score = quizService.calculateScore(userAnswers);
        model.addAttribute("score", score);
        return "quizResult";
    }
    private void shuffleQuiz(Quiz quiz) {
        List<Question> questions = quiz.getQuestions();
        Collections.shuffle(questions);
        for (Question question : questions) {
            Collections.shuffle(question.getAnswers());
        }
    }

}
