package com.example.ali.iqbreaker3;

import android.content.Context;

import java.util.ArrayList;

public class Fact {
    private String facts;
    private String definitions;

    public Fact(String facts, String definitions) {
        this.facts = facts;
        this.definitions = definitions;
    }

    public String getName() {
        return facts;
    }

    public String getCapital() {
        return definitions;
    }

    public static ArrayList<Fact> getFacts(Context context) {

        ArrayList<Fact> Facts = new ArrayList<>();

        String[] factNames =
                context.getResources().getStringArray(R.array.facts);

        String[] capitals =
                context.getResources().getStringArray(R.array.explanation);
        for (int i = 0; i < factNames.length; i++) {
            Facts.add(new Fact(factNames[i], capitals[i]));
        }
        return Facts;

    }
}
