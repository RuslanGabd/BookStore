import com.ruslan.DI.ObjectFactory;
import com.ruslan.DI.context.ApplicationContext;
import com.ruslan.ui.MenuController;

import java.util.HashMap;

public class TestBookService {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext();
        ObjectFactory objectFactory = new ObjectFactory(applicationContext, new HashMap<>());
        applicationContext.setObjectFactory(objectFactory);
        MenuController menuController = applicationContext.getObject(MenuController.class);
        menuController.run();

    }
}
