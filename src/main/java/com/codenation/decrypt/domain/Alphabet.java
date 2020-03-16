package com.codenation.decrypt.domain;

public interface Alphabet {

    Integer getAlphabetSize();

    String getLetter(final Integer index);

    Integer getIndex(final char letter);
}
