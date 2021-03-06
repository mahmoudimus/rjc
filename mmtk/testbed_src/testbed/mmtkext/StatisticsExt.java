package testbed.mmtkext;

import org.mmtk.vm.Statistics;
import org.rjava.restriction.rulesets.RJavaCore;
import org.vmmagic.pragma.Interruptible;

@RJavaCore
public class StatisticsExt extends Statistics {

    @Override
    public long nanoTime() {
        return System.nanoTime();
    }

    @Override
    public double nanosToMillis(long c) {
        return ((double)c) / 1000000;
    }

    @Override
    public double nanosToSecs(long c) {
        return ((double)c) / 1000000000;
    }

    @Override
    public long millisToNanos(double t) {
        return (long) (t * 1000000);
    }

    @Override
    public long secsToNanos(double t) {
        return (long) (t * 1000000000);
    }

    @Override
    public long cycles() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    @Interruptible
    public void perfEventInit(String events) {

    }

    @Override
    public void perfEventRead(int counter, long[] values) {

    }

}
