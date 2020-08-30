package docker;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerCertificates;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;

import java.nio.file.Paths;

public class RemoteTlsDockerMain {
    private static final String DOCKER_HTTPS_IP = "https://52.79.57.81:2376";

    public static void main(String[] args) throws DockerCertificateException, DockerException, InterruptedException {
        DockerClient client = DefaultDockerClient.builder()
                .uri(DOCKER_HTTPS_IP)
                .dockerCertificates(new DockerCertificates(Paths.get("src/main/resources/docker_keystore")))
                .build();

        System.out.println(client.info());
        client.close();
    }
}
