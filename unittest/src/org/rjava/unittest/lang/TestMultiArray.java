package org.rjava.unittest.lang;

import org.rjava.restriction.rulesets.RJavaCore;
import org.rjava.unittest.UnitTest;

@RJavaCore
public class TestMultiArray extends UnitTest {
    public static void main(String[] args) {
        start("Test new int multiarray");
        check(testIntMultiArrayLiteral());
        
        start("Test int multiarray access");
        check(testIntMultiArrayAccess());
        
        start("Test new double multiarray");
        check(testDoubleMultiArrayLiteral());
        
        start("Test double multiarray access");
        check(testDoubleMultiArrayAccess());
        
        start("Test multiarray length");
        check(testMultiArrayLength());
    }
    
    public static boolean testIntMultiArrayLiteral() {
        int[][] multiarray = {
                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {10,11,12,13,14,15,16,17,18,19},
                {20,21,22,23,24,25,26,27,28,29},
                {30,31,32,33,34,35,36,37,38,39},
                {40,41,42,43,44,45,46,47,48,49}
        };
        return true;
    }
    
    public static boolean testIntMultiArrayAccess() {
        int[][] multiarray = {
                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {10,11,12,13,14,15,16,17,18,19},
                {20,21,22,23,24,25,26,27,28,29},
                {30,31,32,33,34,35,36,37,38,39},
                {40,41,42,43,44,45,46,47,48,49}
        };
        return multiarray[0][0] == 0 && multiarray[3][4] == 34 && multiarray[4][9] == 49;
    }
    
    public static boolean testDoubleMultiArrayLiteral() {
        double[][] multiarray = {
                {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9},
                {1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9},
                {2.0, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8, 2.9},
                {3.0, 3.1, 3.2, 3.3, 3.4, 3.5, 3.6, 3.7, 3.8, 3.9},
                {4.0, 4.1, 4.2, 4.3, 4.4, 4.5, 4.6, 4.7, 4.8, 4.9}
        };
        return true;
    }
    
    public static boolean testDoubleMultiArrayAccess() {
        double[][] multiarray = {
                {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9},
                {1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9},
                {2.0, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8, 2.9},
                {3.0, 3.1, 3.2, 3.3, 3.4, 3.5, 3.6, 3.7, 3.8, 3.9},
                {4.0, 4.1, 4.2, 4.3, 4.4, 4.5, 4.6, 4.7, 4.8, 4.9}
        };
        
        return (multiarray[0][0] > - 0.1 && multiarray[0][0] < 0.1)
                && (multiarray[3][4] > 3.3 && multiarray[3][4] < 3.5)
                && (multiarray[4][9] > 4.8 && multiarray[4][9] < 5.0);
    }
    
    public static boolean testMultiArrayLength() {
        int[][] multiarray = {
                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {10,11,12,13,14,15,16,17,18,19},
                {20,21,22,23,24,25,26,27,28,29},
                {30,31,32,33,34,35,36,37,38,39},
                {40,41,42,43,44,45,46,47,48,49}
        };
        return multiarray.length == 5 && multiarray[0].length == 10;
    }
}
