package agenda.controller;

import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.model.base.User;
import agenda.model.repository.interfaces.RepositoryActivity;
import agenda.model.repository.interfaces.RepositoryContact;
import agenda.model.repository.interfaces.RepositoryUser;

import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

public class MainController {
    private RepositoryContact contactRep;
    private RepositoryUser userRep;
    private RepositoryActivity activityRep;

    public MainController(RepositoryContact contactRep, RepositoryUser userRep, RepositoryActivity activityRep) {
        this.contactRep = contactRep;
        this.userRep = userRep;
        this.activityRep = activityRep;
    }

    public List<Activity> activitiesOnDate(String name, Date d) {
        return activityRep.activitiesOnDate(name, d);
    }

    public boolean addActivity(Activity activity) {
        return activityRep.addActivity(activity);
    }

    public void addContact(Contact contact) {
        contactRep.addContact(contact);
    }

    public User getByUsername(String username) {
        return userRep.getByUsername(username);
    }

    public int count() {
        return contactRep.count();
    }

    public List<Activity>  getActivities() {
        return activityRep.getActivities();
    }

    public int countAct() {
        return activityRep.count();
    }
}
