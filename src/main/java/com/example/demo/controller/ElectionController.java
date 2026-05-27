package com.example.demo.controller;
import com.example.demo.dto.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.ElectionService;
import com.example.demo.dto.CreateElectionListRequest;
import java.util.List;
import com.example.demo.dto.ElectionListResponse;
import com.example.demo.dto.CreateCandidateRequest;
import com.example.demo.dto.CreateObservationRequest;
import com.example.demo.dto.CandidateResponse;
import com.example.demo.dto.ElectionResultResponse;
import com.example.demo.dto.ElectionStatsResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

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
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ElectionResponse createElection(@Valid @RequestBody CreateElectionRequest request,
                                           @AuthenticationPrincipal Jwt jwt) {

        String actor = jwt.getClaimAsString("email");
        return electionService.createElection(request,actor);
    }

    //listarElecciones
    @PreAuthorize("hasAnyRole('ADMIN','VOTER','AUDITOR')")
    @GetMapping
    public List<ElectionResponse> getAllElections() {
        return electionService.getAllElections();
    }

    //detalleEleccion
    @PreAuthorize("hasAnyRole('ADMIN','VOTER','AUDITOR')")
    @GetMapping("/{id}")
    public ElectionResponse getElectionById(@PathVariable Long id,
                                            @AuthenticationPrincipal Jwt jwt) {


        return electionService.getElectionById(id);
    }

    //activarEleccion
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/activate")
    public ElectionResponse activateElection(@PathVariable Long id,
                                             @AuthenticationPrincipal Jwt jwt) {
        String actor = jwt.getClaimAsString("email");
        return electionService.activateElection(id ,actor);
    }

    //ELIMINAR
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteElection (@PathVariable Long id,
                                  @AuthenticationPrincipal Jwt jwt) {

        String actor = jwt.getClaimAsString("email");
        return electionService.deleteElection(id, actor);
    }

    //cerrarEleccion
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/close")
    public ElectionResponse closeElection(@PathVariable Long id,
                                          @AuthenticationPrincipal Jwt jwt) {

        String actor = jwt.getClaimAsString("email");

        return electionService.closeElection(id, actor);
    }

    //agregarCargo
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/positions")
    public String addPosition(@PathVariable Long id,
                              @Valid @RequestBody CreatePositionRequest request,
                              @AuthenticationPrincipal Jwt jwt) {

        String actor = jwt.getClaimAsString("email");
        return electionService.addPosition(id, request, actor);
    }

    //Listarcargo
    @PreAuthorize("hasAnyRole('ADMIN','VOTER','AUDITOR')")
    @GetMapping("/{id}/positions")
    public List<PositionResponse> getPositionsByElection(@PathVariable Long id) {
        return electionService.getPositionsByElection(id);
    }

    //registrarLista
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/lists")
    public String registerList(@PathVariable Long id,
                               @Valid @RequestBody CreateElectionListRequest request,
                               @AuthenticationPrincipal Jwt jwt) {

        String actor = jwt.getClaimAsString("email");
        return electionService.registerList(id , request, actor);
    }

    //filtrarLista
    @PreAuthorize("hasAnyRole('ADMIN','VOTER','AUDITOR')")
    @GetMapping("/{id}/lists")
    public List<ElectionListResponse> getListsByElection(@PathVariable Long id) {
        return electionService.getListsByElection(id);
    }

    //CrearCandidato
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{electionId}/lists/{listId}/candidates")
    public String addCandidate (@PathVariable Long electionId,
                                @PathVariable Long listId,
                                @Valid @RequestBody CreateCandidateRequest request,
                                @AuthenticationPrincipal Jwt jwt){

        String actor = jwt.getClaimAsString("email");
        return electionService.addCandidate(electionId, listId, request, actor);
    }

    //ListarListadeCandidatos
    @PreAuthorize("hasAnyRole('ADMIN','VOTER','AUDITOR')")
    @GetMapping ("/{electionId}/lists/{listId}/candidates")
    public List<CandidateResponse> getCandidatesByList(@PathVariable Long electionId,
                                                       @PathVariable Long listId) {
        return electionService.getCandidatesByList(electionId, listId);
    }

    //votar
    @PreAuthorize("hasRole('VOTER')")
    @PostMapping("/{id}/vote")
    public String vote(@PathVariable Long id,
                       @Valid @RequestBody CreateVoteRequest request,
                       @AuthenticationPrincipal Jwt jwt) {

        String voterCode = jwt.getClaimAsString("email");
        return electionService.vote(id, request, voterCode);
    }

    //estadoVoto
    @PreAuthorize("hasRole('VOTER')")
    @GetMapping ("/{id}/my-status")
    public boolean getMyVotingStatus (@PathVariable Long id,
                                      @AuthenticationPrincipal Jwt jwt){

                String voterCode = jwt.getClaimAsString("email");

                return electionService.getMyVotingStatus(id, voterCode);
    }

    //resultadosVotacion
    @PreAuthorize("hasAnyRole('ADMIN','AUDITOR')")
    @GetMapping("/{id}/results")
    public List<ElectionResultResponse> getResults (@PathVariable Long id) {
        return electionService.getResults(id);
    }

    //estadisticas
    @PreAuthorize("hasAnyRole('ADMIN','AUDITOR')")
    @GetMapping("/{id}/stats")
    public ElectionStatsResponse getElectionStats(@PathVariable Long id) {
        return electionService.getElectionStats(id);
    }


    //reporte
    @PreAuthorize("hasAnyRole('ADMIN','AUDITOR')")
    @GetMapping("/{id}/report")
    public String getFinalReport(@PathVariable Long id) {
        return electionService.getFinalReport(id);
    }

    //auditoriaEleccion
    @PreAuthorize("hasRole('AUDITOR')")
    @GetMapping("/{id}/audit")
    public String exportElectionAudit(@PathVariable Long id) {
        return electionService.exportElectionAudit(id);
    }

    //Prueba
    @PreAuthorize("hasRole('AUDITOR')")
    @PostMapping ("/{id}/observations")
    public ObservationResponse electionObservations (@PathVariable Long id ,
                                        @Valid @RequestBody CreateObservationRequest request,
                                        @AuthenticationPrincipal Jwt jwt){

        String actor = jwt.getClaimAsString("email");
        return electionService.electionObservations (id,request,actor );
    }

    //Prueba2
    @PreAuthorize("hasRole('AUDITOR')")
    @PostMapping ("/{id}/incidents")
    public IncidentResponse electionIncidents (@PathVariable Long id,
                                               @Valid @RequestBody CreateIncidentRequest request ,
                                               @AuthenticationPrincipal Jwt jwt){

        String actor = jwt.getClaimAsString("email");
        return electionService.electionIncidents(id ,request ,actor);
    }


}