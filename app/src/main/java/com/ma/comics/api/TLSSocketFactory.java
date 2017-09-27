package com.ma.comics.api;

import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import java.util.*;
import java.security.*;

public class TLSSocketFactory extends SSLSocketFactory
{
    private SSLSocketFactory internalSSLSocketFactory;
    
    public TLSSocketFactory() throws KeyManagementException, NoSuchAlgorithmException {
        final SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, new TrustManager[] { this.systemDefaultTrustManager() }, null);
        this.internalSSLSocketFactory = instance.getSocketFactory();
    }
    
    private Socket enableTLSOnSocket(final Socket socket) {
        if (socket != null && socket instanceof SSLSocket) {
            ((SSLSocket)socket).setEnabledProtocols(new String[] { "TLSv1.1", "TLSv1.2" });
        }
        return socket;
    }
    
    @Override
    public Socket createSocket(final String s, final int n) throws IOException, UnknownHostException {
        return this.enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(s, n));
    }
    
    @Override
    public Socket createSocket(final String s, final int n, final InetAddress inetAddress, final int n2) throws IOException, UnknownHostException {
        return this.enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(s, n, inetAddress, n2));
    }
    
    @Override
    public Socket createSocket(final InetAddress inetAddress, final int n) throws IOException {
        return this.enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(inetAddress, n));
    }
    
    @Override
    public Socket createSocket(final InetAddress inetAddress, final int n, final InetAddress inetAddress2, final int n2) throws IOException {
        return this.enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(inetAddress, n, inetAddress2, n2));
    }
    
    @Override
    public Socket createSocket(final Socket socket, final String s, final int n, final boolean b) throws IOException {
        return this.enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(socket, s, n, b));
    }
    
    @Override
    public String[] getDefaultCipherSuites() {
        return this.internalSSLSocketFactory.getDefaultCipherSuites();
    }
    
    @Override
    public String[] getSupportedCipherSuites() {
        return this.internalSSLSocketFactory.getSupportedCipherSuites();
    }
    
    public X509TrustManager systemDefaultTrustManager() {
        TrustManager[] trustManagers;
        try {
            final TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore)null);
            trustManagers = instance.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
        }
        catch (GeneralSecurityException ex) {
            throw new AssertionError();
        }
        return (X509TrustManager)trustManagers[0];
    }
}
