package com.foo.hellolambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Created by user on 18-Sep-18.
 */
public class HelloHandler implements RequestHandler<HelloInput,HelloOutput> {

    @Override
    public HelloOutput handleRequest(HelloInput helloInput, Context context) {
        HelloOutput o = new HelloOutput();
        o.setMessage("Hello " + helloInput.getName());
        o.setFunctionName(context.getFunctionName());
        o.setMemoryLimit(context.getMemoryLimitInMB());

        context.getLogger().log(helloInput.getName() + " said Hello");

        return o;
    }
}
