package Sem04.src;

public class Employee {
    private String tabNumber;
    private String phoneNumber;
    private String name;
    private int experience;

    public Employee(String tabNumber, String phoneNumber, String name, int experience) {
        this.tabNumber = tabNumber;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }

    // Геттеры и сеттеры
    public String getTabNumber() { return tabNumber; }
    public void setTabNumber(String tabNumber) { this.tabNumber = tabNumber; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    @Override
    public String toString() {
        return "Employee{" +
                "tabNumber='" + tabNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                '}';
    }
}
