package com.theworkshop.randomnumbergenerator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

@RestController
public class RandomNumberGeneratorController {
    @GetMapping("/healthcheck")
    public ResponseEntity generateRandomNumbers{
        return ResponseEntity.ok()
    }
}

@RestController
public class RandomNumberGeneratorController {

    @GetMapping("/generateRandomNumbers")
    public ResponseEntity generateRandomNumbers(
            @RequestParam("count") String countStr,
            @RequestParam("min") String minStr,
            @RequestParam("max") String maxStr
    ) {
        try {
            int count = Integer.parseInt(countStr);
            int min = Integer.parseInt(minStr);
            int max = Integer.parseInt(maxStr);

            // Validate input values
            if (count <= 0) {
                throw new IllegalArgumentException("Invalid Argment: Count must be greater than zero.");
            }

            if (min >= max) {
                throw new IllegalArgumentException("Invalid interval: min must be less than max.");
            }

            // Generate random numbers
            List<Integer> randomNumbers = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i < count; i++) {
                randomNumbers.add(random.nextInt(max - min + 1) + min);
            }
            return ResponseEntity.ok().body(randomNumbers);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("{error:Invalid input: count, min, and max must be valid numbers.}");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("{\n\t"+e.getMessage()+"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

