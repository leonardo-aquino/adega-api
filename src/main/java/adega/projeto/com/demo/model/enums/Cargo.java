package adega.projeto.com.demo.model.enums;

public enum Cargo {
    GERENTE("Gerente"),
    FUNCIONARIO("Funcionário"),
    DONO("Dono");

    private final String descricao;

    Cargo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Cargo toEnum(String valor) {
        for (Cargo cargo : Cargo.values()) {
            if (cargo.descricao.equalsIgnoreCase(valor)) {
                return cargo;
            }
        }
        throw new IllegalArgumentException(
                "Cargo inválido. Válidos são: GERENTE, FUNCIONÁRIO, DONO"
        );
    }
}