package org.theta.WeixinTest.base;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.http.conn.ssl.TrustStrategy;

/**
 * A trust strategy that accepts self-signed certificates as trusted. Verification of all other
 * certificates is done by the trust manager configured in the SSL context.
 *
 * @since 4.1
 */
public class MyTrustSelfSignedStrategy implements TrustStrategy {

    public boolean isTrusted(
            final X509Certificate[] chain, final String authType) throws CertificateException {
        return true;
    }

}