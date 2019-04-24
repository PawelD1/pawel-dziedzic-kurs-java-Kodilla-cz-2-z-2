package com.crud.tasks.domain.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskMapperTest {

    @Test
    public void testMapToTask()
    {
        //Given
        TaskDto taskDto=new TaskDto(1L,"new","task");
        TaskMapper taskMapper=new TaskMapper();

        //When
        Task task= taskMapper.mapToTask(taskDto);

       //Then
        Assert.assertEquals(taskDto.getId(),task.getId());
    }

    @Test
    public void testMapToTaskDto()
    {
        //Given
        Task task=new Task(1L,"new","task");
        TaskMapper taskMapper=new TaskMapper();

        //When
        TaskDto taskDto= taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(task.getId(), taskDto.getId());
    }

    @Test
    public void testMapToTaskDtoList()
    {
        //Given
        Task task=new Task(1L,"new","task");
        Task task2=new Task(2L,"new2","task2");
        List<Task> lists=new ArrayList<>();
        lists.add(task);
        lists.add(task2);
        TaskMapper taskMapper=new TaskMapper();

        //When
        List<TaskDto> listsOfDto=taskMapper.mapToTaskDtoList(lists);

        //Then
        Assert.assertEquals(lists.get(1).getId(),listsOfDto.get(1).getId());
    }
}
