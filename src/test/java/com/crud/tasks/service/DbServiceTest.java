package com.crud.tasks.service;

import com.crud.tasks.domain.*;

import com.crud.tasks.repository.TaskRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import org.mockito.runners.MockitoJUnitRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Transactional
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void shouldShowGetAllTasks() {
        //Given
        Task task = new Task(1L, "task", "content");
        Task task2 = new Task(2L, "task2", "content2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task2);
        when(taskRepository.findAll()).thenReturn(tasks);
        //When
        List<Task> findTasks = dbService.getAllTasks();
        //Then
        assertNotNull(findTasks);
        assertEquals(2, findTasks.size());
    }

    @Test
    public void getTaskWhichDoesNotExists() {
        //Given
        when(taskRepository.findOne(2L)).thenReturn(null);

        //When
        Task findingTaskById = dbService.getTaskById(2L);

        //Then
        assertNull(findingTaskById);
    }


    @Test
    public void shouldShowSaveTask() {
        //Given
        Task task = new Task(1L, "task", "content");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task savedTask = dbService.saveTask(task);
        //Then
        assertEquals(task.getId(), savedTask.getId());
        assertEquals(task.getTitle(), savedTask.getTitle());
        assertEquals(task.getContent(), savedTask.getContent());
    }

    @Test
    public void shouldDeleteAllTasks() {
        //Given
        Task task = new Task(1L, "task", "content");
        //When
        dbService.delete(1L);
        Task savedTask = taskRepository.save(task);
        //Then
        verify(taskRepository, times(1)).delete(1L);
        assertFalse(taskRepository.findAll().contains(savedTask));
    }
}