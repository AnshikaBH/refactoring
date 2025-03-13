package qnmcrefactored;

import java.util.ArrayList;
import java.util.List;

public class Quine {
    // macro
    protected static final int MAX_TERMS = 255;// 0xff=255
    // attribute
    public List<MinTerm> terms = new ArrayList<>();
    private final MinTermManager minTermManager = MinTermManager.getInstance();

    // singleton
    public Quine() {
    }

    // adding minterms
    public void addTerm(String str) throws ExceptionQuine {
        if (terms.size() == MAX_TERMS)
            throw new ExceptionQuine("Cannot add more terms. Maximum term limit reached");
        terms.add(MinTermFactory.createMinTerm(str)); // using minterm facory
    }

    // converted to string
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (MinTerm term: terms) {
            stringBuilder.append(term).append("\n");
        }
        return stringBuilder.toString();
    }


    // verification of the function
    public void simplify() throws ExceptionQuine {
        while (reduceTerms() > 0);
    }

    // reduce is a long method - split into smaller methods
    // reduction of the minterm

    private int reduceTerms() throws ExceptionQuine {

        List<MinTerm> reducedTerms = new ArrayList<>();

        int reducedCount = findReduciblePairs(reducedTerms);
        // copy the unchanged minterm in new list

        for (MinTerm term: terms) {
            if (!reducedTerms.contains(term)) {
                reducedTerms.add(term);
            }
        }
        terms = reducedTerms;
        return reducedCount;

    }

    private int findReduciblePairs(List<MinTerm> reducedTerms) throws ExceptionQuine {
        // working with all minterms
        // variable
        int reducedCount = 0;
        for (int i = 0; i < terms.size(); i++) {
            for (int j = i + 1; j < terms.size(); j++) {
                // finding the terms which differs in one place
                if (minTermManager.resolutionCount(terms.get(i), terms.get(j)) == 1) {
                    MinTerm combinedTerm = minTermManager.combine(terms.get(i), terms.get(j));
                    if (!reducedTerms.contains(combinedTerm)) {
                        reducedTerms.add(combinedTerm);
                        reducedCount++;
                    }
                }
            }
        }
        return reducedCount;
    }
}
