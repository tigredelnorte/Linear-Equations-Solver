package solver;

public abstract class Command {
    int colAInt;
    int colBInt;
    abstract void execute();
    abstract void unexecute();
}
