package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskCategoryTest {

    //Tests for equals and hashcode
    @Test
    void testEqualsSameObject() {
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        assertEquals(taskCategory, taskCategory);
    }

    @Test
    void testEqualsDifferentClass() {
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        assertNotEquals("", taskCategory);
    }

    @Test
    void testEqualsNull() {
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        assertNotEquals(null, taskCategory);
    }

    @Test
    void testEqualsDifferentObject() {
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskCategoryModelo taskCategory1 = new TaskCategoryModelo("Task Category Description 1");
        assertNotEquals(taskCategory, taskCategory1);
    }

    @Test
    void testEqualsSameObjectDifferentDescription() {
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskCategoryModelo taskCategory1 = new TaskCategoryModelo("Task Category Description 1");
        assertNotEquals(taskCategory, taskCategory1);
    }

    @Test
    void testEqualsSameObjectSameDescription() {
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskCategoryModelo taskCategory1 = new TaskCategoryModelo("Task Category Description");
        assertEquals(taskCategory, taskCategory1);
    }

    @Test
    void testHashCodeSameObject() {
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        assertEquals(taskCategory.hashCode(), taskCategory.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskCategoryModelo taskCategory1 = new TaskCategoryModelo("Task Category Description 1");
        assertNotEquals(taskCategory.hashCode(), taskCategory1.hashCode());
    }

    @Test
    void testHashCodeSameObjectDifferentDescription() {
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskCategoryModelo taskCategory1 = new TaskCategoryModelo("Task Category Description 1");
        assertNotEquals(taskCategory.hashCode(), taskCategory1.hashCode());
    }

    @Test
    void testHashCodeSameObjectSameDescription() {
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskCategoryModelo taskCategory1 = new TaskCategoryModelo("Task Category Description");
        assertEquals(taskCategory.hashCode(), taskCategory1.hashCode());
    }

    @Test
    void testEqualsForDifferentObjectType() {
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        assertNotEquals(taskCategory, new Object());
    }

    @Test
    void ensureGetDescriptionWorks() {
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");

        assertEquals("Task Category Description", taskCategory.getDescription());
    }

    @Test
    void ensureCloneWorks() {
        TaskCategoryModelo taskCategory = new TaskCategoryModelo("Task Category Description");
        TaskCategoryModelo clone = taskCategory.clone();
        assertEquals(taskCategory, clone);
    }
}