package Model;

import java.util.List;

public class PontoDeInteresse {
    String idPontoInteresse;
    String name;
    String descricao;
    List<String> tags;
    String pathFoto;
    String endereco;
    List<String> reviews;
    Float classificacaoMedia;

    public PontoDeInteresse(String idPontoInteresse, String name, String descricao, List<String> tags, String pathFoto, String endereco, List<String> reviews, Float classificacaoMedia) {
        this.idPontoInteresse = idPontoInteresse;
        this.name = name;
        this.descricao = descricao;
        this.tags = tags;
        this.pathFoto = pathFoto;
        this.endereco = endereco;
        this.reviews = reviews;
        this.classificacaoMedia = classificacaoMedia;
    }

    public PontoDeInteresse(String idPontoInteresse, String name, String descricao, String pathFoto, String endereco, Float classificacaoMedia) {
        this.idPontoInteresse = idPontoInteresse;
        this.name = name;
        this.descricao = descricao;
        this.pathFoto = pathFoto;
        this.endereco = endereco;
        this.classificacaoMedia = classificacaoMedia;
    }

    public String getIdPontoInteresse() {
        return idPontoInteresse;
    }

    public void setIdPontoInteresse(String idPontoInteresse) {
        this.idPontoInteresse = idPontoInteresse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public Float getClassificacaoMedia() {
        return classificacaoMedia;
    }

    public void setClassificacaoMedia(Float classificacaoMedia) {
        this.classificacaoMedia = classificacaoMedia;
    }

    @Override
    public String toString() {
        return "PontoDeInteresse{" +
                "idPontoInteresse='" + idPontoInteresse + '\'' +
                ", name='" + name + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tags=" + tags +
                ", pathFoto='" + pathFoto + '\'' +
                ", endereco='" + endereco + '\'' +
                ", reviews=" + reviews +
                ", classificacaoMedia=" + classificacaoMedia +
                '}';
    }

    /*
    public void carregaReviews(){

    }

    public String urlDirecoes(String localizacaoUtilizador){

    }

    public void calculaClassificacaoMedia(){

    }*/
}
