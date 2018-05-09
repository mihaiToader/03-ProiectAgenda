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

public class IntegrationTDTest {

    private RepositoryActivity repAct;
    private RepositoryContact repCon;

    @Before
    public void setup() throws Exception {
        repCon = new RepositoryContactMock();
        repAct = new RepositoryActivityMock();
        for (Activity a : repAct.getActivities())
            repAct.removeActivity(a);
    }

    @Test
    public void testFunctionA() throws Exception {
        Contact contact = new Contact("George", "", "0745682458");
        repCon.addContact(contact);
        assertEquals("George", repCon.getByName("George").getName());
        Contact contact1 = new Contact("Simona", "ppp", "0745682458");
        repCon.addContact(contact1);
        assertEquals("George", repCon.getByName("George").getName());
        Contact contact2 = new Contact("Mihai", "", "0745682458");
        repCon.addContact(contact2);
        assertEquals("George", repCon.getByName("George").getName());
        try {
            Contact contact3 = new Contact("George", "", "");
            repCon.addContact(contact3);
            fail();
        } catch (Exception m) {
            assertTrue(true);
        }
    }

    @Test
    public void testFunctionAPlusB() throws Exception {
        Contact Contact = new Contact("Georg1e", "", "0715489856");
        repCon.addContact(Contact);
        Contact Contact1 = new Contact("Simona", "", "0715489856");
        repCon.addContact(Contact1);
        Contact Contact2 = new Contact("Mihai", "", "0715489856");
        repCon.addContact(Contact2);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Activity Activity = new Activity("name1",
                df.parse("03/20/2013 12:00"),
                df.parse("03/20/2013 13:00"),
                null,
                "Lunch break");
        repAct.addActivity(Activity);
        Activity Activity1 = new Activity("name1",
                df.parse("03/20/2013 12:00"),
                df.parse("03/20/2013 13:00"),
                null,
                "Lunch break");
        repAct.addActivity(Activity1);
        Activity Activity2 = new Activity("name1",
                df.parse("03/20/2013 12:00"),
                df.parse("03/20/2013 13:00"),
                null,
                "Lunch break");
        repAct.addActivity(Activity2);
        assertEquals(1, repAct.getActivities().size());
    }
    @Test
    public void testFunctionAPlusBPlusC() throws Exception {
        Calendar c = Calendar.getInstance();
        c.set(2013, 3 - 1, 20, 12, 00);
        Date start = c.getTime();
        c.set(2013, 3 - 1, 20, 12, 30);
        Date end = c.getTime();
        Contact Contact = new Contact("name1", "", "0711223344");
        repCon.addContact(Contact);
        Contact Contact1 = new Contact("Simona", "", "0711223344");
        repCon.addContact(Contact1);
        Contact Contact2 = new Contact("Mihai", "", "0711223344");
        repCon.addContact(Contact2);
        Activity act = new Activity("name1", start, end,
                new LinkedList<Contact>(), "description2");
        repAct.addActivity(act);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Activity Activity1 = new Activity("name1",
                df.parse("03/20/2013 12:00"),
                df.parse("03/20/2013 13:00"),
                null,
                "Lunch break");
        Activity Activity2 = new Activity("name1",
                df.parse("03/20/2013 12:00"),
                df.parse("03/20/2013 13:00"),
                null,
                "Lunch break");
        assertEquals(1, repAct.activitiesOnDate("name1", c.getTime()).size());
        repAct.addActivity(Activity1);
        assertEquals(1, repAct.activitiesOnDate("name1", c.getTime()).size());
        repAct.addActivity(Activity2);
        c.set(2013, 3 - 1, 20);
        List<Activity> result = repAct.activitiesOnDate("name1", c.getTime());
        assertEquals(1, repAct.activitiesOnDate("name1", c.getTime()).size());
    }
}

