package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.exception.NotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ElectionService {
    //constructor
    public final ElectionRepository electionRepository;
    public final PositionRepository positionRepository;
    public final ElectionListRepository electionListRepository;
    public final CandidateRepository candidateRepository;
    public final VoteRepository voteRepository;
    private final AuditService auditService;
    private final ElectionObservationRepository electionObservationRepository;


    public ElectionService(ElectionRepository electionRepository,
                           PositionRepository positionRepository,
                           ElectionListRepository electionListRepository,
                           CandidateRepository candidateRepository,
                           VoteRepository voteRepository,
                           AuditService auditService,
                           ElectionObservationRepository electionObservationRepository) {
        this.electionRepository = electionRepository;
        this.positionRepository = positionRepository;
        this.electionListRepository = electionListRepository;
        this.candidateRepository = candidateRepository;
        this.voteRepository = voteRepository;
        this.auditService = auditService;
        this.electionObservationRepository = electionObservationRepository;
    }
    //fin


    //Listar elecciones desde el service
    public List<ElectionResponse> getAllElections() {
        return electionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //mapeo de mi entity al dto
    private ElectionResponse mapToResponse(Election election) {
        ElectionResponse response = new ElectionResponse();

        response.setId(election.getId());
        response.setTitle(election.getTitle());
        response.setDescription(election.getDescription());
        response.setStartDate(election.getStartDate());
        response.setEndDate(election.getEndDate());
        response.setStatus(election.getStatus());

        return response;
    }
    //Fin

    //CrearEleccion
    public ElectionResponse createElection( CreateElectionRequest request,
                                            String actor) {

        Election election = new Election();

        election.setTitle(request.getTitle());
        election.setDescription(request.getDescription());
        election.setStartDate(request.getStartDate());
        election.setEndDate(request.getEndDate());

        election.setStatus("DRAFT");

        Election savedElection = electionRepository.save(election);

        auditService.log(
                actor,
                "ELECTION_CREATED",
                "ELECTION",
                savedElection.getId(),
                "Se creó una nueva elección"
        );

        return mapToResponse(savedElection);
    }

    //buscar
    public ElectionResponse getElectionById(Long id) {

        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Elección no encontrada"));

        return mapToResponse(election);
    }

    //activarEleccion
    public ElectionResponse activateElection(Long id,
                                             String actor){
        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Elección no encontrada"));

        //validoque no este cerrado
        if ("CLOSED".equals(election.getStatus())){
            throw new IllegalStateException("Eleciones cerradas no se pueden volver a activar");
        }
        election.setStatus("ACTIVE");
        Election updatedElection = electionRepository.save(election);

        auditService.log(
                actor,
                "ELECTION_ACTIVATED",
                "ELECTION",
                updatedElection.getId(),
                "Se activó la elección"
        );

        return mapToResponse(updatedElection);
    }

    //eliminar
    public String deleteElection(Long id,
                                 String actor) {

        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Eleccion no encontrada"));

        electionRepository.delete(election);
        auditService.log(
                actor,
                "ELECTION_DELETED",
                "ELECTION",
                id,
                "Se eliminó la elección"
        );

        return "Elección eliminada con éxito";
    }

    //cerrarElección
    public ElectionResponse closeElection(Long id, String actor){

        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Eleccion no encontrada"));

        election.setStatus("CLOSED");

        Election updateElection = electionRepository.save(election);
        auditService.log(
                actor,
                "ELECTION_CLOSED",
                "ELECTION",
                updateElection.getId(),
                "Se cerró la elección"
        );

        return mapToResponse(updateElection);
    }

    //crearcargo
    public String addPosition(Long electionId,
                              CreatePositionRequest request,
                              String actor) {

        electionRepository.findById(electionId)
                .orElseThrow(() -> new NotFoundException("Elección no encontrada"));

        Position position = new Position();
        position.setName(request.getName());
        position.setElectionId(electionId);
        positionRepository.save(position);

        auditService.log(
                actor,
                "POSITION_CREATED",
                "POSITION",
                position.getId(),
                "Se agregó un cargo a la elección"
        );

        return "Cargo agregado correctamente";
    }

    //listaCargos
    public List<PositionResponse> getPositionsByElection(Long electionId) {

        electionRepository.findById(electionId)
                .orElseThrow(() -> new NotFoundException("Elección no encontrada"));

        return positionRepository.findByElectionId(electionId)
                .stream()
                .map(this::mapPositionToResponse)
                .collect(Collectors.toList());
    }
    private PositionResponse mapPositionToResponse(Position position) {
        PositionResponse response = new PositionResponse();

        response.setId(position.getId());
        response.setName(position.getName());

        return response;
    }

    //CrearLista
    public String registerList(Long electionId, CreateElectionListRequest request, String actor) {

        electionRepository.findById(electionId)
                .orElseThrow(() -> new NotFoundException("Elección no encontrada"));

        ElectionList electionList = new ElectionList();
        electionList.setElectionId(electionId);
        electionList.setName(request.getName());
        electionList.setDescription(request.getDescription());

        electionListRepository.save(electionList);
        auditService.log(
                actor,
                "LIST_CREATED",
                "ELECTION_LIST",
                electionList.getId(),
                "Se registró una nueva lista"
        );

        return "Lista registrada correctamente";
    }

    //filtrarLista
    public List<ElectionListResponse> getListsByElection(Long electionId) {

        electionRepository.findById(electionId)
                .orElseThrow(() -> new NotFoundException("Elección no encontrada"));

        return electionListRepository.findByElectionId(electionId)
                .stream()
                .map(this::mapElectionListToResponse)
                .collect(Collectors.toList());
    }
    private ElectionListResponse mapElectionListToResponse(ElectionList electionList) {
        ElectionListResponse response = new ElectionListResponse();

        response.setId(electionList.getId());
        response.setName(electionList.getName());
        response.setDescription(electionList.getDescription());

        return response;
    }

    //CrearCandidato
    public String addCandidate(Long electionId, Long listId,
                               CreateCandidateRequest request,
                               String actor) {

        electionRepository.findById(electionId)
                .orElseThrow(() -> new NotFoundException("Elección no encontrada"));

        ElectionList electionList = electionListRepository.findById(listId)
                .orElseThrow(() -> new NotFoundException("Lista no encontrada"));

        if (!electionList.getElectionId().equals(electionId)) {
            throw new NotFoundException("La lista no pertenece a la elección");
        }
        Position position = positionRepository.findById(request.getPositionId())
                .orElseThrow(() -> new NotFoundException("Cargo no encontrado"));
        if (!position.getElectionId().equals(electionId)) {
            throw new NotFoundException("El cargo no pertenece a la elección");
        }

        Candidate candidate = new Candidate();
        candidate.setListId(listId);
        candidate.setPositionId(request.getPositionId());
        candidate.setFullName(request.getFullName());
        candidate.setCareer(request.getCareer());
        candidate.setProposal(request.getProposal());

        candidateRepository.save(candidate);
        auditService.log(
                actor,
                "CANDIDATE_CREATED",
                "CANDIDATE",
                candidate.getId(),
                "Se registró un nuevo candidato"
        );

        return "Candidato registrado correctamente";
    }

    //FiltrarCandidato
    public List<CandidateResponse> getCandidatesByList(Long electionId, Long listId) {

        electionRepository.findById(electionId)
                .orElseThrow(() -> new NotFoundException("Elección no encontrada"));

        ElectionList electionList = electionListRepository.findById(listId)
                .orElseThrow(() -> new NotFoundException("Lista no encontrada"));

        if (!electionList.getElectionId().equals(electionId)) {
            throw new NotFoundException("La lista no pertenece a la elección indicada");
        }

        return candidateRepository.findByListId(listId)
                .stream()
                .map(this::mapCandidateToResponse)
                .collect(Collectors.toList());
    }

    private CandidateResponse mapCandidateToResponse(Candidate candidate) {
        CandidateResponse response = new CandidateResponse();

        response.setId(candidate.getId());
        response.setPositionId(candidate.getPositionId());
        response.setFullName(candidate.getFullName());
        response.setCareer(candidate.getCareer());
        response.setProposal(candidate.getProposal());

        return response;
    }
    //VOTAR
    public String vote(Long electionId, CreateVoteRequest request, String voterCode) {

        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new NotFoundException("Elección no encontrada"));

        if (!"ACTIVE".equals(election.getStatus())) {
            throw new NotFoundException("La elección no está activa");
        }

        ElectionList electionList = electionListRepository.findById(request.getListId())
                .orElseThrow(() -> new NotFoundException("Lista no encontrada"));

        if (!electionList.getElectionId().equals(electionId)) {
            throw new NotFoundException("La lista no pertenece a la elección");
        }
        if (voteRepository.existsByElectionIdAndVoterCode(electionId,voterCode)) {

            throw new IllegalStateException("El votante ya realizó su voto");
        }

        Vote vote = new Vote();
        vote.setElectionId(electionId);
        vote.setListId(request.getListId());
        vote.setVoterCode(voterCode);

        voteRepository.save(vote);
        auditService.log(
                voterCode,
                "VOTE_CAST",
                "VOTE",
                vote.getId(),
                "El usuario realizó su voto"
        );

        return "Voto registrado correctamente";
    }

    //ConsultarVoto
    public boolean getMyVotingStatus(Long electionId, String voterCode) {

        electionRepository.findById(electionId)
                .orElseThrow(() -> new NotFoundException("Elección no encontrada"));

        return voteRepository.existsByElectionIdAndVoterCode(electionId, voterCode);
    }

    //miEstadoVoto
    public List<ElectionResultResponse> getResults(Long electionId) {

        electionRepository.findById(electionId)
                .orElseThrow(() -> new NotFoundException("Elección no encontrada"));

        long totalVotes = voteRepository.countByElectionId(electionId);

        return electionListRepository.findByElectionId(electionId)
                .stream()
                .map(list -> {
                    long votes = voteRepository.countByElectionIdAndListId(electionId, list.getId());

                    ElectionResultResponse response = new ElectionResultResponse();
                    response.setListId(list.getId());
                    response.setListName(list.getName());
                    response.setVotes(votes);
                    response.setPercentage(totalVotes == 0 ? 0 : (votes * 100.0) / totalVotes);

                    return response;
                })
                .collect(Collectors.toList());
    }

    //estadisticaParticipacion
    public ElectionStatsResponse getElectionStats(Long electionId) {

        electionRepository.findById(electionId)
                .orElseThrow(() -> new NotFoundException("Elección no encontrada"));

        long votesCast = voteRepository.countByElectionId(electionId);

        ElectionStatsResponse response = new ElectionStatsResponse();
        response.setElectionId(electionId);
        response.setVotesCast(votesCast);

        return response;
    }

    //implementar reporte final detallado
    public String getFinalReport(Long id){
        return "Reporte final elección ID: " + id;
    }

    //implementar exportación real de auditoría
    public String exportElectionAudit(Long id){
        return "Auditoría elección ID: " + id;
    }


    //Prueba
    public ObservationResponse electionObservations  (Long electionId,
                                        CreateObservationRequest request,
                                        String actor){
        electionRepository.findById(electionId)
                .orElseThrow(() -> new NotFoundException("Elección no encontrada"));

        ElectionObservations electionObs = new ElectionObservations();

        electionObs.setElection_id(electionId);
        electionObs.setActor(actor);
        electionObs.setComment(request.getComment());
        electionObs.setCreated_at(LocalDateTime.now());

        ElectionObservations savedObservation =
                electionObservationRepository.save(electionObs);

        auditService.log(
                actor,
                "OBSERVATION_CREATED",
                "ELECTION_OBSERVATION",
                electionObs.getId(),
                "Se registró una nueva Observacion"
        );
        ObservationResponse response = new ObservationResponse();
        response.setId(savedObservation.getId());
        response.setElection_id(savedObservation.getElection_id());
        response.setActor(savedObservation.getActor());
        response.setComment(savedObservation.getComment());
        response.setCreated_at(savedObservation.getCreated_at());
        return response;
    }


}
