package tk.patsite.PSign;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public final class Util {

    private static final String keyFile = "%s.%s.key";

    // SAVE

    public static void saveKeypair(String path, KeyPair keyPair) throws IOException {
        saveKey(path, "public", new X509EncodedKeySpec(keyPair.getPublic().getEncoded()));
        saveKey(path, "private", new PKCS8EncodedKeySpec(keyPair.getPublic().getEncoded()));
    }

    private static void saveKey(String path, String type, EncodedKeySpec encodedKeySpec) throws IOException {
        File file = new File(keyFile.formatted(path, type));
        writeBytesToFile(encodedKeySpec.getEncoded(), file);
    }

    public static void writeBytesToFile(byte[] bytes, File file) throws IOException {
        if(!file.exists()) file.createNewFile();
        try(FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(bytes);
        }
    }

    // LOAD

    public static Key loadKey(String path, String type) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] bytes = readBytesFromFile(new File(keyFile.formatted(path, type)));
        if(type.equals("public")) {
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(bytes);
            return keyFactory.generatePublic(encodedKeySpec);
        } else if(type.equals("private")) {
            PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(bytes);
            return keyFactory.generatePrivate(encodedKeySpec);
        }
        // impossible to get here
        // unless i made a typo somewhere test
        return null;
    }

    public static byte[] readBytesFromFile(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    public static boolean doesFileExist(String file) {
        return new File(file).exists();
    }
}
