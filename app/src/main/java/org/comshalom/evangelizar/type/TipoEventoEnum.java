package org.comshalom.evangelizar.type;

/**
 * Created by matheus on 21/08/15.
 */
public enum TipoEventoEnum {

    OBRA (1, "Halleluya"),
    VOCACIONADO(2, "Lanchonete"),
    P1(3, "Noite de louvor"),
    P2(4, "Reviver"),
    D1(5, "Outro");

    private int codigo;
    private String descricao;

    TipoEventoEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    /**
     * Retorna a descriçao do enum pelo seu codigo
     * @param codigo do enum
     * @return descricao do enum
     */
    public static String getDescricaoByCodigo(int codigo) {
        for (TipoEventoEnum item : TipoEventoEnum.values()) {
            if(item.getCodigo() == codigo) {
                return item.getDescricao();
            }
        }
        return "";
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

}
