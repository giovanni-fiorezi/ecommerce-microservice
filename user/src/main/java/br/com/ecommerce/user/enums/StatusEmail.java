package br.com.ecommerce.user.enums;

public enum StatusEmail {

    ENVIADO(1L, "enviado"),
    ERRO(2L, "erro");

    private final Long id;
    private final String descricao;

    StatusEmail(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
