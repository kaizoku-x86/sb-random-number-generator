package com.theworkshop.randomnumbergenerator.controller;

import org.springframework.http.httpStatus;
import org.springframework.http.ResponseEntity;
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
        try{
            List<Integer> randomNumbers = new ArrayList<>();

            if (min >= max) {
                throw new IllegalArgumentException("Invalid interval: min must be less than max");
            }

            Random random = new Random();
            for (int i = 0; i < count; i++) {
                randomNumbers.add(random.nextInt(max - min + 1) + min);
            }

            return randomNumbers;
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
}
