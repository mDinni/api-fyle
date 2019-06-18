package com.fyle.data;

import java.util.Objects;

public class Banks {
    private String name;
    private long id;

    private Banks() {}

    public static class Builder {
        private Banks bankToBuild;
        
        public Builder() {
            bankToBuild = new Banks();
        }

        public Banks build() {
            Banks builtBank = bankToBuild;
             bankToBuild = new Banks();

             return builtBank;
        }

        public Builder withName(String name) {
            this.bankToBuild.name = name;
            return this;
        }

        public Builder withId(long id) {
            this.bankToBuild.id = id;
            return this;
        }

    }


    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banks banks = (Banks) o;
        return id == banks.id &&
                Objects.equals(name, banks.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "Banks{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
