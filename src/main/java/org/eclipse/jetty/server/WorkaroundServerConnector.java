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

import java.util.concurrent.Executor;

import org.eclipse.jetty.io.ByteBufferPool;
import org.eclipse.jetty.io.ManagedSelector;
import org.eclipse.jetty.io.SelectorManager;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.Scheduler;

public class WorkaroundServerConnector extends ServerConnector
{
    public WorkaroundServerConnector(Server server)
    {
        super(server);
    }

    public WorkaroundServerConnector(Server server, int acceptors, int selectors)
    {
        super(server, acceptors, selectors);
    }

    public WorkaroundServerConnector(Server server, int acceptors, int selectors, ConnectionFactory... factories)
    {
        super(server, acceptors, selectors, factories);
    }

    public WorkaroundServerConnector(Server server, ConnectionFactory... factories)
    {
        super(server, factories);
    }

    public WorkaroundServerConnector(Server server, SslContextFactory sslContextFactory)
    {
        super(server, sslContextFactory);
    }

    public WorkaroundServerConnector(Server server, int acceptors, int selectors, SslContextFactory sslContextFactory)
    {
        super(server, acceptors, selectors, sslContextFactory);
    }

    public WorkaroundServerConnector(Server server, SslContextFactory sslContextFactory, ConnectionFactory... factories)
    {
        super(server, sslContextFactory, factories);
    }

    public WorkaroundServerConnector(Server server, Executor executor, Scheduler scheduler, ByteBufferPool bufferPool, int acceptors, int selectors, ConnectionFactory... factories)
    {
        super(server, executor, scheduler, bufferPool, acceptors, selectors, factories);
    }

    @Override
    protected SelectorManager newSelectorManager(Executor executor, Scheduler scheduler, int selectors)
    {
        return new ServerConnectorManager(executor, scheduler, selectors)
        {
            protected ManagedSelector newSelector(int id)
            {
                return new WorkaroundManagedSelector(this, id);
            }
        };
    }
}
