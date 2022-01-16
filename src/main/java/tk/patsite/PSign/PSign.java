package tk.patsite.PSign;

import java.util.ArrayList;
import java.util.List;

public final class PSign {
    public static void main(String[] args) {
        // Parse Command
        new CommandParser(new ArrayList<>(List.of(args)));
    }
}
