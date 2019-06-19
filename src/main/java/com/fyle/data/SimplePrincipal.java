package com.fyle.data;

import java.security.Principal;
import java.util.Objects;

public class SimplePrincipal implements Principal {
    private String name;

    public SimplePrincipal(String username) {
        this.name = username;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimplePrincipal that = (SimplePrincipal) o;
        return Objects.equals(name, that.name);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SimplePrincipal{" +
                "name='" + name+ '\'' +
                '}';
    }
}
