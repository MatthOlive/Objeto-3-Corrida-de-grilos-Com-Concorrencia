package grilothread;

public class ThreadController extends Thread {
    Grilos grilos;
    public ThreadController(Grilos grilos) {
        this.grilos = grilos;
    }
    public void run()
    {
        grilos.Pulo();
    }

}
