# Experimental Selector Hack for Eclipse Jetty

On some machines, the NIO Selector shows signs of a buggy implementation
that spuriously wakes up the selector to report 0 selected keys.
The symptoms of this kind of OS / Network driver bug is 100% CPU usage.

Example: https://github.com/eclipse/jetty.project/issues/2205

This repository contains an experimental ServerConnector that will
rebuild there are consecutive select of 0 that exceeds `WorkaroundManagedSelector.MAX_NO_SELECT`.

To use this alternate ServerConnector would require substituting usages of
`org.eclipse.jetty.server.ServerConnector` with the special one provide in here
`org.eclipse.jetty.server.WorkaroundServerConnector`.

**NOTICE:** Do not use this workaround if you don't have the above mentioned bugs on your
system, as the performance of this workaround will negatively impact systems without
the selector bug. 