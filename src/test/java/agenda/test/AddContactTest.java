package agenda.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import agenda.exceptions.InvalidFormatException;

import agenda.model.base.Contact;
import agenda.model.repository.classes.RepositoryContactMock;
import agenda.model.repository.interfaces.RepositoryContact;


public class AddContactTest {

    private Contact con;
    private RepositoryContact rep;

    @Before
    public void setUp() throws Exception {
        rep = new RepositoryContactMock();
    }

    @After
    public void tearDown() {
        rep.getContacts().forEach(contact -> rep.removeContact(contact));
    }

    @Test
    public void testCase1() {
        try {
            con = new Contact("name", "address1", "+4071122334455");
        } catch (InvalidFormatException e) {
            assertTrue(false);
        }
        //int n = rep.count();
        rep.addContact(con);
        for (Contact c : rep.getContacts())
            if (c.equals(con)) {
                assertTrue(true);
                break;
            }
        //assertTrue(n+1 == rep.count());
    }

    @Test
    public void testCase2() {
        try {
            rep.addContact((Contact) new Object());
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testCase3() {
        for (Contact c : rep.getContacts())
            rep.removeContact(c);

        try {
            con = new Contact("name", "address1", "+071122334455");
            rep.addContact(con);
        } catch (InvalidFormatException e) {
            assertTrue(false);
        }
        int n = rep.count();
        if (n == 1)
            if (con.equals(rep.getContacts().get(0))) assertTrue(true);
            else assertTrue(false);
        else assertTrue(false);
    }

    @Test
    public void testBB1() {
        try {
            con = new Contact("John", "Cluj-Napoca, jud. Cluj", "0743435234");
            rep.addContact(con);
        } catch (InvalidFormatException e) {
            assertTrue(false);
        }
        assertTrue(rep.getContacts().size() == 4);
        assertTrue(rep.getByName("John").getName().equals("John"));
    }

    @Test
    public void testBB2() {
        try {
            con = new Contact("John Bucker Witcher", "Cluj-Napoca, jud. Cluj", "0743435234");
            rep.addContact(con);
        } catch (InvalidFormatException e) {
            assertTrue(true);
            assertTrue(e.getCause().getMessage().contains("Invalid name"));
        }
        assertTrue(rep.getContacts().size() == 3);
    }

    @Test
    public void testBB3() {
        try {
            con = new Contact("John", "Cluj-Napoca, jud. Cluj", "0743 435q34");
            rep.addContact(con);
        } catch (InvalidFormatException e) {
            assertTrue(true);
            assertTrue(e.getCause().getMessage().contains("Invalid phone number"));
        }
        assertTrue(rep.getContacts().size() == 3);
    }

    @Test
    public void testBB4() {
        try {
            con = new Contact("John", "Cluj-Napoca, jud. Cluj", "+4074343523400");
            rep.addContact(con);
        } catch (InvalidFormatException e) {
            assertTrue(true);
            assertTrue(e.getCause().getMessage().contains("Invalid phone number"));
        }
        assertTrue(rep.getContacts().size() == 3);
    }

    @Test
    public void testBB5() {
        try {
            con = new Contact("", "Cluj-Napoca, jud. Cluj", "0743435234");
            rep.addContact(con);
        } catch (InvalidFormatException e) {
            assertTrue(true);
            assertTrue(e.getCause().getMessage().contains("Invalid name"));
        }
        assertTrue(rep.getContacts().size() == 3);
    }

    //BVA 13
    @Test
    public void testBB6() {
        try {
            con = new Contact("John", "Cluj-Napoca, jud. Cluj", "0743435234");
            rep.addContact(con);
        } catch (InvalidFormatException e) {
            assertTrue(false);
        }
        assertTrue(rep.getContacts().size() == 4);
        assertTrue(rep.getByName("John").getName().equals("John"));
    }

    //BVA 14
    @Test
    public void testBB7() {
        try {
            con = new Contact("John", "Cluj-Napoca, jud. Cluj", "074343523");
            rep.addContact(con);
        } catch (InvalidFormatException e) {
            assertTrue(true);
            assertTrue(e.getCause().getMessage().contains("Invalid phone number"));
        }
        assertTrue(rep.getContacts().size() == 3);
    }

    //BVA 15
    @Test
    public void testBB8() {
        try {
            con = new Contact("John", "Cluj-Napoca, jud. Cluj", "07434352345");
            rep.addContact(con);
        } catch (InvalidFormatException e) {
            assertTrue(true);
            assertTrue(e.getCause().getMessage().contains("Invalid phone number"));
        }
        assertTrue(rep.getContacts().size() == 3);
    }

    //BVA 15
    @Test
    public void testBB9() {
        try {
            con = new Contact("John", "Cluj-Napoca, jud. Cluj", "+40743435234");
            rep.addContact(con);
        } catch (InvalidFormatException e) {
            assertTrue(false);
        }
        assertTrue(rep.getContacts().size() == 4);
        assertTrue(rep.getByName("John").getName().equals("John"));
    }

    //BVA 16
    @Test
    public void testBB10() {
        try {
            con = new Contact("John", "Cluj-Napoca, jud. Cluj", "+407434352343");
            rep.addContact(con);
        } catch (InvalidFormatException e) {
            assertTrue(true);
            assertTrue(e.getCause().getMessage().contains("Invalid phone number"));
        }
        assertTrue(rep.getContacts().size() == 3);
    }


}
