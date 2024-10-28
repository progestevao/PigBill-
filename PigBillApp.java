import java.util.HashMap;
import java.util.Scanner;

public class PigBillApp {
    private static HashMap<String, Double> despesasFixas = new HashMap<>();
    private static HashMap<String, Double> despesasNormais = new HashMap<>();
    private static double receita = 0.0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Adicionar receita");
            System.out.println("2. Adicionar despesa fixa");
            System.out.println("3. Adicionar despesa normal");
            System.out.println("4. Mostrar resumo");
            System.out.println("5. Sair");
            opcao = sc.nextInt();
            sc.nextLine(); // limpa o buffer

            switch (opcao) {
                case 1:
                    adicionarReceita(sc);
                    break;
                case 2:
                    adicionarDespesa(sc, despesasFixas, "fixa");
                    break;
                case 3:
                    adicionarDespesa(sc, despesasNormais, "normal");
                    break;
                case 4:
                    mostrarResumo();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    public static void adicionarReceita(Scanner sc) {
        System.out.println("Digite o valor da receita:");
        double valor = sc.nextDouble();
        receita += valor;
        System.out.println("Receita adicionada com sucesso!");
    }

    public static void adicionarDespesa(Scanner sc, HashMap<String, Double> despesas, String tipo) {
        System.out.println("Digite o nome da despesa " + tipo + ":");
        String nome = sc.nextLine().toLowerCase(); // padroniza o nome da despesa
        System.out.println("Digite o valor da despesa:");
        double valor = sc.nextDouble();

        if (despesas.containsKey(nome)) {
            despesas.put(nome, despesas.get(nome) + valor); // soma o valor se já existir
        } else {
            despesas.put(nome, valor);
        }
        System.out.println("Despesa " + tipo + " adicionada com sucesso!");
    }

    public static void mostrarResumo() {
        System.out.println("\n--- Resumo Financeiro ---");
        System.out.println("Receita total: R$ " + receita);
        
        System.out.println("Despesas Fixas:");
        despesasFixas.forEach((nome, valor) -> System.out.println(nome + ": R$ " + valor));
        
        System.out.println("Despesas Normais:");
        despesasNormais.forEach((nome, valor) -> System.out.println(nome + ": R$ " + valor));
        
        double totalDespesas = despesasFixas.values().stream().mapToDouble(Double::doubleValue).sum() 
                                + despesasNormais.values().stream().mapToDouble(Double::doubleValue).sum();
        System.out.println("Total de Despesas: R$ " + totalDespesas);
        
        double saldoFinal = receita - totalDespesas;
        System.out.println("Saldo Final: R$ " + saldoFinal);
        System.out.println("-------------------------\n");
    }
}
