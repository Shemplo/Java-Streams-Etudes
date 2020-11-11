package tests;


public enum TestVerdict {
    
    ACCEPTED ("accepted"),
    NOT_IMPL ("not implemented"),
    WA       ("wrong answer"),
    NPE      ("missed NULL check"),
    RE       ("runtime exception")
    
    ;
    
    public final String text;
    
    TestVerdict (String text) {
        this.text = text;
    }
    
}
