package agenda.test;

import agenda.controller.MainController;
import agenda.exceptions.InvalidFormatException;
import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.model.base.User;
import agenda.model.repository.classes.RepositoryActivityMock;
import agenda.model.repository.classes.RepositoryContactMock;
import agenda.model.repository.classes.RepositoryUserMock;
import agenda.model.repository.interfaces.RepositoryActivity;
import agenda.model.repository.interfaces.RepositoryContact;
import agenda.model.repository.interfaces.RepositoryUser;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class IntegrationBBTest {

    private RepositoryActivity repAct;
    private RepositoryContact repCon;
    private RepositoryUser repUser;
    private MainController mainController;

    @Before
    public void setup() {
        repCon = new RepositoryContactMock();
        repAct = new RepositoryActivityMock();
        repUser = new RepositoryUserMock();

        repUser.add(new User("test", "testUser", "testPass"));
        mainController = new MainController(repCon, repUser, repAct);
    }

    @Test
    public void testRepContact() {
        int n = repCon.count();

        try {
            Contact c = new Contact("name", "address1", "+071122334455");
            repCon.addContact(c);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        assertEquals(n + 1, repCon.count());
    }

    @Test
    public void testRepActivity() {
        Activity act = null;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            act = new Activity("name1", df.parse("03/20/2013 12:00"),
                    df.parse("03/20/2013 13:00"), null, "Lunch break");
            repAct.addActivity(act);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertTrue(repAct.getActivities().get(0).equals(act)
                && repAct.count() == 1);
    }

    @Test
    public void testRepUser() {
        assertSame("test", repUser.getByUsername("testUser").getName());
    }

    @Test
    public void testMainController() {
        int n = mainController.count();

        try {
            Contact c = new Contact("name", "address1", "0745874569");
            mainController.addContact(c);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        assertEquals(n + 1, mainController.count());

        Activity act = null;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            act = new Activity("name1", df.parse("03/20/2013 12:00"),
                    df.parse("03/20/2013 13:00"), null, "Lunch break");
            mainController.addActivity(act);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertTrue(mainController.getActivities().get(0).equals(act)
                && mainController.countAct() == 1);

        assertSame("test", mainController.getByUsername("testUser").getName());

    }

}
