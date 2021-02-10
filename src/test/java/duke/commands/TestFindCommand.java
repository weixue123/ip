package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * JUnit test for the <code>FindCommand</code> class in duke.commands
 */
public class TestFindCommand {
    private final ToDo toDo;
    private final Deadline deadline;
    private final Event event;
    private final TaskList tasks;
    private final FindCommand command;

    /**
     * Initialize a <code>FindCommand</code> instance and a <code>TaskList</code> instance for testing.
     */
    public TestFindCommand() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("2021-02-06 23:30", formatter);

        this.tasks = new TaskList();
        this.toDo = new ToDo("CS2103 Quiz 1");
        this.deadline = new Deadline("BT4013 Quiz 2", dateTime);
        this.event = new Event("CS2103 Quiz 3", dateTime);

        this.deadline.markAsDone();

        this.tasks.addTask(this.toDo);
        this.tasks.addTask(this.deadline);
        this.tasks.addTask(this.event);

        this.command = new FindCommand("CS");
    }

    /**
     * Test that <code>FindCommand</code> correctly determines whether to exit the application.
     */
    @Test
    public void testIsExit() {
        assertFalse(this.command.isExit());
    }

    /**
     * Test that <code>FindCommand</code> (correctly) does not change the input <code>TaskList</code>.
     */
    @Test
    public void testExecute() {
        assertEquals(3, this.tasks.getSize());
        assertEquals(this.toDo, this.tasks.getTaskByIndex(1));
        assertEquals(this.deadline, this.tasks.getTaskByIndex(2));
        assertEquals(this.event, this.tasks.getTaskByIndex(3));

        this.command.execute(this.tasks);

        assertEquals(3, this.tasks.getSize());
        assertEquals(this.toDo, this.tasks.getTaskByIndex(1));
        assertEquals(this.deadline, this.tasks.getTaskByIndex(2));
        assertEquals(this.event, this.tasks.getTaskByIndex(3));
    }

    /**
     * Test that <code>FindCommand</code> computes the response message correctly.
     */
    @Test
    public void testResponse() {
        String expectedResponse = "Here are the matching tasks in your list:\n"
                + "1.[T][ ] CS2103 Quiz 1\n"
                + "2.[E][ ] CS2103 Quiz 3 (at: 2021-02-06 23:30)\n";
        assertEquals(expectedResponse, this.command.getResponse(this.tasks));

        String expectedEmptyResponse = "There are no tasks matching the string 'CS' in your list :O";
        assertEquals(expectedEmptyResponse, this.command.getResponse(new TaskList()));
    }
}