package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskMap {

    private Map<Integer, Task> tasksMap;

    public TaskMap(){
        tasksMap = new HashMap<>();
    }

    public void add(Task task){
        tasksMap.put(task.getId(), task);
    }

    public Task deleteTaskFromId(Integer id){
        return tasksMap.remove(id);
    }

    public Map<Integer, Task> getTasksMap() {
        return tasksMap;
    }

    public Collection<Task> getTaskByDay(LocalDate date){
        Collection<Task> tasksByDay = new ArrayList<>();
        Collection<Task> allTasks = tasksMap.values();

        for(Task task : allTasks){
            LocalDateTime currentDate = task.getCreationDate();

            if(currentDate.toLocalDate().equals(date)){
                tasksByDay.add(task);
                continue;
            }

            LocalDateTime nextDateTime = currentDate;
            do{
                nextDateTime = task.getTaskRepeatability().nextTime(nextDateTime);
                if(nextDateTime == null){
                    break;
                }
                if(nextDateTime.toLocalDate().equals(date)){
                    tasksByDay.add(task);
                    break;
                }
            }while (nextDateTime.toLocalDate().isBefore(date));
        }

        return tasksByDay;
    }
}
