package com.theworkshop.randomnumbergenerator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class RandomNumberController {

    @GetMapping("/generateRandomNumbers")
    public List<Integer> generateRandomNumbers(
            @RequestParam("count") int count,
            @RequestParam("min") int min,
            @RequestParam("max") int max) {
            List<Integer> randomNumbers = new ArrayList<>();

            if (min >= max) {
                throw new IllegalArgumentException("Invalid interval: min must be less than max");
            }

            Random random = new Random();
            for (int i = 0; i < count; i++) {
                randomNumbers.add(random.nextInt(max - min + 1) + min);
            }
            return randomNumbers;
    }
}
