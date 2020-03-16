package com.codenation.decrypt.domain;

import org.jooq.lambda.Seq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Roman implements Alphabet {

    public static Roman instance = new Roman();

    private Integer alphabetSize;

    private Map<String, Integer> alphabetMap;

    private List<String> alphabet;

    private Roman() {
        int index = 0;
        alphabetMap = new HashMap<>();
        alphabet = new ArrayList<>();
        alphabet = Seq
                .rangeClosed('a', 'z')
                .map(Object::toString)
                .toList();

        for (String letter : alphabet) {
            alphabetMap.put(letter, index);
            index++;
        }
        this.alphabetSize = 26;
    }

    public static Roman getInstance() {
         return instance;
    }

    @Override
    public Integer getAlphabetSize() {
        return this.alphabetSize;
    }

    @Override
    public String getLetter(Integer index) {
        return alphabet.get(index);
    }

    @Override
    public Integer getIndex(char letter) {
        return alphabetMap.get(String.valueOf(letter));
    }
}
