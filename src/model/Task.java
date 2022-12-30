package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {

    private static Integer idCounter = 0;
    private final Integer id;
    private String head;
    private String description;
    private LocalDateTime creationDate;
    private TaskType taskType;
    private TaskRepeatability taskRepeatability;

    public Task(){
        this.id = idCounter++;
    }

    public String getHead() {
        return head;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public TaskRepeatability getTaskRepeatability() {
        return taskRepeatability;
    }

    public Integer getId() {
        return id;
    }

    public void setHead(String head) {
        if(head.isEmpty()){
            throw new IllegalArgumentException("Закголовок не может быть пустым");
        }
        this.head = head;
    }

    public void setDescription(String description) {
        if(description.isEmpty()){
            throw new IllegalArgumentException("Закголовок не может быть пустым");
        }
        this.description = description;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setTaskRepeatability(TaskRepeatability taskRepeatability) {
        this.taskRepeatability = taskRepeatability;
    }

    public void setCreationDate(int year, int month, int dayOfMonth, int hour, int minute) {
        this.creationDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    public void setCreationDate() {
        this.creationDate = LocalDateTime.now();
    }

    public enum TaskType{
        PERSONAL(){
            @Override
            public String toString() {
                return "Персональная";
            }
        },

        WORKING(){
            @Override
            public String toString() {
                return "Рабочая";
            }
        }
    }

    public enum TaskRepeatability{
        ONE_TIME(){
            @Override
            public LocalDateTime nextTime(LocalDateTime currentTime){
                return currentTime;
            }

            @Override
            public String toString() {
                return "Одноразовая";
            }
        },

        EVERY_DAY(){
            @Override
            public LocalDateTime nextTime(LocalDateTime currentTime){
                return currentTime.plusDays(1);
            }

            @Override
            public String toString() {
                return "Ежедневная";
            }
        },

        EVERY_WEEK(){
            @Override
            public LocalDateTime nextTime(LocalDateTime currentTime){
                return currentTime.plusWeeks(1);
            }

            @Override
            public String toString() {
                return "Еженедельная";
            }
        },

        EVERY_MONTH(){
            @Override
            public LocalDateTime nextTime(LocalDateTime currentTime){
                return currentTime.plusMonths(1);
            }

            @Override
            public String toString() {
                return "Ежемесячная";
            }
        },

        EVERY_YEAR(){
            @Override
            public LocalDateTime nextTime(LocalDateTime currentTime){
                return currentTime.plusYears(1);
            }

            @Override
            public String toString() {
                return "Ежегодняя";
            }
        };

        public abstract LocalDateTime nextTime(LocalDateTime currentTime);
    }
}
