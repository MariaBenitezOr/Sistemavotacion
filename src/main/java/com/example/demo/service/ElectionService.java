package com.example.demo.service;

import com.example.demo.dto.CreateElectionRequest;
import org.springframework.stereotype.Service;
import com.example.demo.repository.ElectionRepository;
import com.example.demo.entity.Election;
import java.util.List;
import java.util.stream.Collectors;
import com.example.demo.dto.ElectionResponse;
import com.example.demo.repository.PositionRepository;
import com.example.demo.dto.CreatePositionRequest;
import com.example.demo.entity.Position;
import com.example.demo.dto.PositionResponse;

@Service
public class ElectionService {
    //constructor
    public final ElectionRepository electionRepository;
    public final PositionRepository positionRepository;

    public ElectionService(ElectionRepository electionRepository,
                           PositionRepository positionRepository) {
        this.electionRepository = electionRepository;
        this.positionRepository = positionRepository;
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

    public ElectionResponse createElection(CreateElectionRequest request) {

        Election election = new Election();

        election.setTitle(request.getTitle());
        election.setDescription(request.getDescription());
        election.setStartDate(request.getStartDate());
        election.setEndDate(request.getEndDate());

        election.setStatus("DRAFT");

        Election savedElection = electionRepository.save(election);

        return mapToResponse(savedElection);
    }
    //buscar
    public ElectionResponse getElectionById(Long id) {

        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Elección no encontrada"));

        return mapToResponse(election);
    }
    //activarEleccion
    public ElectionResponse activateElection(Long id){
        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Elección no encontrada"));

        //validoque no este cerrado
        if ("CLOSED".equals(election.getStatus())){
            throw new RuntimeException("Eleciones cerradas no se pueden volver a activar");
        }

        election.setStatus("ACTIVO");

        Election updatedElection = electionRepository.save(election);

        return mapToResponse(updatedElection);
    }

    //eliminar
    public String deleteElection(Long id) {

        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Eleccion no encontrada"));

        electionRepository.delete(election);

        return "Elección eliminada con éxito";
    }
    //cerrarElección
    public ElectionResponse closeElection(Long id){
        Election election = electionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Eleccion no encontrada"));

        election.setStatus("CLOSED");

        Election updateElection = electionRepository.save(election);

        return mapToResponse(updateElection);
    }
    //crearcargo
    public String addPosition(Long electionId, CreatePositionRequest request) {

        electionRepository.findById(electionId)
                .orElseThrow(() -> new RuntimeException("Elección no encontrada"));

        Position position = new Position();
        position.setName(request.getName());
        position.setElectionId(electionId);

        positionRepository.save(position);

        return "Cargo agregado correctamente";
    }
    //listaCargos
    public List<PositionResponse> getPositionsByElection(Long electionId) {

        electionRepository.findById(electionId)
                .orElseThrow(() -> new RuntimeException("Elección no encontrada"));

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





    public String vote(Long id){
        return "Votar en elección ID: " + id;
    }
    //miEstadoVoto
    public String getMyVotingStatus(Long id){
        return "Consultar si ya voté en elección ID: " + id;
    }

    public String getResults(Long id){
        return "Resultados elección ID: " + id;
    }

    public String getElectionStats(Long id){
        return "Estadísticas elección ID: " + id;
    }

    public String getFinalReport(Long id){
        return "Reporte final elección ID: " + id;
    }

    public String exportElectionAudit(Long id){
        return "Auditoría elección ID: " + id;
    }
    public String registerList(Long id){
        return "Registrar lista en elección ID: "+ id;
    }
}
