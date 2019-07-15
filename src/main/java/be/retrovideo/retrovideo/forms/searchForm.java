package be.retrovideo.retrovideo.forms;

import javax.validation.constraints.NotNull;

public class searchForm {
    @NotNull
    private final String zoekterm;

    public searchForm(@NotNull String zoekterm) {
        this.zoekterm = zoekterm;
    }

    public String getZoekterm() {
        return zoekterm;
    }
}
