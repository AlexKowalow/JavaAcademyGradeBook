import org.example.GradeBook;
import org.example.Subject;
import org.example.exception.ElementAlreadyExistsException;
import org.example.exception.InvalidArgumentsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class TestGradeBook {

    @Test
    public void testCreateGradeBook() {
        var subjects = new ArrayList<Subject>();

        assertDoesNotThrow(() -> new GradeBook(subjects));
        assertThrows(InvalidArgumentsException.class, () -> new GradeBook(null));
    }

    @Test
    public void testCreateSubject() {
        var grades = new ArrayList<Integer>();
        var name = "subject";

        assertDoesNotThrow(() -> new Subject(name, grades));
        assertThrows(InvalidArgumentsException.class, () -> new Subject("", grades));
        assertThrows(InvalidArgumentsException.class, () -> new Subject(null, grades));
        assertThrows(InvalidArgumentsException.class, () -> new Subject(name, null));
    }

    @Test
    public void testAddSubjectToGradeBook() throws InvalidArgumentsException {
        var subjects = new ArrayList<Subject>();
        var gradeBook = new GradeBook(subjects);

        var name = "subject";
        var grades = new ArrayList<Integer>();
        var subject = new Subject(name, grades);

        assertDoesNotThrow(() -> gradeBook.addSubject(new Subject("subject1", grades)));

        assertThrows(ElementAlreadyExistsException.class, () -> {
            gradeBook.addSubject(subject);
            gradeBook.addSubject(subject);
        });

        assertThrows(InvalidArgumentsException.class, () -> gradeBook.addSubject(null));
    }

    @Test
    public void testGetSubjectByName() throws InvalidArgumentsException, ElementAlreadyExistsException {
        var subjects = new ArrayList<Subject>();
        var gradeBook = new GradeBook(subjects);

        var name1 = "subject1";
        var grades1 = new ArrayList<Integer>();
        var subject1 = new Subject(name1, grades1);

        var name2 = "subject2";
        var grades2 = new ArrayList<Integer>();
        var subject2 = new Subject(name2, grades2);

        var name3 = "subject3";
        var grades3 = new ArrayList<Integer>();
        var subject3 = new Subject(name3, grades3);

        gradeBook.addSubject(subject1);
        gradeBook.addSubject(subject2);
        gradeBook.addSubject(subject3);

        var name = "subject2";

        assertNotNull(gradeBook.findSubjectByName(name));
        assertNull(gradeBook.findSubjectByName(null));
    }

    @Test
    public void testAddGradeToSubject() throws InvalidArgumentsException, ElementAlreadyExistsException {

        var subjects = new ArrayList<Subject>();
        var gradeBook = new GradeBook(subjects);

        var name1 = "subject1";
        var grades1 = new ArrayList<Integer>();
        var subject1 = new Subject(name1, grades1);

        var name2 = "subject2";
        var grades2 = new ArrayList<Integer>();
        var subject2 = new Subject(name2, grades2);

        var name3 = "subject3";
        var grades3 = new ArrayList<Integer>();
        var subject3 = new Subject(name3, grades3);

        gradeBook.addSubject(subject1);
        gradeBook.addSubject(subject2);
        gradeBook.addSubject(subject3);

        var name = "subject2";
        var grade = 4;
        assertDoesNotThrow(() -> gradeBook.addGradeToSubject(grade, name));
        assertThrows(NoSuchElementException.class, () -> gradeBook.addGradeToSubject(grade, "subjectX"));
        assertThrows(NoSuchElementException.class, () -> gradeBook.addGradeToSubject(grade, null));
        assertTrue(gradeBook.findSubjectByName(name).getGrades().contains(grade));
    }

    @Test
    public void testFindAverageForSubject() throws InvalidArgumentsException {
        var name = "subject2";
        var grades = new ArrayList<Integer>();
        var subject = new Subject(name, grades);

        subject.addGrade(3);
        subject.addGrade(4);
        subject.addGrade(4);
        subject.addGrade(5);
        subject.addGrade(2);

        assertEquals(subject.findAverage(), 18.0 / 5, 0.001);
    }

    @Test
    public void testFindAverageForGradeBook() throws InvalidArgumentsException, ElementAlreadyExistsException {
        var subjects = new ArrayList<Subject>();
        var gradeBook = new GradeBook(subjects);

        var name1 = "subject1";
        var grades1 = new ArrayList<Integer>();
        var subject1 = new Subject(name1, grades1);

        subject1.addGrade(3);
        subject1.addGrade(4);
        subject1.addGrade(4);

        var name2 = "subject2";
        var grades2 = new ArrayList<Integer>();
        var subject2 = new Subject(name2, grades2);

        subject2.addGrade(2);
        subject2.addGrade(3);
        subject2.addGrade(5);

        var name3 = "subject3";
        var grades3 = new ArrayList<Integer>();
        var subject3 = new Subject(name3, grades3);

        subject3.addGrade(4);
        subject3.addGrade(4);
        subject3.addGrade(4);

        gradeBook.addSubject(subject1);
        gradeBook.addSubject(subject2);
        gradeBook.addSubject(subject3);

        assertEquals(gradeBook.findAverageGrade(), 33.0 / 9, 0.001);
    }
}
