public enum Distrito
{
    ACORES(false),
    AVEIRO(false),
    BEJA(false),
    BRAGA(false),
    BRAGANCA(true),
    CASTELO_BRANCO(true),
    COIMBRA(false),
    EVORA(false),
    FARO(false),
    GUARDA(true),
    LEIRIA(false),
    LISBOA(false),
    MADEIRA(false),
    PORTALEGRE(true),
    PORTO(false),
    SANTAREM(false),
    SETUBAL(false),
    VIANA_DO_CASTELO(false),
    VILA_REAL(true),
    VISEU(true);
    
    private boolean interior;
    
    private Distrito(boolean interior){
        this.interior = interior;
    }
}
