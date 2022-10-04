package org.example;

import org.example.exception.InvalidArgumentsException;

import java.util.ArrayList;
import java.util.Objects;

public class Subject {
    private final String name;
    private final ArrayList<Integer> grades;

    public Subject(String name, ArrayList<Integer> grades) throws InvalidArgumentsException {
        if (Objects.isNull(name) || Objects.equals(name, "") || Objects.isNull(grades)) {
            throw new InvalidArgumentsException("Name can not be null or empty. Grades can not be null");
        }

        this.name = name;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    public void addGrade(Integer grade) {
        grades.add(grade);
    }

    public double findAverage() {
        double sum = 0.0;
        for (var g : grades) {
            sum += g;
        }
        return sum / grades.size();
    }
}
