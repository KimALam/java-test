package docker;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;

public class LocalDockerdMain {
    private static final String DOCKER_IP = "unix:///var/run/docker.sock";

    public static void main(String[] args) throws DockerException, InterruptedException {
        DockerClient client = new DefaultDockerClient(DOCKER_IP);
        System.out.println(client.info());
        client.close();
    }
}
