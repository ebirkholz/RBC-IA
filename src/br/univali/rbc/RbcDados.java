package br.univali.rbc;

public class RbcDados {

    private String marca;
    private Double marcaPeso;
    private String modelo;
    private Double modeloPeso;
    private Integer ano;
    private Double anoPeso;
    private String cor;
    private Double corPeso;
    private String kmRodados;
    private Double kmRodadosPeso;
    private Double preco;
    private Double precoPeso;
    private Integer potencia;
    private Double potenciaPeso;
    private String combustivel;
    private Double combustivelPeso;
    private Boolean automatico;
    private Double automaticoPeso;
    private Integer quantidadePortas;
    private String quantidadePortasPeso;
    private String modeloVeiculoVendido;

    public RbcDados(String marca,
                    Double marcaPeso,
                    String modelo,
                    Double modeloPeso,
                    Integer ano,
                    Double anoPeso,
                    String cor,
                    Double corPeso,
                    String kmRodados,
                    Double kmRodadosPeso,
                    Double preco,
                    Double precoPeso,
                    Integer potencia,
                    Double potenciaPeso,
                    String combustivel,
                    Double combustivelPeso,
                    Boolean automatico,
                    Double automaticoPeso,
                    Integer quantidadePortas,
                    String quantidadePortasPeso) {
        this.marca = marca;
        this.marcaPeso = marcaPeso;
        this.modelo = modelo;
        this.modeloPeso = modeloPeso;
        this.ano = ano;
        this.anoPeso = anoPeso;
        this.cor = cor;
        this.corPeso = corPeso;
        this.kmRodados = kmRodados;
        this.kmRodadosPeso = kmRodadosPeso;
        this.preco = preco;
        this.precoPeso = precoPeso;
        this.potencia = potencia;
        this.potenciaPeso = potenciaPeso;
        this.combustivel = combustivel;
        this.combustivelPeso = combustivelPeso;
        this.automatico = automatico;
        this.automaticoPeso = automaticoPeso;
        this.quantidadePortas = quantidadePortas;
        this.quantidadePortasPeso = quantidadePortasPeso;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getMarcaPeso() {
        return marcaPeso;
    }

    public void setMarcaPeso(Double marcaPeso) {
        this.marcaPeso = marcaPeso;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getModeloPeso() {
        return modeloPeso;
    }

    public void setModeloPeso(Double modeloPeso) {
        this.modeloPeso = modeloPeso;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getAnoPeso() {
        return anoPeso;
    }

    public void setAnoPeso(Double anoPeso) {
        this.anoPeso = anoPeso;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getCorPeso() {
        return corPeso;
    }

    public void setCorPeso(Double corPeso) {
        this.corPeso = corPeso;
    }

    public String getKmRodados() {
        return kmRodados;
    }

    public void setKmRodados(String kmRodados) {
        this.kmRodados = kmRodados;
    }

    public Double getKmRodadosPeso() {
        return kmRodadosPeso;
    }

    public void setKmRodadosPeso(Double kmRodadosPeso) {
        this.kmRodadosPeso = kmRodadosPeso;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getPrecoPeso() {
        return precoPeso;
    }

    public void setPrecoPeso(Double precoPeso) {
        this.precoPeso = precoPeso;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public Double getPotenciaPeso() {
        return potenciaPeso;
    }

    public void setPotenciaPeso(Double potenciaPeso) {
        this.potenciaPeso = potenciaPeso;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public Double getCombustivelPeso() {
        return combustivelPeso;
    }

    public void setCombustivelPeso(Double combustivelPeso) {
        this.combustivelPeso = combustivelPeso;
    }

    public Boolean getAutomatico() {
        return automatico;
    }

    public void setAutomatico(Boolean automatico) {
        this.automatico = automatico;
    }

    public Double getAutomaticoPeso() {
        return automaticoPeso;
    }

    public void setAutomaticoPeso(Double automaticoPeso) {
        this.automaticoPeso = automaticoPeso;
    }

    public Integer getQuantidadePortas() {
        return quantidadePortas;
    }

    public void setQuantidadePortas(Integer quantidadePortas) {
        this.quantidadePortas = quantidadePortas;
    }

    public String getQuantidadePortasPeso() {
        return quantidadePortasPeso;
    }

    public void setQuantidadePortasPeso(String quantidadePortasPeso) {
        this.quantidadePortasPeso = quantidadePortasPeso;
    }

    public String getModeloVeiculoVendido() {
        return modeloVeiculoVendido;
    }

    public void setModeloVeiculoVendido(String modeloVeiculoVendido) {
        this.modeloVeiculoVendido = modeloVeiculoVendido;
    }

    @Override
    public String toString() {
        return "RbcDados{" +
                "marca='" + marca + '\'' +
                ", marcaPeso=" + marcaPeso +
                ", modelo='" + modelo + '\'' +
                ", modeloPeso=" + modeloPeso +
                ", ano=" + ano +
                ", anoPeso=" + anoPeso +
                ", cor='" + cor + '\'' +
                ", corPeso=" + corPeso +
                ", kmRodados='" + kmRodados + '\'' +
                ", kmRodadosPeso=" + kmRodadosPeso +
                ", preco=" + preco +
                ", precoPeso=" + precoPeso +
                ", potencia=" + potencia +
                ", potenciaPeso=" + potenciaPeso +
                ", combustivel='" + combustivel + '\'' +
                ", combustivelPeso=" + combustivelPeso +
                ", automatico=" + automatico +
                ", automaticoPeso=" + automaticoPeso +
                ", quantidadePortas=" + quantidadePortas +
                ", quantidadePortasPeso='" + quantidadePortasPeso + '\'' +
                ", modeloVeiculoVendido='" + modeloVeiculoVendido + '\'' +
                '}';
    }
}
