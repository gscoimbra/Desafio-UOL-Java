// O pacote Service contém a lógica do desafio

package service;

import config.ValidationUtil;
import dao.AbrigoDAO;
import entity.Abrigo;
import entity.Doacao;

import java.util.List;
import java.util.Scanner;

public class AbrigoService {
    private AbrigoDAO abrigoDAO = new AbrigoDAO();

    // Método para adicionar um abrigo
    public void adicionarAbrigo(Scanner scanner) {
        Abrigo abrigo = new Abrigo();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        while (!ValidationUtil.isValidNome(nome) || !abrigoDAO.isNomeUnico(nome)) {
            System.out.println("Nome inválido ou já existente. Deve ter no mínimo 3 letras, não conter números ou caracteres especiais e ser único.");
            System.out.print("Nome: ");
            nome = scanner.nextLine();
        }
        abrigo.setNome(nome);

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        while (!ValidationUtil.isValidEndereco(endereco)) {
            System.out.println("Endereço inválido. Deve ser uma string, pode conter espaço e números, mas não caracteres especiais.");
            System.out.print("Endereço: ");
            endereco = scanner.nextLine();
        }
        abrigo.setEndereco(endereco);

        System.out.print("Responsável: ");
        String responsavel = scanner.nextLine();
        while (!ValidationUtil.isValidResponsavel(responsavel)) {
            System.out.println("Responsável inválido. Deve ter no mínimo 3 letras e não pode conter números ou caracteres especiais.");
            System.out.print("Responsável: ");
            responsavel = scanner.nextLine();
        }
        abrigo.setResponsavel(responsavel);

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        while (!ValidationUtil.isValidTelefone(telefone)) {
            System.out.println("Telefone inválido. Deve conter 10 ou 11 dígitos.");
            System.out.print("Telefone: ");
            telefone = scanner.nextLine();
        }
        abrigo.setTelefone(telefone);

        System.out.print("Email: ");
        String email = scanner.nextLine();
        while (!ValidationUtil.isValidEmail(email)) {
            System.out.println("Email inválido. Deve conter um @.");
            System.out.print("Email: ");
            email = scanner.nextLine();
        }
        abrigo.setEmail(email);

        System.out.print("Capacidade: ");
        String capacidade = scanner.nextLine();
        while (!ValidationUtil.isValidCapacidade(capacidade)) {
            System.out.println("Capacidade inválida. Deve ser um número.");
            System.out.print("Capacidade: ");
            capacidade = scanner.nextLine();
        }
        abrigo.setCapacidade(Integer.parseInt(capacidade));

        abrigoDAO.save(abrigo);
        System.out.println("Abrigo adicionado com sucesso!");
    }

    // Método para buscar um abrigo
    public void buscarAbrigo(Scanner scanner) {
        System.out.print("ID do Abrigo: ");
        String idStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(idStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("ID do Abrigo: ");
            idStr = scanner.nextLine();
        }
        int id = Integer.parseInt(idStr);

        Abrigo abrigo = abrigoDAO.find(id);
        if (abrigo != null) {
            System.out.println("Nome: " + abrigo.getNome());
            System.out.println("Endereço: " + abrigo.getEndereco());
            System.out.println("Responsável: " + abrigo.getResponsavel());
            System.out.println("Telefone: " + abrigo.getTelefone());
            System.out.println("Email: " + abrigo.getEmail());
            System.out.println("Capacidade: " + abrigo.getCapacidade());
            System.out.printf("Ocupação: %.2f%%\n", abrigo.getOcupacao() * 100);
        } else {
            System.out.println("Abrigo não encontrado.");
        }
    }

