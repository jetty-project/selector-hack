package org.eclipse.jetty.server;

import java.util.concurrent.Executor;

import org.eclipse.jetty.io.ByteBufferPool;
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
}
