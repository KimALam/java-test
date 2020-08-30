package docker;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerCertificates;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.HostConfig;
import com.spotify.docker.client.messages.PortBinding;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunNginxMain {
    private static final String DOCKER_HTTPS_IP = "https://52.79.57.81:2376";

    public static void main(String[] args) throws DockerCertificateException, DockerException, InterruptedException {
        DockerClient client = DefaultDockerClient.builder()
                .uri(DOCKER_HTTPS_IP)
                .dockerCertificates(new DockerCertificates(Paths.get("src/main/resources/docker_keystore")))
                .build();

        List<PortBinding> hostPort = new ArrayList<>();
        hostPort.add(PortBinding.of("10.10.10.244", "10080"));

        Map<String, List<PortBinding>> portBindings = new HashMap<>();
        portBindings.put("80/tcp", hostPort);

        final HostConfig hostConfig = HostConfig.builder()
                .portBindings(portBindings)
                .build();
        // container 생성
        final ContainerCreation container = client.createContainer(
                ContainerConfig.builder()
                        .image("nginx")
                        .hostConfig(hostConfig)
                        .attachStderr(false)
                        .attachStdin(false)
                        .attachStdout(false)
                        .build(), "mynginx");
        final String id = container.id();

        // container 시작
        client.startContainer(id);
    }
}
