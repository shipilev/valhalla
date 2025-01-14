/*
 * @test /nodynamiccopyright/
 * @bug 8197911
 * @summary Test Javac's treatment of null assignment to primitive class instances
 * @compile/fail/ref=FlattenableNegativeTest.out -XDrawDiagnostics -XDdev -XDenablePrimitiveClasses FlattenableNegativeTest.java
 */

public class FlattenableNegativeTest {
    primitive final class V {
        final int x = 10;
        
        primitive final class X {
            final V v = null;  // Error: initialization illegal
            final V v2 = v;    // OK, null not constant propagated.

            V foo(X x) {
                x.v = null;  // Error: illegal.
                return x.v;
            }
        }
        V foo(X x) {
            x.v = null; // illegal
            return x.v;
        }

        class Y {
            V v;
            V [] va = { null }; // Illegal array initialization
            V [] va2 = new V[] { null }; // Illegal array initialization
            void foo(X x) {
                x.v = null; // illegal
                v = null; // illegal assignment.
                va[0] = null; // Illegal.
                va = new V[] { null }; // Illegal
            }
        }
    }
}
