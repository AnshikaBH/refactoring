package qnmcrefactored;

public class MinTermFactory {
    public static MinTerm createMinTerm(String str) throws ExceptionQuine {
        return new MinTerm(str);
    }
}
