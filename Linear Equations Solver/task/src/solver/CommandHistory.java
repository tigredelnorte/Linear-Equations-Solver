package solver;

import java.util.Stack;

public class CommandHistory {
    private static Stack<Command> history = new Stack<>();

    public static Command get() {
        return history.pop();
    }
    public static void add(Command command) {
        history.push(command);
    }
    public static int getHistorySize (){
        return history.size();
    }
}
