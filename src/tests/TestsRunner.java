package tests;

import java.util.Locale;
import java.util.Optional;

import tasks.StreamTasks;

public class TestsRunner {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    private final StreamTasks implementation;
    private final TestsPool testsPool;
    
    public TestsRunner (StreamTasks implementation, StreamTasks reference) {
        testsPool = new TestsPool (reference);
        this.implementation = implementation;
    }
    
    public int test () {
        Locale.setDefault (Locale.ENGLISH);
        
        final var testsNumber = testsPool.getTestsNumber ();
        int failed  = 0;
        
        System.out.printf ("Running tests...%n%n");
        
        for (int i = 0; i < testsNumber; i++) {
            System.out.printf ("Test %3d -- ", i + 1);
            
            final var start = System.currentTimeMillis ();
            Throwable throwable = null;
            TestVerdict verdict = null;
            
            try {
                runTest (i, implementation);
                verdict = TestVerdict.ACCEPTED;
            } catch (UnsupportedOperationException uoe) {
                verdict = TestVerdict.NOT_IMPL;
            } catch (AssertionError ae) {
                verdict = TestVerdict.WA;
                throwable = ae.getMessage () != null ? ae : null;
            } catch (NullPointerException npe) {
                verdict = TestVerdict.NPE;
                throwable = npe.getMessage () != null ? npe : null;
            } catch (Throwable t) {
                verdict = TestVerdict.RE;
                throwable = t.getMessage () != null ? t : null;
            } finally {
                final var end = System.currentTimeMillis ();
                
                System.out.printf ("[%6dms] %-20s%n", end - start, verdict.text);
                Optional.ofNullable (throwable).ifPresent (t -> {
                    System.out.printf ("  %s%n", t.getMessage ());
                });
                
                failed += verdict == TestVerdict.ACCEPTED ? 0 : 1;
            }
        }
        
        System.out.printf (
            "%nSummary: %d tests, %d successful, %d failed%n", 
            testsNumber, testsNumber - failed, failed
        );
        
        return -failed;
    }
    
    private void runTest (int index, StreamTasks impl) throws UnsupportedOperationException, AssertionError {
        final var tests = testsPool.getTaskTests (index);
        for (int i = 0; i < tests.getCasesNumber (); i++) {
            tests.runCase (i, impl);
        }
    }
    
}
