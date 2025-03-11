package qnmcrefactored;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    protected List<Integer> term;

    // constructing & reading
    // needs input validation
    public MinTerm(String str) throws ExceptionQuine {
        validateInput(str);
        term = new ArrayList<>(str.length());
//        term = new int[str.length()];
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
        StringBuilder stringBuilder = new StringBuilder(term.size());
        for (int i = 0; i < term.size(); i++) {
            switch (term.get(i)) {
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

    // comparing minterm

    // exception message is not clear and parameter a is not descriptive
    public boolean isSame(MinTerm a) throws ExceptionQuine {
        if (term.size() != a.term.size()) {
            throw new ExceptionQuine("Minterms do not match");
        }
        for (int i = 0; i < term.size(); i++) {
            if (!Objects.equals(term.get(i), a.term.get(i)))
                return false;

        }
        return true;
    }

    // number of the difference

    public int resolutionCount(MinTerm a) throws ExceptionQuine {
        if (term.size() != a.term.size())
            throw new ExceptionQuine("Resolution count failed, minterms do not match");
        int resCount = 0;
        for (int i = 0; i < term.size(); i++) {
            if (!Objects.equals(term.get(i), a.term.get(i)))
                resCount++;
        }
        return resCount;
    }

    // position of the first difference

    public int resolutionPosition(MinTerm a) throws ExceptionQuine {
        if (term.size() != a.term.size())
            throw new ExceptionQuine("Failed to find resolution position, minterms do not match");
        for (int i = 0; i < term.size(); i++) {
            if (!Objects.equals(term.get(i), a.term.get(i)))
                return i;
        }

        return -1;
    }

    // combining two minterms

    public static MinTerm combine(MinTerm a, MinTerm b) throws ExceptionQuine {
        if (a.term.size() != b.term.size()) {
            throw new ExceptionQuine("Minterms do not match");
        }
        StringBuilder stringBuilder = new StringBuilder(a.term.size());
        for (int i = 0; i < a.term.size(); i++) {
            if (!Objects.equals(a.term.get(i), b.term.get(i))) {
                stringBuilder.append(ANY_CH);
            } else {
                int value = a.term.get(i);
                if (value == NOT) {
                    stringBuilder.append(NOT_CH);
                } else if (value == SET) {
                    stringBuilder.append(SET_CH);
                } else {
                    stringBuilder.append(ANY_CH);
                }
            }
        }
        return new MinTerm(stringBuilder.toString());
    }
}
