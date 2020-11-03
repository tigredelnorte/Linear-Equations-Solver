package solver;

public class LinearEquationInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }
    public void execute() {
        command.execute();
    }
    public void unexecute() {
        command.unexecute();
        addToHistory();
    }
    public void addToHistory() {
        CommandHistory.add(command);
    }
    public void getFromHistory() {
        this.command = CommandHistory.get();
    }
}
