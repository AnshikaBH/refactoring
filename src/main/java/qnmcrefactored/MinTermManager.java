package qnmcrefactored;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class MinTermManager implements TermService {
    // final - ensures the reference to this set cannot be reassigned
    private final Set<String> minTerms = new TreeSet<>();

    // gets a read only version of minterms
    @Override
    public Set<String> getAllTerms() {
        return Collections.unmodifiableSet(minTerms);
    }

    // add a single minterm
    public void addTerm(String minTerm) {
        minTerms.add(minTerm);
    }

    // method to clear the set when making a new implementation
    @Override
    public void clearTerms() {
        minTerms.clear();
    }
}
