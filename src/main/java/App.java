import config.JPAUtil;
import service.AbrigoService;
import service.CentroDeDistribuicaoService;
import service.DoacaoService;

import java.util.Scanner;

public class App {
    private static AbrigoService abrigoService = new AbrigoService();
    private static DoacaoService doacaoService = new DoacaoService();
    private static CentroDeDistribuicaoService centroDeDistribuicaoService = new CentroDeDistribuicaoService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar um Centro de Distribuição");
            System.out.println("2. Buscar um Centro de Distribuição");
            System.out.println("3. Atualizar um Centro de Distribuição");
            System.out.println("4. Deletar um Centro de Distribuição");
            System.out.println("5. Listar Todos os Centros de Distribuição");
            System.out.println("6. Transferir Doações entre Centros de Distribuição");
            System.out.println("7. Criar ordem de pedido de transferência de doações do centro de distribuição para o abrigo");
            System.out.println("8. Adicionar Doação");
            System.out.println("9. Buscar Doação");
            System.out.println("10. Atualizar Doação");
            System.out.println("11. Deletar Doação");
            System.out.println("12. Listar Todas as Doações");
            System.out.println("13. Adicionar Abrigo");
            System.out.println("14. Buscar Abrigo");
            System.out.println("15. Atualizar Abrigo");
            System.out.println("16. Deletar Abrigo");
            System.out.println("17. Listar Todos os Abrigos");
            System.out.println("18. Listar doações de um abrigo selecionado");
            System.out.println("19. Fazer Pedido de Itens");
            System.out.println("20. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    centroDeDistribuicaoService.adicionarCentroDeDistribuicao(scanner);
                    break;
                case 2:
                    centroDeDistribuicaoService.buscarCentroDeDistribuicao(scanner);
                    break;
                case 3:
                    centroDeDistribuicaoService.atualizarCentroDeDistribuicao(scanner);
                    break;
                case 4:
                    centroDeDistribuicaoService.deletarCentroDeDistribuicao(scanner);
                    break;
                case 5:
                    centroDeDistribuicaoService.listarTodosCentrosDeDistribuicao();
                    break;
                case 6:
                    doacaoService.transferirDoacoes(scanner);
                    break;
                case 7:
                    doacaoService.transferirDoacaoParaAbrigo(scanner, abrigoService);
                    break;
                case 8:
                    doacaoService.adicionarDoacao(scanner);
                    break;
                case 9:
                    doacaoService.buscarDoacao(scanner);
                    break;
                case 10:
                    doacaoService.atualizarDoacao(scanner);
                    break;
                case 11:
                    doacaoService.deletarDoacao(scanner);
                    break;
                case 12:
                    doacaoService.listarTodasDoacoes();
                    break;
                case 13:
                    abrigoService.adicionarAbrigo(scanner);
                    break;
                case 14:
                    abrigoService.buscarAbrigo(scanner);
                    break;
                case 15:
                    abrigoService.atualizarAbrigo(scanner);
                    break;
                case 16:
                    abrigoService.deletarAbrigo(scanner);
                    break;
                case 17:
                    abrigoService.listarTodosAbrigos();
                    break;
                case 18:
                    abrigoService.listarDoacoesAbrigos(scanner);
                    break;
                case 19:
                    doacaoService.fazerPedidoItens(scanner);
                    break;
                case 20:
                    System.out.println("Saindo...");
                    JPAUtil.close();
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
