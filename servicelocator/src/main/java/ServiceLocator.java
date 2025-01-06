import java.util.HashMap;

public class ServiceLocator {

    private final ServiceInitializationContext serviceInitializationContext;

    private final HashMap<String, Object> map = new HashMap<>();

    public ServiceLocator(ServiceInitializationContext serviceInitializationContext) {
        this.serviceInitializationContext = serviceInitializationContext;
    }

    @SuppressWarnings("unchecked")
    public <T> T getService(String serviceName) throws Exception {
        if (map.containsKey(serviceName)) {
            return (T) map.get(serviceName);
        }

        T service = serviceInitializationContext.getService(serviceName);
        map.put(serviceName, service);
        return service;
    }
}
