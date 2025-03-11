package qnmc;

import java.util.Set;
import java.util.TreeSet;

// change class name to MinTermManager
public class GetMintermList {
    private static Set<String> set=new TreeSet<String>(); // sorts strings, static = shared across all instances in this class

//    @SuppressWarnings("unused")
//    private static 	String[] minList=new String[256]; // creates a list of strings size 256 - not being used - dead code
//    int i = 0; // initialised but not used - dead code


    public void setMinList(String x){

        set.add(x);

    }


    public static Set<String> getMin(){
        return set;
    }

}