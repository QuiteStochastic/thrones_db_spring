package thrones_db_spring.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import thrones_db_spring.model.services.SerializationService;

import java.util.List;

/**
 * Created by oliverl1
 */
public class SearchResult {

    @JsonView(SerializationService.Compact.class)
    private String query;
    @JsonView(SerializationService.Compact.class)
    private int resultCount;
    @JsonView(SerializationService.Compact.class)
    private List<?> results;

    public SearchResult(String query, int resultCount,List<?> results){
        this.query=query;
        this.resultCount=resultCount;
        this.results=results;
    }


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<?> getResults() {
        return results;
    }

    public void setResults(List<?> results) {
        this.results = results;
    }
}
