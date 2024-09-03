package by.grsu.liceum.repository;

public interface MinioRepository {
    void makeBucket(String bucketName);
    void upload(String bucketName, String object, String path);
    void unload(String bucketName, String object);
    boolean bucketExists(String bucketName);
}
