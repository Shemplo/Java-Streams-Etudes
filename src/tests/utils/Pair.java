package tests.utils;


public class Pair <F, S> {
    
    public final F F;
    public final S S;
    
    public Pair (F F, S S) {
        this.F = F; this.S = S;
    }
    
    public F f () { return F; }
    public S s () { return S; }
    
}
