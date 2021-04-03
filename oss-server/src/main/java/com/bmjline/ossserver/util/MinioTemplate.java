package com.bmjline.ossserver.util;

import com.bmjline.ossserver.config.MinioClientConfig;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import io.minio.SetBucketPolicyArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.MinioException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.messages.Bucket;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

/**
 * @author bmj
 */
@Component("minioTemplate")
public class MinioTemplate {

    private final MinioClientConfig minioClientConfig;

    public MinioTemplate(MinioClientConfig minioClientConfig) {
        this.minioClientConfig = minioClientConfig;
    }

    private String prepareBucketPolicy(String bucket) {
        return "{\n" +
                "   \"Statement\": [\n" +
                "       {\n" +
                "           \"Action\": [\n" +
                "               \"s3:GetBucketLocation\",\n" +
                "               \"s3:ListBucket\"\n" +
                "           ],\n" +
                "           \"Effect\": \"Allow\",\n" +
                "           \"Principal\": \"*\",\n" +
                "           \"Resource\": \"arn:aws:s3:::" + bucket + "\"\n" +
                "       },\n" +
                "       {\n" +
                "           \"Action\": [\n" +
                "               \"s3:GetObject\",\n" +
                "               \"s3:DeleteObject\",\n" +
                "               \"s3:PutObject\"\n" +
                "           ],\n" +
                "           \"Effect\": \"Allow\",\n" +
                "           \"Principal\": \"*\",\n" +
                "           \"Resource\": \"arn:aws:s3:::" + bucket + "/*\"\n" +
                "       }\n" +
                "   ],\n" +
                "   \"Version\": \"2012-10-17\"\n" +
                "}";
    }

    /**
     * 创建bucket
     *
     * @param bucket bucket名称
     */
    public void bucketExists(String bucket) throws MinioException, NoSuchAlgorithmException, InvalidKeyException,
            IOException {
        if (!minioClientConfig.minioClient().bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
            minioClientConfig.minioClient().makeBucket(MakeBucketArgs.builder()
                    .bucket(bucket)
                    .build());
            minioClientConfig.minioClient().setBucketPolicy(SetBucketPolicyArgs.builder()
                    .bucket(bucket)
                    .config(prepareBucketPolicy(bucket))
                    .build());
        }
    }

    /**
     * 获取全部bucket
     * <p>
     * https://docs.minio.io/cn/java-client-api-reference.html#listBuckets
     */
    public List<Bucket> getAllBuckets() throws MinioException, NoSuchAlgorithmException, InvalidKeyException,
            IOException {
        return minioClientConfig.minioClient().listBuckets();
    }

    /**
     * 根据bucketName获取信息
     *
     * @param bucket bucket名称
     */
    public Optional<Bucket> getBucket(String bucket) throws MinioException, NoSuchAlgorithmException,
            InvalidKeyException, IOException {
        return minioClientConfig.minioClient().listBuckets().stream().filter(b -> b.name().equals(bucket)).findFirst();
    }

    /**
     * 根据bucketName删除信息
     *
     * @param bucket bucket名称
     */
    public void removeBucket(String bucket) throws MinioException, NoSuchAlgorithmException, InvalidKeyException,
            IOException {
        minioClientConfig.minioClient().removeBucket(RemoveBucketArgs.builder().bucket(bucket).build());
    }

    public void uploadObjectByStream(String bucket, String objectName, InputStream inputStream) throws IOException,
            ServerException, InsufficientDataException, InternalException, InvalidResponseException,
            InvalidKeyException, NoSuchAlgorithmException, XmlParserException, ErrorResponseException {
        minioClientConfig.minioClient().putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .contentType("application/octet-stream")
                .stream(inputStream, inputStream.available(), -1)
                .build());
    }

    /**
     * 获取文件外链
     *
     * @param bucket     bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 <=7
     * @return url
     */
    public String getObjectUrl(String bucket, String objectName, Integer expires) throws MinioException,
            NoSuchAlgorithmException, InvalidKeyException, IOException {
        return minioClientConfig.minioClient().getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .expiry(expires)
                .build());
    }

    /**
     * 获取文件路径
     *
     * @param bucket
     * @param objectName
     * @return url
     */
    public String getObjectUrl(String bucket, String objectName) throws MinioException, NoSuchAlgorithmException,
            InvalidKeyException, IOException {
        return minioClientConfig.minioClient().getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .expiry(7)
                .build());
    }

    /**
     * 获取文件流
     *
     * @param bucket     bucket名称
     * @param objectName 文件名称
     * @return InputStream
     */
    public InputStream getObjectStream(String bucket, String objectName) throws MinioException,
            NoSuchAlgorithmException, InvalidKeyException, IOException {
        return minioClientConfig.minioClient().getObject(GetObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .build());
    }

    /**
     * 删除文件
     *
     * @param bucket     bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     */
    public void removeObject(String bucket, String objectName) throws MinioException, NoSuchAlgorithmException,
            InvalidKeyException, IOException {
        minioClientConfig.minioClient().removeObject(RemoveObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .build());
    }
}
