package com.foo.hellolambda;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaAsyncClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.google.gson.Gson;

import static java.nio.charset.StandardCharsets.UTF_8;
/**
 * Created by user on 21-Sep-18.
 */
public class HelloLambdaCaller {
    public static void main(String... args) {
        String name = "Student";
        //this can be found in https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/Concepts.RegionsAndAvailabilityZones.html
        //my region is north verginir
        String region = "us-east-1";

        if (args.length > 0) {
            name = args[0];
        }
        if (args.length > 1) {
            region = args[1];
        }

        //please be noted in your credentials file it is called helloLambda
        final AWSLambdaAsyncClient client =
                new AWSLambdaAsyncClient(new ProfileCredentialsProvider("helloLambda")).withRegion(Regions.fromName(region));
        final HelloInput helloInput = new HelloInput();
        helloInput.setName(name);

        final Gson gson = new Gson();
        //please be noted my lambda function is called "hellolambda"
        final InvokeRequest request = new InvokeRequest().withFunctionName("hellolambda").withPayload(gson.toJson(helloInput));
        final InvokeResult result = client.invoke(request);

        final HelloOutput output = gson.fromJson(UTF_8.decode(result.getPayload()).toString(), HelloOutput.class);

        System.out.println("Message" + output.getMessage());
        System.out.println("function name" + output.getFunctionName());
        System.out.println("Memory" + output.getMemoryLimit());
    }
}
