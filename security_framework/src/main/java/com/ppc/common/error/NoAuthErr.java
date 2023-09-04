package com.ppc.common.error;

public class NoAuthErr extends RuntimeException{
    public NoAuthErr(){
        super("noAuth");
    }
}
