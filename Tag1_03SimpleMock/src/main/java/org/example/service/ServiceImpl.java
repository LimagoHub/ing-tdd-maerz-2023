package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dependency.Dependency;


@RequiredArgsConstructor
public class ServiceImpl {

    private final Dependency dependency;// Stub, Mock, Spy, Fake
    public void abc() {

        dependency.foo("Hallo");
    }

    public int xyz(int x) {
        return dependency.bar() + x;
    }

    public int sdf(String text) {
        return dependency.foobar(text.toUpperCase()) + 3;
    }
}
