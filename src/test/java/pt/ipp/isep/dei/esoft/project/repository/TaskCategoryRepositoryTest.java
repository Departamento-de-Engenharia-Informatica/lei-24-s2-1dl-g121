package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategoryModelo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskCategoryRepositoryTest {

    @Test
    void getTaskCategoryByDescriptionEmptyList() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        assertThrows(IllegalArgumentException.class,
                () -> taskCategoryRepository.getTaskCategoryByDescription(taskCategoryDescription));
    }

    @Test
    void getTaskCategoryByDescriptionNullList() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        assertThrows(IllegalArgumentException.class,
                () -> taskCategoryRepository.getTaskCategoryByDescription(taskCategoryDescription));
    }

    @Test
    void ensureNewTaskCategorySuccessfullyAdded() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        TaskCategoryModelo taskCategory = new TaskCategoryModelo(taskCategoryDescription);
        taskCategoryRepository.add(taskCategory);
    }

    @Test
    void ensureGetTaskCategoryForExistingTaskCategory() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        TaskCategoryModelo taskCategory = new TaskCategoryModelo(taskCategoryDescription);
        taskCategoryRepository.add(taskCategory);
        TaskCategoryModelo taskCategory1 = taskCategoryRepository.getTaskCategoryByDescription(taskCategoryDescription);
        assertEquals(taskCategory, taskCategory1);
    }

    @Test
    void ensureGetTaskCategoryFailsForNonExistingTaskCategory() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        TaskCategoryModelo taskCategory = new TaskCategoryModelo(taskCategoryDescription);
        taskCategoryRepository.add(taskCategory);
        String taskCategoryDescription1 = "Task Category Description 1";
        assertThrows(IllegalArgumentException.class,
                () -> taskCategoryRepository.getTaskCategoryByDescription(taskCategoryDescription1));

    }

    @Test
    void ensureGetTaskCategoriesReturnsAnImmutableList() {
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        TaskCategoryModelo taskCategory = new TaskCategoryModelo(taskCategoryDescription);
        taskCategoryRepository.add(taskCategory);

        assertThrows(UnsupportedOperationException.class,
                () -> taskCategoryRepository.getTaskCategories().add(new TaskCategoryModelo("Task Category Description 1")));

    }

    @Test
    void ensureGetTaskCategoriesReturnsTheCorrectList() {
        //Arrange
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        String taskCategoryDescription = "Task Category Description";
        TaskCategoryModelo taskCategory = new TaskCategoryModelo(taskCategoryDescription);
        taskCategoryRepository.add(taskCategory);
        int expectedSize = 1;

        //Act
        int size = taskCategoryRepository.getTaskCategories().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(taskCategory, taskCategoryRepository.getTaskCategories().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateTaskCategoryFails() {
        //Arrange
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        //Add the first task
        taskCategoryRepository.add(taskCategory);

        //Act
        Optional<TaskCategoryModelo> duplicateTaskCategory = taskCategoryRepository.add(taskCategory);

        //Assert
        assertTrue(duplicateTaskCategory.isEmpty());
    }

    @Test
    void ensureAddingDifferentTaskCategoriesWorks() {
        //Arrange
        TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
        TaskCategoryModelo taskCategoryOne = new TaskCategoryModelo("Task Category Description One");
        TaskCategoryModelo taskCategoryTwo = new TaskCategoryModelo("Task Category Description Two");
        //Add the first task
        taskCategoryRepository.add(taskCategoryOne);

        //Act
        Optional<TaskCategoryModelo> result = taskCategoryRepository.add(taskCategoryTwo);

        //Assert
        assertEquals(taskCategoryTwo, result.get());
    }
}