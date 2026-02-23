package com.xpeho.xpeho_formation_spring.data.models;

import java.util.List;

public class OmdbSearchResponse {
    private List<OmdbMovie> Search;
    private String totalResults;
    private String Response;

    // Getters & Setters
    public List<OmdbMovie> getSearch() {
        return Search;
    }

    public void setSearch(List<OmdbMovie> Search) {
        this.Search = Search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String Response) {
        this.Response = Response;
    }
}
