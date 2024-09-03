package by.grsu.liceum.repository.impl;

import by.grsu.liceum.repository.MinioRepository;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MinioRepositoryImpl implements MinioRepository { //todo replace @SneakyThrows with try/catch
    private final MinioClient minioClient;

    @Override
    @SneakyThrows
    public void makeBucket(String bucketName) {
        if(!bucketExists(bucketName)) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());

            log.info("Created bucket with name {}", bucketName);
        } else
            log.info("Bucket with name {} already exists", bucketName);
    }

    @Override
    @SneakyThrows
    public void upload(String bucketName, String object, String path) {
        minioClient.uploadObject(UploadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(object)
                        .filename(path)
                .build());

        log.info("'{}' is successfully uploaded as object '{}' to bucket '{}'", path, object, bucketName);
    }

    @Override
    @SneakyThrows
    public void unload(String bucketName, String object) {
        minioClient.getObject(GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(object)
                .build());
    }

    @Override
    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
    }
}
