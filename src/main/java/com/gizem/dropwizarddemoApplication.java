package com.gizem;

import com.gizem.api.TodoItem;
import com.gizem.db.TodoItemDao;
import com.gizem.health.TemplateHealthCheck;
import com.gizem.resources.TodoResource;
import com.gizem.service.TodoService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class dropwizarddemoApplication extends Application<dropwizarddemoConfiguration> {

    private HibernateBundle<dropwizarddemoConfiguration> hibernate = new HibernateBundle<dropwizarddemoConfiguration>(TodoItem.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(dropwizarddemoConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };
    
    public static void main(final String[] args) throws Exception {
        new dropwizarddemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-hibernate";
    }

    @Override
    public void initialize(final Bootstrap<dropwizarddemoConfiguration> bootstrap) {
       bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(dropwizarddemoConfiguration configuration, Environment environment) throws Exception{

        TodoItemDao todoItemDao = new TodoItemDao(hibernate.getSessionFactory());
        TodoService service = new TodoService(todoItemDao);

        final TodoResource resource = new TodoResource(service);
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template",healthCheck);

        environment.jersey().register(resource);
    }
}
