package io.bitrise.androidtodokotlinandcomposesample

import org.junit.Assert.*
import org.junit.Test

class TasksTest {
    @Test
    fun addTask_addsTaskToList() {
        val tasks = Tasks()
        val task = Task("Test task", false)
        tasks.addTask(task)
        assertEquals(listOf(task), tasks.getAllTasks())
    }

    @Test
    fun updateTask_updatesTaskInList() {
        val tasks = Tasks()
        val originalTask = Task("Original task", false)
        tasks.addTask(originalTask)
        val updatedTask = Task("Updated task", true)
        tasks.updateTask(originalTask, updatedTask)
        assertEquals(listOf(updatedTask), tasks.getAllTasks())
    }

    @Test
    fun removeTask_removesTaskFromList() {
        val tasks = Tasks()
        val task = Task("Test task", false)
        tasks.addTask(task)
        tasks.removeTask(task)
        assertTrue(tasks.getAllTasks().isEmpty())
    }

    @Test
    fun getNotDoneTasks_returnsOnlyNotDoneTasks() {
        val tasks = Tasks()
        val doneTask = Task("Done task", true)
        val notDoneTask = Task("Not done task", false)
        tasks.addTask(doneTask)
        tasks.addTask(notDoneTask)
        assertEquals(listOf(notDoneTask), tasks.getNotDoneTasks())
    }

    @Test
    fun getDoneTasks_returnsOnlyDoneTasks() {
        val tasks = Tasks()
        val doneTask = Task("Done task", true)
        val notDoneTask = Task("Not done task", false)
        tasks.addTask(doneTask)
        tasks.addTask(notDoneTask)
        assertEquals(listOf(doneTask), tasks.getDoneTasks())
    }

    @Test
    fun getAllTasks_returnsAllTasks() {
        val tasks = Tasks()
        val task1 = Task("Task 1", false)
        val task2 = Task("Task 2", true)
        tasks.addTask(task1)
        tasks.addTask(task2)
        assertEquals(listOf(task1, task2), tasks.getAllTasks())
    }
}
