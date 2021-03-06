package duke.commands;

import duke.tasks.TaskList;

/**
 * Handles the logic of terminating the application.
 */
public class ByeCommand extends Command {

    /**
     * Returns true as the application should terminate when users input "bye".
     *
     * @return true
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Does nothing.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     */
    public void execute(TaskList tasks) {
    }

    /**
     * Creates a response to notify the users that the application is shutting down.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @return A <code>String</code> to respond to the closing of the application.
     */
    public String getResponse(TaskList tasks) {
        return "Meow... Seeya!";
    }
}
