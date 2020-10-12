package security.cert;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Slf4j
public class KeyStoreHandler {
    KeyStore ks;
    private char[] pw;
    private static final String KEY_STORE_DEFAULT_PATH;
    static {
//        KEY_STORE_DEFAULT_PATH = System.getProperty("user.home") + File.separator + "ssc.jks";
        KEY_STORE_DEFAULT_PATH = "src/main/resources/security/ssc.jks";
        log.info("key store path : " + KEY_STORE_DEFAULT_PATH);
    }

    // We'll use this to look up the keystore in the default location.
    // You can specify a password if you like, but this will also
    // work if you pass null (in which case the keystore isn't verified).
    public KeyStoreHandler(char[] pw) {
        // Make a private copy so the original can be collected so
        // that other objects can't locate it/
        if (pw != null) {
            this.pw = new char[pw.length];
            System.arraycopy(pw, 0, this.pw, 0, pw.length);
        } else {
            this.pw = null;
        }

        // Load from the default location
        try {
            ks = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream fis = new FileInputStream(KEY_STORE_DEFAULT_PATH);
            ks.load(fis, pw);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.toString());
        }
    }

    public KeyStore getKeyStore() {
        return ks;
    }

    // Store to the default location
    public void store() throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {
        // If we didn't read with a password, we can't store
        if (pw == null) {
            throw new IllegalArgumentException("Can't store w/o pw");
        }

        FileOutputStream fos = new FileOutputStream(KEY_STORE_DEFAULT_PATH);
        ks.store(fos, pw);
        fos.close();
    }
}
