package listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author gaorj
 * @date 2018/8/30 15:22
 * @description
 */
public class MyServletContextListener implements ServletContextListener {
    /**
     * application创建时执行
     * @param servletContextEvent
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("项目启动");
        ServletContext application = servletContextEvent.getServletContext();
        String path = application.getInitParameter("path");
        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        application.setAttribute("context",context);//将SpringIOC容器存入jsp的application对象中
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("项目销毁");
    }
}
