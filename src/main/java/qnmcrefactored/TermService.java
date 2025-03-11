package qnmcrefactored;

import java.util.Set;

public interface TermService {
    Set<String> getAllTerms();

    void addTerm(String term);

    void clearTerms();
}
