//package com.pmm;

import com.pmm.User;
import com.pmm.userGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.jaegertracing.internal.JaegerTracer;
import io.opentracing.Tracer;
import io.opentracing.contrib.ServerTracingInterceptor;

import static java.lang.Thread.*;
public class client {
    public client() {
    }

    public static void main(String[] args) throws InterruptedException {
        String GRPC_SERVER = System.getenv().getOrDefault("GRPC_SERVER", "grpc-server");
        System.out.println("Connecting to Grpc Server at : "  + GRPC_SERVER);
        ManagedChannel test = ManagedChannelBuilder.forAddress( GRPC_SERVER , 10000).usePlaintext().build();
        //ManagedChannel test1 = ManagedChannelBuilder.forTarget("grpc-server.mpl.internal",443).usePlaintext().build();

        while(true) {
            String USER = System.getenv().getOrDefault("USER", "swapnil");
           User.loginrequest req = User.loginrequest.newBuilder().setUsername(USER).setPassword(USER).build();
  //         Tracer tracer = new JaegerTracer.Builder("GrpcClient").build();
  //         ServerTracingInterceptor tracingInterceptor = new ServerTracingInterceptor(tracer);

           userGrpc.userBlockingStub stub = userGrpc.newBlockingStub(test);
           User.Apiresponse res = stub.login(req);


           System.out.print("response is - ");
           System.out.println(res.getResposebody());
           System.out.println(res.getResposecode());
           sleep(2000);
       }
    }
}
