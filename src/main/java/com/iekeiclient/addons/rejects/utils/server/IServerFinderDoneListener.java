package com.iekeiclient.addons.rejects.utils.server;

public interface IServerFinderDoneListener {
    void onServerDone(ServerPinger pinger);

    void onServerFailed(ServerPinger pinger);
}
