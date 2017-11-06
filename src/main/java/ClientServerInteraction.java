import java.io.IOException;

public interface ClientServerInteraction {

    int send(int output) throws IOException;

    void receive(int input) throws IOException, ClassNotFoundException;
}
