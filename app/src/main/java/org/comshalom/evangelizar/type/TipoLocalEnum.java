package org.comshalom.evangelizar.type;

/**
 * Created by matheus on 21/08/15.
 */
public enum TipoLocalEnum {

    OBRA (1, "Catete"),
    VOCACIONADO(2, "Recreio"),
    P1(3, "Santa Cruz"),
    P2(4, "Vigário");

    private int codigo;
    private String descricao;

    TipoLocalEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    /**
     * Retorna a descriçao do enum pelo seu codigo
     * @param codigo do enum
     * @return descricao do enum
     */
    public static String getDescricaoByCodigo(int codigo) {
        for (TipoLocalEnum item : TipoLocalEnum.values()) {
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
