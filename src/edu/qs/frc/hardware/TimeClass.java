/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.hardware;

/**
 *
 * @author Darvin
 */
public class TimeClass {
        public static void Time(final double seconds) {
        try {
            Thread.sleep((long) (seconds * 1e3));
        } catch (final InterruptedException e) {
        }
    }
    
}
