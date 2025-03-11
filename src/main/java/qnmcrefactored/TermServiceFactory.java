package qnmcrefactored;

//OPTION 1
// creates objects of mintermanager
//public class TermServiceFactory {
//    public static TermService createMinTermManager() {
//        return new MinTermManager();
//    }
//}

// OPTION 2
// with dependency injection
public class TermServiceFactory {
    private TermService service;

    public TermServiceFactory(TermService service) {
        this.service = service;
    }

    public TermService getService() {
        return this.service;
    }
}
