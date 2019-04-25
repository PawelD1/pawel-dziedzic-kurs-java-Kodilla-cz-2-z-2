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
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void shouldGetTaskById() throws Exception {
        //Given
        Task task = new Task(1L, "taskOne", "content");
        TaskDto taskDto = new TaskDto(1L, "taskOne", "content");
        taskRepository.save(task);
        when(taskRepository.findOne(task.getId())).thenReturn(task);
        when(taskMapper.mapToTaskDto(dbService.getTask(task.getId()))).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1L)))
                .andExpect(jsonPath("$[0].title", is("taskOne")))
                .andExpect(jsonPath("$[0].content", is("content")));
    }

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        Task task = new Task(1L, "taskOne", "content");
        TaskDto taskDto = new TaskDto(1L, "taskOne", "content");
        taskRepository.save(task);
        List<Task> list = new ArrayList<>();
        list.add(task);
        List<TaskDto> dtoList = new ArrayList<>();
        dtoList.add(taskDto);
        when(taskRepository.findAll()).thenReturn(list);
        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(dtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1L)))
                .andExpect(jsonPath("$[0].title", is("taskOne")))
                .andExpect(jsonPath("$[0].content", is("content")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        Task task = new Task(1L, "taskOne", "content");
        taskRepository.save(task);
        //taskRepository.delete(task.getId());
        dbService.delete(task.getId());
        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(new ArrayList<>());
        //When & Then
        Assert.assertEquals(new ArrayList<>(), dbService.getAllTasks());
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "taskOne", "content");
        TaskDto taskDto = new TaskDto(1L, "taskOne", "content");
        //taskRepository.save(task);
        dbService.saveTask(taskMapper.mapToTask(taskDto));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1L)))
                .andExpect(jsonPath("$.title", is("taskOne")))
                .andExpect(jsonPath("$.content", is("content")));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "taskOne", "content");
        TaskDto taskDto = new TaskDto(1L, "taskOne", "content");
        task.setTitle("newTaskOne");
        taskRepository.save(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1L)))
                .andExpect(jsonPath("$.title", is("taskOne")))
                .andExpect(jsonPath("$.content", is("content")));
    }
}
