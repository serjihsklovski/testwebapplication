package database.factory;

public interface DaoFactory<DaoType> {

    DaoType getDao();
}
