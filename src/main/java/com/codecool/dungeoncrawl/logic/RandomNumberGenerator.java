package com.codecool.dungeoncrawl.logic;

import java.util.Random;

public class RandomNumberGenerator {
    Random random = new Random();
    public int GenerateRandom(int length) {
        int randomNumber = 0;
        for (int i = 1; i <= length; i++) {
            int digit = random.nextInt(10);
            randomNumber = randomNumber * 10 + digit;
        }
        return randomNumber;
    }
}
