package com.fyle.data;

import java.util.Objects;

public class Branches {
    private String ifsc;
    private long bank_id;
    private String branch;
    private String address;
    private String city;
    private String district;
    private String state;

    private Branches() {}

    public static class Builder {
        private Branches branchToBuild;

        public Builder() {
            branchToBuild = new Branches();
        }

        public Branches build() {
            Branches builtBranch = branchToBuild;
            branchToBuild = new Branches();
            return builtBranch;
        }

        public Builder withIfsc(String ifsc) {
            this.branchToBuild.ifsc = ifsc;
            return this;
        }

        public Builder withBranch(String branch) {
            this.branchToBuild.branch = branch;
            return this;
        }

        public Builder withAddress(String address) {
            this.branchToBuild.address = address;
            return this;
        }
        public Builder withBank_id(long bank_id) {
            this.branchToBuild.bank_id = bank_id;
            return this;
        }
        public Builder withCity(String city) {
            this.branchToBuild.city = city;
            return this;
        }
        public Builder withDistrict(String district) {
            this.branchToBuild.district = district;
            return this;
        }
        public Builder withState(String state) {
            this.branchToBuild.state = state;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branches branches = (Branches) o;
        return bank_id == branches.bank_id &&
                Objects.equals(ifsc, branches.ifsc) &&
                Objects.equals(branch, branches.branch) &&
                Objects.equals(address, branches.address) &&
                Objects.equals(city, branches.city) &&
                Objects.equals(district, branches.district) &&
                Objects.equals(state, branches.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ifsc, bank_id, branch, address, city, district, state);
    }

    public String getIfsc() {
        return ifsc;
    }

    public long getBank_id() {
        return bank_id;
    }

    public String getBranch() {
        return branch;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Branches{" +
                "ifsc='" + ifsc + '\'' +
                ", bank_id=" + bank_id +
                ", branch='" + branch + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
