package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.ElectionService;
import com.example.demo.dto.ElectionResponse;
import java.util.List;
import com.example.demo.entity.Election;
import com.example.demo.dto.CreateElectionRequest;
import com.example.demo.dto.CreatePositionRequest;
import com.example.demo.dto.PositionResponse;

@RestController
@RequestMapping("/api/v1/elections")
public class ElectionController {
    //inyección por constructor
    private final ElectionService electionService;

    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }
    //fin const

    //creamosEleccion
    @PostMapping
    public ElectionResponse createElection(@RequestBody CreateElectionRequest request) {
        return electionService.createElection(request);
    }
    //listarElecciones
    @GetMapping
    public List<ElectionResponse> getAllElections() {
        return electionService.getAllElections();
    }
    //detalleEleccion
    @GetMapping("/{id}")
    public ElectionResponse getElectionById(@PathVariable Long id) {
        return electionService.getElectionById(id);
    }

    //activarEleccion
    @PutMapping("/{id}/activate")
    public ElectionResponse activateElection(@PathVariable Long id) {
        return electionService.activateElection(id);
    }
    //ELIMINAR
    @DeleteMapping("/{id}")
    public String deleteElection (@PathVariable Long id) {
        return electionService.deleteElection(id);

    }
    //cerrarEleccion
    @PutMapping("/{id}/close")
    public ElectionResponse closeElection(@PathVariable Long id) {
        return electionService.closeElection(id);
    }

    //agregarCargo
    @PostMapping("/{id}/positions")
    public String addPosition(@PathVariable Long id,
                              @RequestBody CreatePositionRequest request) {
        return electionService.addPosition(id, request);
    }
    //Listarcargo
    @GetMapping("/{id}/positions")
    public List<PositionResponse> getPositionsByElection(@PathVariable Long id) {
        return electionService.getPositionsByElection(id);
    }




    //votar
    @PostMapping("/{id}/vote")
    public String vote(@PathVariable Long id) {
        return electionService.vote(id);
    }
    //estadoVoto
    @GetMapping("/{id}/my-status")
    public String getMyVotingStatus(@PathVariable Long id) {
        return electionService.getMyVotingStatus(id);
    }
    //resultados
    @GetMapping("/{id}/results")
    public String getResults(@PathVariable Long id) {
        return electionService.getResults(id);
    }
    //estadisticas
    @GetMapping("/{id}/stats")
    public String getElectionStats(@PathVariable Long id) {
        return electionService.getElectionStats(id);
    }
    //reporte
    @GetMapping("/{id}/report")
    public String getFinalReport(@PathVariable Long id) {
        return electionService.getFinalReport(id);
    }
    //auditoriaEleccion
    @GetMapping("/{id}/audit")
    public String exportElectionAudit(@PathVariable Long id) {
        return electionService.exportElectionAudit(id);
    }
    //registrarLista
    @PostMapping("/{id}/lists")
    public String registerList(@PathVariable Long id) {
        return electionService.registerList(id);
    }
}