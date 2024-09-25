package org.thoughtcrimedual.securesms;

import org.signal.libsignal.zkgroup.ServerPublicParams;
import org.signal.libsignal.zkgroup.ServerSecretParams;
import org.signal.libsignal.zkgroup.profiles.ServerZkProfileOperations;
import org.whispersystems.signalservice.test.LibSignalLibraryUtil;

/**
 * Provides Zk group operations that the server would provide.
 * Copied in app from libsignal
 */
public final class TestZkGroupServer {

  private final ServerPublicParams        serverPublicParams;
  private final ServerZkProfileOperations serverZkProfileOperations;

  public TestZkGroupServer() {
    LibSignalLibraryUtil.assumeLibSignalSupportedOnOS();

    ServerSecretParams serverSecretParams = ServerSecretParams.generate();

    serverPublicParams        = serverSecretParams.getPublicParams();
    serverZkProfileOperations = new ServerZkProfileOperations(serverSecretParams);
  }

  public ServerPublicParams getServerPublicParams() {
    return serverPublicParams;
  }
}
