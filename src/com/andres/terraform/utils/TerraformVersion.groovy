package com.andres.terraform.utils

class TerraformVersion implements Comparable<TerraformVersion>, Serializable {

    public static final String MIN_VERSION_SUPPORTED = "v0.11.00"
    public static final String MAX_VERSION_SUPPORTED = "v0.12.7"
    private String version

    final String get() {
        return this.version
    }

    TerraformVersion(String version) {
        if (version == null)
            throw new IllegalArgumentException("TerraformVersion can not be null")
        if (!version.matches("v[0-9]+(\\.[0-9]+)*"))
            throw new IllegalArgumentException("Invalid version format ${version}")
        this.version = version.replace("v", "")
    }

    @Override
    int compareTo(TerraformVersion that) {
        if (that == null)
            return 1
        String[] thisParts = this.get().split("\\.")
        String[] thatParts = that.get().split("\\.")
        int length = Math.max(thisParts.length, thatParts.length)
        for (int i = 0; i < length; i++) {
            int thisPart = i < thisParts.length ?
                    Integer.parseInt(thisParts[i]) : 0
            int thatPart = i < thatParts.length ?
                    Integer.parseInt(thatParts[i]) : 0
            if (thisPart < thatPart)
                return -1
            if (thisPart > thatPart)
                return 1
        }
        return 0
    }

    @Override
    boolean equals(Object that) {
        if (this == that)
            return true
        if (that == null)
            return false
        if (this.getClass() != that.getClass())
            return false
        return this.compareTo((TerraformVersion) that) == 0
    }

    boolean between(TerraformVersion start, TerraformVersion end) {
        if (this.compareTo(start) < 0)
            return false
        if (this.compareTo(end) > 0)
            return false
        return true
    }

}