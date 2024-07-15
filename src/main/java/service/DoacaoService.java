package service;

import config.ValidationUtil;
import dao.CentroDeDistribuicaoDAO;
import dao.DoacaoDAO;
import entity.CentroDeDistribuicao;
import entity.Doacao;
import entity.Abrigo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DoacaoService {
    private DoacaoDAO doacaoDAO = new DoacaoDAO();
    private CentroDeDistribuicaoDAO centroDeDistribuicaoDAO = new CentroDeDistribuicaoDAO();

    // Método para adicionar a doação
    public void adicionarDoacao(Scanner scanner) {
        Doacao doacao = new Doacao();

        System.out.print("ID do Centro de Distribuição: ");
        String centroIdStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(centroIdStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("ID do Centro de Distribuição: ");
            centroIdStr = scanner.nextLine();
        }
        int centroId = Integer.parseInt(centroIdStr);
        CentroDeDistribuicao centro = centroDeDistribuicaoDAO.find(centroId);
        if (centro == null) {
            System.out.println("Centro de Distribuição não encontrado.");
            return;
        }
        doacao.setCentroDeDistribuicao(centro);

        System.out.print("Tipo de Item (roupa/higiene/alimento): ");
        String tipoItem = scanner.nextLine();
        while (!ValidationUtil.isValidTipoItem(tipoItem)) {
            System.out.println("Tipo de item inválido. Deve ser roupa, higiene ou alimento.");
            System.out.print("Tipo de Item (roupa/higiene/alimento): ");
            tipoItem = scanner.nextLine();
        }
        doacao.setTipoItem(tipoItem);

        System.out.print("Quantidade: ");
        String quantidade = scanner.nextLine();
        while (!ValidationUtil.isValidQuantidade(quantidade)) {
            System.out.println("Quantidade inválida. Deve ser um número inteiro.");
            System.out.print("Quantidade: ");
            quantidade = scanner.nextLine();
        }
        doacao.setQuantidade(Integer.parseInt(quantidade));

        int quantidadeAtual = centroDeDistribuicaoDAO.getQuantidadeAtualDeItensPorTipo(centroId, doacao.getTipoItem());
        if (quantidadeAtual + doacao.getQuantidade() > 1000) {
            System.out.println("Erro: Capacidade máxima de 1000 itens atingida para o tipo de item " + doacao.getTipoItem() + " no centro de distribuição " + centro.getNome());
            return;
        }

        if (tipoItem.equalsIgnoreCase("roupa")) {
            System.out.print("Descrição da Roupa: ");
            String descricao = scanner.nextLine();
            while (!ValidationUtil.isValidDescricao(descricao)) {
                System.out.println("Descrição inválida. Deve ser uma string, pode conter espaço e números, mas não caracteres especiais.");
                System.out.print("Descrição da Roupa: ");
                descricao = scanner.nextLine();
            }
            doacao.setRoupaDescricao(descricao);

            System.out.print("Gênero (Masculino/Feminino): ");
            String genero = scanner.nextLine();
            while (!ValidationUtil.isValidGenero(genero)) {
                System.out.println("Gênero inválido. Deve ser Masculino ou Feminino.");
                System.out.print("Gênero (Masculino/Feminino): ");
                genero = scanner.nextLine();
            }
            doacao.setRoupaGenero(genero);

            System.out.print("Tamanho (Infantil/PP/P/M/G/GG): ");
            String tamanho = scanner.nextLine();
            while (!ValidationUtil.isValidTamanho(tamanho)) {
                System.out.println("Tamanho inválido. Deve ser Infantil, PP, P, M, G ou GG.");
                System.out.print("Tamanho (Infantil/PP/P/M/G/GG): ");
                tamanho = scanner.nextLine();
            }
            doacao.setRoupaTamanho(tamanho);
        } else if (tipoItem.equalsIgnoreCase("higiene")) {
            System.out.print("Descrição do Produto de Higiene: ");
            String descricao = scanner.nextLine();
            while (!ValidationUtil.isValidDescricao(descricao)) {
                System.out.println("Descrição inválida. Deve ser uma string, pode conter espaço e números, mas não caracteres especiais.");
                System.out.print("Descrição do Produto de Higiene: ");
                descricao = scanner.nextLine();
            }
            doacao.setHigieneDescricao(descricao);

            System.out.print("Sabonete (true/false): ");
            String sabonete = scanner.nextLine();
            while (!ValidationUtil.isValidBoolean(sabonete)) {
                System.out.println("Valor inválido. Deve ser true ou false.");
                System.out.print("Sabonete (true/false): ");
                sabonete = scanner.nextLine();
            }
            doacao.setHigieneSabonete(Boolean.parseBoolean(sabonete));

            System.out.print("Escova de Dentes (true/false): ");
            String escova = scanner.nextLine();
            while (!ValidationUtil.isValidBoolean(escova)) {
                System.out.println("Valor inválido. Deve ser true ou false.");
                System.out.print("Escova de Dentes (true/false): ");
                escova = scanner.nextLine();
            }
            doacao.setHigieneEscova(Boolean.parseBoolean(escova));

            System.out.print("Pasta de Dentes (true/false): ");
            String pasta = scanner.nextLine();
            while (!ValidationUtil.isValidBoolean(pasta)) {
                System.out.println("Valor inválido. Deve ser true ou false.");
                System.out.print("Pasta de Dentes (true/false): ");
                pasta = scanner.nextLine();
            }
            doacao.setHigienePasta(Boolean.parseBoolean(pasta));

            System.out.print("Absorventes (true/false): ");
            String absorvente = scanner.nextLine();
            while (!ValidationUtil.isValidBoolean(absorvente)) {
                System.out.println("Valor inválido. Deve ser true ou false.");
                System.out.print("Absorventes (true/false): ");
                absorvente = scanner.nextLine();
            }
            doacao.setHigieneAbsorvente(Boolean.parseBoolean(absorvente));
        } else if (tipoItem.equalsIgnoreCase("alimento")) {
            System.out.print("Descrição do Alimento: ");
            String descricao = scanner.nextLine();
            while (!ValidationUtil.isValidDescricao(descricao)) {
                System.out.println("Descrição inválida. Deve ser uma string, pode conter espaço e números, mas não caracteres especiais.");
                System.out.print("Descrição do Alimento: ");
                descricao = scanner.nextLine();
            }
            doacao.setAlimentoDescricao(descricao);

            System.out.print("Unidade de Medida (kg, mg, g, t, l, ml): ");
            String unidade = scanner.nextLine();
            while (!ValidationUtil.isValidUnidadeMedida(unidade)) {
                System.out.println("Unidade de medida inválida. Deve ser kg, mg, g, t, l ou ml.");
                System.out.print("Unidade de Medida (kg, mg, g, t, l, ml): ");
                unidade = scanner.nextLine();
            }
            doacao.setAlimentoUnidadeMedida(unidade);

            System.out.print("Validade (dd/MM/yyyy): ");
            String dataValidade = scanner.nextLine();
            while (!ValidationUtil.isValidDataFutura(dataValidade)) {
                System.out.println("Data inválida ou no passado. Deve ser no formato dd/MM/yyyy e no futuro.");
                System.out.print("Validade (dd/MM/yyyy): ");
                dataValidade = scanner.nextLine();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date data = sdf.parse(dataValidade);
                doacao.setAlimentoValidade(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        doacaoDAO.save(doacao);
        System.out.println("Doação adicionada com sucesso!");
    }

    // Método para buscar a doação
    public void buscarDoacao(Scanner scanner) {
        System.out.print("ID da Doação: ");
        String idStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(idStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("ID da Doação: ");
            idStr = scanner.nextLine();
        }
        int id = Integer.parseInt(idStr);

        Doacao doacao = doacaoDAO.find(id);
        if (doacao != null) {
            System.out.println("Centro de Distribuição ID: " + doacao.getCentroDeDistribuicao().getId());
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

    // Método para atualizar a doação
    public void atualizarDoacao(Scanner scanner) {
        System.out.print("ID da Doação: ");
        String idStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(idStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("ID da Doação: ");
            idStr = scanner.nextLine();
        }
        int id = Integer.parseInt(idStr);

        Doacao doacao = doacaoDAO.find(id);
        if (doacao != null) {
            System.out.print("ID do Centro de Distribuição (" + doacao.getCentroDeDistribuicao().getId() + "): ");
            String centroIdStr = scanner.nextLine();
            while (!ValidationUtil.isValidId(centroIdStr)) {
                System.out.println("ID inválido. Deve ser um número inteiro.");
                System.out.print("ID do Centro de Distribuição (" + doacao.getCentroDeDistribuicao().getId() + "): ");
                centroIdStr = scanner.nextLine();
            }
            int centroId = Integer.parseInt(centroIdStr);
            CentroDeDistribuicao centro = centroDeDistribuicaoDAO.find(centroId);
            if (centro != null) {
                doacao.setCentroDeDistribuicao(centro);
            } else {
                System.out.println("Centro de Distribuição não encontrado.");
                return;
            }

            System.out.print("Tipo de Item (roupa/higiene/alimento) (" + doacao.getTipoItem() + "): ");
            String tipoItem = scanner.nextLine();
            while (!ValidationUtil.isValidTipoItem(tipoItem)) {
                System.out.println("Tipo de item inválido. Deve ser roupa, higiene ou alimento.");
                System.out.print("Tipo de Item (roupa/higiene/alimento) (" + doacao.getTipoItem() + "): ");
                tipoItem = scanner.nextLine();
            }
            doacao.setTipoItem(tipoItem);

            System.out.print("Quantidade (" + doacao.getQuantidade() + "): ");
            String quantidade = scanner.nextLine();
            while (!ValidationUtil.isValidQuantidade(quantidade)) {
                System.out.println("Quantidade inválida. Deve ser um número inteiro.");
                System.out.print("Quantidade (" + doacao.getQuantidade() + "): ");
                quantidade = scanner.nextLine();
            }
            doacao.setQuantidade(Integer.parseInt(quantidade));

            if (tipoItem.equalsIgnoreCase("roupa")) {
                System.out.print("Descrição da Roupa (" + doacao.getRoupaDescricao() + "): ");
                String descricao = scanner.nextLine();
                while (!ValidationUtil.isValidDescricao(descricao)) {
                    System.out.println("Descrição inválida. Deve ser uma string, pode conter espaço e números, mas não caracteres especiais.");
                    System.out.print("Descrição da Roupa (" + doacao.getRoupaDescricao() + "): ");
                    descricao = scanner.nextLine();
                }
                doacao.setRoupaDescricao(descricao);

                System.out.print("Gênero (Masculino/Feminino) (" + doacao.getRoupaGenero() + "): ");
                String genero = scanner.nextLine();
                while (!ValidationUtil.isValidGenero(genero)) {
                    System.out.println("Gênero inválido. Deve ser Masculino ou Feminino.");
                    System.out.print("Gênero (Masculino/Feminino) (" + doacao.getRoupaGenero() + "): ");
                    genero = scanner.nextLine();
                }
                doacao.setRoupaGenero(genero);

                System.out.print("Tamanho (Infantil/PP/P/M/G/GG) (" + doacao.getRoupaTamanho() + "): ");
                String tamanho = scanner.nextLine();
                while (!ValidationUtil.isValidTamanho(tamanho)) {
                    System.out.println("Tamanho inválido. Deve ser Infantil, PP, P, M, G ou GG.");
                    System.out.print("Tamanho (Infantil/PP/P/M/G/GG) (" + doacao.getRoupaTamanho() + "): ");
                    tamanho = scanner.nextLine();
                }
                doacao.setRoupaTamanho(tamanho);
            } else if (tipoItem.equalsIgnoreCase("higiene")) {
                System.out.print("Descrição do Produto de Higiene (" + doacao.getHigieneDescricao() + "): ");
                String descricao = scanner.nextLine();
                while (!ValidationUtil.isValidDescricao(descricao)) {
                    System.out.println("Descrição inválida. Deve ser uma string, pode conter espaço e números, mas não caracteres especiais.");
                    System.out.print("Descrição do Produto de Higiene (" + doacao.getHigieneDescricao() + "): ");
                    descricao = scanner.nextLine();
                }
                doacao.setHigieneDescricao(descricao);

                System.out.print("Sabonete (true/false) (" + doacao.getHigieneSabonete() + "): ");
                String sabonete = scanner.nextLine();
                while (!ValidationUtil.isValidBoolean(sabonete)) {
                    System.out.println("Valor inválido. Deve ser true ou false.");
                    System.out.print("Sabonete (true/false) (" + doacao.getHigieneSabonete() + "): ");
                    sabonete = scanner.nextLine();
                }
                doacao.setHigieneSabonete(Boolean.parseBoolean(sabonete));

                System.out.print("Escova de Dentes (true/false) (" + doacao.getHigieneEscova() + "): ");
                String escova = scanner.nextLine();
                while (!ValidationUtil.isValidBoolean(escova)) {
                    System.out.println("Valor inválido. Deve ser true ou false.");
                    System.out.print("Escova de Dentes (true/false) (" + doacao.getHigieneEscova() + "): ");
                    escova = scanner.nextLine();
                }
                doacao.setHigieneEscova(Boolean.parseBoolean(escova));

                System.out.print("Pasta de Dentes (true/false) (" + doacao.getHigienePasta() + "): ");
                String pasta = scanner.nextLine();
                while (!ValidationUtil.isValidBoolean(pasta)) {
                    System.out.println("Valor inválido. Deve ser true ou false.");
                    System.out.print("Pasta de Dentes (true/false) (" + doacao.getHigienePasta() + "): ");
                    pasta = scanner.nextLine();
                }
                doacao.setHigienePasta(Boolean.parseBoolean(pasta));

                System.out.print("Absorventes (true/false) (" + doacao.getHigieneAbsorvente() + "): ");
                String absorvente = scanner.nextLine();
                while (!ValidationUtil.isValidBoolean(absorvente)) {
                    System.out.println("Valor inválido. Deve ser true ou false.");
                    System.out.print("Absorventes (true/false) (" + doacao.getHigieneAbsorvente() + "): ");
                    absorvente = scanner.nextLine();
                }
                doacao.setHigieneAbsorvente(Boolean.parseBoolean(absorvente));
            } else if (tipoItem.equalsIgnoreCase("alimento")) {
                System.out.print("Descrição do Alimento (" + doacao.getAlimentoDescricao() + "): ");
                String descricao = scanner.nextLine();
                while (!ValidationUtil.isValidDescricao(descricao)) {
                    System.out.println("Descrição inválida. Deve ser uma string, pode conter espaço e números, mas não caracteres especiais.");
                    System.out.print("Descrição do Alimento (" + doacao.getAlimentoDescricao() + "): ");
                    descricao = scanner.nextLine();
                }
                doacao.setAlimentoDescricao(descricao);

                System.out.print("Unidade de Medida (kg, mg, g, t, l, ml) (" + doacao.getAlimentoUnidadeMedida() + "): ");
                String unidade = scanner.nextLine();
                while (!ValidationUtil.isValidUnidadeMedida(unidade)) {
                    System.out.println("Unidade de medida inválida. Deve ser kg, mg, g, t, l ou ml.");
                    System.out.print("Unidade de Medida (kg, mg, g, t, l, ml) (" + doacao.getAlimentoUnidadeMedida() + "): ");
                    unidade = scanner.nextLine();
                }
                doacao.setAlimentoUnidadeMedida(unidade);

                System.out.print("Validade (dd/MM/yyyy) (" + new SimpleDateFormat("dd/MM/yyyy").format(doacao.getAlimentoValidade()) + "): ");
                String dataValidade = scanner.nextLine();
                while (!ValidationUtil.isValidData(dataValidade)) {
                    System.out.println("Data inválida. Deve ser no formato dd/MM/yyyy.");
                    System.out.print("Validade (dd/MM/yyyy) (" + new SimpleDateFormat("dd/MM/yyyy").format(doacao.getAlimentoValidade()) + "): ");
                    dataValidade = scanner.nextLine();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date data = sdf.parse(dataValidade);
                    doacao.setAlimentoValidade(data);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            doacaoDAO.update(doacao);
            System.out.println("Doação atualizada com sucesso!");
        } else {
            System.out.println("Doação não encontrada.");
        }
    }

    // Método para deletar a doação
    public void deletarDoacao(Scanner scanner) {
        System.out.print("ID da Doação: ");
        String idStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(idStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("ID da Doação: ");
            idStr = scanner.nextLine();
        }
        int id = Integer.parseInt(idStr);

        doacaoDAO.delete(id);
        System.out.println("Doação deletada com sucesso!");
    }

    // Listar todas as doações associadas aos abrigos
    public void listarTodasDoacoes() {
        List<Doacao> doacoes = doacaoDAO.findAll();
        System.out.println("Lista de Doações:");
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
            String centroNome = doacao.getCentroDeDistribuicao() != null ? doacao.getCentroDeDistribuicao().getNome() : "N/A";
            System.out.println("ID: " + doacao.getId() +
                    ", Tipo de Item: " + doacao.getTipoItem() +
                    ", Descrição: " + descricao +
                    ", Quantidade: " + doacao.getQuantidade() +
                    ", Centro de Distribuição: " + centroNome);
        }
    }

    // Esse método é o requisito "Os abrigos listarão suas necessidades (item e atributos), e a aplicação indicará em quais
    //centros de distribuição os itens podem ser encontrados."
    public void fazerPedidoItens(Scanner scanner) {
        System.out.println("Tipos de Itens disponíveis: ");
        for (String tipoItem : doacaoDAO.findDistinctTipoItem()) {
            System.out.println(tipoItem);
        }
        System.out.print("Digite o tipo de item que deseja: ");
        String tipoItem = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        for (Doacao doacao : doacaoDAO.findByTipoItemAndQuantidade(tipoItem, quantidade)) {
            System.out.println("ID: " + doacao.getId() +
                    ", Centro de Distribuição ID: " + doacao.getCentroDeDistribuicao().getId() +
                    ", Centro de Distribuição: " + doacao.getCentroDeDistribuicao().getNome() +
                    ", Quantidade: " + doacao.getQuantidade());
        }
    }

    // Esse método transfere doações entre os centros de distribuição.
    public void transferirDoacoes(Scanner scanner) {
        System.out.println("Quais doações você deseja transferir?");
        System.out.println("Digite o tipo de item de doação:");

        for (String tipoItem : doacaoDAO.findDistinctTipoItem()) {
            System.out.println(tipoItem);
        }

        String tipoItemSelecionado = scanner.nextLine();
        System.out.println("Qual doação você deseja transferir?");
        System.out.println("Doações disponíveis:");

        for (Doacao doacao : doacaoDAO.findByTipoItem(tipoItemSelecionado)) {
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
        String doacaoIdStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(doacaoIdStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("Digite o ID da doação a ser transferida: ");
            doacaoIdStr = scanner.nextLine();
        }
        int doacaoId = Integer.parseInt(doacaoIdStr);

        int centroDeDistribuicaoOrigemId = doacaoDAO.find(doacaoId).getCentroDeDistribuicao().getId();
        System.out.println("Essa doação está cadastrada no Centro de Distribuição: " + centroDeDistribuicaoOrigemId);

        System.out.println("Para qual centro de distribuição você deseja transferir?");
        for (CentroDeDistribuicao centro : centroDeDistribuicaoDAO.findAll()) {
            System.out.println("ID: " + centro.getId() + ", Nome: " + centro.getNome());
        }
        String receberCentroIdStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(receberCentroIdStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("Digite o ID do Centro de Distribuição que irá RECEBER: ");
            receberCentroIdStr = scanner.nextLine();
        }
        int receberCentroId = Integer.parseInt(receberCentroIdStr);

        doacaoDAO.updateCentroDeDistribuicao(doacaoId, receberCentroId);
        System.out.println("Doação transferida com sucesso!");
    }

    // Esse método transfere doação para um abrigo
    public void transferirDoacaoParaAbrigo(Scanner scanner, AbrigoService abrigoService) {
        System.out.println("Digite o tipo de item de doação:");
        for (String tipoItem : doacaoDAO.findDistinctTipoItem()) {
            System.out.println(tipoItem);
        }

        String tipoItemSelecionado = scanner.nextLine();
        System.out.println("Digite o ID da doação a ser transferida:");
        for (Doacao doacao : doacaoDAO.findByTipoItem(tipoItemSelecionado)) {
            System.out.println("ID: " + doacao.getId() + ", Descrição: " + doacao.getRoupaDescricao());
        }
        String doacaoIdStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(doacaoIdStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("Digite o ID da doação a ser transferida: ");
            doacaoIdStr = scanner.nextLine();
        }
        int doacaoId = Integer.parseInt(doacaoIdStr);

        System.out.print("Digite o ID do Abrigo que irá RECEBER a doação: ");
        String abrigoIdStr = scanner.nextLine();
        while (!ValidationUtil.isValidId(abrigoIdStr)) {
            System.out.println("ID inválido. Deve ser um número inteiro.");
            System.out.print("Digite o ID do Abrigo que irá RECEBER a doação: ");
            abrigoIdStr = scanner.nextLine();
        }
        int abrigoId = Integer.parseInt(abrigoIdStr);

        int quantidadeAtual = abrigoService.getQuantidadeAtualDeItensPorTipo(abrigoId, tipoItemSelecionado);
        if (quantidadeAtual + doacaoDAO.find(doacaoId).getQuantidade() > 200) {
            System.out.println("Erro: Capacidade máxima de 200 itens atingida para o tipo de item " + tipoItemSelecionado + " no abrigo.");
            return;
        }

        doacaoDAO.transferirParaAbrigo(doacaoId, abrigoId);
        System.out.println("Doação transferida com sucesso!");
    }
}
