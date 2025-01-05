public interface ServiceInitializationContext {
    <T> T getService(String serviceName) throws Exception;
}
