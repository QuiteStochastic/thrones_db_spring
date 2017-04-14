package thrones_db_spring.model;

import java.util.List;

/**
 * Created by oliverl1
 */
public class SearchResult {

    private String query;
    private int resultCount;
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
