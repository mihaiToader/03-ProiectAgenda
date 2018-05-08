package agenda.test;

import agenda.model.base.Activity;
import agenda.model.repository.classes.RepositoryActivityFile;
import agenda.model.repository.classes.RepositoryActivityMock;
import agenda.model.repository.classes.RepositoryContactFile;
import agenda.model.repository.interfaces.RepositoryActivity;
import agenda.model.repository.interfaces.RepositoryContact;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class WBTActivityTest {
	private Activity act;
	private RepositoryActivity rep;
	private RepositoryContact repContact;

	@Before
	public void setUp() throws Exception {
        rep = new RepositoryActivityMock();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        rep.addActivity(new Activity("theName", df.parse("03/20/2013 12:00"),
                df.parse("03/20/2013 13:00"), null, "Lunch break"));
        rep.addActivity(new Activity("theName", df.parse("03/20/2013 20:00"),
                df.parse("03/20/2013 21:00"), null, "Dinner"));

    }

	@Test
	public void testCase1()
	{
        assertEquals(2, rep.activitiesByName("theName").size());
	}

    @Test
    public void testCase2()
    {
        assertEquals(0, rep.activitiesByName("invalid").size());
    }

    @Test
    public void testCase3()
    {
        assertEquals(1, rep.activitiesByName("name2").size());
    }
}
