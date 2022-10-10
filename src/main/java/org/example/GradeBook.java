package org.example;

import org.example.exception.ElementAlreadyExistsException;
import org.example.exception.InvalidArgumentsException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class GradeBook {
    private final List<Subject> subjects;

    public GradeBook(List<Subject> subjects) throws InvalidArgumentsException {
        if (Objects.isNull(subjects)) {
            throw new InvalidArgumentsException("Subjects can not be null");
        }
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) throws ElementAlreadyExistsException, InvalidArgumentsException {
        if (Objects.isNull(subject)) {
            throw new InvalidArgumentsException("Subject can not be null");
        }

        var sub = subjects.stream().filter(s -> s.getName().equals(subject.getName())).findFirst().orElse(null);
        if (Objects.nonNull(sub)) {
            throw new ElementAlreadyExistsException("Subject already exists");
        }

        subjects.add(subject);
    }

    public Subject findSubjectByName(String name) {
        return subjects.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    public void addGradeToSubject(Integer grade, String name) {
        var subject = findSubjectByName(name);

        if (Objects.equals(subject, null)) {
            throw new NoSuchElementException("No elements found by name: [ " + name + " ]");
        }
        subject.addGrade(grade);
    }

    public double findAverageGrade() {
//        double sum = 0.0;
//
//        for (var s : subjects) {
//            sum += s.findAverage();
//        }
//
//        return sum / subjects.size();

        return subjects.stream().map(Subject::getGrades).flatMapToDouble(grades -> grades.stream().mapToDouble(Integer::doubleValue)).average().orElse(0.0);
    }
}
