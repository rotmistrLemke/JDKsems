package Sem04.src;


public class Main {
    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();

        // Создание и добавление сотрудников


        // Основной цикл программы
        boolean continueProgram = true;
        while (continueProgram) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить нового сотрудника");
            System.out.println("2. Найти сотрудников по стажу");
            System.out.println("3. Найти номера телефонов по имени");
            System.out.println("4. Найти сотрудника по табельному номеру");
            System.out.println("5. Выход");

            try {
                System.out.print("Введите номер действия: ");
                int action = Integer.parseInt(directory.scanner.nextLine());

                switch (action) {
                    case 1:
                        System.out.println("\nДобавьте нового сотрудника:");
                        directory.addEmployeeWithInput();
                        break;
                    case 2:
                        directory.searchEmployeesByExperience();
                        break;
                    case 3:
                        directory.searchPhoneNumbersByName();
                        break;
                    case 4:
                        directory.searchEmployeeByTabNumber();
                        break;
                    case 5:
                        continueProgram = false;
                        break;
                    default:
                        System.out.println("Некорректный выбор. Пожалуйста, попробуйте снова.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Пожалуйста, введите целое число.");
            }
        }

        System.out.println("Программа завершена.");
    }
}