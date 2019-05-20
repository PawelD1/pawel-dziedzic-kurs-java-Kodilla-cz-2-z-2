package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.*;

import com.crud.tasks.repository.TaskRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@Transactional
public class DbServiceTest {

    @Autowired
    DbService dbService;

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void shouldShowGetAllTasks() {
        //Given
        Task task = new Task(1L, "task", "content");
        Task task2 = new Task(2L, "task2", "content2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task2);
        dbService.saveTask(task);
        dbService.saveTask(task2);

        //When
        List<Task> findTasks = dbService.getAllTasks();

        //Then
        assertEquals(tasks, findTasks);
    }

    @Test
    public void shouldShowGetTask() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "task", "content");
        Task task2 = new Task(2L, "task2", "content2");
        taskRepository.save(task);
        taskRepository.save(task2);

        //When
        Task findTask = dbService.getTask(task2.getId());

        //Then
        assertEquals(task2.getId(), findTask.getId());
    }

    @Test
    public void shouldShowGetTaskById() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "task", "content");
        Task task2 = new Task(2L, "task2", "content2");
        taskRepository.save(task);
        taskRepository.save(task2);

        //When
        Task findTask = dbService.getTaskById(task2.getId());

        //Then
        assertEquals(task2.getId(), findTask.getId());
    }

    @Test
    public void shouldShowSaveTask() {
        //Given
        Task task = new Task(1L, "task", "content");
        Task task2 = new Task(2L, "task2", "content2");
        dbService.saveTask(task);
        dbService.saveTask(task2);

        //When && Then
        assertTrue(dbService.getAllTasks().contains(task));
        assertTrue(dbService.getAllTasks().contains(task2));
    }

    @Test
    public void shouldDeleteAllTasks() {
        //Given
        Task task = new Task(1L, "task", "content");
        Task task2 = new Task(2L, "task2", "content2");
        dbService.saveTask(task);
        dbService.saveTask(task2);

        //When
        dbService.delete(task.getId());
        dbService.delete(task2.getId());

        //Then
        assertEquals(0,dbService.getAllTasks().size());
    }
}