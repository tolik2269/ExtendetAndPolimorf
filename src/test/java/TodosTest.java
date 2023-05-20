import Todos.Epic;
import Todos.Meeting;
import Todos.SimpletTask;
import Todos.Todos;
import Todos.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpletTask simpletTask = new SimpletTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpletTask);
        todos.add(epic);
        todos.add(meeting);


        Task[] expected = {simpletTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testEpicMatches() {
        String[] subtasks = {"Subtask 1", "Subtask 2", "Subtask 3"};
        Epic epic = new Epic(1003, subtasks);

        assertTrue(epic.matches("Subtask"));
        assertTrue(epic.matches("Subtask 1"));
        assertTrue(epic.matches("Subtask 2"));
        assertTrue(epic.matches("Subtask 3"));
        assertTrue(epic.matches("task"));
        assertTrue(epic.matches(" "));
        assertFalse(epic.matches("Subtitle"));
    }

    @Test
    public void testMeetingMatches() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        assertTrue(meeting.matches(" "));
        assertTrue(meeting.matches("приложения"));
        assertTrue(meeting.matches("Приложение"));
        assertTrue(meeting.matches("Приложение НетоБанка"));
        assertTrue(meeting.matches("Выкатка 3й версии приложения"));
        assertFalse(meeting.matches("Ёж"));
        assertFalse(meeting.matches("Во вторник после обеда"));
    }

    @Test
    public void testSimpleTaskMatching() {
        SimpletTask simpletTask = new SimpletTask(2005, "SimpleTask");

        assertTrue(simpletTask.matches("Simple"));
        assertTrue(simpletTask.matches("Task"));
        assertTrue(simpletTask.matches("SimpleTask"));
        assertTrue(simpletTask.matches("S"));
        assertFalse(simpletTask.matches(" "));
        assertFalse(simpletTask.matches("SimpleTask1"));
        assertFalse(simpletTask.matches("Simple Task"));
        assertFalse(simpletTask.matches("Project"));
    }

    @Test
    public void testSearch() {
        SimpletTask simpletTask1 = new SimpletTask(2004, "Simple Task 1");
        SimpletTask simpletTask2 = new SimpletTask(2004, "Simple Task 2");

        Epic epic = new Epic(4005, new String[]{"SubTask 1", "Subtask2"});

        Meeting meeting1 = new Meeting(124, "За нас", "МИР", "После дождичка в четверг");
        Meeting meeting2 = new Meeting(124, "Simple solutions discussion", "Task", "Never");

        Todos todos = new Todos();
        todos.add(simpletTask1);
        todos.add(simpletTask2);
        todos.add(epic);
        todos.add(meeting1);
        todos.add(meeting2);

        assertArrayEquals(new Task[]{simpletTask1, simpletTask2, meeting2}, todos.search("Simple"));

    }


}