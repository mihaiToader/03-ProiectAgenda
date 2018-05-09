package agenda.test;

import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.model.repository.classes.RepositoryActivityMock;
import agenda.model.repository.interfaces.RepositoryActivity;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestCerinta3 {

    private RepositoryActivity rep;

    @Before
    public void setup() {
        rep = new RepositoryActivityMock();
    }

    @Test
    public void testActivityReport1() {
        for (Activity activity : rep.getActivities())
            rep.removeActivity(activity);

        Calendar c = Calendar.getInstance();
        c.set(2012, 3, 22, 12, 50);
        Date start = c.getTime();

        System.out.println("n");
        c.set(2012, 3, 25, 12, 30);
        Date end = c.getTime();

        Activity activity = new Activity("name1", start, end,
                new LinkedList<Contact>(), "description2");

        rep.addActivity(activity);

        List<Activity> result = rep.activitiesOnDate("name1", c.getTime());
        assertEquals(1, result.size());
    }

    @Test
    public void testActivityReport2() {
        for (Activity activity : rep.getActivities())
            rep.removeActivity(activity);

        Calendar c = Calendar.getInstance();
        c.set(0, -3 , 22, 12, 123);
        Date start = c.getTime();

        c.set(2012, 3 , 25, 12, 30);
        Date end = c.getTime();

        Activity activity = new Activity("name1", start, end,
                new LinkedList<Contact>(), "description2");

        rep.addActivity(activity);

        List<Activity> result = rep.activitiesOnDate("n1", c.getTime());
        assertEquals(0, result.size());
    }
}
