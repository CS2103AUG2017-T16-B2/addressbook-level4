package seedu.address.model.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class PhoneTest {

    @Test
    public void isValidPhone() {
        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("student/97272031 parent/9797979")); // parent do not have exactly 8 digit
        assertFalse(Phone.isValidPhone("student/9727 parent/979")); // both do not have exactly 8 digit
        assertFalse(Phone.isValidPhone("student/9727 parent/97979797")); // student do not have exactly 8 digit
        assertFalse(Phone.isValidPhone("student:97272031 parent:97979797")); // use : instead of /
        assertFalse(Phone.isValidPhone("studen/97272031 paret/97979797")); // wrong labelling
        assertFalse(Phone.isValidPhone("student/97272031   parent/97979797")); // consecutive whitespaces
        assertFalse(Phone.isValidPhone("!@@#$#@$#@{}")); // random symbol
        assertFalse(Phone.isValidPhone("student/972jb72031 parent/97jhb979797")); // alphanumeric numbers
        assertFalse(Phone.isValidPhone("student/gfxgfx parent/gfxgfxgfx")); // purely alphabets

        // valid phone numbers
        assertTrue(Phone.isValidPhone("Student: 97272031 Parent: 97979797")); // exactly 8 numbers
        assertTrue(Phone.isValidPhone("Student: 87272111 Parent: 87767988")); // another set of exact 8 digit numbers
    }

    @Test
    public void changeToAppropriateUiFormat() {
        // Invalid Ui Format
        assertNotEquals(Phone.changeToAppropriateUiFormat("student/97272030 parent/97979797"),
                "Student:97272030 Parent:97979797"); // No spacing after Student: and Parent:
        assertNotEquals(Phone.changeToAppropriateUiFormat("student/97272030 parent/97979797"),
                "student:97272030 parent:97979797"); // No capital letter S and P
        assertNotEquals(Phone.changeToAppropriateUiFormat("student/97272030 parent/97979797"),
                "student:97272011 parent:97979700"); // Incorrect numbers
        assertNotEquals(Phone.changeToAppropriateUiFormat("student/97272030 parent/97979797"),
                "student/ 97272011 parent/ 97979700"); // / does not change to :
        assertNotEquals(Phone.changeToAppropriateUiFormat("student/97272030 parent/97979797"),
                "student: 97272011 Parent: 97979700"); // Letter s not capitalised
        assertNotEquals(Phone.changeToAppropriateUiFormat("student/97272030 parent/97979797"),
                "Student: 97272011 parent: 97979700"); // Letter p not capitalised


        // Valid Ui Format
        assertEquals(Phone.changeToAppropriateUiFormat("student/97272030 parent/97979797"),
                "Student: 97272030 Parent: 97979797"); // valid Ui format
        assertEquals(Phone.changeToAppropriateUiFormat("student/97278977 parent/97979776"),
                "Student: 97278977 Parent: 97979776"); // valid Ui format with another set of numbers
        assertEquals(Phone.changeToAppropriateUiFormat("student/82278977 parent/97979776"),
                "Student: 82278977 Parent: 97979776"); // valid Ui format with another set of numbers
    }
}
