package com.codecadamy.unquotenuonulio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;
    ArrayList<Question> questions;
    String color_string = "#FFFD4D3F";
    int myColor = Color.parseColor(color_string);

    // TODO 3-A: Declare View member variables


    ImageView questionImageView;
    TextView questionTextView;
    TextView questionsRemainingNumber;
    TextView questionsRemainingText;
    Button answer0;
    Button answer1;
    Button answer2;
    Button answer3;
    Button submitAnswer;
    String color_onclick = "#FF810E05";
    int colorOnClick = Color.parseColor(color_onclick);
    ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO 2-G: Show app icon in ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);
        setContentView(R.layout.activity_main);

        // TODO 3-B: assign View member variables

        questionImageView = findViewById(R.id.iv_main_question_image);
        questionTextView = findViewById(R.id.tv_main_question_title);
        questionsRemainingNumber = findViewById(R.id.tv_main_questions_remaining_count);
        questionsRemainingText = findViewById(R.id.tv_main_questions_remaining);
        answer0 = findViewById(R.id.btn_main_answer_0);
        answer1 = findViewById(R.id.btn_main_answer_1);
        answer2 = findViewById(R.id.btn_main_answer_2);
        answer3 = findViewById(R.id.btn_main_answer_3);
        submitAnswer = findViewById(R.id.btn_main_submit_answer);

        // TODO 4-E: set onClickListener for each answer Button
        answer0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(0);


            }
        });
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(1);
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(2);
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(3);
            }
        });

        // TODO 5-A: set onClickListener for the submit answer Button
        submitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSubmission();
            }
        });
        startNewGame();
    }

    // TODO 3-F: displayQuestion(Question question) {...}
    void displayQuestion(Question questions) {

// use int color to set Color

        questionImageView.setImageResource(questions.imageId);
        questionTextView.setText(questions.questionText);
        answer0.setText(questions.answer0);
        answer0.setBackgroundTintList(ColorStateList.valueOf(myColor));
        answer1.setText(questions.answer1);
        answer1.setBackgroundTintList(ColorStateList.valueOf(myColor));
        answer2.setText(questions.answer2);
        answer2.setBackgroundTintList(ColorStateList.valueOf(myColor));
        answer3.setText(questions.answer3);
        answer3.setBackgroundTintList(ColorStateList.valueOf(myColor));
    }

    // TODO 3-C: displayQuestionsRemaining(int questionRemaining) {...}
    void displayQuestionsRemaining(int questionsRemaining) {
        questionsRemainingNumber.setText(String.valueOf(questionsRemaining));
    }

    // TODO 4-A: onAnswerSelected(int answerSelected) {...}
    void onAnswerSelected(int answerSelected) {
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.playerAnswer = answerSelected;

            switch (answerSelected) {
                case 0:
                    answer0.setBackgroundTintList(ColorStateList.valueOf(colorOnClick));
                    answer1.setBackgroundTintList(ColorStateList.valueOf(myColor));
                    answer2.setBackgroundTintList(ColorStateList.valueOf(myColor));
                    answer3.setBackgroundTintList(ColorStateList.valueOf(myColor));

                    break;
                case 1:
                    answer1.setBackgroundTintList(ColorStateList.valueOf(colorOnClick));
                    answer0.setBackgroundTintList(ColorStateList.valueOf(myColor));
                    answer2.setBackgroundTintList(ColorStateList.valueOf(myColor));
                    answer3.setBackgroundTintList(ColorStateList.valueOf(myColor));
                    break;
                case 2:
                    answer2.setBackgroundTintList(ColorStateList.valueOf(colorOnClick));
                    answer0.setBackgroundTintList(ColorStateList.valueOf(myColor));
                    answer1.setBackgroundTintList(ColorStateList.valueOf(myColor));
                    answer3.setBackgroundTintList(ColorStateList.valueOf(myColor));
                    break;
                case 3:
                    answer3.setBackgroundTintList(ColorStateList.valueOf(colorOnClick));
                    answer0.setBackgroundTintList(ColorStateList.valueOf(myColor));
                    answer1.setBackgroundTintList(ColorStateList.valueOf(myColor));
                    answer2.setBackgroundTintList(ColorStateList.valueOf(myColor));
                    break;
            }
        }
    /*void onAnswerSelected(int answerSelected) {
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.playerAnswer = answerSelected;
        if (answerSelected == currentQuestion.correctAnswer) {
            switch (answerSelected) {
                case 0:
                    answer0.setText("✔ " + currentQuestion.answer0);
                    answer0.getBackground().setColorFilter(answer0.getContext().getResources().getColor(R.color.black), PorterDuff.Mode.MULTIPLY);

                    break;
                case 1:
                    answer1.setText("✔ " + currentQuestion.answer1);
                    break;
                case 2:
                    answer2.setText("✔ " + currentQuestion.answer2);
                    break;
                case 3:
                    answer3.setText("✔ " + currentQuestion.answer3);
                    break;
            }
        } else {
            // Toast.makeText(getApplicationContext(), "Wrong answer!", Toast.LENGTH_SHORT).show();

            // Snackbar.make(questionImageView, "Wrong answer! Try again.", Snackbar.LENGTH_SHORT).setDuration(500).show();
            displayQuestion(getCurrentQuestion());

        }
    } */

    void onAnswerSubmission() {


        Question currentQuestion = getCurrentQuestion();
if (currentQuestion.playerAnswer != -1) {
    if (currentQuestion.isCorrect()) {
        totalCorrect = totalCorrect + 1;
    }
    questions.remove(currentQuestion);

    // TODO 3-D.i: Uncomment the line below after implementing displayQuestionsRemaining(int)
    displayQuestionsRemaining(questions.size());

    if (questions.size() == 0) {
        String gameOverMessage = getGameOverMessage(totalCorrect, totalQuestions);

        // TODO 5-D: Show a popup instead
        AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        gameOverDialogBuilder.setCancelable(false);
        gameOverDialogBuilder.setTitle("Game Over!");
        gameOverDialogBuilder.setMessage(gameOverMessage);
        gameOverDialogBuilder.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startNewGame();
            }
        });
        gameOverDialogBuilder.create().show();


    } else {
        chooseNewQuestion();

        // TODO 3-H.i: uncomment after implementing displayQuestion(Question)
        displayQuestion(getCurrentQuestion());
    }
} else {
     Toast.makeText(getApplicationContext(), "Select an answer before submitting!", Toast.LENGTH_SHORT).show();;
}
    }

    void startNewGame() {
        questions = new ArrayList<>();

        // TODO 2-H: Provide actual drawables for each of these questions!
        Question question0 = new Question(R.drawable.img_quote_0, "Pretty good advice, and perhaps a scientist did say it...Who actually did?", "Albert Einstein", "Isaac Newton", "Rita Mae Brown", "Rosalind Franklin", 2);
        Question question1 = new Question(R.drawable.img_quote_1, "Was honest Abe honestly quoted? Who autored this pithy bit of wisdom?", "Edward Stieglitz", "Maya Angelou", "Abraham Lincoln", "Ralph Waldo Emerson", 0);
        Question question2 = new Question(R.drawable.img_quote_2, "Easy advice to read, difficult advice to follow - who actually said it?", "Martin Luther King Jr.", "Mother Teresa", "Fred Rogers", "Oprah Winfrey", 1);
        Question question3 = new Question(R.drawable.img_quote_3, "Insanely inspiring, insanely incorrect (maybe). Who is the true source of this inspiration?", "Nelson Mandela", "Harriet Tubman", "Mahatma Gandhi", "Nicholas Klein", 3);
        Question question4 = new Question(R.drawable.img_quote_4, "A peace worth striving for - who actually reminded us of this?", "Malala Yousafzai", "Martin Luther King Jr.", "Liu Xiaobo", "Dalai Lama", 1);
        Question question5 = new Question(R.drawable.img_quote_5, "Unfortunately, true - but did marilyn Monroe convey it or did someone else?", "Laurel Thatcher Ulrich", "Eleanor Roosevelt", "Marilyn Monroe", "Queen Victoria", 0);

        Question question6 = new Question(R.drawable.img_quote_6,
                "Here's the truth, Will Smith did say this, but in which movie?",
                "Independence Day",
                "Bad Boys",
                "Men In Black",
                "The Pursuit of Happyness",
                2);
        Question question7 = new Question(R.drawable.img_quote_7,
                "Which TV funny gal actually quipped this 1-liner?",
                "Ellen Degeneres",
                "Amy Poehler",
                "Betty White",
                "Tina Fay",
                3);
        Question question8 = new Question(R.drawable.img_quote_8,
                "This mayor won't get my vote - but did he actually give this piece of advice? And if not, who did?",
                "Forest Gump, Forest Gump",
                "Dory, Finding Nemo",
                "Esther Williams",
                "The Mayor, Jaws",
                1);
        Question question9 = new Question(R.drawable.img_quote_9,
                "Her heart will go on, but whose heart is it?",
                "Whitney Houston",
                "Diana Ross",
                "Celine Dion",
                "Mariah Carey",
                0);
        Question question10 = new Question(R.drawable.img_quote_10,
                "He's the king of something alright - to whom does this line belong to?",
                "Tony Montana, Scarface",
                "Joker, The Dark Knight",
                "Lex Luthor, Batman v Superman",
                "Jack, Titanic",
                3);
        Question question11 = new Question(R.drawable.img_quote_11,
                "Is GREY synonymous for WISE? If so, maybe Gandalf did preach this advice. if not, Who did?",
                "Yoda, Star Wars",
                "Gandalf",
                "Dumbledore",
                "Uncle Ben, Spider-man",
                0);
        Question question12 = new Question(R.drawable.img_quote_12,
                "Houston, we have a problem with this quote - which space-traveler does this catch-phrase actually belong to?",
                "Han Solo, Star Wars",
                "Captain Kirk, Star Trek",
                "Buzz Lightyear, Toy Story",
                "Jim Lovell, Apollo 13",
                2);



        questions.add(question0);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);

        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);
        questions.add(question10);
        questions.add(question11);
        questions.add(question12);
        for (int i = 0; i < 7; i++) {
            chooseNewQuestion();
            questions.remove(currentQuestionIndex);
        }

        totalCorrect = 0;
        totalQuestions = questions.size();

        Question firstQuestion = chooseNewQuestion();


        // TODO 3-D.ii: Uncomment the line below after implementing displayQuestionsRemaining(int)
        displayQuestionsRemaining(questions.size());

        // TODO 3-H.ii: Uncomment after implementing displayQuestion(Question)
        displayQuestion(firstQuestion);
    }

    Question chooseNewQuestion() {
        int newQuestionIndex = generateRandomNumber(questions.size());
        currentQuestionIndex = newQuestionIndex;
        return questions.get(currentQuestionIndex);


    }

    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    Question getCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        return currentQuestion;
    }

    String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalQuestions + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }

}

