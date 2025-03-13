package qnmcrefactored;

import java.util.Set;

public interface TermRepository {
    Set<String> getAllTerms();
    // future usages - extensibility

    void addTerm(String term);

    void clearTerms();
    // future extensibility when using getAllTerms in a set - clear terms in the set
}
