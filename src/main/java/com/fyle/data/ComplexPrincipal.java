package com.fyle.data;

import java.security.Principal;

/**
 * ComplexPrincipal
 */
public class ComplexPrincipal implements Principal{

    private final String admin;

    public ComplexPrincipal(String admin) {
        this.admin = admin;
    }

    @Override
    public String getName() {
        return admin;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((admin == null) ? 0 : admin.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ComplexPrincipal other = (ComplexPrincipal) obj;
        if (admin == null) {
            if (other.admin != null)
                return false;
        } else if (!admin.equals(other.admin))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ComplexPrincipal [admin=" + admin + "]";
    }
}