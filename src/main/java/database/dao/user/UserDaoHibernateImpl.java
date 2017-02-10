package database.dao.user;

import database.model.user.User;
import helper.HibernateSessionFactory;
import org.hibernate.Session;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createTableIfNotExists() throws SQLException {
        // NOP
    }

    @Override
    public void dropTableIfExists() throws SQLException {
        // NOP
    }

    @Override
    public long insert(User user) throws SQLException {
        Session session = HibernateSessionFactory
                .getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();

            user.setId((long) session.save(user));

            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user.getId();
    }

    @Override
    public User get(long id) throws SQLException {
        User user = null;
        Session session = HibernateSessionFactory
                .getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();
            user = session.get(User.class, id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return user;
    }

    @Override
    public List<User> getList() throws SQLException {
        List<User> users = new LinkedList<>();
        Session session = HibernateSessionFactory
                .getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();
            users = session.createQuery("FROM User", User.class).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return users;
    }

    @Override
    public boolean update(User user) throws SQLException {
        boolean res = false;
        Session session = HibernateSessionFactory
                .getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.update(user);
            session.flush();
            session.getTransaction().commit();

            res = true;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return res;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        boolean res = false;
        Session session = HibernateSessionFactory
                .getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();

            User user = session.load(User.class, id);
            session.delete(user);

            session.flush();
            session.getTransaction().commit();

            res = true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return res;
    }

    @Override
    public User getByLogin(String login) throws SQLException {
        String hql = "FROM User WHERE login = :login";
        User user = null;
        Session session = HibernateSessionFactory
                .getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();

            user = session.createQuery(hql, User.class)
                    .setParameter("login", login)
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return user;
    }

    @Override
    public User getByEmail(String email) throws SQLException {
        String hql = "FROM User WHERE login = :login";
        User user = null;
        Session session = HibernateSessionFactory
                .getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();

            user = session.createQuery(hql, User.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return user;
    }

    @Override
    public boolean updateLogin(long id, String login) throws SQLException {
        return updateUserField(id, "login", login);
    }

    @Override
    public boolean updateEmail(long id, String email) throws SQLException {
        return updateUserField(id, "email", email);
    }

    @Override
    public boolean updatePassword(long id, String password) throws SQLException {
        return updateUserField(id, "password", password);
    }

    @Override
    public boolean updateRole(long id, String role) throws SQLException {
        return updateUserField(id, "role", role);
    }

    private <T> boolean updateUserField(long id, String fieldName, T fieldValue) {
        String hql = String.format("UPDATE User u SET u.%s = :{0} WHERE u.id = :id", fieldName);
        boolean result = false;
        Session session = HibernateSessionFactory
                .getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();

            int updatedEntities = session
                    .createQuery(hql)
                    .setParameter("id", id)
                    .setParameter(fieldName, fieldValue)
                    .executeUpdate();

            result = updatedEntities != 0;

            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
}
