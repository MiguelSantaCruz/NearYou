package Model;

import java.util.List;

public interface ISessao {
    public void refreshLocalizacao();
    public List<PontoDeInteresse> localizaPisPorNome(String  nome, Float raioDistancia, int  classificacao, List<String>  tags);
    public List<PontoDeInteresse> localizaPisLocalizacao(String  localizacao, Float  raioDistancia, int  classificacao, List<String>  tags);
    public void alteraGosto(String  reviewID);
    public boolean verificaGosto(String  reviewID);
    public void alteraReport(String  reviewID);
    public boolean verificaReport(String  reviewID);
    public List<Utilizador> searchUser(String  Username);
    public int alteraDados(String  username, String  email, String  password);
    public void alteraReview(String  reviewID, String  comentario, int  classificacao);
    public boolean addReview(int classificacao, String  comentario, String idPI);
    public void alteraPiguardado(String  idPI);
    public boolean removePI(String  idPI);
    public boolean removeReview(String  reviewID);
    public void removeConta();
    public boolean verificaPtguardado(String  idPI);
    public boolean guardarPI(String  idPI);
}
