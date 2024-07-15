package service;

import config.ValidationUtil;
import dao.CentroDeDistribuicaoDAO;
import entity.CentroDeDistribuicao;

import java.util.Scanner;

public class CentroDeDistribuicaoService {
    private CentroDeDistribuicaoDAO centroDeDistribuicaoDAO = new CentroDeDistribuicaoDAO();

    // Método para adicionar um centro
    public void adicionarCentroDeDistribuicao(Scanner scanner) {
        CentroDeDistribuicao centro = new CentroDeDistribuicao();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        while (!ValidationUtil.isValidEndereco(nome) || !centroDeDistribuicaoDAO.isNomeUnico(nome)) {
            System.out.println("Nome inválido ou já existente. Deve ser uma string válida, sem caracteres especiais, e ser único.");
            System.out.print("Nome: ");
            nome = scanner.nextLine();
        }
        centro.setNome(nome);

        System.out.print("Capacidade Total: ");
        String capacidadeTotal = scanner.nextLine();
        while (!ValidationUtil.isValidCapacidadeTotal(capacidadeTotal)) {
            System.out.println("Capacidade Total inválida. Deve ser um número inteiro.");
            System.out.print("Capacidade Total: ");
            capacidadeTotal = scanner.nextLine();
        }
        centro.setCapacidadeTotal(Integer.parseInt(capacidadeTotal));

        centroDeDistribuicaoDAO.save(centro);
        System.out.println("Centro de Distribuição adicionado com sucesso!");
    }

    // Método para buscar um centro
    public void buscarCentroDeDistribuicao(Scanner scanner) {
        System.out.print("ID do Centro de Distribuição: ");
        String idStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(idStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("ID do Centro de Distribuição: ");
            idStr = scanner.nextLine();
        }
        int id = Integer.parseInt(idStr);

        CentroDeDistribuicao centro = centroDeDistribuicaoDAO.find(id);
        if (centro != null) {
            System.out.println("Nome: " + centro.getNome());
            System.out.println("Capacidade total: " + centro.getCapacidadeTotal());
        } else {
            System.out.println("Centro de Distribuição não encontrado.");
        }
    }

    // Método para atualizar um centro
    public void atualizarCentroDeDistribuicao(Scanner scanner) {
        System.out.print("ID do Centro de Distribuição: ");
        String idStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(idStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("ID do Centro de Distribuição: ");
            idStr = scanner.nextLine();
        }
        int id = Integer.parseInt(idStr);

        CentroDeDistribuicao centroDeDistribuicao = centroDeDistribuicaoDAO.find(id);
        if (centroDeDistribuicao != null) {
            System.out.print("Nome (" + centroDeDistribuicao.getNome() + "): ");
            String nome = scanner.nextLine();
            while (!ValidationUtil.isValidDescricao(nome)) {
                System.out.println("Nome inválido. Deve ser uma string, pode conter espaço e números, mas não caracteres especiais.");
                System.out.print("Nome (" + centroDeDistribuicao.getNome() + "): ");
                nome = scanner.nextLine();
            }
            centroDeDistribuicao.setNome(nome);

            System.out.print("Capacidade Total (" + centroDeDistribuicao.getCapacidadeTotal() + "): ");
            String capacidadeTotal = scanner.nextLine();
            while (!ValidationUtil.isValidCapacidadeTotal(capacidadeTotal)) {
                System.out.println("Capacidade Total inválida. Deve ser um número inteiro.");
                System.out.print("Capacidade Total (" + centroDeDistribuicao.getCapacidadeTotal() + "): ");
                capacidadeTotal = scanner.nextLine();
            }
            centroDeDistribuicao.setCapacidadeTotal(Integer.parseInt(capacidadeTotal));

            centroDeDistribuicaoDAO.update(centroDeDistribuicao);
            System.out.println("Centro de Distribuição atualizado com sucesso!");
        } else {
            System.out.println("Centro de Distribuição não encontrado.");
        }
    }

    // Método para deletar um centro
    public void deletarCentroDeDistribuicao(Scanner scanner) {
        System.out.print("ID do Centro de Distribuição: ");
        String idStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(idStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("ID do Centro de Distribuição: ");
            idStr = scanner.nextLine();
        }
        int id = Integer.parseInt(idStr);

        centroDeDistribuicaoDAO.delete(id);
        System.out.println("Centro de Distribuição deletado com sucesso!");
    }

    // Método para listar todos os centros
    public void listarTodosCentrosDeDistribuicao() {
        System.out.println("Lista dos Centros de Distribuição:");
        for (CentroDeDistribuicao centro : centroDeDistribuicaoDAO.findAll()) {
            System.out.println("ID: " + centro.getId() + ", Nome: " + centro.getNome() + ", Capacidade: " + centro.getCapacidadeTotal());
        }
    }
}
