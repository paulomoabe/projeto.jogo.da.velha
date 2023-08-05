package jogo_da_velha;

import java.util.Random;
import java.util.Scanner;

public class jogo_da_velha {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		char[][] velha =  { {' ', ' ', ' '},
							{' ', ' ', ' '}, 
							{' ', ' ', ' '}};
		int sair=1;
		
		
		
		
		
		menu();
		do{
			imagemjogo(velha);
			int opcao = quemcomeca(scan);
			scan.nextLine();
			switch(opcao) {								//sitch case para o usario escolher o modo de jogo,a variavel foi pedida no metodo
			case 1:
				while(true) {									//dentro de cada uma da opcoes precisamo de uma estrutura de repeticao para nao tenha so 2 jogadas
																//ou seja eh necessario para rodar o jogo ate o final,ira sair da repeticao quando os metodos retornarem verdadeiros e assim forcando o break
					jogador1(velha,scan);						//o break forca a parada de repeticao. uma vez o jogo finalizado o jogo ira para o metodo novo jogo e esperara a decisao do usario
					imagemjogo(velha);							//o case1 do sitch case eh caso o usario queria jogar multiplayer e comecano com x
						if(	gameganhoJogador1(velha)==true) {	//case 2 caso multiplater e O comecando
							break;								//case 3 single player. computador
						}
						
					jogador2(velha,scan);
					imagemjogo(velha);
					if(	gameganhoJogador2(velha)==true) {
						break;
					}
				
				}
				break;
			case 2:
				while(true) {
			
			
					jogador2(velha,scan);
					imagemjogo(velha);
						if(	gameganhoJogador2(velha)==true) {
					break;
						}
			 
					jogador1(velha,scan);
					imagemjogo(velha);
						if(	gameganhoJogador1(velha)==true) {
						break;
						}
			}
			case 3:
				while(true) {
					
					jogador1(velha,scan);
					imagemjogo(velha);
						if(	gameganhoJogador1(velha)==true) {
							break;
						}
					
						
					computador(velha,scan);
					imagemjogo(velha);
					if(	gameganhoComputador(velha)==true) {
						break;
					}
				
				}
			
			}	
			
			
			sair = novoJogo(scan, velha, sair);
		
			}while(sair==1);
	
}
	
	
	
	

	private static int novoJogo(Scanner scan, char[][] velha, int sair) {// contem outro metodo dentro dele e eh essencial para oq fazer depois do jogo
		int y;
		System.out.println("deseja jogar novamente? 1-sim| 2-nao");		//caso usario digite "1"= zeramos o jogo com a funcao e continuamos no loop, voltra para outro jogo
		y=scan.nextInt();												//caso "2"= a variavel "sair" vai ser somada com 1 e com isso satifazedno o do while do metodo principal
		if(y==1) {														//reparem no final do codigo a variavel.ela foi inicializa com 1 e caso somada +1 sair==2 e sai da estrutura de repeticao
			zerarJogo(velha);
			scan.nextLine();
		}else {
			System.out.println("Fim de jogo");
			sair++;
		}
		return sair;
	}

	public static void computador(char[][]velha,Scanner scan) {
		Random gerator= new Random();
		
		System.out.println("Vez do computador.");
		while(true) {
		int posicao=gerator.nextInt(9)+1;
		
		if(verificacao(posicao, velha)==true) {
			System.out.println("computador escolheu posicao "+posicao);
		switch(posicao) {
		
		case 1:
			velha[0][0]='O';
			return;
		case 2:
			velha[0][1]='O';
			return;
		case 3:
			velha[0][2]='O';
			return;
		case 4:
			velha[1][0]='O';
			return;
		case 5:
			velha[1][1]='O';
			return;
		case 6:
			velha[1][2]='O';
			return;
		case 7:
			velha[2][0]='O';
			return;
		case 8:
			velha[2][1]='O';
			return;
		case 9:
			velha[2][2]='O';
			return;
		}
			
		}
		}
	}
	public static boolean gameganhoComputador(char [][]velha) {
		if((velha[0][0]=='O'&& velha[0][1]=='O' && velha[0][2]=='O')
		||(velha[1][0]=='O' && velha[1][1]=='O' && velha[1][2]=='O')
		||(velha[2][0]=='O' && velha[2][1]=='O' && velha[2][2]=='O')
		||(velha[0][0]=='O' && velha[1][1]=='O' && velha[2][2]=='O')
		||(velha[0][2]=='O'&& velha[1][1]=='O' && velha[2][0]=='O')
		||(velha[0][0]=='O' && velha[1][0]=='O'&& velha[2][0]=='O')
		||(velha[0][1]=='O'&& velha[1][1]=='O'&& velha[2][1]=='O')
		||(velha[0][2]=='O'&& velha[1][2]=='O'&& velha[2][2]=='O')){
		System.out.println("Jogo finalizado. Computador venceu.");
		System.out.println("----------------------------------------");
		return true;
		
		}else if(empate(velha)==true) {
			System.out.println(" jogo terminou empatado.");
			return true;
		}else {
			return false;
		}
			
	}
	

	private static int quemcomeca(Scanner scan) {						//estrutura que pede o modo de jogo, pede e escaneia um numero
		System.out.println("Escolha uma das opcoes abaixo:");			//esse numera retorna para o metodo main e sera usado no switch case
		System.out.println("1- 2 jogadores e X comeca jogando");
		System.out.println("2- 2 jogadores e O comeca jogando");
		System.out.println("3- jogar sozinho contra o computador");
		int z=scan.nextInt();
		return z;
	}	
	
	
	
	public static void zerarJogo(char[][]velha) {						//caso o usario queira jogar denovo precisamos tirar os X e 0 presnetes na matriz
		for(int i=0;i<velha.length;i++) {							// de um jeito bem simples,basta prencher cada posicao dela com espacos novamente
			for(int j=0;j<velha.length;j++) {						//metodo necessario para jogar novamente, sem ele, matriz estaria ocupada e reotrnaria "casa ocuapda"
				velha[i][j]=' ';
			}
		}
	}
	
	public static boolean gameganhoJogador1(char [][]velha) {					//igual ao jogador 2
		if((velha[0][0]=='X' && velha[0][1]=='X' && velha[0][2]=='X')
		||(velha[1][0]=='X' && velha[1][1]=='X' && velha[1][2]=='X')
		||(velha[2][0]=='X'&& velha[2][1]=='X' && velha[2][2]=='X')
		||(velha[0][0]=='X'&& velha[1][1]=='X'&& velha[2][2]=='X')
		||(velha[0][2]=='X'&& velha[1][1]=='X'&& velha[2][0]=='X')
		||(velha[0][0]=='X' && velha[1][0]=='X'&& velha[2][0]=='X')
		||(velha[0][1]=='X'&& velha[1][1]=='X'&& velha[2][1]=='X')
		||(velha[0][2]=='X'&& velha[1][2]=='X'&& velha[2][2]=='X')){
		System.out.println("Jogo finalizado. jogador X venceu.");
		System.out.println("----------------------------------------");
		return true;
		}else if(empate(velha)==true) {
			System.out.println(" jogo terminou empatado.");
			System.out.println("----------------------------------------");
			return true;
		}else {
			return false;
		}
	}
	public static boolean gameganhoJogador2(char [][]velha) {				//verifica as condicoes de vitoria,lembrando que sao 8 formas.
		if((velha[0][0]=='O'&& velha[0][1]=='O' && velha[0][2]=='O')		//temos metodos parecidos que sao mudam o nome e numero, tem jogador1 e pc
		||(velha[1][0]=='O' && velha[1][1]=='O' && velha[1][2]=='O')		//reparem que a funcao empate esta presente e importantissima
		||(velha[2][0]=='O' && velha[2][1]=='O' && velha[2][0]=='O')		//reparem no if, se nenhuma das condicoes de vitoria bater, ele verifica o empate
		||(velha[0][0]=='O' && velha[1][1]=='O' && velha[2][2]=='O')		//caso haja vencedor nao verifica o empate.
		||(velha[0][2]=='O'&& velha[1][1]=='O' && velha[2][0]=='O')			//assim como outros eh um metodo que reotrna verdadeiro ou falso e faz parte diretamente do main
		||(velha[0][0]=='O' && velha[1][0]=='O'&& velha[2][0]=='O')			//
		||(velha[0][1]=='O'&& velha[1][1]=='O'&& velha[2][1]=='O')
		||(velha[0][2]=='O'&& velha[1][2]=='O'&& velha[2][2]=='O')){
		System.out.println("Jogo finalizado. jogador O venceu.");
		System.out.println("----------------------------------------");
		return true;
		
		}else if(empate(velha)==true) {
			System.out.println(" jogo terminou empatado.");
			return true;
		}else {
			return false;
		}
			
	}
	
	public static boolean empate(char [][]velha) {		//funcao que esta contida em ourta funcao, ou seja aparece diretamente no metodo main
		int cont=0;										//verifica se houve empate.basicamente verifica se ha espacos na matriz que nao foram prenchidos por X ou 0
		for(int x=0;x<velha.length;x++) {				//caso ngm ganhou, e todas as casas prenchidas dara empate. cont=0 empate,pois nao ha nenhuma casa nao preenchida.
			for(int j=0;j<velha.length;j++) {
			if	(velha[x][j]==' ') {
				cont++;
			}
			}
			
		}
		if(cont==0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
	public static boolean verificacao(int y,char[][] velha) {	//funcao que n aparece diretamente no metodo principal,pois esta presente dentro de outros metodos
		switch(y) {												//ligado ao jogadores e pc. verifica se a posicao nao esta ocupada por x ou 0.
		case 1:													//lembrando que nao esta vazio, contem um espaco entre as aspas simples.
			if(	velha[0][0]==' ') {								//aspas simples pois eh uma matriz de char.por ser um boolean retorna verdadeiro ou falso
				return true;
			}else {
				return false;
			}
		case 2:
			if(	velha[0][1]==' ') {
				return true;
			}else {
				return false;
			}
		case 3:
			if	(velha[0][2]==' '){
				return true;
			}else {
				return false;
			}
		case 4:
			if	(velha[1][0]==' '){
				return true;
			}else {
				return false;
			}
		case 5:
			if	(velha[1][1]==' '){
				return true;
			}else {
				return false;
			}
		case 6:
			if	(velha[1][2]==' '){
				return true;
			}else {
				return false;
			}
		case 7:
			if	(velha[2][0]==' '){
				return true;
			}else {
				return false;
			}
		case 8:
			if	(velha[2][1]==' '){
				return true;
			}else {
				return false;
			}
		case 9:
			if	(velha[2][2]==' '){
				return true;
			}else {
				return false;
			}
			
		}
		return false;
		
	}
	
	public static void jogador2(char[][] velha, Scanner scan ) {
		
		System.out.println("Vez do jogador: O");
		while(true) {
		System.out.println("Qual posicao gostaria de jogar? 1-9");
		int posicao=scan.nextInt();
		if(verificacao(posicao, velha)==true) {
		switch(posicao) {
		
		case 1:
			velha[0][0]='O';
			return;
		case 2:
			velha[0][1]='O';
			return;
		case 3:
			velha[0][2]='O';
			return;
		case 4:
			velha[1][0]='O';
			return;
		case 5:
			velha[1][1]='O';
			return;
		case 6:
			velha[1][2]='O';
			return;
		case 7:
			velha[2][0]='O';
			return;
		case 8:
			velha[2][1]='O';
			return;
		case 9:
			velha[2][2]='O';
			return;
		}
			
		}else{
			System.out.println("casa ocupada ou numero fora de intervalo. Escolha outro numero");
		
		}	
		}
	}
		
		
	
	
	public static void jogador1(char[][] velha, Scanner scan ) {	//metodo no qual o jogador tem que inserir a posicao, scanemos esse numeroe fazemos
																	//a verificacao.programa em loop,so vai sar do loop se verificacao indicar verdadeiro.
	System.out.println("Vez do jogador: X");						//sendo verdadeiro, o metodo insere o sinal na matriz.
	while(true) {													//vale lembrar que esse metodo contem uma funcao dentro dele (verificao)
	System.out.println("Qual posicao gostaria de jogar? 1-9");
	int posicao=scan.nextInt();
	if(verificacao(posicao, velha)==true) {
	switch(posicao) {
	
	case 1:
		velha[0][0]='X';
		return;
	case 2:
		velha[0][1]='X';
		return;
	case 3:
		velha[0][2]='X';
		return;
	case 4:
		velha[1][0]='X';
		return;
	case 5:
		velha[1][1]='X';
		return;
	case 6:
		velha[1][2]='X';
		return;
	case 7:
		velha[2][0]='X';
		return;
	case 8:
		velha[2][1]='X';
		return;
	case 9:
		velha[2][2]='X';
		return;
	}
		
	}else {
		System.out.println("casa ocupada ou numero fora de intervalo. Escolha outro numero");
	
	}	
		
	}
	}
	
	
	private static void menu() { 						//menu, exibi a mensagem do inicio do programa, aparece uma unica vez no programa
		System.out.println("JOGO DA VELHA");  
		System.out.println("");
		System.out.println("Participam duas pessoas, que jogam alternadamente, preenchendo cada um dos espacos vazios.");
		System.out.println("Cada participante sera representado por um simbolo (X ou O).");
		System.out.println("Vence o jogador que conseguir formar primeiro uma linha com tres simbolos iguais, seja ela na horizontal, vertical ou diagonal.");
		System.out.println("Caso os jogadores joguem da melhor formar possivel terminara em empate(ou deu velha)");
		System.out.println("Para adicionar um simbolo basta digitar o numero que representa posicao na tabela.");
		System.out.println("Observe o exemplo abaixo:");
		
		char[][] exemplo =  {   {'1','2', '3'},						//dentro do menu,reparem que inicializei as variaveis com numeros,com o objeitvo
								{'4', '5', '6'}, 					// de mostrar para o usuario como seria,somente aqui esta inicializado com numeros.
								{'7', '8', '9'}};					// e como eu so quero mostrar aqui os numeros, eu coloco imprmir jogo dentro desse metodo
		
		System.out.println(exemplo[0][0] + "|" +  exemplo[0][1] + "|" +  exemplo[0][2] );
		System.out.println("-+-+-");
		System.out.println(exemplo[1][0] + "|" + exemplo[1][1] + "|" +  exemplo[1][2] );
		System.out.println("-+-+-");
		System.out.println(exemplo[2][0] + "|" +  exemplo[2][1] + "|" +  exemplo[2][2] );
		System.out.println("");
			
	}

	
	
	
	
	public static void imagemjogo(char[][] velha) {
		System.out.println(velha[0][0] + "|" +  velha[0][1] + "|" +  velha[0][2] );// serve somente para exibir o tabuleiro do jogo
		System.out.println("-+-+-");
		System.out.println(velha[1][0] + "|" +  velha[1][1] + "|" +  velha[1][2] );
		System.out.println("-+-+-");
		System.out.println(velha[2][0] + "|" +  velha[2][1] + "|" +  velha[2][2] );
	}

}