    // Método para atualizar um abrigo
    public void atualizarAbrigo(Scanner scanner) {
        System.out.print("ID do Abrigo: ");
        String idStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(idStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("ID do Abrigo: ");
            idStr = scanner.nextLine();
        }
        int id = Integer.parseInt(idStr);

        Abrigo abrigo = abrigoDAO.find(id);
        if (abrigo != null) {
            System.out.print("Nome (" + abrigo.getNome() + "): ");
            String nome = scanner.nextLine();
            while (!ValidationUtil.isValidNome(nome)) {
                System.out.println("Nome inválido. Deve ter no mínimo 3 letras e não pode conter números ou caracteres especiais.");
                System.out.print("Nome (" + abrigo.getNome() + "): ");
                nome = scanner.nextLine();
            }
            abrigo.setNome(nome);

            System.out.print("Endereço (" + abrigo.getEndereco() + "): ");
            String endereco = scanner.nextLine();
            while (!ValidationUtil.isValidEndereco(endereco)) {
                System.out.println("Endereço inválido. Deve ser uma string, pode conter espaço e números, mas não caracteres especiais.");
                System.out.print("Endereço (" + abrigo.getEndereco() + "): ");
                endereco = scanner.nextLine();
            }
            abrigo.setEndereco(endereco);

            System.out.print("Responsável (" + abrigo.getResponsavel() + "): ");
            String responsavel = scanner.nextLine();
            while (!ValidationUtil.isValidResponsavel(responsavel)) {
                System.out.println("Responsável inválido. Deve ter no mínimo 3 letras e não pode conter números ou caracteres especiais.");
                System.out.print("Responsável (" + abrigo.getResponsavel() + "): ");
                responsavel = scanner.nextLine();
            }
            abrigo.setResponsavel(responsavel);

            System.out.print("Telefone (" + abrigo.getTelefone() + "): ");
            String telefone = scanner.nextLine();
            while (!ValidationUtil.isValidTelefone(telefone)) {
                System.out.println("Telefone inválido. Deve conter 10 ou 11 dígitos.");
                System.out.print("Telefone (" + abrigo.getTelefone() + "): ");
                telefone = scanner.nextLine();
            }
            abrigo.setTelefone(telefone);

            System.out.print("Email (" + abrigo.getEmail() + "): ");
            String email = scanner.nextLine();
            while (!ValidationUtil.isValidEmail(email)) {
                System.out.println("Email inválido. Deve conter um @.");
                System.out.print("Email (" + abrigo.getEmail() + "): ");
                email = scanner.nextLine();
            }
            abrigo.setEmail(email);

            System.out.print("Capacidade (" + abrigo.getCapacidade() + "): ");
            String capacidade = scanner.nextLine();
            while (!ValidationUtil.isValidCapacidade(capacidade)) {
                System.out.println("Capacidade inválida. Deve ser um número.");
                System.out.print("Capacidade (" + abrigo.getCapacidade() + "): ");
                capacidade = scanner.nextLine();
            }
            abrigo.setCapacidade(Integer.parseInt(capacidade));

            abrigoDAO.update(abrigo);
            System.out.println("Abrigo atualizado com sucesso!");
        } else {
            System.out.println("Abrigo não encontrado.");
        }
    }

    // Método para deletar um abrigo
    public void deletarAbrigo(Scanner scanner) {
        System.out.print("ID do Abrigo: ");
        String idStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(idStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("ID do Abrigo: ");
            idStr = scanner.nextLine();
        }
        int id = Integer.parseInt(idStr);

        abrigoDAO.delete(id);
        System.out.println("Abrigo deletado com sucesso!");
    }

    // Método para listar todos os abrigos
    public void listarTodosAbrigos() {
        List<Abrigo> abrigos = abrigoDAO.findAllWithDoacoes();
        System.out.println("Lista de Abrigos:");
        for (Abrigo abrigo : abrigos) {
            System.out.println("ID: " + abrigo.getId() + ", Nome: " + abrigo.getNome() + ", Endereço: " + abrigo.getEndereco() + ", Ocupação: " + abrigo.getOcupacao() * 100 + "%");
        }
    }

    // Listo as doações que estão associadas aos abrigos
    public void listarDoacoesAbrigos(Scanner scanner) {
        List<Abrigo> abrigos = abrigoDAO.findAll();
        System.out.println("Lista de Abrigos:");
        for (Abrigo abrigo : abrigos) {
            System.out.println("ID: " + abrigo.getId() + ", Nome: " + abrigo.getNome() + ", Endereço: " + abrigo.getEndereco());
        }
        System.out.print("Selecione o ID do Abrigo que deseja listar as doações: ");
        String idStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(idStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("Selecione o ID do Abrigo que deseja listar as doações: ");
            idStr = scanner.nextLine();
        }
        int id = Integer.parseInt(idStr);
        Abrigo abrigo = abrigoDAO.findWithDoacoes(id);
        System.out.println("Doações do abrigo " + abrigo.getNome() + ":");
        for (Doacao doacao : abrigo.getDoacoes()) {
            String descricao = "";
            switch (doacao.getTipoItem().toLowerCase()) {
                case "roupa":
                    descricao = doacao.getRoupaDescricao();
                    break;
                case "higiene":
                    descricao = doacao.getHigieneDescricao();
                    break;
                case "alimento":
                    descricao = doacao.getAlimentoDescricao();
                    break;
            }
            System.out.println("ID: " + doacao.getId() +
                    ", Tipo de Item: " + doacao.getTipoItem() +
                    ", Descrição: " + descricao +
                    ", Quantidade: " + doacao.getQuantidade());
        }
    }

    // Esse método uso para saber quanto de cada item tem no abrigo para não passar do limite
    public int getQuantidadeAtualDeItensPorTipo(int abrigoId, String tipoItem) {
        return abrigoDAO.getQuantidadeAtualDeItensPorTipo(abrigoId, tipoItem);
    }
}
