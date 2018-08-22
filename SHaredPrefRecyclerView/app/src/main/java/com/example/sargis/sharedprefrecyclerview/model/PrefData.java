package com.example.sargis.sharedprefrecyclerview.model;

public final class PrefData {
    public String key;
    public String value;

    public PrefData() {
    }

    public PrefData(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof PrefData && key.equals(((PrefData) object).key);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
