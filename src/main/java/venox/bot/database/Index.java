package venox.bot.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import venox.bot.globals.Constants;
import venox.bot.models.user.VenoxUser;

import java.util.Date;
import java.util.List;

public class Index {

    public static HikariDataSource MySqlConnection;
    private static final String host = "jdbc:mysql://localhost:3306/whatsappbot" ;
    private static final String user = "whatsappbot";
    private static final String pass = "7G!PLGo/mXaUF.Y9UvecYJOBK!)2rU.Q";
    private static HikariConfig config = new HikariConfig();
    public static HikariDataSource dataSource;

    public static SessionFactory sessionFactory;

    public static void CreateDatabaseConnection(){
        config.setJdbcUrl(host);
        config.setUsername(user);
        config.setPassword(pass);
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(20);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        dataSource = new HikariDataSource(config);

// Erstelle eine Configuration-Instanz
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", host);
        configuration.setProperty("hibernate.connection.username", user);
        configuration.setProperty("hibernate.connection.password", pass);
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.addAnnotatedClass(VenoxUser.class);
        sessionFactory = configuration.buildSessionFactory();


        System.out.println(dataSource);
        LoadDatabaseUser();
    }

    public static void LoadDatabaseUser(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<VenoxUser> users = session.createQuery("from VenoxUser", VenoxUser.class).getResultList();
        for (VenoxUser user:users){
            if(!Constants.Userlist.containsKey(user.getId()))
                Constants.Userlist.put(user.getId(), user);
            if(user.getAdminLevel() > 2)
                if(!Constants.AdminList.containsKey(user.getId()))
                    Constants.AdminList.put(user.getId(), user);
        }


        session.getTransaction().commit();
        session.close();
        System.out.print(Constants.Userlist.size() + " user has been loaded.");
    }

    public static VenoxUser InsertDatabaseUser(Long id, String name, String chatId){
        VenoxUser user = new VenoxUser();
        user.setId(Long.parseLong(id.toString().replaceAll("[^0-9]", "")));
        user.setVerified_name(name);
        user.setGroupId(chatId);
        user.setCreation_date(new Date().getTime());
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        user = session.get(VenoxUser.class, id);
        return user;
    }
}
