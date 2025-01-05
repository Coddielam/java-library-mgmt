public class ServiceLocator {

    private final ServiceInitializationContext serviceInitializationContext;

    public ServiceLocator(ServiceInitializationContext serviceInitializationContext) {
        this.serviceInitializationContext = serviceInitializationContext;
    }

    public <T> T getService(String serviceName) throws Exception {
        return serviceInitializationContext.getService(serviceName);
    }
}
