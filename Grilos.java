package grilothread;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Grilos {
    public Grilos(String name, int time) {
        this.griloNome = name;
        this.timeID = time;
        this.controller = new Semaphore(3);
    }
    Semaphore controller;
    Time times[] =null;
    String griloNome;
    int distancia;
    Random rand = new Random();
    int posicaoAtual = 0;
    int contadorPulo = 0;
    boolean finalizou = false;
    int timeID = 0;
    boolean first = false;

    public synchronized void Pulo() {
        try {
            controller.acquire();
            distancia = rand.nextInt(70);
            posicaoAtual += distancia;
            contadorPulo += 1;
            Corrida.times[timeID].Update(distancia);
            System.out.println("o " + griloNome +" pulou " + distancia +" Cm e percorreu " + posicaoAtual +" Cm");
            }
        catch(InterruptedException e1) {
            e1.printStackTrace();
        }
        finally {
            controller.release();
        }

    }
    public void Vencedor() {
        first = true;
        Corrida.times[timeID].vencedor = true;
    }

}
