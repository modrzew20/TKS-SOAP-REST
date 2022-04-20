package com.example.applicationsoap.soapEndpoints;

import com.example.applicationsoap.soapAdapters.LaneServiceSoapAdapter;
import com.example.applicationsoap.soapmodel.lanemodel.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
public class LaneEndpoint {
    private static final String URI = "http://example.com/applicationsoap/soapmodel/lanemodel";

    LaneServiceSoapAdapter laneServiceSoapAdapter;

    @PayloadRoot(namespace = URI, localPart = "ReadAllLaneRequest")
    @ResponsePayload
    public ReadAllLaneResponse readAllLaneResponse(@RequestPayload ReadAllLaneRequest request) {
        ReadAllLaneResponse response = new ReadAllLaneResponse();
        response.getLaneSoap().addAll(laneServiceSoapAdapter.readAllLane());
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "ReadOneLaneRequest")
    @ResponsePayload
    public ReadOneLaneResponse readOneLaneResponse(@RequestPayload ReadOneLaneRequest request) {
        ReadOneLaneResponse response = new ReadOneLaneResponse();
        response.setLaneSoap(laneServiceSoapAdapter.readOneLane(UUID.fromString(request.getUuid())));
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "CreateLaneRequest")
    @ResponsePayload
    public CreateLaneResponse createLaneResponse(@RequestPayload CreateLaneRequest request) {
        CreateLaneResponse response = new CreateLaneResponse();
        response.setLaneSoap(laneServiceSoapAdapter.addLane(request.getLaneType().value()));
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "UpdateLaneRequest")
    @ResponsePayload
    public UpdateLaneResponse updateLaneResponse(@RequestPayload UpdateLaneRequest request) {
        UpdateLaneResponse response = new UpdateLaneResponse();
        response.setLaneSoap(laneServiceSoapAdapter.updateLane(UUID.fromString(request.getUuid()),request.getLaneType().value()));
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "DeleteLaneRequest")
    @ResponsePayload
    public DeleteLaneResponse deleteLaneResponse(@RequestPayload DeleteLaneRequest request) {
        DeleteLaneResponse response = new DeleteLaneResponse();
        response.setLaneSoap(laneServiceSoapAdapter.deleteLine(UUID.fromString(request.getUuid())));
        return response;
    }





}