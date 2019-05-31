package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    public void shouldGetTaskById() throws Exception {
        //Given
        Task task = new Task(1L, "taskOne", "content");
        TaskDto taskDto = new TaskDto(1L, "taskOne", "content");
        //taskRepository.save(task);
        when(dbService.getTask(any())).thenReturn(task);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTaskById").param("taskId","1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("taskOne")))
                .andExpect(jsonPath("$.content", is("content")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        Task task = new Task(1L, "taskOne", "content");
        TaskDto taskDto = new TaskDto(1L, "taskOne", "content");
        //taskRepository.save(task);
        when(dbService.getTask(any())).thenReturn(task);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask").param("taskId","1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("taskOne")))
                .andExpect(jsonPath("$.content", is("content")));
    }

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        Task task = new Task(1L, "taskOne", "content");
        TaskDto taskDto = new TaskDto(1L, "taskOne", "content");
        List<Task> list = new ArrayList<>();
        list.add(task);
        List<TaskDto> dtoList = new ArrayList<>();
        dtoList.add(taskDto);
        when(dbService.getAllTasks()).thenReturn(list);
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(dtoList);

        //When & Then
        mockMvc.perform(get("/v1/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("taskOne")))
                .andExpect(jsonPath("$[0].content", is("content")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask").param("taskId","1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
        verify(dbService, times(1)).delete(any());
    }


    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "taskOne", "content");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))//tam gdzie body
                .andExpect(status().isOk());
                verify(dbService, times(1)).saveTask(any());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "taskOne", "content");
        TaskDto taskDto = new TaskDto(1L, "taskOne", "content");
        task.setTitle("newTaskOne");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        when(taskMapper.mapToTask(any())).thenReturn(task);
        when(dbService.saveTask(any())).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("taskOne")))
                .andExpect(jsonPath("$.content", is("content")));
    }
}
