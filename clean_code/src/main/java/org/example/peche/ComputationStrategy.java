package org.example.peche;

import com.sun.net.httpserver.Authenticator;

import javax.naming.Context;

interface ComputationStrategy<T extends Number, C extends Context> {
    <R extends Authenticator.Result> R compute(T v, C c);
}
