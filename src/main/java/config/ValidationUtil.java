// Esse é a classe de Validação dos dados, toda vez que o usuário fizer o input dos dados, chamo essa classe
// e verifico se o input está de acordo com o que espero, por exemplo, o nome de um Abrigo ou do responsável eu
// quero que não tenha caracter especial, não contenha nenhum número e que tenha no mínimo 3 letras.

package config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern NOME_PATTERN = Pattern.compile("^[\\p{L} ]{3,}$"); // Mínimo 3 letras e não pode caracter especial e nem número
    private static final Pattern ENDERECO_PATTERN = Pattern.compile("^[\\p{L}0-9 .,-]+$"); // Endereço pode alguns caracteres especiais(.,-)
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^\\d{10,11}$"); // Telefone precisa ter 11 ou 10 dígitos e precisa ser inteiro
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$"); // email precisa ter o @
    private static final Pattern TIPO_ITEM_PATTERN = Pattern.compile("^(roupa|higiene|alimento)$"); // precisa ser ou roupa, ou higiene ou alimento
    private static final Pattern DESCRICAO_PATTERN = Pattern.compile("^[\\p{L}0-9 ]+$"); // descrição não pode ter caractere especial
    private static final Pattern UNIDADE_MEDIDA_PATTERN = Pattern.compile("^(kg|mg|g|t|l|ml)$"); // só podem ser essas unidades de mediga
    private static final Pattern DATA_PATTERN = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$"); // precisa ser uma data no formato dd/MM/yyyy

    public static boolean isValidNome(String nome) {
        return NOME_PATTERN.matcher(nome).matches();
    }

    public static boolean isValidEndereco(String endereco) {
        return ENDERECO_PATTERN.matcher(endereco).matches();
    }

    public static boolean isValidResponsavel(String responsavel) {
        return NOME_PATTERN.matcher(responsavel).matches();
    }

    public static boolean isValidTelefone(String telefone) {
        return TELEFONE_PATTERN.matcher(telefone).matches();
    }

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidCapacidade(String capacidade) {
        try {
            Integer.parseInt(capacidade);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidTipoItem(String tipoItem) {
        return TIPO_ITEM_PATTERN.matcher(tipoItem).matches();
    }

    public static boolean isValidQuantidade(String quantidade) {
        try {
            Integer.parseInt(quantidade);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDescricao(String descricao) {
        return DESCRICAO_PATTERN.matcher(descricao).matches();
    }

    public static boolean isValidGenero(String genero) {
        return genero.equalsIgnoreCase("Masculino") || genero.equalsIgnoreCase("Feminino");
    }

    public static boolean isValidTamanho(String tamanho) {
        return tamanho.matches("^(Infantil|PP|P|M|G|GG)$");
    }

    public static boolean isValidBoolean(String value) {
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
    }

    public static boolean isValidUnidadeMedida(String unidade) {
        return UNIDADE_MEDIDA_PATTERN.matcher(unidade).matches();
    }

    public static boolean isValidData(String data) {
        return DATA_PATTERN.matcher(data).matches();
    }

    public static boolean isValidCapacidadeTotal(String capacidadeTotal) {
        try {
            Integer.parseInt(capacidadeTotal);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidId(String id) {
        try {
            Integer.parseInt(id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Essa validação é para verificar se a data da validade é válida, por exemplo, não quero um alimento vencido.
    public static boolean isValidDataFutura(String data) {
        if (!isValidData(data)) {
            return false;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date date = sdf.parse(data);
            return date.after(new Date());
        } catch (ParseException e) {
            return false;
        }
    }
}