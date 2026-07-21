/**
 * Thrown when an attempt is made to enroll a student in a course
 * that has already reached its maximum capacity.
 */
public class CourseFullException extends Exception {

    public CourseFullException(String message) {
        super(message);
    }
}
