package de.messetat.sling.core.components.util;

import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.Group;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class UserHelper {

    private static final Logger LOG = LoggerFactory.getLogger(UserHelper.class);


    public static List<Authorizable> getEveryone(ResourceResolver resourceResolver) throws RepositoryException {
        LOG.trace("getEveryone");
        List<Authorizable> allUsers = new LinkedList<>();
        final UserManager userManager = AdapterHelper.adaptTo(resourceResolver, UserManager.class);

        Group group = (Group) userManager.getAuthorizable("everyone");
        if (userManager != null) {

            Iterator<Authorizable> authorizableIterator = group.getMembers();
            for (Authorizable authorizable : new IterableIterator<>(authorizableIterator)) {
                allUsers.add(authorizable);
            }
        }
        return allUsers;
    }

    public static List<Authorizable> getAllUsers(ResourceResolver resourceResolver) throws RepositoryException {
        List<Authorizable> allUsers = new LinkedList<>();
        final UserManager userManager = AdapterHelper.adaptTo(resourceResolver, UserManager.class);

        if (userManager != null) {
            Iterator<Authorizable> authorizableIterator = userManager.findAuthorizables("jcr:primaryType", "rep:User");

            for (Authorizable authorizable : new IterableIterator<>(authorizableIterator)) {
                allUsers.add(authorizable);
            }
        }
        return allUsers;
    }

    public List<Authorizable> getAllGroups(ResourceResolver resourceResolver) throws RepositoryException {
        List<Authorizable> allUsers = new LinkedList<>();
        final UserManager userManager = AdapterHelper.adaptTo(resourceResolver, UserManager.class);

        if (userManager != null) {
            Iterator<Authorizable> authorizableIterator = userManager.findAuthorizables("jcr:primaryType", "rep:Group");

            for (Authorizable authorizable : new IterableIterator<>(authorizableIterator)) {
                allUsers.add(authorizable);
            }
        }
        return allUsers;
    }
}
