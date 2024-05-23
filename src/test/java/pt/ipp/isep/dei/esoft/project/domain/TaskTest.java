package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void ensureTaskIsCreatedSuccessfully() {
        Employee employee = new Employee("john.doe@this.company.com");
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskModelo task = new TaskModelo("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, employee);
    }

    @Test
    void ensureTaskReferenceIsNotNull() {
        //Arrange
        Employee employee = new Employee("john.doe@this.company.com");
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");

        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new TaskModelo(null, "description", "informal description", "technical description", 1, 1d,
                        taskCategory, employee));
    }

    @Test
    void testEqualsSameObject() {
        Employee employee = new Employee("john.doe@this.company.com");
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskModelo task = new TaskModelo("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, employee);

        assertEquals(task, task);
    }

    @Test
    void testEqualsDifferentClass() {
        Employee employee = new Employee("john.doe@this.company.com");
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskModelo task = new TaskModelo("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, employee);

        assertNotEquals(task, new Object());
    }

    @Test
    void testEqualsNull() {
        Employee employee = new Employee("john.doe@this.company.com");
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskModelo task = new TaskModelo("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, employee);

        assertNotEquals(task, null);
    }

    @Test
    void testEqualsDifferentObject() {
        Employee employee = new Employee("john.doe@this.company.com");
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskModelo task = new TaskModelo("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, employee);
        TaskModelo task1 = new TaskModelo("reference1", "description1", "informal description1", "technical description1", 2, 2d,
                taskCategory, employee);

        assertNotEquals(task, task1);
    }

    @Test
    void testEqualsSameObjectDifferentDescription() {
        Employee employee = new Employee("john.doe@this.company.com");
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskModelo task = new TaskModelo("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, employee);
        TaskModelo task1 = new TaskModelo("reference1", "description", "informal description1", "technical description1", 2, 2d,
                taskCategory, employee);

        assertNotEquals(task, task1);
    }

    @Test
    void testEqualsSameObjectSameDescription() {
        Employee employee = new Employee("john.doe@this.company.com");
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskModelo task = new TaskModelo("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, employee);
        TaskModelo task1 = new TaskModelo("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, employee);

        assertEquals(task, task1);
    }

    @Test
    void testHashCodeSameObject() {
        Employee employee = new Employee("john.doe@this.company.com");
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskModelo task = new TaskModelo("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, employee);

        assertEquals(task.hashCode(), task.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Employee employee = new Employee("john.doe@this.company.com");
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskModelo task = new TaskModelo("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, employee);
        TaskModelo task1 = new TaskModelo("reference1", "description1", "informal description1", "technical description1", 2, 2d,
                taskCategory, employee);

        assertNotEquals(task.hashCode(), task1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        Employee employee = new Employee("john.doe@this.company.org");
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskModelo task = new TaskModelo("reference", "description", "informal description", "technical description", 1, 1d,
                taskCategory, employee);
        TaskModelo clone = task.clone();
        assertEquals(task, clone);
    }
}