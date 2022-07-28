package com.iberthy.backend.utils;

public final class Message {
    public static final String nomeNotBlank = "O nome não pode ser vazio ou nulo!";

    public static final String descricaoNotBlank = "A descrição não pode ser vazia ou nula!";

    public static final String telefoneNotBlank = "O telefone não pode ser vazio ou nulo!";

    public static final String loginNotBlank = "O login não pode ser vazio ou nulo!";

    public static final String saldoCarteiraNotNull = "O saldo da carteira não pode ser nulo!";

    public static final String sexoNotBlank = "O sexo não pode ser vazio ou nulo!";
    public static final String sexoValidate = "O sexo informado não corresponde aos aceitos pela API! [Feminino(F), Masculino(M), Não informado(N)]";

    public static final String cpfNotBlank = "O cpf não pode ser vazio ou nulo!";
    public static final String cpfInvalidFormat = "O cpf está com o formato inválido! (Esperado: 000.000.000-00)";

    public static final String emailNotBlank = "O e-mail não pode ser vazio ou nulo!";
    public static final String emailInvalidFormat = "O e-mail está com o formato inválido! (Esperado: exemplo@hotmail.com)";

    public static final String senhaNotBlank = "A senha não pode ser vazio ou nula!";
    public static final String senhaInvalidFormat = "A senha deve conter no mínimo 6 caracteres, pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial!";

    public static final String precoNotNull = "O preço do produto não pode nulo!";
    public static final String precoMin = "O preço do produto não pode ser menor que zero!";


    public static final String invalidClienteId = "O ID do cliente é invalido!";

    public static final String invalidProdutoId = "O ID do produto é invalido!";

    public static final String notSavePedidoAndItemsIsEmpty = "Não é possível realizar um pedido que não contenha itens!";

}
