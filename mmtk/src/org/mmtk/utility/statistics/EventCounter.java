/*
 *  This file is part of the Jikes RVM project (http://jikesrvm.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License. You
 *  may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  See the COPYRIGHT.txt file distributed with this work for information
 *  regarding copyright ownership.
 */
package org.mmtk.utility.statistics;

import org.mmtk.utility.Log;

import org.mmtk.vm.VM;

import org.rjava.restriction.rulesets.MMTk;
import org.vmmagic.pragma.*;

/**
 * This class implements a simple event counter (counting number
 * events that occur for each phase).
 */
@MMTk
public class EventCounter extends Counter {

  /****************************************************************************
   *
   * Instance variables
   */

  /**
   *
   */
  private final long[] count;

  protected long currentCount = 0;
  private boolean running = false;

  /****************************************************************************
   *
   * Initialization
   */

  /**
   * Constructor
   *
   * @param name The name to be associated with this counter
   */
  public EventCounter(String name) {
    this(name, true, false);
  }

  /**
   * Constructor
   *
   * @param name The name to be associated with this counter
   * @param start True if this counter is to be implicitly started
   * when <code>startAll()</code> is called (otherwise the counter
   * must be explicitly started).
   */
  public EventCounter(String name, boolean start) {
    this(name, start, false);
  }

  /**
   * Constructor
   *
   * @param name The name to be associated with this counter
   * @param start {@code true} if this counter is to be implicitly started
   * when <code>startAll()</code> is called (otherwise the counter
   * must be explicitly started).
   * @param mergephases {@code true} if this counter does not separately
   * report GC and Mutator phases.
   */
  public EventCounter(String name, boolean start, boolean mergephases) {
    super(name, start, mergephases);
    count = new long[Stats.MAX_PHASES];
  }

  /****************************************************************************
   *
   * Counter-specific methods
   */

  /**
   * Increment the event counter
   */
  public void inc() {
    if (running) inc(1);
  }

  /**
   * Increment the event counter by <code>value</code>
   *
   * @param value The amount by which the counter should be incremented.
   */
  public void inc(int value) {
    if (running) currentCount += value;
  }

  /****************************************************************************
   *
   * Generic counter control methods: start, stop, print etc
   */

  /**
   * {@inheritDoc}
   */
  @Override
  protected void start() {
    if (!Stats.gatheringStats) return;
    if (VM.VERIFY_ASSERTIONS) VM.assertions._assert(!running);
    running = true;
  }

  @Override
  protected void stop() {
    if (!Stats.gatheringStats) return;
    if (VM.VERIFY_ASSERTIONS) VM.assertions._assert(running);
    count[Stats.phase] = currentCount;
    currentCount = 0;
    running = false;
  }

  /**
   * The phase has changed (from GC to mutator or mutator to GC).
   * Take action with respect to the last phase if necessary.
   * <b>Do nothing in this case.</b>
   *
   * @param oldPhase The last phase
   */
  @Override
  void phaseChange(int oldPhase) {
    if (running) {
      count[oldPhase] = currentCount;
      currentCount = 0;
    }
  }

  /**
   * {@inheritDoc}
   * Print '0' for {@code false}, '1' for {@code true}.
   */
  @Override
  protected final void printCount(int phase) {
    if (VM.VERIFY_ASSERTIONS && mergePhases())
      if (VM.VERIFY_ASSERTIONS) VM.assertions._assert((phase | 1) == (phase + 1));
    if (mergePhases())
      printValue(count[phase] + count[phase + 1]);
    else
      printValue(count[phase]);
  }

  /**
   * Print the current value for this counter (mid-phase)
   */
  public final void printCurrent() {
    printValue(currentCount);
  }

  @Override
  public final void printTotal() {
    long total = 0;
    for (int p = 0; p <= Stats.phase; p++) {
      total += count[p];
    }
    printValue(total);
  }

  @Override
  protected final void printTotal(boolean mutator) {
    long total = 0;
    for (int p = (mutator) ? 0 : 1; p <= Stats.phase; p += 2) {
      total += count[p];
    }
    printValue(total);
  }

  @Override
  protected final void printMin(boolean mutator) {
    int p = (mutator) ? 0 : 1;
    long min = count[p];
    for (; p < Stats.phase; p += 2) {
      if (count[p] < min) min = count[p];
    }
    printValue(min);
  }

  @Override
  protected final void printMax(boolean mutator) {
    int p = (mutator) ? 0 : 1;
    long max = count[p];
    for (; p < Stats.phase; p += 2) {
      if (count[p] > max) max = count[p];
    }
    printValue(max);
  }

  /**
   * Print the given value
   *
   * @param value The value to be printed
   */
  void printValue(long value) {
    Log.write(value);
  }

  @Override
  public void printLast() {
    if (Stats.phase > 0) printCount(Stats.phase - 1);
  }
}
