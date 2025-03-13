package qnmcrefactored;

import java.util.*;

import static qnmcrefactored.MinTerm.*;

public class MinTermManager implements TermRepository {

    private static MinTermManager instance;
    // final - ensures the reference to this set cannot be reassigned
    private final Set<String> minTerms = new TreeSet<>();

    private MinTermManager() {}

    public static MinTermManager getInstance() {
        if (instance == null) {
            instance = new MinTermManager();
        }
        return instance;
    }
    // gets a read only version of minterms
    @Override
    public Set<String> getAllTerms() {
        return Collections.unmodifiableSet(minTerms);
    }

    // add a single minterm
    @Override
    public void addTerm(String minTerm) {
        minTerms.add(minTerm);
    }

    // method to clear the set when making a new implementation
    @Override
    public void clearTerms() {
        minTerms.clear();
    }

    // comparing minterms

    // exception message is not clear and parameter is not descriptive
    public boolean isSame(MinTerm a, MinTerm b) throws ExceptionQuine {
        if (a.getSize() != b.getSize()) {
            throw new ExceptionQuine("Minterms do not match");
        }
        for (int i = 0; i < a.getSize(); i++) {
            if (a.getValue(i) != b.getValue(i))
                return false;

        }
        return true;
    }

    // number of the difference

    public int resolutionCount(MinTerm a, MinTerm b) throws ExceptionQuine {
        if (a.getSize() != b.getSize())
            throw new ExceptionQuine("Resolution count failed, minterms do not match");
        int resCount = 0;
        for (int i = 0; i < a.getSize(); i++) {
            if (a.getValue(i) != b.getValue(i))
                resCount++;
        }
        return resCount;
    }

    // position of the first difference

    public int resolutionPosition(MinTerm a, MinTerm b) throws ExceptionQuine {
        if (a.getSize() != b.getSize())
            throw new ExceptionQuine("Failed to find resolution position, minterms do not match");
        for (int i = 0; i < a.getSize(); i++) {
            if (a.getValue(i) != b.getValue(i))
                return i;
        }

        return -1;
    }

    // combining two minterms

    public MinTerm combine(MinTerm a, MinTerm b) throws ExceptionQuine {
        if (a.getSize() != b.getSize()) {
            throw new ExceptionQuine("Minterms do not match");
        }
        StringBuilder stringBuilder = new StringBuilder(a.getSize());
        for (int i = 0; i < a.getSize(); i++) {
            if (a.getValue(i) != b.getValue(i)) {
                stringBuilder.append(ANY_CH);
            } else {
                int value = a.getValue(i);
                if (value == NOT) {
                    stringBuilder.append(NOT_CH);
                } else if (value == SET) {
                    stringBuilder.append(SET_CH);
                } else {
                    stringBuilder.append(ANY_CH);
                }
            }
        }
        String combinedTerm = stringBuilder.toString();
        addTerm(combinedTerm);
        return new MinTerm(combinedTerm);
    }
}

