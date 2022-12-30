package view;

import model.Task;
import model.TaskMap;
import java.util.Collection;
import java.util.Scanner;

public class View {

    public void printVisualSeparator() {
        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println();
    }

    public String getStringInput() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ввод: ");
        String s = scanner.nextLine();
        System.out.println(s);
        return s;
    }

    public void printMainMenu(){
        System.out.println("1 - Получить все задачи");
        System.out.println("2 - Получить задачи на день");
        System.out.println("3 - Добавить задачу");
        System.out.println("4 - Удалить задачу");
        System.out.println();
        System.out.println("0 - Выход");
    }

    public void printDeleteTask(){
        printVisualSeparator();
        System.out.println("Введите ID задачи которую необходимо удалить:");
        System.out.println("Для показа всех задач введите \"показать\"");
        System.out.println("Для отмены операции введите \"отмена\"");
    }

    public void printDeletedError(){
        printVisualSeparator();
        System.out.println("Данной задачи не существует");
    }
    public void printHeadingInfo(){
        printVisualSeparator();
        System.out.println("Введите заголовок");
    }

    public void printDescriptionInfo(){
        printVisualSeparator();
        System.out.println("Введите содержание задачи");
    }

    public void printTypeSelection(){
        printVisualSeparator();
        System.out.println("1 - Личная заметка");
        System.out.println("2 - Рабочая заметка");
    }

    public void printTaskRepeatabilitySelection(){
        printVisualSeparator();
        System.out.println("1 - Одноразовая заметка ");
        System.out.println("2 - Ежедневная заметка");
        System.out.println("3 - Еженедельная заметка");
        System.out.println("4 - Ежемесячная заметка");
        System.out.println("5 - Ежегодная заметка");
    }

    public void printDateSelection(){
        printVisualSeparator();
        System.out.println("Введите дату начала заметки в формате \" год-месяц-день (1234-01-22) \"");
        System.out.println("Если вы хотите выбрать нынешюю дату и время просто нажмите ENTER");
    }

    public void printHeadAndDescriptionError(){
        System.out.println("Заголовок или описание не могут быть пустыми");
    }

    public void printAllTasks(TaskMap taskMap){
        printVisualSeparator();
        taskMap.getTasksMap().forEach((k, v) -> System.out.println(k + " " + v.getHead() + "\n" +
                v.getTaskType().toString() + "\n" +
                v.getTaskRepeatability().toString() + "\n" +
                v.getCreationDate() + "\n" +
                v.getDescription() + "\n" +
                "--------------------------------------------------------" + "\n")) ;
    }

    public void printTasksByDay(Collection<Task> tasks){
        printVisualSeparator();
        tasks.forEach((task) -> System.out.println(task.getId() + " " + task.getHead() + "\n" +
                task.getTaskType().toString() + "\n" +
                task.getTaskRepeatability().toString() + "\n" +
                task.getCreationDate() + "\n" +
                task.getDescription() + "\n" +
                "--------------------------------------------------------" + "\n")) ;
    }

    public void printTaskByDaySelection(){
        printVisualSeparator();
        System.out.println("Введите день в формате год-месяц-день чтобы вывести задачи на данный день");
        System.out.println("Если вы не хотитете вводить время нажмите ENTER");
    }

    public void printTimeSelection(){
        printVisualSeparator();
        System.out.println("Введите день в формате ЧАСЫ:МИНУТЫ (12:34)");
        System.out.println("Если вы не хотитете вводить время нажмите ENTER, тогда время и дата будут текущие");
    }

    public void printGreeting() {
        printVisualSeparator();
        System.out.println("**** Добро пожаловать в еженедельник! ****");
    }

    public void printFarewell() {
        printVisualSeparator();
        System.out.println("Спасибо за использование еженедельника");
    }
}
