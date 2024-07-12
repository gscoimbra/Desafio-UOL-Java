import config.JPAUtil;
import dao.AbrigoDAO;
import entity.Doacao;
import dao.DoacaoDAO;
import entity.Abrigo;
import entity.CentroDeDistribuicao;
import dao.CentroDeDistribuicaoDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.List;

public class App {
    private static AbrigoDAO abrigoDAO = new AbrigoDAO();
    private static DoacaoDAO doacaoDAO = new DoacaoDAO();
    private static CentroDeDistribuicaoDAO centroDeDistribuicaoDAO = new CentroDeDistribuicaoDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar Abrigo");
            System.out.println("2. Buscar Abrigo");
            System.out.println("3. Atualizar Abrigo");
            System.out.println("4. Deletar Abrigo");
            System.out.println("5. Listar Todos os Abrigos");
            System.out.println("6. Listar doações de um abrigo selecionado");
            System.out.println("7. Adicionar Doação");
            System.out.println("8. Buscar Doação");
            System.out.println("9. Atualizar Doação");
            System.out.println("10. Deletar Doação");
            System.out.println("11. Listar Todas as Doações");
            System.out.println("12. Fazer Pedido de Itens");
            System.out.println("13. Adicionar um Centro de Distribuição");
            System.out.println("14. Buscar um Centro de Distribuição");
            System.out.println("15. Atualizar um Centro de Distribuição");
            System.out.println("16. Deletar um Centro de Distribuição");
            System.out.println("17. Listar Todos os Centros de Distribuição");
            System.out.println("18. Transferir Doações entre Centros de Distribuição");
            System.out.println("19. Criar ordem de pedido de transferência de doações do centro de distribuição para o abrigo");
            System.out.println("20. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    adicionarAbrigo();
                    break;
                case 2:
                    buscarAbrigo();
                    break;
                case 3:
                    atualizarAbrigo();
                    break;
                case 4:
                    deletarAbrigo();
                    break;
                case 5:
                    listarTodosAbrigos();
                    break;
                case 6:
                    listarDoacoesAbrigos();
                    break;
                case 7:
                    adicionarDoacao();
                    break;
                case 8:
                    buscarDoacao();
                    break;
                case 9:
                    atualizarDoacao();
                    break;
                case 10:
                    deletarDoacao();
                    break;
                case 11:
                    listarTodasDoacoes();
                    break;
                case 12:
                    fazerPedidoItens();
                    break;
                case 13:
                    adicionarCentroDeDistribuicao();
                    break;
                case 14:
                    buscarCentroDeDistribuicao();
                    break;
                case 15:
                    atualizarCentroDeDistribuicao();
                    break;
                case 16:
                    deletarCentroDeDistribuicao();
                    break;
                case 17:
                    listarCentrosDeDistribuicao();
                    break;
                case 18:
                    transferirDoacoes();
                    break;
                case 19:
                    transferirDoacaoParaAbrigo();
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

    private static void listarCentrosDeDistribuicao() {
        System.out.println("Lista dos Centros de Distribuição:");
        for (CentroDeDistribuicao centro : centroDeDistribuicaoDAO.findAll()) {
            System.out.println("ID: " + centro.getId() + ", Nome: " + centro.getNome() + ", Capacidade: " + centro.getCapacidadeTotal());
        }
    }

    private static void listarCentrosDeDistribuicaoCadastrado(int id) {
        if(id != 0){
            CentroDeDistribuicao centro = centroDeDistribuicaoDAO.findCentroDeDistribuicaoById(id);
            System.out.println("ID: " + centro.getId() + ", Nome: " + centro.getNome() + ", Capacidade: " + centro.getCapacidadeTotal());
        }
    }

    private static void deletarCentroDeDistribuicao() {
        System.out.print("ID do Centro de Distribuição: ");
        int id = Integer.parseInt(scanner.nextLine());

        centroDeDistribuicaoDAO.delete(id);
        System.out.println("Centro de Distribuição deletado com sucesso!");
    }

    private static void atualizarCentroDeDistribuicao() {
        System.out.print("ID do Centro de Distribuição: ");
        int id = Integer.parseInt(scanner.nextLine());

        CentroDeDistribuicao centroDeDistribuicao = centroDeDistribuicaoDAO.find(id);
        if (centroDeDistribuicao != null) {
            System.out.print("Nome (" + centroDeDistribuicao.getNome() + "): ");
            centroDeDistribuicao.setNome(scanner.nextLine());

            System.out.print("Capacidade total (" + centroDeDistribuicao.getCapacidadeTotal() + "): ");
            centroDeDistribuicao.setCapacidadeTotal(Integer.parseInt(scanner.nextLine()));

            centroDeDistribuicaoDAO.update(centroDeDistribuicao);
            System.out.println("Abrigo atualizado com sucesso!");
        } else {
            System.out.println("Abrigo não encontrado.");
        }
    }

    private static void buscarCentroDeDistribuicao() {
        System.out.print("ID do Centro de Distribuição: ");
        int id = Integer.parseInt(scanner.nextLine());
        CentroDeDistribuicao centroDeDistribuicao = centroDeDistribuicaoDAO.find(id);
        if (centroDeDistribuicao != null) {
            System.out.println("Nome: " + centroDeDistribuicao.getNome());
            System.out.println("Capacidade total: " + centroDeDistribuicao.getCapacidadeTotal());
        } else {
            System.out.println("Centro de Distribuição não encontrado.");
        }
    }

    private static void adicionarCentroDeDistribuicao() {
        CentroDeDistribuicao centro = new CentroDeDistribuicao();

        System.out.print("Nome: ");
        centro.setNome(scanner.nextLine());

        System.out.print("Capacidade Total: ");
        centro.setCapacidadeTotal(Integer.parseInt(scanner.nextLine()));

        centroDeDistribuicaoDAO.save(centro);
        System.out.println("Centro de Distribuição adicionado com sucesso!");
    }

    private static void adicionarAbrigo() {
        Abrigo abrigo = new Abrigo();

        System.out.print("Nome: ");
        abrigo.setNome(scanner.nextLine());

        System.out.print("Endereço: ");
        abrigo.setEndereco(scanner.nextLine());

        System.out.print("Responsável: ");
        abrigo.setResponsavel(scanner.nextLine());

        System.out.print("Telefone: ");
        abrigo.setTelefone(scanner.nextLine());

        System.out.print("Email: ");
        abrigo.setEmail(scanner.nextLine());

        System.out.print("Capacidade: ");
        abrigo.setCapacidade(Integer.parseInt(scanner.nextLine()));

        System.out.print("Ocupação (%): ");
        abrigo.setOcupacao(Double.parseDouble(scanner.nextLine()));

        abrigoDAO.save(abrigo);
        System.out.println("Abrigo adicionado com sucesso!");
    }

    private static void buscarAbrigo() {
        System.out.print("ID do Abrigo: ");
        int id = Integer.parseInt(scanner.nextLine());
        Abrigo abrigo = abrigoDAO.find(id);
        if (abrigo != null) {
            System.out.println("Nome: " + abrigo.getNome());
            System.out.println("Endereço: " + abrigo.getEndereco());
            System.out.println("Responsável: " + abrigo.getResponsavel());
            System.out.println("Telefone: " + abrigo.getTelefone());
            System.out.println("Email: " + abrigo.getEmail());
            System.out.println("Capacidade: " + abrigo.getCapacidade());
            System.out.println("Ocupação: " + abrigo.getOcupacao());
        } else {
            System.out.println("Abrigo não encontrado.");
        }
    }

    private static void atualizarAbrigo() {
        System.out.print("ID do Abrigo: ");
        int id = Integer.parseInt(scanner.nextLine());

        Abrigo abrigo = abrigoDAO.find(id);
        if (abrigo != null) {
            System.out.print("Nome (" + abrigo.getNome() + "): ");
            abrigo.setNome(scanner.nextLine());

            System.out.print("Endereço (" + abrigo.getEndereco() + "): ");
            abrigo.setEndereco(scanner.nextLine());

            System.out.print("Responsável (" + abrigo.getResponsavel() + "): ");
            abrigo.setResponsavel(scanner.nextLine());

            System.out.print("Telefone (" + abrigo.getTelefone() + "): ");
            abrigo.setTelefone(scanner.nextLine());

            System.out.print("Email (" + abrigo.getEmail() + "): ");
            abrigo.setEmail(scanner.nextLine());

            System.out.print("Capacidade (" + abrigo.getCapacidade() + "): ");
            abrigo.setCapacidade(Integer.parseInt(scanner.nextLine()));

            System.out.print("Ocupação (" + abrigo.getOcupacao() + "): ");
            abrigo.setOcupacao(Double.parseDouble(scanner.nextLine()));

            abrigoDAO.update(abrigo);
            System.out.println("Abrigo atualizado com sucesso!");
        } else {
            System.out.println("Abrigo não encontrado.");
        }
    }

    private static void deletarAbrigo() {
        System.out.print("ID do Abrigo: ");
        int id = Integer.parseInt(scanner.nextLine());

        abrigoDAO.delete(id);
        System.out.println("Abrigo deletado com sucesso!");
    }

    private static void listarTodosAbrigos() {
        System.out.println("Lista de Abrigos:");
        for (Abrigo abrigo : abrigoDAO.findAll()) {
            System.out.println("ID: " + abrigo.getId() + ", Nome: " + abrigo.getNome() + ", Endereço: " + abrigo.getEndereco());
        }
    }

    private static void listarDoacoesAbrigos() {
        System.out.println("Lista de Abrigos:");
        for (Abrigo abrigo : abrigoDAO.findAll()) {
            System.out.println("ID: " + abrigo.getId() + ", Nome: " + abrigo.getNome() + ", Endereço: " + abrigo.getEndereco());
        }
        System.out.print("Selecione o ID do Abrigo que deseja listar as doações: ");
        int id = Integer.parseInt(scanner.nextLine());
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

    private static void adicionarDoacao() {
        CentroDeDistribuicaoDAO centroDAO = new CentroDeDistribuicaoDAO();
        Doacao doacao = new Doacao();

        System.out.print("ID do Centro de Distribuição: ");
        int centroId = Integer.parseInt(scanner.nextLine());
        CentroDeDistribuicao centro = centroDAO.findCentroDeDistribuicaoById(centroId);
        if (centro == null) {
            System.out.println("Centro de Distribuição não encontrado.");
            return;
        } else {
            doacao.setCentroDeDistribuicao(centro);
            System.out.println("Centro de Distribuição definido com sucesso.");
        }

        System.out.print("Tipo de Item (roupa/higiene/alimento): ");
        String tipoItem = scanner.nextLine();
        doacao.setTipoItem(tipoItem);

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        doacao.setQuantidade(quantidade);

        // Verificar capacidade do centro de distribuição
        int quantidadeAtual = centroDAO.getQuantidadeAtualDeItensPorTipo(centroId, tipoItem);
        if (quantidadeAtual + quantidade > 1000) {
            System.out.println("Erro: Capacidade máxima de 1000 itens atingida para o tipo de item " + tipoItem + " no centro de distribuição " + centro.getNome());
            return;
        }

        if (tipoItem.equalsIgnoreCase("roupa")) {
            System.out.print("Descrição da Roupa: ");
            doacao.setRoupaDescricao(scanner.nextLine());

            System.out.print("Gênero (M/F): ");
            doacao.setRoupaGenero(scanner.nextLine());

            System.out.print("Tamanho (Infantil/PP/P/M/G/GG): ");
            doacao.setRoupaTamanho(scanner.nextLine());
        } else if (tipoItem.equalsIgnoreCase("higiene")) {
            System.out.print("Descrição do Produto de Higiene: ");
            doacao.setHigieneDescricao(scanner.nextLine());

            System.out.print("Sabonete (true/false): ");
            doacao.setHigieneSabonete(Boolean.parseBoolean(scanner.nextLine()));

            System.out.print("Escova de Dentes (true/false): ");
            doacao.setHigieneEscova(Boolean.parseBoolean(scanner.nextLine()));

            System.out.print("Pasta de Dentes (true/false): ");
            doacao.setHigienePasta(Boolean.parseBoolean(scanner.nextLine()));

            System.out.print("Absorventes (true/false): ");
            doacao.setHigieneAbsorvente(Boolean.parseBoolean(scanner.nextLine()));
        } else if (tipoItem.equalsIgnoreCase("alimento")) {
            System.out.print("Descrição do Alimento: ");
            doacao.setAlimentoDescricao(scanner.nextLine());

            System.out.print("Unidade de Medida: ");
            doacao.setAlimentoUnidadeMedida(scanner.nextLine());

            System.out.print("Validade (dd/MM/yyyy): ");
            String dataInput = scanner.nextLine();

            // Converter a string de data para o objeto Date
            Date dataDoacao = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dataDoacao = sdf.parse(dataInput);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            doacao.setAlimentoValidade(dataDoacao);
        }

        doacaoDAO.save(doacao);
        System.out.println("Doação adicionada com sucesso!");
    }

    private static void buscarDoacao() {
        System.out.print("ID da Doação: ");
        int id = Integer.parseInt(scanner.nextLine());

        Doacao doacao = doacaoDAO.find(id);
        if (doacao != null) {
            System.out.println("Centro de Distribuição ID: " + doacao.getCentroDeDistribuicao());
            System.out.println("Tipo de Item: " + doacao.getTipoItem());
            System.out.println("Quantidade: " + doacao.getQuantidade());

            if (doacao.getTipoItem().equalsIgnoreCase("roupa")) {
                System.out.println("Descrição da Roupa: " + doacao.getRoupaDescricao());
                System.out.println("Gênero: " + doacao.getRoupaGenero());
                System.out.println("Tamanho: " + doacao.getRoupaTamanho());
            } else if (doacao.getTipoItem().equalsIgnoreCase("higiene")) {
                System.out.println("Descrição do Produto de Higiene: " + doacao.getHigieneDescricao());
                System.out.println("Sabonete: " + doacao.getHigieneSabonete());
                System.out.println("Escova de Dentes: " + doacao.getHigieneEscova());
                System.out.println("Pasta de Dentes: " + doacao.getHigienePasta());
                System.out.println("Absorventes: " + doacao.getHigieneAbsorvente());
            } else if (doacao.getTipoItem().equalsIgnoreCase("alimento")) {
                System.out.println("Descrição do Alimento: " + doacao.getAlimentoDescricao());
                System.out.println("Unidade de Medida: " + doacao.getAlimentoUnidadeMedida());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataFormatada = sdf.format(doacao.getAlimentoValidade());
                System.out.println("Validade: " + dataFormatada);
            }
        } else {
            System.out.println("Doação não encontrada.");
        }
    }

    private static void atualizarDoacao() {
        CentroDeDistribuicaoDAO centroDAO = new CentroDeDistribuicaoDAO();

        System.out.print("ID da Doação: ");
        int id = Integer.parseInt(scanner.nextLine());

        Doacao doacao = doacaoDAO.find(id);
        if (doacao != null) {
            System.out.print("ID do Centro de Distribuição (" + doacao.getCentroDeDistribuicao() + "): ");
            int centroId = Integer.parseInt(scanner.nextLine());
            CentroDeDistribuicao centro = centroDAO.findCentroDeDistribuicaoById(centroId);
            if (centro != null) {
                doacao.setCentroDeDistribuicao(centro);
                System.out.println("Centro de Distribuição definido com sucesso.");
            } else {
                System.out.println("Centro de Distribuição não encontrado.");
            }

            System.out.print("Tipo de Item (roupa/higiene/alimento) (" + doacao.getTipoItem() + "): ");
            doacao.setTipoItem(scanner.nextLine());

            System.out.print("Quantidade (" + doacao.getQuantidade() + "): ");
            doacao.setQuantidade(Integer.parseInt(scanner.nextLine()));

            if (doacao.getTipoItem().equalsIgnoreCase("roupa")) {
                System.out.print("Descrição da Roupa (" + doacao.getRoupaDescricao() + "): ");
                doacao.setRoupaDescricao(scanner.nextLine());

                System.out.print("Gênero (" + doacao.getRoupaGenero() + "): ");
                doacao.setRoupaGenero(scanner.nextLine());

                System.out.print("Tamanho (" + doacao.getRoupaTamanho() + "): ");
                doacao.setRoupaTamanho(scanner.nextLine());
            } else if (doacao.getTipoItem().equalsIgnoreCase("higiene")) {
                System.out.print("Descrição do Produto de Higiene (" + doacao.getHigieneDescricao() + "): ");
                doacao.setHigieneDescricao(scanner.nextLine());

                System.out.print("Sabonete (" + doacao.getHigieneSabonete() + "): ");
                doacao.setHigieneSabonete(Boolean.parseBoolean(scanner.nextLine()));
                System.out.print("Escova de Dentes (" + doacao.getHigieneEscova() + "): ");
                doacao.setHigieneEscova(Boolean.parseBoolean(scanner.nextLine()));

                System.out.print("Pasta de Dentes (" + doacao.getHigienePasta() + "): ");
                doacao.setHigienePasta(Boolean.parseBoolean(scanner.nextLine()));

                System.out.print("Absorventes (" + doacao.getHigieneAbsorvente() + "): ");
                doacao.setHigieneAbsorvente(Boolean.parseBoolean(scanner.nextLine()));
            } else if (doacao.getTipoItem().equalsIgnoreCase("alimento")) {
                System.out.print("Descrição do Alimento (" + doacao.getAlimentoDescricao() + "): ");
                doacao.setAlimentoDescricao(scanner.nextLine());

                System.out.print("Unidade de Medida (" + doacao.getAlimentoUnidadeMedida() + "): ");
                doacao.setAlimentoUnidadeMedida(scanner.nextLine());

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataFormatada = sdf.format(doacao.getAlimentoValidade());
                System.out.print("Validade(dd/MM/yyyy): (" + dataFormatada + "): ");
                String dataInput = scanner.nextLine();

                // Converter a string de data para o objeto Date
                Date dataDoacao = null;
                try {
                    dataDoacao = sdf.parse(dataInput);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                doacao.setAlimentoValidade(dataDoacao);
            }

            doacaoDAO.update(doacao);
            System.out.println("Doação atualizada com sucesso!");
        } else {
            System.out.println("Doação não encontrada.");
        }
    }

    private static void deletarDoacao() {
        System.out.print("ID da Doação: ");
        int id = Integer.parseInt(scanner.nextLine());

        doacaoDAO.delete(id);
        System.out.println("Doação deletada com sucesso!");
    }

    private static void listarTodasDoacoes() {
        System.out.println("Lista de Doações:");
        for (Doacao doacao : doacaoDAO.findAll()) {
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
                    ", Quantidade: " + doacao.getQuantidade() +
                    ", Centro de Distribuição: " + doacao.getCentroDeDistribuicao().getNome());
        }
    }

    private static void fazerPedidoItens() {
        System.out.println("Tipos de Itens disponíveis: ");
        List<String> tiposItens = doacaoDAO.findDistinctTipoItem();
        for (String tipoItem : tiposItens) {
            System.out.println(tipoItem);
        }
        System.out.print("Digite o tipo de item que deseja: ");
        String tipoItem = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        List<Doacao> doacoes = doacaoDAO.findByTipoItemAndQuantidade(tipoItem, quantidade);
        if (!doacoes.isEmpty()) {
            System.out.println("Doações encontradas:");
            for (Doacao doacao : doacoes) {
                System.out.println("ID: " + doacao.getId() +
                        ", Centro de Distribuição ID: " + doacao.getCentroDeDistribuicao().getId() +
                        ", Centro de Distribuição: " + doacao.getCentroDeDistribuicao().getNome() +
                        ", Quantidade: " + doacao.getQuantidade());
            }
        } else {
            System.out.println("Nenhuma doação encontrada para o tipo de item e quantidade especificados.");
        }
    }


    private static void transferirDoacoes() {
        System.out.println("Quais doações você deseja transferir?");
        System.out.println("Digite o tipo de item de doação:");

        List<String> tiposItens = doacaoDAO.findDistinctTipoItem();
        for (String tipoItem : tiposItens) {
            System.out.println(tipoItem);
        }

        String tipoItemSelecionado = scanner.nextLine();
        System.out.println("Qual doação você deseja transferir?");
        System.out.println("Doações disponíveis:");

        List<Doacao> doacoes = doacaoDAO.findByTipoItem(tipoItemSelecionado);
        for (Doacao doacao : doacoes) {
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

        System.out.print("Digite o ID da doação a ser transferida: ");
        int doacaoId = Integer.parseInt(scanner.nextLine());
        Doacao doacaoSelecionada = doacaoDAO.find(doacaoId);
        if (doacaoSelecionada == null) {
            System.out.println("Doação não encontrada!");
            return;
        }

        int centroDeDistribuicaoOrigemId = doacaoSelecionada.getCentroDeDistribuicao().getId();
        System.out.println("Essa doação está cadastrada no Centro de Distribuição: " + centroDeDistribuicaoOrigemId);

        System.out.print("Para qual centro de distribuição você deseja transferir?");
        listarCentrosDeDistribuicao();
        System.out.print("Digite o ID do Centro de Distribuição que irá RECEBER: ");
        int receberCentroId = Integer.parseInt(scanner.nextLine());

        doacaoDAO.updateCentroDeDistribuicao(doacaoId, receberCentroId);
        System.out.println("Doação transferida com sucesso!");
    }

    private static void transferirDoacaoParaAbrigo() {
        System.out.println("Quais doações você deseja transferir?");
        System.out.println("Digite o tipo de item de doação:");

        List<String> tiposItens = doacaoDAO.findDistinctTipoItem();
        for (String tipoItem : tiposItens) {
            System.out.println(tipoItem);
        }

        String tipoItemSelecionado = scanner.nextLine();
        System.out.println("Qual doação você deseja transferir?");
        System.out.println("Doações disponíveis:");

        List<Doacao> doacoes = doacaoDAO.findByTipoItem(tipoItemSelecionado);
        for (Doacao doacao : doacoes) {
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
                    ", Quantidade: " + doacao.getQuantidade() +
                    ", Centro de Distribuição: " + doacao.getCentroDeDistribuicao().getNome());
        }

        System.out.print("Digite o ID da doação a ser transferida: ");
        int doacaoId = Integer.parseInt(scanner.nextLine());

        // Buscar a doação selecionada
        Doacao doacaoSelecionada = doacaoDAO.find(doacaoId);
        if (doacaoSelecionada == null) {
            System.out.println("Doação não encontrada!");
            return;
        }

        listarAbrigos();
        System.out.print("Digite o ID do Abrigo que irá RECEBER a doação: ");
        int abrigoId = Integer.parseInt(scanner.nextLine());

        Abrigo abrigo = abrigoDAO.find(abrigoId);
        if (abrigo == null) {
            System.out.println("Abrigo não encontrado!");
            return;
        }

        // Verificar capacidade do abrigo
        int quantidadeAtual = abrigoDAO.getQuantidadeAtualDeItensPorTipo(abrigoId, tipoItemSelecionado);
        if (quantidadeAtual + doacaoSelecionada.getQuantidade() > 200) {
            System.out.println("Erro: Capacidade máxima de 200 itens atingida para o tipo de item " + tipoItemSelecionado + " no abrigo " + abrigo.getNome());
            return;
        }

        // Atualizar a doação para remover do centro de distribuição e associar ao abrigo
        doacaoSelecionada.setCentroDeDistribuicao(null);
        doacaoSelecionada.setAbrigo(abrigo);
        doacaoDAO.update(doacaoSelecionada);

        System.out.println("Doação transferida com sucesso para o abrigo " + abrigo.getNome() + "!");
    }

    private static void listarAbrigos() {
        System.out.println("Lista dos Abrigos:");
        for (Abrigo abrigo : abrigoDAO.findAll()) {
            System.out.println("ID: " + abrigo.getId() + ", Nome: " + abrigo.getNome() + ", Endereço: " + abrigo.getEndereco());
        }
    }


}