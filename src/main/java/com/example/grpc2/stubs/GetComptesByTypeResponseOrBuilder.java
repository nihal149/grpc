// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CompteService.proto

package com.example.grpc2.stubs;

public interface GetComptesByTypeResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:GetComptesByTypeResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Liste des comptes correspondants
   * </pre>
   *
   * <code>repeated .Compte comptes = 1;</code>
   */
  java.util.List<com.example.grpc2.stubs.Compte> 
      getComptesList();
  /**
   * <pre>
   * Liste des comptes correspondants
   * </pre>
   *
   * <code>repeated .Compte comptes = 1;</code>
   */
  com.example.grpc2.stubs.Compte getComptes(int index);
  /**
   * <pre>
   * Liste des comptes correspondants
   * </pre>
   *
   * <code>repeated .Compte comptes = 1;</code>
   */
  int getComptesCount();
  /**
   * <pre>
   * Liste des comptes correspondants
   * </pre>
   *
   * <code>repeated .Compte comptes = 1;</code>
   */
  java.util.List<? extends com.example.grpc2.stubs.CompteOrBuilder> 
      getComptesOrBuilderList();
  /**
   * <pre>
   * Liste des comptes correspondants
   * </pre>
   *
   * <code>repeated .Compte comptes = 1;</code>
   */
  com.example.grpc2.stubs.CompteOrBuilder getComptesOrBuilder(
      int index);
}
