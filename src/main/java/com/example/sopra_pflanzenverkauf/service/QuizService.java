package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Question;
import com.example.sopra_pflanzenverkauf.entity.Quiz;
import com.example.sopra_pflanzenverkauf.entity.Answer;
import com.example.sopra_pflanzenverkauf.repository.QuizRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

            Answer a2 = new Answer();
            a2.setAnswerText("Dunkelheit");
            a2.setCorrect(false);
            a2.setQuestion(q1);

            Answer a3 = new Answer();
            a3.setAnswerText("Kohlendioxid");
            a3.setCorrect(false);
            a3.setQuestion(q1);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers1 = Arrays.asList(a1, a2, a3);
            Collections.shuffle(answers1);
            q1.getAnswers().addAll(answers1);

            // Frage 2
            Question q2 = new Question();
            q2.setQuestionText("Welcher Teil der Pflanze nimmt Wasser aus dem Boden auf?");
            q2.setQuiz(quiz);

            Answer a4 = new Answer();
            a4.setAnswerText("Wurzeln");
            a4.setCorrect(true);
            a4.setQuestion(q2);

            Answer a5 = new Answer();
            a5.setAnswerText("Blätter");
            a5.setCorrect(false);
            a5.setQuestion(q2);

            Answer a6 = new Answer();
            a6.setAnswerText("Stängel");
            a6.setCorrect(false);
            a6.setQuestion(q2);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers2 = Arrays.asList(a4, a5, a6);
            Collections.shuffle(answers2);
            q2.getAnswers().addAll(answers2);

            // Weitere Fragen...

            // Frage 3
            Question q3 = new Question();
            q3.setQuestionText("Wie nennt man den Prozess, bei dem Pflanzen Nährstoffe aus Sonnenlicht herstellen?");
            q3.setQuiz(quiz);

            Answer a7 = new Answer();
            a7.setAnswerText("Photosynthese");
            a7.setCorrect(true);
            a7.setQuestion(q3);

            Answer a8 = new Answer();
            a8.setAnswerText("Respiration");
            a8.setCorrect(false);
            a8.setQuestion(q3);

            Answer a9 = new Answer();
            a9.setAnswerText("Transpiration");
            a9.setCorrect(false);
            a9.setQuestion(q3);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers3 = Arrays.asList(a7, a8, a9);
            Collections.shuffle(answers3);
            q3.getAnswers().addAll(answers3);

            // Frage 4
            Question q4 = new Question();
            q4.setQuestionText("Welche Pflanze ist bekannt für ihre fleischfressenden Eigenschaften?");
            q4.setQuiz(quiz);

            Answer a10 = new Answer();
            a10.setAnswerText("Venusfliegenfalle");
            a10.setCorrect(true);
            a10.setQuestion(q4);

            Answer a11 = new Answer();
            a11.setAnswerText("Kaktus");
            a11.setCorrect(false);
            a11.setQuestion(q4);

            Answer a12 = new Answer();
            a12.setAnswerText("Efeu");
            a12.setCorrect(false);
            a12.setQuestion(q4);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers4 = Arrays.asList(a10, a11, a12);
            Collections.shuffle(answers4);
            q4.getAnswers().addAll(answers4);

            // Frage 5
            Question q5 = new Question();
            q5.setQuestionText("Welche Pflanze produziert Kaffee?");
            q5.setQuiz(quiz);

            Answer a13 = new Answer();
            a13.setAnswerText("Kaffeepflanze");
            a13.setCorrect(true);
            a13.setQuestion(q5);

            Answer a14 = new Answer();
            a14.setAnswerText("Teepflanze");
            a14.setCorrect(false);
            a14.setQuestion(q5);

            Answer a15 = new Answer();
            a15.setAnswerText("Tabakpflanze");
            a15.setCorrect(false);
            a15.setQuestion(q5);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers5 = Arrays.asList(a13, a14, a15);
            Collections.shuffle(answers5);
            q5.getAnswers().addAll(answers5);

            // Frage 6
            Question q6 = new Question();
            q6.setQuestionText("Welche Pflanze ist für ihre heilenden Eigenschaften bekannt und wird oft bei Verbrennungen verwendet?");
            q6.setQuiz(quiz);

            Answer a16 = new Answer();
            a16.setAnswerText("Aloe Vera");
            a16.setCorrect(true);
            a16.setQuestion(q6);

            Answer a17 = new Answer();
            a17.setAnswerText("Lavendel");
            a17.setCorrect(false);
            a17.setQuestion(q6);

            Answer a18 = new Answer();
            a18.setAnswerText("Minze");
            a18.setCorrect(false);
            a18.setQuestion(q6);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers6 = Arrays.asList(a16, a17, a18);
            Collections.shuffle(answers6);
            q6.getAnswers().addAll(answers6);

            // Frage 7
            Question q7 = new Question();
            q7.setQuestionText("Welche Pflanze ist ein wichtiges Getreide und die Grundlage für Brot?");
            q7.setQuiz(quiz);

            Answer a19 = new Answer();
            a19.setAnswerText("Weizen");
            a19.setCorrect(true);
            a19.setQuestion(q7);

            Answer a20 = new Answer();
            a20.setAnswerText("Reis");
            a20.setCorrect(false);
            a20.setQuestion(q7);

            Answer a21 = new Answer();
            a21.setAnswerText("Mais");
            a21.setCorrect(false);
            a21.setQuestion(q7);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers7 = Arrays.asList(a19, a20, a21);
            Collections.shuffle(answers7);
            q7.getAnswers().addAll(answers7);

            // Frage 8
            Question q8 = new Question();
            q8.setQuestionText("Welche Pflanze ist bekannt für ihren starken Geruch und wird oft in der Küche verwendet?");
            q8.setQuiz(quiz);

            Answer a22 = new Answer();
            a22.setAnswerText("Knoblauch");
            a22.setCorrect(true);
            a22.setQuestion(q8);

            Answer a23 = new Answer();
            a23.setAnswerText("Basilikum");
            a23.setCorrect(false);
            a23.setQuestion(q8);

            Answer a24 = new Answer();
            a24.setAnswerText("Petersilie");
            a24.setCorrect(false);
            a24.setQuestion(q8);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers8 = Arrays.asList(a22, a23, a24);
            Collections.shuffle(answers8);
            q8.getAnswers().addAll(answers8);

            // Frage 9
            Question q9 = new Question();
            q9.setQuestionText("Welcher Baum produziert Eicheln?");
            q9.setQuiz(quiz);

            Answer a25 = new Answer();
            a25.setAnswerText("Eiche");
            a25.setCorrect(true);
            a25.setQuestion(q9);

            Answer a26 = new Answer();
            a26.setAnswerText("Ahorn");
            a26.setCorrect(false);
            a26.setQuestion(q9);

            Answer a27 = new Answer();
            a27.setAnswerText("Birke");
            a27.setCorrect(false);
            a27.setQuestion(q9);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers9 = Arrays.asList(a25, a26, a27);
            Collections.shuffle(answers9);
            q9.getAnswers().addAll(answers9);

            // Frage 10
            Question q10 = new Question();
            q10.setQuestionText("Welche Pflanze ist bekannt für ihre beruhigenden Eigenschaften und wird oft als Tee getrunken?");
            q10.setQuiz(quiz);

            Answer a28 = new Answer();
            a28.setAnswerText("Kamille");
            a28.setCorrect(true);
            a28.setQuestion(q10);

            Answer a29 = new Answer();
            a29.setAnswerText("Rosmarin");
            a29.setCorrect(false);
            a29.setQuestion(q10);

            Answer a30 = new Answer();
            a30.setAnswerText("Salbei");
            a30.setCorrect(false);
            a30.setQuestion(q10);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers10 = Arrays.asList(a28, a29, a30);
            Collections.shuffle(answers10);
            q10.getAnswers().addAll(answers10);

            // Frage 11
            Question q11 = new Question();
            q11.setQuestionText("Welche Pflanze wird oft als 'Zierpflanze' in Häusern gehalten und hat dicke, fleischige Blätter?");
            q11.setQuiz(quiz);

            Answer a31 = new Answer();
            a31.setAnswerText("Sukkulente");
            a31.setCorrect(true);
            a31.setQuestion(q11);

            Answer a32 = new Answer();
            a32.setAnswerText("Farne");
            a32.setCorrect(false);
            a32.setQuestion(q11);

            Answer a33 = new Answer();
            a33.setAnswerText("Efeu");
            a33.setCorrect(false);
            a33.setQuestion(q11);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers11 = Arrays.asList(a31, a32, a33);
            Collections.shuffle(answers11);
            q11.getAnswers().addAll(answers11);

            // Frage 12
            Question q12 = new Question();
            q12.setQuestionText("Welche Pflanze hat bunte Blüten und wird oft als Symbol der Liebe verwendet?");
            q12.setQuiz(quiz);

            Answer a34 = new Answer();
            a34.setAnswerText("Rose");
            a34.setCorrect(true);
            a34.setQuestion(q12);

            Answer a35 = new Answer();
            a35.setAnswerText("Tulpe");
            a35.setCorrect(false);
            a35.setQuestion(q12);

            Answer a36 = new Answer();
            a36.setAnswerText("Lilie");
            a36.setCorrect(false);
            a36.setQuestion(q12);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers12 = Arrays.asList(a34, a35, a36);
            Collections.shuffle(answers12);
            q12.getAnswers().addAll(answers12);

            // Frage 13
            Question q13 = new Question();
            q13.setQuestionText("Welche Pflanze ist bekannt für ihre hohen Nährwerte und wird oft als 'Superfood' bezeichnet?");
            q13.setQuiz(quiz);

            Answer a37 = new Answer();
            a37.setAnswerText("Kale (Grünkohl)");
            a37.setCorrect(true);
            a37.setQuestion(q13);

            Answer a38 = new Answer();
            a38.setAnswerText("Kartoffel");
            a38.setCorrect(false);
            a38.setQuestion(q13);

            Answer a39 = new Answer();
            a39.setAnswerText("Spinat");
            a39.setCorrect(false);
            a39.setQuestion(q13);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers13 = Arrays.asList(a37, a38, a39);
            Collections.shuffle(answers13);
            q13.getAnswers().addAll(answers13);

            // Frage 14
            Question q14 = new Question();
            q14.setQuestionText("Welche Pflanze ist eine wichtige Quelle für Zucker und wird in großen Mengen angebaut?");
            q14.setQuiz(quiz);

            Answer a40 = new Answer();
            a40.setAnswerText("Zuckerrübe");
            a40.setCorrect(true);
            a40.setQuestion(q14);

            Answer a41 = new Answer();
            a41.setAnswerText("Raps");
            a41.setCorrect(false);
            a41.setQuestion(q14);

            Answer a42 = new Answer();
            a42.setAnswerText("Gerste");
            a42.setCorrect(false);
            a42.setQuestion(q14);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers14 = Arrays.asList(a40, a41, a42);
            Collections.shuffle(answers14);
            q14.getAnswers().addAll(answers14);

            // Frage 15
            Question q15 = new Question();
            q15.setQuestionText("Welche Pflanze ist bekannt für ihre Verwendung in Salaten und hat einen hohen Wassergehalt?");
            q15.setQuiz(quiz);

            Answer a43 = new Answer();
            a43.setAnswerText("Gurke");
            a43.setCorrect(true);
            a43.setQuestion(q15);

            Answer a44 = new Answer();
            a44.setAnswerText("Paprika");
            a44.setCorrect(false);
            a44.setQuestion(q15);

            Answer a45 = new Answer();
            a45.setAnswerText("Tomate");
            a45.setCorrect(false);
            a45.setQuestion(q15);

            // Zufällige Reihenfolge der Antworten
            List<Answer> answers15 = Arrays.asList(a43, a44, a45);
            Collections.shuffle(answers15);
            q15.getAnswers().addAll(answers15);

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
