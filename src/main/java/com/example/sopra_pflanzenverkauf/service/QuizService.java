package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Question;
import com.example.sopra_pflanzenverkauf.entity.Quiz;
import com.example.sopra_pflanzenverkauf.entity.Answer;
import com.example.sopra_pflanzenverkauf.repository.QuizRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public Quiz getQuiz() {
        return quizRepository.findAll().stream().findFirst().orElse(null);
    }

    public void saveQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public int calculateScore(Map<Long, Long> userAnswers) {
        Quiz quiz = getQuiz();
        int score = 0;
        for (Question question : quiz.getQuestions()) {
            Long selectedAnswerId = userAnswers.get(question.getId());
            if (selectedAnswerId != null) {
                boolean correct = question.getAnswers().stream()
                        .filter(a -> a.getId().equals(selectedAnswerId))
                        .findFirst()
                        .map(Answer::isCorrect)
                        .orElse(false);
                if (correct) score++;
            }
        }
        return score;
    }

    @PostConstruct
    public void initializeQuiz() {
        if (quizRepository.count() == 0) {
            Quiz quiz = new Quiz();
            quiz.setTitle("Wissenstest über Pflanzen");

            // Frage 1
            Question q1 = new Question();
            q1.setQuestionText("Was benötigen Pflanzen für die Photosynthese?");
            q1.setQuiz(quiz);

            Answer a1 = new Answer();
            a1.setAnswerText("Sonnenlicht");
            a1.setCorrect(true);
            a1.setQuestion(q1);
            q1.getAnswers().add(a1);

            Answer a2 = new Answer();
            a2.setAnswerText("Dunkelheit");
            a2.setCorrect(false);
            a2.setQuestion(q1);
            q1.getAnswers().add(a2);

            // Frage 2
            Question q2 = new Question();
            q2.setQuestionText("Welcher Teil der Pflanze nimmt Wasser aus dem Boden auf?");
            q2.setQuiz(quiz);

            Answer a3 = new Answer();
            a3.setAnswerText("Wurzeln");
            a3.setCorrect(true);
            a3.setQuestion(q2);
            q2.getAnswers().add(a3);

            Answer a4 = new Answer();
            a4.setAnswerText("Blätter");
            a4.setCorrect(false);
            a4.setQuestion(q2);
            q2.getAnswers().add(a4);

            Answer a5 = new Answer();
            a5.setAnswerText("Stängel");
            a5.setCorrect(false);
            a5.setQuestion(q2);
            q2.getAnswers().add(a5);

            // Frage 3
            Question q3 = new Question();
            q3.setQuestionText("Wie nennt man den Prozess, bei dem Pflanzen Nährstoffe aus Sonnenlicht herstellen?");
            q3.setQuiz(quiz);

            Answer a6 = new Answer();
            a6.setAnswerText("Photosynthese");
            a6.setCorrect(true);
            a6.setQuestion(q3);
            q3.getAnswers().add(a6);

            Answer a7 = new Answer();
            a7.setAnswerText("Respiration");
            a7.setCorrect(false);
            a7.setQuestion(q3);
            q3.getAnswers().add(a7);

            Answer a8 = new Answer();
            a8.setAnswerText("Transpiration");
            a8.setCorrect(false);
            a8.setQuestion(q3);
            q3.getAnswers().add(a8);

            // Frage 4
            Question q4 = new Question();
            q4.setQuestionText("Welche Pflanze ist bekannt für ihre fleischfressenden Eigenschaften?");
            q4.setQuiz(quiz);

            Answer a9 = new Answer();
            a9.setAnswerText("Venusfliegenfalle");
            a9.setCorrect(true);
            a9.setQuestion(q4);
            q4.getAnswers().add(a9);

            Answer a10 = new Answer();
            a10.setAnswerText("Kaktus");
            a10.setCorrect(false);
            a10.setQuestion(q4);
            q4.getAnswers().add(a10);

            Answer a11 = new Answer();
            a11.setAnswerText("Efeu");
            a11.setCorrect(false);
            a11.setQuestion(q4);
            q4.getAnswers().add(a11);

            // Frage 5
            Question q5 = new Question();
            q5.setQuestionText("Welche Pflanze produziert Kaffee?");
            q5.setQuiz(quiz);

            Answer a12 = new Answer();
            a12.setAnswerText("Kaffeepflanze");
            a12.setCorrect(true);
            a12.setQuestion(q5);
            q5.getAnswers().add(a12);

            Answer a13 = new Answer();
            a13.setAnswerText("Teepflanze");
            a13.setCorrect(false);
            a13.setQuestion(q5);
            q5.getAnswers().add(a13);

            Answer a14 = new Answer();
            a14.setAnswerText("Tabakpflanze");
            a14.setCorrect(false);
            a14.setQuestion(q5);
            q5.getAnswers().add(a14);

            // Frage 6
            Question q6 = new Question();
            q6.setQuestionText("Welche Pflanze ist für ihre heilenden Eigenschaften bekannt und wird oft bei Verbrennungen verwendet?");
            q6.setQuiz(quiz);

            Answer a15 = new Answer();
            a15.setAnswerText("Aloe Vera");
            a15.setCorrect(true);
            a15.setQuestion(q6);
            q6.getAnswers().add(a15);

            Answer a16 = new Answer();
            a16.setAnswerText("Lavendel");
            a16.setCorrect(false);
            a16.setQuestion(q6);
            q6.getAnswers().add(a16);

            Answer a17 = new Answer();
            a17.setAnswerText("Minze");
            a17.setCorrect(false);
            a17.setQuestion(q6);
            q6.getAnswers().add(a17);

            // Frage 7
            Question q7 = new Question();
            q7.setQuestionText("Welche Pflanze ist ein wichtiges Getreide und die Grundlage für Brot?");
            q7.setQuiz(quiz);

            Answer a18 = new Answer();
            a18.setAnswerText("Weizen");
            a18.setCorrect(true);
            a18.setQuestion(q7);
            q7.getAnswers().add(a18);

            Answer a19 = new Answer();
            a19.setAnswerText("Reis");
            a19.setCorrect(false);
            a19.setQuestion(q7);
            q7.getAnswers().add(a19);

            Answer a20 = new Answer();
            a20.setAnswerText("Mais");
            a20.setCorrect(false);
            a20.setQuestion(q7);
            q7.getAnswers().add(a20);

            // Frage 8
            Question q8 = new Question();
            q8.setQuestionText("Welche Pflanze ist bekannt für ihren starken Geruch und wird oft in der Küche verwendet?");
            q8.setQuiz(quiz);

            Answer a21 = new Answer();
            a21.setAnswerText("Knoblauch");
            a21.setCorrect(true);
            a21.setQuestion(q8);
            q8.getAnswers().add(a21);

            Answer a22 = new Answer();
            a22.setAnswerText("Basilikum");
            a22.setCorrect(false);
            a22.setQuestion(q8);
            q8.getAnswers().add(a22);

            Answer a23 = new Answer();
            a23.setAnswerText("Petersilie");
            a23.setCorrect(false);
            a23.setQuestion(q8);
            q8.getAnswers().add(a23);

            // Frage 9
            Question q9 = new Question();
            q9.setQuestionText("Welcher Baum produziert Eicheln?");
            q9.setQuiz(quiz);

            Answer a24 = new Answer();
            a24.setAnswerText("Eiche");
            a24.setCorrect(true);
            a24.setQuestion(q9);
            q9.getAnswers().add(a24);

            Answer a25 = new Answer();
            a25.setAnswerText("Ahorn");
            a25.setCorrect(false);
            a25.setQuestion(q9);
            q9.getAnswers().add(a25);

            Answer a26 = new Answer();
            a26.setAnswerText("Birke");
            a26.setCorrect(false);
            a26.setQuestion(q9);
            q9.getAnswers().add(a26);

            // Frage 10
            Question q10 = new Question();
            q10.setQuestionText("Welche Pflanze ist bekannt für ihre beruhigenden Eigenschaften und wird oft als Tee getrunken?");
            q10.setQuiz(quiz);

            Answer a27 = new Answer();
            a27.setAnswerText("Kamille");
            a27.setCorrect(true);
            a27.setQuestion(q10);
            q10.getAnswers().add(a27);

            Answer a28 = new Answer();
            a28.setAnswerText("Rosmarin");
            a28.setCorrect(false);
            a28.setQuestion(q10);
            q10.getAnswers().add(a28);

            Answer a29 = new Answer();
            a29.setAnswerText("Salbei");
            a29.setCorrect(false);
            a29.setQuestion(q10);
            q10.getAnswers().add(a29);

            // Frage 11
            Question q11 = new Question();
            q11.setQuestionText("Welche Pflanze wird oft als 'Zierpflanze' in Häusern gehalten und hat dicke, fleischige Blätter?");
            q11.setQuiz(quiz);

            Answer a30 = new Answer();
            a30.setAnswerText("Sukkulente");
            a30.setCorrect(true);
            a30.setQuestion(q11);
            q11.getAnswers().add(a30);

            Answer a31 = new Answer();
            a31.setAnswerText("Farne");
            a31.setCorrect(false);
            a31.setQuestion(q11);
            q11.getAnswers().add(a31);

            Answer a32 = new Answer();
            a32.setAnswerText("Efeu");
            a32.setCorrect(false);
            a32.setQuestion(q11);
            q11.getAnswers().add(a32);

            // Frage 12
            Question q12 = new Question();
            q12.setQuestionText("Welche Pflanze hat bunte Blüten und wird oft als Symbol der Liebe verwendet?");
            q12.setQuiz(quiz);

            Answer a33 = new Answer();
            a33.setAnswerText("Rose");
            a33.setCorrect(true);
            a33.setQuestion(q12);
            q12.getAnswers().add(a33);

            Answer a34 = new Answer();
            a34.setAnswerText("Tulpe");
            a34.setCorrect(false);
            a34.setQuestion(q12);
            q12.getAnswers().add(a34);

            Answer a35 = new Answer();
            a35.setAnswerText("Lilie");
            a35.setCorrect(false);
            a35.setQuestion(q12);
            q12.getAnswers().add(a35);

            // Frage 13
            Question q13 = new Question();
            q13.setQuestionText("Welche Pflanze ist bekannt für ihre hohen Nährwerte und wird oft als 'Superfood' bezeichnet?");
            q13.setQuiz(quiz);

            Answer a36 = new Answer();
            a36.setAnswerText("Kale (Grünkohl)");
            a36.setCorrect(true);
            a36.setQuestion(q13);
            q13.getAnswers().add(a36);

            Answer a37 = new Answer();
            a37.setAnswerText("Kartoffel");
            a37.setCorrect(false);
            a37.setQuestion(q13);
            q13.getAnswers().add(a37);

            Answer a38 = new Answer();
            a38.setAnswerText("Spinat");
            a38.setCorrect(false);
            a38.setQuestion(q13);
            q13.getAnswers().add(a38);

            // Frage 14
            Question q14 = new Question();
            q14.setQuestionText("Welche Pflanze ist eine wichtige Quelle für Zucker und wird in großen Mengen angebaut?");
            q14.setQuiz(quiz);

            Answer a39 = new Answer();
            a39.setAnswerText("Zuckerrübe");
            a39.setCorrect(true);
            a39.setQuestion(q14);
            q14.getAnswers().add(a39);

            Answer a40 = new Answer();
            a40.setAnswerText("Raps");
            a40.setCorrect(false);
            a40.setQuestion(q14);
            q14.getAnswers().add(a40);

            Answer a41 = new Answer();
            a41.setAnswerText("Gerste");
            a41.setCorrect(false);
            a41.setQuestion(q14);
            q14.getAnswers().add(a41);

            // Frage 15
            Question q15 = new Question();
            q15.setQuestionText("Welche Pflanze ist bekannt für ihre Verwendung in Salaten und hat einen hohen Wassergehalt?");
            q15.setQuiz(quiz);

            Answer a42 = new Answer();
            a42.setAnswerText("Gurke");
            a42.setCorrect(true);
            a42.setQuestion(q15);
            q15.getAnswers().add(a42);

            Answer a43 = new Answer();
            a43.setAnswerText("Paprika");
            a43.setCorrect(false);
            a43.setQuestion(q15);
            q15.getAnswers().add(a43);

            Answer a44 = new Answer();
            a44.setAnswerText("Tomate");
            a44.setCorrect(false);
            a44.setQuestion(q15);
            q15.getAnswers().add(a44);

            // Hinzufügen der Fragen zum Quiz
            quiz.getQuestions().add(q1);
            quiz.getQuestions().add(q2);
            quiz.getQuestions().add(q3);
            quiz.getQuestions().add(q4);
            quiz.getQuestions().add(q5);
            quiz.getQuestions().add(q6);
            quiz.getQuestions().add(q7);
            quiz.getQuestions().add(q8);
            quiz.getQuestions().add(q9);
            quiz.getQuestions().add(q10);
            quiz.getQuestions().add(q11);
            quiz.getQuestions().add(q12);
            quiz.getQuestions().add(q13);
            quiz.getQuestions().add(q14);
            quiz.getQuestions().add(q15);

            // Speichern des Quiz in der Datenbank
            quizRepository.save(quiz);
        }
    }

}
