package tk.patsite.PSign.commands;

import java.util.List;

public abstract class Command {
    public abstract void invoke(List<String> args);
}
