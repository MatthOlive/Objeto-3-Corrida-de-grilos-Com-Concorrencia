package grilothread;
import java.util.concurrent.Semaphore;
public class Time {
    Semaphore mutex;
    public Time(int id) {
        this.id = id;
        this.mutex = new Semaphore(1);
    }

    int id =0;
    boolean vencedor = false;
    int pulosTotais =0;
    int totalPercorrido;

    public synchronized void Update(int distance) {
        try {
            mutex.acquire();
            pulosTotais += 1;
            totalPercorrido += distance;
        }
        catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        finally {
            mutex.release();
        }

    }
    public void Finish() {
        System.out.println("Time "+(id+1) + " percorreu " + totalPercorrido + "Cm, com "+ pulosTotais + " pulos.");
    }

    public void mostrarVencedor() {
        if(vencedor) {
            System.out.println("Time " + (id+1) + " ganhou a corrida.");
            System.out.println("Acabou a corrida!");
        }
    }




}
