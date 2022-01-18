# PSign
PSign is a smart lightweight digital signer made in Java. You can sign, verify and generate public and private keys.
# What are digital signatures?
Digital signatures are basically a form of validating the sender, to check if the message is being sent by whom it says it was sent by. Basically it makes the message, or in this case, the file, hard-to-write, since, like real signatures, digital ones can't be faked easily.
# How do I use this?
First, go to [Releases](https://github.com/PatrickMSM/PSign/releases), download the latest release, ensure you have Java 17 by running `java --version`, then go into the folder where PSign-X.X.jar is located, and type `java -jar PSign-X.X.jar help` to get a list of help commands, then type `java -jar PSign-X.X.jar <command-here>` to run a command. 
# Technical details
PSign generates RSA keys with the SecureRandom java class, stores them in files with the private key being encoded to PKCS8 and the public one to X509.