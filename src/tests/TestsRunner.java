package tests;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import tasks.StreamTasksMain;

public class TestsRunner {
    
    /*******************************/
    /* DO NOT TOUCH METHODS BELLOW */
    /*******************************/
    
    private final StreamTasksTests implementation, reference;
    private final TestsPool testsPool;
    
    public TestsRunner (StreamTasksTests implementation, StreamTasksTests reference) {
        testsPool = new TestsPool (StreamTasksTests.class);
        this.implementation = implementation;
        this.reference = reference;
    }
    
    public int test () {
        Locale.setDefault (Locale.ENGLISH);
        
        final var testsNumber = testsPool.getTestsNumber ();
        int failed  = 0;
        
        System.out.printf ("Running tests...%n%n");
        
        for (int i = 0; i < testsNumber; i++) {
            System.out.printf ("Test %3d -- ", i + 1);
            
            final var start = System.currentTimeMillis ();
            List <String> customOutput = List.of ();
            long internalRuntime = Long.MAX_VALUE;
            Throwable throwable = null;
            TestVerdict verdict = null;
            
            try {
                final var result = testsPool.runTest (i, implementation, reference);
                internalRuntime = result.getRuntime ();
                customOutput = result.getOutput ();
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
                System.setOut (StreamTasksMain.ORIGINAL_OUT);
                final var end = System.currentTimeMillis ();
                
                final var runtime = Math.min (end - start, internalRuntime);
                System.out.printf ("[%6dms] %-20s%n", runtime, verdict.text);
                Optional.ofNullable (throwable).ifPresent (t -> {
                    System.out.printf ("  %s%n", t.getMessage ());
                });
                
                for (int j = 0; customOutput != null && j < customOutput.size (); j++) {
                    final var output = customOutput.get (j);
                    
                    if (output != null && !output.isBlank ()) {
                        System.out.printf ("  -- Your output (case %2d) --%n", j + 1);
                        System.out.println (customOutput.get (j));                        
                    }
                }
                
                failed += verdict == TestVerdict.ACCEPTED ? 0 : 1;
            }
        }
        
        System.out.printf (
            "%nSummary: %d tests, %d successful, %d failed%n", 
            testsNumber, testsNumber - failed, failed
        );
        
        return -failed;
    }
    
}
