//
// ========================================================================
// Copyright (c) 1995-2020 Mort Bay Consulting Pty Ltd and others.
//
// This program and the accompanying materials are made available under
// the terms of the Eclipse Public License 2.0 which is available at
// https://www.eclipse.org/legal/epl-2.0
//
// This Source Code may also be made available under the following
// Secondary Licenses when the conditions for such availability set
// forth in the Eclipse Public License, v. 2.0 are satisfied:
// the Apache License v2.0 which is available at
// https://www.apache.org/licenses/LICENSE-2.0
//
// SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
// ========================================================================
//

package org.eclipse.jetty.server;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.jetty.io.ManagedSelector;
import org.eclipse.jetty.io.SelectorManager;

public class WorkaroundManagedSelector extends ManagedSelector
{
    public final int MAX_NO_SELECT = 1024;
    private final AtomicInteger noSelectCount = new AtomicInteger();

    public WorkaroundManagedSelector(SelectorManager selectorManager, int id)
    {
        super(selectorManager, id);
    }

    @Override
    protected int nioSelect(Selector selector, boolean now) throws IOException
    {
        int selected = super.nioSelect(selector, now);
        if (selected == 0)
        {
            // Count consecutive no-selects
            if (noSelectCount.incrementAndGet() > MAX_NO_SELECT)
            {
                // we exceeded the maximum consecutive no-select count
                handleSelectFailure(selector, new IOException("Rebuilding Selector: " + selector));
                // reset count
                noSelectCount.set(0);
            }
        }
        else if (selected > 0)
        {
            // rest
            noSelectCount.set(0);
        }
        return selected;
    }
}
