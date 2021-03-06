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
package org.mmtk.plan.concurrent.marksweep;

import org.mmtk.plan.*;
import org.mmtk.plan.concurrent.Concurrent;
import org.mmtk.policy.MarkSweepSpace;
import org.mmtk.policy.Space;
import org.mmtk.utility.heap.VMRequest;

import org.rjava.restriction.rulesets.MMTk;
import org.vmmagic.pragma.*;
import org.vmmagic.unboxed.ObjectReference;

/**
 * This class implements the global state of a concurrent mark-sweep collector.
 */
@MMTk
public class CMS extends Concurrent {

  /****************************************************************************
   * Constants
   */

  /****************************************************************************
   * Class variables
   */

  /**
   *
   */
  public static final MarkSweepSpace msSpace = new MarkSweepSpace("ms", VMRequest.create());
  public static final int MARK_SWEEP = msSpace.getDescriptor();

  static {
    msSpace.makeAllocAsMarked();
    smallCodeSpace.makeAllocAsMarked();
    nonMovingSpace.makeAllocAsMarked();
  }

  /****************************************************************************
   * Instance variables
   */

  /**
   *
   */
  public final Trace msTrace = new Trace(metaDataSpace);

  /*****************************************************************************
   *
   * Collection
   */

  /**
   * {@inheritDoc}
   */
  @Override
  @Inline
  public void collectionPhase(short phaseId) {
    if (phaseId == PREPARE) {
      super.collectionPhase(phaseId);
      msTrace.prepareNonBlocking();
      msSpace.prepare(true);
      return;
    }

    if (phaseId == RELEASE) {
      msTrace.release();
      msSpace.release();
      super.collectionPhase(phaseId);
      return;
    }

    super.collectionPhase(phaseId);
  }

  /*****************************************************************************
   *
   * Accounting
   */

  /**
   * {@inheritDoc}
   * The superclass accounts for its spaces, we just
   * augment this with the mark-sweep space's contribution.
   */
  @Override
  public int getPagesUsed() {
    return (msSpace.reservedPages() + super.getPagesUsed());
  }

  @Override
  public boolean willNeverMove(ObjectReference object) {
    if (Space.isInSpace(MARK_SWEEP, object))
      return true;
    return super.willNeverMove(object);
  }

@Override
@Interruptible
public ParallelCollector newCollectorContext() {
    return new CMSCollector();
}
}
