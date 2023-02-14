package wissen.rest.helper;

import lombok.Data;

@Data
public class PagedListInfo {
    private int page;
    private int size;
    private String sortBy;
    private boolean ascending = false;
}
