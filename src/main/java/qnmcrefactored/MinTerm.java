package qnmcrefactored;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinTerm {
    // input data representation
    public static final char NOT_CH = '0';
    public static final char SET_CH = '1';
    public static final char ANY_CH = '_';

    // internal data representation
    protected static final int NOT = 0;
    protected static final int SET = 1;
    protected static final int ANY = -1;

    // attribute
    protected final List<Integer> term;

    // constructing & reading
    // needs input validation
    public MinTerm(String str) throws ExceptionQuine {
        validateInput(str);
        term = new ArrayList<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case NOT_CH:
                    term.add(NOT);
                    break;
                case SET_CH:
                    term.add(SET);
                    break;
                case ANY_CH:
                    term.add(ANY);
                    break;
            }
        }
    }

    public int getSize() {
        return term.size();
    }

    public int getValue(int index) {
        return term.get(index);
    }

    // helper method for input validation
    private void validateInput(String str) throws ExceptionQuine {
        if (StringUtils.isEmpty(str)) {
            throw new ExceptionQuine("Minterm is null or empty");
        }

        List<Character> validChars = Arrays.asList(NOT_CH, SET_CH, ANY_CH);

        for (char ch : str.toCharArray()) {
            if (!validChars.contains(ch)) {
                throw new ExceptionQuine("Invalid character");
            }

        }
    }

    // converted to string
    // changed string buffer to string builder - string buffer is slow so used string builder

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getSize());
        for (Integer integer : term) {
            switch (integer) {
                case NOT:
                    stringBuilder.append(NOT_CH);
                    break;
                case SET:
                    stringBuilder.append(SET_CH);
                    break;
                case ANY:
                    stringBuilder.append(ANY_CH);
                    break;
            }
        }
        return stringBuilder.toString();
    }
}

