package tk.patsite.PSign.commands;

import tk.patsite.PSign.Util;

import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public final class SignCommand extends Command {
    @Override
    public void invoke(List<String> args) {
        // Check arguments
        if (args.size() < 2) {
            System.out.println("Not enough arguments!");
            return;
        }

        // Load key
        // First check if the key exists
        if (!Util.doesFileExist(args.get(0) + ".private.key")) {
            System.out.println("Private key \"" + args.get(0) + ".private.key\" not found!");
            return;
        }

        System.out.println("Loading key...");
        PrivateKey privateKey;
        try {
            privateKey = (PrivateKey) Util.loadKey(args.get(0), "private");
        } catch (IOException e) {
            // Regular errors
            e.printStackTrace();
            return;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            // Security errors
            // throw new Error(ex);
            ex.printStackTrace();
            return;
        }
        // Key is loaded


        // Get file that needs to be signed
        File file = new File(args.get(1));
        // First check if the file exists
        if (!file.exists()) {
            System.out.println("No private key \"" + args.get(0) + "\" exists.");
        }

        System.out.println("Loading file...");
        byte[] data;
        try {
            data = Util.readBytesFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // File is loaded


        // Sign file
        System.out.println("Signing file...");
        byte[] signedData;
        try {
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(privateKey);
            signature.update(data);
            signedData = signature.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            // Security errors
            //throw new Error(e);
            e.printStackTrace();
            return;
        }

        System.out.println("Outputting signature...");
        try {
            Util.writeBytesToFile(signedData, new File(args.get(1) + ".psign"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Signed file! Outputted signature to " + args.get(1) + ".psign");
        System.out.println("Signature can be appended to the file with a notice to extract it into another file to then verify it");
    }
}
