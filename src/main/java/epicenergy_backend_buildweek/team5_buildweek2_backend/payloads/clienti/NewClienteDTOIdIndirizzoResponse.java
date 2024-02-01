package epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.clienti;

//bisognerebbe passare anche l'id dell'indirizzo nella response
public record NewClienteDTOIdIndirizzoResponse(String ragioneSociale,
                                               String emailAziendale,
                                               long idIndirizzo
) {
}
