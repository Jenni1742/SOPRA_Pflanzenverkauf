package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Question;
import com.example.sopra_pflanzenverkauf.entity.Quiz;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.QuizService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showQuiz(Model model) {
        shuffleQuiz(quizService.getQuiz());
        model.addAttribute("quiz", quizService.getQuiz());

        User user = userService.getCurrentUser();
        model.addAttribute("coinCount", user.getPlantCoinCount());

        return "quiz";
    }

    @PostMapping("/submit")
    public String submitQuiz(@RequestParam Map<String, String> allParams, Model model) {
        Map<Long, Long> userAnswers = new HashMap<>();
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            if (!entry.getKey().equals("_csrf")) {
                try {
                    Long questionId = Long.parseLong(entry.getKey());
                    Long answerId = Long.parseLong(entry.getValue());
                    userAnswers.put(questionId, answerId);
                } catch (NumberFormatException e) {
                    // nix
                }
            }
        }
        int score = quizService.calculateScore(userAnswers);
        int earnedCoins = score;

        User user = userService.getCurrentUser();
        int newCoinCount = user.getPlantCoinCount() + earnedCoins;
        user.setPlantCoinCount(newCoinCount);
        userService.updateCoinCount(user);

        model.addAttribute("score", score);
        model.addAttribute("earnedCoins", earnedCoins);
        model.addAttribute("coinCount", newCoinCount);

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