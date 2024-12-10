package com.example.grpc2.controllers;

import io.grpc.stub.StreamObserver;
import com.example.grpc2.services.CompteService;
import com.example.grpc2.stubs.*;
import com.example.grpc2.*;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@GrpcService
public class CompteServiceImpl extends CompteServiceGrpc.CompteServiceImplBase {
    private final CompteService compteService;
    public CompteServiceImpl (CompteService compteService) {
        this.compteService =compteService;
    }
    @Override
    public void allComptes (GetAllComptesRequest request,
                            StreamObserver<GetAllComptesResponse> responseObserver) {
        var comptes = compteService.findAllComptes().stream()
                .map(compte -> Compte.newBuilder()
                        .setId(String.valueOf(compte.getId()))
                        .setSolde (compte.getSolde())
                        .setDateCreation(compte.getDateCreation())
                        .setType (TypeCompte.valueOf(compte.getType()))
                        .build())
                .collect (Collectors.toList());
        responseObserver.onNext (GetAllComptesResponse.newBuilder()
                .addAllComptes (comptes).build());
        responseObserver.onCompleted();
    }

    @Override
    public void saveCompte(SaveCompteRequest request, StreamObserver<SaveCompteResponse> responseObserver) {
        // Récupérer les informations du compte depuis la requête
        var compteReq = request.getCompte();
        // Créer un nouvel objet 'Compte' (entité JPA, pas gRPC)
        var compte = new com.example.grpc2.entities.Compte();
        compte.setSolde(compteReq.getSolde());
        compte.setDateCreation(compteReq.getDateCreation());
        compte.setType(compteReq.getType().name());

        // Sauvegarder le compte via le service
        var savedCompte = compteService.saveCompte(compte);

        // Convertir le compte sauvegardé en objet gRPC pour la réponse
        var grpcCompte = Compte.newBuilder()
                .setSolde(savedCompte.getSolde())
                .setDateCreation(savedCompte.getDateCreation())
                .setType(TypeCompte.valueOf(savedCompte.getType()))
                .build();

        // Envoyer la réponse avec le compte sauvegardé
        responseObserver.onNext(SaveCompteResponse.newBuilder()
                .setCompte(grpcCompte)
                .build());
        responseObserver.onCompleted();
    }
    @Override
    public void deleteCompteById(DeleteCompteByIdRequest request, StreamObserver<DeleteCompteByIdResponse> responseObserver) {
        String compteId = request.getId();
        Boolean success =true;
        try {
            compteService.deleteCompteById(compteId); // Implémenter cette méthode dans votre service
        } catch (Exception e) {
            success = false; // En cas d'erreur, marquer l'opération comme échouée
        }
        // Envoyer la réponse gRPC
        responseObserver.onNext(DeleteCompteByIdResponse.newBuilder()
                .setSuccess(success)
                .build());
        responseObserver.onCompleted();
    }
    @Override
    public void getComptesByType(GetComptesByTypeRequest request, StreamObserver<GetComptesByTypeResponse> responseObserver) {
        // Récupérer le type de compte depuis la requête
        String typeCompte = request.getType().name();

        // Appeler la couche service pour récupérer les comptes correspondants
        List<com.example.grpc2.entities.Compte> comptes = compteService.GetComptesByType(typeCompte);

        // Convertir la liste des entités JPA en objets gRPC
        List<Compte> grpcComptes = comptes.stream()
                .map(compte -> Compte.newBuilder()
                        .setId(String.valueOf(compte.getId()))
                        .setSolde(compte.getSolde())
                        .setDateCreation(compte.getDateCreation())
                        .setType(TypeCompte.valueOf(compte.getType()))
                        .build())
                .collect(Collectors.toList());

        // Construire et envoyer la réponse
        responseObserver.onNext(GetComptesByTypeResponse.newBuilder()
                .addAllComptes(grpcComptes)
                .build());
        responseObserver.onCompleted();
    }

}