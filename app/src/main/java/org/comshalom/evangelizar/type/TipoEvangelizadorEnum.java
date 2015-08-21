package org.comshalom.evangelizar.type;

/**
 * Created by matheus on 21/08/15.
 */
public enum TipoEvangelizadorEnum {

    OBRA (1, "Obra"),
    VOCACIONADO(2, "Vocacionado(a)"),
    P1(3, "P1"),
    P2(4, "P2"),
    D1(5, "D1"),
    D2(6, "D2"),
    CONSAGRADO(7, "Consagrado(a)");

    private int codigo;
    private String descricao;

    TipoEvangelizadorEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    /**
     * Retorna a descriçao do enum pelo seu codigo
     * @param codigo do enum
     * @return descricao do enum
     */
    public static String getDescricaoByCodigo(int codigo) {
        for (TipoEvangelizadorEnum item : TipoEvangelizadorEnum.values()) {
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
