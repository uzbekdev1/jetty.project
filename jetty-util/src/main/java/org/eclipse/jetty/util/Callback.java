//
//  ========================================================================
//  Copyright (c) 1995-2016 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.util;

/**
 * <p>A callback abstraction that handles completed/failed events of asynchronous operations.</p>
 *
 * <p>Semantically this is equivalent to an optimise Promise&lt;Void&gt;, but callback is a more meaningful
 * name than EmptyPromise</p>
 */
public interface Callback
{
    /**
     * Instance of Adapter that can be used when the callback methods need an empty
     * implementation without incurring in the cost of allocating a new Adapter object.
     */
    static Callback NOOP = new Callback(){};


    /**
     * <p>Callback invoked when the operation completes.</p>
     *
     * @see #failed(Throwable)
     */
    default void succeeded()
    {}

    /**
     * <p>Callback invoked when the operation fails.</p>
     * @param x the reason for the operation failure
     */
    default void failed(Throwable x)
    {}

    /**
     * @return True if the callback is known to never block the caller
     */
    default boolean isNonBlocking()
    {
        return false;
    }
    
    
    /**
     * Callback interface that declares itself as non-blocking
     */
    interface NonBlocking extends Callback
    {
        @Override
        public default boolean isNonBlocking()
        {
            return true;
        }
    }
    
    
    /**
     * <p>Empty implementation of {@link Callback}</p>
     */
    @Deprecated
    static class Adapter implements Callback
    {}
}
