package com.example.rentproject.utils;

import java.util.Random;

public class RandomPinUtils {
    public static String UUIDPinCode() {
        char[] arr = {'a', 'A', 'b', '8', 'W', 'O', '1', '9', '4'};
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(arr.length);
            char temp = arr[index];
            sb.append(temp);
        }
        return String.valueOf(sb);
    }
}
