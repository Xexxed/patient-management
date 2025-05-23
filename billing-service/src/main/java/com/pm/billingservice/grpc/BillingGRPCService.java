package com.pm.billingservice.grpc;


import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@GrpcService
public class BillingGRPCService extends BillingServiceImplBase {


    private static final Logger log = LoggerFactory.getLogger(BillingGRPCService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver){
        log.info("CreateBillingAccount request received{}", billingRequest.toString());


        BillingResponse billingResponse = BillingResponse.newBuilder().setAccountId("123456789").setStatus("SUCCESS").build();
        responseObserver.onNext(billingResponse);
        responseObserver.onCompleted();
    }




}
