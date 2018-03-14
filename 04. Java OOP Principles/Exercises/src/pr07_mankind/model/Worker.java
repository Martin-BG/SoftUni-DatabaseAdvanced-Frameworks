package pr07_mankind.model;

public class Worker extends Human {

    private static final double MIN_WEEK_SALARY = 10d;
    private static final double MIN_WORK_HOURS = 1d;
    private static final double MAX_WORK_HOURS = 12d;
    private static final int MIN_LAST_NAME_LENGTH = 3;
    private static final double WORK_DAYS_PER_WEEK = 7d;

    private double weekSalary;
    private double workingHours;

    public Worker(String firstName, String lastName, double weekSalary, double workingHours) {
        super(firstName, lastName);
        this.setWeekSalary(weekSalary);
        this.setWorkingHours(workingHours);
    }

    public double getWeekSalary() {
        return this.weekSalary;
    }

    private void setWeekSalary(double weekSalary) {
        if (weekSalary <= MIN_WEEK_SALARY) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }
        this.weekSalary = weekSalary;
    }

    public double getWorkingHours() {
        return this.workingHours;
    }

    private void setWorkingHours(double workingHours) {
        if (workingHours < MIN_WORK_HOURS || workingHours > MAX_WORK_HOURS) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }

        this.workingHours = workingHours;
    }

    @Override
    void setLastName(String lastName) {
        if (lastName.length() <= MIN_LAST_NAME_LENGTH) {
            throw new IllegalArgumentException("Expected length more than 3 symbols!Argument: lastName");
        }

        super.setLastName(lastName);
    }

    @Override
    public String toString() {
        return String.format("%s%nWeek Salary: %.2f%nHours per day: %.2f%nSalary per hour: %.2f",
                super.toString(), this.getWeekSalary(), this.getWorkingHours(), this.getSalaryPerHour());
    }

    private double getSalaryPerHour() {
        return (this.getWeekSalary() / (WORK_DAYS_PER_WEEK * this.getWorkingHours()));
    }
}
