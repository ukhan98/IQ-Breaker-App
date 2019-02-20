package com.example.ali.iqbreaker3;

// This class contains a list of questions

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    // declare list of Question objects
    List<Question> list = new ArrayList<>();
    QuestionDb myDataBaseHelper;

    // method returns number of questions in list
    public int getLength() {
        return list.size();
    }

    // method returns question from list based on list index
    public String getQuestion(int a) {
        return list.get(a).getQuestion();
    }

    // method return a single multiple choice item for question based on list index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4
    // as an argument
    public String getChoice(int index, int num) {
        return list.get(index).getChoice(num - 1);
    }

    //  method returns correct answer for the question based on list index
    public String getCorrectAnswer(int a) {
        return list.get(a).getAnswer();
    }


    public void initQuestions(Context context) {
        myDataBaseHelper = new QuestionDb(context);
        list = myDataBaseHelper.getAllQuestionsList();//get questions/choices/answers from database

        if (list.isEmpty() && UserPrefActivity.isChecked() == false) {//if list is empty, populate database with default questions/choices/answers
            myDataBaseHelper.addInitialQuestion(new Question("Which number should come next in this series? 25,24,22,19,15",
                    new String[]{"14", "5", "30", "10"}, "10"));
            myDataBaseHelper.addInitialQuestion(new Question("At a conference, 12 members shook hands with each other before & after the meeting. How many total number of hand shakes occurred?",
                    new String[]{"100", "132", "145", "144"}, "132"));
            myDataBaseHelper.addInitialQuestion(new Question("The day after the day after tomorrow is four days before Monday. What day is it today?",
                    new String[]{"Friday", "Tuesday", "Wednesday", "Monday"}, "Monday"));
            myDataBaseHelper.addInitialQuestion(new Question("Forest is to tree as tree is to ?",
                    new String[]{"leaf", "branch", "mangrove", "twig"}, "leaf"));
            myDataBaseHelper.addInitialQuestion(new Question("Which one of the five is least like the other four?",
                    new String[]{"Cat", "Snake", "Rat", "Dog"}, "Snake"));
            myDataBaseHelper.addInitialQuestion(new Question("Which number should come next in the series? 1 - 1 - 2 - 3 - 5 - 8 - 13",
                    new String[]{"8", "13", "21", "28"}, "21"));
            myDataBaseHelper.addInitialQuestion(new Question(" Mary, who is sixteen years old, is four times as old as her brother. How old will Mary be when she is twice as old as her brother?",
                    new String[]{"20", "24", "25", "28"}, "24"));
            myDataBaseHelper.addInitialQuestion(new Question("Which one of the numbers does not belong in the following series? 2 - 3 - 6 - 7 - 8 - 14 - 15 - 30",
                    new String[]{"THREE", "SEVEN", "EIGHT", "FIFTEEN"}, "EIGHT"));
            myDataBaseHelper.addInitialQuestion(new Question("Which one of the five choices makes the best comparison? Finger is to Hand as Leaf is to:",
                    new String[]{"Tree", "Branch", "Bark", "Twig"}, "Twig"));
            myDataBaseHelper.addInitialQuestion(new Question("If you rearrange the letters \"CIFAIPC\" you would have the name of a(n):",
                    new String[]{"City", "Animal", "Ocean", "River"}, "Ocean"));
        }


// Hard Questions
        else if (list.isEmpty() && UserPrefActivity.isChecked() == true) {
            myDataBaseHelper.addInitialQuestion(new Question("Mary had a number of cookies. After eating one, she gave half the remainder to her sister. After eating another cookie, she gave half of what was left to her brother. Mary now had only five cookies left. How many cookies did she start with?",
                    new String[]{"11", "22", "23", "45  "}, "23"));
            myDataBaseHelper.addInitialQuestion(new Question("Which one of the five is least like the other four?",
                    new String[]{"Brass", "Copper", "Iron", "Tin"}, "Brass"));
            myDataBaseHelper.addInitialQuestion(new Question("Two people can make 2 bicycles in 2 hours. How many people are needed to make 12 bicycles in 6 hours?",
                    new String[]{"6", "4", "2", "1"}, "4"));
            myDataBaseHelper.addInitialQuestion(new Question("Which one of the following is least like the other four?",
                    new String[]{"Football", "Billiards", "Badminton", "Base"}, "Football"));
            myDataBaseHelper.addInitialQuestion(new Question("If there are three oranges and you take away two, then how many do you have?",
                    new String[]{"One", "Two", "Three", "Five"}, "Two"));
            myDataBaseHelper.addInitialQuestion(new Question("Paul had 21 goats, all but 10 died. How many goats are left alive with Paul?",
                    new String[]{"10", "11", "21", "20"}, "10"));
        }


        if (list.size() == 6 && UserPrefActivity.isChecked() == false) {
            QuestionDb QuestionDbNew = myDataBaseHelper;
            QuestionDbNew.deleteRecordID();
            myDataBaseHelper.addInitialQuestion(new Question("Which number should come next in this series? 25,24,22,19,15",
                    new String[]{"14", "5", "30", "10"}, "10"));
            myDataBaseHelper.addInitialQuestion(new Question("At a conference, 12 members shook hands with each other before & after the meeting. How many total number of hand shakes occurred?",
                    new String[]{"100", "132", "145", "144"}, "132"));
            myDataBaseHelper.addInitialQuestion(new Question("The day after the day after tomorrow is four days before Monday. What day is it today?",
                    new String[]{"Friday", "Tuesday", "Wednesday", "Monday"}, "Monday"));
            myDataBaseHelper.addInitialQuestion(new Question("Forest is to tree as tree is to ?",
                    new String[]{"leaf", "branch", "mangrove", "twig"}, "leaf"));
            myDataBaseHelper.addInitialQuestion(new Question("Which one of the five is least like the other four?",
                    new String[]{"Cat", "Snake", "Rat", "Dog"}, "Snake"));
            myDataBaseHelper.addInitialQuestion(new Question("Which number should come next in the series? 1 - 1 - 2 - 3 - 5 - 8 - 13",
                    new String[]{"8", "13", "21", "28"}, "21"));
            myDataBaseHelper.addInitialQuestion(new Question("Which one of the five is least like the other four?",
                    new String[]{"Brass", "Copper", "Iron", "Tin"}, "Brass"));
            myDataBaseHelper.addInitialQuestion(new Question("Which one of the numbers does not belong in the following series? 2 - 3 - 6 - 7 - 8 - 14 - 15 - 30",
                    new String[]{"THREE", "SEVEN", "EIGHT", "FIFTEEN"}, "EIGHT"));
            myDataBaseHelper.addInitialQuestion(new Question("Which one of the five choices makes the best comparison? Finger is to Hand as Leaf is to:",
                    new String[]{"Tree", "Branch", "Bark", "Twig"}, "Twig"));
            myDataBaseHelper.addInitialQuestion(new Question("If you rearrange the letters \"CIFAIPC\" you would have the name of a(n):",
                    new String[]{"City", "Animal", "Ocean", "River"}, "Ocean"));


        }

        if (list.size() == 10 && UserPrefActivity.isChecked() == true) {
            QuestionDb QuestionDbNew = myDataBaseHelper;
            QuestionDbNew.deleteRecordID();
            myDataBaseHelper.addInitialQuestion(new Question("Mary had a number of cookies. After eating one, she gave half the remainder to her sister. After eating another cookie, she gave half of what was left to her brother. Mary now had only five cookies left. How many cookies did she start with?",
                    new String[]{"11", "22", "23", "45  "}, "23"));
            myDataBaseHelper.addInitialQuestion(new Question("Which one of the five is least like the other four?",
                    new String[]{"Brass", "Copper", "Iron", "Tin"}, "Brass"));
            myDataBaseHelper.addInitialQuestion(new Question("Two people can make 2 bicycles in 2 hours. How many people are needed to make 12 bicycles in 6 hours?",
                    new String[]{"6", "4", "2", "1"}, "4"));
            myDataBaseHelper.addInitialQuestion(new Question("Which one of the following is least like the other four?",
                    new String[]{"Football", "Billiards", "Badminton", "Base"}, "Football"));
            myDataBaseHelper.addInitialQuestion(new Question("If there are three oranges and you take away two, then how many do you have?",
                    new String[]{"One", "Two", "Three", "Five"}, "Two"));
            myDataBaseHelper.addInitialQuestion(new Question("Paul had 21 goats, all but 10 died. How many goats are left alive with Paul?",
                    new String[]{"10", "11", "21", "20"}, "10"));

        }


        list = myDataBaseHelper.getAllQuestionsList();//get list from database again

    }


}