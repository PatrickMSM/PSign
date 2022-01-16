package tk.patsite.PSign.commands;

import tk.patsite.PSign.Util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.List;

public final class GenkeyCommand extends Command {
    @Override
    public void invoke(List<String> args) {
        // Generate key

        // Check arguments
        if (args.size() < 1) {
            System.out.println("Not enough arguments!");
            return;
        }

        String path = args.get(0);

        try {
            KeyPairGenerator pairGen = KeyPairGenerator.getInstance("RSA");
            pairGen.initialize(1024);

            System.out.println("Generating keypair...");
            // Generate key
            KeyPair pair = pairGen.generateKeyPair();

            System.out.println("Saving keypair...");

            // Output pair
            Util.saveKeypair(path, pair);
            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
