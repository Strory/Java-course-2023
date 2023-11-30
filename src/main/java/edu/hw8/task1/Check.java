package edu.hw8.task1;

public class Check {
    private Check() {}

    public static void runCheck() {
        Server server = new Server();
        Client client2 = new Client();
        Client client3 = new Client();
        Thread thread1 = new Thread(server::runServer);
        Thread thread2 = new Thread(client2::runClient);
        Thread thread3 = new Thread(client3::runClient);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
