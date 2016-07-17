package com.example;

import java.util.Random;

public class JokeWizard {

    private static final String[] jokeList = {
            "Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all.",
            "I asked God for a bike, but I know God doesn't work that way. So I stole a bike and asked for forgiveness.",
            "A recent study has found that women who carry a little extra weight live longer than the men who mention it.",
            "If you can smile when things go wrong, you have someone in mind to blame.",
            "My wife and I were happy for twenty years. Then we met.",
            "You know you're ugly when it comes to a group picture and they hand you the camera.",
            "Relationships are a lot like algebra. Have you ever looked at your X and wondered Y?",
            "Regular naps prevent old age, especially if you take them while driving.",
            "I hate when I am about to hug someone really sexy and my face hits the mirror.",
            "When my boss asked me who is the stupid one, me or him? I told him everyone knows he doesn't hire stupid people.",
            "I find it ironic that the colors red, white, and blue stand for freedom until they are flashing behind you.",
            "I'm great at multitasking. I can waste time, be unproductive, and procrastinate all at once."
    };

    public String getJoke() {

        int num = 0;

        Random random = new Random();
        num = random.nextInt(jokeList.length);

        if (num < 0 || num >= jokeList.length) {
            num = 0;
        }
        return jokeList[num];


    }

}
