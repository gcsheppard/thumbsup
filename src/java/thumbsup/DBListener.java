package thumbsup;

import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@DataSourceDefinition(
        name = "java:app/jdbc/images",
        className = "org.apache.derby.jdbc.ClientDataSource",
        url = "jdbc:derby://localhost:1527/",
        databaseName = "images",
        user = "app",
        password = "app")
@WebListener
public class DBListener implements ServletContextListener {
    @Resource(lookup = "java:app/jdbc/images")
    DataSource dataSource;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("++thumbsup");
        sce.getServletContext().setAttribute("imageManager", new ImageManager(dataSource));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("--thumbsup");
    }
}
