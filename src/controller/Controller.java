package controller;

import model.Task;
import model.TaskMap;
import view.View;

import java.time.LocalDate;
import java.time.YearMonth;

public class Controller {
    private View view;
    private TaskMap taskMap;

    public Controller(){
        view = new View();
        taskMap = new TaskMap();

        control();
    }

    public void deleteTaskForId(){
        while(true){
            view.printDeleteTask();
            String input = view.getStringInput();

            if(input.matches("^\\d*")){
                if(taskMap.deleteTaskFromId(Integer.parseInt(input)) != null){
                    taskMap.deleteTaskFromId(Integer.parseInt(input));
                    break;
                } else {
                    view.printDeletedError();
                }
            }else if(input.equalsIgnoreCase("показать")){
                view.printAllTasks(this.taskMap);
            }else if(input.equalsIgnoreCase("отмена")){
                break;
            }
        }
    }

    public void addTask(){
        Task task = new Task();
        view.printVisualSeparator();

        while (true) {
            view.printHeadingInfo();
            String inputHead = view.getStringInput();

            view.printDescriptionInfo();
            String inputDescription = view.getStringInput();

            if(!inputHead.isEmpty() && !inputDescription.isEmpty()) {
                task.setHead(inputHead);
                task.setDescription(inputDescription);
                break;
            } else {
                view.printHeadAndDescriptionError();
            }
        }

        String input;

        loop: while(true) {
            view.printTypeSelection();
            input = view.getStringInput();

            switch (input) {
                case "1":
                    task.setTaskType(Task.TaskType.PERSONAL);
                    break loop;

                case "2":
                    task.setTaskType(Task.TaskType.WORKING);
                    break loop;

                default:
                    System.out.println("Введены некоректные данные, попробуйте снова");
            }
        }

        loop: while(true) {
            view.printTaskRepeatabilitySelection();
            input = view.getStringInput();

            switch (input) {
                case "1":
                    task.setTaskRepeatability(Task.TaskRepeatability.ONE_TIME);
                    break loop;

                case "2":
                    task.setTaskRepeatability(Task.TaskRepeatability.EVERY_DAY);
                    break loop;

                case "3":
                    task.setTaskRepeatability(Task.TaskRepeatability.EVERY_WEEK);
                    break loop;

                case "4":
                    task.setTaskRepeatability(Task.TaskRepeatability.EVERY_MONTH);
                    break loop;

                case "5":
                    task.setTaskRepeatability(Task.TaskRepeatability.EVERY_YEAR);
                    break loop;

                default:
                    System.out.println("Введены некоректные данные, попробуйте снова");
            }
        }

        int year;
        int month;
        int days;

        while(true){
            view.printDateSelection();
            input = view.getStringInput();

            if(!input.isEmpty() && input.matches("^\\d{4}-\\d{2}-\\d*$")){
                year = Integer.parseInt(input.split("-")[0]);
                month = Integer.parseInt(input.split("-")[1]);
                days = Integer.parseInt(input.split("-")[2]);

                if(month <= 12 && days <= YearMonth.of(year, month).lengthOfMonth()) {
                    break;
                }
            }else{
                task.setCreationDate();
                return;
            }
        }

        int hour;
        int minutes;

        while(true){
            view.printTimeSelection();
            input = view.getStringInput();

            if(!input.isEmpty() && input.matches("^\\d{2}:\\d{2}$")){
                hour = Integer.parseInt(input.split(":")[0]);
                minutes = Integer.parseInt(input.split(":")[1]);

                if (hour <= 24 && minutes <= 59){
                    task.setCreationDate(year, month, days, hour, minutes);
                    break;
                }
            }
        }

        taskMap.add(task);
    }

    public void findTaskByDay(){
        while(true){
            view.printTaskByDaySelection();
            String input = view.getStringInput();

            if(!input.isEmpty()){
                int year = Integer.parseInt(input.split("-")[0]);
                int month = Integer.parseInt(input.split("-")[1]);
                int days = Integer.parseInt(input.split("-")[2]);

                if (input.matches("^\\d{4}-\\d{2}-\\d*$")
                        && month <= 12
                        && days <= YearMonth.of(year, month).lengthOfMonth()) {
                    view.printTasksByDay(taskMap.getTaskByDay(LocalDate.of(year, month, days)));
                    break;
                }
            } else {
                view.printTasksByDay(taskMap.getTaskByDay(LocalDate.now()));
                break;
            }
        }
    }

    public void control(){
        view.printGreeting();
        view.printVisualSeparator();

        loop: while(true){
            view.printMainMenu();
            String input = view.getStringInput();

            switch (input) {
                case "1":
                    view.printAllTasks(this.taskMap);
                    break;

                case "2":
                    findTaskByDay();
                    break;

                case "3":
                    addTask();
                    break;

                case "4":
                    deleteTaskForId();
                    break;

                case "0":
                    view.printFarewell();
                    break loop;
            }
        }
    }
}
