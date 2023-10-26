package edu.hw3.task1;

import java.util.ArrayList;
import java.util.List;

public class Task1 {
    private List<Character> alphabet;

    public Task1() {
        this.alphabet = createAlphabet();
    }

    private List<Character> createAlphabet() {
        List<Character> newAlphabet = new ArrayList<>();
        for (char letter = 'a'; letter <= 'z'; ++letter) {
            newAlphabet.add(letter);
        }
        return newAlphabet;
    }

    public String decodeString(String inputString) {
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < inputString.length(); ++i) {
            char letter = inputString.charAt(i);
            int index = alphabet.indexOf(Character.toLowerCase(letter));
            if (index != -1) {
                char newSymbol = (Character.isUpperCase(letter))
                    ? Character.toUpperCase(alphabet.get(alphabet.size() - 1 - index))
                    : alphabet.get(alphabet.size() - 1 - index);
                newString.append(newSymbol);
            } else {
                newString.append(letter);
            }
        }
        return newString.toString();
    }
}
