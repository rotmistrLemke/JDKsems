package Sem04.src;

import java.util.*;

import Sem04.Employee;

public class EmployeeDirectory {
    private Map<String, Employee> employees;
    Scanner scanner;

    public EmployeeDirectory() {
        this.employees = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    // Метод для добавления нового сотрудника
    public void addEmployee(Employee employee) {
        employees.put(employee.getTabNumber(), employee);
    }

    // Метод для поиска сотрудников по стажу
    public List<Employee> findEmployeesByExperience(int experience) {
        return employees.values().stream()
                .filter(e -> e.getExperience() == experience)
                .toList();
    }

    // Метод для получения номера телефона по имени
    public List<String> getPhoneNumbersByName(String name) {
        return employees.values().stream()
                .filter(e -> e.getName().equals(name))
                .map(Employee::getPhoneNumber)
                .toList();
    }

    // Метод для поиска сотрудника по табельному номеру
    public Employee findEmployeeByTabNumber(String tabNumber) {
        return employees.get(tabNumber);
    }
    public void searchEmployeesByExperience() {
        try {
            System.out.print("Введите стаж работы для поиска: ");
            int experience = Integer.parseInt(scanner.nextLine());

            List<Employee> foundEmployees = findEmployeesByExperience(experience);

            if (foundEmployees.isEmpty()) {
                System.out.println("Сотрудники с таким стажем не найдены.");
            } else {
                System.out.println("Найденные сотрудники со стажем " + experience + " лет:");
                foundEmployees.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.err.println("Пожалуйста, введите целое число.");
        }
    }

    // Новый метод для поиска номеров телефонов по имени с вводом данных
    public void searchPhoneNumbersByName() {
        System.out.print("Введите имя для поиска: ");
        String name = scanner.nextLine();

        List<String> phoneNumbers = getPhoneNumbersByName(name);

        if (phoneNumbers.isEmpty()) {
            System.out.println("Сотрудники с именем '" + name + "' не найдены.");
        } else {
            System.out.println("Найденные номера телефонов для '" + name + "':");
            phoneNumbers.forEach(System.out::println);
        }
    }

    // Новый метод для поиска сотрудника по табельному номеру с вводом данных
    public void searchEmployeeByTabNumber() {
        System.out.print("Введите табельный номер для поиска: ");
        String tabNumber = scanner.nextLine();

        Employee foundEmployee = findEmployeeByTabNumber(tabNumber);

        if (foundEmployee != null) {
            System.out.println("Найденный сотрудник:");
            System.out.println(foundEmployee);
        } else {
            System.out.println("Сотрудник с табельным номером '" + tabNumber + "' не найден.");
        }
    }
       public void addEmployeeWithInput() {
        try {
            System.out.print("Введите табельный номер: ");
            String tabNumber = scanner.nextLine();

            if (employees.containsKey(tabNumber)) {
                throw new IllegalArgumentException("Сотрудник с таким табельным номером уже существует");
            }

            System.out.print("Введите номер телефона: ");
            String phoneNumber = scanner.nextLine();

            System.out.print("Введите имя: ");
            String name = scanner.nextLine();

            int experience;
            while (true) {
                try {
                    System.out.print("Введите стаж работы: ");
                    experience = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Пожалуйста, введите целое число.");
                }
            }

            Employee employee = new Employee(tabNumber, phoneNumber, name, experience);
            employees.put(tabNumber, employee);

            System.out.println("Сотрудник успешно добавлен!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
