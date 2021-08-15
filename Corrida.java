package grilothread;
import java.util.Scanner;
public class Corrida {
	static int griloQueTerminou = 0;
	static int linhaChegada = 200;
	public static Time[] times;
	static int contadorTime = 0;
	static boolean declaracaoVencedor = false;
	static int colocacao = 1;
	
	public static void main(String[] args) {
		int contadorGrilo = 0;
		int desiredContadorGrilobyTime = 2;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira a quantidade de Grilos participantes da corrida:");
		contadorGrilo = scanner.nextInt();
		System.out.println("A quantidade de Grilos participantes é: " + contadorGrilo);
		System.out.println("Insira a distancia da Linha de chegada para essa corrida:");
		linhaChegada = scanner.nextInt();
		ThreadController thread[] = null;
		Grilos[] griloMain = new Grilos[contadorGrilo];
		scanner.close();
		
		if(contadorGrilo % desiredContadorGrilobyTime == 0) {
			contadorTime = (int)(contadorGrilo / desiredContadorGrilobyTime);
		}
		else {
			contadorTime = (int) Math.ceil(contadorGrilo / desiredContadorGrilobyTime);
		}
		
		times = new Time[contadorTime];
		System.out.println("total de times: "+ contadorTime);
		for(int i =0; i<contadorGrilo; i++) {
			griloMain[i] = new Grilos("Grilo"+(i+1),i%contadorTime);
		}
		for(int i = 0; i < contadorTime; i++) {
			times[i] = new Time(i);
		}
		ComecarCorrida(contadorGrilo, thread, griloMain);
	}
	public static void ComecarCorrida(int contadorGrilo, ThreadController[] thread, Grilos[] grilos) {
		thread = new ThreadController[contadorGrilo];
		
		for(int i = 0; i<contadorGrilo;i++) {
			thread[i] = new ThreadController(grilos[i]);
			thread[i].start();
			
		}
		for(int i = 0; i<thread.length; i++) {
			try {
				thread[i].join();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		while(griloQueTerminou < contadorGrilo) {
			Race(contadorGrilo,thread, grilos);			
		}
		
		for(int i = 0; i < contadorTime; i++) {
			times[i].Finish();
		}
		for(int i = 0; i<contadorTime; i++) {
			times[i].mostrarVencedor();
		}
	}
	
	public static void Race(int contadorGrilo, ThreadController[] thread, Grilos[] grilos) {
		for(int i = 0; i < contadorGrilo; i++) {
			if(grilos[i].posicaoAtual >= linhaChegada) {
				griloQueTerminou ++;
				System.out.println("O "+grilos[i].griloNome + " chegou na posição " + colocacao + " e pulou " + grilos[i].contadorPulo + " vezes.");
				colocacao++;
				grilos[i].finalizou = true;
				if(!declaracaoVencedor) {
					grilos[i].Vencedor();
					declaracaoVencedor = true;
				}
			}
			else
			{
				thread[i].run();
			}
		}
	
	
		for(int i =0; i < thread.length ;i++) {
			try {
				thread[i].join();
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		
	}
	
}}
